package com.fank243.study.core.config;

import com.fank243.study.core.annotation.Interceptor;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Skywalking TraceId 拦截器
 * 
 * @author FanWeiJie
 * @since 2021-07-16 20:29:08
 */
@Interceptor(order = -100)
public class TraceIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
        @Nullable Object handler) {
        MDC.put("traceId", TraceContext.traceId());
        return true;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
        @Nullable Object handler, Exception ex) {
        MDC.remove("traceId");
    }
}
