package com.fank243.study.gateway.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Oauth2 Feign Client
 * 
 * @author FanWeiJie
 * @since 2021-11-29 09:53:41
 */
@FeignClient(name = "server-oauth2")
public interface OauthFeignClient {

    /**
     * 申请令牌
     * 
     * @param params 请求参数
     * @return 返回结果
     */
    @RequestMapping("/oauth2/token")
    String token(@RequestParam Map<String, Object> params);

    /**
     * 刷新令牌
     * 
     * @param params 请求参数
     * @return 返回结果
     */
    @RequestMapping("/oauth2/refresh")
    String refresh(@RequestParam Map<String, Object> params);
}
