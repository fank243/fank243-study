package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 日志类型
 * 
 * @author FanWeiJie
 * @date 2020-07-22 23:02:45
 */
@Getter
public enum AppLogType {

    /** 登录与注册 **/
    LOGIN("登录与注册"),

    /** 用户管理 **/
    USER("用户管理"),

    ;

    private final String desc;

    AppLogType(String desc) {
        this.desc = desc;
    }
}
