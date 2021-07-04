package com.fank243.study.client.system;

import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.base.BaseController;
import com.fank243.study.core.utils.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author FanWeiJie
 * @since 2021-07-03 21:43:11
 */
@RequestMapping("/system/admin")
@RestController
public class SysUserCtrl extends BaseController {

    @Resource
    private ISysUserClient sysUserClient;

    @GetMapping("/getById/{id}")
    public ResultInfo<SysUserVO> getById(@PathVariable("id") String userId) {
        return sysUserClient.getById(userId);
    }
}