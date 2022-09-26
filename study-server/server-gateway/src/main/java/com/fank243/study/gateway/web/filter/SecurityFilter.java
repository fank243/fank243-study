package com.fank243.study.gateway.web.filter;

import javax.annotation.Resource;

import com.fank243.study.common.constants.RedisConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.common.constants.Constants;
import com.fank243.study.common.constants.TimeConstant;
import com.fank243.study.ds.service.RedisService;
import com.fank243.study.gateway.constants.FilterOrderConstant;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Request Params Filter
 *
 * @author FanWeiJie
 * @since 2021-07-24 15:45:46
 */
@Slf4j
@Component
public class SecurityFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uuid = StrUtil.uuid();
        ServerHttpRequest request = exchange.getRequest().mutate().header(Constants.SECURITY_TOKEN, uuid).build();
        redisService.setObj(RedisConstants.SECURITY_TOKEN + uuid, uuid, TimeConstant.MINUTE_5);
        return chain.filter(exchange.mutate().request(request.mutate().build()).build());
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }

}
