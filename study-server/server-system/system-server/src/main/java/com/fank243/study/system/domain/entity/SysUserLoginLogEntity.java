package com.fank243.study.system.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fank243.study.ds.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 系统管理员登录日志表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@Builder
@TableName("tb_sys_user_login_log")
public class SysUserLoginLogEntity {

    @TableId
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
