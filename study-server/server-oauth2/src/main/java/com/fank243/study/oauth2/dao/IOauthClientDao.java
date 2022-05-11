package com.fank243.study.oauth2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.oauth2.domain.OauthClientEntity;
import com.fank243.study.oauth2.domain.SysUserEntity;

/**
 * 授权客户端表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Mapper
public interface IOauthClientDao extends BaseMapper<OauthClientEntity> {

    /**
     * 根据用户名查找
     * 
     * @param username 用户名
     * @return 用户实体
     */
    @Select("select * from tb_sys_user where username = #{username}")
    SysUserEntity findSysUserByUsername(String username);
}
