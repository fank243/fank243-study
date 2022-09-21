package com.fank243.study.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.api.system.vo.Area;
import com.fank243.study.system.entity.AreaEntity;

/**
 * 国家行政区划表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Repository
public interface IAreaDao extends BaseMapper<AreaEntity> {

    /**
     * 查询所有省级行政区划列表
     * 
     * @return 省级行政区划列表
     */
    @Select("select province_code as areaCode,province_name as areaName from tb_area group by province_code order by province_code")
    List<Area> findProvinces();

    /**
     * 查询省级下辖市级行政区划列表
     * 
     * @param provinceCode 省级行政区划代码
     * @return 市行政区划列表
     */
    @Select("select city_code as areaCode,city_name as areaName from tb_area where province_code = #{provinceCode} group by city_code order by city_code")
    List<Area> findCityByProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 查询市级下辖区县级行政区划列表
     * 
     * @param cityCode 市级行政区划代码
     * @return 区县级行政区划列表
     */
    @Select("select area_code as areaCode,area_name as areaName from tb_area where city_code = #{cityCode} group by area_code order by area_code")
    List<Area> findAreaByCityCode(@Param("cityCode") String cityCode);

}
