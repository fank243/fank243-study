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

package com.github.fank243.kong.oauth2.api.domain.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.model.validation.ValidatorGroup;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 登录实体
 * 
 * @author FanWeiJie
 * @date 2023-01-13 09:28
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OauthLoginDTO extends BaseDTO {

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 登录密码 */
    @NotBlank(message = "请填写登录密码", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[password]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String password;

    @NotBlank(message = "请填写图形验证码", groups = {ValidatorGroup.Login.class})
    private String imgCode;

    @NotBlank(message = "请重新获取图形验证码", groups = {ValidatorGroup.Login.class})
    private String randomStr;
}
