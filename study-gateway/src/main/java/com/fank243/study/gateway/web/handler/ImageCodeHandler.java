package com.fank243.study.gateway.web.handler;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fank243.study.common.core.constants.CacheConstants;
import com.fank243.study.common.core.constants.SecurityConstants;
import com.fank243.study.common.core.service.RedisService;
import com.wf.captcha.GifCaptcha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 图形验证码生成
 * 
 * @author FanWeiJie
 * @since 2022-10-05 15:59:36
 */
@Slf4j
@RequiredArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {

    private static final Integer DEFAULT_IMAGE_WIDTH = 100;

    private static final Integer DEFAULT_IMAGE_HEIGHT = 40;

    private final RedisService redisService;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        GifCaptcha captcha = new GifCaptcha(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
        captcha.setLen(4);
        String result = captcha.text();

        // 保存验证码信息
        Optional<String> randomStr = serverRequest.queryParam("randomStr");
        randomStr
            .ifPresent(s -> redisService.setObj(CacheConstants.IMG_CODE_KEY + s, result, SecurityConstants.CODE_TIME));

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        captcha.out(os);

        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG)
            .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }

}
