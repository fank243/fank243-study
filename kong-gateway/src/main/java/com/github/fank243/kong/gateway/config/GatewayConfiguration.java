/*
 * Copyright (c) 2024 Kong@杰少
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

package com.github.fank243.kong.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.gateway.web.filter.ApiLogFilter;
import com.github.fank243.kong.gateway.web.filter.SecurityFilter;
import com.github.fank243.kong.gateway.web.filter.ValidateImageCodeFilter;
import com.github.fank243.kong.gateway.web.handler.GatewayExceptionHandler;
import com.github.fank243.kong.gateway.web.handler.ImageCodeHandler;

/**
 * saToken注册统一鉴权
 *
 * @author FanWeiJie
 * @since 2022-05-11 10:46:17
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {
    @Bean
    public GatewayExceptionHandler gatewayExceptionHandler() {
        return new GatewayExceptionHandler();
    }

    @Bean
    public ImageCodeHandler imageCodeHandler(RedisService redisService) {
        return new ImageCodeHandler(redisService);
    }

    @Bean
    public ValidateImageCodeFilter validateImageCodeFilter(RedisService redisService) {
        return new ValidateImageCodeFilter(redisService);
    }

    @Bean
    public ApiLogFilter apiLogFilter() {
        return new ApiLogFilter();
    }

    @Bean
    public SecurityFilter securityFilter(RedisService redisService) {
        return new SecurityFilter(redisService);
    }

}
