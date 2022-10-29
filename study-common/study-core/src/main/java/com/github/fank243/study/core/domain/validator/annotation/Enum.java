package com.github.fank243.study.core.domain.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.fank243.study.core.domain.validator.validation.EnumConstraintValidator;

/**
 * 枚举值校验
 * 
 * @author FanWeiJie
 * @since 2022-05-26 09:35:52
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumConstraintValidator.class)
public @interface Enum {

    String message() default "枚举值验证失败";

    Class<? extends java.lang.Enum<?>> clazz();

    /** 验证方法 **/
    String methodName() default "valid";

    /** 是否允许为空 **/
    boolean empty() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
