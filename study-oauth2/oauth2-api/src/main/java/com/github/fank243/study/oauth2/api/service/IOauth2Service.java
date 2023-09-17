package com.github.fank243.study.oauth2.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.oauth2.api.domain.dto.OauthAccessTokenDTO;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserDTO;

/**
 * Oauth2 客户端
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@FeignClient(contextId = "iOauth2Service", value = ServerConstants.SERVER_OAUTH2,
    path = ServerConstants.BASE_URI_OAUTH2)
public interface IOauth2Service {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 令牌
     */
    @PostMapping(value = "/doLogin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResultInfo<?> login(@RequestParam("name") String username, @RequestParam("pwd") String password);

    /**
     * 获取AccessToken > 密码模式
     * 
     * @param grantType 授权类型，{@see cn.dev33.satoken.oauth2.logic.SaOAuth2Consts.GrantType}
     * @param username 用户名
     * @param password 密码
     * @param scope 授权范围
     * @param clientId 客户端ID
     * @param clientSecret 客户端秘钥
     * @return 令牌
     */
    @PostMapping(value = "/token", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResultInfo<OauthAccessTokenDTO> getAccessToken(@RequestParam("grant_type") String grantType,
        @RequestParam("username") String username, @RequestParam("password") String password,
        @RequestParam("scope") String scope, @RequestParam("client_id") String clientId,
        @RequestParam("client_secret") String clientSecret);

    /**
     * 获取AccessToken > 授权码模式
     * 
     * @param grantType 授权类型，{@see cn.dev33.satoken.oauth2.logic.SaOAuth2Consts.GrantType}
     * @param code 授权代码
     * @param clientId 客户端ID
     * @param clientSecret 客户端秘钥
     * @return 令牌
     */
    @PostMapping(value = "/token", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResultInfo<OauthAccessTokenDTO> getAccessToken(@RequestParam("grant_type") String grantType,
        @RequestParam("code") String code, @RequestParam("client_id") String clientId,
        @RequestParam("client_secret") String clientSecret);

    /**
     * 刷新令牌
     *
     * @param grantType 授权类型
     * @param refreshToken 刷新令牌
     * @param clientId 客户端ID
     * @param clientSecret 客户端秘钥
     * @return 令牌
     */
    @PostMapping(value = "/refresh", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResultInfo<OauthAccessTokenDTO> refreshToken(@RequestParam("grant_type") String grantType,
        @RequestParam("refresh_token") String refreshToken, @RequestParam("client_id") String clientId,
        @RequestParam("client_secret") String clientSecret);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping(value = "/users/username")
    ResultInfo<?> getUserByUsername(@RequestParam("username") String username);

    /**
     * 获取用户信息
     *
     * @param accessToken 令牌
     * @param openId OpenID
     * @return 用户信息
     */
    @GetMapping(value = "/users/info")
    ResultInfo<OauthUserDTO> getUserInfo(@RequestParam("accessToken") String accessToken,
        @RequestParam("openId") String openId);

    /**
     * 创建账号
     *
     * @param oauthUserAccessTokenDTO 账号信息
     * @return OpenID
     */
    @PostMapping(value = "/users/add")
    ResultInfo<?> addUser(@RequestBody OauthUserAccessTokenDTO oauthUserAccessTokenDTO);

    /**
     * 修改密码
     *
     * @param oauthUserAccessTokenDTO 账号信息
     * @return 操作结果
     */
    @PostMapping(value = "/users/password")
    ResultInfo<?> modifyPassword(@RequestBody OauthUserAccessTokenDTO oauthUserAccessTokenDTO);

}
