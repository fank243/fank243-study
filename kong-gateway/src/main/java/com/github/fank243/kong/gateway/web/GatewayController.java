/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.gateway.web;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerWebExchange;

import com.github.fank243.kong.core.constants.HttpConstants;
import com.github.fank243.kong.core.properties.KongProperties;
import com.github.fank243.kong.gateway.utils.ReactiveUtils;

import cn.hutool.core.net.URLDecoder;
import lombok.Data;
import reactor.core.publisher.Mono;

/**
 * 登录重定向封装
 * 
 * @author FanWeiJie
 * @since 2023-05-31 15:27
 */
@Data
@Controller
@RequestMapping
public class GatewayController {

    @RequestMapping("/login")
    public Mono<Void> login(@RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
        ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String authorizeUrl = ReactiveUtils.getAuthorizeUrl(request, KongProperties.clientId, redirect);
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location", authorizeUrl);
        return response.setComplete();
    }

    @RequestMapping(HttpConstants.ERROR_401)
    public String err401(String message, String path, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/401";
    }

    @RequestMapping(HttpConstants.ERROR_403)
    public String err403(String message, String path, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/403";
    }

    @RequestMapping(HttpConstants.ERROR_404)
    public String err404(String message, String path, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/404";
    }

    @RequestMapping(HttpConstants.ERROR_405)
    public String err405(String message, String path, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/405";
    }

    @RequestMapping(HttpConstants.ERROR_500)
    public String err500(String message, String path, Model model) {
        model.addAttribute("message", URLDecoder.decode(message, StandardCharsets.UTF_8));
        return "error/500";
    }
}
