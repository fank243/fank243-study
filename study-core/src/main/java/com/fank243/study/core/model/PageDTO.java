package com.fank243.study.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页对象
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:27:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO implements Serializable {

    private Long currPage;

    private Long pageSize;

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
