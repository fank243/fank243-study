package com.github.fank243.study.system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.LogRecordType;
import com.github.fank243.study.core.domain.enums.UserStatusEnum;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.core.model.redis.RedisService;
import com.github.fank243.study.core.utils.BeanUtils;
import com.github.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.study.oauth2.api.domain.dto.OauthAccessTokenDTO;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import com.github.fank243.study.oauth2.api.service.IOauth2Service;
import com.github.fank243.study.system.domain.SysUserEntity;
import com.github.fank243.study.system.domain.SysUserLoginLogEntity;
import com.github.fank243.study.system.domain.dto.SysUserDTO;
import com.github.fank243.study.system.domain.vo.SysUserVO;
import com.github.fank243.study.system.mapper.ISysUserMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;

/**
 * 系统管理员表 服务类
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Service
public class SysUserService extends ServiceImpl<ISysUserMapper, SysUserEntity> {

    @Resource
    private ISysUserMapper sysUserMapper;
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
        QueryWrapper queryWrapper = new QueryWrapper();
        Page<SysUserEntity> sysUserEntityPage =
            sysUserMapper.paginate(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), queryWrapper);
        return BeanUtils.convert(sysUserEntityPage, SysUserVO.class);
    }

    /**
     * 系统管理员表_新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_USER, subType = "insert", bizNo = "{{#sysUser.userId}}",
        success = "新增管理员【{{#sysUser.username}}】", successCondition = "{{#sysUser.userId!=null}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysUserDTO sysUser) throws BizException {

        ResultInfo<?> result = oauth2Service.getUserByUsername(sysUser.getUsername());
        if (!result.isSuccess()) {
            throw new BizException(result.getMessage());
        }
        OauthUserAccessTokenDTO oauth2UserDTO = BeanUtil.toBean(result.getPayload(), OauthUserAccessTokenDTO.class);
        if (oauth2UserDTO != null) {
            SysUserEntity sysUserEntity = SysUserEntity.builder().build();
            sysUserEntity.setOpenId(oauth2UserDTO.getOpenId());
            QueryWrapper queryWrapper = QueryWrapper.create(sysUserEntity);
            sysUserEntity = sysUserMapper.selectOneByQuery(queryWrapper);
            if (sysUserEntity != null) {
                throw new BizException("用户名已存在");
            }
            sysUserEntity = BeanUtil.toBean(oauth2UserDTO, SysUserEntity.class);
            sysUserEntity.setNickname(sysUser.getNickname());
            sysUserEntity.setStatus(UserStatusEnum.NORMAL);

            if (!save(sysUserEntity)) {
                return Boolean.FALSE;
            }
            sysUser.setUserId(sysUserEntity.getUserId());
            return Boolean.TRUE;
        }

        String userId = StpUtil.getLoginIdAsString();

        Object obj = redisService.getObj(CacheConstants.OAUTH2_TOKEN + userId);
        if (obj == null) {
            throw new IllegalStateException("令牌已过期失效，请重新登录");
        }
        OauthAccessTokenDTO oauthAccessTokenDTO = (OauthAccessTokenDTO)obj;

        // @formatter:off
        OauthUserAccessTokenDTO oauthUserAccessTokenDTO =
             OauthUserAccessTokenDTO.builder()
                .username(sysUser.getUsername()).nickname(sysUser.getNickname())
                .password(sysUser.getPassword())
                .accessToken(oauthAccessTokenDTO.getAccessToken())
                .openId(oauthAccessTokenDTO.getOpenId())
                .build();
        // @formatter:on
        result = oauth2Service.addUser(oauthUserAccessTokenDTO);
        if (!result.isSuccess()) {
            throw new BizException(result.getMessage());
        }
        oauth2UserDTO = JSONUtil.toBean(JSONUtil.parseObj(result.getPayload()), OauthUserAccessTokenDTO.class);

        SysUserEntity sysUserEntity;
        if (result.getStatus() == Oauth2Constants.USER_REPEAT_CODE) {
            sysUserEntity = BeanUtil.toBean(oauth2UserDTO, SysUserEntity.class);
            sysUserEntity.setNickname(sysUser.getNickname());
        } else {
            sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);
            sysUserEntity.setOpenId(oauth2UserDTO.getOpenId());
        }
        sysUserEntity.setStatus(UserStatusEnum.NORMAL);

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
    @LogRecord(type = LogRecordType.LOG_SYS_USER, subType = "update", bizNo = "{{#sysUser.userId}}",
        successCondition = "#_ret==true", success = "修改管理员信息：{_DIFF{#oldObject, #sysUser}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysUserDTO sysUser) throws BizException {
        SysUserEntity sysUserEntity = super.getById(sysUser.getUserId());
        if (sysUserEntity == null) {
            throw new BizException("用户不存在");
        }
        LogRecordContext.putVariable("oldObject", BeanUtil.copyProperties(sysUserEntity, SysUserDTO.class));

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);

        return saveOrUpdate(sysUserEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> login(String openId, String clientIp, String userAgent) {
        SysUserEntity sysUser = sysUserMapper.findByCondition(SysUserEntity.builder().openId(openId).build());
        if (sysUser == null) {
            return ResultInfo.err400("账号不存在");
        }
        if (EnumUtil.equals(UserStatusEnum.DISABLED, String.valueOf(sysUser.getStatus()))) {
            return ResultInfo.err400("账户已被禁用，请联系客服处理");
        }

        // 执行登录流程
        StpUtil.login(sysUser.getUserId(), "PC");

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = new SysUserLoginLogEntity();
        sysUserLoginLog.setUserId(sysUser.getUserId());
        sysUserLoginLog.setLoginTime(LocalDateTime.now());
        sysUserLoginLog.setLoginIp(clientIp);
        sysUserLoginLog.setLoginDevice(userAgent);
        sysUserLoginLogService.add(sysUserLoginLog);

        return ResultInfo.ok(sysUser);
    }

    public List<SysUserVO> findByUserIdIn(List<String> ids) {
        List<SysUserEntity> sysUserEntities = sysUserMapper.selectListByIds(ids);
        return BeanUtil.copyToList(sysUserEntities, SysUserVO.class);
    }

    public SysUserVO findByUserId(String userId) {
        SysUserEntity sysUserEntity = super.getById(userId);
        return BeanUtil.copyProperties(sysUserEntity, SysUserVO.class);
    }
}
