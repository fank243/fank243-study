package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.system.entity.SysUserLoginLogEntity;

/**
 * 系统管理员登录日志表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
public interface ISysUserLoginLogMapper extends BaseMapper<SysUserLoginLogEntity> {

     /**
     * 根据主键ID查询
     *
     * @param id 主键
     * @return 系统管理员登录日志表
     */
     SysUserLoginLogEntity findById(Long id);

     /**
     * 多条件查询
     *
     * @param sysUserLoginLog 条件参数
     * @return 系统管理员登录日志表
     */
     SysUserLoginLogEntity findByCondition(@Param("sysUserLoginLog") SysUserLoginLogEntity sysUserLoginLog);

     /**
     * 多条件查询
     *
     * @param sysUserLoginLog 条件参数
     * @return 系统管理员登录日志表
     */
     List<SysUserLoginLogEntity> findListByCondition(@Param("sysUserLoginLog") SysUserLoginLogEntity sysUserLoginLog);
}
