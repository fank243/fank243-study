package com.github.fank243.study.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.fank243.study.core.domain.model.PageBean;

import cn.hutool.core.bean.BeanUtil;

/**
 * Bean Util
 *
 * @author FanWeiJie
 * @since 2021-07-09 21:27:29
 */
public class BeanUtils {

    /**
     * 分页组件转换函数
     *
     * @param page IPage
     * @param targetType 目标类型
     * @return IPage
     */
    public static <T> PageBean<T> convert(IPage<?> page, Class<T> targetType) {
        List<T> voList = new ArrayList<>(1);
        if (page.getTotal() > 0) {
            voList = BeanUtil.copyToList(page.getRecords(), targetType);
        }
        return new PageBean<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), voList);
    }
}
