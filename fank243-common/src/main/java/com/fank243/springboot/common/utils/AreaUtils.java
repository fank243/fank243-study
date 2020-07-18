package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.common.model.Area;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 国家行政区划工具类 > 省市县版
 *
 * @author FanWeiJie
 * @date 2019-11-30 17:39:23
 */
@Slf4j
public class AreaUtils {

    /**
     * 国家行政区划代码
     **/
    private static JSONArray gb2260;

    /** 台港澳代码 **/
    private static final List<Integer> cityList = Arrays.asList(710000, 820000, 810000);

    static {
        InputStream is = AreaUtils.class.getResourceAsStream("/area-202002.json");
        try {
            String json = IOUtils.toString(is, StandardCharsets.UTF_8);
            gb2260 = JSON.parseArray(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有省份数据
     *
     * @return 省份列表
     */
    public static List<Area> getProvList() {
        List<Area> provList = new ArrayList<>();
        for (Object obj : gb2260) {
            JSONObject json = (JSONObject)obj;
            int code = json.getInteger("code");
            if (cityList.contains(code)) {
                continue;
            }
            provList.add(new Area(code, json.getString("name")));
        }

        provList.sort(Comparator.comparingInt(Area::getCode));

        return provList;
    }

    /**
     * 获取省份下城市列表
     *
     * @param provCode 省份代码
     * @return 城市列表
     */
    public static List<Area> getCity(Integer provCode) {
        List<Area> cityList = new ArrayList<>();
        try {
            for (Object obj : gb2260) {
                JSONObject json = (JSONObject)obj;
                if (json.getInteger("code").equals(provCode)) {
                    for (Object cityObj : json.getJSONArray("city")) {
                        JSONObject cityJson = (JSONObject)cityObj;
                        cityList.add(new Area(cityJson.getInteger("code"), cityJson.getString("name")));
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }

        cityList.sort(Comparator.comparingInt(Area::getCode));

        return cityList;
    }

    /**
     * 获取城市下属区县数据
     *
     * @param cityCode 城市代码
     * @return 区县列表
     */
    public static List<Area> getArea(Integer cityCode) {
        List<Area> areaList = new ArrayList<>();
        try {
            for (Object obj : gb2260) {
                JSONObject json = (JSONObject)obj;
                JSONArray cityArray = json.getJSONArray("city");
                if (cityArray == null || cityArray.isEmpty()) {
                    continue;
                }
                for (Object cityObj : cityArray) {
                    JSONObject cityJson = (JSONObject)cityObj;
                    if (cityJson.getInteger("code").equals(cityCode)) {
                        for (Object areaObj : cityJson.getJSONArray("area")) {
                            JSONObject areaJson = (JSONObject)areaObj;
                            areaList.add(new Area(areaJson.getInteger("code"), areaJson.getString("name")));
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }

        areaList.sort(Comparator.comparingInt(Area::getCode));

        return areaList;
    }

    public static Area getProvByCode(Integer provCode) {
        for (Object obj : gb2260) {
            JSONObject json = (JSONObject)obj;
            if (provCode.equals(json.getInteger("code"))) {
                return new Area(json.getInteger("code"), json.getString("name"));
            }
        }
        return null;
    }

    public static Area getCityByCode(Integer provCode, Integer cityCode) {
        for (Object obj : gb2260) {
            JSONObject json = (JSONObject)obj;
            if (provCode.equals(json.getInteger("code"))) {
                JSONArray cityArray = json.getJSONArray("city");
                for (Object cityObj : cityArray) {
                    JSONObject cityJson = (JSONObject)cityObj;
                    if (cityCode.equals(cityJson.getInteger("code"))) {
                        return new Area(cityJson.getInteger("code"), cityJson.getString("name"));
                    }
                }
            }
        }
        return null;
    }

    public static Area getAreaByCode(Integer provCode, Integer cityCode, Integer areaCode) {
        for (Object obj : gb2260) {
            JSONObject json = (JSONObject)obj;
            if (provCode.equals(json.getInteger("code"))) {
                JSONArray cityArray = json.getJSONArray("city");
                for (Object cityObj : cityArray) {
                    JSONObject cityJson = (JSONObject)cityObj;
                    if (cityCode.equals(cityJson.getInteger("code"))) {
                        JSONArray areaArray = cityJson.getJSONArray("area");
                        for (Object areaObj : areaArray) {
                            JSONObject areaJson = (JSONObject)areaObj;
                            if (areaCode.equals(areaJson.getInteger("code"))) {
                                return new Area(areaJson.getInteger("code"), areaJson.getString("name"));
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
