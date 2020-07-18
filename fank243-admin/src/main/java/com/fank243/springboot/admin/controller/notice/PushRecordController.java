package com.fank243.springboot.admin.controller.notice;

import com.fank243.springboot.admin.service.notice.PushRecordService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.dto.PushRecordDTO;
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
 * 推送通知发送记录
 * 
 * @author FanWeiJie
 * @date 2020-03-26 22:56:51
 */
@RequestMapping("/admin/pushrecord")
@Controller
public class PushRecordController {

    @Resource
    private PushRecordService pushRecordService;

    @RequiresPermissions("pushrecord:query")
    @GetMapping("")
    public String pushrecord() {
        return "/admin/notice/pushrecord";
    }

    @ResponseBody
    @GetMapping("/pushrecord")
    public LayuiResultInfo pushrecord(PageInfo pageInfo, String username, String startDate, String endDate) {

        PageBean<PushRecordDTO> pageBean = pushRecordService.pageOfPushRecord(pageInfo, username, startDate, endDate);

        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }
}
