package com.fank243.study.gateway.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@TableName("tb_sys_role")
public class SysRoleVO implements Serializable {

    /** 角色ID */
    private Long roleId;

    /** 角色代码 */
    private String roleCode;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 状态(0：正常，1：禁用) */
    private Integer status;

}
