package com.fank243.springboot.app.service;

import com.fank243.springboot.app.repository.SysUserRepository;
import com.fank243.springboot.app.service.base.BaseService;
import com.fank243.springboot.core.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-03-08 12:21:56
 */
@Service
public class SysUserService extends BaseService<SysUser> {
    @Resource
    private SysUserRepository repository;

    /**
     * 根据id查找
     *
     * @param sysUserId 管理员ID
     * @return Admin
     */
    public SysUser findById(Long sysUserId) {
        return repository.findByIdAndIsDeletedFalse(sysUserId);
    }
}
