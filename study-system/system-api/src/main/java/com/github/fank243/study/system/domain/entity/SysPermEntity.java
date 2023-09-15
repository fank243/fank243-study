package com.github.fank243.study.system.domain.entity;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table("tb_sys_perm")
public class SysPermEntity extends BaseEntity {

    /** 权限ID */
    @NotNull(message = "菜单ID必传", groups = {ValidatorGroup.Modify.class})
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
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

}
