package com.github.fank243.spring.boot.springdoc.properties;

import static com.github.fank243.spring.boot.springdoc.properties.SpringDocProperties.PREFIX;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Spring Fox Properties
 * 
 * @author FanWeiJie
 * @date 2022-10-30 10:13
 */
@Data
@ConfigurationProperties(prefix = PREFIX)
public class SpringDocProperties {

    public static final String PREFIX = "study.spring-doc";

    private String basePackage = "com.github.fank243.study";

    /** 作者相关信息 **/
    private Author author = new Author();

    /** API的相关信息 **/
    private ApiInfo apiInfo = new ApiInfo();

    @Data
    public static class ApiInfo {
        private String title = "Api接口文档";
        private String description = "Api接口文档";
        private String version = "1.0";
        private String termsOfServiceUrl;
        private String license = "Apache 2.0";
        private String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0.html";
    }

    @Data
    public static class Author {
        private String name = "FanWeiJie";
        private String email = "fank243@163.com";
        private String url = "https://github.com/fank243";
    }
}
