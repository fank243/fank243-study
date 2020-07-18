package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 角色代码
 * 
 * @author FanWeiJie
 * @date 2020-03-08 15:30:01
 */
@Getter
public enum RoleType {

    /** 系统管理员 **/
    ROOT(0, "role:root", "系统管理员"),

    ADMIN(1, "role:admin", "普通管理员");

    RoleType(int level, String name, String desc) {
        this.level = level;
        this.name = name;
        this.desc = desc;
    }

    private int level;
    private String name;
    private String desc;

    public static RoleType getEnum(String name) {
        RoleType[] values = RoleType.values();
        for (RoleType roleType : values) {
            if (name.equalsIgnoreCase(roleType.name())) {
                return roleType;
            }
        }
        return null;
    }
}
