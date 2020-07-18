package com.fank243.springboot.app.controller;

import com.fank243.springboot.app.annotation.Login;
import com.fank243.springboot.app.model.AppRequest;
import com.fank243.springboot.app.service.UserService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 首页
 * 
 * @author FanWeiJie
 * @date 2020-03-29 21:54:38
 */
@RequestMapping("/app/index")
@RestController
public class IndexController {

    @Resource
    private UserService userService;

    @PostMapping("")
    public ResultInfo index(@Validated AppRequest appRequest) {
        User user = userService.findById(2L);
        return ResultInfo.ok(user);
    }

    @Login
    @PostMapping("/index2")
    public ResultInfo index2(@Validated AppRequest appRequest) {
        return ResultInfo.ok();
    }

    @PostMapping("index3")
    public ResultInfo index3(@Validated AppRequest appRequest) {
        return ResultInfo.ok();
    }
}
