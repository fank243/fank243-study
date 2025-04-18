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

package com.github.fank243.kong.oauth2.config;

import org.springframework.stereotype.Component;

import com.github.fank243.kong.oauth2.domain.OauthAccessTokenEntity;
import com.github.fank243.kong.oauth2.domain.OauthClientEntity;
import com.github.fank243.kong.oauth2.service.OauthAccessTokenService;
import com.github.fank243.kong.oauth2.service.OauthClientService;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Template;
import cn.dev33.satoken.oauth2.model.SaClientModel;
import cn.hutool.core.convert.Convert;
import jakarta.annotation.Resource;

/**
 * Sa-Token OAuth2.0 整合实现
 *
 * @author FanWeiJie
 * @since 2021-11-24 16:25:36
 */
@Component
public class Oauth2TemplateImpl extends SaOAuth2Template {

    @Resource
    private OauthClientService oauthClientService;
    @Resource
    private OauthAccessTokenService oauthAccessTokenService;

    /** 根据 id 获取 Client 信息 **/
    @Override
    public SaClientModel getClientModel(String clientId) {
        OauthClientEntity entity = oauthClientService.findByClientId(clientId);
        if (entity == null) {
            return null;
        }
        return new SaClientModel().setClientId(clientId ).setClientSecret(entity.getClientSecret()).setAllowUrl("*")
            .setContractScope(entity.getScope());
    }

    /** 根据ClientId 和 LoginId 获取openid **/
    @Override
    public String getOpenid(String clientId, Object userId) {
        OauthAccessTokenEntity entity = oauthAccessTokenService.findByUserId(Convert.toLong(userId));
        return entity.getOpenId();
    }
}
