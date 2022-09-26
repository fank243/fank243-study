package com.fank243.study.api.system.domain.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 管理登录响应参数
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysUserLoginResp implements Serializable {

    /*** 用户ID */
    private String userId;

    /*** 用户名 */
    private String username;

    /*** 昵称 */
    private String nickname;

    /*** 状态(0：正常，1：禁用，2：登录锁定) */
    private Integer status;

    /*** 登录累计错误次数 */
    private Integer loginErrCount;

    /*** 登录锁定时间 */
    private Date loginLockTime;

    /*** 最近登录时间 */
    private Date lastLoginTime;

    /*** 最近登录IP */
    private String lastLoginIp;

}
