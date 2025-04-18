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

package com.github.fank243.kong.system.interceptor;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.annotation.Interceptor;
import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.core.properties.KongProperties;
import com.github.fank243.kong.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthAccessTokenDTO;
import com.github.fank243.kong.oauth2.api.service.IOauth2Service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Oauth2 访问令牌自动刷新拦截器
 *
 * @author FanWeiJie
 * @since 2022-10-03 11:33:02
 */
@Slf4j
@Interceptor(value = "tokenRefreshFilter", include = "/system/**")
public class TokenRefreshInterceptor implements HandlerInterceptor {

    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull Object handler) throws Exception {
        if (!request.getRequestURI().startsWith(Oauth2Constants.SYSTEM)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        if (!StpUtil.isLogin()) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        String userId = StpUtil.getLoginIdAsString();
        String key = CacheConstants.OAUTH2_TOKEN + userId;
        if (redisService.getExpire(key) > TimeConstants.MINUTE_5) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        Object obj = redisService.getObj(key);
        if (obj instanceof OauthAccessTokenDTO oauthAccessTokenDTO) {
            // 有请求就刷新令牌
            IOauth2Service oauth2Service = SpringUtil.getBean(IOauth2Service.class);
            ResultInfo<OauthAccessTokenDTO> result =
                oauth2Service.refreshToken(Oauth2Constants.GrantType.REFRESH_TOKEN.name().toLowerCase(),
                    oauthAccessTokenDTO.getRefreshToken(), KongProperties.clientId, KongProperties.clientSecret);
            if (!result.isSuccess()) {
                log.info("【令牌刷新拦截器】刷新令牌失败：{}", result);
            } else {
                // 覆写redis
                redisService.setObj(key, result.getPayload(), TimeConstants.MINUTE_30);
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
