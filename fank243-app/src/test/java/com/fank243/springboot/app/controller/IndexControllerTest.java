package com.fank243.springboot.app.controller;

import com.alibaba.fastjson.JSON;
import com.fank243.springboot.app.consts.AppConfig;
import com.fank243.springboot.common.encrypt.RsaUtils;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class IndexControllerTest extends BaseTest {

    @Resource
    private AppConfig appConfig;

    @Test
    public void index() throws Exception {
        Map<String, Object> payload = new HashMap<>(3);
        payload.put("page", "1");
        payload.put("limit", "6");
        payload.put("userId", "0");
        String paylod = JSON.toJSONString(payload);
        String sign = RsaUtils.pubEncrypt(appConfig.getRsaPub(), paylod);
        params.add("payload", sign);

        MvcResult result = request("/app/index", params);
        System.out.println(result.getResponse().getContentAsString());
    }
}