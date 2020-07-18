package com.fank243.springboot.admin.shiro;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员登录参数实体
 * 
 * @author FanWeiJie
 * @date 2019-11-23 11:00:20
 */
@Getter
@Setter
public class ShiroToken {
    private String username;
    private String password;
    private String imgCode;
    private Boolean rememberMe = Boolean.FALSE;
}
