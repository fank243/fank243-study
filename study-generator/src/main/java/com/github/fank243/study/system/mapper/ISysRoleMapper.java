package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.system.entity.SysRoleEntity;

/**
 * 系统角色表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
public interface ISysRoleMapper extends BaseMapper<SysRoleEntity> {

     /**
     * 根据主键ID查询
     *
     * @param roleId 主键
     * @return 系统角色表
     */
     SysRoleEntity findByRoleId(Long roleId);

     /**
     * 多条件查询
     *
     * @param sysRole 条件参数
     * @return 系统角色表
     */
     SysRoleEntity findByCondition(@Param("sysRole") SysRoleEntity sysRole);

     /**
     * 多条件查询
     *
     * @param sysRole 条件参数
     * @return 系统角色表
     */
     List<SysRoleEntity> findListByCondition(@Param("sysRole") SysRoleEntity sysRole);
}
