/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.kong.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthUserDTO;
import com.github.fank243.kong.oauth2.api.domain.enums.Oauth2UserStatusEnum;
import com.github.fank243.kong.oauth2.domain.OauthAccessTokenEntity;
import com.github.fank243.kong.oauth2.domain.OauthClientEntity;
import com.github.fank243.kong.oauth2.domain.OauthUserEntity;
import com.github.fank243.kong.oauth2.repository.IOauthUserRepository;
import com.github.fank243.kong.oauth2.utils.Oauth2Utils;
import com.github.fank243.kong.tool.result.ResultInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import jakarta.annotation.Resource;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthUserService {

    @Resource
    private IOauthUserRepository oauthUserRepository;
    @Resource
    private OauthClientService oauthClientService;
    @Resource
    private OauthAccessTokenService oauthAccessTokenService;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<Long> login(String name, String pwd) {
        OauthUserEntity oauthUserEntity = oauthUserRepository.findByUsername(name);
        if (oauthUserEntity == null) {
            return ResultInfo.err400("用户名或密码错误");
        }
        if (!oauthUserEntity.getPassword().equalsIgnoreCase(SecureUtil.md5(pwd))) {
            return ResultInfo.err400("用户名或密码错误");
        }
        if (oauthUserEntity.getStatus().getCode() == Oauth2UserStatusEnum.DISABLED.getCode()) {
            return ResultInfo.err400("账号已被禁用");
        }
        if (oauthUserEntity.getStatus().getCode() == Oauth2UserStatusEnum.LOGIN_LOCK.getCode()) {
            return ResultInfo.err400("账号已被锁定");
        }

        return ResultInfo.ok(oauthUserEntity.getUserId());
    }

    public OauthUserAccessTokenDTO findUserAccessTokenByUsername(String username) {
        return oauthUserRepository.findAccessTokenByUsername(username);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> addUser(String clientId, OauthUserAccessTokenDTO oauthUserAccessTokenDTO) {
        OauthUserEntity oauthUserEntity = oauthUserRepository.findByUsername(oauthUserAccessTokenDTO.getUsername());
        if (oauthUserEntity != null) {
            OauthAccessTokenEntity oauthAccessTokenEntity =
                oauthAccessTokenService.findByUserId(oauthUserEntity.getUserId());
            // @formatter:off
            OauthUserAccessTokenDTO oauth2UserDTO = OauthUserAccessTokenDTO.builder()
                    .userId(oauthUserEntity.getUserId())
                    .username(oauthUserEntity.getUsername())
                    .nickname(oauthUserEntity.getNickname())
                    .openId(oauthAccessTokenEntity.getOpenId())
                    .build();
            // @formatter:on
            return ResultInfo.error(Oauth2Constants.USER_REPEAT_CODE, "用户已经存在").payload(oauth2UserDTO);
        }
        // 创建用户
        oauthUserEntity = BeanUtil.copyProperties(oauthUserAccessTokenDTO, OauthUserEntity.class);
        oauthUserEntity.setPassword(SecureUtil.md5(oauthUserEntity.getPassword()));
        oauthUserRepository.save(oauthUserEntity);

        OauthClientEntity oauthClientEntity = oauthClientService.findByClientId(clientId);
        // 创建OpenID
        String openId = Oauth2Utils.generateOpenId(oauthClientEntity.getClientId(), oauthUserEntity.getUserId());
        return oauthAccessTokenService.addAccessToken(oauthUserEntity.getUserId(), openId);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> modifyPassword(OauthUserAccessTokenDTO oauthUserAccessTokenDTO) {
        OauthUserEntity oauthUserEntity = BeanUtil.toBean(oauthUserAccessTokenDTO, OauthUserEntity.class);
        oauthUserEntity.setPassword(SecureUtil.md5(oauthUserAccessTokenDTO.getPassword()));

        oauthUserRepository.save(oauthUserEntity);

        return ResultInfo.ok();
    }

    public OauthUserDTO findByUserId(Long userId) {
        OauthUserEntity oauthUser = oauthUserRepository.findByUserId(userId);
        return oauthUser != null ? BeanUtil.copyProperties(oauthUser, OauthUserDTO.class) : null;
    }
}
