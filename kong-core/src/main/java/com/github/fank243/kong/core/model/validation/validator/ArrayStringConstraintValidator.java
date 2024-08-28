/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.core.model.validation.validator;

import com.github.fank243.kong.core.model.validation.annotation.ArrayString;

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
