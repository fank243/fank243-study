package com.fank243.study.common.domain.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
*  日期重叠对象
* @author FanWeiJie
* @since 2022-06-09 14:58:22
*/
@Data
public class OverlapDate implements Serializable {

    /** 开始日期 **/
    private Date beginDate;

    /** 结束日期 **/
    private Date endDate;

    /** UUID **/
    private String uuid;
}
