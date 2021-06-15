package com.fank243.study.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.client.domain.PageBean;
import com.fank243.study.client.domain.system.dto.SysUserDTO;
import com.fank243.study.client.domain.system.vo.SysUserVO;
import com.fank243.study.system.dao.ISysUserDao;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:11:16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserDao, SysUserEntity> implements ISysUserService {

    @Resource
    private ISysUserDao sysUserDao;

    @Override
    public PageBean<SysUserVO> pageOfUser(SysUserDTO sysUser) {
        IPage<SysUserEntity> page =
            sysUserDao.selectPage(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), null);
        return convert(page, SysUserVO.class);
    }
}
