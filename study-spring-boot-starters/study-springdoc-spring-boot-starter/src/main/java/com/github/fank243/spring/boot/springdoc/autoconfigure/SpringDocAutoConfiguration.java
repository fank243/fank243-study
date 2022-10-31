package com.github.fank243.spring.boot.springdoc.autoconfigure;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.fank243.spring.boot.springdoc.properties.SpringDocProperties;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Spring Fox Auto Configuration
 * 
 * @author FanWeiJie
 * @date 2022-10-30 10:13
 */
@EnableConfigurationProperties(SpringDocProperties.class)
@Configuration
public class SpringDocAutoConfiguration {

    @Resource
    private SpringDocProperties springDocProperties;

    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact();
        contact.setName(springDocProperties.getAuthor().getName());
        contact.email(springDocProperties.getAuthor().getEmail());
        contact.url(springDocProperties.getAuthor().getUrl());

        // @formatter:off
        return new OpenAPI()
                .info(apiInfo())
                ;
        // @formatter:on
    }

    @Bean
    public Info apiInfo() {
        Contact contact = new Contact().name(springDocProperties.getAuthor().getName())
            .email(springDocProperties.getAuthor().getEmail()).url(springDocProperties.getAuthor().getUrl());

        License license = new License().name(springDocProperties.getApiInfo().getLicense())
            .url(springDocProperties.getApiInfo().getLicenseUrl());
        // @formatter:off
        return new Info()
                .title(springDocProperties.getApiInfo().getTitle())
                .description(springDocProperties.getApiInfo().getDescription())
                .version(springDocProperties.getApiInfo().getVersion())
                .termsOfService(springDocProperties.getApiInfo().getTermsOfServiceUrl())
                .license(license)
                .contact(contact);
        // @formatter:off
    }
}
