package com.fank243.springboot.admin.controller.system;

import com.fank243.springboot.admin.service.SysUserEventService;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.dto.SysUserEventDTO;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 管理员操作事件
 * 
 * @author FanWeiJie
 * @date 2020-03-25 22:05:59
 */
@RequiresRoles(value = {IConsts.RoleType.ROOT, IConsts.RoleType.ADMIN}, logical = Logical.OR)
@RequestMapping("/admin/sysuser-event")
@Controller
public class SysUserEventController {

    @Resource
    private SysUserEventService sysUserEventService;

    @RequiresPermissions("sysuser-event:query")
    @GetMapping("")
    public String event() {
        return "/admin/system/sysuser-event/event";
    }

    @RequiresPermissions("sysuser-event:query")
    @ResponseBody
    @GetMapping("/event")
    public LayuiResultInfo event(PageInfo pageInfo, String eventType, String beginDate, String endDate) {
        PageBean<SysUserEventDTO> page = sysUserEventService.pageOfEvent(pageInfo, eventType, beginDate, endDate);
        return new LayuiResultInfo(page.getTotalCount(), page.getList());
    }
}
