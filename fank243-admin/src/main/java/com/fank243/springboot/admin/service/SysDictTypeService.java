package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.repository.SysDictTypeRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.core.entity.SysDictData;
import com.fank243.springboot.core.entity.SysDictType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import com.fank243.springboot.core.utils.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典类别表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:54:56
 */
@Service
public class SysDictTypeService extends BaseService<SysDictType> {
    @Resource
    private SysDictTypeRepository repository;
    @Resource
    private SysDictDataService sysDictDataService;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        List<SysDictType> dictTypeList = repository.findByStatus(0);
        for (SysDictType sysDictType : dictTypeList) {
            List<SysDictData> sysDictDataList = sysDictDataService.findByDictType(sysDictType.getDictType());
            DictUtils.setDictCache(sysDictType.getDictType(), sysDictDataList);
        }
    }

    public List<SysDictType> findAll() {
        return repository.findByStatus(0);
    }

    public PageBean<SysDictType> pageOfDictType(PageInfo pageInfo, String dictName, String dictType,
        Integer dictStatus) {
        StringBuffer countSQL = new StringBuffer();
        StringBuffer querySQL = new StringBuffer();

        querySQL.append("select * from tb_sys_dict_type where status = 0");
        countSQL.append("select count(*) from tb_sys_dict_type where status = 0");

        Map<String, Object> conditionArgs = new HashMap<>(3);
        if (StringUtils.isNotBlank(dictName)) {
            querySQL.append(" AND dict_name like :dictName");
            countSQL.append(" AND dict_name like :dictName");
            conditionArgs.put("dictName", "%" + dictName + "%");
        }
        if (StringUtils.isNotBlank(dictType)) {
            querySQL.append(" AND dict_type like :dictType");
            countSQL.append(" AND dict_type like :dictType");
            conditionArgs.put("dictType", "%" + dictType + "%");
        }
        if (dictStatus != null && dictStatus >= 0) {
            querySQL.append(" AND status = :dictStatus");
            countSQL.append(" AND status = :dictStatus");
            conditionArgs.put("dictStatus", dictStatus);
        }

        return pageOfBySql(pageInfo, countSQL.toString(), querySQL.toString(), conditionArgs);
    }

}
