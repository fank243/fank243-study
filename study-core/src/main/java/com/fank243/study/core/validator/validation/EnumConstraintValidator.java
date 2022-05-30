package com.fank243.study.core.validator.validation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fank243.study.core.validator.annotation.ValidEnum;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 校验枚举值是否合法，枚举类需提供静态方法.
 * 
 * <code>
 * public static boolean valid(String code){
 *     // 执行验证逻辑
 *     return Boolean.FALSE;
 * }
 * </code>
 * 
 * @author FanWeiJie
 * @since 2022-05-26 09:38:50
 */
public class EnumConstraintValidator implements ConstraintValidator<ValidEnum, String> {

    private boolean empty;
    private Class<? extends Enum<?>> enumClass;
    private String methodName;

    @Override
    public void initialize(ValidEnum validEnum) {
        empty = validEnum.empty();
        enumClass = validEnum.enumClass();
        methodName = validEnum.methodName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (empty && StrUtil.isBlank(value)) {
            return Boolean.TRUE;
        } else if (StrUtil.isBlank(value)) {
            return Boolean.FALSE;
        }

        if (!EnumUtil.isEnum(enumClass)) {
            throw new RuntimeException(enumClass.getName() + "不是枚举类");
        }

        Class<?> valueClass = value.getClass();
        try {
            Method method = enumClass.getMethod(methodName, valueClass);

            if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
                String errMsg =
                    String.format("%s method return is not boolean type in the %s class", methodName, enumClass);
                throw new RuntimeException(errMsg);
            }

            if (!Modifier.isStatic(method.getModifiers())) {
                String errMsg = String.format("%s method is not static method in the %s class", methodName, enumClass);
                throw new RuntimeException(errMsg);
            }

            // 验证结果
            Boolean valid = (Boolean)method.invoke(null, value);
            return Convert.toBool(valid, Boolean.FALSE);
        } catch (NoSuchMethodException | SecurityException e) {
            String errMsg =
                String.format("This %s(%s) method does not exist in the %s", methodName, valueClass, enumClass);
            throw new RuntimeException(errMsg, e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
