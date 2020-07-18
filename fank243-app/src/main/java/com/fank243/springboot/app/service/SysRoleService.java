package com.fank243.springboot.app.service;

import com.fank243.springboot.app.repository.SysRoleRepository;
import com.fank243.springboot.app.service.base.BaseService;
import com.fank243.springboot.core.entity.SysRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 角色
 * 
 * @author FanWeiJie
 * @date 2020-03-08 12:21:56
 */
@Service
public class SysRoleService extends BaseService<SysRole> {
    @Resource
    private SysRoleRepository repository;
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 根据ID查找
     * 
     * @param roleId 角色ID
     * @return Role
     */
    public Optional<SysRole> findById(Long roleId) {
        return repository.findById(roleId);
    }

}
