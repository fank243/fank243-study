package com.fank243.study.gateway.web.handler;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.common.core.utils.ResultInfo;
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
        ResultInfo<?> result = ReactiveUtils.handlerException(response, ex);

        return ReactiveUtils.renderJson(response, result);
    }
}
