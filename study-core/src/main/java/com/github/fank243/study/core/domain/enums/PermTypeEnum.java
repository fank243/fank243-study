package com.github.fank243.study.core.domain.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * 权限类型
 * 
 * @author FanWeiJie
 * @since 2022-06-27 11:11:49
 */
@Getter
public enum PermTypeEnum {

    /** 目录 **/
    DIR(0, "目录"),

    MENU(1, "菜单"),

    BUTTON(2, "接口");

    /** 访问鉴权 **/
    public static final List<Integer> PERMS = Arrays.asList(MENU.code, BUTTON.code);
    private final int code;
    private final String desc;

    PermTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PermTypeEnum getEnum(String code) {
        PermTypeEnum[] values = PermTypeEnum.values();
        for (PermTypeEnum value : values) {
            if (code.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return null;
    }

    @SuppressWarnings("unused")
    public static boolean valid(String code) {
        return getEnum(code) != null;
    }
}
