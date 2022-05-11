package com.fank243.study.gateway.dao;

import java.util.List;

import com.fank243.study.gateway.entity.SysPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fank243.study.gateway.entity.SysRoleEntity;

/**
 * 系统权限
 * 
 * @author FanWeiJie
 * @since 2022-05-11 14:03:57
 */
@Mapper
public interface SysPermissionDao {

    /**
     * 根据userId查询权限集合
     * 
     * @param userId 管理员ID
     * @return 权限集合
     */
    @Select("""
        SELECT a.* FROM `tb_sys_permission` a LEFT JOIN tb_sys_role_permission b ON a.id=b.perm_id LEFT JOIN tb_sys_user_role c ON b.role_id=c.role_id
        WHERE c.user_id= #{userId}
        """)
    List<SysPermissionEntity> findByUserId(String userId);

    /**
     * 查询所有权限
     * 
     * @return 权限集合
     */
    @Select("select * from tb_sys_permission")
    List<SysPermissionEntity> findAll();
}
