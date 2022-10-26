package com.github.fank243.study.core.web.interceptor;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.Interceptor;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.Constants;
import com.github.fank243.study.core.constants.InterceptorOrderConstant;
import com.github.fank243.study.core.utils.WebUtils;

import cn.hutool.core.util.StrUtil;

/**
 * 安全请求拦截器
 * 
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@Interceptor(value = "securityInterceptor",
    exclude = {"/login/**", "/oauth2/**", "/getToken/**", "/callback/**", "/view/**"},
    order = InterceptorOrderConstant.SECURITY)
@ConditionalOnWebApplication(type = SERVLET)
public class SecurityInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull Object handler) throws Exception {
        String securityToken = request.getHeader(Constants.SECURITY_TOKEN);
        String securityFeignValue = request.getHeader(Constants.SECURITY_FEIGN_KEY);
        if (StrUtil.isBlank(securityToken) && StrUtil.isBlank(securityFeignValue)) {
            WebUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }
        String securityTokenWithRedis = redisTemplate.opsForValue().get(CacheConstants.SECURITY_TOKEN + securityToken);
        boolean isOkWithToken = StrUtil.isNotBlank(securityTokenWithRedis);
        boolean isOkWithFeign = StrUtil.equalsIgnoreCase(securityFeignValue, Constants.SECURITY_FEIGN_VALUE);
        if (!isOkWithToken && !isOkWithFeign) {
            WebUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
