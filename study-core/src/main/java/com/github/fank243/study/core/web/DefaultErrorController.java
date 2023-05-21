package com.github.fank243.study.core.web;

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

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.utils.WebUtils;

import cn.hutool.core.bean.BeanUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            return ResponseEntity.status(httpStatus).body(errMap);
        }
        Map<String, Object> errMap =
            BeanUtil.beanToMap(ResultInfo.error(httpStatus.value(), httpStatus.getReasonPhrase()));
        return ResponseEntity.status(httpStatus).body(errMap);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus httpStatus = getStatus(request);
        // 转换404异常
        if (HttpStatus.NOT_FOUND.value() == httpStatus.value()) {
            Map<String, Object> errMap = BeanUtil.beanToMap(ResultInfo.err404());
            WebUtils.renderJson(response, errMap);
            return null;
        }
        WebUtils.renderJson(response, ResultInfo.error(httpStatus.value(), httpStatus.getReasonPhrase()));
        return null;
    }

}
