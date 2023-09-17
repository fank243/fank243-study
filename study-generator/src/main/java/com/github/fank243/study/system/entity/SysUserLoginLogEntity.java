package com.github.fank243.study.system.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员登录日志表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_sys_user_login_log", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SysUserLoginLogEntity extends BaseEntity implements Serializable {

    /** 主键ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 登录时间 */
    private LocalDateTime loginTime;

    /** 登录IP */
    private String loginIp;

    /** 登录设备 */
    private String loginDevice;

}
