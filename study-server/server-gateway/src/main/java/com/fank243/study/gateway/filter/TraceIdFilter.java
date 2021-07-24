package com.fank243.study.gateway.filter;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Skywalking TraceId 拦截器
 * 
 * @author FanWeiJie
 * @since 2021-07-24 15:45:46
 */
@Order(-1)
@Component
public class TraceIdFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MDC.put("traceId", TraceContext.traceId());
        return chain.filter(exchange);
    }
}
