package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 用户注册来源
 * 
 * @author FanWeiJie
 * @date 2019-10-29 10:44:20
 */
@Getter
public enum UserSource {

    /** 未知 **/
    UNKNOWN(0, "未知"),

    ANDROID(1, "安卓"),

    IOS(2, "苹果"),

    WX(3, "微信"),

    OTHER(99, "其他"),

    ;

    UserSource(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public static UserSource getEnum(int code) {
        UserSource[] values = UserSource.values();
        for (UserSource deviceType : values) {
            if (code == deviceType.getCode()) {
                return deviceType;
            }
        }
        return null;
    }
}
