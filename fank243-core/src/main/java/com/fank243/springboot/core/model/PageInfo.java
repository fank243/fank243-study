package com.fank243.springboot.core.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页组件
 * 
 * @author FanWeiJie
 * @date 2018-09-09 18:12:18
 */
@Getter
@Setter
public class PageInfo implements Serializable {

    private static final long serialVersionUID = -2901230338160131244L;

    public PageInfo() {}

    public PageInfo(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    /** 默认页码 **/
    private static final int DEFAULT_PAGE_NUM = 1;

    /** 默认页记录数 **/
    private static final int DEFAULT_PAGE_SIZE = 10;

    /** 当前页码 **/
    private int page = 1;

    /** 页记录数 **/
    private int limit = 10;

    public int getPage() {
        return this.page <= 0 ? 0 : this.page - 1;
    }

}