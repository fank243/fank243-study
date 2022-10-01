package com.fank243.study.security.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fank243.study.common.core.constants.SecurityConstants;
import com.fank243.study.security.principal.StudyUser;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * Spring Security 工具类
 * 
 * @author FanWeiJie
 * @since 2022-10-01 22:02:10
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取认证信息
     * 
     * @return Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户信息
     * 
     * @param authentication Authentication
     * @return 登录用户
     */
    public StudyUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof StudyUser) {
            return (StudyUser)principal;
        }
        return null;
    }

    /**
     * 获取用户信息
     * 
     * @return 登录用户
     */
    public StudyUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     * 
     * @return 角色集合
     */
    public List<Long> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Long> roleIds = new ArrayList<>();
        authorities.stream().filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE_PREFIX))
            .forEach(granted -> {
                String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE_PREFIX);
                roleIds.add(Long.parseLong(id));
            });
        return roleIds;
    }

}
