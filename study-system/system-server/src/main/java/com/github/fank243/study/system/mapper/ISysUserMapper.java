package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.fank243.study.system.domain.entity.SysUserEntity;
import com.mybatisflex.core.BaseMapper;

/**
 * 系统管理员表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
public interface ISysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return 用户实体
     */
    @Select("select * from tb_sys_user where username = #{username}")
    SysUserEntity findByUsername(String username);

    /**
     * 更新用户登录信息
     *
     * @param sysUser 登录用户
     * @return 操作结果
     */
    @Update("update tb_sys_user set last_login_time = #{lastLoginTime},last_login_ip = #{lastLoginIp} where user_id = #{userId}")
    int updateLoginInfoByUserId(SysUserEntity sysUser);
}
