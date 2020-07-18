package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 用户状态
 * 
 * @author FanWeiJie
 * @date 2020-03-24 17:28:39
 */
@Getter
public enum UserStatus {

    /** 正常 **/
    ENABLE(0, "正常"),

    DISABLE(1, "禁用"),

    LOCK(2, "登录锁定");

    UserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public static UserStatus getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        UserStatus[] values = UserStatus.values();
        for (UserStatus status : values) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}
