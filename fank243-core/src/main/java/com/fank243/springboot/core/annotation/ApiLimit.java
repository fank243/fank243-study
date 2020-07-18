package com.fank243.springboot.core.annotation;

import java.lang.annotation.*;

/**
 * 并发限流，主要针对App API 接口做的
 * 
 * 限流策略主要有两种，分别是：IP、deviceNumber
 * 
 * IP比较好理解就是每个IP单位时间内请求同一接口次数，超过了就返回错误提示
 * 
 * deviceNumber策略就是基于某个App的频繁请求起作用的，主要是防止App用户的频繁点击导致数据库同一时间段内新增多条记录或者修改的时候重复执行
 * 
 * 在需要限流的接口上加上注解，perSecond参数不可少，否则不限流
 * 
 * perSecond参数一定要参照注释谨慎使用，否则会造成接口性能问题甚至接口超时崩溃问题
 * 
 * 建议仅用在发送短信、电邮、新增、修改等操作类接口上
 *
 * @author FanWeiJie
 * @date 2019-05-22 16:00:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApiLimit {
    /**
     * 每秒钟可以放入令牌桶的数量，默认最大值不限流
     * 
     * IP限流策略不在阐述了，下面主要说明基于deviceNumber策略限流
     * 
     * 这里是通过App端传递的<code>deviceNumber</code>参数做限流的
     * 
     * 因此这里的值针对的是某一个App在每秒内能够请求接口的次数，超过限制就被限流了，返回错误提示
     * 
     * 示例：发送短信、电邮
     * 
     * 由于1min内只能发送一条短信，但是为了防止出现网络波动原因导致请求短信接口失败，我们限制app在5s内只能请求一次接口
     * 
     * 那么这里的这个值就设置为：0.2，为什么是0.2呢，因为这个值指的是（1秒钟）可以获取到的令牌数量
     * 
     * 因此计算公式 = 1 / 5 = 0.2
     */
    double perSecond() default Double.MAX_VALUE;

    /**
     * 限流策略方式指定，默认为：deviceNumber策略
     * 
     * 如果需要通过IP限流的，则需要自行指定limitType为：IP，否则该参数可以不传
     */
    LimitType limitType() default LimitType.CUSTOMER;

    enum LimitType {
        /** 自定义key */
        CUSTOMER,
        /** 根据请求者IP */
        IP
    }

}
