package com.fank243.springboot.app.repository;

import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.enums.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:08:02
 */
@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

    List<SysPermission> findByPidAndAvailableIsTrue(long pid);

    List<SysPermission> findByIdIn(List<Long> ids);

    /**
     * 查找所有类型为菜单的权限
     * 
     * @param type {@link PermissionType}
     * @return 菜单权限
     */
    List<SysPermission> findByTypeAndAvailableTrue(PermissionType type);

    /**
     * 查找所有可用权限
     * 
     * @return 权限
     */
    List<SysPermission> findByAvailableTrue();
}
