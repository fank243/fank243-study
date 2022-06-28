package com.fank243.study.gateway.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysPermVO implements Serializable {

    /** 权限ID */
    private Long permId;

    /** 父ID */
    private Long pid;

    /** 权限代码 */
    private String permCode;

    /** 权限地址 */
    private String permUri;

    /** 权限名称 */
    private String permName;

    /** 权限描述 */
    private String permDesc;

    /** 权限类型(0：目录，1：菜单，2：接口) */
    private Integer permType;

    /** 是否外部链接(0：否，1：是) */
    private Boolean isExternal;

    /** 状态(0：正常，1：禁用) */
    private Integer status;

}
