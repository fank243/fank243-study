package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 模板消息类型
 * 
 * @author FanWeiJie
 * @date 2019-11-29 14:44:46
 */
@Getter
public enum TemplateType {

    /** 站内信 **/
    MSG(0, "站内信"),

    SMS(1, "短信消息"),

    EMAIL(2, "电子邮件"),

    PUSH(3, "推送消息"),

    OTHER(99, "其他"),

    ;

    private int code;
    private String desc;

    TemplateType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TemplateType getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        TemplateType[] values = TemplateType.values();
        for (TemplateType noticeType : values) {
            if (noticeType.code == code) {
                return noticeType;
            }
        }
        return null;
    }
}
