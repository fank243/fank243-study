package com.github.fank243.kong.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.system.dto.SysRolePermDTO;

/**
* 系统角色权限关联表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface ISysRolePermMapper extends BaseMapper<SysRolePermEntity> {

    /**
    * 根据主键ID查询
    *
    * @param id 主键
    * @return 系统角色权限关联表
    */
    SysRolePermDTO findById(Long id);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统角色权限关联表
    */
    SysRolePermDTO findByCondition(@Param("params") SysRolePermDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统角色权限关联表
    */
    List<SysRolePermDTO> findListByCondition(@Param("params") SysRolePermDTO params);
}
