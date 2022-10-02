package com.fank243.study.oauth2.service;

import javax.annotation.Resource;

import com.fank243.study.oauth2.api.domain.vo.OauthUserVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.oauth2.api.domain.entity.OauthUserEntity;
import com.fank243.study.oauth2.mapper.IOauthUserDao;

import cn.hutool.crypto.SecureUtil;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthUserService extends ServiceImpl<IOauthUserDao, OauthUserEntity> {

    @Resource
    private IOauthUserDao oauthUserDao;

    public String login(String name, String pwd) throws AuthException {
        OauthUserEntity oauthUserEntity = oauthUserDao.findSysUserByUsername(name);
        if (oauthUserEntity == null) {
            throw new AuthException("用户名或密码错误");
        }
        if (!oauthUserEntity.getPassword().equalsIgnoreCase(SecureUtil.md5(pwd))) {
            throw new AuthException("用户名或密码错误");
        }
        return oauthUserEntity.getUserId();
    }

    public OauthUserEntity findByUserId(String userId) {
        return oauthUserDao.findByUserId(userId);
    }
}
