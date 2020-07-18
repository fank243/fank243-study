package com.fank243.springboot.admin.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义登录认证器
 * 
 * @author FanWeiJie
 * @date 2020-03-22 16:14:13
 */
public class MyShiroToken extends UsernamePasswordToken {

    private ShiroToken shiroToken;

    public MyShiroToken() {}

    public MyShiroToken(ShiroToken shiroToken) {
        this.shiroToken = shiroToken;
    }

    @Override
    public Object getPrincipal() {
        return shiroToken;
    }

    @Override
    public Object getCredentials() {
        return shiroToken.getPassword();
    }

    @Override
    public boolean isRememberMe() {
        return shiroToken.getRememberMe();
    }
}
