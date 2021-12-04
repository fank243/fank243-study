package com.fank243.study.api.system.vo;

import com.fank243.study.core.base.BaseVO;

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
public class LoginUserVO extends BaseVO {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

}
