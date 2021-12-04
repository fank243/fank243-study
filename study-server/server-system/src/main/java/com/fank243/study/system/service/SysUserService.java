package com.fank243.study.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.ds.utils.BeanUtils;
import com.fank243.study.system.dao.ISysUserDao;
import com.fank243.study.system.entity.SysUserEntity;

import cn.hutool.core.bean.BeanUtil;

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
        // TODO FanWeiJie 业务逻辑
        SysUserEntity sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
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
        // TODO FanWeiJie 业务逻辑
        SysUserEntity sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
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
