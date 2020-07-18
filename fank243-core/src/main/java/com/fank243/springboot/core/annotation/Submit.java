package com.fank243.springboot.core.annotation;

import java.lang.annotation.*;

/**
 * 基于google guva 和 表单令牌策略对form表单以及ajax请求限流以及放重复提交
 *
 * @author FanWeiJie
 * @date 2020-04-11 21:34:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Submit {
    /**
     * 是否仅允许表单提交一次，默认：否
     * 
     * 如果该参数为false，则不限制表单重复提交，仅限制每次提交表单需要间隔一定的时间
     *
     * 如果该参数为true，则限制表单仅可以提交一次，需要配合 freemarker 自定义标签使用
     *
     * 1.在需要提交的表单页面增加自定义标签：<@authenticityToken/>
     *
     * 2.在接收表单后台接口增加注解：@Submit(repeat = true)
     */
    boolean repeat() default false;
}
