package com.github.fank243.study.system.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.domain.enums.PermTypeEnum;
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
 * 系统权限表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_sys_perm", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SysPermEntity extends BaseEntity implements Serializable {

    /** 权限ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long permId;

    /** 权限父ID */
    private Long permPid;

    /** 权限代码 */
    private String permCode;

    /** 权限路径 */
    private String permUri;

    /** 权限名称 */
    private String permName;

    /** 权限描述 */
    private String permDesc;

    /** 权限类型(0：目录，1：菜单，2：接口) */
    private PermTypeEnum permType;

    /** 是否外部链接(0：否，1：是) */
    private Boolean isExternal;

    /** 权限状态(0：正常，1：禁用) */
    private Boolean status;

}
