package com.github.fank243.study.support.domain.dto;

import com.github.fank243.study.core.base.BaseDTO;
import com.github.fank243.study.core.base.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 国家行政区划表
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AreaDTO extends BaseDTO {

    /** 主键ID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
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
