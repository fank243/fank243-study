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

package com.github.fank243.kong.oauth2.api.convert;

import com.github.fank243.kong.oauth2.api.domain.enums.Oauth2UserStatusEnum;

import jakarta.persistence.AttributeConverter;

/**
 * 枚举转换类
 * 
 * @author FanWeiJie
 * @since 1.2.3
 */
public class EnumConvert {

    public static class Oauth2UserStatusConverter implements AttributeConverter<Oauth2UserStatusEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Oauth2UserStatusEnum attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.getCode();
        }

        @Override
        public Oauth2UserStatusEnum convertToEntityAttribute(Integer dbData) {
            if (dbData == null) {
                throw new RuntimeException("枚举值不能为NULL");
            }
            return Oauth2UserStatusEnum.getEnum(dbData);
        }
    }
}
