package com.fank243.springboot.admin.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常跳转页面
 * 
 * @author FanWeiJie
 * @date 2020-04-01 11:21:06
 */
@RequestMapping("/error")
@Controller
public class ErrorController {

    @RequestMapping("/401")
    public String err401(String message, Model model) {
        model.addAttribute("message", message);
        return "error/401";
    }

    @RequestMapping("/403")
    public String err403(String message, Model model) {
        model.addAttribute("message", message);
        return "error/403";
    }

    @RequestMapping("/404")
    public String err404(String message, Model model) {
        model.addAttribute("message", message);
        return "error/404";
    }

    @RequestMapping("/500")
    public String err500(String message, Model model) {
        model.addAttribute("message", message);
        return "error/500";
    }

    @RequestMapping("/msg")
    public String errMsg(String message, Model model) {
        model.addAttribute("message", message);
        return "error/msg";
    }
}
