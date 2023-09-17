package com.github.fank243.study.support.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 国家行政区划表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_support_area", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class SupportAreaEntity extends BaseEntity implements Serializable {

    /** 区域ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
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
