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
    UNKNOWN(0, "未知"),

    /** 安卓 **/
    ANDROID(1, "安卓"),

    IOS(2, "苹果"),

    WX(3, "微信"),

    OTHER(99, "其他"),

    ;

    DeviceType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public static DeviceType getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        DeviceType[] values = DeviceType.values();
        for (DeviceType deviceType : values) {
            if (code == deviceType.getCode()) {
                return deviceType;
            }
        }
        return null;
    }
}
