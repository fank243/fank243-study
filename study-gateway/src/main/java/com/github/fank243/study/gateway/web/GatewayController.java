package com.github.fank243.study.gateway.web;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerWebExchange;

import com.github.fank243.study.core.constants.HttpConstants;
import com.github.fank243.study.gateway.utils.ReactiveUtils;

import cn.hutool.core.net.URLDecoder;
import lombok.Data;
import reactor.core.publisher.Mono;

/**
 * 登录重定向封装
 * 
 * @author FanWeiJie
 * @date 2023-05-31 15:27
 */
@Data
@Controller
@RequestMapping
public class GatewayController {

    private static final String LOGIN_URL =
        "/oauth2/authorize?response_type=code&client_id={{clientId}}&redirect_uri={{domain}}/system/login/{{redirect}}&scope=user_info";
    @Value("${study.oauth.client-id:}")
    private String clientId;

    @RequestMapping("/login")
    public Mono<Void> login(@RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
        ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", ReactiveUtils.getAuthorizeUrl(request, clientId, redirect));
        return response.setComplete();
    }

    @RequestMapping(HttpConstants.ERROR_401)
    public String err401(String message, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/401";
    }

    @RequestMapping(HttpConstants.ERROR_403)
    public String err403(String message, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/403";
    }

    @RequestMapping(HttpConstants.ERROR_404)
    public String err404(String message, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/404";
    }

    @RequestMapping(HttpConstants.ERROR_405)
    public String err405(String message, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/405";
    }

    @RequestMapping(HttpConstants.ERROR_500)
    public String err500(String message, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/500";
    }
}
