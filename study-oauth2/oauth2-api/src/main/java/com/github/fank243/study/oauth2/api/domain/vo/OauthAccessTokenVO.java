package com.github.fank243.study.oauth2.api.domain.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OauthAccessTokenVO implements Serializable {

    /** OpenID **/
    private String openId;

    /** 客户端ID **/
    private String clientId;

    /** 授权作用域 **/
    private String scope;

    /** 令牌 */
    private String accessToken;

    /** 刷新令牌 */
    private String refreshToken;

    /** 令牌有效时长(秒) */
    private String expiresIn;

    /** 刷新令牌有效时长(秒) */
    private String refreshExpiresIn;

}
