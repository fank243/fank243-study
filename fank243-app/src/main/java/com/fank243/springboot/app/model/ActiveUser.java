package com.fank243.springboot.app.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 当前登录用户信息
 * 
 * @author FanWeiJie
 * @date 2020-03-29 21:32:26
 */
@Data
public class ActiveUser implements Serializable {

    public ActiveUser(Long userId) {
        this.userId = userId;
    }

    private Long userId;
}
