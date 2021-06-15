package com.fank243.study.client.domain.system.dto;

import com.fank243.study.client.domain.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户表
 * 
 * @author FanWeiJie
 * @date 2021-06-07 00:40:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDTO extends PageDTO {

    private String id;

    private String username;

    private String password;
}
