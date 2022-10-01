package com.fank243.study.security.interceptor;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

import com.fank243.study.common.core.constants.SecurityConstants;
import com.fank243.study.common.core.utils.ServletUtils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Oauth2 feign token传递
 * 
 * @author FanWeiJie
 * @since 2022-10-01 22:29:03
 */
@Slf4j
@RequiredArgsConstructor
public class Oauth2RequestInterceptor implements RequestInterceptor {

    private final BearerTokenResolver tokenResolver;

    /**
     * Create a template with the header of provided name and extracted extract </br>
     *
     * 1. 如果使用 非web 请求，header 区别 </br>
     *
     * 2. 根据authentication 还原请求token
     * 
     * @param template RequestTemplate
     */
    @Override
    public void apply(RequestTemplate template) {
        Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
        // 带from 请求直接跳过
        if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
            return;
        }

        // 非web 请求直接跳过
        if (ServletUtils.getRequest() == null) {
            return;
        }
        HttpServletRequest request = ServletUtils.getRequest();
        // 避免请求参数的 query token 无法传递
        String token = tokenResolver.resolve(request);
        if (StrUtil.isBlank(token)) {
            return;
        }
        template.header(HttpHeaders.AUTHORIZATION,
            String.format("%s %s", OAuth2AccessToken.TokenType.BEARER.getValue(), token));

    }

}
