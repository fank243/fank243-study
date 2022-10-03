package com.fank243.study.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.gateway.mapper.ISysUserLoginLogMapper;
import com.fank243.study.system.domain.entity.SysUserLoginLogEntity;

/**
 * 系统管理员登录日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@DS("system")
@Service
public class SysUserLoginLogService extends ServiceImpl<ISysUserLoginLogMapper, SysUserLoginLogEntity> {

    /**
     * 系统管理员登录日志表_新增
     *
     * @param sysUserLoginLog 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysUserLoginLogEntity sysUserLoginLog) {
        return save(sysUserLoginLog);
    }

}
