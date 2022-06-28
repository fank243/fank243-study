package com.fank243.study.api.system.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统管理员登录日志表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysUserLoginLogVO implements Serializable {

    /*** 用户ID */
    private String userId;

    /*** 登录时间 */
    private Date loginTime;

    /*** 登录IP */
    private String loginIp;

    /*** 登录设备信息 */
    private String loginDevice;

}
