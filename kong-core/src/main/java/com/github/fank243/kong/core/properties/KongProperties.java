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

package com.github.fank243.kong.core.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 配置参数
 * 
 * @author FanWeiJie
 * @since 1.1.0
 */
@Data
@Component
public class KongProperties {

    /** <a href="http://localhost:8900">...</a> **/
    public static String baseUrl;

    /** ClientID **/
    public static String clientId;

    /** ClientSecret **/
    public static String clientSecret;

    @Value("${kong.base-url:}")
    public void setBaseUrl(String baseUrl) {
        KongProperties.baseUrl = baseUrl;
    }

    @Value("${kong.oauth2.client-id:}")
    public void setClientId(String clientId) {
        KongProperties.clientId = clientId;
    }

    @Value("${kong.oauth2.client-secret:}")
    public void setClientSecret(String clientSecret) {
        KongProperties.clientSecret = clientSecret;
    }
}
