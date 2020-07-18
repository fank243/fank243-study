package com.fank243.springboot.admin.controller.notice;

import com.fank243.springboot.admin.service.notice.EmailRecordService;
import com.fank243.springboot.admin.service.notice.SmsRecordService;
import com.fank243.springboot.core.dto.EmailRecordDTO;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 电子邮件发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 22:56:51
 */
@RequestMapping("/admin/emailrecord")
@Controller
public class EmailRecordController {

    @Resource
    private EmailRecordService emailRecordService;

    @RequiresPermissions("emailrecord:query")
    @GetMapping("")
    public String emailrecord() {
        return "/admin/notice/emailrecord";
    }

    @ResponseBody
    @GetMapping("/emailrecord")
    public LayuiResultInfo emailrecord(PageInfo pageInfo, String keyword, String startDate, String endDate) {

        PageBean<EmailRecordDTO> pageBean = emailRecordService.pageOfEmailRecord(pageInfo, keyword, startDate, endDate);

        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }
}
