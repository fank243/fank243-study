package com.github.fank243.study.system.domain.entity;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table("tb_sys_role")
public class SysRoleEntity extends BaseEntity {

    /** 角色ID */
    @NotBlank(message = "角色ID必传", groups = {ValidatorGroup.Modify.class})
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String roleId;

    /*** 角色代码 */
    @Length(min = 2, max = 10, message = "角色代码长度在3-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "请填写角色代码", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String roleCode;

    /*** 角色名称 */
    @Length(min = 3, max = 20, message = "角色名称长度在3-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "请填写角色名称", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String roleName;

    /*** 角色描述 */
    private String roleDesc;

    /*** 状态(0：正常，1：禁用) */
    @NotNull(message = "请选择角色状态", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Integer status;

    /** 是否已删除(0：未删除，1：已删除) */
    @Column(isLogicDelete = true)
    private Boolean isDeleted;

}
