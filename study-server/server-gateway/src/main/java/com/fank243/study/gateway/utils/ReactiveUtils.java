package com.fank243.study.gateway.utils;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;

import com.fank243.study.common.utils.ResultInfo;

import reactor.core.publisher.Mono;

/**
 * WebFlux 响应工具类
 * 
 * @author FanWeiJie
 * @since 2020-10-25 15:44:35
 */
public class ReactiveUtils {

    /**
     * 响应 JSON
     * 
     * @param response {@link ServerHttpResponse}
     * @param httpStatus {@link HttpStatus}
     * @param result {@link com.fank243.study.common.utils.ResultInfo}
     * @return void
     */
    public static Mono<Void> renderJson(ServerHttpResponse response, HttpStatus httpStatus, ResultInfo<?> result) {
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}
