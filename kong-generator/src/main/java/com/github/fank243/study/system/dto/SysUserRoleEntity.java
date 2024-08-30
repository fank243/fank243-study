package com.github.fank243.kong.system.dto;

import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统用户角色关联表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:10
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_sys_user_role", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SysUserRoleEntity extends BaseEntity implements Serializable {

    /** 主键ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

}
