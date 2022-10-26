package com.github.fank243.study.oauth2.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.core.net.URLDecoder;

/**
 * 错误页面
 * 
 * @author FanWeiJie
 * @since 2022-10-02 21:03:55
 */
@RequestMapping("/error")
@Controller
public class Oauth2ErrorController {

    @GetMapping("/401")
    public String err401(String message, ModelMap modelMap) {
        modelMap.put("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/401";
    }

    @GetMapping("/403")
    public String err403(String message, ModelMap modelMap) {
        modelMap.put("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/403";
    }

    @GetMapping("/404")
    public String err404(String message, ModelMap modelMap) {
        modelMap.put("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/404";
    }

    @GetMapping("/500")
    public String err500(String message, ModelMap modelMap) {
        modelMap.put("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/500";
    }
}
