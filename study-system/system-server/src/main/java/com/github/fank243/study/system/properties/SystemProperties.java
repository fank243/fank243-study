package com.github.fank243.study.system.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Oauth2 属性
 * 
 * @author FanWeiJie
 * @since 2022-10-03 03:25:13
 */
@Data
@Component
public class SystemProperties {

    /** 客户端ID **/
    @Value("${study.oauth2.client-id:}")
    private String clientId;

    /** 客户端秘钥 **/
    @Value("${study.oauth2.client-secret:}")
    private String clientSecret;

}
