package com.github.fank243.study.system.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.study.core.base.BaseDTO;
import com.github.fank243.study.core.constants.ValidatorGroup;
import com.github.fank243.study.core.domain.enums.PermTypeEnum;
import com.github.fank243.study.core.domain.validator.annotation.Enum;
import com.mzt.logapi.starter.annotation.DiffLogAllFields;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统菜单表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@DiffLogAllFields
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPermDTO extends BaseDTO {

    /*** 菜单ID */
    @NotNull(message = "菜单ID必传", groups = {ValidatorGroup.Modify.class})
    private String permId;

    /** 父ID */
    @NotNull(message = "请选择父菜单", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String pid;

    /*** 菜单代码 */
    @Length(min = 2, max = 20, message = "菜单代码长度在2-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "菜单代码不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String permCode;

    /*** 菜单地址 */
    private String permUri;

    /*** 菜单名称 */
    @Length(min = 2, max = 20, message = "菜单名称长度在2-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "菜单名称不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String permName;

    /*** 菜单描述 */
    private String permDesc;

    /** 菜单类型(0：目录，1：菜单，2：接口) */
    @Enum(clazz = PermTypeEnum.class, message = "菜单类型不存在",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "菜单类型不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Integer permType;

    /** 是否外部链接(0：否，1：是) */
    @NotNull(message = "是否外部链接不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Boolean isExternal;
}
