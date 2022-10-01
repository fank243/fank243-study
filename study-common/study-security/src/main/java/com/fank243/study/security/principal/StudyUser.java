package com.fank243.study.security.principal;

import java.io.Serial;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Getter;

/**
 * Spring Security 登录主体扩展字段
 * 
 * @author FanWeiJie
 * @since 2022-10-01 22:03:15
 */
public class StudyUser extends User implements OAuth2AuthenticatedPrincipal {

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /** 用户ID */
    @Getter
    @JsonSerialize(using = ToStringSerializer.class)
    private final String userId;

    /** 手机号 */
    @Getter
    private final String mobile;

    public StudyUser(String userId, String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
        String mobile) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.mobile = mobile;
    }

    /**
     * Get the OAuth 2.0 token attributes
     * 
     * @return the OAuth 2.0 token attributes
     */
    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>(0);
    }

    @Override
    public String getName() {
        return this.getUsername();
    }

}
