package com.github.fank243.study.system.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统角色权限关联表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_sys_role_perm", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SysRolePermEntity extends BaseEntity implements Serializable {

    /** 主键ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long id;

    /** 角色ID */
    private Long roleId;

    /** 权限ID */
    private Long permId;

}
