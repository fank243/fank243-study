package com.fank243.springboot.admin.controller.logger;

import com.fank243.springboot.admin.service.logger.SysLogService;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.dto.logger.SysLogDTO;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 系统日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 23:20:18
 */
@RequiresRoles(IConsts.RoleType.ROOT)
@RequestMapping("/admin/system/logger/sys")
@Controller
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    @GetMapping("/logIndex")
    public String logIndex() {
        return "/admin/system/logger/sysLogIndex";
    }

    @GetMapping("/logList")
    @ResponseBody
    public LayuiResultInfo logList(PageInfo pageInfo, String keyword, String logType, String logLevel, String logDesc,
        String requestUri, String sessionId, String requestId, Integer responseStatus, Integer resultCode,
        String date) {
        responseStatus = responseStatus == null || responseStatus < 0 ? 0 : responseStatus;
        resultCode = resultCode == null || resultCode < 0 ? 0 : resultCode;
        keyword = StringUtils.trim(keyword);
        logType = StringUtils.trim(logType);
        logLevel = StringUtils.trim(logLevel);
        sessionId = StringUtils.trim(sessionId);
        requestId = StringUtils.trim(requestId);
        requestUri = StringUtils.trim(requestUri);
        date = StringUtils.trim(date);

        String beginTime = "";
        String endTime = "";
        if (StringUtils.isNotBlank(date)) {
            String[] split = date.split("~");
            beginTime = split[0];
            endTime = split[1];
        }

        PageBean<SysLogDTO> pageBean = sysLogService.pageOfLog(pageInfo, keyword, logType, logLevel, logDesc,
            requestUri, sessionId, requestId, responseStatus, resultCode, beginTime, endTime);

        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }
}
