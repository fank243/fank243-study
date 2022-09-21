package com.fank243.study.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.fank243.study.common.constants.TimeConstant;
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

    @Cached(name = "gateway:perm:user:", key = "#userId", expire = TimeConstant.HOUR_1)
    @CacheRefresh(refresh = TimeConstant.MINUTE_5, stopRefreshAfterLastAccess = TimeConstant.HOUR_1)
    public List<SysPermVO> findByUserId(String userId) {
        return sysPermissionDao.findByUserId(userId);
    }

    @Cached(name = "gateway:perm:type:", expire = TimeConstant.DAY_1)
    @CacheRefresh(refresh = TimeConstant.HOUR_1, stopRefreshAfterLastAccess = TimeConstant.DAY_1)
    public List<SysPermVO> findByPermTypes(List<Integer> permTypes) {
        return sysPermissionDao.findByPermTypes(permTypes);
    }
}
