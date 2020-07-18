package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.common.encrypt.EncryptUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 友盟推送
 * 
 * @author FanWeiJie
 * @date 2019-12-20 16:34:58
 */
public class UpushUtils {

    public static void main(String[] args) {
        String url = "https://msgapi.umeng.com/api/send";
        String appkey = "5dfc8c740cafb24de2000f02";
        String secret = "d6a1bafdab56e4d59c8258525f4f7953";
        String masterSecret = "t9ohz1yutnb6nwkgfb1qfpylxq4zy2u6";

        Map<String, String> map = new HashMap<>();
        map.put("appkey", appkey);
        map.put("timestamp", System.currentTimeMillis() + "");
        map.put("type", "broadcast");
        // map.put("device_tokens", "unicast");

        JSONObject json = new JSONObject();
        json.put("display_type", "message");
        JSONObject body = new JSONObject();
        // body.put("ticker", "ce");
        // body.put("title", "测试");
        // body.put("text", "测试aaaa");
        body.put("custom", "处理卡时间段风口浪尖打算理发卡就是");
        json.put("body", body);
        map.put("payload", json.toString());
        map.put("production_mode", "false");

        String sign = EncryptUtils.md5("POST" + url + JSON.toJSONString(map) + masterSecret);

        String s = HttpsUtils.postForJson(url + "?sign=" + sign, map);
        System.out.println(s);
    }
}
