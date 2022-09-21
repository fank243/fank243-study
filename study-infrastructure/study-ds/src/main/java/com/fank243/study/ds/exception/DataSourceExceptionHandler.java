package com.fank243.study.ds.exception;

import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        log.error("数据源异常拦截[INTERNAL_SERVER_ERROR]：{}", e.getMessage(), e);
        return renderJSON(e.getMessage(), Objects.requireNonNull(e.getRootCause()).getMessage());
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerSqlSyntaxErrorException(SQLSyntaxErrorException e) {
        log.error("数据源异常拦截[SQLSyntaxErrorException]：{}", e.getMessage(), e);
        return renderJSON(e.getMessage(), e.toString());
    }

    private String renderJSON(String message, String error) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        json.put("success", Boolean.FALSE);
        json.put("timestamp", System.currentTimeMillis());
        json.put("payload", "");
        json.put("message", message);
        json.put("error", error);
        return json.toString();
    }
}
