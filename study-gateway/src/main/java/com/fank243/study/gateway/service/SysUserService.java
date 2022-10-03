package com.fank243.study.gateway.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.domain.enums.UserStatusEnum;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.gateway.mapper.ISysUserMapper;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.domain.entity.SysUserLoginLogEntity;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 系统管理员表 服务类
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@DS("system")
@Service
public class SysUserService extends ServiceImpl<ISysUserMapper, SysUserEntity> {

    @Resource
    private ISysUserMapper sysUserDao;
    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> login(String openId, String clientIp, String userAgent) {
        SysUserEntity sysUser = sysUserDao.findByOpenId(openId);
        if (sysUser == null) {
            return ResultInfo.fail("账号不存在");
        }
        if (UserStatusEnum.DISABLED.getCode() == sysUser.getStatus()) {
            return ResultInfo.fail("账户已被禁用，请联系客服处理");
        }

        // 执行登录流程
        StpUtil.login(sysUser.getUserId(), "PC");

        Date now = new Date();

        // 更新登录信息
        sysUser.setLastLoginTime(now);
        sysUser.setLastLoginIp(clientIp);
        sysUserDao.updateLoginInfoByUserId(sysUser);

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = SysUserLoginLogEntity.builder().userId(sysUser.getUserId())
            .loginTime(now).loginIp(clientIp).loginDevice(userAgent).build();
        sysUserLoginLogService.add(sysUserLoginLog);

        return ResultInfo.ok(sysUser);
    }
}
