package com.fank243.study.system.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.study.core.model.entity.BaseEntity;
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
@TableName("tb_sys_user")
public class SysUserDTO extends BaseEntity {

    private String id;

    private String username;

    private String password;
}
