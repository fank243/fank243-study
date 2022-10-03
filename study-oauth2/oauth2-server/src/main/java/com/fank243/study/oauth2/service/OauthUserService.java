package com.fank243.study.oauth2.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.oauth2.api.domain.dto.OauthUserDTO;
import com.fank243.study.oauth2.api.domain.entity.OauthClientEntity;
import com.fank243.study.oauth2.api.domain.entity.OauthUserEntity;
import com.fank243.study.oauth2.mapper.IOauthUserDao;
import com.fank243.study.oauth2.utils.Oauth2Utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
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
    @Resource
    private OauthClientService oauthClientService;
    @Resource
    private OauthAccessTokenService oauthAccessTokenService;

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> addUser(String clientId, OauthUserDTO oauthUserDTO) {
        QueryWrapper<OauthUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", oauthUserDTO.getUsername());
        Long count = oauthUserDao.selectCount(wrapper);
        if (Convert.toLong(count) > 0) {
            return ResultInfo.fail("用户名已被占用");
        }
        // 创建用户
        OauthUserEntity oauthUserEntity = BeanUtil.copyProperties(oauthUserDTO, OauthUserEntity.class);
        oauthUserEntity.setPassword(SecureUtil.md5(oauthUserEntity.getPassword()));
        boolean isOk = save(oauthUserEntity);
        if (!isOk) {
            return ResultInfo.fail("创建账号失败");
        }
        OauthClientEntity oauthClientEntity = oauthClientService.findByClientId(clientId);

        // 创建OpenID
        String openId = Oauth2Utils.generateOpenID(oauthClientEntity.getClientId(), oauthUserEntity.getUserId());
        return oauthAccessTokenService.addAccessToken(oauthUserEntity.getUserId(), openId);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> modifyPassword(String userId, OauthUserDTO oauthUserDTO) {
        UpdateWrapper<OauthUserEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.set("password", SecureUtil.md5(oauthUserDTO.getPassword()));
        boolean isOk = update(null, wrapper);

        return isOk ? ResultInfo.ok() : ResultInfo.fail("修改密码失败");
    }

}
