package com.fank243.study.core.web.exception;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson2.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Slf4j
@Order(0)
@RestControllerAdvice
public class StudyExceptionHandler {

    /** 业务异常 */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handlerBizException(HttpServletResponse response, BizException e) {
        log.error("全局异常拦截[BizException]:{}", e.getLocalizedMessage(), e);
        renderJson(response, e.getMessage(), e.toString());
    }

    private void renderJson(HttpServletResponse response, String message, String error) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        json.put("success", Boolean.FALSE);
        json.put("timestamp", System.currentTimeMillis());
        json.put("payload", "");
        json.put("message", message);
        json.put("error", error);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        try (Writer writer = response.getWriter()) {
            writer.write(json.toString());
            writer.flush();
        } catch (IOException ignored) {
        }
    }
}
