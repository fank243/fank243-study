package com.github.fank243.study.core.base;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Column;
import com.mzt.logapi.starter.annotation.DIffLogIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Base Entity
 * 
 * @author FanWeiJie
 * @since 2021-06-08 22:43:21
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    protected static final String YY_MM_DD_HH_MM_SS = "yy/MM/dd HH:mm:ss";

    /** 创建人 **/
    @DIffLogIgnore
    private String createdBy;

    /** 创建时间 **/
    @JsonFormat(pattern = YY_MM_DD_HH_MM_SS)
    @DIffLogIgnore
    private Date createdDate;

    /** 最近修改人 **/
    @DIffLogIgnore
    private String lastModifiedBy;

    /** 最近修改时间 **/
    @JsonFormat(pattern = YY_MM_DD_HH_MM_SS)
    @DIffLogIgnore
    private Date lastModifiedDate;

    /** 当前页码 **/
    @Column(ignore = true)
    private Integer currPage;

    /** 页记录数 **/
    @Column(ignore = true)
    private Integer pageSize;

    public long getCurrPage() {
        return currPage == null || currPage <= 0 ? 1 : currPage;
    }

    public long getPageSize() {
        if (pageSize == null || pageSize <= 0) {
            return 10;
        }
        return pageSize > 100 ? 100 : pageSize;
    }
}
