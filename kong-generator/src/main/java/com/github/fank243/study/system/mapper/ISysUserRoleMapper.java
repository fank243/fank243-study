package com.github.fank243.kong.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.system.dto.SysUserRoleDTO;

/**
* 系统用户角色关联表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface ISysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    /**
    * 根据主键ID查询
    *
    * @param id 主键
    * @return 系统用户角色关联表
    */
    SysUserRoleDTO findById(Long id);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统用户角色关联表
    */
    SysUserRoleDTO findByCondition(@Param("params") SysUserRoleDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统用户角色关联表
    */
    List<SysUserRoleDTO> findListByCondition(@Param("params") SysUserRoleDTO params);
}
