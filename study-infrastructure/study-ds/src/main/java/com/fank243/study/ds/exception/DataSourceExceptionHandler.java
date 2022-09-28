package com.fank243.study.ds.exception;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson2.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源异常拦截
 *
 * @author FanWeiJie
 * @since 2021-06-16 22:20:34
 */
@Slf4j
@Order(-1)
@RestControllerAdvice
public class DataSourceExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerDataAccessException(DataAccessException e) {
        log.error("数据源异常拦截[DataAccessException]：{}", e.getMessage(), e);
        return renderJson(e.getMessage(), Objects.requireNonNull(e.getRootCause()).getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerSqlSyntaxErrorException(SQLException e) {
        log.error("数据源异常拦截[SQLException]：{}", e.getMessage(), e);
        return renderJson(e.getMessage(), e.toString());
    }

    private String renderJson(String message, String error) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        json.put("success", Boolean.FALSE);
        json.put("timestamp", System.currentTimeMillis());
        json.put("payload", "");
        json.put("message", message);
        json.put("error", error);

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletResponse response = attributes.getResponse();
            assert response != null;
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            try (Writer writer = response.getWriter()) {
                writer.write(json.toString());
                writer.flush();
            } catch (IOException ignored) {
            }
            return null;
        }
        return json.toString();
    }
}
