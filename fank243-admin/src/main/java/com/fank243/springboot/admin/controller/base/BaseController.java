package com.fank243.springboot.admin.controller.base;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 基类
 * 
 * @author FanWeiJie
 * @date 2020-03-25 20:28:13
 */
public class BaseController {

    public String err401() {
        return "redirect:/error/403";
    }

    public String err403() {
        return "redirect:/error/403";
    }

    public String err404() {
        return "redirect:/error/404";
    }

    public String errMsg(String msg) {
        return "redirect:/error/msg?message=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
    }
}
