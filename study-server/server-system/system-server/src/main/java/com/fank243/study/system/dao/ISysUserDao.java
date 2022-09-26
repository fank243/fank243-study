package com.fank243.study.system.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.system.domain.entity.SysUserEntity;

/**
 * 系统管理员表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Repository
public interface ISysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return 用户实体
     */
    @Select("select * from tb_sys_user where username = #{username}")
    SysUserEntity findSysUserByUsername(String username);

    /***
     * 锁定登录错误信息
     *
     * @param userId 用户ID
     * @param loginLockTime 登录锁定时间
     * @param status 用户状态
     */
    @Update("""
        update tb_sys_user set
        login_err_count = login_err_count + 1,login_lock_time = #{loginLockTime},`status` = #{status} where user_id = #{userId}
        """)
    void lockLoginErr(@Param("userId") String userId, @Param("loginLockTime") Date loginLockTime,
        @Param("status") int status);

    /***
     * 解锁登录错误信息
     *
     * @param sysUserEntity SysUserEntity
     */
    @Update("update tb_sys_user set login_err_count = 0,login_lock_time = null, status = #{status},last_login_time = #{lastLoginTime},last_login_ip = #{lastLoginIp} where user_id = #{userId}")
    void unLockLoginErr(SysUserEntity sysUserEntity);
}
