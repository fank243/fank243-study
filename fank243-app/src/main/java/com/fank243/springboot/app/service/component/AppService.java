package com.fank243.springboot.app.service.component;

import com.fank243.springboot.app.consts.AppConfig;
import com.fank243.springboot.app.model.AppRequest;
import com.fank243.springboot.common.encrypt.EncryptUtils;
import com.fank243.springboot.common.encrypt.RsaUtils;
import com.fank243.springboot.common.utils.ResultCode;
import com.fank243.springboot.common.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * App 安全过滤工具
 *
 * @author FanWeiJie
 * @date 2019-11-27 11:03:09
 */
@Slf4j
@Component
public class AppService {
    @Resource
    private AppConfig appConfig;

    /**
     * 根据UA过滤常见爬虫工具的接口抓取
     *
     * @param userAgent userAgent
     * @return 操作结果
     */
    public ResultInfo filterReptile(String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return ResultInfo.ok();
        }
        if (userAgent.matches(
            "FeedDemon|JikeSpider|Indy Library|Alexa Toolbar|AskTbFXTV|AhrefsBot|CrawlDaddy|CoolpadWebkit|Java|Feedly|UniversalFeedParser|ApacheBench|Microsoft URL Control|Swiftbot|ZmEu|oBot|jaunty|Python-urllib|lightDeckReports Bot|YYSpider|DigExt|YisouSpider|HttpClient|MJ12bot|heritrix|EasouSpider|LinkpadBot|Ezooms|")) {
            log.warn("触发防爬机制：[{}]", userAgent);
            return ResultInfo.forbidden();
        }
        return ResultInfo.ok();
    }

    /**
     * 对比客户端传递的版本号与服务端设置的版本号大小，是否需要提示客户端进行升级
     * 
     * 1、前者大则返回一个正数 2、后者大返回一个负数 3、相等则返回0
     *
     * @param serVersion 服务端版本号，示例：{@code 1.0.1}
     * @param appVersion App端版本号，示例：{@code 1.0.0}
     * @return int
     */
    public int compareAppVersion(String serVersion, String appVersion) {
        if (serVersion == null || appVersion == null) {
            throw new RuntimeException("版本号不能为空");
        }
        // 注意此处为正则匹配，不能用.
        String[] versionArray1 = serVersion.split("\\.");
        String[] versionArray2 = appVersion.split("\\.");
        int idx = 0;
        // 取数组最小长度值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        // 先比较长度，再比较字符
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 验签、解密请求参数
     * 
     * @param appRequest 请求实体
     * @param isNeed 是否需要对请求、返回参数加解密
     * @return 成功返回解密后的载体，否则返回错误消息
     */
    public ResultInfo validateAndDecrypt(AppRequest appRequest, boolean isNeed) {
        // MD5验签
        String plainTxt = appRequest.getApiVer() + "" + appRequest.getTimestamp() + appRequest.getDeviceNumber();
        if (!EncryptUtils.md5(plainTxt).equalsIgnoreCase(appRequest.getSignature())) {
            return ResultInfo.fail(ResultCode.R102.getCode(), ResultCode.R102.getMsg());
        }

        // 不需要解密
        if (!isNeed) {
            return ResultInfo.ok(appRequest.getPayload());
        }

        String payload = RsaUtils.priDecrypt(appConfig.getRsaPri(), appRequest.getPayload());
        if (StringUtils.isBlank(payload)) {
            return ResultInfo.fail("参数解密失败");
        }

        return ResultInfo.ok(payload);
    }
}
