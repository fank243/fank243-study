package com.fank243.springboot.admin.config.freemarker.dict;

import com.fank243.springboot.admin.config.freemarker.dict.template.DictFormatTemplate;
import com.fank243.springboot.common.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author FanWeiJie
 * @date 2020-09-13 15:29:02
 */
@Slf4j
public class DictFormatFactory {

    public static DictFormatInterface createInstance(String eleType) {
        if (StringUtils.isEmpty(eleType)) {
            return new DictFormatTemplate();
        }
        // 文件名 如果type传template 就需要有一个名为ThFormatterTemplate的文件
        // 并且实现了ThFormatterInterface以及重写生成html的方法
        String fileName = "DictFormat" + StrUtils.firstCharUpperCase(eleType);
        // 类路径 通过反射去创建实现类
        String className = "com.fank243.springboot.admin.config.freemarker.dict.template." + fileName;
        // 生成表头格式实现类
        DictFormatInterface dictFormatInterface = null;
        try {
            dictFormatInterface = (DictFormatInterface)Class.forName(className).newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return dictFormatInterface;
    }
}