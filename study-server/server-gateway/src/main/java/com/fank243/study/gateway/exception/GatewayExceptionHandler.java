package com.fank243.study.gateway.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.common.enums.ResultCodeEnum;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.AuthException;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.gateway.utils.ResponseUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Slf4j
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ex.printStackTrace();
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        HttpStatus status = response.getStatusCode();

        ResultInfo<String> result;
        if (ex instanceof NotFoundException) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            result = ResultInfo.err503();
        } else if (ex instanceof ResponseStatusException responseStatusException) {
            status = responseStatusException.getStatus();
            result = ResultInfo.error(ResultCodeEnum.R500.getMessage(), responseStatusException.getMessage());
        } else if (ex instanceof AuthException) {
            status = HttpStatus.UNAUTHORIZED;
            result = ResultInfo.err401(ex.getMessage());
        } else if (ex instanceof BizException) {
            result = ResultInfo.fail(ex.getMessage());
        } else {
            result = ResultInfo.error("系统内部错误，请稍后重试", ex.getMessage());
        }

        log.error("[网关异常处理]请求路径:{}，异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());

        return ResponseUtils.printJson(response, status, result);
    }
}