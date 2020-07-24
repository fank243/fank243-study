package com.fank243.springboot.admin.service.notice;

import com.fank243.springboot.admin.service.UserInfoService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.PushRecord;
import com.fank243.springboot.core.entity.UserInfo;
import com.fank243.springboot.core.enums.DeviceType;
import com.fank243.springboot.third.service.ym.YmPushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板消息发送服务
 *
 * @author FanWeiJie
 * @date 2019-11-02 15:03:59
 */
@Slf4j
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
@Component
public class PushService {

    @Resource
    private YmPushService ymPushService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private PushRecordService pushRecordService;

    /** 发送推送通知 **/
    public ResultInfo sendPush(Long userId, String title, String content) {
        UserInfo userInfo = userInfoService.findById(userId);

        if (userInfo.getDeviceType() == null) {
            return ResultInfo.fail("未知设备类型");
        }
        if (StringUtils.isBlank(userInfo.getDeviceToken())) {
            return ResultInfo.fail("用户DeviceToken不存在");
        }

        ResultInfo result;
        // Android
        if (DeviceType.ANDROID.name().equals(userInfo.getDeviceType().name())) {
            result = ymPushService.sendAndroidWithUnicast(userInfo.getDeviceToken(), title, content);
        } else {
            // IOS
            result = ymPushService.sendIosWithUnicast(userInfo.getDeviceToken(), title, content);
        }

        if (!result.isSuccess()) {
            return result;
        }

        return pushRecordService.addRecord(userId, userInfo.getDeviceType(), userInfo.getDeviceToken(), title, content);
    }

    /** 发送推送通知(批量) **/
    public ResultInfo sendBatchPush(List<Long> userIdList, String title, String content) {
        if (userIdList == null || userIdList.isEmpty()) {
            return ResultInfo.fail("请至少选择一个用户");
        }

        List<PushRecord> pushRecords = new ArrayList<>();
        StringBuilder android = new StringBuilder();
        StringBuilder ios = new StringBuilder();
        for (Long userId : userIdList) {
            UserInfo userInfo = userInfoService.findById(userId);
            if (userInfo == null) {
                continue;
            }
            if (userInfo.getDeviceType() == null) {
                continue;
            }
            if (DeviceType.ANDROID.name().equals(userInfo.getDeviceType().name())) {
                android.append(userInfo.getDeviceToken()).append(",");
            } else if (DeviceType.IOS.name().equals(userInfo.getDeviceType().name())) {
                ios.append(userInfo.getDeviceToken()).append(",");
            }

            PushRecord pushRecord = new PushRecord();
            pushRecord.setUserId(userId);
            pushRecord.setDeviceType(userInfo.getDeviceType());
            pushRecord.setDeviceToken(userInfo.getDeviceToken());
            pushRecord.setTitle(title);
            pushRecord.setContent(content);

            pushRecords.add(pushRecord);
        }

        ResultInfo result = ResultInfo.fail("发送推送通知失败");
        // Android
        if (StringUtils.isNotBlank(android.toString())) {
            result = ymPushService.sendAndroidWithUnicast(android.toString(), title, content);
        }
        // IOS
        if (StringUtils.isNotBlank(ios.toString())) {
            result = ymPushService.sendAndroidWithUnicast(ios.toString(), title, content);
        }

        if (!result.isSuccess()) {
            return result;
        }

        return pushRecordService.batchAddRecord(pushRecords);
    }

}
