package com.fank243.springboot.admin.shiro;

import com.fank243.springboot.admin.model.ActiveUser;
import com.fank243.springboot.admin.model.Menu;
import com.fank243.springboot.admin.service.SysPermissionService;
import com.fank243.springboot.admin.service.SysUserService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro 认证
 * 
 * @author FanWeiJie
 * @date 2018-09-04 08:51:03
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 授权权限
     *
     * 用户进行权限验证时shiro会去缓存中找,如果查不到数据,会执行这个方法去查询权限,并放入缓存中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser)principals.getPrimaryPrincipal();

        SysUser sysUser = sysUserService.findById(activeUser.getUserId());
        Set<SysRole> sysRoles = sysUser.getRoles();

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        if (sysRoles.size() > 0) {
            for (SysRole sysRole : sysRoles) {
                // 排除已禁用的角色
                if (!sysRole.getAvailable()) {
                    continue;
                }
                roleSet.add(sysRole.getName());

                // 权限
                Set<SysPermission> sysPermissions = sysRole.getPermissions();
                if (sysPermissions.size() > 0) {
                    for (SysPermission sysPermission : sysPermissions) {
                        // 加入已启用的权限
                        if (sysPermission.getAvailable()) {
                            permissionSet.add(sysPermission.getPermission());
                        }
                    }
                }
            }
        }

        // 将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }

    /**
     * 用户身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        // 获取登录参数
        ShiroToken shiroToken = (ShiroToken)authenticationToken.getPrincipal();

        ResultInfo result =
            sysUserService.login(shiroToken.getUsername(), shiroToken.getPassword(), shiroToken.getImgCode());
        if (!result.isSuccess()) {
            throw new AuthenticationException(result.getMsg());
        }
        SysUser sysUser = (SysUser)result.getPayload();

        ActiveUser activeUser = new ActiveUser(sysUser.getId(), sysUser.getUsername(), sysUser.getRealname());

        // 获取管理员所拥有的菜单列表
        List<Menu> menus = sysPermissionService.findPermissionBySysUserId(sysUser.getId());
        activeUser.setMenus(menus);

        // 进行验证
        return new SimpleAuthenticationInfo(activeUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()),
            getName());
    }

}
