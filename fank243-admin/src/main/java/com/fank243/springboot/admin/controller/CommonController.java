package com.fank243.springboot.admin.controller;

import com.fank243.springboot.common.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共控制器
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:23:00
 */
@RequestMapping("/admin/common")
@RestController
public class CommonController {

    /** 发送短信 **/
    @PostMapping("/sendSms")
    public ResultInfo sendSms() {
        return ResultInfo.ok();
    }

    /** 发送邮件 **/
    @PostMapping("/sendEmail")
    public ResultInfo sendEmail() {
        return ResultInfo.ok();
    }

    /** 上传图片 **/
    @PostMapping("/uploadImage")
    public ResultInfo uploadImage() {
        return ResultInfo.ok();
    }

}
