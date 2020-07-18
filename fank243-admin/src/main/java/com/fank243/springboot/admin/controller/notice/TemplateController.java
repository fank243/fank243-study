package com.fank243.springboot.admin.controller.notice;

import com.fank243.springboot.admin.model.ActiveUser;
import com.fank243.springboot.admin.service.notice.TemplateNoticeService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.TemplateNotice;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 通知模板
 * 
 * @author FanWeiJie
 * @date 2020-03-26 22:56:51
 */
@Slf4j
@RequestMapping("/admin/template")
@Controller
public class TemplateController {
    @Resource
    private TemplateNoticeService templateNoticeService;

    @RequiresPermissions("template:query")
    @GetMapping("")
    public String templateIndex() {
        return "/admin/notice/template/template";
    }

    @ResponseBody
    @GetMapping("/templateList")
    public LayuiResultInfo templateList(PageInfo pageInfo, Integer type, String keyword) {
        keyword = StringUtils.trim(keyword);
        PageBean<TemplateNotice> pageBean = templateNoticeService.pageOfTemplate(pageInfo, type, keyword);

        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }

    /** 修改模板页面 **/
    @RequiresPermissions("template:update")
    @GetMapping("/modify/{templateId}")
    public String modify(@PathVariable("templateId") Long templateId, Model model) {
        TemplateNotice templateNotice = templateNoticeService.findById(templateId);

        model.addAttribute("templateNotice", templateNotice);
        return "/admin/notice/template/modify";
    }

    /** 修改模板 **/
    @RequiresPermissions("template:update")
    @PostMapping("/modify")
    @ResponseBody
    public ResultInfo modify(TemplateNotice templateNotice) {
        ActiveUser activeUser = ShiroUtils.getActiveUser();

        ResultInfo result = templateNoticeService.modify(activeUser.getUserId(), templateNotice);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    /** 修改模板状态 **/
    @RequiresPermissions("template:update")
    @PutMapping("/modify-status/{id}")
    @ResponseBody
    public ResultInfo modifyStatus(@PathVariable(name = "id") Long templateId) {
        ActiveUser activeUser = ShiroUtils.getActiveUser();

        ResultInfo result = templateNoticeService.modifyStatus(activeUser.getUserId(), templateId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

}
