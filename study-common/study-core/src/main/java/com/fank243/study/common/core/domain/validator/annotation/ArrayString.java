package com.fank243.study.common.core.domain.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fank243.study.common.core.domain.validator.validation.ArrayStringConstraintValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 数组值校验
 * 
 * @author FanWeiJie
 * @since 2022-05-26 09:35:52
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ArrayStringConstraintValidator.class)
public @interface ArrayString {

    String message() default "数据验证失败";

    /** 允许的值 **/
    String[] array();

    /** 是否允许为空 **/
    boolean empty() default false;

    /** 是否忽略大小写 **/
    boolean ignoreCase() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
