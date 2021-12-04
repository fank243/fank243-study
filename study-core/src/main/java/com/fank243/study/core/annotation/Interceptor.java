package com.fank243.study.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义拦截器注解，免除手动配置
 * 
 * @author FanWeiJie
 * @since 2021-07-16 20:50:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Interceptor {
    /** 拦截器名称 **/
    String[] value() default {};

    /** 包含资源 **/
    String[] include() default {};

    /** 排除资源 **/
    String[] exclude() default {};

    /** 顺序，数值越小优先级越高 */
    int order() default Ordered.LOWEST_PRECEDENCE;
}