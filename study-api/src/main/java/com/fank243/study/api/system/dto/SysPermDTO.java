package com.fank243.study.api.system.dto;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
* 系统权限表
*
* @author FanWeiJie
* @since 2022-05-13
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermDTO extends BaseDTO {


    /** 权限ID */
    @NotBlank(message = "权限ID必传", groups = {ValidatorGroup.Modify.class})
    private String permId;

    /*** 权限代码 */
    @NotBlank(message = "请填写权限代码")
    private String permCode;

    /*** 权限地址 */
    @NotBlank(message = "请填写菜单地址")
    private String permUri;

    /*** 权限描述 */
    private String permDesc;


}
