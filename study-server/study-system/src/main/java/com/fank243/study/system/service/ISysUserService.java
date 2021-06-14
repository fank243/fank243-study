package com.fank243.study.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fank243.study.client.context.system.SysUserContext;
import com.fank243.study.system.dao.SysUserDao;
import com.fank243.study.system.model.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-14 00:01:40
 */
@Service
public class ISysUserService {

    @Resource
    private SysUserDao sysUserDao;

    public List<SysUserContext> findAll() {
        List<SysUserEntity> sysUserList = sysUserDao.selectList(null);
        return BeanUtil.copyToList(sysUserList, SysUserContext.class, CopyOptions.create());
    }
}
