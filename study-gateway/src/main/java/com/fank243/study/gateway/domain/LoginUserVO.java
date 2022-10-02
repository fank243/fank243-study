package com.fank243.study.gateway.domain;

import java.io.Serializable;

import cn.dev33.satoken.stp.SaTokenInfo;
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
public class LoginUserVO implements Serializable {

    /** 用户名 **/
    private String username;

    /** Token 信息 **/
    private SaTokenInfo tokenInfo;

    /** Access Token 信息 **/
    private AccessTokenVO accessTokenInfo;
}
