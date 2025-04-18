package com.github.fank243.kong.support.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 国家行政区划表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:10
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SupportAreaEntity extends BaseDTO  {

    /** 区域ID */
    private Long areaId;

    /** 区划代码(省级) */
    private String provinceCode;

    /** 区划名称(省级) */
    private String provinceName;

    /** 区划代码(市级) */
    private String cityCode;

    /** 区划名称(市级) */
    private String cityName;

    /** 区划代码(县级) */
    private String areaCode;

    /** 区划名称(县级) */
    private String areaName;

}
