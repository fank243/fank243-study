package com.fank243.study.oauth2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fank243.study.common.core.utils.ServletUtils;

/**
 *
 * @author FanWeiJie
 * @since 2022-09-30 16:18:51
 */
@RequestMapping
@Controller
public class Oauth2Controller {

    @RequestMapping
    public String index(HttpServletRequest request, ModelMap modelMap) {
        String baseUrl = ServletUtils.getBaseUrl(request);

        String params = "?response_type=code&client_id=client1&scope=all&state=some-state&redirect_uri="
            + "http://127.0.0.1:8904/getToken";

        modelMap.put("authorizeUrl", baseUrl + "/oauth2/authorize" + params);
        return "index";
    }
}
