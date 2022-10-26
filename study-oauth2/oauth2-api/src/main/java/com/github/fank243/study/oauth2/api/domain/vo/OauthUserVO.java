package com.github.fank243.study.oauth2.api.domain.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class OauthUserVO implements Serializable {

    /** OpenID **/
    private String openId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickname;

}
