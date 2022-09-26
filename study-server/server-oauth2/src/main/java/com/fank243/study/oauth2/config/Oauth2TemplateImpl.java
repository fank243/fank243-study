package com.fank243.study.oauth2.config;

import javax.annotation.Resource;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import com.fank243.study.oauth2.domain.entity.OauthAccessTokenEntity;
import com.fank243.study.oauth2.domain.entity.OauthClientEntity;
import com.fank243.study.oauth2.service.OauthAccessTokenService;
import com.fank243.study.oauth2.service.OauthClientService;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Template;
import cn.dev33.satoken.oauth2.model.SaClientModel;

/**
 * Sa-Token OAuth2.0 整合实现
 *
 * @author FanWeiJie
 * @since 2021-11-24 16:25:36
 */
@Component
public class Oauth2TemplateImpl extends SaOAuth2Template {

    @Resource
    private OauthClientService oauthClientService;
    @Resource
    private OauthAccessTokenService oauthAccessTokenService;

    /** 根据 id 获取 Client 信息 **/
    @Override
    public SaClientModel getClientModel(String clientId) {
        OauthClientEntity entity = oauthClientService.findByClientId(clientId);
        if (entity == null) {
            return null;
        }
        return new SaClientModel().setClientId(clientId).setClientSecret(entity.getClientSecret()).setAllowUrl("*")
            .setContractScope(entity.getScope());
    }

    /** 根据ClientId 和 LoginId 获取openid **/
    @SneakyThrows
    @Override
    public String getOpenid(String clientId, Object userId) {
        OauthAccessTokenEntity entity = oauthAccessTokenService.getOpenId(userId);
        return entity.getOpenId();
    }

}
