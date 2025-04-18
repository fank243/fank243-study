package com.github.fank243.kong.system.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
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
 * 系统管理员登录日志表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:09
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SysUserLoginLogEntity extends BaseDTO  {

    /** 主键ID */
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
