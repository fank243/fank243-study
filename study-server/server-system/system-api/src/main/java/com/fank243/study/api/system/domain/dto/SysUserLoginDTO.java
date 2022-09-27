package com.fank243.study.api.system.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fank243.study.common.constants.RegexConstants;
import com.fank243.study.common.domain.validator.annotation.ValidEnum;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.domain.enums.LoginType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 管理登录请求参数
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserLoginDTO implements Serializable {

    /** 用户ID */
    @ValidEnum(enumClass = LoginType.class, message = "登录方式参数有误", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "登录方式参数必传", groups = {ValidatorGroup.Login.class})
    private String loginType;

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.LoginUsername.class})
    @Length(min = 3, max = 20, message = "用户名的长度在3-20位之间", groups = {ValidatorGroup.LoginUsername.class})
    private String username;

    /** 密码 **/
    @NotBlank(message = "请填写密码", groups = {ValidatorGroup.LoginUsername.class})
    @Length(min = 6, max = 20, message = "密码的长度在3-20位之间", groups = {ValidatorGroup.LoginUsername.class})
    private String password;

    /** 用户名 */
    @NotBlank(message = "请填写手机号码", groups = {ValidatorGroup.LoginMobile.class})
    @Pattern(regexp = RegexConstants.MOBILE, message = "请填写正确的手机号码", groups = {ValidatorGroup.LoginMobile.class})
    private String mobile;

    /** 短信验证码 **/
    @NotBlank(message = "请填写短信验证码", groups = {ValidatorGroup.LoginMobile.class})
    @Pattern(regexp = RegexConstants.NUMBER_SIX, message = "短信验证码为6位数字", groups = {ValidatorGroup.LoginMobile.class})
    private String smsCode;

}