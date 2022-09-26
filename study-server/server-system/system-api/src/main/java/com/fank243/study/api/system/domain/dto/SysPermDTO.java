package com.fank243.study.api.system.domain.dto;

import com.fank243.study.common.domain.base.BaseDTO;
import com.fank243.study.core.constants.ValidatorGroup;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermDTO extends BaseDTO {

    /*** 权限ID */
    @NotNull(message = "权限ID必传", groups = {ValidatorGroup.Modify.class})
    private Long permId;

    /** 父ID */
    private Long pid;
    /*** 权限代码 */
    @NotEmpty(message = "权限代码不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String permCode;

    /*** 权限地址 */
    private String permUri;

    /*** 权限名称 */
    @NotEmpty(message = "权限名称不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String permName;

    /*** 权限描述 */
    private String permDesc;

    /** 权限类型(0：目录，1：菜单，2：接口) */
    @NotEmpty(message = "权限类型不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Integer permType;

    /** 是否外部链接(0：否，1：是) */
    @NotEmpty(message = "是否外部链接不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Boolean isExternal;
}
