package com.github.fank243.study.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.oauth2.api.domain.entity.OauthAccessTokenEntity;
import com.github.fank243.study.oauth2.mapper.IOauthAccessTokenDao;

import jakarta.annotation.Resource;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthAccessTokenService extends ServiceImpl<IOauthAccessTokenDao, OauthAccessTokenEntity> {

    @Resource
    private IOauthAccessTokenDao oauthAccessTokenDao;

    public OauthAccessTokenEntity findByUserId(String userId) {
        return oauthAccessTokenDao
            .selectOne(new LambdaQueryWrapper<OauthAccessTokenEntity>().eq(OauthAccessTokenEntity::getUserId, userId));
    }

    public OauthAccessTokenEntity findByOpenId(String openId) {
        return oauthAccessTokenDao
            .selectOne(new LambdaQueryWrapper<OauthAccessTokenEntity>().eq(OauthAccessTokenEntity::getOpenId, openId));

    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> addAccessToken(String userId, String openId) {
        OauthAccessTokenEntity oauthAccessTokenEntity = new OauthAccessTokenEntity();
        oauthAccessTokenEntity.setUserId(userId);
        oauthAccessTokenEntity.setOpenId(openId);
        boolean isOk = save(oauthAccessTokenEntity);

        return isOk ? ResultInfo.ok(openId) : ResultInfo.err500("分配OpenID失败");
    }
}
