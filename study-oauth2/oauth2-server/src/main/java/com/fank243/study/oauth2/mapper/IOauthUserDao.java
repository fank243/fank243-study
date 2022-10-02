package com.fank243.study.oauth2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.oauth2.api.domain.entity.OauthUserEntity;

/**
 * 授权用户表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Mapper
public interface IOauthUserDao extends BaseMapper<OauthUserEntity> {

    /**
     * 根据用户名查找
     * 
     * @param username 用户名
     * @return 用户实体
     */
    @Select("select * from tb_oauth_user where username = #{username}")
    OauthUserEntity findSysUserByUsername(String username);

    /**
     * 根据用户ID查找
     *
     * @param userId 用户ID
     * @return 用户实体
     */
    @Select("select * from tb_oauth_user where user_id = #{userId}")
    OauthUserEntity findByUserId(String userId);
}
