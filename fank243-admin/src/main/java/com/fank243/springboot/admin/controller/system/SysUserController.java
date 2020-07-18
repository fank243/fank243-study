package com.fank243.springboot.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.service.SysRoleService;
import com.fank243.springboot.admin.service.SysUserService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:18:40
 */
@Slf4j
@RequiresRoles(value = {IConsts.RoleType.ROOT, IConsts.RoleType.ADMIN}, logical = Logical.OR)
@RequestMapping("/admin/sysuser")
@Controller
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;

    @RequiresPermissions("sysuser:query")
    @GetMapping("")
    public String sysUserIndex() {
        return "admin/system/sysuser/sysuser";
    }

    @RequiresPermissions("sysuser:query")
    @GetMapping("/sysUserList")
    @ResponseBody
    public LayuiResultInfo sysUserList(PageInfo pageInfo, String keyword, Integer status) {
        keyword = StringUtils.trim(keyword);
        PageBean<SysUser> pageBean = sysUserService.pageOfSysUser(pageInfo, keyword, status);
        return new LayuiResultInfo(pageBean.getTotalCount(), pageBean.getList());
    }

    @RequiresPermissions("sysuser:create")
    @GetMapping("/add")
    public String add(Model model) {
        // 角色列表
        List<SysRole> sysRoles = sysRoleService.findByAvailableIsTrue();

        JSONArray jsonArray = new JSONArray();
        for (SysRole sysRole : sysRoles) {
            JSONObject json = new JSONObject();
            json.put("name", sysRole.getDescription());
            json.put("value", sysRole.getId());
            jsonArray.add(json);
        }

        model.addAttribute("sysRoles", jsonArray.toString());
        return "admin/system/sysuser/addOrModify";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResultInfo get(@PathVariable(value = "id") Long roleId) {
        SysUser sysUser = sysUserService.findById(roleId);
        if (sysUser == null) {
            return ResultInfo.fail();
        }
        return ResultInfo.ok();
    }

    @RequiresPermissions("sysuser:update")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable(value = "id") Long sysUserId, Model model) {
        SysUser sysUser = sysUserService.findById(sysUserId);

        // 角色列表
        List<SysRole> sysRoles = sysRoleService.findByAvailableIsTrue();

        JSONArray jsonArray = new JSONArray();
        for (SysRole sysRole : sysRoles) {
            JSONObject json = new JSONObject();
            json.put("name", sysRole.getDescription());
            json.put("value", sysRole.getId());

            Set<SysRole> roles = sysUser.getRoles();
            if (roles != null) {
                for (SysRole role : roles) {
                    if (sysRole.getId().equals(role.getId())) {
                        json.put("selected", true);
                    }
                }
            }
            jsonArray.add(json);
        }

        model.addAttribute("sysUser", sysUser);
        model.addAttribute("sysRoles", jsonArray.toString());
        return "admin/system/sysuser/addOrModify";
    }

    @RequiresPermissions(value = {"sysuser:create", "sysuser:update"}, logical = Logical.OR)
    @PostMapping("/addOrModify")
    @ResponseBody
    public ResultInfo addOrModify(SysUser sysUser, String roleIds) {
        ResultInfo result = sysUserService.addOrModify(ShiroUtils.getActiveUser().getUserId(), sysUser, roleIds);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions(value = "sysuser:update")
    @PutMapping("/modify-status/{id}")
    @ResponseBody
    public ResultInfo modifyStatus(@PathVariable("id") Long sysUserId) {
        ResultInfo result = sysUserService.modifyStatus(ShiroUtils.getActiveUser().getUserId(), sysUserId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions(value = "sysuser:update")
    @PutMapping("/login-unlock/{id}")
    @ResponseBody
    public ResultInfo loginUnlock(@PathVariable("id") Long sysUserId) {
        ResultInfo result = sysUserService.loginUnlock(ShiroUtils.getActiveUser().getUserId(), sysUserId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions(value = "sysuser:update")
    @PutMapping("/reset-password/{id}")
    @ResponseBody
    public ResultInfo resetPassword(@PathVariable("id") Long sysUserId) {
        ResultInfo result = sysUserService.resetPassword(ShiroUtils.getActiveUser().getUserId(), sysUserId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions(value = "sysuser:delete")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResultInfo delete(@PathVariable("id") Long sysUserId) {
        ResultInfo result = sysUserService.delete(ShiroUtils.getActiveUser().getUserId(), sysUserId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }
}
