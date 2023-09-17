package com.github.fank243.study.system.domain;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统角色表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 16:26:06
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_sys_role", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SysRoleEntity extends BaseEntity {

    /** 角色ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long roleId;

    /** 角色代码 */
    private String roleCode;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 角色状态(0：正常，1：禁用) */
    private Boolean status;

    /** 是否已删除(0：否，1：是) */
    @Column(isLogicDelete = true)
    private Integer isDeleted;

}
