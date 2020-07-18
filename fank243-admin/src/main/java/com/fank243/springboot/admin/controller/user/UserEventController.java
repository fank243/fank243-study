package com.fank243.springboot.admin.controller.user;

import com.fank243.springboot.admin.service.UserEventService;
import com.fank243.springboot.core.dto.UserEventDTO;
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
 * 用户操作事件
 * 
 * @author FanWeiJie
 * @date 2020-03-25 22:05:59
 */
@RequestMapping("/admin/user-event")
@Controller
public class UserEventController {

    @Resource
    private UserEventService userEventService;

    @RequiresPermissions("user-event:query")
    @GetMapping("")
    public String event() {
        return "/admin/system/user-event/event";
    }

    @RequiresPermissions("user-event:query")
    @ResponseBody
    @GetMapping("/event")
    public LayuiResultInfo event(PageInfo pageInfo, String eventType, String beginDate, String endDate) {
        PageBean<UserEventDTO> page = userEventService.pageOfEvent(pageInfo, eventType, beginDate, endDate);
        return new LayuiResultInfo(page.getTotalCount(), page.getList());
    }
}
