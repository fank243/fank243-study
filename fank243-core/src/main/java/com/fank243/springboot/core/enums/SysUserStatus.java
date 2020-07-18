package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 管理员状态
 * 
 * @author FanWeiJie
 * @date 2020-03-08 13:14:03
 */
@Getter
public enum SysUserStatus {

    /** 正常 **/
    ENABLE(0, "正常"),

    DISABLE(1, "禁用"),

    LOCK(2, "登录锁定"),

    ;

    SysUserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public static SysUserStatus getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        SysUserStatus[] values = SysUserStatus.values();
        for (SysUserStatus sysUserStatus : values) {
            if (code == sysUserStatus.code) {
                return sysUserStatus;
            }
        }
        return null;
    }
}
