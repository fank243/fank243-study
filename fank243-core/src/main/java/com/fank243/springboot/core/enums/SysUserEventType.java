package com.fank243.springboot.core.enums;

import lombok.Getter;

/**
 * 管理员操作事件类型
 * 
 * @author FanWeiJie
 * @date 2019-11-05 18:02:07
 */
@Getter
public enum SysUserEventType {

    /** 登陆事件 **/
    LOGIN("登录"),

    /** 角色管理 **/
    ROLE_MNG("角色管理"),

    /** 角色管理 **/
    PERM_MNG("角色管理"),

    /** 菜单管理 **/
    MENU_MNG("菜单管理"),

    /** 管理员 **/
    ADMIN_MNG("管理员"),

    USER_MNG("用户管理"),

    MODIFY_PWD("修改登录密码"),

    VIDEO_MNG("视频管理"),

    VIDEO_LESSON("视频课节"),

    CAROUSEL_MNG("视频轮播图"),

    CLASS_MNG("班级管理"),

    SCHEDULE_MNG("课表管理"),

    SITE_MNG("站点配置"),

    TEMPLATE_MNG("模板管理"),

    ;

    SysUserEventType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public static SysUserEventType getEnum(String type) {
        SysUserEventType[] values = SysUserEventType.values();
        for (SysUserEventType eventType : values) {
            if (eventType.name().equalsIgnoreCase(type)) {
                return eventType;
            }
        }
        return null;
    }
}
