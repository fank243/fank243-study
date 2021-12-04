package com.fank243.study.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.study.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_sys_permission")
public class SysPermissionEntity extends BaseEntity {

    /**
     * 权限代码
     */
    private String permCode;

    /**
     * 权限地址
     */
    private String permUri;

    /**
     * 权限描述
     */
    private String permDesc;

}
