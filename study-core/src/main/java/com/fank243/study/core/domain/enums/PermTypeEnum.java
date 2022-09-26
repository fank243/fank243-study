package com.fank243.study.core.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

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

    PermTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final int code;

    private final String desc;

    /** 访问鉴权 **/
    public static final List<Integer> PERMS = Arrays.asList(MENU.code, BUTTON.code);
}
