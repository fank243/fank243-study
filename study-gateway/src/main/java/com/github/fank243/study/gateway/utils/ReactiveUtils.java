package com.github.fank243.study.gateway.utils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import com.github.fank243.common.result.ResultCodeEnum;
import com.github.fank243.common.result.ResultInfo;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.codec.Base64;
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
    private static final String AUTHORIZE_URL =
        "/oauth2/authorize?response_type=code&client_id={{clientId}}&redirect_uri={{domain}}/system/login/{{redirect}}&scope=user_info";

    /**
     * 响应 JSON
     * 
     * @param response {@link ServerHttpResponse}
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
     * 响应 JSON
     * 
     * @param response {@link ServerHttpResponse}
     * @param result {@link ResultInfo}
     * @return void
     */
    public static Mono<Void> renderJsonOk(ServerHttpResponse response, ResultInfo<?> result) {
        response.setRawStatusCode(HttpStatus.OK.value());
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
    public static String getClientIp(ServerHttpRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }

        return getClientIpByHeader(request, headers);
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
    public static String getClientIpByHeader(ServerHttpRequest request, String... headerNames) {
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
        HttpStatusCode statusCode = response.getStatusCode();

        ResultInfo<?> result;
        switch (Objects.requireNonNull(statusCode).value()) {
            // 200
            case 200 -> {
                if (ex instanceof ResponseStatusException responseStatusException) {
                    switch (responseStatusException.getStatusCode().value()) {
                        case 400 -> {
                            // Spring Validate 参数验证异常
                            if (ex instanceof WebExchangeBindException webExchangeBindException) {
                                String message = webExchangeBindException.getAllErrors().get(0).getDefaultMessage();
                                result = ResultInfo.err400(message);
                            } else {
                                result = ResultInfo.err400(ex.getMessage()).error(ex.toString());
                            }
                        }
                        case 401 -> result = ResultInfo.err401();
                        case 403 -> result = ResultInfo.err403();
                        case 404 -> result = ResultInfo.err404();
                        case 405 -> result = ResultInfo.err405();
                        case 503 -> result = ResultInfo.err503();
                        default -> result = ResultInfo.err500(ex.getMessage()).error(ex.toString());
                    }
                } else if (ex instanceof SaTokenException) {
                    if (ex.getCause() instanceof NotLoginException) {
                        result = ResultInfo.err401().error(ex.getMessage());
                    } else if (ex.getCause() instanceof RuntimeException) {
                        result = ResultInfo.err500(ex.getMessage()).error(ex.getMessage());
                    } else {
                        result = ResultInfo.err403().error(ex.getMessage());
                    }
                } else {
                    result = ResultInfo.err500(ex.getMessage()).error(ex.toString());
                }
            }

            // 401
            case 401 -> {
                if (ex.getCause() instanceof NotLoginException) {
                    result = ResultInfo.err401(ResultCodeEnum.R401.getMessage()).error(ex.getMessage());
                } else {
                    result = ResultInfo.err401();
                }
            }
            // 403
            case 403 -> {
                if (ex instanceof SaTokenException && !(ex.getCause() instanceof NotLoginException)) {
                    result = ResultInfo.err403(ex.getMessage());
                } else {
                    result = ResultInfo.err403();
                }
            }
            // 404
            case 404 -> result = ResultInfo.err404();
            // 503
            case 503 -> result = ResultInfo.err503();
            // 500
            default -> result = ResultInfo.err500(ex.getMessage()).error(ex.toString());
        }

        return result;
    }

    /**
     * 获取访问域名
     *
     * @param request ServerHttpRequest
     * @return 域名
     */
    public static String getDomain(ServerHttpRequest request) {
        URI url = request.getURI();
        return url.getScheme() + "://" + url.getAuthority();
    }

    public static String getAuthorizeUrl(ServerHttpRequest request, String clientId, String redirect) {
        String domain = ReactiveUtils.getDomain(request);
        // @formatter:off
        return AUTHORIZE_URL.replace("{{clientId}}", clientId)
                .replace("{{domain}}", domain)
                .replace("{{redirect}}", Base64.encodeStr(redirect.getBytes(StandardCharsets.UTF_8), false, false));
        // @formatter:on
    }
}
