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
public class SysUserLoginVO implements Serializable {

    /*** 用户ID */
    private String userId;

    /*** 用户名 */
    private String username;

    /*** 昵称 */
    private String nickname;

    /*** 最近登录时间 */
    private Date lastLoginTime;

    /*** 最近登录IP */
    private String lastLoginIp;

    /** 登陆令牌 */
    private String accessToken;

    /** token剩余有效期 (单位: 秒) */
    public long tokenTimeout;

    /** User-Session剩余有效时间 (单位: 秒) */
    public long sessionTimeout;

    /** Token-Session剩余有效时间 (单位: 秒) */
    public long tokenSessionTimeout;

    /** token剩余无操作有效时间 (单位: 秒) */
    public long tokenActivityTimeout;

}
