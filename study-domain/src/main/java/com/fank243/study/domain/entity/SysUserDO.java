package com.fank243.study.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户表
 * 
 * @author FanWeiJie
 * @date 2021-06-07 00:40:27
 */
@Data
@TableName("tb_sys_user")
public class SysUserDO implements Serializable {

    private String id;

    private String username;
}
