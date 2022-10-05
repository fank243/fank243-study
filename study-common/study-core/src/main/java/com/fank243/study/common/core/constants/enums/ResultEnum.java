package com.fank243.study.common.core.constants.enums;

import lombok.Getter;

/**
 * 错误代码枚举
 * 
 * @author FanWeiJie
 * @since 2022-10-03 22:29:07
 */
@Getter
public enum ResultEnum {

    /** 请求成功 **/
    R0000("0000", "OK"),

    /** 1000号段，登录注册及跟用户相关 **/
    R1000("1000", "您的登录会话已失效，请重新登录"),

    R1001("1001", "账号或密码错误"),

    R1002("1002", "账号已被注册"),

    R1003("1003", "用户名不存在"),

    /** 5000号段，框架相关 **/
    R5000("5000", "系统繁忙，请稍后重试"),

    ;

    ResultEnum(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    /** 消息代码 **/
    private final String code;

    /** 错误消息 **/
    private final String errMsg;
}
