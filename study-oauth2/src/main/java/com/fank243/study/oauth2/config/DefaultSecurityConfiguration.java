package com.fank243.study.oauth2.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration
 * 
 * @author FanWeiJie
 * @since 2022-10-01 14:55:42
 */
@EnableWebSecurity
public class DefaultSecurityConfiguration {

    /**
     * 配置默认拦截器
     * 
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests(
            // 拦截所有请求，需要认证通过后才可以访问
            authorizeRequests -> authorizeRequests.anyRequest().authenticated()
        )
        // 登录主体
        .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // 基于数据库认证主体实体配置
        return new JdbcUserDetailsManager(dataSource);
    }

}
