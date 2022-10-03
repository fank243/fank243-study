package com.fank243.study.common.core;

import java.nio.charset.StandardCharsets;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * 测试专用
 * 
 * @author FanWeiJie
 * @since 2022-10-03 02:35:28
 */
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String uid = "10001524311652144201729" + i;
            String hashCode = uid.hashCode() + "";
            String key = Base64Encoder.encode(hashCode);
            System.out.println(hashCode + " ===> " + key);
            uid = SecureUtil.des(key.getBytes(StandardCharsets.UTF_8)).encryptBase64(uid);
            System.out.println(uid + " ===> " + uid.length());
        }
    }
}
