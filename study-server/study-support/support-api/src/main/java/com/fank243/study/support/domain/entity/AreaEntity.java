package com.fank243.study.support.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fank243.study.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国家行政区划表
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_support_area")
public class AreaEntity extends BaseEntity {

    /** 主键ID */
    @TableId
    private String areaId;

    /** 省级行政区划代码 */
    private String provinceCode;

    /** 省级行政区划名称 */
    private String provinceName;

    /** 市级行政区划代码 */
    private String cityCode;

    /** 市级行政区划名称 */
    private String cityName;

    /** 区县级行政区划代码 */
    private String areaCode;

    /** 区县级行政区划名称 */
    private String areaName;
}
