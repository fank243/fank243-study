package com.fank243.study.common.core.domain.validator.validation;

import com.fank243.study.common.core.domain.validator.annotation.ArrayNumber;

import cn.hutool.core.util.ArrayUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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
