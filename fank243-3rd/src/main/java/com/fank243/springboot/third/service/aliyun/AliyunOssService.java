package com.fank243.springboot.third.service.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.third.model.aliyun.PostObjectPolicy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 阿里云接口
 * 
 * @author FanWeiJie
 * @date 2020-01-02 10:56:54
 */
@Setter
@Slf4j
@Component
public class AliyunOssService {

    private OSSClient initOssClient(String accessKeyId, String accessKeySecret, String endpoint) {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 获取Policy签名等信息
     * 
     * @param dir 存储在bucket的目录，示例：{@code /images/dev}
     * @param expiredSeconds 过期时间，单位：s
     */
    public ResultInfo getPostObjectPolicy(String accessKeyId, String accessKeySecret, String endpoint, String bucket,
        String dir, long expiredSeconds) {
        OSSClient ossClient = initOssClient(accessKeyId, accessKeySecret, endpoint);

        long expireEndTime = System.currentTimeMillis() + expiredSeconds * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        PostObjectPolicy policy = new PostObjectPolicy();
        policy.setAccessId(accessKeyId);
        policy.setHost("https://" + bucket + "." + endpoint);
        policy.setDir(dir);
        policy.setExpire(String.valueOf(expireEndTime / 1000));
        policy.setPolicy(encodedPolicy);
        policy.setSignature(postSignature);

        return ResultInfo.ok(policy);
    }

    /**
     * 上传文件
     *
     * @param inputStream 文件流
     * @param newFileName 文件名称，示例：{@code /images/2020/03/123456789.png }
     * @return 操作结果
     */
    public ResultInfo uploadFile(String accessKeyId, String accessKeySecret, String endpoint, String bucket,
        InputStream inputStream, String newFileName) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 上传文件流。
            ossClient.putObject(bucket, newFileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            log.error(e.toString());
            return ResultInfo.fail("文件上传失败");
        }

        return ResultInfo.ok("/" + newFileName).message("文件上传成功");
    }
}
