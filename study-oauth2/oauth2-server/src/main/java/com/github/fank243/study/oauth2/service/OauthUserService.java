package com.github.fank243.study.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.domain.enums.UserStatusEnum;
import com.github.fank243.study.oauth2.api.constants.Oauth2Constants;
import com.github.fank243.study.oauth2.api.domain.dto.OauthUserAccessTokenDTO;
import com.github.fank243.study.oauth2.domain.OauthAccessTokenEntity;
import com.github.fank243.study.oauth2.domain.OauthClientEntity;
import com.github.fank243.study.oauth2.domain.OauthUserEntity;
import com.github.fank243.study.oauth2.mapper.IOauthUserMapper;
import com.github.fank243.study.oauth2.utils.Oauth2Utils;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.row.Row;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import jakarta.annotation.Resource;

/**
 * 授权客户端表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Service
public class OauthUserService extends ServiceImpl<IOauthUserMapper, OauthUserEntity> {

    @Resource
    private IOauthUserMapper oauthUserMapper;
    @Resource
    private OauthClientService oauthClientService;
    @Resource
    private OauthAccessTokenService oauthAccessTokenService;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<String> login(String name, String pwd) {
        QueryWrapper queryWrapper = QueryWrapper.create(OauthUserEntity.builder().username(name).build());
        OauthUserEntity oauthUserEntity = oauthUserMapper.selectOneByQuery(queryWrapper);
        if (oauthUserEntity == null) {
            return ResultInfo.err400("用户名或密码错误");
        }
        if (!oauthUserEntity.getPassword().equalsIgnoreCase(SecureUtil.md5(pwd))) {
            return ResultInfo.err400("用户名或密码错误");
        }
        if (oauthUserEntity.getStatus() == UserStatusEnum.DISABLED.getCode()) {
            return ResultInfo.err400("账号已被禁用");
        }
        if (oauthUserEntity.getStatus() == UserStatusEnum.LOGIN_LOCK.getCode()) {
            return ResultInfo.err400("账号已被锁定");
        }

        return ResultInfo.ok(oauthUserEntity.getUserId());
    }

    public OauthUserAccessTokenDTO findUserAccessTokenByUsername(String username) {
        Row row = Db.selectOneBySql(
            "select a.user_id,a.username,a.nickname,b.open_id from tb_oauth_user a left join tb_oauth_access_token b on a.user_id = b.user_id where a.username = ?",
            username);
        return row != null ? row.toEntity(OauthUserAccessTokenDTO.class) : null;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> addUser(String clientId, OauthUserAccessTokenDTO oauthUserAccessTokenDTO) {
        QueryWrapper queryWrapper =
            QueryWrapper.create(OauthUserEntity.builder().username(oauthUserAccessTokenDTO.getUsername()).build());
        OauthUserEntity oauthUserEntity = oauthUserMapper.selectOneByQuery(queryWrapper);
        if (oauthUserEntity != null) {
            OauthAccessTokenEntity oauthAccessTokenEntity =
                oauthAccessTokenService.findByUserId(oauthUserEntity.getUserId());
            // @formatter:off
            OauthUserAccessTokenDTO oauth2UserDTO = OauthUserAccessTokenDTO.builder()
                    .userId(oauthUserEntity.getUserId())
                    .username(oauthUserEntity.getUsername())
                    .nickname(oauthUserEntity.getNickname())
                    .openId(oauthAccessTokenEntity.getOpenId())
                    .build();
            // @formatter:on
            return ResultInfo.error(Oauth2Constants.USER_REPEAT_CODE, "用户已经存在").payload(oauth2UserDTO);
        }
        // 创建用户
        oauthUserEntity = BeanUtil.copyProperties(oauthUserAccessTokenDTO, OauthUserEntity.class);
        oauthUserEntity.setPassword(SecureUtil.md5(oauthUserEntity.getPassword()));
        boolean isOk = save(oauthUserEntity);
        if (!isOk) {
            return ResultInfo.err400("创建账号失败");
        }
        OauthClientEntity oauthClientEntity = oauthClientService.findByClientId(clientId);

        // 创建OpenID
        String openId = Oauth2Utils.generateOpenId(oauthClientEntity.getClientId(), oauthUserEntity.getUserId());
        return oauthAccessTokenService.addAccessToken(oauthUserEntity.getUserId(), openId);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> modifyPassword(OauthUserAccessTokenDTO oauthUserAccessTokenDTO) {
        OauthUserEntity oauthUserEntity = BeanUtil.toBean(oauthUserAccessTokenDTO, OauthUserEntity.class);
        oauthUserEntity.setPassword(SecureUtil.md5(oauthUserAccessTokenDTO.getPassword()));

        boolean isOk = saveOrUpdate(oauthUserEntity);

        return isOk ? ResultInfo.ok() : ResultInfo.err500("修改密码失败");
    }

    public OauthUserEntity findByCondition(OauthUserEntity oauthUserEntity) {
        return oauthUserMapper.findByCondition(oauthUserEntity);
    }
}
