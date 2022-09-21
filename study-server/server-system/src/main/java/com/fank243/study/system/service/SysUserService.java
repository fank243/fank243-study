package com.fank243.study.system.service;

import javax.annotation.Resource;

import com.fank243.study.api.domain.PageBean;
import com.fank243.study.api.utils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.system.dao.ISysUserDao;
import com.fank243.study.system.entity.SysUserEntity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 系统管理员表 服务类
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Service
public class SysUserService extends ServiceImpl<ISysUserDao, SysUserEntity> {

    @Resource
    private ISysUserDao sysUserDao;

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

}
