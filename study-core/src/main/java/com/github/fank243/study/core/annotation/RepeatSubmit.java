package com.github.fank243.study.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 防重复提交注解
 * 
 * @author FanWeiJie
 * @since 2022-06-10 09:51:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RepeatSubmit {

    /** 间隔时间，单位：ms **/
    long interval() default 5000;

    /** 错误消息 **/
    String message() default "您的请求过于频繁，请稍后再试";
}