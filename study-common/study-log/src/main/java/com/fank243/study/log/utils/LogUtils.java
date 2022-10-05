package com.fank243.study.log.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.fank243.study.common.core.utils.WebUtils;
import com.fank243.study.log.enums.LogTypeEnum;
import com.fank243.study.support.domain.dto.LogDTO;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

/**
 * 系统日志工具类
 *
 * @author L.cm
 */
@UtilityClass
public class LogUtils {

    public LogDTO getSysLog() {
        HttpServletRequest request = WebUtils.getRequest();
        assert request != null;

        LogDTO sysLog = new LogDTO();
        sysLog.setUserId(StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : "");
        sysLog.setLogType(LogTypeEnum.NORMAL.getLogType());
        sysLog.setClientIp(ServletUtil.getClientIP(request));
        sysLog.setReqUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setContentType(request.getContentType());
        sysLog.setReqMethod(request.getMethod());
        sysLog.setReqHeader(JSONUtil.toJsonStr(WebUtils.getHeader(request)));
        sysLog.setReqTime(new Date());
        return sysLog;
    }

}
