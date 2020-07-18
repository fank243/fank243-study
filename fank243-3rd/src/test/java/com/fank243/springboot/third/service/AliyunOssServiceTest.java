package com.fank243.springboot.third.service;

import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.third.service.aliyun.AliyunOssService;
import org.junit.Test;

/**
 * Aliyun OSS Test
 * 
 * @author FanWeiJie
 * @date 2020-03-24 15:56:40
 */
public class AliyunOssServiceTest extends AliyunTest {

    private AliyunOssService aliyunOssService = new AliyunOssService();

    @Test
    public void getPostObjectPolicy() {
        ResultInfo result =
            aliyunOssService.getPostObjectPolicy(accessKeyId, accessKeySecret, endpoint, bucket, "/app", 60);

        System.out.println(result);
    }

    @Test
    public void uploadFile() {}
}