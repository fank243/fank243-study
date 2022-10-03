package com.fank243.study.gateway.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;

import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.utils.ResultInfo;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
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
     * @param result {@link ResultInfo}
     * @return void
     */
    public static Mono<Void> renderJson(ServerHttpResponse response, ResultInfo<?> result) {
        response.setRawStatusCode(result.getStatus());
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 获取客户端IP
     *
     * <p>
     * 默认检测的Header:
     *
     * <pre>
     * 1、X-Forwarded-For
     * 2、X-Real-IP
     * 3、Proxy-Client-IP
     * 4、WL-Proxy-Client-IP
     * </pre>
     *
     * <p>
     * otherHeaderNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request 请求对象{@link ServerHttpRequest}
     * @param otherHeaderNames 其他自定义头文件，通常在Http服务器（例如Nginx）中配置
     * @return IP地址
     */
    public static String getClientIP(ServerHttpRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }

        return getClientIPByHeader(request, headers);
    }

    /**
     * 获取客户端IP
     *
     * <p>
     * headerNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request 请求对象{@link ServerHttpRequest}
     * @param headerNames 自定义头，通常在Http服务器（例如Nginx）中配置
     * @return IP地址
     * @since 4.4.1
     */
    public static String getClientIPByHeader(ServerHttpRequest request, String... headerNames) {
        String ip;
        for (String header : headerNames) {
            List<String> headerList = request.getHeaders().get(header);
            if (CollUtil.isEmpty(headerList)) {
                continue;
            }
            ip = headerList.get(0);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }

        ip = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        return NetUtil.getMultistageReverseProxyIp(ip);
    }

    @SuppressWarnings("AlibabaSwitchStatement")
    public static ResultInfo<?> handlerException(ServerHttpResponse response, Throwable ex) {
        HttpStatus httpStatus = response.getStatusCode();

        ResultInfo<?> result;
        switch (Objects.requireNonNull(httpStatus)) {
            // 200
            case OK -> {
                if (ex instanceof ResponseStatusException responseStatusException) {
                    switch (responseStatusException.getRawStatusCode()) {
                        case 401 -> result = ResultInfo.err401();
                        case 403 -> result = ResultInfo.err403();
                        case 404 -> result = ResultInfo.err404();
                        case 405 -> result = ResultInfo.err405();
                        case 503 -> result = ResultInfo.err503();
                        default -> result = ResultInfo.error(ex.getMessage(), ex.toString());
                    }
                } else {
                    result = ResultInfo.error(ex.getMessage(), ex.toString());
                }
            }

            // 401
            case UNAUTHORIZED -> {
                if (ex instanceof AuthException || ex.getCause() instanceof NotLoginException) {
                    result = ResultInfo.err401(ex.getMessage());
                } else {
                    result = ResultInfo.err401();
                }
            }
            // 403
            case FORBIDDEN -> {
                if (ex instanceof SaTokenException && !(ex.getCause() instanceof NotLoginException)) {
                    result = ResultInfo.err403(ex.getMessage());
                } else {
                    result = ResultInfo.err403();
                }
            }
            // 404
            case NOT_FOUND -> result = ResultInfo.err404();
            // 503
            case SERVICE_UNAVAILABLE -> result = ResultInfo.err503();
            // 500
            default -> result = ResultInfo.error(ex.getMessage(), ex.toString());
        }

        return result;
    }
}
