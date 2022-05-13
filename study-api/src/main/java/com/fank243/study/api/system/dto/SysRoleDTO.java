package com.fank243.study.api.system.dto;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.core.base.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends BaseDTO {

    /** 角色ID */
    @NotBlank(message = "角色ID必传", groups = {ValidatorGroup.Modify.class})
    private String roleId;

    /**
     * 角色代码
     */
    @NotBlank(message = "请填写角色代码")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(message = "请填写角色名称")
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

}
