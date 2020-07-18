package com.fank243.springboot.app.controller;

import com.fank243.springboot.app.AppApplication;
import com.fank243.springboot.app.consts.AppConfig;
import com.fank243.springboot.common.encrypt.EncryptUtils;
import com.fank243.springboot.common.encrypt.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class BaseTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        // 此种方式可通过spring上下文来自动配置一个或多个controller
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        long timestamp = System.currentTimeMillis();
        String deviceNumber = "AAAAAAAAAA-BBBBBBBBB-CCCCCCCCC";
        params.add("apiVer", "10");
        params.add("version", "1.0.0");
        params.add("timestamp", System.currentTimeMillis() + "");
        params.add("deviceType", "1");
        params.add("deviceNumber", deviceNumber);
        String planTxt = "10" + timestamp + deviceNumber;
        params.add("signature", EncryptUtils.md5(planTxt));

    }

    protected MultiValueMap<String, String> params = new LinkedMultiValueMap<>(7);

    protected MvcResult request(String url, MultiValueMap<String, String> params) throws Exception {
        log.info("请求参数：{}", params);
        return mockMvc.perform(//
            MockMvcRequestBuilders.post(url)//
                .params(params))//
            .andExpect(status().isOk())// 模拟向testRest发送get请求
            .andExpect(content().contentType("application/json;charset=UTF-8"))// 预期返回值的媒体类型text/plain;charset=UTF-8
            .andReturn();
    }

}
