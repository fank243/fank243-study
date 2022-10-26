package com.github.fank243.study.oauth2.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.oauth2.api.domain.entity.OauthClientEntity;
import com.github.fank243.study.oauth2.mapper.IOauthClientDao;

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
        QueryWrapper<OauthClientEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("client_id", clientId);
        return oauthClientDao.selectOne(wrapper);
    }

}
