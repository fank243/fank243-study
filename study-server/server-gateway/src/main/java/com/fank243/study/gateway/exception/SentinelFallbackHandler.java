package com.fank243.study.gateway.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.gateway.utils.ReactiveUtils;

import lombok.NonNull;
import reactor.core.publisher.Mono;

/**
 * 自定义限流异常处理
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Configuration
public class SentinelFallbackHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        if (!BlockException.isBlockException(ex)) {
            return Mono.error(ex);
        }
        return ReactiveUtils.renderJson(response, HttpStatus.TOO_MANY_REQUESTS, ResultInfo.fail("请求过于频繁，请稍后再试"));
    }
}
