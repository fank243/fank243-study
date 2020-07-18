package com.fank243.springboot.admin.controller.system;

import com.fank243.springboot.admin.controller.base.BaseController;
import com.fank243.springboot.admin.model.Create;
import com.fank243.springboot.admin.model.Update;
import com.fank243.springboot.admin.model.vo.SysPermissionVO;
import com.fank243.springboot.admin.service.SysPermissionService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.dto.SysPermissionDTO;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 权限
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:18:40
 */
@Slf4j
@RequiresRoles(value = IConsts.RoleType.ROOT, logical = Logical.OR)
@RequestMapping("/admin/permission")
@Controller
public class SysPermissionController extends BaseController {

    @Resource
    private SysPermissionService sysPermissionService;

    /** 首页 **/
    @RequiresPermissions("perm:query")
    @GetMapping("")
    public String permissionIndex(Model model) {
        // 查找所有一级菜单
        List<SysPermission> permissionList = sysPermissionService.findByPid(0L);

        model.addAttribute("permissionList", permissionList);
        return "admin/system/permission/permission";
    }

    @RequiresPermissions("perm:query")
    @GetMapping("/permissionList")
    @ResponseBody
    public LayuiResultInfo permissionList(PageInfo pageInfo, Long pid, Long spid, String keyword) {
        pid = pid == null || pid <= 0 ? 0 : pid;
        spid = spid == null || spid <= 0 ? 0 : spid;
        pid = spid > 0 ? spid : pid;

        PageBean<SysPermissionDTO> page = sysPermissionService.pageOfPermission(pageInfo, pid, keyword);
        return new LayuiResultInfo(page.getTotalCount(), page.getList());
    }

    @RequiresPermissions("perm:create")
    @GetMapping("/add")
    public String add(Model model) {
        // 查询所有一级菜单
        List<SysPermission> sysPermissionList = sysPermissionService.findByPid(0L);

        model.addAttribute("permissionList", sysPermissionList);
        return "admin/system/permission/add";
    }

    @RequiresPermissions(value = "perm:create", logical = Logical.OR)
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo add(@Validated(Create.class) SysPermissionVO vo) {
        ResultInfo result = sysPermissionService.add(ShiroUtils.getActiveUser().getUserId(), vo);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("perm:update")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable(value = "id") Long permissionId, Model model) {
        SysPermission sysPermission;
        Optional<SysPermission> optional = sysPermissionService.findById(permissionId);
        if (optional.isEmpty()) {
            return errMsg("权限不存在");
        }
        sysPermission = optional.get();

        List<Long> list = new ArrayList<>(3);
        sysPermissionService.findAllPid(list, sysPermission);
        // 排序
        Collections.sort(list);
        switch (list.size()) {
            // 一级菜单
            case 1:
                model.addAttribute("sid0", sysPermission.getId());
                model.addAttribute("permissionList0", sysPermissionService.findByPid(0L));
                break;

            // 二级菜单
            case 2:
                model.addAttribute("sid0", sysPermission.getPid());
                model.addAttribute("permissionList0", sysPermissionService.findByPid(0L));

                model.addAttribute("sid1", sysPermission.getId());
                model.addAttribute("permissionList1", sysPermissionService.findByPid(list.get(1)));
                break;

            // 三级菜单
            case 3:
                // 获取一级菜单选中
                optional = sysPermissionService.findById(list.get(1));
                SysPermission permission = new SysPermission();
                if (optional.isPresent()) {
                    permission = optional.get();
                }

                model.addAttribute("sid0", permission.getId());
                model.addAttribute("permissionList0", sysPermissionService.findByPid(0L));

                model.addAttribute("sid1", sysPermission.getPid());
                model.addAttribute("permissionList1", sysPermissionService.findByPid(list.get(1)));

                model.addAttribute("sid2", sysPermission.getId());
                model.addAttribute("permissionList2", sysPermissionService.findByPid(list.get(2)));
                break;

            // 三级菜单
            default:
                // 获取一级菜单选中
                optional = sysPermissionService.findById(list.get(1));
                permission = new SysPermission();
                if (optional.isPresent()) {
                    permission = optional.get();
                }

                model.addAttribute("sid0", permission.getId());
                model.addAttribute("permissionList0", sysPermissionService.findByPid(0L));

                optional = sysPermissionService.findById(list.get(2));
                permission = new SysPermission();
                if (optional.isPresent()) {
                    permission = optional.get();
                }

                model.addAttribute("sid1", permission.getId());
                model.addAttribute("permissionList1", sysPermissionService.findByPid(list.get(1)));

                model.addAttribute("sid2", sysPermission.getPid());
                model.addAttribute("permissionList2", sysPermissionService.findByPid(list.get(2)));
                break;
        }

        model.addAttribute("permission", sysPermission);
        return "admin/system/permission/modify";
    }

    @RequiresPermissions(value = "perm:update", logical = Logical.OR)
    @PostMapping("/modify")
    @ResponseBody
    public ResultInfo modify(@Validated(Update.class) SysPermissionVO vo) {
        ResultInfo result = sysPermissionService.modify(ShiroUtils.getActiveUser().getUserId(), vo);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("perm:update")
    @PutMapping("/modify-status/{id}")
    @ResponseBody
    public ResultInfo modifyStatus(@PathVariable("id") Long permissionId) {
        ResultInfo result = sysPermissionService.modifyStatus(ShiroUtils.getActiveUser().getUserId(), permissionId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("perm:update")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResultInfo delete(@PathVariable("id") Long permissionId) {
        ResultInfo result = sysPermissionService.delete(ShiroUtils.getActiveUser().getUserId(), permissionId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    /** 获取角色信息 **/
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResultInfo get(@PathVariable(value = "id") Long roleId) {
        Optional<SysPermission> optional = sysPermissionService.findById(roleId);
        if (optional.isPresent()) {
            return ResultInfo.ok();
        }
        return ResultInfo.fail();
    }

    /** 获取子菜单 **/
    @GetMapping("/getChildMenu/{pid}")
    @ResponseBody
    public ResultInfo getChildMenu(@PathVariable(value = "pid") Long pid) {
        List<SysPermission> permissionList = sysPermissionService.findByPid(pid);
        return ResultInfo.ok(permissionList);
    }

}
