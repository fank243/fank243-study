package com.fank243.study.client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

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
     * 当前页
     */
    protected long currPage = 1;

    /**
     * 每页显示条数，默认 10
     */
    protected long pageSize = 10;

    /**
     * 总记录数
     */
    protected long records = 0;

    /**
     * 总页数
     */
    protected long pages = 0;

    /**
     * 查询数据列表
     */
    protected List<T> recordList = Collections.emptyList();
}
