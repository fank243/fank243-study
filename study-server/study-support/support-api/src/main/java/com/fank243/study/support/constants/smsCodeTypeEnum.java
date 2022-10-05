package com.fank243.study.support.constants;

import lombok.Getter;

/**
 * 短信类型
 * 
 * @author FanWeiJie
 * @since 2022-10-05 18:29:54
 */
@Getter
public enum smsCodeTypeEnum {

    /** 登录 **/
    LOGIN(0, "登录"),

    REGISTER(1, "注册"),

    RESET_PWD(2, "重置密码"),

    MODIFY_PWD(3, "修改密码"),

    MODIFY_MOBILE(4, "修改手机号码"),

    ;

    private final int code;

    private final String desc;

    smsCodeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static smsCodeTypeEnum getEnum(Integer code) {
        smsCodeTypeEnum[] values = smsCodeTypeEnum.values();
        for (smsCodeTypeEnum smsCodeTypeEnum : values) {
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
