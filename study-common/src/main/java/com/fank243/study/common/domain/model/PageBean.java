package com.fank243.study.common.domain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页对象
 *
 * @author FanWeiJie
 * @since 2021-06-15 19:27:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> implements Serializable {

    /**
     * 当前页码，默认：1
     */
    protected long currPage = 1;

    /**
     * 每页显示条数，默认 10
     */
    protected long pageSize = 10;

    /**
     * 总记录数
     */
    protected long totalCount = 0;

    /**
     * 总页数
     */
    protected long totalPage = 0;

    /**
     * 数据集
     */
    protected List<T> data = Collections.emptyList();
}
