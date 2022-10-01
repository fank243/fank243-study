//package com.fank243.study.gateway.config;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//
//import com.fank243.study.common.core.utils.ResultInfo;
//import com.fank243.study.core.web.exception.BizException;
//
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.json.JSONUtil;
//import feign.Response;
//import feign.RetryableException;
//import feign.Retryer;
//import feign.Util;
//import feign.codec.ErrorDecoder;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * Open Feign 配置
// *
// * @author FanWeiJie
// * @since 2021-06-08 23:50:34
// */
//@Slf4j
//@Configuration
//public class FeignConfig {
//
//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new FeignErrorDecoder();
//    }
//
//    @Bean
//    public Retryer retryer() {
//        // 重试间隔：2s，最大重试间隔：4s，最多重试次数：5
//        return new Retryer.Default(2000, 4000, 5);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
//        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
//    }
//
//    /** 自定义错误解码器 */
//    static class FeignErrorDecoder extends ErrorDecoder.Default {
//
//        @Override
//        public Exception decode(String methodKey, Response response) {
//            Exception exception = super.decode(methodKey, response);
//            try {
//                if (exception instanceof RetryableException) {
//                    return exception;
//                }
//                String respTxt = Util.toString(response.body().asReader(StandardCharsets.ISO_8859_1));
//                if (StrUtil.isNotEmpty(respTxt)) {
//                    exception = new BizException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
//                    return exception;
//                }
//                if (!JSONUtil.isTypeJSON(respTxt)) {
//                    exception = new BizException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
//                    return exception;
//                }
//                ResultInfo<?> result = JSONUtil.toBean(respTxt, ResultInfo.class);
//                if (result == null) {
//                    exception = new BizException(StrUtil.format("系统出错了[{}]", exception.getLocalizedMessage()));
//                    return exception;
//                }
//                if (!result.isSuccess()) {
//                    exception = new BizException(response.status(), result.getMessage());
//                }
//                log.error("OpenFeign 调用异常：{}，响应信息：{}", response.status(), exception.getLocalizedMessage(), exception);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//                exception = new BizException(StrUtil.format("系统出错了[{}]", ioException.getLocalizedMessage()));
//            }
//            return exception;
//        }
//    }
//}
