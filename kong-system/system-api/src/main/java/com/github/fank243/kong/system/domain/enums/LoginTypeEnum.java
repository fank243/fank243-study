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

package com.github.fank243.kong.system.domain.enums;

import lombok.Getter;

/**
 * 登录方式
 * 
 * @author FanWeiJie
 * @since 2022-09-23 09:22:22
 */
@Getter
public enum LoginTypeEnum {
    /** 手机号码 **/
    MOBILE,

    /** 用户名 **/
    USERNAME;

    public static LoginTypeEnum getEnum(String loginType) {
        LoginTypeEnum[] values = LoginTypeEnum.values();
        for (LoginTypeEnum value : values) {
            if (loginType.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return null;
    }

    @SuppressWarnings("unused")
    public static boolean valid(String loginType) {
        return getEnum(loginType) != null;
    }
}
