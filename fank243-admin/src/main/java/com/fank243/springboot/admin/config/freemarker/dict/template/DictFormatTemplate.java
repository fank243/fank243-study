package com.fank243.springboot.admin.config.freemarker.dict.template;

import com.fank243.springboot.admin.config.freemarker.dict.DictFormatInterface;
import com.fank243.springboot.admin.service.SysDictDataService;
import com.fank243.springboot.common.utils.spring.SpringUtils;
import com.fank243.springboot.core.entity.SysDictData;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author FanWeiJie
 * @date 2020-09-13 15:30:36
 */
public class DictFormatTemplate implements DictFormatInterface {

    @Override
    public String buildHtml(String dictType, String fieldName) {
        SysDictDataService sysDictDataService = SpringUtils.getBean(SysDictDataService.class);
        List<SysDictData> sysDictModelList = sysDictDataService.getType(dictType);

        // {{# if(d.status == 0){ }}
        // <button class="layui-btn layui-btn-sm">${dictService.getLabel('user_status',0)}</button>
        // {{# } else if(d.status == 1) { }}
        // <button class="layui-btn layui-btn-sm">${dictService.getLabel('user_status',1)}</button>
        // {{# } }}
        StringBuilder dictHtml = new StringBuilder();
        // layui templet

        for (SysDictData sysDictData : sysDictModelList) {
            dictHtml.append("{{# if(d.").append(fieldName).append(" == ").append(sysDictData.getDictValue())
                .append("){ }}");

            dictHtml.append("<button style='cursor:default;' class=\"layui-btn layui-btn-sm ");
            if (StringUtils.isNotBlank(sysDictData.getListClass())) {
                dictHtml.append("layui-btn-").append(sysDictData.getListClass());
            }
            dictHtml.append("\">").append(sysDictData.getDictLabel()).append("</button>").append("{{# } }}");
        }

        return dictHtml.toString();
    }
}