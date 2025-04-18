package com.github.fank243.kong.oauth2.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.String;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.oauth2.dto.OauthAccessTokenDTO;

/**
* 用户授权令牌表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface IOauthAccessTokenMapper extends BaseMapper<OauthAccessTokenEntity> {

    /**
    * 根据主键ID查询
    *
    * @param tokenId 主键
    * @return 用户授权令牌表
    */
    OauthAccessTokenDTO findByTokenId(String tokenId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 用户授权令牌表
    */
    OauthAccessTokenDTO findByCondition(@Param("params") OauthAccessTokenDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 用户授权令牌表
    */
    List<OauthAccessTokenDTO> findListByCondition(@Param("params") OauthAccessTokenDTO params);
}
