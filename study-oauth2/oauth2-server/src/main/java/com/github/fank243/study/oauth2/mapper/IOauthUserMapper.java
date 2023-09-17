package com.github.fank243.study.oauth2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.fank243.study.oauth2.domain.OauthUserEntity;
import com.mybatisflex.core.BaseMapper;

/**
 * Oauth2用户表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:19
 */
public interface IOauthUserMapper extends BaseMapper<OauthUserEntity> {

    /**
     * 根据主键ID查询
     *
     * @param userId 主键
     * @return Oauth2用户表
     */
    OauthUserEntity findByUserId(Long userId);

    /**
     * 多条件查询
     *
     * @param oauthUser 条件参数
     * @return Oauth2用户表
     */
    OauthUserEntity findByCondition(@Param("oauthUser") OauthUserEntity oauthUser);

    /**
     * 多条件查询
     *
     * @param oauthUser 条件参数
     * @return Oauth2用户表
     */
    List<OauthUserEntity> findListByCondition(@Param("oauthUser") OauthUserEntity oauthUser);
}
