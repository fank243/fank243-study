package com.fank243.study.file.service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.fank243.study.common.core.service.RedissonService;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.core.constants.DateConstant;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.common.core.utils.WebUtils;
import com.fank243.study.file.constants.FileConstants;
import com.fank243.study.file.mapper.IFileMapper;
import com.fank243.study.file.domain.dto.FileDTO;
import com.fank243.study.file.domain.entity.FileEntity;
import com.fank243.study.file.web.config.FileProperties;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 文件表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Service
public class FileService extends ServiceImpl<IFileMapper, FileEntity> {

    @Resource
    private IFileMapper fileDao;
    @Resource
    private FileProperties fileProperties;
    @Resource
    private RedissonService redissonService;

    /**
     * 上传文件，MD5值一致的文件不重复写入
     *
     * @param inputStream 文件流
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<Map<String, String>> saveFile(InputStream inputStream, FileDTO fileDTO) {
        String dir = DateUtil.format(new Date(), DateConstant.YYYY_MM);

        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String uploadPath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()
            + "/src/main/resources/" + fileProperties.getPath();
        File fileDir = new File(uploadPath + File.separator + dir);
        if (!FileUtil.exist(fileDir)) {
            boolean isOk = fileDir.mkdirs();
            if (!isOk) {
                return ResultInfo.fail("创建上传文件目录失败");
            }
        }

//        String loginId = StpUtil.getLoginIdAsString();
        String baseUrl = WebUtils.getBaseUrl();
        FileEntity fileEntity;
        try {
            redissonService.lock(FileConstants.LOCK_KEY_UPLOAD + "loginId");

            String fileName = UUID.fastUUID() + "." + fileDTO.getFileSuffix();
            File file = FileUtil.writeFromStream(inputStream, fileDir.getAbsolutePath() + File.separator + fileName);

            String fileMd5 = SecureUtil.md5(file);
            fileEntity = fileDao.findByFileMd5(fileMd5);
            if (fileEntity != null) {
                ThreadUtil.execute(() -> FileUtil.del(file));
                // 如果文件不一样，新增一条记录
                if (!fileDTO.getFileName().equalsIgnoreCase(fileEntity.getFileName())) {
                    FileEntity newFileEntity = BeanUtil.copyProperties(fileEntity, FileEntity.class);
                    newFileEntity.setFileId(null);
                    newFileEntity.setFileName(fileDTO.getFileName());
                    save(newFileEntity);
                }
                Map<String, String> map = new HashMap<>(2);
                map.put("uri", fileEntity.getFilePath());
                map.put("baseUrl", baseUrl);
                return ResultInfo.ok(map).message("上传文件成功");
            }

            // 文件相对路径
            fileEntity = BeanUtil.copyProperties(fileDTO, FileEntity.class);
            fileEntity.setFilePath("/" + fileProperties.getPath().substring(fileProperties.getPath().indexOf("/") + 1)
                + "/" + dir + "/" + fileName);
            fileEntity.setFileSize(file.length());
            fileEntity.setFileMd5(fileMd5);

            save(fileEntity);
        } finally {
            redissonService.unlock(FileConstants.LOCK_KEY_UPLOAD + "loginId");
        }

        Map<String, String> map = new HashMap<>(2);
        map.put("uri", fileEntity.getFilePath());
        map.put("baseUrl", baseUrl);
        return ResultInfo.ok(map).message("上传文件成功");
    }
}
