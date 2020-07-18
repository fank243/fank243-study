package com.fank243.springboot.app.service;

import com.fank243.springboot.app.repository.UserInfoRepository;
import com.fank243.springboot.app.service.base.BaseService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.User;
import com.fank243.springboot.core.entity.UserInfo;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.core.enums.Gender;
import com.fank243.springboot.core.enums.UserSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 用户详细信息
 * 
 * @author FanWeiJie
 * @date 2020-03-25 23:41:32
 */
@Service
public class UserInfoService extends BaseService<UserInfo> {

    @Resource
    private UserInfoRepository repository;

    public UserInfo findById(Long userId) {
        Optional<UserInfo> optional = repository.findById(userId);
        return optional.orElse(new UserInfo());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo addUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setMobile(user.getMobile());
        userInfo.setIsVerifyEmail(false);
        userInfo.setIsVerifyRealname(false);
        userInfo.setGender(Gender.SECRET);
        userInfo.setSource(UserSource.UNKNOWN);
        userInfo.setDeviceType(DeviceType.UNKNOWN);

        userInfo = repository.save(userInfo);
        if (userInfo.getId() == null || userInfo.getId() <= 0) {
            return ResultInfo.fail("添加用户详细信息失败");
        }

        return ResultInfo.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateMobile(User user) {
        int rows = repository.updateMobileByUserId(user.getMobile(), user.getId());
        if (rows <= 0) {
            return ResultInfo.fail("同步用户手机号码失败");
        }

        return ResultInfo.ok();
    }
}
