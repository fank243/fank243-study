package com.fank243.study.gateway.web.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.common.core.constants.enums.ResultCodeEnum;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.gateway.utils.ReactiveUtils;

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
        log.error("[网关异常处理]请求路径:{}，异常信息:{}", exchange.getRequest().getPath(), ex.getMessage(), ex);

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
            if (HttpStatus.NOT_FOUND.value() == status.value()) {
                result = ResultInfo.err404();
            } else {
                result = ResultInfo.error(ResultCodeEnum.R500.getMessage(), responseStatusException.getMessage());
            }
        } else if (ex instanceof AuthException) {
            status = HttpStatus.UNAUTHORIZED;
            result = ResultInfo.err401(ex.getMessage());
//        } else if (ex instanceof SaTokenException) {
//            if (ex.getCause() instanceof NotLoginException) {
//                status = HttpStatus.UNAUTHORIZED;
//                result = ResultInfo.err401(ResultCodeEnum.R401.getMessage(), ex.getCause().getMessage());
//            } else if (ex.getCause() instanceof NotRoleException || ex instanceof NotPermissionException) {
//                status = HttpStatus.FORBIDDEN;
//                result = ResultInfo.err403(ResultCodeEnum.R403.getMessage(), ex.getCause().getMessage());
//            } else {
//                status = HttpStatus.FORBIDDEN;
//                result = ResultInfo.err403(ResultCodeEnum.R403.getMessage(), ex.getCause().getMessage());
//            }
        } else if (ex instanceof BizException) {
            result = ResultInfo.fail(ex.getMessage());
        } else {
            result = ResultInfo.error("系统内部错误，请稍后重试", ex.getMessage());
        }

        return ReactiveUtils.renderJson(response, status, result);
    }
}
