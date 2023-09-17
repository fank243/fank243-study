package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.system.entity.SysPermEntity;

/**
 * 系统权限表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface ISysPermMapper extends BaseMapper<SysPermEntity> {

     /**
     * 根据主键ID查询
     *
     * @param permId 主键
     * @return 系统权限表
     */
     SysPermEntity findByPermId(Long permId);

     /**
     * 多条件查询
     *
     * @param sysPerm 条件参数
     * @return 系统权限表
     */
     SysPermEntity findByCondition(@Param("sysPerm") SysPermEntity sysPerm);

     /**
     * 多条件查询
     *
     * @param sysPerm 条件参数
     * @return 系统权限表
     */
     List<SysPermEntity> findListByCondition(@Param("sysPerm") SysPermEntity sysPerm);
}
