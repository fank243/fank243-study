package com.fank243.springboot.admin.controller.user;

import com.fank243.springboot.admin.controller.base.BaseController;
import com.fank243.springboot.admin.model.Create;
import com.fank243.springboot.admin.model.Update;
import com.fank243.springboot.admin.model.vo.UserVO;
import com.fank243.springboot.admin.service.UserService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.entity.User;
import com.fank243.springboot.core.exception.BizException;
import com.fank243.springboot.core.model.LayuiResultInfo;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户管理
 * 
 * @author FanWeiJie
 * @date 2020-03-25 23:36:47
 */
@Slf4j
@RequestMapping("/admin/user")
@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequiresPermissions("user:query")
    @GetMapping("")
    public String userIndex() {
        return "/admin/user/user";
    }

    @RequiresPermissions("user:query")
    @GetMapping("/userList")
    @ResponseBody
    public LayuiResultInfo userList(PageInfo pageInfo, String keyword, Integer status) {
        keyword = StringUtils.trim(keyword);
        PageBean<User> page = userService.pageOfUser(pageInfo, keyword, status);
        return new LayuiResultInfo(page.getTotalCount(), page.getList());
    }

    @RequiresPermissions("user:create")
    @GetMapping("/add")
    public String add() {
        return "/admin/user/add";
    }

    @RequiresPermissions("user:create")
    @PostMapping("/add")
    @ResponseBody
    public ResultInfo add(@Validated(Create.class) UserVO vo) throws BizException {
        ResultInfo result = userService.add(ShiroUtils.getActiveUser().getUserId(), vo);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("user:update")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long userId, Model model) {
        userId = userId == null || userId <= 0 ? 0 : userId;
        User user = userService.findById(userId);
        if (user == null) {
            return errMsg("用户不存在");
        }

        model.addAttribute("user", user);
        return "/admin/user/modify";
    }

    @RequiresPermissions("user:update")
    @PostMapping("/modify")
    @ResponseBody
    public ResultInfo modify(@Validated(Update.class) UserVO vo) throws BizException {
        ResultInfo result = userService.modify(ShiroUtils.getActiveUser().getUserId(), vo);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("user:update")
    @PutMapping("/modify-status/{id}")
    @ResponseBody
    public ResultInfo modifyStatus(@PathVariable("id") Long userId) {
        ResultInfo result = userService.modifyStatus(ShiroUtils.getActiveUser().getUserId(), userId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("user:update")
    @PutMapping("/login-unlock/{id}")
    @ResponseBody
    public ResultInfo loginUnlock(@PathVariable("id") Long userId) {
        ResultInfo result = userService.loginUnlock(ShiroUtils.getActiveUser().getUserId(), userId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }

    @RequiresPermissions("user:update")
    @PutMapping("/reset-password/{id}")
    @ResponseBody
    public ResultInfo resetPassword(@PathVariable("id") Long userId) {
        ResultInfo result = userService.resetPassword(ShiroUtils.getActiveUser().getUserId(), userId);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }
}
