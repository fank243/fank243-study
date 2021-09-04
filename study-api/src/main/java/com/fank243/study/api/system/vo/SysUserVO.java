package com.fank243.study.api.system.vo;

import com.fank243.study.core.base.BaseVO;
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
public class SysUserVO extends BaseVO {


    /**
    * 用户名
    */
    private String username;

    /**
    * 登录密码
    */
    private String password;


}
