package com.fank243.study.client;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fank243.study.client.domain.PageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Service
 * 
 * @author FanWeiJie
 * @since 2021-06-15 21:24:08
 */
public interface BaseService {

    /**
     * 分页组件转换函数
     *
     * @param page IPage
     * @param targetType 目标类型
     * @return IPage
     */
    default <T> PageBean<T> convert(IPage<?> page, Class<T> targetType) {
        List<T> voList = new ArrayList<>(1);
        if (page.getTotal() > 0) {
            voList = BeanUtil.copyToList(page.getRecords(), targetType);
        }
        return new PageBean<T>(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), voList);
    }

}
