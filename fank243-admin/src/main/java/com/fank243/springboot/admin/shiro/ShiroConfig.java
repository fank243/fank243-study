package com.fank243.springboot.admin.shiro;

import com.fank243.springboot.admin.shiro.MyShiroRealm;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的， 防止密码在数据库里明码保存，当然在登陆认证的时候， 这个类也负责对form里输入的密码进行编码。
     *
     * 加密次数需要与中的加密次数一致，否则认证时密码校验不通过
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(ShiroUtils.ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        return credentialsMatcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /** 安全管理器 */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义realm
        securityManager.setRealm(myAuthorizingRealm());
        // 记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        // Session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /** 自定义的Realm */
    @Bean
    public MyShiroRealm myAuthorizingRealm() {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /** rememberMe 管理器 */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    /** 记住我Cookie */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        rememberMeCookie.setHttpOnly(true);
        // 7天
        rememberMeCookie.setMaxAge(7 * 24 * 60 * 60);
        return rememberMeCookie;
    }

    /** session管理器(单机环境) */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // session 30分钟
        sessionManager.setSessionValidationInterval(1800 * 1000);
        sessionManager.setGlobalSessionTimeout(900 * 1000);
        // 是否删除过期session
        sessionManager.setDeleteInvalidSessions(true);
        // 是否允许定时验证session有效性
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 去掉登录页面URL参数 JSESSIONID=7616f7a7-76f6-4b09-8a5c-17774f7ce2e0
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    /** Shiro的过滤器链 */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 默认的登陆访问url
        shiroFilter.setLoginUrl("/login.html");
        // 登陆成功后跳转的url
        shiroFilter.setSuccessUrl("/admin/console.html");
        // 没有权限跳转的url
        shiroFilter.setUnauthorizedUrl("/error/403");

        Map<String, String> hashMap = new LinkedHashMap<>();
        // 需要认证
        hashMap.put("/admin/**", "user");
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
