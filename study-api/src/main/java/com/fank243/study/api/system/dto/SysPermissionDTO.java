package com.fank243.study.api.system.dto;

import com.fank243.study.core.base.BaseDTO;
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
public class SysPermissionDTO extends BaseDTO {


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
