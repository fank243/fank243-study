/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.LogRecordType;
import com.github.fank243.kong.core.domain.model.PageBean;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthAccessTokenDTO;
import com.github.fank243.kong.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import com.github.fank243.kong.oauth2.api.service.IOauth2Service;
import com.github.fank243.kong.system.domain.entity.SysUserEntity;
import com.github.fank243.kong.system.domain.entity.SysUserLoginLogEntity;
import com.github.fank243.kong.system.domain.dto.SysUserDTO;
import com.github.fank243.kong.system.domain.enums.UserStatusEnum;
import com.github.fank243.kong.system.domain.vo.SysUserVO;
import com.github.fank243.kong.system.repository.ISysUserRepository;
import com.github.fank243.kong.tool.result.ResultInfo;
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
public class SysUserService {

    @Resource
    private ISysUserRepository sysUserRepository;
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
        // Page<SysUserEntity> sysUserEntityPage =
        // sysUserRepository.paginate(new Page<>(sysUser.getCurrPage(), sysUser.getPageSize()), queryWrapper);
        // return BeanUtils.convert(sysUserEntityPage, SysUserVO.class);
        return null;
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
            SysUserEntity sysUserEntity = sysUserRepository.findByOpenId(oauth2UserDTO.getOpenId());
            if (sysUserEntity != null) {
                throw new BizException("用户已存在");
            }
            sysUserEntity = BeanUtil.toBean(oauth2UserDTO, SysUserEntity.class);
            sysUserEntity.setNickname(sysUser.getNickname());
            sysUserEntity.setStatus(UserStatusEnum.NORMAL);

            sysUserRepository.save(sysUserEntity);

            sysUser.setUserId(sysUserEntity.getId());
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

        sysUserRepository.save(sysUserEntity);

        sysUser.setUserId(sysUserEntity.getId());

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
        SysUserEntity sysUserEntity = sysUserRepository.findById(sysUser.getUserId()).orElse(null);
        if (sysUserEntity == null) {
            throw new BizException("用户不存在");
        }
        LogRecordContext.putVariable("oldObject", BeanUtil.copyProperties(sysUserEntity, SysUserDTO.class));

        sysUserEntity = BeanUtil.toBean(sysUser, SysUserEntity.class);

        sysUserRepository.save(sysUserEntity);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> login(String openId, String clientIp, String userAgent) {
        SysUserEntity sysUser = sysUserRepository.findByOpenId(openId);
        if (sysUser == null) {
            return ResultInfo.err400("账号不存在");
        }
        if (EnumUtil.equals(UserStatusEnum.DISABLED, String.valueOf(sysUser.getStatus()))) {
            return ResultInfo.err400("账户已被禁用，请联系客服处理");
        }

        // 执行登录流程
        StpUtil.login(sysUser.getId(), "PC");

        // 登录日志
        SysUserLoginLogEntity sysUserLoginLog = new SysUserLoginLogEntity();
        sysUserLoginLog.setUserId(sysUser.getId());
        sysUserLoginLog.setLoginTime(LocalDateTime.now());
        sysUserLoginLog.setLoginIp(clientIp);
        sysUserLoginLog.setLoginDevice(userAgent);
        sysUserLoginLogService.add(sysUserLoginLog);

        return ResultInfo.ok(sysUser);
    }

    public List<SysUserVO> findAllByUserIdIn(List<String> userIdList) {
        List<SysUserEntity> sysUserEntities =
            sysUserRepository.findAllByIdIn(userIdList.stream().map(Long::valueOf).toList());
        return BeanUtil.copyToList(sysUserEntities, SysUserVO.class);
    }

    public SysUserVO findByUserId(String userId) {
        SysUserEntity sysUserEntity = sysUserRepository.findById(Long.valueOf(userId)).orElse(null);
        return BeanUtil.copyProperties(sysUserEntity, SysUserVO.class);
    }

    public void removeByIds(List<String> ids) {
        sysUserRepository.deleteAllByIdInBatch(ids.stream().map(Long::valueOf).toList());
    }
}
