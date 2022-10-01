package com.fank243.study.system.domain.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysRoleVO implements Serializable {

    /*** 角色ID */
    private Long roleId;

    /*** 角色代码 */
    private String roleCode;

    /*** 角色名称 */
    private String roleName;

    /*** 角色描述 */
    private String roleDesc;

    /*** 状态(0：正常，1：禁用) */
    private Integer status;

}
