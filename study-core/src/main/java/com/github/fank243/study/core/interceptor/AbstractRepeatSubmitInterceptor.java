package com.github.fank243.study.core.interceptor;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.lang.reflect.Method;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.Interceptor;
import com.github.fank243.study.core.annotation.RepeatSubmit;
import com.github.fank243.study.core.constants.InterceptorOrderConstant;
import com.github.fank243.study.core.utils.WebUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 防重复提交拦截器抽象父类
 *
 * <p>该类是一个抽象父类，用于实现防止重复提交的拦截器功能。</p>
 *
 * <p>该拦截器通过拦截器注解和条件注解来实现，用于防止用户在Web应用程序中进行重复提交操作。</p>
 *
 * @author FanWeiJie
 * @since 2022-06-10 09:56:23
 */
@Interceptor(value = "repeatSubmitInterceptor", order = InterceptorOrderConstant.REPEAT_SUBMIT)
@ConditionalOnWebApplication(type = SERVLET)
public abstract class AbstractRepeatSubmitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();

            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation == null) {
				return true;
            }

            // 根据不同的验证规则执行相应的验证逻辑
            if (this.isRepeatSubmit(request, annotation)) {
                WebUtils.renderJson(response, ResultInfo.err429(annotation.message()));
				return false;
            }
        }
		return true;
    }

    /**
     * 重复提交验证接口，由子类去实现
     * 
     * @param request HttpServletRequest
     * @param annotation RepeatSubmit
     * @return 验证结果
     * @throws Exception Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws Exception;
}
