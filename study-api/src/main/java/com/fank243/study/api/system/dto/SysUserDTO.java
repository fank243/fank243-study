package com.fank243.study.api.system.dto;

import com.fank243.study.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* <p>
* 系统管理员表
* </p>
*
* @author FanWeiJie
* @since 2021-09-03
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends BaseDTO {


    /**
    * 用户名
    */
    private String username;

    /**
    * 登录密码
    */
    private String password;


}
