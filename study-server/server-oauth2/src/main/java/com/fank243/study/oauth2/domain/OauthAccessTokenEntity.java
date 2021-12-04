package com.fank243.study.oauth2.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 系统管理员角色关联表
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Data
@TableName("tb_oauth_access_token")
public class OauthAccessTokenEntity {

    /** 用户ID */
    private String userId;

    /** OpenID */
    private String openId;

}
