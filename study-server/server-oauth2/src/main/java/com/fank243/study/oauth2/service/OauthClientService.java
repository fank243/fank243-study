package com.fank243.study.oauth2.service;

import javax.annotation.Resource;

import com.fank243.study.core.exception.AuthException;
import com.fank243.study.oauth2.domain.SysUserEntity;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.oauth2.dao.IOauthClientDao;
import com.fank243.study.oauth2.domain.OauthClientEntity;

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

    public String login(String name, String pwd) throws AuthException {
        SysUserEntity sysUser = oauthClientDao.findSysUserByUsername(name);
        if (sysUser == null) {
            throw new AuthException("用户名或密码错误");
        }
        if (!sysUser.getPassword().equalsIgnoreCase(pwd)) {
            throw new AuthException("用户名或密码错误");
        }
        return sysUser.getId();
    }
}
