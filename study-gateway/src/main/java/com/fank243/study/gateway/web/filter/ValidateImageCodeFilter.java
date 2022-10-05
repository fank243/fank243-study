/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.fank243.study.gateway.web.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.common.core.constants.CacheConstants;
import com.fank243.study.common.core.constants.ServerConstants;
import com.fank243.study.common.core.service.RedisService;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.gateway.constants.FilterOrderConstant;
import com.fank243.study.gateway.utils.ReactiveUtils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * The type Validate code gateway filter.
 *
 * @author lengleng
 * @date 2018 /7/4 验证码处理
 */
@Slf4j
@RequiredArgsConstructor
public class ValidateImageCodeFilter implements GlobalFilter, Ordered {

    private final RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        boolean isLogin =
            CharSequenceUtil.containsAnyIgnoreCase(request.getURI().getPath(), ServerConstants.BASE_URI_SYSTEM_LOGIN);

        // 不是登录请求，直接向下执行
        if (!isLogin) {
            return chain.filter(exchange);
        }

        ResultInfo<String> result = checkCode(request);
        if (!result.isSuccess()) {
            return ReactiveUtils.renderJsonWithOK(exchange.getResponse(), result);
        }

        return chain.filter(exchange);
    }

    private ResultInfo<String> checkCode(ServerHttpRequest request) {
        String imgCode = request.getQueryParams().getFirst("imgCode");
        if (StrUtil.isBlank(imgCode)) {
            return ResultInfo.err400("图形验证码不能为空");
        }
        String randomStr = request.getQueryParams().getFirst("randomStr");

        String key = CacheConstants.IMG_CODE_KEY + randomStr;
        Object imgCodeObj = redisService.getObj(key);
        redisService.delete(key);

        if (ObjectUtil.isEmpty(imgCodeObj) || !imgCode.equalsIgnoreCase(String.valueOf(imgCodeObj))) {
            return ResultInfo.err400("图形验证码不正确");
        }
        return ResultInfo.ok();
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }
}
