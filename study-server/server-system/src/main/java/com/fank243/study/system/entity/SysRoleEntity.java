package com.fank243.study.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fank243.study.ds.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_sys_role")
public class SysRoleEntity extends BaseEntity {

    /** 角色ID */
    @TableId
    private String roleId;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /** 状态(0：正常，1：禁用) **/
    private Integer status;

    /**
     * 是否已删除(0：未删除，1：已删除)
     */
    @TableLogic
    private Boolean isDeleted;

}
