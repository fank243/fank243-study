package com.fank243.study.common.core.domain.validator.validation;

import com.fank243.study.common.core.domain.validator.annotation.ArrayString;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 校验数组值是否合法，仅支持多选一
 * 
 * @author FanWeiJie
 * @since 2022-05-26 09:38:50
 */
public class ArrayStringConstraintValidator implements ConstraintValidator<ArrayString, String> {

    private boolean empty;
    private boolean ignoreCase;
    private String[] array;

    @Override
    public void initialize(ArrayString validArray) {
        empty = validArray.empty();
        ignoreCase = validArray.ignoreCase();
        array = validArray.array();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (empty && StrUtil.isBlank(value)) {
            return Boolean.TRUE;
        } else if (StrUtil.isBlank(value)) {
            return Boolean.FALSE;
        }

        if (ArrayUtil.isEmpty(array)) {
            return Boolean.TRUE;
        }

        return ignoreCase ? ArrayUtil.containsIgnoreCase(array, value) : ArrayUtil.contains(array, value);
    }
}
