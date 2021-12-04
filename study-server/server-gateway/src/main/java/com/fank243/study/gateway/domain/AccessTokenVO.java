package com.fank243.study.gateway.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录实体
 *
 * @author FanWeiJie
 * @since 2021-11-24 14:17:14
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccessTokenVO implements Serializable {

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
