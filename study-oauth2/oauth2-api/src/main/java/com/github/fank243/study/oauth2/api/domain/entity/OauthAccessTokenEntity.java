package com.github.fank243.study.oauth2.api.domain.entity;

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
@Table("tb_oauth_access_token")
public class OauthAccessTokenEntity implements Serializable {

    /** tokenId */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String tokenId;

    /** userId */
    private String userId;

    /** openId */
    private String openId;

}
