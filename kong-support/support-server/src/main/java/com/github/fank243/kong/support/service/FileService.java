/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.support.service;

import java.io.File;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fank243.kong.core.constants.CacheConstants;
import com.github.fank243.kong.core.constants.Constants;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.core.model.redis.RedisService;
import com.github.fank243.kong.core.model.redisson.RedissonService;
import com.github.fank243.kong.support.constants.SupportConstants;
import com.github.fank243.kong.support.domain.FileEntity;
import com.github.fank243.kong.support.domain.dto.FileDTO;
import com.github.fank243.kong.support.repository.IFileRepository;
import com.github.fank243.kong.support.web.config.FileProperties;
import com.github.fank243.kong.tool.result.ResultInfo;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import jakarta.annotation.Resource;

/**
 * 文件表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Service
public class FileService {
    @Resource
    private IFileRepository fileRepository;
    @Resource
    private FileProperties fileProperties;
    @Resource
    private RedissonService redissonService;
    @Resource
    private RedisService redisService;

    /**
     * 上传文件，MD5值一致的文件不重复写入
     *
     * @param inputStream 文件流
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<Long> saveFile(InputStream inputStream, FileDTO fileDTO) {
        // 登录人ID
        String loginId = StpUtil.getLoginIdAsString();

        // 上传文件目录
        String uploadPath = fileProperties.getPath();
        // 文件存储相对路径
        String fileDir = "/files";
        if (Constants.FILE_TYPE_TEXT.contains(fileDTO.getFileType())) {
            fileDir += "/text";
        } else if (Constants.FILE_TYPE_IMAGE.contains(fileDTO.getFileType())) {
            fileDir += "/image";
        } else if (Constants.FILE_TYPE_OFFICE.contains(fileDTO.getFileType())) {
            fileDir += "/office";
        }

        File uploadFile = new File(uploadPath + fileDir);
        if (!FileUtil.exist(uploadFile)) {
            boolean isOk = uploadFile.mkdirs();
            if (!isOk) {
                return ResultInfo.err500("创建上传文件目录失败");
            }
        }

        FileEntity newFileEntity;
        try {
            redissonService.lock(SupportConstants.LOCK_KEY_UPLOAD + loginId);

            // 写入文件
            String fileName = UUID.fastUUID() + "." + fileDTO.getFileType();
            File file = FileUtil.writeFromStream(inputStream, uploadFile.getAbsolutePath() + File.separator + fileName);

            String fileMd5 = SecureUtil.md5(file);
            FileEntity fileEntity = fileRepository.findByFileNameAndFilePrefixAndFileMd5(fileDTO.getFileName(),
             StrUtil.blankToDefault(fileDTO.getFilePrefix(), ""), fileMd5);
            if (fileEntity != null) {
                // 删除重复文件
                ThreadUtil.execute(() -> FileUtil.del(file));
                return ResultInfo.ok(fileEntity.getId()).message("上传文件成功");
            }
            newFileEntity = BeanUtil.copyProperties(fileDTO, FileEntity.class);

            FileEntity md5FileEntity =  fileRepository.findByFileMd5(fileMd5);
            if (md5FileEntity != null) {
                // 删除重复文件
                ThreadUtil.execute(() -> FileUtil.del(file));
                newFileEntity = BeanUtil.copyProperties(md5FileEntity, FileEntity.class);
                newFileEntity.setId(null);
                newFileEntity.setFileName(fileDTO.getFileName());
                newFileEntity.setFilePrefix(fileDTO.getFilePrefix());
            } else {
                newFileEntity.setId(null);
                newFileEntity.setMineType(FileUtil.getMimeType(file.getAbsolutePath()));
                newFileEntity.setFilePrefix(fileDTO.getFilePrefix());
                newFileEntity.setFilePath(fileDir + "/" + fileName);
                newFileEntity.setFileSize(file.length());
                newFileEntity.setFileMd5(fileMd5);
            }

            fileRepository.save(newFileEntity);
        } finally {
            redissonService.unlock(SupportConstants.LOCK_KEY_UPLOAD + loginId);
        }

        // 存入缓存
        redisService.setHashValue(CacheConstants.FILE_ID_KEY, newFileEntity.getId() + "", newFileEntity,
            TimeConstants.DAY_7);

        return ResultInfo.ok(newFileEntity.getId()).message("上传文件成功");
    }

    /**
     * 根据文件ID读取文件
     *
     * @param fileId 文件ID
     * @return 文件流
     */
    public File getFile(String fileId) {
        Object obj = redisService.getHashValue(CacheConstants.FILE_ID_KEY, fileId);

        FileEntity fileEntity;
        if (obj == null) {
            fileEntity = fileRepository.findById(fileId).orElse(null);
            if (fileEntity != null) {
                // 存入缓存
                redisService.setHashValue(CacheConstants.FILE_ID_KEY, fileId, fileEntity, TimeConstants.DAY_7);
            } else {
                return null;
            }
        }
        fileEntity = (FileEntity)obj;

        // 文件前缀访问权限控制
        if (StrUtil.isNotBlank(fileEntity.getFilePrefix())) {
            // 免登录可查看下载
            if (!Constants.FILE_PREFIX_NOT_LOGIN.contains(fileEntity.getFilePrefix())) {
                StpUtil.checkLogin();
            }
            // 登录用户独有
            if (fileEntity.getFilePrefix().startsWith(Constants.FILE_PREFIX_USER)) {
                String loginId = StpUtil.getLoginIdAsString();
                if (!fileEntity.getFilePrefix().contains(loginId)) {
                    throw new NotPermissionException(ResultInfo.err403().getMessage());
                }
            }
        }
        return new File(fileProperties.getPath() + fileEntity.getFilePath());
    }
}
