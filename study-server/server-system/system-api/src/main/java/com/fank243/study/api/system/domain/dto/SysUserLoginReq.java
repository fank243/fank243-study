package com.fank243.study.api.system.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import com.fank243.study.common.domain.validator.annotation.ValidEnum;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.domain.enums.LoginType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理登录请求参数
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserLoginReq implements Serializable {

    /** 用户ID */
    @ValidEnum(enumClass = LoginType.class, message = "登录方式参数有误", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "登录方式参数必传")
    private String loginType;

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.LoginUsername.class})
    private String username;

    /** 密码 **/
    @NotBlank(message = "请填写密码", groups = {ValidatorGroup.LoginUsername.class})
    private String password;

    /** 用户名 */
    @NotBlank(message = "请填写手机号码", groups = {ValidatorGroup.LoginMobile.class})
    private String mobile;

    /** 短信验证码 **/
    @NotBlank(message = "请填写短信验证码", groups = {ValidatorGroup.LoginMobile.class})
    private String smsCode;

}
