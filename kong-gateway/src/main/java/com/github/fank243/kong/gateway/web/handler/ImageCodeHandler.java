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

package com.github.fank243.kong.gateway.web.handler;

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

import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.SecurityConstants;
import com.github.fank243.kong.core.model.redis.RedisService;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
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
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
        String result = lineCaptcha.getCode();

        // 保存验证码信息
        Optional<String> randomStr = serverRequest.queryParam("randomStr");
        randomStr
            .ifPresent(s -> redisService.setObj(CacheConstants.IMG_CODE_KEY + s, result, SecurityConstants.CODE_TIME));

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        lineCaptcha.write(os);

        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG)
            .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }

}
