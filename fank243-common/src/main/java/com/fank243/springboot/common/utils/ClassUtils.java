package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Utils
 * 
 * @author FanWeiJie
 * @date 2020-03-24 20:22:16
 */
public class ClassUtils {

    /**
     * 忽略编辑器检测 Unchecked assignment: 'java.util.List' to 'java.util.List<T>'
     * 
     * @param obj Object
     * @param <T> 目标对象
     * @return 目标对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        if (obj == null) {
            return null;
        }
        return (T)obj;
    }




}
