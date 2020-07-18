package com.fank243.springboot.admin.shiro;

import com.fank243.springboot.admin.model.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;

/**
 * Shiro 工具类
 * 
 * @author FanWeiJie
 * @date 2018-09-04 09:48:51
 */
public class ShiroUtils {

    /** 加密方式 **/
    public static final String ALGORITHM_NAME = "MD5";
    /** 密码重复次数 **/
    public static final int HASH_ITERATIONS = 2048;

    /**
     * MD5加密
     *
     * 加密次数需要与{@link ShiroConfig#hashedCredentialsMatcher()}的加密次数一致，否则认证时密码校验不通过
     *
     * @param salt 盐值
     * @param password 密码
     * @return MD5摘要
     */
    public static String md5WithSalt(String salt, String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
    }

    /**
     * 获取当前用户Session
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     */
    public static ActiveUser getActiveUser() {
        return (ActiveUser)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     */
    public static void deleteCache(String username, boolean isRemoveSession) {
        // // 从缓存中获取Session
        // Session session = null;
        // Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        // CurrUser currUser;
        // Object attribute = null;
        // for (Session sessionInfo : sessions) {
        // // 遍历Session,找到该用户名称对应的Session
        // attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        // if (attribute == null) {
        // continue;
        // }
        // currUser = (CurrUser)((SimplePrincipalCollection)attribute).getPrimaryPrincipal();
        // if (currUser == null) {
        // continue;
        // }
        // if (Objects.equals(currUser.getUsername(), username)) {
        // session = sessionInfo;
        // }
        // }
        // if (session == null || attribute == null) {
        // return;
        // }
        // // 删除session
        // if (isRemoveSession) {
        // redisSessionDAO.delete(session);
        // }
        // // 删除Cache，在访问受限接口时会重新授权
        // DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        // Authenticator authc = securityManager.getAuthenticator();
        // ((LogoutAware)authc).onLogout((SimplePrincipalCollection)attribute);
    }
}
