//package com.github.fank243.kong.system.mapper;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import com.github.fank243.kong.system.domain.entity.SysUserEntity;
//import com.mybatisflex.core.BaseMapper;
//
///**
// * 系统管理员表 数据访问层
// *
// * @author FanWeiJie
// * @since 2021-11-24
// */
//@Mapper
//public interface ISysUserMapper extends BaseMapper<SysUserEntity> {
//
//    /**
//     * 根据主键ID查询
//     *
//     * @param userId 主键
//     * @return 系统管理员表
//     */
//    SysUserEntity findByUserId(Long userId);
//
//    /**
//     * 多条件查询
//     *
//     * @param sysUser 条件参数
//     * @return 系统管理员表
//     */
//	SysUserEntity findByCondition(@Param("params") SysUserEntity sysUser);
//
//    /**
//     * 多条件查询
//     *
//     * @param sysUser 条件参数
//     * @return 系统管理员表
//     */
//	List<SysUserEntity> findListByCondition(@Param("params") SysUserEntity sysUser);
//
//}
