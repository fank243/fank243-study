package com.fank243.study.client.context.system;

import lombok.Data;

/**
 * 系统用户表
 * 
 * @author FanWeiJie
 * @date 2021-06-07 00:40:27
 */
@Data
public class SysUserContext {

    private String id;

    private String username;

    private String password;
}
