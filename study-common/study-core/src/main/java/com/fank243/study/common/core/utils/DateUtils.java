package com.fank243.study.common.core.utils;

import java.util.Date;
import java.util.List;

import com.fank243.study.common.core.domain.model.OverlapDate;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 日期工具类
 * 
 * @author FanWeiJie
 * @since 2022-06-13 10:38:12
 */
public class DateUtils {

    /**
     * 验证传入的时间区间是否与集合中的时间区间存在重叠
     *
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @param dateList 日期列表
     * @return true：重叠，false：不重叠
     */
    private static boolean overlap(Date beginDate, Date endDate, String uuid, List<OverlapDate> dateList) {
        for (OverlapDate overlapDate : dateList) {
            // 为NULL不比较
            if (beginDate == null || endDate == null || StrUtil.isEmpty(uuid)) {
                continue;
            }

            // 自身不比较
            if (uuid.equalsIgnoreCase(overlapDate.getUuid())) {
                continue;
            }

            if (beginDate.getTime() < overlapDate.getEndDate().getTime()
                && endDate.getTime() > overlapDate.getBeginDate().getTime()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 验证集合中的时间区间是否存在重叠
     *
     * @param dateList 日期列表
     * @return true：重叠，false：不重叠
     */
    public static boolean isOverlap(List<OverlapDate> dateList) {
        if (CollUtil.isEmpty(dateList)) {
            return Boolean.FALSE;
        }

        for (OverlapDate overlapDate : dateList) {
            Date beginDate = overlapDate.getBeginDate();
            Date endDate = overlapDate.getEndDate();
            String uuid = overlapDate.getUuid();
            // 为NULL不比较
            if (beginDate == null || endDate == null || StrUtil.isEmpty(uuid)) {
                continue;
            }

            // 只要有一组存在重叠，则不继续验证
            if (overlap(beginDate, endDate, uuid, dateList)) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
}
