package com.github.fank243.kong.oauth2.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.oauth2.dto.OauthUserDTO;

/**
* Oauth2用户表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface IOauthUserMapper extends BaseMapper<OauthUserEntity> {

    /**
    * 根据主键ID查询
    *
    * @param userId 主键
    * @return Oauth2用户表
    */
    OauthUserDTO findByUserId(Long userId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return Oauth2用户表
    */
    OauthUserDTO findByCondition(@Param("params") OauthUserDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return Oauth2用户表
    */
    List<OauthUserDTO> findListByCondition(@Param("params") OauthUserDTO params);
}
