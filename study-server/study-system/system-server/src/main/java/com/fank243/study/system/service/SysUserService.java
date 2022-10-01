package com.fank243.study.system.service;

import java.util.Date;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fank243.study.common.core.utils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.system.domain.dto.SysUserDTO;
import com.fank243.study.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.system.domain.vo.SysUserLoginResp;
import com.fank243.study.system.domain.vo.SysUserVO;
import com.fank243.study.common.core.domain.model.PageBean;
import com.fank243.study.common.core.exception.AuthException;
import com.fank243.study.common.core.domain.enums.UserStatusEnum;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.system.mapper.ISysUserMapper;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.domain.entity.SysUserLoginLogEntity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 系统管理员表 服务类
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Service
public class SysUserService extends ServiceImpl<ISysUserMapper, SysUserEntity> {

    @Resource
    private ISysUserMapper sysUserDao;
    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    /**
     * 系统管理员表_分页
     *
     * @param sysUser 查询条件
     * @return 列表
     */
    public PageBean<SysUserVO> page(SysUserDTO sysUser) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        IPage<SysUserEntity> page =
            sysUserDao.selectPage(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), wrapper);
        return BeanUtils.convert(page, SysUserVO.class);
    }

    /**
     * 系统管理员表_新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = sysUserDao
            .selectOne(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, sysUser.getUsername()));
        if (sysUserEntity != null) {
            throw new BizException("用户名已存在");
        }

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
        sysUserEntity.setPassword(SecureUtil.md5(sysUser.getPassword()));
        return save(sysUserEntity);
    }

    /**
     * 系统管理员表_修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = sysUserDao.selectById(sysUser.getUserId());
        if (sysUserEntity == null) {
            throw new BizException("用户不存在");
        }

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
        sysUserEntity.setPassword(SecureUtil.md5(sysUser.getPassword()));
        return sysUserDao.updateById(sysUserEntity) > 0;
    }

    /**
     * 根据用户名查找
     *
     * @param username 请求参数
     * @return 操作结果
     */
    public SysUserEntity findByUsername(String username) throws BizException {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserDao.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public String login(SysUserLoginDTO loginReq, String clientIp, String userAgent) throws AuthException {
        SysUserEntity sysUser = sysUserDao.findSysUserByUsername(loginReq.getUsername());
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
        if (!sysUser.getPassword().equalsIgnoreCase(SecureUtil.md5(loginReq.getPassword()))) {
            if (sysUser.getLoginErrCount() + 1 >= 3) {
                sysUserDao.lockLoginErr(sysUser.getUserId(), now, UserStatusEnum.LOGIN_LOCK.getCode());
            } else {
                sysUserDao.lockLoginErr(sysUser.getUserId(), null, sysUser.getStatus());
            }
            throw new AuthException("用户名或密码错误");
        }

        // 解锁登录错误信息
        sysUser.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUser.setLastLoginTime(now);
        sysUser.setLastLoginIp(clientIp);
        sysUserDao.unLockLoginErr(sysUser);

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = SysUserLoginLogEntity.builder().userId(sysUser.getUserId())
            .loginTime(new Date()).loginIp(clientIp).loginDevice(userAgent).build();
        sysUserLoginLogService.add(sysUserLoginLog);

        SysUserLoginResp sysUserLoginResp = new SysUserLoginResp();
        sysUserLoginResp.setUserId(sysUser.getUserId());
        return sysUser.getUserId();
    }
}
