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

package com.github.fank243.kong.core.interceptor;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.kong.core.annotation.Interceptor;
import com.github.fank243.kong.core.constants.InterceptorOrderConstant;

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
