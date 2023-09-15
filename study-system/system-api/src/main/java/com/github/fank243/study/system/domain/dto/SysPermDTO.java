package com.github.fank243.study.system.domain.dto;

import com.github.fank243.study.core.domain.enums.PermTypeEnum;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.core.model.validation.annotation.Enum;
import com.github.fank243.study.system.domain.entity.SysPermEntity;
import com.mzt.logapi.starter.annotation.DiffLogAllFields;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统菜单表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Getter
@Setter
@SuperBuilder
@DiffLogAllFields
@NoArgsConstructor
public class SysPermDTO extends SysPermEntity {

    /** 菜单类型(0：目录，1：菜单，2：接口) */
    @Enum(clazz = PermTypeEnum.class, message = "菜单类型不存在",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "菜单类型不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Integer permType;

    /** 是否外部链接(0：否，1：是) */
    @NotNull(message = "是否外部链接不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Boolean isExternal;
}
