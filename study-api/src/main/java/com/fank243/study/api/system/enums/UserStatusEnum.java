package com.fank243.study.api.system.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 * 
 * @author FanWeiJie
 * @since 2022-06-27 11:11:49
 */
@Getter
public enum UserStatusEnum {

    /** 正常 **/
    NORMAL(0, "正常"),

    DISABLED(1, "已禁用"),

    LOGIN_LOCK(2, "登录锁定");

    UserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final int code;

    private final String desc;
}
