package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 设备类型
 * 
 * @author FanWeiJie
 * @date 2019-10-29 10:44:20
 */
@Getter
public enum DeviceType {

    /** 未知 **/
    UNKNOWN("未知"),

    /** 安卓 **/
    ANDROID("安卓"),

    IOS("苹果"),

    WX("微信"),

    OTHER("其他"),

    ;

    DeviceType(String desc) {
        this.desc = desc;
    }

    private final String desc;

}
