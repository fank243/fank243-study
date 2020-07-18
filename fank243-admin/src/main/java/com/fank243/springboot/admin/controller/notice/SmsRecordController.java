package com.fank243.springboot.admin.controller.notice;

import com.fank243.springboot.admin.service.notice.PushRecordService;
import com.fank243.springboot.admin.service.notice.SmsRecordService;
import com.fank243.springboot.core.dto.SmsRecordDTO;
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
 * 短信发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 22:56:51
 */
@RequestMapping("/admin/smsrecord")
@Controller
public class SmsRecordController {

    @Resource
    private SmsRecordService smsRecordService;

    @RequiresPermissions("smsrecord:query")
    @GetMapping("")
    public String smsrecord() {
        return "/admin/notice/smsrecord";
    }

    @ResponseBody
    @GetMapping("/smsrecord")
    public LayuiResultInfo smsrecord(PageInfo pageInfo, String keyword, String startDate, String endDate) {

        PageBean<SmsRecordDTO> pageBean = smsRecordService.pageOfSmsRecord(pageInfo, keyword, startDate, endDate);

        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }
}
