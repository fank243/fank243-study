package com.github.fank243.kong.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.system.dto.SysPermDTO;

/**
* 系统权限表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:11
*/
public interface ISysPermMapper extends BaseMapper<SysPermEntity> {

    /**
    * 根据主键ID查询
    *
    * @param permId 主键
    * @return 系统权限表
    */
    SysPermDTO findByPermId(Long permId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统权限表
    */
    SysPermDTO findByCondition(@Param("params") SysPermDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统权限表
    */
    List<SysPermDTO> findListByCondition(@Param("params") SysPermDTO params);
}
