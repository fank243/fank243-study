package com.fank243.springboot.admin.service;

import com.fank243.springboot.core.entity.SysDictData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * freermark call
 * 
 * @author FanWeiJie
 * @date 2020-09-13 00:31:14
 */
@Service
public class DictService {

    private static SysDictDataService dictDataService = null;

    public DictService(SysDictDataService dictDataService) {
        DictService.dictDataService = dictDataService;
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public static List<SysDictData> getType(String dictType) {
        return dictDataService.getType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public static String getLabel(String dictType, Object dictValue) {
        return dictDataService.findByDictTypeAndDictValue(dictType, dictValue);
    }
}
