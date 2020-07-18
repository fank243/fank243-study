package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON 工具类
 * 
 * @author FanWeiJie
 * @date 2019-11-28 11:25:57
 */
@Slf4j
public class JsonUtils {

    /**
     * 字符串转JSON
     * 
     * @param str 字符串
     * @return JSON
     */
    public static JSONObject strToJson(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return JSON.parseObject(str);
        } catch (JSONException e) {
            log.error("JSON转换异常：{}", str);
        }
        return null;
    }

    /**
     * 字符串转Java
     *
     * @param str 字符串
     * @return JSON
     */
    public static <T> T strToJava(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        } catch (JSONException e) {
            log.error("JSON转换异常：{}", str);
        }
        return null;
    }

    /**
     * Map 转换目标对象
     * 
     * @param map Map
     * @param clazz 目标对象
     * @param <T> 目标对象
     * @return 目标对象
     */
    public static <T> T cast(Map<String, String[]> map, Class<T> clazz) {
        Map<String, String> reqMap = new HashMap<>(map.size());
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            reqMap.put(entry.getKey(), entry.getValue()[0]);
        }
        try {
            return JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(reqMap)), clazz);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return null;
    }
}
