package com.github.fank243.study.system.domain.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.domain.enums.UserStatusEnum;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table("tb_sys_user")
public class SysUserEntity extends BaseEntity {

    /** 用户ID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @NotBlank(message = "用户ID必传", groups = {ValidatorGroup.Modify.class})
    private String userId;

    /** 用户名 */
    @Length(min = 3, max = 20, message = "用户名的长度在3-20位之间", groups = {ValidatorGroup.LoginUsername.class})
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.LoginUsername.class})
    private String username;

    /** 昵称 */
    private String nickname;

    /** 状态(0：正常，1：禁用，2：登录锁定) */
    private UserStatusEnum status;

    /** 最近登录时间 */
    private Date lastLoginTime;

    /** 最近登录IP */
    private String lastLoginIp;

    /** 是否已删除(0：未删除，1：已删除) */
    @Column(isLogicDelete = true)
    private Integer isDeleted;

    /** OpenID **/
    private String openId;
}
