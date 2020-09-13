package com.fank243.springboot.core.utils;

import java.util.List;

import com.fank243.springboot.common.redis.RedisKey;
import com.fank243.springboot.common.utils.CacheUtils;
import com.fank243.springboot.core.entity.SysDictData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 字典工具类
 * 
 * @author ruoyi
 */
@Component
public class DictUtils {
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * 设置字典缓存
     *
     * @param key 参数键
     * @param sysDictDataList 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> sysDictDataList) {
        CacheUtils.hashPut(getCacheName(), key, sysDictDataList);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return 字典数据列表
     */
    @SuppressWarnings("unchecked")
    public static List<SysDictData> getDictCache(String key) {
        Object cacheObj = CacheUtils.hashGet(getCacheName(), key);
        if (cacheObj != null) {
            return (List<SysDictData>)cacheObj;
        }
        return null;
    }

    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictType 字典类型
     * @param dictValue 字典值
     * @return 字典标签
     */
    public static String getDictLabel(String dictType, String dictValue) {
        return getDictLabel(dictType, dictValue, SEPARATOR);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     *
     * @param dictType 字典类型
     * @param dictLabel 字典标签
     * @return 字典值
     */
    public static String getDictValue(String dictType, String dictLabel) {
        return getDictValue(dictType, dictLabel, SEPARATOR);
    }

    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictType 字典类型
     * @param dictValue 字典值
     * @param separator 分隔符
     * @return 字典标签
     */
    public static String getDictLabel(String dictType, String dictValue, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictData> sysDictDataList = getDictCache(dictType);

        if (CollectionUtils.isEmpty(sysDictDataList)) {
            return "";
        }

        if (StringUtils.containsAny(separator, dictValue)) {
            for (SysDictData dict : sysDictDataList) {
                for (String value : dictValue.split(separator)) {
                    if (value.equals(dict.getDictValue())) {
                        propertyString.append(dict.getDictLabel()).append(separator);
                        break;
                    }
                }
            }
        } else {
            for (SysDictData dict : sysDictDataList) {
                if (dictValue.equals(dict.getDictValue())) {
                    return dict.getDictLabel();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     *
     * @param dictType 字典类型
     * @param dictLabel 字典标签
     * @param separator 分隔符
     * @return 字典值
     */
    public static String getDictValue(String dictType, String dictLabel, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictData> sysDictDataList = getDictCache(dictType);

        if (CollectionUtils.isEmpty(sysDictDataList)) {
            return "";
        }

        if (StringUtils.containsAny(separator, dictLabel)) {
            for (SysDictData dict : sysDictDataList) {
                for (String label : dictLabel.split(separator)) {
                    if (label.equals(dict.getDictLabel())) {
                        propertyString.append(dict.getDictValue()).append(separator);
                        break;
                    }
                }
            }
        } else {
            for (SysDictData dict : sysDictDataList) {
                if (dictLabel.equals(dict.getDictLabel())) {
                    return dict.getDictValue();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        CacheUtils.delete(getCacheName());
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    public static String getCacheName() {
        return RedisKey.SYS_DICT;
    }

}