package com.github.fank243.kong.support.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.support.dto.SupportAreaDTO;

/**
* 国家行政区划表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface ISupportAreaMapper extends BaseMapper<SupportAreaEntity> {

    /**
    * 根据主键ID查询
    *
    * @param areaId 主键
    * @return 国家行政区划表
    */
    SupportAreaDTO findByAreaId(Long areaId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 国家行政区划表
    */
    SupportAreaDTO findByCondition(@Param("params") SupportAreaDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 国家行政区划表
    */
    List<SupportAreaDTO> findListByCondition(@Param("params") SupportAreaDTO params);
}
