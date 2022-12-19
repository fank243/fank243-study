package com.github.fank243.study.core.config;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.fank243.study.core.annotation.Interceptor;

/**
 * Mvc Configuration
 * 
 * @author FanWeiJie
 * @since 2021-07-16 20:44:11
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = SERVLET)
public class WebMvcConfiguration implements WebMvcConfigurer {

    /** 可选注入，防止子项目启动失败 **/
    private final List<HandlerInterceptor> handlerInterceptors;

    public WebMvcConfiguration(List<HandlerInterceptor> handlerInterceptors) {
        this.handlerInterceptors = handlerInterceptors;
    }

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        if (handlerInterceptors == null || handlerInterceptors.isEmpty()) {
            return;
        }
        // 根据Order字段进行排序
        handlerInterceptors.sort(Comparator.comparingInt(this::getOrder));

        for (HandlerInterceptor handlerInterceptor : handlerInterceptors) {
            // 为添加了此注解的类自动配置
            Interceptor annotation = AnnotationUtils.findAnnotation(handlerInterceptor.getClass(), Interceptor.class);
            InterceptorRegistration registration = registry.addInterceptor(handlerInterceptor);
            if (annotation == null) {
                continue;
            }

            // 需要拦截的资源
            List<String> includeList = new ArrayList<>(Arrays.asList(annotation.include()));
            if (includeList.size() > 0) {
                registration.addPathPatterns(includeList);
            }

            // 需要排除的资源以及静态资源
            List<String> excludeList = new ArrayList<>(Arrays.asList(annotation.exclude()));
            excludeList.add("/docs/**");
            registration.excludePathPatterns(excludeList);
        }
    }

    /**
     * 对列表中的拦截根据order字段的值从小到大排序
     * 
     * @param o 目标对象
     * @return 序号
     */
    private int getOrder(Object o) {
        Interceptor annotation = AnnotationUtils.findAnnotation(o.getClass(), Interceptor.class);
        if (annotation == null) {
            return 0;
        }
        return annotation.order();
    }

}
