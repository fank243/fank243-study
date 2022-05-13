package com.fank243.study.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import com.fank243.study.core.config.CacheKeyGenerator;
import com.fank243.study.gateway.dao.SysPermissionDao;
import com.fank243.study.gateway.entity.SysPermissionEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fank243.study.gateway.dao.SysRoleDao;
import com.fank243.study.gateway.entity.SysRoleEntity;

/**
 * 系统权限
 * 
 * @author FanWeiJie
 * @since 2022-05-11 14:03:17
 */
@Service
public class SysPermissionService {

    @Resource
    private SysPermissionDao sysPermissionDao;

    @Cacheable(value = "perm:user", key = "#userId")
    public List<SysPermissionEntity> findByUserId(String userId) {
        return sysPermissionDao.findByUserId(userId);
    }

    @Cacheable(value = "perm:all", keyGenerator = "cacheKeyGenerator")
    public List<SysPermissionEntity> findAll() {
        return sysPermissionDao.findAll();
    }
}
