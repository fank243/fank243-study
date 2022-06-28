package com.fank243.study.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.oauth2.dao.ISysUserLoginLogDao;
import com.fank243.study.oauth2.domain.SysUserLoginLogEntity;

/**
 * 系统管理员登录日志表 服务类
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Service
public class SysUserLoginLogService extends ServiceImpl<ISysUserLoginLogDao, SysUserLoginLogEntity> {

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
