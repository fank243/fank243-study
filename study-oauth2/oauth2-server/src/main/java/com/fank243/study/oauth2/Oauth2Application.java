package com.fank243.study.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fank243.study.common.core.constants.Constants;

/**
 * Oauth2
 *
 * @author FanWeiJie
 * @since 2021-11-15 14:24:02
 */
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@SpringBootApplication
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }
}
