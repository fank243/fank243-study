package com.github.fank243.study.support.service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.CacheConstants;
import com.github.fank243.study.core.constants.Constants;
import com.github.fank243.study.core.constants.TimeConstant;
import com.github.fank243.study.core.service.RedisService;
import com.github.fank243.study.core.service.RedissonService;
import com.github.fank243.study.support.constants.SupportConstants;
import com.github.fank243.study.support.domain.dto.FileDTO;
import com.github.fank243.study.support.domain.entity.FileEntity;
import com.github.fank243.study.support.mapper.IFileMapper;
import com.github.fank243.study.support.web.config.FileProperties;

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
public class FileService extends ServiceImpl<IFileMapper, FileEntity> {
    @Resource
    private IFileMapper fileMapper;
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
    public ResultInfo<Map<String, String>> saveFile(InputStream inputStream, FileDTO fileDTO) {
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
            FileEntity fileEntity = fileMapper.findByFileNameAndFilePrefixAndFileMd5(fileDTO.getFileName(),
                fileDTO.getFilePrefix(), fileMd5);
            if (fileEntity != null) {
                // 删除重复文件
                ThreadUtil.execute(() -> FileUtil.del(file));
                Map<String, String> map = new HashMap<>(1);
                map.put("fileId", fileEntity.getFileId());
                return ResultInfo.ok(map).message("上传文件成功");
            }
            newFileEntity = BeanUtil.copyProperties(fileDTO, FileEntity.class);

            FileEntity md5FileEntity = fileMapper.findByFileMd5(fileMd5);
            if (md5FileEntity != null) {
                // 删除重复文件
                ThreadUtil.execute(() -> FileUtil.del(file));
                newFileEntity = BeanUtil.copyProperties(md5FileEntity, FileEntity.class);
                newFileEntity.setFileId(null);
                newFileEntity.setFileName(fileDTO.getFileName());
                newFileEntity.setFilePrefix(fileDTO.getFilePrefix());

                newFileEntity.setCreatedBy(null);
                newFileEntity.setCreatedDate(null);
                newFileEntity.setLastModifiedBy(null);
                newFileEntity.setLastModifiedDate(null);

            } else {
                newFileEntity.setFileId(null);
                newFileEntity.setMineType(FileUtil.getMimeType(file.getAbsolutePath()));
                newFileEntity.setFilePrefix(fileDTO.getFilePrefix());
                newFileEntity.setFilePath(fileDir + "/" + fileName);
                newFileEntity.setFileSize(file.length());
                newFileEntity.setFileMd5(fileMd5);
            }

            save(newFileEntity);
        } finally {
            redissonService.unlock(SupportConstants.LOCK_KEY_UPLOAD + loginId);
        }

        // 存入缓存
        redisService.setHashValue(CacheConstants.FILE_ID_KEY, newFileEntity.getFileId(), newFileEntity,
            TimeConstant.DAY_7);

        Map<String, String> map = new HashMap<>(2);
        map.put("fileId", newFileEntity.getFileId());
        return ResultInfo.ok(map).message("上传文件成功");
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
            fileEntity = fileMapper.selectById(fileId);
            if (fileEntity != null) {
                // 存入缓存
                redisService.setHashValue(CacheConstants.FILE_ID_KEY, fileId, fileEntity, TimeConstant.DAY_7);
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
