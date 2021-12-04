package com.fank243.study.gateway.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fank243.study.core.exception.AuthException;
import com.fank243.study.gateway.domain.AccessTokenVO;
import com.fank243.study.gateway.domain.LoginUserDTO;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Oauth2 客户端
 *
 * @author FanWeiJie
 * @since 2021-11-26 13:36:40
 */
@Slf4j
@Service
public class OauthClientService {
    @Resource
    private OauthFeignClient oauthFeignClient;

    private AccessTokenVO request(String method, Map<String, Object> params) throws AuthException {
        Future<String> future;
        if ("token".equalsIgnoreCase(method)) {
            future = ThreadUtil.execAsync(() -> oauthFeignClient.token(params));
        } else {
            future = ThreadUtil.execAsync(() -> oauthFeignClient.refresh(params));
        }
        return parseResponse(future);
    }

    private AccessTokenVO parseResponse(Future<String> future) throws AuthException {
        try {
            String body = future.get();
            if (!JSONUtil.isJson(body)) {
                throw new AuthException("申请令牌失败：内部网络错误");
            }

            JSONObject json = JSONUtil.parseObj(body);
            if (!json.containsKey("code") || json.getInt("code") != HttpStatus.OK.value()) {
                throw new AuthException(json.getStr("msg"));
            }
            JSONObject respData = json.getJSONObject("data");

            return AccessTokenVO.builder().openId(respData.getStr("openid")).clientId(respData.getStr("client_id"))
                .scope(respData.getStr("scope")).accessToken(respData.getStr("access_token"))
                .expiresIn(respData.getStr("expires_in")).refreshToken(respData.getStr("refresh_token"))
                .refreshExpiresIn(respData.getStr("refresh_expires_in")).build();
        } catch (InterruptedException | ExecutionException e) {
            log.error("调用Oauth2服务异常：{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 向oauth2服务端申请令牌
     *
     * @param loginDTO 请求参数
     * @return 令牌
     * @throws AuthException AuthException
     */
    public AccessTokenVO token(LoginUserDTO loginDTO) throws AuthException {
        Map<String, Object> params = new HashMap<>(4);
        params.put("grant_type", "password");
        params.put("client_id", loginDTO.getClientId());
        params.put("username", loginDTO.getUsername());
        params.put("password", loginDTO.getPassword());

        // 发送请求
        return request("token", params);
    }

    /**
     * 向oauth2服务端刷新令牌
     *
     * @param loginDTO 请求参数
     * @return 令牌
     * @throws AuthException AuthException
     */
    public AccessTokenVO refresh(LoginUserDTO loginDTO) throws AuthException {
        Map<String, Object> params = new HashMap<>(4);
        params.put("grant_type", "refresh_token");
        params.put("client_id", loginDTO.getClientId());
        params.put("client_secret", loginDTO.getClientSecret());
        params.put("refresh_token", loginDTO.getRefreshToken());

        // 发送请求
        return request("refresh", params);
    }
}
