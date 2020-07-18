package com.fank243.springboot.third.service.ym;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.common.encrypt.EncryptUtils;
import com.fank243.springboot.common.utils.HttpsUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.third.model.ym.AndroidNotification;
import com.fank243.springboot.third.model.ym.IosNotification;
import com.fank243.springboot.third.model.ym.YmRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 友盟消息推送
 * 
 * @author FanWeiJie
 * @date 2019-12-20 17:56:38
 */
@Setter
@Slf4j
@Component
public class YmPushService {

    private String ymApiUrl;
    private String androidAppKey;
    private String androidMasterSecret;
    private String iosAppKey;
    private String iosMasterSecret;

    private ResultInfo sendPush(String secret, YmRequest request) {
        JSONObject params = JSON.parseObject(JSON.toJSONString(request));

        String sign = EncryptUtils.md5("POST" + ymApiUrl + params.toString() + secret);
        String url = ymApiUrl + "?sign=" + sign;

        log.info("U-Push Req ：{} {}", url, params);

        String response = HttpsUtils.postForJson(url, params);
        if (StringUtils.isBlank(response)) {
            log.info("U-Push Res ：{}", response);
            return ResultInfo.fail("推送失败");
        }

        JSONObject json = JSON.parseObject(response);
        if ("SUCCESS".equalsIgnoreCase(json.getString("ret"))) {
            return ResultInfo.ok().message("推送成功");
        }

        return ResultInfo.fail(json.getJSONObject("data").getString("error_msg"));
    }

    /**
     * 发送推送 > Android
     * 
     * @param type 通知类型
     * @param deviceTokens 设备ID
     * @param title 标题
     * @param content 内容
     * @return 操作结果
     */
    private ResultInfo sendAndroid(String type, String deviceTokens, String title, String content) {
        YmRequest request = new YmRequest();
        request.setAppkey(androidAppKey);
        request.setType(type);
        request.setDevice_tokens(deviceTokens);
        request.setPayload(new AndroidNotification("notification", title, title, content).getJson());

        return sendPush(androidMasterSecret, request);
    }

    /**
     * 单播推送 > Android
     *
     * @param deviceToken 设备ID
     * @param title 标题
     * @param content 内容
     * @return 操作结果
     */
    public ResultInfo sendAndroidWithUnicast(String deviceToken, String title, String content) {
        return sendAndroid("unicast", deviceToken, title, content);
    }

    /**
     * 列播推送 > Android
     *
     * @param deviceTokens 设备ID集合
     * @param title 标题
     * @param content 内容
     * @return 操作结果
     */
    public ResultInfo sendAndroidWithListcast(String deviceTokens, String title, String content) {
        return sendAndroid("listcast", deviceTokens, title, content);
    }

    /**
     * 发送推送 > IOS
     *
     * @param type 通知类型
     * @param deviceTokens 设备ID
     * @param title 标题
     * @param content 内容
     * @return 操作结果
     */
    private ResultInfo sendIos(String type, String deviceTokens, String title, String content) {
        YmRequest request = new YmRequest();
        request.setAppkey(iosAppKey);
        request.setType(type);
        request.setDevice_tokens(deviceTokens);
        request.setPayload(new IosNotification(title, title, content).getJson());

        return sendPush(iosMasterSecret, request);
    }

    /**
     * 单播推送 > IOS
     *
     * @param deviceToken 设备ID
     * @param title 标题
     * @param content 内容
     * @return 操作结果
     */
    public ResultInfo sendIosWithUnicast(String deviceToken, String title, String content) {
        return sendIos("unicast", deviceToken, title, content);
    }

    /**
     * 列播推送 > IOS
     *
     * @param deviceTokens 设备ID集合
     * @param title 标题
     * @param content 内容
     * @return 操作结果
     */
    public ResultInfo sendIosWithListcast(String deviceTokens, String title, String content) {
        return sendIos("listcast", deviceTokens, title, content);
    }
}
