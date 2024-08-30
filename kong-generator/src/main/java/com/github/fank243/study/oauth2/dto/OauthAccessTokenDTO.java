package com.github.fank243.kong.oauth2.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 用户授权令牌表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:10
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OauthAccessTokenEntity extends BaseDTO  {

    /** 令牌ID */
    private String tokenId;

    /** 用户ID */
    private Long userId;

    /** OpenID */
    private String openId;

}
