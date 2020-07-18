package com.fank243.springboot.third.service.ipaddr;

import com.fank243.springboot.common.utils.HttpsUtils;
import com.fank243.springboot.common.utils.ResultInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 太平洋IP地址库
 * 
 * @author FanWeiJie
 * @date 2020-03-07 11:58:01
 */
@Component
public class IpQueryService {

    /** 太平洋IP地址查询接口URL **/
    private String url = "http://whois.pconline.com.cn/ipJson.jsp?ip=#ip#&json=true";

    public ResultInfo getIpAddr(String ip) {
        url = url.replaceAll("#ip#", ip);
        String result = HttpsUtils.get(url);
        if (StringUtils.isBlank(result)) {
            return ResultInfo.fail("获取IP地址归属地失败");
        }
        return ResultInfo.ok(result);
    }
}
