package com.github.fank243.study.system.domain.entity;

import java.util.Date;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统管理员登录日志表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Getter
@Setter
@Table("tb_sys_user_login_log")
public class SysUserLoginLogEntity {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String id;

    /** 用户ID */
    private String userId;

    /** 登录时间 */
    private Date loginTime;

    /** 登录IP */
    private String loginIp;

    /** 登录设备信息 */
    private String loginDevice;

}
