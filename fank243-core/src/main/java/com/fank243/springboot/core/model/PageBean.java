package com.fank243.springboot.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页组件
 * 
 * @author FanWeiJie
 * @date 2020-03-24 20:42:20
 */
@ToString
@Data
public class PageBean<T> implements Serializable {

    public PageBean() {
        this(DEFAULT_PAGE_SIZE);
    }

    public PageBean(int pageSize) {
        this.pageSize = pageSize;
    }

    /** 默认页记录数 **/
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    /** 默认页码 **/
    public static final Integer DEFAULT_CURRENT_PAGE = 1;

    /** 当前页 */
    private int currPage = 1;

    /** 总记录数 */
    private int totalCount;

    /** 总页数 */
    private int totalPageCount;

    /** 每页的记录条数 */
    private int pageSize;

    /** 当前页的记录 */
    private List<T> list;

    /** 搜索条件 */
    private Map<String, Object> conditions;

    public void setCurrPage(int currPage) {
        this.currPage = currPage <= 0 ? DEFAULT_CURRENT_PAGE : currPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageNum(Object pageNum) {
        if (pageNum instanceof String[]) {
            String[] pageStr = (String[])pageNum;
            try {
                this.currPage = Integer.parseInt(pageStr[0]);
                this.currPage = this.currPage <= 0 ? 1 : this.currPage;
            } catch (Exception e) {
                this.currPage = 1;
            }
        }
        if (pageNum instanceof String) {
            String pageStr = (String)pageNum;
            try {
                this.currPage = Integer.parseInt(pageStr);
                this.currPage = this.currPage <= 0 ? 1 : this.currPage;
            } catch (Exception e) {
                this.currPage = 1;
            }
        }

        if (pageNum instanceof Integer) {
            try {
                this.currPage = (Integer)pageNum;
                this.currPage = this.currPage <= 0 ? 1 : this.currPage;
            } catch (Exception e) {
                this.currPage = 1;
            }
        }
    }

    public void setPageNum(int pageNum) {
        this.currPage = this.currPage <= 0 ? 1 : this.currPage;
    }

    /**
     * 得到总记录条数
     * 
     * @param totalCount 总数量
     * @return 如果不存在记录，返回false
     */
    public boolean setTotalNum(int totalCount) {
        this.totalCount = totalCount;

        if (this.totalCount == 0) {
            this.totalPageCount = 0;

            return false;
        } else {
            this.totalPageCount = (this.totalCount - 1) / this.pageSize + 1;
        }

        this.currPage = Math.min(this.currPage, this.totalPageCount);

        return true;
    }

    public int getTotalPageCount() {
        return (this.totalCount - 1) / this.pageSize + 1;
    }

}
