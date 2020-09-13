package com.fank243.springboot.admin.config.freemarker.dict;

/**
 * @author FanWeiJie
 * @date 2020-09-13 15:28:21
 */
public interface DictFormatInterface {
    /**
     * 构造生成枚举html
     *
     * @param dictType dictType
     * @param fieldName fieldName
     * @return html
     */
    String buildHtml(String dictType, String fieldName);
}