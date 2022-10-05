package com.fank243.study.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 系统日志记录
 *
 * @author FanWeiJie
 * @since 2021-07-16 20:50:07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ApiLog {

    /** 日志描述 **/
    String value() default "";

    /** spel 表达式 **/
    String expression() default "";
}
