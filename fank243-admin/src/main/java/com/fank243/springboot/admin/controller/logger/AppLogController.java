package com.fank243.springboot.admin.controller.logger;

import com.fank243.springboot.admin.service.logger.AppLogService;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.dto.logger.AppLogDTO;
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
 * App 日志
 * 
 * @author FanWeiJie
 * @date 2020-07-24 23:20:18
 */
@RequiresRoles(IConsts.RoleType.ROOT)
@RequestMapping("/admin/system/logger/app")
@Controller
public class AppLogController {

    @Resource
    private AppLogService appLogService;

    @GetMapping("/logIndex")
    public String logIndex() {
        return "/admin/system/logger/appLogIndex";
    }

    @GetMapping("/logList")
    @ResponseBody
    public LayuiResultInfo logList(PageInfo pageInfo, String keyword, String logType, String logLevel, String logDesc,
        String deviceType, String deviceNumber, String requestUri, String sessionId, String requestId,
        Integer responseStatus, Integer resultCode, String date) {
        responseStatus = responseStatus == null || responseStatus < 0 ? 0 : responseStatus;
        resultCode = resultCode == null || resultCode < 0 ? 0 : resultCode;
        keyword = StringUtils.trim(keyword);
        logType = StringUtils.trim(logType);
        logLevel = StringUtils.trim(logLevel);
        deviceType = StringUtils.trim(deviceType);
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

        PageBean<AppLogDTO> page = appLogService.pageOfLog(pageInfo, keyword, logType, logLevel, logDesc, deviceType,
            deviceNumber, requestUri, sessionId, requestId, responseStatus, resultCode, beginTime, endTime);

        return new LayuiResultInfo(page.getTotalCount(), page.getList());
    }
}
