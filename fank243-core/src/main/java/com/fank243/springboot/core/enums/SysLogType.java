package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 日志类型
 * 
 * @author FanWeiJie
 * @date 2020-07-22 23:02:45
 */
@Getter
public enum SysLogType {

    /** 登录 **/
    LOGIN("登录"),

    /** 用户管理 **/
    USER("用户管理"),

    /** 管理员管理 **/
    SYS_USER("管理员管理"),

    /** 系统设置 **/
    SYS_SET("系统设置"),

    SYS_SET_ROLE("角色管理"),

    SYS_SET_SITE("站点配置"),

    ;

    private final String desc;

    SysLogType(String desc) {
        this.desc = desc;
    }
}
