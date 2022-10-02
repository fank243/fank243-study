package com.fank243.study.oauth2.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.oauth2.api.domain.entity.OauthAccessTokenEntity;
import com.fank243.study.oauth2.mapper.IOauthAccessTokenDao;

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

    public OauthAccessTokenEntity findByUserId(Object userId) {
        QueryWrapper<OauthAccessTokenEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return oauthAccessTokenDao.selectOne(queryWrapper);
    }
}
