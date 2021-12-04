package com.fank243.study.oauth2.dao;

import com.fank243.study.oauth2.domain.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.oauth2.domain.OauthClientEntity;
import org.apache.ibatis.annotations.Select;

/**
 * 授权客户端表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Mapper
public interface IOauthClientDao extends BaseMapper<OauthClientEntity> {

    @Select("select * from tb_sys_user where username = #{username}")
    SysUserEntity findSysUserByUsername(String username);
}
