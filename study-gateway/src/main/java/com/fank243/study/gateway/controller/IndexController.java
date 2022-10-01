package com.fank243.study.gateway.controller;

import com.fank243.study.common.core.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 * 
 * @author FanWeiJie
 * @since 2022-02-15 13:54:32
 */
@RequestMapping("")
@RestController
public class IndexController {

    @RequestMapping("/")
    public ResultInfo<?> index() {
        return ResultInfo.err404();
    }
}
