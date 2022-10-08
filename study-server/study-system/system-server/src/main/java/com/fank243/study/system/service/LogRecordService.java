package com.fank243.study.system.service;

import java.util.List;
import java.util.stream.Collectors;

import com.fank243.study.system.domain.vo.SysUserVO;

import cn.hutool.extra.spring.SpringUtil;

/**
 * 自定义处理函数
 * 
 * @author FanWeiJie
 * @since 2022-10-07 19:46:17
 */
public class LogRecordService {

    private final static SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);

    public static String findUsername(String[] ids) {
        List<SysUserVO> sysUserList = sysUserService.findByUserIdIn(List.of(ids));
        return sysUserList.stream().map(SysUserVO::getUsername).collect(Collectors.joining("、"));
    }
}
