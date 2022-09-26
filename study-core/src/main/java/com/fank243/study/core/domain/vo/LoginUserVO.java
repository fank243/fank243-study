package com.fank243.study.core.domain.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
public class LoginUserVO implements Serializable {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

}
