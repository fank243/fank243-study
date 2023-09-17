package com.github.fank243.study.system.domain;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.domain.enums.UserStatusEnum;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 16:25:51
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_sys_user", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SysUserEntity extends BaseEntity {

    /** 用户ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
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
    @Column(isLogicDelete = true)
    private Boolean isDeleted;

}
