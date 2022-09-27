package com.fank243.study.api.system.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fank243.study.common.domain.base.BaseDTO;
import com.fank243.study.core.constants.ValidatorGroup;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends BaseDTO {

    /*** 角色ID */
    @NotBlank(message = "角色ID必传", groups = {ValidatorGroup.Modify.class})
    private Long roleId;

    /*** 角色代码 */
    @NotBlank(message = "请填写角色代码", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @Length(min = 2, max = 10, message = "角色代码长度在3-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String roleCode;

    /*** 角色名称 */
    @NotBlank(message = "请填写角色名称", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @Length(min = 3, max = 20, message = "角色名称长度在3-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String roleName;

    /*** 角色描述 */
    private String roleDesc;

    /*** 状态(0：正常，1：禁用) */
    @NotNull(message = "请选择角色状态", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Integer status;

}
