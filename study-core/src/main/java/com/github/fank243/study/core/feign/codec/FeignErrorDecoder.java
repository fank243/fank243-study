package com.github.fank243.study.core.feign.codec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.github.fank243.common.result.ResultInfo;

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
        Exception exception = super.decode(methodKey, response);
        try {
            if (exception instanceof RetryableException) {
                return exception;
            }
            String respTxt = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
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
        } catch (IOException ioException) {
            ioException.printStackTrace();
            exception = new RuntimeException(StrUtil.format("系统出错了[{}]", ioException.getLocalizedMessage()));
        }
        return exception;
    }
}