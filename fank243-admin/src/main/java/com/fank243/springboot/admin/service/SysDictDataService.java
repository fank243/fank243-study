package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.repository.SysDictDataRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.core.entity.SysDictData;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import com.fank243.springboot.core.utils.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典数据表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:54:56
 */
@Service
public class SysDictDataService extends BaseService<SysDictData> {
    @Resource
    private SysDictDataRepository repository;

    public List<SysDictData> getType(String dictType) {
        List<SysDictData> sysDictDataList = DictUtils.getDictCache(dictType);
        if (!CollectionUtils.isEmpty(sysDictDataList)) {
            return sysDictDataList;
        }

        sysDictDataList = repository.findByDictTypeAndStatusOrderByDictSort(dictType, 0);
        if (!CollectionUtils.isEmpty(sysDictDataList)) {
            DictUtils.setDictCache(dictType, sysDictDataList);
            return sysDictDataList;
        }

        return null;
    }

    public List<SysDictData> findByDictType(String dictType) {
        return repository.findByDictTypeAndStatusOrderByDictSort(dictType, 0);
    }

    public String findByDictTypeAndDictValue(String dictType, Object dictValue) {
        return repository.findByDictTypeAndDictValue(dictType, dictValue);
    }

    public PageBean<SysDictData> pageOfDictData(PageInfo pageInfo, String dictType, String dictLabel,
        Integer dictStatus) {
        StringBuffer countSQL = new StringBuffer();
        StringBuffer querySQL = new StringBuffer();

        querySQL.append("select * from tb_sys_dict_data where status = 0");
        countSQL.append("select count(*) from tb_sys_dict_data where status = 0");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(dictType)) {
            querySQL.append(" AND dict_type = :dictType");
            countSQL.append(" AND dict_type = :dictType");
            conditionArgs.put("dictType", dictType);
        }
        if (StringUtils.isNotBlank(dictLabel)) {
            querySQL.append(" AND dict_label like :dictLabel");
            countSQL.append(" AND dict_label like :dictLabel");
            conditionArgs.put("dictLabel", "%" + dictLabel + "%");
        }
        if (dictStatus != null && dictStatus >= 0) {
            querySQL.append(" AND status = :dictStatus");
            countSQL.append(" AND status = :dictStatus");
            conditionArgs.put("dictStatus", dictStatus);
        }

        return pageOfBySql(pageInfo, countSQL.toString(), querySQL.toString(), conditionArgs);
    }
}
