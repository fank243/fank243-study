package com.fank243.study.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fank243.study.gateway.dao.ISysPermissionDao;
import com.fank243.study.gateway.domain.SysPermVO;

/**
 * 系统权限
 * 
 * @author FanWeiJie
 * @since 2022-05-11 14:03:17
 */
@Service
public class SysPermissionService {

    @Resource
    private ISysPermissionDao sysPermissionDao;

    @Cacheable(value = "perm:user", key = "#userId")
    public List<SysPermVO> findByUserId(String userId) {
        return sysPermissionDao.findByUserId(userId);
    }

    @Cacheable(value = "perm:perm-type",  key = "#permTypes")
    public List<SysPermVO> findByPermTypes(List<Integer> permTypes) {
        return sysPermissionDao.findByPermTypes(permTypes);
    }
}
