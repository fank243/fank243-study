package com.github.fank243.study.gateway.web.filter;

import java.util.Objects;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.github.fank243.common.pattern.RegexExtPool;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.Constants;
import com.github.fank243.study.core.constants.TimeConstant;
import com.github.fank243.study.core.service.RedisService;
import com.github.fank243.study.gateway.constants.FilterOrderConstant;
import com.github.fank243.study.gateway.utils.ReactiveUtils;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Request Params Filter
 *
 * @author FanWeiJie
 * @since 2021-07-24 15:45:46
 */
@Slf4j
@RequiredArgsConstructor
public class SecurityFilter implements GlobalFilter, Ordered {

    private final RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String userAgent = Objects.requireNonNull(request.getHeaders().get(HttpHeaders.USER_AGENT)).get(0);
        if (userAgent.matches(RegexExtPool.USER_AGENT)) {
            return ReactiveUtils.renderJson(response, ResultInfo.err401("请求未授权"));
        }

        String uuid = StrUtil.uuid();
        ServerHttpRequest serverHttpRequest = request.mutate().header(Constants.SECURITY_TOKEN, uuid).build();
        redisService.setObj(CacheConstants.SECURITY_TOKEN + uuid, uuid, TimeConstant.MINUTE_5);
        return chain.filter(exchange.mutate().request(serverHttpRequest.mutate().build()).build());
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }

}
