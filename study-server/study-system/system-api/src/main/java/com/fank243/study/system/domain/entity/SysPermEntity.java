package com.fank243.study.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.study.common.mybatis.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_sys_perm")
public class SysPermEntity extends BaseEntity {

    /** 权限ID */
    @TableId
    private String permId;

    private String pid;

    /** 权限代码 */
    private String permCode;

    /** 权限地址 */
    private String permUri;

    /** 状态(0：正常，1：禁用) **/
    private Integer status;

    /*** 权限名称 */
    private String permName;

    /** 权限描述 */
    private String permDesc;

}
