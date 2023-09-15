package com.github.fank243.study.system.interceptor;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.Interceptor;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.TimeConstants;
import com.github.fank243.study.core.model.redis.RedisService;
import com.github.fank243.study.core.properties.StudyProperties;
import com.github.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.study.oauth2.api.domain.vo.OauthAccessTokenVO;
import com.github.fank243.study.oauth2.api.service.IOauth2Service;

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
        if (obj instanceof OauthAccessTokenVO oauthAccessTokenVO) {
            // 有请求就刷新令牌
            IOauth2Service oauth2Service = SpringUtil.getBean(IOauth2Service.class);
            ResultInfo<OauthAccessTokenVO> result =
                oauth2Service.refreshToken(Oauth2Constants.GrantType.REFRESH_TOKEN.name().toLowerCase(),
                    oauthAccessTokenVO.getRefreshToken(), StudyProperties.clientId, StudyProperties.clientSecret);
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
