/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.core.model.error.handler;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.sql.SQLException;
import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.fank243.kong.tool.result.ResultInfo;

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
