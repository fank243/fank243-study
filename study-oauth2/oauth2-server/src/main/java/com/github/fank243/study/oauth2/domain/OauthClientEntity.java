package com.github.fank243.study.oauth2.domain;

import java.io.Serializable;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 授权客户端表
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Getter
@Setter
@Builder
@Table("tb_oauth_client")
public class OauthClientEntity implements Serializable {

    /** AppID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
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
