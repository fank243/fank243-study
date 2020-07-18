package com.fank243.springboot.third.service;

import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.third.service.aliyun.AliyunSmsService;
import org.junit.Test;

/**
 * Aliyun SMS Test
 *
 * @author FanWeiJie
 * @date 2020-03-24 15:56:40
 */
public class AliyunSmsServiceTest extends AliyunTest {

    private AliyunSmsService aliyunSmsService = new AliyunSmsService();

    @Test
    public void sendSms() {
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", "123456");
        ResultInfo result = aliyunSmsService.sendSms(accessKeyId, accessKeySecret, "签名", "SMS_123456",
            "13212345678", smsJson.toString());
        System.out.println(result);
    }
}