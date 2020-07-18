package com.fank243.springboot.app.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 需要登录
 *
 * @author FanWeiJie
 * @date 2019-05-21 21:57:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Login {

}