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

package com.github.fank243.kong.gateway.web.filter;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.github.fank243.kong.core.model.log.LogUtils;
import com.github.fank243.kong.core.model.log.ReqRespLog;
import com.github.fank243.kong.gateway.constants.FilterOrderConstant;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Request Params Filter
 *
 * @author FanWeiJie
 * @since 2021-07-24 15:45:46
 */
@Slf4j
@RequiredArgsConstructor
public class ApiLogFilter implements GlobalFilter, Ordered {

    private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String traceId = TraceContext.traceId(), spanId = String.valueOf(TraceContext.spanId());

        MediaType contentType = request.getHeaders().getContentType();

        String loginId = "";
        try {
            loginId = StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : "";
        } catch (Exception e) {
            log.warn("获取登录用户信息异常：{}", e.getMessage(), e);
        }

        ReqRespLog reqRespLog = new ReqRespLog();
        reqRespLog.setUserId(loginId);
        reqRespLog.setClientIp(Objects.requireNonNull(request.getRemoteAddress()).getHostString());
        reqRespLog.setReqUri(request.getPath().toString());
        reqRespLog.setReqMethod(request.getMethod().name());
        reqRespLog.setContentType(contentType != null ? contentType.toString() : null);
        reqRespLog.setReqTime(new Date());
        reqRespLog.setTraceId(traceId);
        reqRespLog.setSpanId(spanId);

        Map<String, String> headerMap = new HashMap<>(request.getHeaders().entrySet().size());
        for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
            headerMap.put(entry.getKey(), entry.getValue().get(0));
        }
        String headers = JSONUtil.toJsonStr(headerMap);
        reqRespLog.setReqHeader(headers);

        if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)
            || MediaType.APPLICATION_JSON.isCompatibleWith(contentType)) {
            return writeBodyLog(exchange, chain, reqRespLog);
        }

        return writeBasicLog(exchange, chain, reqRespLog);
    }

    private Mono<Void> writeBasicLog(ServerWebExchange exchange, GatewayFilterChain chain, ReqRespLog reqRespLog) {
        Map<String, String> params = exchange.getRequest().getQueryParams().toSingleValueMap();
        if (!params.isEmpty()) {
            reqRespLog.setReqBody(JSONUtil.toJsonStr(params));
        }
        // 获取响应体
        ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, reqRespLog);
        return chain.filter(exchange.mutate().response(decoratedResponse).build())
            .then(Mono.fromRunnable(() -> LogUtils.printLog(reqRespLog)));
    }

    /**
     * 解决request body 只能读取一次问题
     *
     * @param exchange ServerWebExchange
     * @param chain GatewayFilterChain
     * @param reqRespLog 请求响应信息
     */
    private Mono<Void> writeBodyLog(ServerWebExchange exchange, GatewayFilterChain chain, ReqRespLog reqRespLog) {
        ServerRequest serverRequest = ServerRequest.create(exchange, MESSAGE_READERS);
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            if (JSONUtil.isTypeJSON(body)) {
                if (JSONUtil.isTypeJSONArray(body)) {
                    reqRespLog.setReqBody(JSONUtil.parseArray(body).toJSONString(0));
                } else {
                    reqRespLog.setReqBody(JSONUtil.parseObj(body).toJSONString(0));
                }
            } else {
                reqRespLog.setReqBody(body);
            }
            return Mono.just(body);
        });

        // 通过 BodyInsert 插入 body(支持修改body), 避免 request body 只能获取一次
        BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter =
            BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());

        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);

        return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
            // 重新封装请求
            ServerHttpRequest decoratedRequest = requestDecorate(exchange, headers, outputMessage);

            // 记录响应日志
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, reqRespLog);

            // 记录普通的
            return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build())
                .then(Mono.fromRunnable(() -> LogUtils.printLog(reqRespLog)));
        }));
    }

    /**
     * 请求装饰器，重新计算 headers
     *
     * @param exchange ServerWebExchange
     * @param headers HttpHeaders
     * @param outputMessage CachedBodyOutputMessage
     * @return ServerHttpRequestDecorator
     */
    private ServerHttpRequestDecorator requestDecorate(ServerWebExchange exchange, HttpHeaders headers,
        CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @NotNull
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @NotNull
            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    /**
     * 记录响应日志
     *
     * @param exchange ServerWebExchange
     * @param reqRespLog 请求响应信息
     * @return ServerHttpResponseDecorator
     */
    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, ReqRespLog reqRespLog) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();

        return new ServerHttpResponseDecorator(response) {
            @NotNull
            @Override
            public Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    reqRespLog.setRespTime(new Date());

                    // 获取响应类型，如果是 json 就打印
                    String respContentType =
                        exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);

                    if (respContentType != null) {
                        if (respContentType.startsWith(MediaType.APPLICATION_JSON_VALUE)
                            || respContentType.startsWith(MediaType.TEXT_PLAIN_VALUE)) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            return super.writeWith(fluxBody.buffer().map(dataBuffers -> {

                                // 合并多个流集合，解决返回体分段传输
                                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                                DataBuffer join = dataBufferFactory.join(dataBuffers);
                                byte[] content = new byte[join.readableByteCount()];
                                join.read(content);

                                // 释放掉内存
                                DataBufferUtils.release(join);
                                String responseResult = new String(content, StandardCharsets.UTF_8);
                                reqRespLog.setRespBody(responseResult);

                                return bufferFactory.wrap(content);
                            }));
                        }
                    }
                }
                return super.writeWith(body);
            }
        };
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }
}
