package com.fank243.study.core.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Base Entity
 * 
 * @author FanWeiJie
 * @since 2021-06-08 22:43:21
 */
@Data
public class BaseEntity implements Serializable {

    protected static final String YY_MM_DD_HH_MM_SS = "yy/MM/dd HH:mm:ss";

    @TableId
    private String id;

    /** 创建人 **/
    private String createdBy;

    /** 创建时间 **/
    @JsonFormat(pattern = YY_MM_DD_HH_MM_SS)
    private Date createdDate;

    /** 最近修改人 **/
    private String lastModifiedBy;

    /** 最近修改时间 **/
    @JsonFormat(pattern = YY_MM_DD_HH_MM_SS)
    private Date lastModifiedDate;
}
