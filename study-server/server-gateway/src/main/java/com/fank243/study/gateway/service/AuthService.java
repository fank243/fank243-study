package com.fank243.study.gateway.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.core.exception.AuthException;
import com.fank243.study.gateway.dao.IAuthDao;
import com.fank243.study.gateway.domain.AccessTokenVO;
import com.fank243.study.gateway.domain.LoginUserDTO;
import com.fank243.study.gateway.domain.LoginUserVO;
import com.fank243.study.gateway.domain.SysUserEntity;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Oauth 认证
 *
 * @author FanWeiJie
 * @since 2021-11-24 14:54:25
 */
@SuppressWarnings("ALL")
@Service
public class AuthService extends ServiceImpl<IAuthDao, SysUserEntity> {

    @Resource
    private IAuthDao authDao;
    @Resource
    private OauthClientService oauthClientService;

    /**
     * 根据用户名查找
     *
     * @param username 请求参数
     * @return 操作结果
     */
    public SysUserEntity findByUsername(String username) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return authDao.selectOne(wrapper);
    }

    /**
     * 登录
     *
     * @param loginDTO 登录实体
     * @return 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public LoginUserVO login(LoginUserDTO loginDTO) throws AuthException {
        SysUserEntity sysUser = findByUsername(loginDTO.getUsername());
        if (sysUser == null) {
            throw new AuthException("用户名或密码错误");
        }
        if (!sysUser.getPassword().equalsIgnoreCase(loginDTO.getPassword())) {
            throw new AuthException("用户名或密码错误");
        }

        AccessTokenVO accessTokenVO = oauthClientService.token(loginDTO);

        // 执行登录
        StpUtil.login(sysUser.getId());

        return LoginUserVO.builder().username(sysUser.getUsername()).accessTokenInfo(accessTokenVO)
            .tokenInfo(StpUtil.getTokenInfo()).build();
    }

    /**
     * 刷新令牌
     *
     * @param loginDTO 登录实体
     * @return 用户ID
     */
    public AccessTokenVO refreshToken(LoginUserDTO loginDTO) throws AuthException {
        return oauthClientService.refresh(loginDTO);
    }
}
