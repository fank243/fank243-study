package com.fank243.springboot.admin.controller;

import com.fank243.springboot.admin.consts.ConfConfig;
import com.fank243.springboot.admin.model.vo.SysUserInfoVO;
import com.fank243.springboot.admin.service.SysRoleService;
import com.fank243.springboot.admin.service.SysUserService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 首页控制器
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:18:40
 */
@Slf4j
@RequestMapping("/admin")
@Controller
public class HomeController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private ConfConfig confConfig;

    /** 控制台 **/
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    /** 首页 **/
    @GetMapping("/console.html")
    public String console() {
        return "admin/console";
    }

    /** 退出登录 **/
    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.html";
    }

    /** 修改密码首页 **/
    @GetMapping("/modify-password")
    public String modifyPassword() {
        return "/admin/modify-password";
    }

    /** 修改密码 **/
    @PostMapping("/modify-password")
    @ResponseBody
    public ResultInfo modifyPassword(String oldPassword, String password, String rePassword) {
        ResultInfo result =
            sysUserService.modifyPassword(ShiroUtils.getActiveUser().getUserId(), oldPassword, password, rePassword);
        if (!result.isSuccess()) {
            log.warn(result.toString());
            return result;
        }

        return ResultInfo.ok().message("密码修改成功，请重新登录");
    }

    /** 管理员编辑个人资料 **/
    @GetMapping("/modify-info")
    public String modifyInfo(Model model) {
        SysUser sysUser = sysUserService.findById(ShiroUtils.getActiveUser().getUserId());

        String roleNames = sysRoleService.findByUserId(sysUser.getId());

        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roleNames", roleNames);
        model.addAttribute("isProd", confConfig.getMode().equalsIgnoreCase(IConsts.MODE_PROD));
        return "/admin/modify-info";
    }

    /** 管理员编辑个人资料 **/
    @PostMapping("/modify-info")
    @ResponseBody
    public ResultInfo modifyInfo(SysUserInfoVO vo) {
        ResultInfo result = sysUserService.modifyInfo(ShiroUtils.getActiveUser().getUserId(), vo);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

}
