/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.system.convert;

import com.github.fank243.kong.system.domain.enums.PermTypeEnum;
import com.github.fank243.kong.system.domain.enums.StatusEnum;

import jakarta.persistence.AttributeConverter;

/**
 * 枚举转换类
 * 
 * @author FanWeiJie
 * @since 1.2.3
 */
public class EnumConvert {

    public static class PermTypeConverter implements AttributeConverter<PermTypeEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PermTypeEnum attribute) {
            if (attribute == null) {
                throw new RuntimeException("枚举类不能为NULL");
            }
            return attribute.getCode();
        }

        @Override
        public PermTypeEnum convertToEntityAttribute(Integer dbData) {
            if (dbData == null) {
                throw new RuntimeException("枚举值不能为NULL");
            }
            return PermTypeEnum.getEnum(dbData);
        }
    }

    public static class StatusConverter implements AttributeConverter<StatusEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(StatusEnum attribute) {
            if (attribute == null) {
                throw new RuntimeException("枚举类不能为NULL");
            }
            return attribute.getCode();
        }

        @Override
        public StatusEnum convertToEntityAttribute(Integer dbData) {
            if (dbData == null) {
                throw new RuntimeException("枚举值不能为NULL");
            }
            return StatusEnum.getEnum(dbData);
        }
    }
}
