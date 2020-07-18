package com.fank243.springboot.app.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.app.annotation.Login;
import com.fank243.springboot.app.consts.AppConfig;
import com.fank243.springboot.app.model.AppRequest;
import com.fank243.springboot.app.service.UserService;
import com.fank243.springboot.app.service.component.AppService;
import com.fank243.springboot.app.service.component.RedisService;
import com.fank243.springboot.app.utils.WebUtils;
import com.fank243.springboot.common.encrypt.RsaUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.consts.RedisKey;
import com.fank243.springboot.core.consts.SysKey;
import com.fank243.springboot.core.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * App 请求拦截
 * 
 * 全局优先级：2
 * 
 * @author FanWeiJie
 * @date 2020-04-01 20:13:13
 */
@Slf4j
@Aspect
@Component
public class AppAop {

    @Resource
    private AppService appService;
    @Resource
    private RedisService redisService;
    @Resource
    private AppConfig appConfig;
    @Resource
    private UserService userService;

    @Pointcut("execution (public * com.fank243.springboot.app.controller.*.*(..))")
    public void point() {}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = WebUtils.getRequest();
        if (log.isDebugEnabled()) {
            log.debug("{} [{}] body[{}] header[{}]", request.getRequestURI(), WebUtils.getIp(request),
                WebUtils.getBody(request), WebUtils.getHeader(request));
        }
        // 防爬虫
        ResultInfo result = appService.filterReptile(request.getHeader("user-agent"));
        if (!result.isSuccess()) {
            return result;
        }

        String accessToken = request.getHeader(IConsts.ACCESS_TOKEN);
        String redisToken = redisService.hget(RedisKey.SYS_CONFIG, SysKey.YAPI_ACCESS_TOKEN) + "";
        // 是否需要对请求、返回参数加解密
        boolean isNeed = !(StringUtils.isNotBlank(accessToken) && accessToken.equalsIgnoreCase(redisToken));

        // 解析请求参数并解密payload参数
        result = preHandle(joinPoint.getArgs(), joinPoint.getSignature(), isNeed);
        if (!result.isSuccess()) {
            return result;
        }
        Object[] args = (Object[])result.getPayload();

        // 调用接口并解析返回参数
        return postHandle(joinPoint.proceed(args), isNeed);
    }

    /**
     * 前置拦截，解析请求参数并解密payload参数
     * 
     * @param args 请求参数
     * @param signature 目标方法签名
     * @param isNeed 是否需要对请求、返回参数加解密
     * @return 解密后的参数
     */
    private ResultInfo preHandle(Object[] args, Signature signature, boolean isNeed) {
        // 验签及解密
        AppRequest appRequest = (AppRequest)args[0];
        ResultInfo result = appService.validateAndDecrypt(appRequest, isNeed);
        if (!result.isSuccess()) {
            log.warn(result.toString());
            return result;
        }
        MethodSignature methodSignature = (MethodSignature)signature;
        Login login = methodSignature.getMethod().getAnnotation(Login.class);
        // 接口要求必须传递userId参数
        Object payload = result.getPayload();
        if (login != null) {
            if (payload == null) {
                return ResultInfo.notLogin();
            }
            JSONObject json = JSON.parseObject(payload + "");
            if (!json.containsKey("userId")) {
                return ResultInfo.notLogin();
            }
            // 验证用户是否有效
            User user = userService.findAvailableById(json.getLongValue("userId"));
            if (user == null) {
                return ResultInfo.fail("账号不存在或已被限制登录");
            }
        }

        appRequest.setPayload(String.valueOf(payload));
        args[0] = appRequest;
        return ResultInfo.ok(args);
    }

    /**
     * 后置拦截，解析返回参数并加密payload参数
     *
     * @param proceed 返回参数
     * @param isNeed 是否需要对请求、返回参数加解密
     * @return 加密后的参数
     */
    private Object postHandle(Object proceed, boolean isNeed) {
        if (!isNeed) {
            return proceed;
        }

        if (proceed instanceof ResultInfo) {
            ResultInfo result = (ResultInfo)proceed;
            if (StringUtils.isBlank(result.getPayload() + "")) {
                return proceed;
            }
            // 公钥加密
            String payload = RsaUtils.pubEncrypt(appConfig.getRsaPub(), String.valueOf(result.getPayload()));
            result.setPayload(payload);
            return result;
        }
        return proceed;
    }
}
