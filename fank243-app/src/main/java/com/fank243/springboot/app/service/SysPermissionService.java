package com.fank243.springboot.app.service;

import com.fank243.springboot.app.repository.SysPermissionRepository;
import com.fank243.springboot.app.service.base.BaseService;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 权限
 * 
 * @author FanWeiJie
 * @date 2020-03-08 12:21:56
 */
@Service
public class SysPermissionService extends BaseService<SysPermission> {
    @Resource
    private SysPermissionRepository repository;
    @Resource
    private SysUserService sysUserService;

    /**
     * 根据ID查找
     *
     * @param permissionId 权限ID
     * @return Admin
     */
    public Optional<SysPermission> findById(Long permissionId) {
        return repository.findById(permissionId);
    }

    /**
     * 根据管理员ID查找
     * 
     * @param sysUserId 管理员iD
     * @return 权限列表
     */
    public List<SysPermission> findBySysUserId(Long sysUserId) {
        SysUser sysUser = sysUserService.findById(sysUserId);
        List<SysPermission> sysPermissions = new ArrayList<>();
        if (sysUser.getRoles().size() > 0) {
            for (SysRole sysRole : sysUser.getRoles()) {
                if (sysRole.getPermissions().size() > 0) {
                    sysPermissions.addAll(sysRole.getPermissions());
                }
            }
        }
        return sysPermissions;
    }

}
