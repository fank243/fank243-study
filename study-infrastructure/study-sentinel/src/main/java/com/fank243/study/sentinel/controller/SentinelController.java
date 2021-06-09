//package com.fank243.study.sentinel.controller;
//
//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.fank243.study.sentinel.exception.SentinelFallback;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 控制器
// *
// * @author FanWeiJie
// * @date 2021-06-05 20:16:57
// */
//@RequestMapping("/sentinel")
//@RestController
//public class SentinelController {
//
//    /**
//     * 官方文档：@see <a href=
//     * "https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81#sentinelresource-%E6%B3%A8%E8%A7%A3">注解支持</a>
//     *
//     * 测试时请在Sentinel 控制台添加相关流控、降级规则
//     *
//     * SentinelResource 注解参数说明
//     *
//     * value 资源名称，直接在sentinel控制台显示，默认为：类全限定名:方法名(参数类型全限定名,...)，示例：com.fank243.study.sentinel.controller.SentinelController:hello(java.lang.String)
//     *
//     * blockHandlerClass 当出现BlockException调用的处理类
//     *
//     * blockHandler 当出现BlockException调用的处理方法，该方法必须为public static且返回返回值需要与原方法一致，方法参数列表也需一致，并可在最后追加一个Throwable类型参数
//     *
//     * fallbackClass 当出现异常时调用的处理类
//     *
//     * fallback 当出现异常时调用的处理方法，优先级高于defaultFallback，该方法必须为public static且返回返回值需要与原方法一致，方法参数列表也需一致，并可在最后追加一个Throwable类型参数
//     *
//     * defaultFallback 通用异常处理方法，该方法必须为public static且返回返回值需要与原方法一致，参数列表可以为空或者添加一个Throwable类型参数
//     */
//    @SentinelResource(fallbackClass = SentinelFallback.class, defaultFallback = "handlerException")
//    @GetMapping("/hello")
//    public String hello(String username) {
//        int i = 10 / 0;
//        return "hello world :" + username;
//    }
//}
