/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.gateway.web.handler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.gateway.utils.ReactiveUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Slf4j
@Order(-1)
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    @NotNull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @NotNull Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        log.error("[网关异常处理]请求路径:{}，响应状态：{}，异常信息:{}", path, response.getStatusCode(), ex.getMessage(), ex);

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        ResultInfo<?> result = ReactiveUtils.handlerException(response, ex);
        result.setPath(path);

        if (ReactiveUtils.acceptTextHtml(request)) {
            String errUri = String.format("/error/%s?message=%s&path=%s", result.getStatus(),
                URLEncoder.encode(result.getMessage(), StandardCharsets.UTF_8), path);
            response.setStatusCode(HttpStatus.SEE_OTHER);
            response.getHeaders().set("Location", errUri);
            return response.setComplete();
        }

        return ReactiveUtils.renderJson(response, result);
    }
}
