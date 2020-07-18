package com.fank243.springboot.app.shiro;

import com.fank243.springboot.app.model.AppRequest;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录主体信息
 * 
 * @author FanWeiJie
 * @date 2020-03-31 21:22:26
 */
public class MyShiroToken extends UsernamePasswordToken {
    private AppRequest appRequest;

    public MyShiroToken(AppRequest appRequest) {
        this.appRequest = appRequest;
    }

    @Override
    public Object getPrincipal() {
        return appRequest;
    }

    @Override
    public Object getCredentials() {
        return appRequest.getSignature();
    }

}
