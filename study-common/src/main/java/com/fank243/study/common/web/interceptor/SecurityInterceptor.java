package com.fank243.study.common.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fank243.study.common.annotation.Interceptor;
import com.fank243.study.common.constants.Constants;
import com.fank243.study.common.constants.InterceptorOrderConstant;
import com.fank243.study.common.constants.RedisConstants;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.common.utils.ServletUtils;

import cn.hutool.core.util.StrUtil;

/**
 * 安全请求拦截器
 * 
 * @author FanWeiJie
 * @since 2022-06-10 10:10:49
 */
@Interceptor(value = "securityInterceptor", exclude = {"/oauth2/**", "/view/**"},
    order = InterceptorOrderConstant.SECURITY)
public class SecurityInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
        @NotNull Object handler) throws Exception {
        String securityToken = request.getHeader(Constants.SECURITY_TOKEN);
        String securityFeignValue = request.getHeader(Constants.SECURITY_FEIGN_KEY);
        if (StrUtil.isBlank(securityToken) && StrUtil.isBlank(securityFeignValue)) {
            ServletUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }
        String securityTokenWithRedis = redisTemplate.opsForValue().get(RedisConstants.SECURITY_TOKEN + securityToken);
        boolean isOkWithToken = StrUtil.isNotBlank(securityTokenWithRedis);
        boolean isOkWithFeign = StrUtil.equalsIgnoreCase(securityFeignValue, Constants.SECURITY_FEIGN_VALUE);
        if (!isOkWithToken && !isOkWithFeign) {
            ServletUtils.renderJson(response, ResultInfo.err401("请求未授权"));
            return Boolean.FALSE;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
