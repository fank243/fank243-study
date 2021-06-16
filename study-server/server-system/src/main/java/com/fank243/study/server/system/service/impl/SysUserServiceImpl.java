package com.fank243.study.server.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.server.system.dao.ISysUserDao;
import com.fank243.study.server.system.domain.entity.SysUserEntity;
import com.fank243.study.server.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:11:16
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<ISysUserDao, SysUserEntity> implements ISysUserService {

    @Resource
    private ISysUserDao sysUserDao;

    @Override
    public boolean save(SysUserDTO sysUser) {
        return false;
    }

    @Override
    public boolean updateById(SysUserDTO sysUser) {
        return false;
    }

    @Override
    public SysUserVO findById(String id) {
        return BeanUtil.toBean(sysUserDao.selectById(id), SysUserVO.class);
    }

    @Override
    public SysUserVO findByCondition(SysUserDTO sysUser) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotEmpty(sysUser.getUsername()), "username", sysUser.getUsername());
        SysUserEntity sysUserEntity = sysUserDao.selectOne(wrapper);
        return BeanUtil.toBean(sysUserEntity, SysUserVO.class);
    }

    @Override
    public List<SysUserVO> findListByCondition(SysUserDTO sysUser) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotEmpty(sysUser.getUsername()), "username", sysUser.getUsername());
        List<SysUserEntity> sysUserEntityList = sysUserDao.selectList(wrapper);
        return BeanUtil.copyToList(sysUserEntityList, SysUserVO.class);
    }

    @Override
    public PageBean<SysUserVO> pageOfUser(SysUserDTO sysUser) {
        IPage<SysUserEntity> page =
            sysUserDao.selectPage(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), null);
        return convert(page, SysUserVO.class);
    }
}
