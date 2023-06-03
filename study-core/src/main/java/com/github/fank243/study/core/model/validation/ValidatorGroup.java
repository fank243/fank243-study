package com.github.fank243.study.core.model.validation;

/**
 * Hibernate Validator
 * 
 * @author FanWeiJie
 * @since 2021-08-28 18:50:00
 */
public interface ValidatorGroup {

    /** 新增 **/
    interface Create {}

    /** 修改 **/
    interface Modify {}

    /** 登录 **/
    interface Login {}

    /** 登录 > 手机号码 **/
    interface LoginMobile {}

    /** 登录 > 用户名 **/
    interface LoginUsername {}
}
