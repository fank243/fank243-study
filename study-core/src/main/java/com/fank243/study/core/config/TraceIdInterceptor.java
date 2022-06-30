//package com.fank243.study.core.config;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.MDC;
//import org.springframework.lang.Nullable;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.fank243.study.core.annotation.Interceptor;
//import com.fank243.study.core.constant.InterceptorOrderConstant;
//
//import brave.Span;
//import brave.Tracer;
//
///**
// * Skywalking TraceId 拦截器
// *
// * @author FanWeiJie
// * @since 2021-07-16 20:29:08
// */
//@Interceptor(order = InterceptorOrderConstant.TRACE_ID)
//public class TraceIdInterceptor implements HandlerInterceptor {
//
//    @Resource
//    private Tracer tracer;
//
//    @Override
//    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
//        @Nullable Object handler) {
//        Span currentSpan = tracer.currentSpan();
//        if (currentSpan != null) {
//            MDC.put("traceId", currentSpan.context().traceIdString());
//            MDC.put("spanId", currentSpan.context().spanIdString());
//        }
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
//        @Nullable Object handler, Exception ex) {
//        MDC.remove("traceId");
//    }
//}
