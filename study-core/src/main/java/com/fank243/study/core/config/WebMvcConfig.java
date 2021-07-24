package com.fank243.study.core.config;

import com.fank243.study.core.annotation.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Mvc Configuration
 * 
 * @author FanWeiJie
 * @since 2021-07-16 20:44:11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** 可选注入，防止子项目启动失败 **/
    private final List<HandlerInterceptor> handlerInterceptors;

    public WebMvcConfig(List<HandlerInterceptor> handlerInterceptors) {
        this.handlerInterceptors = handlerInterceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
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
            List<String> excludeList = new ArrayList<>();
            excludeList.addAll(Arrays.asList(annotation.exclude()));
            excludeList
                .addAll(Arrays.asList("/style/**", "/js/**", "/layui/**", "*.js", "*.css", "*.icon", "*.png", "*.jpg"));
            if (excludeList.size() > 0) {
                registration.excludePathPatterns(excludeList);
            }
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
