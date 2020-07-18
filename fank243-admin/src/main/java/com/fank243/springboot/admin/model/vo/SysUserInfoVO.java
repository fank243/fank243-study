package com.fank243.springboot.admin.model.vo;

import lombok.Data;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @date 2020-03-26 20:38:48
 */
@Data
public class SysUserInfoVO {

    private Long id;

    private String username;

    private String realname;

    private String mobile;

    private Boolean isVerifyMobile;

    private String smsCode;

    private String email;

    private Boolean isVerifyEmail;

    private String emailCode;

    private String wxNumber;
}
