package com.fank243.springboot.third.service.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;
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
public class AliyunVodService {

    private DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * 获取视频上传地址和凭证
     * 
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     */
    public ResultInfo createUploadVideo(String accessKeyId, String accessKeySecret, String title, String filename) {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(title);
        request.setFileName(filename);
        // 转码组ID，阿里云手动设置
        request.setTemplateGroupId("3b107baab569bf61889ca47d3968a60a");
        CreateUploadVideoResponse response;
        try {
            log.info("Aliyun Create Video Req：{}", JSON.toJSON(request));
            response = client.getAcsResponse(request);
            log.info("Aliyun Create Video Resp：{}", response != null ? JSON.toJSON(response) : "null");
        } catch (ClientException e) {
            e.printStackTrace();
            return ResultInfo.fail("获取视频上传地址和凭证失败");
        }

        return ResultInfo.ok(JSON.toJSON(response));
    }

    /**
     * 刷新视频上传地址和凭证
     *
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     */
    public ResultInfo refreshUploadVideo(String accessKeyId, String accessKeySecret, String videoId) {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        RefreshUploadVideoResponse response;
        try {
            log.info("Aliyun Refresh Video Req：{}", JSON.toJSON(request));
            response = client.getAcsResponse(request);
            log.info("Aliyun Refresh Video Resp：{}", response != null ? JSON.toJSON(response) : "null");
        } catch (ClientException e) {
            e.printStackTrace();
            return ResultInfo.fail("刷新视频凭证失败");
        }

        return ResultInfo.ok(JSON.toJSON(response));
    }

}
