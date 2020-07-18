package com.fank243.springboot.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 国家行政区划
 * 
 * @author FanWeiJie
 * @date 2019-12-02 11:35:10
 */
@Data
@ApiModel("Area")
public class Area implements Serializable {

    @ApiModelProperty(value = "行政区划代码", required = true)
    private int code;
    @ApiModelProperty(value = "行政区划名称", required = true, position = 1)
    private String name;

    public Area() {}

    public Area(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
