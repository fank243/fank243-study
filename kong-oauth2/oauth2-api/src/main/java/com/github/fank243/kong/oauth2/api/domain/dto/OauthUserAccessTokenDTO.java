/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.oauth2.api.domain.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.model.validation.ValidatorGroup;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OauthUserAccessTokenDTO extends BaseDTO {

    public OauthUserAccessTokenDTO(Long userId, String username, String nickname, String openId) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.openId = openId;
    }

    @NotBlank(message = "参数[accessToken]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String accessToken;

    @NotBlank(message = "参数[openId]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String openId;

    /** 用户ID */
    private Long userId;

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    private String password;

}
