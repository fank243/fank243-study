package com.fank243.study.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Oauth2 属性
 * 
 * @author FanWeiJie
 * @since 2022-10-03 03:25:13
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "study.oauth2")
public class Oauth2Properties {

    /** 客户端ID **/
    private String clientId;

    /** 客户端秘钥 **/
    private String clientSecret;

}
