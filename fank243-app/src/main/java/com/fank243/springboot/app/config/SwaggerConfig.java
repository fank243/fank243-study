package com.fank243.springboot.app.config;

import com.fank243.springboot.app.consts.AppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * swagger2 配置
 * 
 * @author FanWeiJie
 * @date 2020-01-02 13:47:49
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Resource
    private AppConfig appConfig;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)//
            .select()//
            .apis(RequestHandlerSelectors.basePackage("com.fank243.springboot.app.controller"))//
            .paths(PathSelectors.any())//
            .build()//
            .apiInfo(apiInfo())//
            .enable(appConfig.getSwagger());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
            .title("JBoot App")//
            .description("JBoot App 在线文档")//
            .termsOfServiceUrl("https://www.google.com")//
            .version("1.0.0").build();

    }
}