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

package com.github.fank243.study.oauth2.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.study.oauth2.domain.OauthAccessTokenEntity;
import com.github.fank243.study.oauth2.mapper.IOauthAccessTokenMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import jakarta.annotation.Resource;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthAccessTokenService extends ServiceImpl<IOauthAccessTokenMapper, OauthAccessTokenEntity> {

    @Resource
    private IOauthAccessTokenMapper oauthAccessTokenDao;

    public OauthAccessTokenEntity findByUserId(String userId) {
        OauthAccessTokenEntity entity = OauthAccessTokenEntity.builder().userId(userId).build();
        return oauthAccessTokenDao.selectOneByQuery(QueryWrapper.create(entity));
    }

    public OauthAccessTokenEntity findByOpenId(String openId) {
        OauthAccessTokenEntity entity = OauthAccessTokenEntity.builder().openId(openId).build();
        return oauthAccessTokenDao.selectOneByQuery(QueryWrapper.create(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> addAccessToken(String userId, String openId) {
        OauthAccessTokenEntity oauthAccessTokenEntity =
            OauthAccessTokenEntity.builder().userId(userId).openId(openId).build();
        return save(oauthAccessTokenEntity) ? ResultInfo.ok(Map.of("openId", openId)) : ResultInfo.err500("分配OpenID失败");
    }
}
