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

package com.github.fank243.kong.oauth2.utils;

import java.nio.charset.StandardCharsets;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;

/**
 * 工具类
 * 
 * @author FanWeiJie
 * @since 2022-10-03 09:59:31
 */
public class Oauth2Utils {

    public static String generateOpenId(String clientId, Long userId) {
        String key = clientId + userId;
        String desKey = Base64Encoder.encode(key);
        return SecureUtil.des(desKey.getBytes(StandardCharsets.UTF_8)).encryptBase64(key);
    }

    public static void main(String[] args) {
        String clientId = "1000";
        long userId = 15243116521442L;
        int length = 100;
        for (int i = 0; i < length; i++) {
            String openId = generateOpenId(clientId, userId + 1);
            System.out.println(openId + " ===> " + openId.length());
        }
    }
}
