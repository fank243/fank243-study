package com.fank243.study.oauth2.controller;

import javax.servlet.http.HttpServletRequest;

import com.fank243.study.common.core.utils.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.json.JSONUtil;

/**
 *
 * @author FanWeiJie
 * @since 2022-09-30 16:18:51
 */
@RequestMapping
@Controller
public class Oauth2Controller {

    @RequestMapping("/token")
    public String token(HttpServletRequest request, String code, String state, ModelMap modelMap) {
        modelMap.put("code", code);
        modelMap.put("state", state);
        modelMap.put("scopes", "openid,message.read,message.write");
        modelMap.put("client_id", "messaging-client");
        modelMap.put("client_secret", "123456");
        modelMap.put("redirect_uri", ServletUtils.getBaseUrl(request) + "/callback");
        modelMap.put("grant_type", "authorization_code");
        return "token.html";
    }

    @RequestMapping("/callback")
    @ResponseBody
    public String callback(HttpServletRequest request) {
        return JSONUtil.toJsonStr(request.getParameterMap());
    }
}
