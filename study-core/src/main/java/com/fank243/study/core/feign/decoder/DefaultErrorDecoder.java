//package com.fank243.study.core.feign.decoder;
//
//import cn.hutool.http.HttpStatus;
//import feign.Response;
//import feign.RetryableException;
//import feign.codec.ErrorDecoder;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 自定义错误解码器
// *
// * @author FanWeiJie
// * @since 2021-06-14 17:51:19
// */
//@Slf4j
//public class DefaultErrorDecoder implements ErrorDecoder {
//
//    private final ErrorDecoder defaultErrorDecoder = new Default();
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        Exception exception = defaultErrorDecoder.decode(methodKey, response);
//        log.error("OpenFeign调用异常，响应状态：{}，异常类：{}", response.status(), exception.getClass().getName());
//
//        // 重试异常
//        if (exception instanceof RetryableException) {
//            return exception;
//        }
//
//        // 404 继续重试
//        if (response.status() == HttpStatus.HTTP_NOT_FOUND) {
//            return new RetryableException(response.status(), exception.getMessage(), response.request().httpMethod(),
//                null, response.request());
//        }
//
//        return exception;
//    }
//}
