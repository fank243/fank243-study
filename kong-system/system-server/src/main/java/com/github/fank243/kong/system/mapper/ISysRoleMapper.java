//package com.github.fank243.kong.system.mapper;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//import com.github.fank243.kong.system.domain.entity.SysRoleEntity;
//import com.github.fank243.kong.system.domain.vo.SysRoleVO;
//import com.mybatisflex.core.BaseMapper;
//
///**
// * 系统角色表 数据访问层
// *
// * @author FanWeiJie
// * @since 2021-11-24
// */
//@Mapper
//public interface ISysRoleMapper extends BaseMapper<SysRoleEntity> {
//
//    /**
//     * 根据userId查询角色集合
//     *
//     * @param userId 管理员ID
//     * @return 角色集合
//     */
//    @Select("SELECT a.* FROM `tb_sys_role` a left join tb_sys_user_role b on a.role_id = b.role_id where b.user_id = #{userId}")
//    List<SysRoleVO> findByUserId(String userId);
//}
