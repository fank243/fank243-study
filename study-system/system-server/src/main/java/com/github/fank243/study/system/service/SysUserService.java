package com.github.fank243.study.system.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.LogRecordType;
import com.github.fank243.study.core.domain.enums.UserStatusEnum;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.core.service.RedisService;
import com.github.fank243.study.core.utils.BeanUtils;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserDTO;
import com.github.fank243.study.oauth2.api.domain.vo.OauthAccessTokenVO;
import com.github.fank243.study.oauth2.api.service.IOauth2Service;
import com.github.fank243.study.system.domain.dto.SysUserDTO;
import com.github.fank243.study.system.domain.entity.SysUserEntity;
import com.github.fank243.study.system.domain.entity.SysUserLoginLogEntity;
import com.github.fank243.study.system.domain.vo.SysUserVO;
import com.github.fank243.study.system.mapper.ISysUserMapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

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
    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

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
    @LogRecord(type = LogRecordType.SYS_USER, bizNo = "{{#sysUser.userId}}", success = "新增管理员【{{#sysUser.username}}】",
        successCondition = "{{#sysUser.userId!=null}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = sysUserDao
            .selectOne(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, sysUser.getUsername()));
        if (sysUserEntity != null) {
            throw new BizException("用户名已存在");
        }

        String userId = StpUtil.getLoginIdAsString();

        Object obj = redisService.getObj(CacheConstants.OAUTH2_TOKEN + userId);
        if (obj == null) {
            throw new IllegalStateException("令牌已过期失效，请重新登录");
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
        String openId = (String)result.getPayload();

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
        sysUserEntity.setOpenId(openId);

        if (!save(sysUserEntity)) {
            return Boolean.FALSE;
        }

        sysUser.setUserId(sysUserEntity.getUserId());
        return Boolean.TRUE;
    }

    /**
     * 系统管理员表_修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.SYS_USER, bizNo = "{{#sysUser.userId}}", successCondition = "#_ret==true",
        success = "修改管理员信息：{_DIFF{#oldObject, #sysUser}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = sysUserDao.selectById(sysUser.getUserId());
        if (sysUserEntity == null) {
            throw new BizException("用户不存在");
        }
        LogRecordContext.putVariable("oldObject", BeanUtil.copyProperties(sysUserEntity, SysUserDTO.class));

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

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> login(String openId, String clientIp, String userAgent) {
        SysUserEntity sysUser = sysUserDao.findByOpenId(openId);
        if (sysUser == null) {
            return ResultInfo.err400("账号不存在");
        }
        if (UserStatusEnum.DISABLED.getCode() == sysUser.getStatus()) {
            return ResultInfo.err400("账户已被禁用，请联系客服处理");
        }

        // 执行登录流程
        StpUtil.login(sysUser.getUserId(), "PC");

        Date now = new Date();

        // 更新登录信息
        sysUser.setLastLoginTime(now);
        sysUser.setLastLoginIp(clientIp);
        sysUserDao.updateLoginInfoByUserId(sysUser);

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = SysUserLoginLogEntity.builder().userId(sysUser.getUserId())
            .loginTime(now).loginIp(clientIp).loginDevice(userAgent).build();
        sysUserLoginLogService.add(sysUserLoginLog);

        return ResultInfo.ok(sysUser);
    }

    public List<SysUserVO> findByUserIdIn(List<String> ids) {
        List<SysUserEntity> sysUserEntities =
            sysUserDao.selectList(new LambdaQueryWrapper<SysUserEntity>().in(SysUserEntity::getUserId, ids));
        return BeanUtil.copyToList(sysUserEntities, SysUserVO.class);
    }

    public SysUserVO findByUserId(String userId) {
        SysUserEntity sysUserEntity = getById(userId);
        return BeanUtil.copyProperties(sysUserEntity, SysUserVO.class);
    }
}
