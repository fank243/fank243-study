package com.fank243.springboot.admin.controller;

import com.fank243.springboot.admin.model.ActiveUser;
import com.fank243.springboot.admin.shiro.MyShiroToken;
import com.fank243.springboot.admin.shiro.ShiroToken;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.redis.RedisKey;
import com.fank243.springboot.common.utils.CacheUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.SysLog;
import com.fank243.springboot.core.consts.SysKey;
import com.fank243.springboot.core.enums.SysLogType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 引导控制器
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:18:40
 */
@Slf4j
@RequestMapping("")
@Controller
public class IndexController {

    /** 登录页面 **/
    @GetMapping({"/", "/login.html"})
    public String loginHtml(Model model) {
        ActiveUser currAdmin = ShiroUtils.getActiveUser();
        if (currAdmin != null) {
            return "redirect:/admin/console.html";
        }

        // 站点信息
        String siteName = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.SITE_NAME) + "";
        model.addAttribute("siteName", siteName);
        // 版权
        String copyright = CacheUtils.hashGet(RedisKey.SYS_CONFIG, SysKey.SITE_COPYRIGHT) + "";
        model.addAttribute("copyright", copyright);
        return "login";
    }

    /** 登录 **/
    @SysLog(logType = SysLogType.LOGIN, desc = "登录系统")
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(ShiroToken shiroToken) {
        Subject subject = SecurityUtils.getSubject();
        try {
            MyShiroToken myShiroToken = new MyShiroToken(shiroToken);
            subject.login(myShiroToken);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return ResultInfo.fail("账号或密码错误");
        } catch (DisabledAccountException e) {
            return ResultInfo.fail("账号已被禁用，请联系管理员");
        } catch (Throwable e) {
            return ResultInfo.fail(e.getMessage());
        }

        return ResultInfo.ok();
    }
}
