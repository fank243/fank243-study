package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.system.entity.SysUserEntity;

/**
 * 系统管理员表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:18
 */
public interface ISysUserMapper extends BaseMapper<SysUserEntity> {

     /**
     * 根据主键ID查询
     *
     * @param userId 主键
     * @return 系统管理员表
     */
     SysUserEntity findByUserId(Long userId);

     /**
     * 多条件查询
     *
     * @param sysUser 条件参数
     * @return 系统管理员表
     */
     SysUserEntity findByCondition(@Param("sysUser") SysUserEntity sysUser);

     /**
     * 多条件查询
     *
     * @param sysUser 条件参数
     * @return 系统管理员表
     */
     List<SysUserEntity> findListByCondition(@Param("sysUser") SysUserEntity sysUser);
}
