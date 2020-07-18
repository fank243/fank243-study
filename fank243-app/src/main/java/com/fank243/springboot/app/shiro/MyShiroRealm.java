package com.fank243.springboot.app.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.app.consts.AppConfig;
import com.fank243.springboot.app.model.ActiveUser;
import com.fank243.springboot.app.model.AppRequest;
import com.fank243.springboot.app.service.UserService;
import com.fank243.springboot.app.utils.WebUtils;
import com.fank243.springboot.common.encrypt.RsaUtils;
import com.fank243.springboot.common.utils.ResultCode;
import com.fank243.springboot.core.consts.IConsts;
import com.fank243.springboot.core.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义登录、授权
 * 
 * @author FanWeiJie
 * @date 2020-03-31 21:17:50
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private AppConfig appConfig;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser)principals.getPrimaryPrincipal();

        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        if (activeUser.getUserId() != null && activeUser.getUserId() > 0) {
            User user = userService.findById(activeUser.getUserId());
            return null;
        }

        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AppRequest appRequest = (AppRequest)token.getPrincipal();
        if (StringUtils.isBlank(appRequest.getSignature())) {
            throw new AuthenticationException(ResultCode.R102.getMsg());
        }

        String payload = appRequest.getPayload();
        // 此处不对token进行校验，仅判断请求头参数是否传递了accessToken参数，如果传递了就不解密，具体的判断交给aop处理
        String accessToken = WebUtils.getRequest().getHeader(IConsts.ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(payload)) {
            payload = RsaUtils.pubDecrypt(appConfig.getRsaPub(), payload);
            if (StringUtils.isBlank(payload)) {
                throw new AuthenticationException("参数解密失败");
            }
        }

        ActiveUser activeUser = new ActiveUser(0L);
        if (StringUtils.isNotBlank(payload)) {
            JSONObject json = JSON.parseObject(payload);
            if (json.containsKey("userId")) {
                activeUser.setUserId(json.getLongValue("userId"));
            }
        }

        return new SimpleAuthenticationInfo(activeUser, appRequest.getSignature(), getName());
    }
}
