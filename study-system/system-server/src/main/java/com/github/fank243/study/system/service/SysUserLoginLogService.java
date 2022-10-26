package com.github.fank243.study.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.system.domain.entity.SysUserLoginLogEntity;
import com.github.fank243.study.system.mapper.ISysUserLoginLogMapper;

/**
 * 系统管理员登录日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
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
