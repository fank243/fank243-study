package com.fank243.study.gateway.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.study.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_sys_user")
public class SysUserEntity extends BaseEntity {

    /** 用户名 */
    private String username;

    /** 登录密码  */
    private String password;

    /** 是否已删除 */
    private Boolean isDeleted;

}
