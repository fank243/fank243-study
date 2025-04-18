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

package com.github.fank243.kong.core.model.security;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.annotation.Interceptor;
import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.InterceptorOrderConstant;
import com.github.fank243.kong.core.utils.WebUtils;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 安全请求拦截器
 *
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@Interceptor(value = "securityInterceptor",
    exclude = {"/login/**", "/oauth2/**", "/getToken/**", "/callback/**", "/file/**"},
    order = InterceptorOrderConstant.SECURITY)
@ConditionalOnWebApplication(type = SERVLET)
public class SecurityInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${kong.security.feign.header.name:SecurityToken}")
    private String securityFeignHeaderName;

    @Value("${kong.security.feign.header.value:}")
    private String securityFeignHeaderValue;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull Object handler) throws Exception {
        String securityToken = request.getHeader(securityFeignHeaderName);
        String securityFeignValue = request.getHeader(securityFeignHeaderName);
        if (StrUtil.isBlank(securityToken) || StrUtil.isBlank(securityFeignValue)) {
            WebUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }
        String securityTokenWithRedis = redisTemplate.opsForValue().get(CacheConstants.SECURITY_TOKEN + securityToken);
        boolean isOkWithToken = StrUtil.isNotBlank(securityTokenWithRedis);
        boolean isOkWithFeign = StrUtil.equalsIgnoreCase(securityFeignValue, securityFeignHeaderValue);
        if (!isOkWithToken && !isOkWithFeign) {
            WebUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
