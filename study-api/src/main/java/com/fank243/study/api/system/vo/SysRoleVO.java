package com.fank243.study.api.system.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Data
public class SysRoleVO implements Serializable {

    /** 角色ID **/
    private String roleId;

    /** 角色代码 */
    private String roleCode;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

}
