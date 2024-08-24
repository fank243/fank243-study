/*
 * Copyright (c) 2024 fank243
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

package com.github.fank243.study.oauth2.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserDTO;
import com.github.fank243.study.oauth2.domain.OauthUserEntity;
import com.github.fank243.study.oauth2.service.OauthUserService;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Util;
import cn.dev33.satoken.oauth2.model.AccessTokenModel;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户接口
 * 
 * @author FanWeiJie
 * @since 2022-10-03 08:48:29
 */
@Slf4j
@Validated
@RequestMapping(ServerConstants.BASE_URI_OAUTH2)
@RestController
public class OauthUserController {

    @Resource
    private OauthUserService oauthUserService;

    private ResultInfo<?> validate(String accessToken, String openId) {
        AccessTokenModel accessTokenModel;
        try {
            accessTokenModel = SaOAuth2Util.checkAccessToken(accessToken);
            if (!openId.equalsIgnoreCase(accessTokenModel.openid)) {
                return ResultInfo.err403("invalid openid");
            }
        } catch (Exception e) {
            return ResultInfo.err403(e.getMessage());
        }

        return ResultInfo.ok(accessTokenModel);
    }

    private ResultInfo<?> validateUserModify(String accessToken, String openId) {
        ResultInfo<?> result = validate(accessToken, openId);
        if (!result.isSuccess()) {
            return result;
        }

        // 验证用户是否具有修改用户权限
        SaOAuth2Util.checkScope(accessToken, Oauth2Constants.Scope.USER_MODIFY.name().toLowerCase());

        return result;
    }

    /** 获取用户信息 **/
    @GetMapping({"/users/info"})
    public ResultInfo<?> userInfo(String accessToken, String openId) {
        ResultInfo<?> result = validate(accessToken, openId);
        if (!result.isSuccess()) {
            return result;
        }
        AccessTokenModel accessTokenModel = (AccessTokenModel)result.getPayload();

        OauthUserEntity oauthUserEntity =
            OauthUserEntity.builder().userId(String.valueOf(accessTokenModel.loginId)).build();
        oauthUserEntity = oauthUserService.findByCondition(oauthUserEntity);
        OauthUserDTO oauthUserDTO = BeanUtil.copyProperties(oauthUserEntity, OauthUserDTO.class);
        oauthUserDTO.setOpenId(openId);

        return ResultInfo.ok(oauthUserDTO);
    }

    /** 根据用户名获取 **/
    @GetMapping({"/users/username"})
    public ResultInfo<?> username(String username) {
        OauthUserAccessTokenDTO oauthUserAccessTokenDTO = oauthUserService.findUserAccessTokenByUsername(username);
        return ResultInfo.ok(oauthUserAccessTokenDTO);
    }

    /**
     * 新增用户
     **/
    @PostMapping({"/users/add"})
    public ResultInfo<?>
        addUser(@RequestBody @Validated(ValidatorGroup.Create.class) OauthUserAccessTokenDTO oauthUserAccessTokenDTO) {
        ResultInfo<?> result =
            validateUserModify(oauthUserAccessTokenDTO.getAccessToken(), oauthUserAccessTokenDTO.getOpenId());
        if (!result.isSuccess()) {
            return result;
        }
        AccessTokenModel accessTokenModel = (AccessTokenModel)result.getPayload();

        return oauthUserService.addUser(accessTokenModel.clientId, oauthUserAccessTokenDTO);
    }

    /** 修改用户密码 **/
    @PostMapping({"/users/password"})
    public ResultInfo<?> modifyPassword(
        @RequestBody @Validated(ValidatorGroup.Modify.class) OauthUserAccessTokenDTO oauthUserAccessTokenDTO) {
        ResultInfo<?> result =
            validateUserModify(oauthUserAccessTokenDTO.getAccessToken(), oauthUserAccessTokenDTO.getOpenId());
        if (!result.isSuccess()) {
            return result;
        }
        AccessTokenModel accessTokenModel = (AccessTokenModel)result.getPayload();
        oauthUserAccessTokenDTO.setUserId(String.valueOf(accessTokenModel.loginId));

        result = oauthUserService.modifyPassword(oauthUserAccessTokenDTO);
        if (result.isSuccess()) {
            return result;
        }

        // 回收令牌
        SaOAuth2Util.revokeAccessToken(oauthUserAccessTokenDTO.getAccessToken());

        return result;
    }

}
