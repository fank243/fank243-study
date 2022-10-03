package com.fank243.study.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.constants.TimeConstant;
import com.fank243.study.gateway.mapper.ISysRoleMapper;
import com.fank243.study.system.domain.entity.SysRoleEntity;
import com.fank243.study.system.domain.vo.SysRoleVO;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@DS("system")
@Service
public class SysRoleService extends ServiceImpl<ISysRoleMapper, SysRoleEntity> {

    @Resource
    private ISysRoleMapper sysRoleDao;

    @Cached(name = "gateway:role:user:", key = "#userId", expire = TimeConstant.HOUR_1)
    @CacheRefresh(refresh = TimeConstant.MINUTE_5, stopRefreshAfterLastAccess = TimeConstant.HOUR_1)
    public List<SysRoleVO> findByUserId(String userId) {
        return sysRoleDao.findByUserId(userId);
    }
}
