package com.fank243.springboot.admin.controller.system;

import com.fank243.springboot.admin.service.SysDictDataService;
import com.fank243.springboot.admin.service.SysDictTypeService;
import com.fank243.springboot.core.entity.SysDictData;
import com.fank243.springboot.core.entity.SysDictType;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author FanWeiJie
 * @date 2020-09-13 17:22:28
 */
@Slf4j
@RequiresRoles("role:root")
@RequestMapping("/admin/system/dict")
@Controller
public class SysDictController {

    private final String pre = "admin/system/dict";

    @Resource
    private SysDictTypeService sysDictTypeService;
    @Resource
    private SysDictDataService sysDictDataService;

    @GetMapping("/dictTypeIndex")
    public String dictTypeIndex() {
        return pre + "/dictTypeIndex";
    }

    @GetMapping("/dictTypeList")
    @ResponseBody
    public LayuiResultInfo dictTypeList(PageInfo pageInfo, String dictName, String dictType, Integer dictStatus) {
        PageBean<SysDictType> pageBean = sysDictTypeService.pageOfDictType(pageInfo, dictName, dictType, dictStatus);
        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }

    @GetMapping("/dictDataIndex")
    public String dictDataIndex(String dictType, Model model) {

        model.addAttribute("dictType", dictType);
        return pre + "/dictDataIndex";
    }

    @GetMapping("/dictDataList")
    @ResponseBody
    public LayuiResultInfo dictDataList(PageInfo pageInfo, String dictType, String dictLabel,
        Integer dictStatus) {
        PageBean<SysDictData> pageBean =
            sysDictDataService.pageOfDictData(pageInfo, dictType, dictLabel, dictStatus);
        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }
}
