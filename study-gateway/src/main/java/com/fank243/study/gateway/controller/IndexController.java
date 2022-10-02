package com.fank243.study.gateway.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fank243.study.common.core.utils.ResultInfo;

/**
 * 首页控制器
 * 
 * @author FanWeiJie
 * @since 2022-02-15 13:54:32
 */
@RequestMapping("")
@RestController
public class IndexController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public ResultInfo<?> index() {
        return ResultInfo.err404();
    }

}
