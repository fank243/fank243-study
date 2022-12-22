package com.github.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.system.domain.entity.SysUserEntity;

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
     * 根据OpenID查找
     *
     * @param openId OpenID
     * @return 用户实体
     */
    @Select("select * from tb_sys_user where open_id = #{openId}")
    SysUserEntity findByOpenId(String openId);

    /**
     * 更新用户登录信息
     *
     * @param sysUser 登录用户
     * @return 操作结果
     */
    @Update("update tb_sys_user set last_login_time = #{lastLoginTime},last_login_ip = #{lastLoginIp} where user_id = #{userId}")
    int updateLoginInfoByUserId(SysUserEntity sysUser);
}
