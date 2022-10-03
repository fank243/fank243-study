package com.fank243.study.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.constants.TimeConstant;
import com.fank243.study.gateway.mapper.ISysPermMapper;
import com.fank243.study.system.domain.entity.SysPermEntity;
import com.fank243.study.system.domain.vo.SysPermVO;

/**
 * 系统权限表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@DS("system")
@Service
public class SysPermService extends ServiceImpl<ISysPermMapper, SysPermEntity> {

    @Resource
    private ISysPermMapper sysPermDao;

    @Cached(name = "gateway:perm:user:", key = "#userId", expire = TimeConstant.HOUR_1)
    @CacheRefresh(refresh = TimeConstant.MINUTE_5, stopRefreshAfterLastAccess = TimeConstant.HOUR_1)
    public List<SysPermVO> findByUserId(String userId) {
        return sysPermDao.findByUserId(userId);
    }

    @Cached(name = "gateway:perm:type:", expire = TimeConstant.DAY_1)
    @CacheRefresh(refresh = TimeConstant.HOUR_1, stopRefreshAfterLastAccess = TimeConstant.DAY_1)
    public List<SysPermVO> findByPermTypes(List<Integer> permTypes) {
        return sysPermDao.findByPermTypes(permTypes);
    }
}
