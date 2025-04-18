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

package com.github.fank243.kong.core.model.error;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.model.log.LogUtils;
import com.github.fank243.kong.core.properties.KongProperties;

import cn.hutool.core.bean.BeanUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

/**
 * 全局异常处理器
 *
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
@ConditionalOnWebApplication(type = SERVLET)
public class DefaultErrorController extends BasicErrorController {

    public DefaultErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus httpStatus = getStatus(request);
        // 转换404异常
        if (HttpStatus.NOT_FOUND.value() == httpStatus.value()) {
            Map<String, Object> errMap = BeanUtil.beanToMap(ResultInfo.err404());
            LogUtils.printLog(DefaultErrorController.class.getSimpleName(), request, ResultInfo.err404());
            return ResponseEntity.status(httpStatus).body(errMap);
        }
        ResultInfo<Object> result = ResultInfo.error(httpStatus.value(), httpStatus.getReasonPhrase());
        Map<String, Object> errMap = BeanUtil.beanToMap(result);
        LogUtils.printLog(DefaultErrorController.class.getSimpleName(), request, result);
        return ResponseEntity.status(httpStatus).body(errMap);
    }

    @SneakyThrows
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus httpStatus = getStatus(request);
        LogUtils.printLog(DefaultErrorController.class.getSimpleName(), request,
            ResultInfo.error(httpStatus.value(), httpStatus.getReasonPhrase()));

        response.setStatus(httpStatus.value());
        response.sendRedirect(KongProperties.baseUrl + "/error/" + httpStatus.value());
        return null;
    }

}
