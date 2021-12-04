package com.fank243.study.oauth2.service;

import javax.annotation.Resource;
import javax.annotation.WillClose;

import cn.dev33.satoken.util.SaFoxUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fank243.study.core.exception.BizException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.oauth2.dao.IOauthAccessTokenDao;
import com.fank243.study.oauth2.domain.OauthAccessTokenEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理员角色关联表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthAccessTokenService extends ServiceImpl<IOauthAccessTokenDao, OauthAccessTokenEntity> {

    @Resource
    private IOauthAccessTokenDao oauthAccessTokenDao;

    @Transactional(rollbackFor = Exception.class)
    public OauthAccessTokenEntity getOpenId(Object userId) throws BizException {
        QueryWrapper<OauthAccessTokenEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        OauthAccessTokenEntity entity = oauthAccessTokenDao.selectOne(wrapper);

        String openId = "st_" + SaFoxUtil.getRandomString(18) + "_" + SaFoxUtil.getRandomString(36);
        if (entity == null) {
            entity = new OauthAccessTokenEntity();
            entity.setUserId((String)userId);
            entity.setOpenId(openId);

            int rows = oauthAccessTokenDao.insert(entity);
            if (rows <= 0) {
                throw new BizException("获取用户OpenID失败");
            }
        }
        return entity;
    }

    public static void main(String[] args) {
        String s = RandomUtil.randomString(16);
        System.out.println(s);
        String openId = "st_" + SaFoxUtil.getRandomString(14) + "_" + SaFoxUtil.getRandomString(16) + "__";
        System.out.println(openId);
    }
}
