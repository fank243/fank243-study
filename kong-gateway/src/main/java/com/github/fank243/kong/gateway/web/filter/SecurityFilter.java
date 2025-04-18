/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.gateway.web.filter;

import java.util.Objects;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.github.fank243.kong.tool.pattern.RegexExtPool;
import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.Constants;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.gateway.constants.FilterOrderConstant;
import com.github.fank243.kong.gateway.utils.ReactiveUtils;

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
        redisService.setObj(CacheConstants.SECURITY_TOKEN + uuid, uuid, TimeConstants.MINUTE_5);
        return chain.filter(exchange.mutate().request(serverHttpRequest.mutate().build()).build());
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }

}
