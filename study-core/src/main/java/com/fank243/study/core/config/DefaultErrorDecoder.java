package com.fank243.study.core.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.core.utils.ResultInfo;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Open Feign 配置
 * 
 * @author FanWeiJie
 * @date 2021-06-08 23:50:34
 */
@Slf4j
@EnableFeignClients(basePackages = {"com.fank243.study"})
@Configuration
public class DefaultErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);
        try {
            if (exception instanceof RetryableException) {
                return exception;
            }
            String respTxt = Util.toString(response.body().asReader(StandardCharsets.ISO_8859_1));
            if (StrUtil.isNotEmpty(respTxt)) {
                exception = new BizException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
                return exception;
            }
            if (!JSONUtil.isJson(respTxt)) {
                exception = new BizException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
                return exception;
            }
            ResultInfo<?> result = JSONUtil.toBean(respTxt, ResultInfo.class);
            if (result == null) {
                exception = new BizException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
                return exception;
            }
            if (!result.isSuccess()) {
                exception = new BizException(response.status(), result.getMessage());
            }
            log.error("OpenFeign 调用异常：{}，响应信息：{}", response.status(), exception.getLocalizedMessage(), exception);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            exception = new BizException(StrUtil.format("系统出错了[{}]", ioException.getLocalizedMessage()));
        }
        return exception;
    }
}
