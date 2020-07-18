package com.fank243.springboot.third.service.aliyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fank243.springboot.common.utils.ResultInfo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 阿里云接口
 * 
 * @author FanWeiJie
 * @date 2020-01-02 10:56:54
 */
@Setter
@Slf4j
@Component
public class AliyunSmsService {
    /**
     * 发送手机短信
     *
     * @param signName 签名名称
     * @param aliyunCode 模板Code
     * @param mobile 手机号码，多个之间使用“,”分割
     * @param json 替换参数
     * @return 操作结果
     */
    public ResultInfo sendSms(String accessKeyId, String accessSecret, String signName, String aliyunCode,
        String mobile, String json) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setAction("SendSms");
        request.setVersion("2017-05-25");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", aliyunCode);
        request.putQueryParameter("TemplateParam", json);
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject data = JSON.parseObject(response.getData());
            if (!"OK".equalsIgnoreCase(data.getString("Code"))) {
                return ResultInfo.fail(data.getString("Message"));
            }
            return ResultInfo.ok();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return ResultInfo.fail("短信发送失败");
    }
}
