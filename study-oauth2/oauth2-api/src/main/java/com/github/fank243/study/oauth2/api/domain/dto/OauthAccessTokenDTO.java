/*
 * Copyright (c) 2024 fank243
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

package com.github.fank243.study.oauth2.api.domain.dto;

import com.github.fank243.study.core.base.BaseDTO;

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
@NoArgsConstructor
public class OauthAccessTokenDTO extends BaseDTO {

    /** OpenID **/
    private String openId;

    /** 客户端ID **/
    private String clientId;

    /** 授权作用域 **/
    private String scope;

    /** 令牌 */
    private String accessToken;

    /** 刷新令牌 */
    private String refreshToken;

    /** 令牌有效时长(秒) */
    private String expiresIn;

    /** 刷新令牌有效时长(秒) */
    private String refreshExpiresIn;
}
