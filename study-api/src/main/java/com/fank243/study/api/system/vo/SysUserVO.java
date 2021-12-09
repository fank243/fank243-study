package com.fank243.study.api.system.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
public class SysUserVO implements Serializable {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

    /** 登录密码 */
    private String password;

}
