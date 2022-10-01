package com.fank243.study.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.json.JSONUtil;

@Controller
public class IndexController {

    @RequestMapping("/getToken")
    public String token(String code, String state, ModelMap modelMap) {
        modelMap.put("grant_type", "authorization_code");
        modelMap.put("code", code);
        modelMap.put("state", state);
        modelMap.put("client_id", "client1");
        modelMap.put("client_secret", "123456");
        modelMap.put("redirect_uri", "http://127.0.0.1:8904/getToken");
        return "token";
    }

    @RequestMapping("/callback")
    @ResponseBody
    public String callback(@RequestBody String body) {
        return JSONUtil.toJsonStr(body);
    }
}
