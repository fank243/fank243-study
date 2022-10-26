package com.github.fank243.study.oauth2.api.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 授权客户端表
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Data
@TableName("tb_oauth_access_token")
public class OauthAccessTokenEntity {

    /** tokenId */
    @TableId
    private String tokenId;

    /** userId */
    private String userId;

    /** openId */
    private String openId;

}
