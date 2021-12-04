package com.fank243.study.api.oauth2.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 登录实体
 * 
 * @author FanWeiJie
 * @since 2021-11-24 14:17:14
 */
@Builder
@Data
public class AccessTokenVO implements Serializable {

    /** 用户名 **/
    private String username;

    /** OpenID **/
    private String openId;

    /** clientId **/
    private String clientId;

    private String accessToken;

    private String refreshToken;

    private String refreshExpiresIn;

    private String scope;

    private String expiresIn;

}
