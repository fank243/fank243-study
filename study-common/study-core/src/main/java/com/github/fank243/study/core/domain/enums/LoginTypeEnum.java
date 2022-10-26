package com.github.fank243.study.core.domain.enums;

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

    public static boolean valid(String loginType) {
        return getEnum(loginType) != null;
    }
}
