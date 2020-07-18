package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 开放平台类别
 * 
 * @author FanWeiJie
 * @date 2020-03-24 17:31:14
 */
@Getter
public enum UserBindType {

    /** 微信登录 **/
    WECHAT(0, "微信登录"),

    QQ(1, "QQ登录"),

    WEIBO(2, "微博登录"),

    ALIPAY(3, "支付宝登录");

    UserBindType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

}
