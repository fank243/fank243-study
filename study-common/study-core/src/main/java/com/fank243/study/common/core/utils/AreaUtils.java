package com.fank243.study.common.core.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fank243.study.common.core.constants.AreaConstants;
import com.fank243.study.common.core.domain.model.Area;

/**
 * 国家行政区划工具类
 * 
 * @author FanWeiJie
 * @since 2022-09-20 10:50:53
 */
public class AreaUtils {

    /**
     * 根据行政区划代码获取行政区划类别
     * 
     * @param code 行政区划代码
     * @return 行政区划类别
     */
    public static AreaConstants.AreaCodeTypeEnum getAreaCodeType(String code) {
        if (code.endsWith("0000")) {
            return AreaConstants.AreaCodeTypeEnum.PROVINCE;
        }
        if (code.endsWith("00")) {
            return AreaConstants.AreaCodeTypeEnum.CITY;
        }
        return AreaConstants.AreaCodeTypeEnum.AREA;
    }

    /**
     * 将字符串转换为行政区划
     * 
     * @param list 行政区划文本字符串
     * @return 行政区划列表
     */
    public static List<Area> parse(List<String> list) {
        List<Area> areaList = new ArrayList<>();
        list.forEach(s -> {
            String[] split = s.split(",");
            for (String str : split) {
                String[] array = StrUtil.trimToEmpty(str).split("\t");
                String areaCode = "", areaName = "";
                for (String str2 : array) {
                    str2 = StrUtil.trimToEmpty(str2);
                    if (StrUtil.isBlank(str2)) {
                        continue;
                    }
                    if (NumberUtil.isNumber(str2)) {
                        areaCode = str2;
                    } else {
                        areaName = str2;
                    }
                }
                if (StrUtil.isBlank(areaCode) || StrUtil.isBlank(areaName)) {
                    continue;
                }
                Area area = new Area();
                area.setAreaCode(areaCode);
                area.setAreaName(areaName);
                areaList.add(area);
            }
        });
        areaList.sort(Comparator.comparing(Area::getAreaCode));
        System.out.println(JSONUtil.toJsonStr(areaList));
        return areaList;
    }
}
