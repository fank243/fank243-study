package com.github.fank243.study.core.model.validation;

import jakarta.validation.groups.Default;

/**
 * Hibernate Validator
 * 
 * @author FanWeiJie
 * @since 2021-08-28 18:50:00
 */
public interface ValidatorGroup {

    /** 新增 **/
    interface Create extends Default {}

    /** 修改 **/
    interface Modify extends Default {}

    /** 登录 **/
    interface Login extends Default {}

    /** 登录 > 手机号码 **/
    interface LoginMobile extends Login {}

    /** 登录 > 用户名 **/
    interface LoginUsername extends Login {}
}
