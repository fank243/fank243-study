package com.fank243.study.core.validator.annotation;

import com.fank243.study.core.validator.validation.EnumConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举值校验
 * 
 * @author FanWeiJie
 * @since 2022-05-26 09:35:52
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumConstraintValidator.class)
public @interface ValidEnum {

    String message() default "枚举值验证失败";

    Class<? extends Enum<?>> enumClass();

    /** 验证方法 **/
    String methodName() default "valid";

    /** 是否允许为空 **/
    boolean empty() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
