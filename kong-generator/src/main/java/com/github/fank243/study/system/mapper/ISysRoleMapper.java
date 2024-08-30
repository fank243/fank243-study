package com.github.fank243.kong.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.system.dto.SysRoleDTO;

/**
* 系统角色表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface ISysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
    * 根据主键ID查询
    *
    * @param roleId 主键
    * @return 系统角色表
    */
    SysRoleDTO findByRoleId(Long roleId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统角色表
    */
    SysRoleDTO findByCondition(@Param("params") SysRoleDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统角色表
    */
    List<SysRoleDTO> findListByCondition(@Param("params") SysRoleDTO params);
}
