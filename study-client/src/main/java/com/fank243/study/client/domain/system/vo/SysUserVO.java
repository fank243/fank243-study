package com.fank243.study.client.domain.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户表
 * 
 * @author FanWeiJie
 * @date 2021-06-07 00:40:27
 */
@Data
public class SysUserVO implements Serializable {

    private String id;

    private String username;

    private String password;
}
