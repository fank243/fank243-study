package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.system.entity.SysRolePermEntity;

/**
 * 系统角色权限关联表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
public interface ISysRolePermMapper extends BaseMapper<SysRolePermEntity> {

     /**
     * 根据主键ID查询
     *
     * @param id 主键
     * @return 系统角色权限关联表
     */
     SysRolePermEntity findById(Long id);

     /**
     * 多条件查询
     *
     * @param sysRolePerm 条件参数
     * @return 系统角色权限关联表
     */
     SysRolePermEntity findByCondition(@Param("sysRolePerm") SysRolePermEntity sysRolePerm);

     /**
     * 多条件查询
     *
     * @param sysRolePerm 条件参数
     * @return 系统角色权限关联表
     */
     List<SysRolePermEntity> findListByCondition(@Param("sysRolePerm") SysRolePermEntity sysRolePerm);
}
