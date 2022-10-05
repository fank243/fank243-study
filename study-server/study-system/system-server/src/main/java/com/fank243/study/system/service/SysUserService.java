package com.fank243.study.system.service;

import javax.annotation.Resource;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.constants.RedisConstants;
import com.fank243.study.common.core.domain.model.PageBean;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.common.core.service.RedisService;
import com.fank243.study.common.core.utils.BeanUtils;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.oauth2.api.domain.dto.OauthUserDTO;
import com.fank243.study.oauth2.api.domain.vo.OauthAccessTokenVO;
import com.fank243.study.oauth2.api.service.IOauth2Service;
import com.fank243.study.system.domain.dto.SysUserDTO;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.domain.vo.SysUserVO;
import com.fank243.study.system.mapper.ISysUserMapper;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;

/**
 * 系统管理员表 服务类
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Service
public class SysUserService extends ServiceImpl<ISysUserMapper, SysUserEntity> {

    @Resource
    private ISysUserMapper sysUserDao;
    @Resource
    private IOauth2Service oauth2Service;
    @Resource
    private RedisService redisService;

    /**
     * 系统管理员表_分页
     *
     * @param sysUser 查询条件
     * @return 列表
     */
    public PageBean<SysUserVO> page(SysUserDTO sysUser) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        IPage<SysUserEntity> page =
            sysUserDao.selectPage(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), wrapper);
        return BeanUtils.convert(page, SysUserVO.class);
    }

    /**
     * 系统管理员表_新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = sysUserDao
            .selectOne(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, sysUser.getUsername()));
        if (sysUserEntity != null) {
            throw new BizException("用户名已存在");
        }

        String userId = StpUtil.getLoginIdAsString();

        Object obj = redisService.getObj(RedisConstants.OAUTH2_TOKEN + userId);
        if (obj == null) {
            throw new SaTokenException("令牌已过期失效，请重新登录");
        }
        OauthAccessTokenVO oauthAccessTokenVO = (OauthAccessTokenVO)obj;

        // @formatter:off
        OauthUserDTO oauthUserDTO =
            OauthUserDTO.builder().username(sysUser.getUsername()).nickname(sysUser.getNickname())
                .password(sysUser.getPassword())
                .accessToken(oauthAccessTokenVO.getAccessToken())
                .openId(oauthAccessTokenVO.getOpenId())
                .build();
        // @formatter:on
        ResultInfo<?> result = oauth2Service.addUser(oauthUserDTO);
        if (!result.isSuccess()) {
            throw new BizException(result.getMessage());
        }
        String openId = (String) result.getPayload();

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
        sysUserEntity.setOpenId(openId);
        return save(sysUserEntity);
    }

    /**
     * 系统管理员表_修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = sysUserDao.selectById(sysUser.getUserId());
        if (sysUserEntity == null) {
            throw new BizException("用户不存在");
        }

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
        return sysUserDao.updateById(sysUserEntity) > 0;
    }

    /**
     * 根据用户名查找
     *
     * @param username 请求参数
     * @return 操作结果
     */
    public SysUserEntity findByUsername(String username) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserDao.selectOne(wrapper);
    }

}
