package com.fank243.springboot.admin.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.service.SysConfigService;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.annotation.SysLog;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.SysConfig;
import com.fank243.springboot.core.enums.SysLogType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 站点配置
 * 
 * @author FanWeiJie
 * @date 2020-03-24 15:15:11
 */
@Slf4j
@RequiresRoles(value = IConsts.RoleType.ROOT)
@RequestMapping("/admin/site")
@Controller
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    @RequiresPermissions({"site:query"})
    @GetMapping("")
    public String site(Model model) {
        List<SysConfig> systemConfigList = sysConfigService.findAll();

        JSONObject json = new JSONObject();
        for (SysConfig sysConfig : systemConfigList) {
            json.put(sysConfig.getSysKey(), sysConfig.getSysValue());
        }

        model.addAttribute("sysConfig", json);
        return "/admin/system/site/site";
    }

    @SysLog(logType = SysLogType.SYS_SET_SITE, desc = "修改站点配置")
    @RequiresPermissions({"site:update"})
    @ResponseBody
    @PostMapping(value = "/modify", produces = "application/json;charset=UTF-8")
    public ResultInfo modify(@RequestBody String body) {
        ResultInfo result = sysConfigService.modify(ShiroUtils.getActiveUser().getUserId(), body);
        if (!result.isSuccess()) {
            log.warn(result.toString());
        }
        return result;
    }
}
