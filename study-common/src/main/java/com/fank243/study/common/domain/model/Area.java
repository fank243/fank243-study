package com.fank243.study.common.domain.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 国家行政区划表
 * 
 * @author FanWeiJie
 * @since 2022-09-20 10:17:11
 */
@Data
public class Area implements Serializable {

    private String areaCode;

    private String areaName;

    private List<Area> children;
}
