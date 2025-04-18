/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.system.domain.dto;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.kong.tool.pattern.RegexExtPool;
import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.system.domain.enums.LoginTypeEnum;
import com.github.fank243.kong.system.domain.enums.UserStatusEnum;
import com.github.fank243.kong.core.model.validation.ValidatorGroup;
import com.github.fank243.kong.core.model.validation.annotation.Enum;
import com.mzt.logapi.starter.annotation.DiffLogAllFields;

import cn.hutool.core.lang.RegexPool;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Getter
@Setter
@SuperBuilder
@DiffLogAllFields
@NoArgsConstructor
public class SysUserDTO extends BaseDTO {

    /** 用户ID */
	@NotNull(message = "用户ID必传", groups = {ValidatorGroup.Modify.class})
    private Long userId;

    /** 用户名 */
    @Length(min = 3, max = 20, message = "用户名的长度在3-20位之间", groups = {ValidatorGroup.LoginUsername.class})
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.LoginUsername.class})
    private String username;

    /** 昵称 */
    private String nickname;

    /** 状态(0：正常，1：禁用，2：登录锁定) */
    private UserStatusEnum status;

    /** OpenID **/
    private String openId;

    /** 用户ID */
    @Enum(clazz = LoginTypeEnum.class, message = "登录方式参数有误", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "登录方式参数必传", groups = {ValidatorGroup.Login.class})
    private String loginType;

    /** 密码 **/
    @Length(min = 6, max = 20, message = "密码的长度在3-20位之间", groups = {ValidatorGroup.LoginUsername.class})
    @NotBlank(message = "请填写密码", groups = {ValidatorGroup.LoginUsername.class})
    private String password;

    /** 用户名 */
    @Pattern(regexp = RegexPool.MOBILE, message = "请填写正确的手机号码", groups = {ValidatorGroup.LoginMobile.class})
    @NotBlank(message = "请填写手机号码", groups = {ValidatorGroup.LoginMobile.class})
    private String mobile;

    /** 短信验证码 **/
    @Pattern(regexp = RegexExtPool.NUMBER_SIX, message = "短信验证码为6位数字", groups = {ValidatorGroup.LoginMobile.class})
    @NotBlank(message = "请填写短信验证码", groups = {ValidatorGroup.LoginMobile.class})
    private String smsCode;
}
