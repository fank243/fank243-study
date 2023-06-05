package com.github.fank243.study.core.interceptor;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.study.core.annotation.Interceptor;
import com.github.fank243.study.core.constants.InterceptorOrderConstant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Skywalking TraceId 拦截器
 *
 * @author FanWeiJie
 * @since 2021-07-16 20:29:08
 */
@Interceptor(value = "traceIdInterceptor", order = InterceptorOrderConstant.TRACE_ID)
@ConditionalOnWebApplication(type = SERVLET)
public class TraceIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
        @Nullable Object handler) {
        MDC.put("trace_id", TraceContext.traceId());
        MDC.put("span_id", String.valueOf(TraceContext.spanId()));
        return true;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
        @Nullable Object handler, Exception ex) {
        MDC.clear();
    }
}
