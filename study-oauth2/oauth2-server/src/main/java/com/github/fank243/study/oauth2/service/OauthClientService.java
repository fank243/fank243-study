package com.github.fank243.study.oauth2.service;

import org.springframework.stereotype.Service;

import com.github.fank243.study.oauth2.api.domain.entity.OauthClientEntity;
import com.github.fank243.study.oauth2.mapper.IOauthClientMapper;
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
public class OauthClientService extends ServiceImpl<IOauthClientMapper, OauthClientEntity> {

    @Resource
    private IOauthClientMapper oauthClientDao;

    public OauthClientEntity findByClientId(String clientId) {
        OauthClientEntity entity = OauthClientEntity.builder().clientId(clientId).build();
        return oauthClientDao.selectOneByQuery(QueryWrapper.create(entity));
    }

}
