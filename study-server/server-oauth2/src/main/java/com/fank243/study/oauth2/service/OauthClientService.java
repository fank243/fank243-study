package com.fank243.study.oauth2.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.enums.UserStatusEnum;
import com.fank243.study.core.exception.AuthException;
import com.fank243.study.oauth2.dao.IOauthClientDao;
import com.fank243.study.oauth2.domain.OauthClientEntity;
import com.fank243.study.oauth2.domain.SysUserEntity;
import com.fank243.study.oauth2.domain.SysUserLoginLogEntity;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;

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
    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    public OauthClientEntity findByClientId(String clientId) {
        QueryWrapper<OauthClientEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("client_id", clientId);
        return oauthClientDao.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public String login(String name, String pwd, String clientIp, String userAgent) throws AuthException {
        SysUserEntity sysUser = oauthClientDao.findSysUserByUsername(name);
        if (sysUser == null) {
            throw new AuthException("用户名或密码错误");
        }
        if (UserStatusEnum.DISABLED.getCode() == sysUser.getStatus()) {
            throw new AuthException("账户已被禁用，请联系客服处理");
        }
        Date now = new Date();
        if (UserStatusEnum.LOGIN_LOCK.getCode() == sysUser.getStatus()
            && DateUtil.offsetMinute(sysUser.getLoginLockTime(), 30).isAfter(now)) {
            throw new AuthException("账户已被锁定30分钟，请稍后再试");
        }
        if (!sysUser.getPassword().equalsIgnoreCase(SecureUtil.md5(pwd))) {
            if (sysUser.getLoginErrCount() + 1 >= 3) {
                oauthClientDao.lockLoginErr(sysUser.getUserId(), now, UserStatusEnum.LOGIN_LOCK.getCode());
            } else {
                oauthClientDao.lockLoginErr(sysUser.getUserId(), null, sysUser.getStatus());
            }
            throw new AuthException("用户名或密码错误");
        }

        // 解锁登录错误信息
        sysUser.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUser.setLastLoginTime(now);
        sysUser.setLastLoginIp(clientIp);
        oauthClientDao.unLockLoginErr(sysUser);

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = SysUserLoginLogEntity.builder().userId(sysUser.getUserId())
            .loginTime(new Date()).loginIp(clientIp).loginDevice(userAgent).build();
        sysUserLoginLogService.add(sysUserLoginLog);

        return sysUser.getUserId();
    }

}
