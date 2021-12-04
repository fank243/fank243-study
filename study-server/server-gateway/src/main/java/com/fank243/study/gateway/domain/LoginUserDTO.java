package com.fank243.study.gateway.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录实体
 *
 * @author FanWeiJie
 * @since 2021-11-24 14:17:14
 */
@Data
public class LoginUserDTO implements Serializable {

    /** 用户名 **/
    private String username;

    /** 密码 **/
    private String password;

    /** 记住我 **/
    private Boolean isRememberMe;

    /** clientId **/
    private String clientId;

    /** clientSecret **/
    private String clientSecret;

    /** refreshToken **/
    private String refreshToken;

}
