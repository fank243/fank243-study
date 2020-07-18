package com.fank243.springboot.app.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro Configuration
 *
 * @author FanWeiJie
 * @date 2020-03-22 13:39:09
 */
@Configuration
public class ShiroConfig {

    @Bean
    public AuthorizationAttributeSourceAdvisor
        authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /** 安全管理器 */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义realm
        securityManager.setRealm(myShiroRealm());
        // 关闭Shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 设置自定义Cache缓存
        // securityManager.setCacheManager(new CustomCacheManager());
        return securityManager;
    }

    /** 自定义的Realm */
    @Bean
    public MyShiroRealm myShiroRealm() {
        return new MyShiroRealm();
    }

    /** Shiro的过滤器链 */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new HashMap<>(1);
        filterMap.put("app", new MyShiroFilter());
        shiroFilter.setFilters(filterMap);

        Map<String, String> hashMap = new LinkedHashMap<>();
        // 需要认证
        hashMap.put("/app/**", "app");
        hashMap.put("/**", "anon");

        shiroFilter.setFilterChainDefinitionMap(hashMap);

        return shiroFilter;
    }

    /** Shiro生命周期处理器 */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
