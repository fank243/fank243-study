/*
 * Copyright (c) 2024 fank243
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

package com.github.fank243.study.core.model.feign;

import java.nio.charset.StandardCharsets;

import com.github.fank243.kong.tool.result.ResultInfo;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理器
 * 
 * @author FanWeiJie
 * @date 2022/10/25 14:28
 */
@Slf4j
public class FeignErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception;
        try {
            String respTxt = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            exception = super.decode(methodKey, response);
            if (exception instanceof RetryableException) {
                return exception;
            }
            if (StrUtil.isEmpty(respTxt)) {
                exception = new RuntimeException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
                return exception;
            }
            if (!JSONUtil.isTypeJSON(respTxt)) {
                exception = new RuntimeException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
                return exception;
            }
            ResultInfo<?> result = JSONUtil.toBean(respTxt, ResultInfo.class);
            if (result == null) {
                return new RuntimeException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
            }
            if (!result.isSuccess()) {
                exception = new RuntimeException(result.getMessage());
            }
            log.error("OpenFeign 调用异常：{}，响应信息：{}", response.status(), exception.getLocalizedMessage(), exception);
        } catch (Exception ioException) {
			log.error(ioException.getMessage(), ioException);
            exception = new RuntimeException(StrUtil.format("系统出错了[{}]", ioException.getLocalizedMessage()));
        }
        return exception;
    }
}