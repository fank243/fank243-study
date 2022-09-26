package com.fank243.study.oauth2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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

    /** 主键ID */
    @TableId
    private String id;

    /** 用户ID */
    private String userId;

    /** OpenID */
    private String openId;

}
