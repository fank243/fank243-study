package com.fank243.study.gateway.dao;

import com.fank243.study.gateway.domain.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统角色
 * 
 * @author FanWeiJie
 * @since 2022-05-11 14:03:57
 */
@Mapper
public interface ISysRoleDao {

    /**
     * 根据userId查询角色集合
     *
     * @param userId 管理员ID
     * @return 角色集合
     */
    @Select("SELECT a.* FROM `tb_sys_role` a left join tb_sys_user_role b on a.role_id = b.role_id where b.user_id = #{userId}")
    List<SysRoleVO> findByUserId(String userId);
}
