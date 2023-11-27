package com.github.fank243.study.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.fank243.study.core.domain.model.PageBean;
import com.mybatisflex.core.paginate.Page;

import cn.hutool.core.bean.BeanUtil;

/**
 * BeanUtil工具类，提供了将分页组件转换为自定义类型的方法。
 *
 * @author FanWeiJie
 * @since 2021-07-09 21:27:29
 */
public class BeanUtils {

    /**
	 * 将分页组件转换为自定义类型的方法。
     *
	 * @param page 分页组件
     * @param targetType 目标类型
	 * @return 自定义类型的分页组件
	 * @throws IllegalArgumentException 如果参数为空，则抛出异常
     */
	public static <T> PageBean<T> convert(Page<?> page, Class<T> targetType) throws IllegalArgumentException {
		if (page == null || targetType == null) {
			throw new IllegalArgumentException("参数不能为空");
		}

		List<T> voList;
        if (page.getTotalRow() > 0) {
            voList = BeanUtil.copyToList(page.getRecords(), targetType);
		} else {
			voList = new ArrayList<>();
        }
        return new PageBean<>(page.getPageNumber(), page.getPageSize(), page.getTotalRow(), page.getTotalPage(),
            voList);
    }
}
