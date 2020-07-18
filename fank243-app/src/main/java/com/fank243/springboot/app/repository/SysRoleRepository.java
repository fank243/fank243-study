package com.fank243.springboot.app.repository;

import com.fank243.springboot.core.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色持久层
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:08:02
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    List<SysRole> findByAvailableIsTrue();

    List<SysRole> findByIdIn(List<Long> roleIds);

    @Query(
        value = "select group_concat(description) from tb_sys_role a left join tb_sys_user_role tsur on a.id = tsur.role_id where tsur.sys_user_id =:sysUserId",
        nativeQuery = true)
    String findByUserId(Long sysUserId);

    @Transactional
    @Modifying
    @Query(value = "update SysRole set available =:available where id =:roleId")
    int updateAvailableById(Long roleId, boolean available);
}
