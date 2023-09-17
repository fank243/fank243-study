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
 * 用户授权令牌表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_oauth_access_token", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class OauthAccessTokenEntity extends BaseEntity implements Serializable {

    /** 令牌ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private String tokenId;

    /** 用户ID */
    private Long userId;

    /** OpenID */
    private String openId;

}
