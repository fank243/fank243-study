package com.github.fank243.study.system.domain.dto;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.system.domain.entity.SysRoleEntity;
import com.mzt.logapi.starter.annotation.DiffLogAllFields;

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
 * @since 2022-06-27
 */
@Getter
@Setter
@SuperBuilder
@DiffLogAllFields
@NoArgsConstructor
public class SysRoleDTO extends SysRoleEntity {

    /*** 角色ID */
    @NotBlank(message = "角色ID必传", groups = {ValidatorGroup.Modify.class})
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

}
