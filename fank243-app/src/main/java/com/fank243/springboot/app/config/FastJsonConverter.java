package com.fank243.springboot.app.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加FastJson转换配置
 *
 * @author FanWeiJie
 * @date 2019-12-16 11:26:40
 */
@Configuration
public class FastJsonConverter {

    /** 当value为null时，将值替换为空串，防止返回客户端时参数不存在 **/
    @Bean
    public HttpMessageConverters fastJsonConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
            // 保留 Map 空的字段
            SerializerFeature.WriteMapNullValue,
            // 将 String 类型的 null 转成""
            SerializerFeature.WriteNullStringAsEmpty,
            // 将 Number 类型的 null 转成 0
            SerializerFeature.WriteNullNumberAsZero,
            // 将 List 类型的 null 转成 []
            SerializerFeature.WriteNullListAsEmpty,
            // 将 Boolean 类型的 null 转成 false
            SerializerFeature.WriteNullBooleanAsFalse,
            // 避免循环引用
            SerializerFeature.DisableCircularReferenceDetect);

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题，相当于在 Controller 上的 @RequestMapping 中加了个属性 produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);

        // 将转换器添加到converters中
        return new HttpMessageConverters(converter);
    }
}
