package com.fank243.study.oauth2.utils;

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

    public static String generateOpenID(String clientId, String userId) {
        String key = clientId + userId;
        String desKey = Base64Encoder.encode(key);
        return SecureUtil.des(desKey.getBytes(StandardCharsets.UTF_8)).encryptBase64(key);
    }

    public static void main(String[] args) {
        String clientId = "1000", userId = "152431165214420172950000152431165214420172950000152431165214420172950000";
        for (int i = 0; i < 100; i++) {
            String openId = generateOpenID(clientId, userId + i);
            System.out.println(openId + " ===> " + openId.length());
        }
    }
}
