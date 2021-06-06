package com.fank243.study.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 * 
 * @author FanWeiJie
 * @date 2021-06-04 23:36:59
 */
@RequestMapping
@RestController
public class NacosController {

    @Value("${test}")
    private String test;
    @Value("${test2}")
    private String test2;

    @GetMapping("/hello")
    public String hello() {
        return "hello:" + test;
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello:" + test2;
    }
}
