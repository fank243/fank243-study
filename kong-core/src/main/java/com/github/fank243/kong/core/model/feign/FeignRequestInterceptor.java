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

package com.github.fank243.kong.core.model.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * OpenFeign 安全认证配置
 * 
 * @author FanWeiJie
 * @since 2022-09-26 10:40:01
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${kong.security.feign.header.name:SecurityToken}")
    private String securityFeignHeaderName;

    @Value("${kong.security.feign.header.value:}")
    private String securityFeignHeaderValue;

    @Override
    public void apply(RequestTemplate template) {
        template.header(securityFeignHeaderName, securityFeignHeaderValue);
    }
}
