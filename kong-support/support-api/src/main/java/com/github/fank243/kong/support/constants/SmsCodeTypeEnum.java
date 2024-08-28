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

package com.github.fank243.kong.support.constants;

import lombok.Getter;

/**
 * 短信类型
 * 
 * @author FanWeiJie
 * @since 2022-10-05 18:29:54
 */
@Getter
public enum SmsCodeTypeEnum {

    /** 登录 **/
    LOGIN(0, "登录"),

    REGISTER(1, "注册"),

    RESET_PWD(2, "重置密码"),

    MODIFY_PWD(3, "修改密码"),

    MODIFY_MOBILE(4, "修改手机号码"),

    ;

    private final int code;

    private final String desc;

    SmsCodeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SmsCodeTypeEnum getEnum(Integer code) {
        SmsCodeTypeEnum[] values = SmsCodeTypeEnum.values();
        for (SmsCodeTypeEnum smsCodeTypeEnum : values) {
            if (smsCodeTypeEnum.getCode() == code) {
                return smsCodeTypeEnum;
            }
        }
        return null;
    }

    public static boolean valid(Integer code) {
        return getEnum(code) != null;
    }
}
