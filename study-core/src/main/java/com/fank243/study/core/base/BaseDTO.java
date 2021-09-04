package com.fank243.study.core.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BaseDTO
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:27:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {

    private Long id;

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
