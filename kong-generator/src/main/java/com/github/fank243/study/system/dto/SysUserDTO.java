package com.github.fank243.kong.system.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.system.domain.enums.UserStatusEnum;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:09
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SysUserEntity extends BaseDTO  {

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 用户状态(0：正常，1：已禁用) */
    private UserStatusEnum status;

    /** OpenID */
    private String openId;

    /** 是否已删除(0：否，1：是) */
    private Boolean isDeleted;

}