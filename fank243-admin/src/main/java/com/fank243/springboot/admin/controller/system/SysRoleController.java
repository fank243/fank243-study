package com.fank243.springboot.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.controller.base.BaseController;
import com.fank243.springboot.admin.model.Menu;
import com.fank243.springboot.admin.service.SysPermissionService;
import com.fank243.springboot.admin.service.SysRoleService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.SysLog;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.enums.SysLogType;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:18:40
 */
@Slf4j
@RequiresRoles(value = {IConsts.RoleType.ROOT, IConsts.RoleType.ADMIN}, logical = Logical.OR)
@RequestMapping("/admin/role")
@Controller
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysPermissionService sysPermissionService;

    @RequiresPermissions("role:query")
    @GetMapping("")
    public String role() {
        return "admin/system/role/role";
    }

    @RequiresPermissions("role:query")
    @GetMapping("/role")
    @ResponseBody
    public LayuiResultInfo role(PageInfo pageInfo, String keyword) {
        PageBean<SysRole> page = sysRoleService.pageOfRole(pageInfo, keyword);
        return new LayuiResultInfo(page.getTotalCount(), page.getList());
    }

    @RequiresPermissions("role:create")
    @GetMapping("/add")
    public String add() {
        return "admin/system/role/addOrModify";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResultInfo get(@PathVariable(value = "id") Long roleId) {
        Optional<SysRole> optional = sysRoleService.findById(roleId);
        if (optional.isPresent()) {
            return ResultInfo.ok();
        }
        return ResultInfo.fail();
    }

    @RequiresPermissions("role:update")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable(value = "id") Long roleId, Model model) {
        SysRole sysRole = null;
        Optional<SysRole> optional = sysRoleService.findById(roleId);
        if (optional.isPresent()) {
            sysRole = optional.get();
        }

        model.addAttribute("sysRole", sysRole);
        return "admin/system/role/addOrModify";
    }

    @SysLog(logType = SysLogType.SYS_SET_ROLE, desc = "添加或修改角色")
    @RequiresPermissions({"role:create", "role:update"})
    @PostMapping("/addOrModify")
    @ResponseBody
    public ResultInfo addOrModify(SysRole sysRole) {
        ResultInfo result = sysRoleService.addOrModify(ShiroUtils.getActiveUser().getUserId(), sysRole);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("role:auth")
    @GetMapping("/authorize/{roleId}")
    public String authorize(@PathVariable("roleId") Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "admin/system/role/authorize";
    }

    @ResponseBody
    @GetMapping("/authorize")
    public ResultInfo authorize(Long roleId) {
        // 查询该角色拥有的全部权限
        Optional<SysRole> optional = sysRoleService.findById(roleId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("角色不存在");
        }
        Set<SysPermission> sysPermissions = optional.get().getPermissions();
        List<Long> permissionIds = new ArrayList<>(sysPermissions.size());
        for (SysPermission permission : sysPermissions) {
            permissionIds.add(permission.getId());
        }

        // 查询所有权限菜单
        List<Menu> menuList = sysPermissionService.findPermission();

        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(menuList));

        JSONObject json = new JSONObject();
        // 默认选中权限
        json.put("checkedId", permissionIds);
        // 禁用权限
        json.put("disabledId", "");
        json.put("list", jsonArray);

        return ResultInfo.ok(json);
    }

    @RequiresPermissions("role:auth")
    @PostMapping("/authorize/{roleId}")
    @ResponseBody
    public ResultInfo authorize(@PathVariable("roleId") Long roleId, String permIds) {
        ResultInfo result = sysRoleService.authorize(ShiroUtils.getActiveUser().getUserId(), roleId, permIds);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("role:update")
    @PutMapping("/modify-status/{roleId}")
    @ResponseBody
    public ResultInfo modifyStatus(@PathVariable("roleId") Long roleId) {
        ResultInfo result = sysRoleService.modifyStatus(ShiroUtils.getActiveUser().getUserId(), roleId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

}
