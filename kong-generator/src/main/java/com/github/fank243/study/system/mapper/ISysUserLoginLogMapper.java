package com.github.fank243.kong.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.system.dto.SysUserLoginLogDTO;

/**
* 系统管理员登录日志表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:09
*/
public interface ISysUserLoginLogMapper extends BaseMapper<SysUserLoginLogEntity> {

    /**
    * 根据主键ID查询
    *
    * @param id 主键
    * @return 系统管理员登录日志表
    */
    SysUserLoginLogDTO findById(Long id);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统管理员登录日志表
    */
    SysUserLoginLogDTO findByCondition(@Param("params") SysUserLoginLogDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统管理员登录日志表
    */
    List<SysUserLoginLogDTO> findListByCondition(@Param("params") SysUserLoginLogDTO params);
}
