package com.github.fank243.study.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.enums.EnvEnum;
import com.github.fank243.study.core.service.RedisService;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserDTO;
import com.github.fank243.study.oauth2.api.domain.entity.OauthClientEntity;
import com.github.fank243.study.oauth2.api.domain.entity.OauthUserEntity;
import com.github.fank243.study.oauth2.mapper.IOauthUserDao;
import com.github.fank243.study.oauth2.utils.Oauth2Utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.spring.SpringUtil;
import jakarta.annotation.Resource;

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
    @Resource
    private RedisService redisService;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<String> login(String name, String pwd, String imgCode, String randomStr) {
        if (StrUtil.isBlank(imgCode) || StrUtil.isBlank(randomStr)) {
            return ResultInfo.err400("请输入图形验证码");
        }
        if (EnvEnum.PROD.name().equalsIgnoreCase(SpringUtil.getActiveProfile())) {
            String key = CacheConstants.IMG_CODE_KEY + randomStr;
            Object imgCodeObj = redisService.getObj(key);
            redisService.delete(key);
            if (ObjectUtil.isEmpty(imgCodeObj) || !imgCode.equalsIgnoreCase(String.valueOf(imgCodeObj))) {
                return ResultInfo.err400("图形验证码不正确");
            }
        }

        OauthUserEntity oauthUserEntity =
            oauthUserDao.selectOne(new LambdaQueryWrapper<OauthUserEntity>().eq(OauthUserEntity::getUsername, name));
        if (oauthUserEntity == null) {
            return ResultInfo.err400("用户名或密码错误");
        }
        if (!oauthUserEntity.getPassword().equalsIgnoreCase(SecureUtil.md5(pwd))) {
            return ResultInfo.err400("用户名或密码错误");
        }
        return ResultInfo.ok(oauthUserEntity.getUserId());
    }

    public OauthUserEntity findByUserId(String userId) {
        return oauthUserDao.selectOne(new LambdaQueryWrapper<OauthUserEntity>().eq(OauthUserEntity::getUserId, userId));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> addUser(String clientId, OauthUserDTO oauthUserDTO) {
        Long count = oauthUserDao.selectCount(
            new LambdaQueryWrapper<OauthUserEntity>().eq(OauthUserEntity::getUsername, oauthUserDTO.getUsername()));
        if (Convert.toLong(count) > 0) {
            return ResultInfo.err400("用户名已被占用");
        }
        // 创建用户
        OauthUserEntity oauthUserEntity = BeanUtil.copyProperties(oauthUserDTO, OauthUserEntity.class);
        oauthUserEntity.setPassword(SecureUtil.md5(oauthUserEntity.getPassword()));
        boolean isOk = save(oauthUserEntity);
        if (!isOk) {
            return ResultInfo.err400("创建账号失败");
        }
        OauthClientEntity oauthClientEntity = oauthClientService.findByClientId(clientId);

        // 创建OpenID
        String openId = Oauth2Utils.generateOpenId(oauthClientEntity.getClientId(), oauthUserEntity.getUserId());
        return oauthAccessTokenService.addAccessToken(oauthUserEntity.getUserId(), openId);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> modifyPassword(String userId, OauthUserDTO oauthUserDTO) {
        LambdaUpdateWrapper<OauthUserEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(OauthUserEntity::getPassword, SecureUtil.md5(oauthUserDTO.getPassword()))
            .eq(OauthUserEntity::getUserId, userId);

        boolean isOk = update(null, wrapper);

        return isOk ? ResultInfo.ok() : ResultInfo.err500("修改密码失败");
    }

}
