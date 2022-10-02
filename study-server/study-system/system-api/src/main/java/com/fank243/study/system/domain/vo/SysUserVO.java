package com.fank243.study.system.domain.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysUserVO implements Serializable {

    /*** 用户ID */
    private String userId;

    /*** 用户名 */
    private String username;

    /*** 昵称 */
    private String nickname;

    /*** 状态(0：正常，1：禁用，2：登录锁定) */
    private Integer status;

    /*** 最近登录时间 */
    private Date lastLoginTime;

    /*** 最近登录IP */
    private String lastLoginIp;

}
