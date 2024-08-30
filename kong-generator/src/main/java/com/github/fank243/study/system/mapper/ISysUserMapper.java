package com.github.fank243.kong.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.system.dto.SysUserDTO;

/**
* 系统管理员表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:09
*/
public interface ISysUserMapper extends BaseMapper<SysUserEntity> {

    /**
    * 根据主键ID查询
    *
    * @param userId 主键
    * @return 系统管理员表
    */
    SysUserDTO findByUserId(Long userId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统管理员表
    */
    SysUserDTO findByCondition(@Param("params") SysUserDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 系统管理员表
    */
    List<SysUserDTO> findListByCondition(@Param("params") SysUserDTO params);
}
