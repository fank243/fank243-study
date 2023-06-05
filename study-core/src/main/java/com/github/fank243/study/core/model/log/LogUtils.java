package com.github.fank243.study.core.model.log;

import java.util.Date;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.utils.WebUtils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Log Tool
 * 
 * @author FanWeiJie
 * @since 2020-10-25 15:44:35
 */
@Slf4j
public class LogUtils {

    /**
     * 打印日志
     * 
     * @param request HttpServletRequest
     * @param result 响应内容
     */
    public static void printLog(String desc, HttpServletRequest request, ResultInfo<?> result) {
        printLog(desc, request, JSONUtil.toJsonStr(result));
    }

    /**
     * 打印日志
     * 
     * @param request HttpServletRequest
     * @param respBody 响应内容
     */
    public static void printLog(String desc, HttpServletRequest request, String respBody) {
        ReqRespLog reqRespLog = new ReqRespLog();
        reqRespLog.setReqMethod(request.getMethod());
        reqRespLog.setReqUri(request.getRequestURI());
        reqRespLog.setReqHeader(JSONUtil.toJsonStr(WebUtils.getHeader(request)));
        reqRespLog.setReqBody(JSONUtil.toJsonStr(WebUtils.getParams(request)));
        reqRespLog.setReqTime(new Date());
        reqRespLog.setRespBody(respBody);
        reqRespLog.setRespTime(new Date());

        printLog(desc, reqRespLog);
    }

    /**
     * 打印日志
     *
     * @param reqRespLog 请求参数
     */
    public static void printLog(ReqRespLog reqRespLog) {
        printLog("", reqRespLog);
    }

    /**
     * 打印日志
     *
     * @param reqRespLog 请求参数
     */
    public static void printLog(String desc, ReqRespLog reqRespLog) {
        if (log.isDebugEnabled()) {
            if (StrUtil.isNotBlank(desc)) {
                log.debug("请求日志[{}]：{} {} {}", desc, reqRespLog.getReqMethod(), reqRespLog.getReqUri(),
                    JSONUtil.toJsonStr(reqRespLog));
            } else {
                log.debug("请求日志：{} {} {}", reqRespLog.getReqMethod(), reqRespLog.getReqUri(),
                    JSONUtil.toJsonStr(reqRespLog));
            }
        }
    }

}
