package com.fank243.study.gateway.web.filter;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

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
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.fank243.study.api.support.api.IReqRespLogApi;
import com.fank243.study.api.support.domain.dto.ReqRespLogDTO;
import com.fank243.study.core.web.exception.BizException;
import com.fank243.study.gateway.constants.FilterOrderConstant;
import com.fank243.study.gateway.utils.LogUtils;

import brave.Span;
import brave.Tracer;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
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
@Component
public class LogFilter implements GlobalFilter, Ordered {

    @Resource
    private Tracer tracer;
    @Resource
    private IReqRespLogApi reqRespLogApi;
    /**
     * default HttpMessageReader
     */
    private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        Span currentSpan = tracer.currentSpan();
        String traceId = "", spanId = "";
        if (currentSpan != null) {
            traceId = currentSpan.context().traceIdString();
            spanId = currentSpan.context().spanIdString();
        }

        MediaType contentType = request.getHeaders().getContentType();

        ReqRespLogDTO logDTO = new ReqRespLogDTO();
        logDTO.setUserId(StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : null);
        logDTO.setClientIp(Objects.requireNonNull(request.getRemoteAddress()).getHostString());
        logDTO.setReqUri(request.getPath().toString());
        logDTO.setReqMethod(request.getMethodValue());
        logDTO.setContentType(contentType != null ? contentType.toString() : null);
        logDTO.setReqTime(new Date());
        logDTO.setTraceId(traceId);
        logDTO.setSpanId(spanId);

        Map<String, String> headerMap = new HashMap<>(request.getHeaders().entrySet().size());
        for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
            headerMap.put(entry.getKey(), entry.getValue().get(0));
        }
        String headers = JSONUtil.toJsonStr(headerMap);
        logDTO.setReqHeader(headers);

        if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)
            || MediaType.APPLICATION_JSON.isCompatibleWith(contentType)) {
            return writeBodyLog(exchange, chain, logDTO);
        }

        return writeBasicLog(exchange, chain, logDTO);
    }

    private Mono<Void> writeBasicLog(ServerWebExchange exchange, GatewayFilterChain chain, ReqRespLogDTO logDTO) {
        Map<String, String> params = exchange.getRequest().getQueryParams().toSingleValueMap();
        if (!params.isEmpty()) {
            logDTO.setReqBody(JSONUtil.toJsonStr(params));
        }
        // 获取响应体
        ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, logDTO);
        return chain.filter(exchange.mutate().response(decoratedResponse).build()).then(Mono.fromRunnable(() -> {
            LogUtils.printLog(logDTO);
            ThreadUtil.execAsync(() -> sendLog(logDTO));
        }));
    }

    /**
     * 解决request body 只能读取一次问题
     *
     * @param exchange ServerWebExchange
     * @param chain GatewayFilterChain
     * @param logDTO 请求响应信息
     */
    private Mono<Void> writeBodyLog(ServerWebExchange exchange, GatewayFilterChain chain, ReqRespLogDTO logDTO) {
        ServerRequest serverRequest = ServerRequest.create(exchange, MESSAGE_READERS);
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            if (JSONUtil.isTypeJSON(body)) {
                if (JSONUtil.isTypeJSONArray(body)) {
                    logDTO.setReqBody(JSONUtil.parseArray(body).toJSONString(0));
                } else {
                    logDTO.setReqBody(JSONUtil.parseObj(body).toJSONString(0));
                }
            } else {
                logDTO.setReqBody(body);
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
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, logDTO);

            // 记录普通的
            return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build())
                .then(Mono.fromRunnable(() -> {
                    LogUtils.printLog(logDTO);
                    ThreadUtil.execAsync(() -> sendLog(logDTO));
                }));
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
     * @param logDTO 请求响应信息
     * @return ServerHttpResponseDecorator
     */
    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, ReqRespLogDTO logDTO) {
        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();

        return new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    logDTO.setRespTime(new Date());

                    // 获取响应类型，如果是 json 就打印
                    String respContentType =
                        exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);

                    if (StrUtil.equals(respContentType, MediaType.APPLICATION_JSON_VALUE)) {
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
                            logDTO.setRespBody(responseResult);

                            return bufferFactory.wrap(content);
                        }));
                    }
                }
                return super.writeWith(body);
            }
        };
    }

    private void sendLog(ReqRespLogDTO reqRespLogDTO) {
        ThreadUtil.execAsync(() -> {
            try {
                reqRespLogApi.add(reqRespLogDTO);
            } catch (BizException e) {
                log.error("保存请求响应日志异常：{}", e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public int getOrder() {
        return FilterOrderConstant.getOrder(this.getClass().getName());
    }
}