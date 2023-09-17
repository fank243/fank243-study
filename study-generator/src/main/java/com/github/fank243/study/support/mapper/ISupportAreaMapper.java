package com.github.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.support.entity.SupportAreaEntity;

/**
 * 国家行政区划表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface ISupportAreaMapper extends BaseMapper<SupportAreaEntity> {

     /**
     * 根据主键ID查询
     *
     * @param areaId 主键
     * @return 国家行政区划表
     */
     SupportAreaEntity findByAreaId(Long areaId);

     /**
     * 多条件查询
     *
     * @param supportArea 条件参数
     * @return 国家行政区划表
     */
     SupportAreaEntity findByCondition(@Param("supportArea") SupportAreaEntity supportArea);

     /**
     * 多条件查询
     *
     * @param supportArea 条件参数
     * @return 国家行政区划表
     */
     List<SupportAreaEntity> findListByCondition(@Param("supportArea") SupportAreaEntity supportArea);
}
