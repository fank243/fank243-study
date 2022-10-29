package com.github.fank243.study.core.domain.validator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.fank243.study.core.domain.validator.annotation.ArrayNumber;

import cn.hutool.core.util.ArrayUtil;

/**
 * 校验数组值是否合法，仅支持多选一
 *
 * @author FanWeiJie
 * @since 2022-05-26 09:38:50
 */
public class ArrayNumberConstraintValidator implements ConstraintValidator<ArrayNumber, Integer> {

    private boolean nullable;
    private int[] array;

    @Override
    public void initialize(ArrayNumber validArray) {
        nullable = validArray.nullable();
        array = validArray.array();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (nullable && value == null) {
            return Boolean.TRUE;
        } else if (value == null) {
            return Boolean.FALSE;
        }

        if (ArrayUtil.isEmpty(array)) {
            return Boolean.TRUE;
        }
        for (int number : array) {
            if (number == value) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
