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

package com.github.fank243.kong.core.model.validation;

import java.util.List;

import com.github.fank243.kong.tool.result.ResultInfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.validation.BeanValidationResult;
import cn.hutool.extra.validation.ValidationUtil;

/**
 * Spring Validation 验证器工具类
 * 
 * @author FanWeiJie
 * @since 2022-09-23 09:35:17
 */
public class ValidationUtils {

    /**
     * Hibernate Validate 实体Bean验证器
     *
     * @param bean 待验证的JavaBean
     * @param groups 分组，可选参数
     * @param <T> 待验证的JavaBean
     * @return 返回ResponseContent对象
     */
    public static <T> ResultInfo<?> validate(T bean, Class<?>... groups) {
        BeanValidationResult result = ValidationUtil.warpValidate(bean, groups);
        if (result.isSuccess()) {
            return ResultInfo.ok();
        }

        List<BeanValidationResult.ErrorMessage> errorMessages = result.getErrorMessages();
        if (CollUtil.isEmpty(errorMessages)) {
            return ResultInfo.ok();
        }

        return ResultInfo.err400(errorMessages.get(0).getMessage());
    }

    /**
     * Hibernate Validate 实体Bean验证器
     *
     * @param list 待验证的JavaBean
     * @param groups 分组，可选参数
     * @param <T> 待验证的JavaBean
     * @return 返回ResponseContent对象
     */
    public static <T> ResultInfo<?> validate(List<T> list, Class<?>... groups) {
        if (CollUtil.isEmpty(list)) {
            return ResultInfo.ok();
        }
        for (int i = 0; i < list.size(); i++) {
            T bean = list.get(i);
            ResultInfo<?> resultInfo = validate(bean, groups);
            if (!resultInfo.isSuccess()) {
                resultInfo.setMessage("第" + (i + 1) + "条记录校验失败[" + resultInfo.getMessage() + "]");
                return resultInfo;
            }
        }
        return ResultInfo.ok();
    }
}
