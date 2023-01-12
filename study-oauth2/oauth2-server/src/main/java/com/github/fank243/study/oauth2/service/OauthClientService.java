package com.github.fank243.study.oauth2.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.oauth2.api.domain.entity.OauthClientEntity;
import com.github.fank243.study.oauth2.mapper.IOauthClientDao;

import jakarta.annotation.Resource;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthClientService extends ServiceImpl<IOauthClientDao, OauthClientEntity> {

    @Resource
    private IOauthClientDao oauthClientDao;

    public OauthClientEntity findByClientId(String clientId) {
        return oauthClientDao
            .selectOne(new LambdaQueryWrapper<OauthClientEntity>().eq(OauthClientEntity::getClientId, clientId));
    }

}
