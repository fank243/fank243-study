package com.github.fank243.study.core.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 配置参数
 * 
 * @author FanWeiJie
 * @date 2023-06-02 17:12
 */
@Data
@Component
public class StudyProperties {

    /** <a href="http://localhost:8900">...</a> **/
    public static String baseUrl;

    /** ClientID **/
    public static String clientId;

    /** ClientSecret **/
    public static String clientSecret;

    @Value("${study.base-url:}")
    public void setBaseUrl(String baseUrl) {
        StudyProperties.baseUrl = baseUrl;
    }

    @Value("${study.oauth2.client-id:}")
    public void setClientId(String clientId) {
        StudyProperties.clientId = clientId;
    }

    @Value("${study.oauth2.client-secret:}")
    public void setClientSecret(String clientSecret) {
        StudyProperties.clientSecret = clientSecret;
    }
}
