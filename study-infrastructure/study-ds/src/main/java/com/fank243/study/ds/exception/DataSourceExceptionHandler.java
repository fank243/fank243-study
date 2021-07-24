package com.fank243.study.ds.exception;

import com.fank243.study.common.enums.ResultCodeEnum;
import com.fank243.study.common.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

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
    public ResultInfo<?> handlerDataAccessException(DataAccessException e) {
        log.error("数据源异常拦截：{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R500.getStatus(), Objects.requireNonNull(e.getRootCause()).getMessage(),
            e.getMessage());
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo<?> handlerSqlSyntaxErrorException(SQLSyntaxErrorException e) {
        log.error("数据源异常拦截：{}", e.getMessage(), e);
        return ResultInfo.error(ResultCodeEnum.R500.getStatus(), e.getLocalizedMessage(), e.getMessage());
    }
}
