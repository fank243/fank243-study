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

package com.github.fank243.kong.oauth2.service;

import org.springframework.stereotype.Service;

import com.github.fank243.kong.oauth2.domain.OauthClientEntity;
import com.github.fank243.kong.oauth2.repository.IOauthClientRepository;

import jakarta.annotation.Resource;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthClientService {

    @Resource
    private IOauthClientRepository oauthClientRepository;

    public OauthClientEntity findByClientId(String clientId) {
         return oauthClientRepository.findByClientId(clientId);
    }

}
