package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 模板类别
 * 
 * @author FanWeiJie
 * @date 2019-11-02 15:06:36
 */
@Getter
public enum TemplateCode {
    /** 发送验证码 **/
    VER_CODE("发送验证码"),

    ;

    TemplateCode(String desc) {
        this.desc = desc;
    }

    private String desc;

    public static TemplateCode getEnum(String type) {
        TemplateCode[] values = TemplateCode.values();
        for (TemplateCode templateCode : values) {
            if (type.equalsIgnoreCase(templateCode.name())) {
                return templateCode;
            }
        }
        return null;
    }
}
