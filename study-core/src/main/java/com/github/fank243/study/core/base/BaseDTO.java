package com.github.fank243.study.core.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * BaseDTO
 * 
 * @author FanWeiJie
 * @since 2021-06-15 19:27:04
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseDTO implements Serializable {

    /** 当前页码 **/
    private Integer currPage;

    /** 页记录数 **/
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
