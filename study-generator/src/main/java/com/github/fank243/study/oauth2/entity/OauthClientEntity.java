package com.github.fank243.study.oauth2.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Oauth2认证客户端表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_oauth_client", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class OauthClientEntity extends BaseEntity implements Serializable {

    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private String clientId;

    /** 客户端密钥 */
    private String clientSecret;

    /** 资源ID集合(英文逗号分割) */
    private String resourceIds;

    /** 授权范围(英文逗号分割) */
    private String scope;

    /** 授权类型(英文逗号分割) */
    private String grantTypes;

    /** 重定向地址 */
    private String redirectUrl;

    /** 额外信息 */
    private String additionalInformation;

}
