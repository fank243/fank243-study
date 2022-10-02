package com.fank243.study.common.core.web.handler;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.sql.SQLException;
import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fank243.study.common.core.utils.ResultInfo;

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

    @ExceptionHandler(DataAccessException.class)
    public ResultInfo<?> handlerDataAccessException(DataAccessException e) {
        log.error("数据源异常拦截[DataAccessException]：{}", e.getMessage(), e);
        return ResultInfo.error(e.getMessage(), Objects.requireNonNull(e.getRootCause()).getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResultInfo<?> handlerSqlSyntaxErrorException(SQLException e) {
        log.error("数据源异常拦截[SQLException]：{}", e.getMessage(), e);
        return ResultInfo.error(e.getMessage(), e.toString());
    }

}
