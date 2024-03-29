package com.github.fank243.study.core.model.error.handler;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.sql.SQLException;
import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.fank243.common.result.ResultInfo;

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
@ConditionalOnWebApplication(type = SERVLET)
public class DataSourceExceptionHandler {

    /** 数据库异常 **/
    @ExceptionHandler(DataAccessException.class)
    public ResultInfo<?> handlerDataAccessException(DataAccessException e) {
        log.error("数据源异常拦截[DataAccessException]：{}", e.getMessage(), e);
        return ResultInfo.err500("数据库连接异常，请稍后再试").error(Objects.requireNonNull(e.getRootCause()).getMessage());
    }

    /** SQL语法错误异常 **/
    @ExceptionHandler(SQLException.class)
    public ResultInfo<?> handlerSqlException(SQLException e) {
        log.error("数据源异常拦截[SQLException]：{}", e.getMessage(), e);
        return ResultInfo.err500("SQL执行异常，请联系管理员").error(e.getMessage());
    }

}
