package com.fank243.study.system.service;

import java.util.Date;

import javax.annotation.Resource;

import com.fank243.study.system.domain.vo.SysUserLoginVO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.domain.enums.UserStatusEnum;
import com.fank243.study.common.core.domain.model.PageBean;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.common.core.utils.BeanUtils;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.system.domain.dto.SysUserDTO;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.domain.entity.SysUserLoginLogEntity;
import com.fank243.study.system.domain.vo.SysUserLoginResp;
import com.fank243.study.system.domain.vo.SysUserVO;
import com.fank243.study.system.mapper.ISysUserMapper;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;

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
        return sysUserDao.updateById(sysUserEntity) > 0;
    }

    /**
     * 根据用户名查找
     *
     * @param username 请求参数
     * @return 操作结果
     */
    public SysUserEntity findByUsername(String username) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserDao.selectOne(wrapper);
    }

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

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = SysUserLoginLogEntity.builder().userId(sysUser.getUserId())
            .loginTime(new Date()).loginIp(clientIp).loginDevice(userAgent).build();
        sysUserLoginLogService.add(sysUserLoginLog);

        return ResultInfo.ok(sysUser);
    }
}
