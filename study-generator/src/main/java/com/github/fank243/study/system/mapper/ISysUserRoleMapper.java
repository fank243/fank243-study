package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.system.entity.SysUserRoleEntity;

/**
 * 系统用户角色关联表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface ISysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

     /**
     * 根据主键ID查询
     *
     * @param id 主键
     * @return 系统用户角色关联表
     */
     SysUserRoleEntity findById(Long id);

     /**
     * 多条件查询
     *
     * @param sysUserRole 条件参数
     * @return 系统用户角色关联表
     */
     SysUserRoleEntity findByCondition(@Param("sysUserRole") SysUserRoleEntity sysUserRole);

     /**
     * 多条件查询
     *
     * @param sysUserRole 条件参数
     * @return 系统用户角色关联表
     */
     List<SysUserRoleEntity> findListByCondition(@Param("sysUserRole") SysUserRoleEntity sysUserRole);
}
