package com.fank243.study.common.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 国家行政区划表 常量池
 * 
 * @author FanWeiJie
 * @since 2022-09-20 11:03:37
 */
public class AreaConstants {

    /** 特殊行政区划：直辖市、台港澳地区 **/
    public static final List<String> AREA_SPECIAL =
        Arrays.asList("110000", "120000", "310000", "500000", "710000", "810000", "820000");

    /** 直辖市行政区划代码 **/
    public static final List<String> AREA_DIRECTLY = Arrays.asList("110000", "120000", "310000", "500000");

    /** 台港澳行政区划代码 **/
    public static final List<String> AREA_TGA = Arrays.asList("710000", "810000", "820000");

    /** 行政区划代码类别 **/
    public enum AreaCodeTypeEnum {
        /** 省级 **/
        PROVINCE, CITY, AREA;
    }
}
