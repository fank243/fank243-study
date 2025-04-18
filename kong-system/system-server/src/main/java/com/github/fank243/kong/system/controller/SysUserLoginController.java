/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.system.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.github.fank243.kong.tool.validator.BeanValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.base.BaseController;
import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.system.domain.enums.LoginTypeEnum;
import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.core.model.validation.ValidatorGroup;
import com.github.fank243.kong.core.properties.KongProperties;
import com.github.fank243.kong.core.utils.WebUtils;
import com.github.fank243.kong.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthAccessTokenDTO;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthUserDTO;
import com.github.fank243.kong.oauth2.api.service.IOauth2Service;
import com.github.fank243.kong.system.domain.entity.SysUserEntity;
import com.github.fank243.kong.system.domain.dto.SysUserDTO;
import com.github.fank243.kong.system.domain.vo.SysUserLoginVO;
import com.github.fank243.kong.system.service.SysUserService;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理员登录 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Slf4j
@RequestMapping
@Controller
public class SysUserLoginController extends BaseController {

    @Resource
    private IOauth2Service oauth2Service;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private RedisService redisService;

    /**
     * Oauth2 > 密码模式
     *
     * @param sysUserDTO 请求参数
     * @return 登录用户信息
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo<?> login(HttpServletRequest request,
        @RequestBody @Validated({ValidatorGroup.Login.class}) SysUserDTO sysUserDTO) {
        Class<?> clazz = ValidatorGroup.LoginUsername.class;
        if (LoginTypeEnum.MOBILE.name().equalsIgnoreCase(sysUserDTO.getLoginType())) {
            clazz = ValidatorGroup.LoginMobile.class;
        }
        ResultInfo<?> validateResult = BeanValidator.validate(sysUserDTO, clazz);
        if (!validateResult.isSuccess()) {
            return validateResult;
        }

        // 获取令牌
        ResultInfo<OauthAccessTokenDTO> result = oauth2Service.getAccessToken(
            Oauth2Constants.GrantType.PASSWORD.name().toLowerCase(), sysUserDTO.getUsername(), sysUserDTO.getPassword(),
            String.join(",", Oauth2Constants.Scope.SCOPE_ALL), KongProperties.clientId, KongProperties.clientSecret);
        if (!result.isSuccess()) {
            return result;
        }
        return doLogin(request, result.getPayload());
    }

    /**
     * Oauth2 > 授权码模式 认证授权成功后回调后进行本地登录
     *
     * @param code 授权码
     * @return 登录用户信息
     */
    @GetMapping({"/login", "/login/{redirect}"})
    public String login(@PathVariable(value = "redirect", required = false) String redirect, HttpServletRequest request,
        HttpServletResponse response, String code, String message) throws IOException {
        // 用户拒绝授权
        if (StrUtil.isNotBlank(message)) {
            WebUtils.renderJson(response, ResultInfo.err500(URLDecoder.decode(message, StandardCharsets.UTF_8)));
            return null;
        }
        if (StrUtil.isBlank(code)) {
            WebUtils.renderJson(response, ResultInfo.err400("code不能为空"));
            return null;
        }
        // 获取令牌
        ResultInfo<OauthAccessTokenDTO> result =
            oauth2Service.getAccessToken(Oauth2Constants.GrantType.AUTHORIZATION_CODE.name().toLowerCase(), code,
                KongProperties.clientId, KongProperties.clientSecret);
        if (!result.isSuccess()) {
            WebUtils.renderJson(response, result);
            return null;
        }

        ResultInfo<?> resultInfo = doLogin(request, result.getPayload());
        if (!resultInfo.isSuccess()) {
            WebUtils.renderJson(response, resultInfo);
            return null;
        }

        redirect =
            StrUtil.isNotBlank(redirect) ? Base64.decodeStr(redirect, StandardCharsets.UTF_8) : KongProperties.baseUrl;
        response.sendRedirect(redirect);
        return null;
    }

    private ResultInfo<?> doLogin(HttpServletRequest request, OauthAccessTokenDTO oauthAccessTokenDTO) {
        // 获取用户信息
        ResultInfo<OauthUserDTO> result =
            oauth2Service.getUserInfo(oauthAccessTokenDTO.getAccessToken(), oauthAccessTokenDTO.getOpenId());
        if (!result.isSuccess()) {
            return result;
        }
        OauthUserDTO oauthUserDTO = result.getPayload();

        String clientIp = JakartaServletUtil.getClientIP(request);
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        // 本地登录
        ResultInfo<?> loginResult = sysUserService.login(oauthUserDTO.getOpenId(), clientIp, userAgent);
        if (!loginResult.isSuccess()) {
            return loginResult;
        }
        SysUserEntity sysUserEntity = (SysUserEntity)loginResult.getPayload();
        SysUserLoginVO sysUserLoginVO = BeanUtil.copyProperties(sysUserEntity, SysUserLoginVO.class);
        // 获取登录Token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        sysUserLoginVO.setTokenName(tokenInfo.getTokenName());
        sysUserLoginVO.setTokenValue(tokenInfo.getTokenValue());
        sysUserLoginVO.setTokenTimeout(tokenInfo.getTokenTimeout());
        sysUserLoginVO.setSessionTimeout(tokenInfo.getSessionTimeout());
        sysUserLoginVO.setTokenSessionTimeout(tokenInfo.getTokenSessionTimeout());
		sysUserLoginVO.setTokenActiveTimeout(tokenInfo.getTokenActiveTimeout());

        // 写入redis
        String key = CacheConstants.OAUTH2_TOKEN + sysUserLoginVO.getId();
        redisService.setObj(key, oauthAccessTokenDTO, TimeConstants.MINUTE_30);

        return ResultInfo.ok(sysUserLoginVO);
    }

    /** 登出 **/
    @GetMapping("/logout")
    @ResponseBody
    public ResultInfo<?> sysUserLogout() {
        logout();
        return ResultInfo.ok().message("登出成功");
    }
}
