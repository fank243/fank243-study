package com.fank243.study.oauth2.domain;

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
@TableName("tb_oauth_client")
public class OauthClientEntity {

    /** AppID */
    @TableId
    private String clientId;

    /** AppSecret */
    private String clientSecret;

    /** 资源ID集合(英文逗号分割) */
    private String resourceIds;

    /** 授权范围 */
    private String scope;

    /** 授权类型 */
    private String grantTypes;

    /** 重定向URL */
    private String redirectUrl;

    /** 其他信息 */
    private String additionalInformation;

}
