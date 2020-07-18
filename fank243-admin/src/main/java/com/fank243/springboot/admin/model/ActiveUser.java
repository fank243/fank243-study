package com.fank243.springboot.admin.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 当前登录用户
 *
 * @author FanWeiJie
 * @date 2020-03-22 01:29:39
 */
@ToString
@Data
public class ActiveUser implements Serializable {

    public ActiveUser() {}

    public ActiveUser(Long userId, String username, String realname) {
        this.userId = userId;
        this.username = username;
        this.realname = realname;
    }

    private Long userId;
    private String username;
    private String realname;

    /** 菜单列表 */
    private List<Menu> menus;

}