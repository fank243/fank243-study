package com.fank243.study.file.service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.common.constants.DateConstant;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.file.dao.IFileDao;
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
public class FileService extends ServiceImpl<IFileDao, FileEntity> {

    @Resource
    private IFileDao fileDao;
    @Resource
    private FileProperties fileProperties;

    /**
     * 上传文件，MD5值一致的文件不重复写入
     *
     * @param inputStream 文件流
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<String> saveFile(InputStream inputStream, FileDTO fileDTO) {
        String dir = DateUtil.format(new Date(), DateConstant.YYYY_MM);
        File fileDir = new File(fileProperties.getPath() + "/" + dir);
        if (!FileUtil.exist(fileDir)) {
            boolean isOk = fileDir.mkdirs();
            if (!isOk) {
                return ResultInfo.fail("创建上传文件目录失败");
            }
        }
        String fileName = UUID.fastUUID() + "." + fileDTO.getFileSuffix();
        File file = FileUtil.writeFromStream(inputStream, fileDir.getAbsolutePath() + "/" + fileName);

        String fileMd5 = SecureUtil.md5(file);
        FileEntity fileEntity = fileDao.findByFileMd5(fileMd5);
        if (fileEntity != null) {
            ThreadUtil.execute(() -> FileUtil.del(file));
            // 如果文件不一样，新增一条记录
            if (!fileDTO.getFileOriginalName().equalsIgnoreCase(fileEntity.getFileOriginalName())) {
                FileEntity newFileEntity = BeanUtil.copyProperties(fileEntity, FileEntity.class);
                newFileEntity.setFileId(null);
                newFileEntity.setFileOriginalName(fileDTO.getFileOriginalName());
                save(newFileEntity);
            }
            return ResultInfo.ok(fileEntity.getFileName()).message("上传文件成功");
        }

        // 文件相对路径
        fileEntity = BeanUtil.copyProperties(fileDTO, FileEntity.class);
        fileEntity.setFileName(file.getName());
        fileEntity.setFilePath(file.getAbsolutePath().replaceAll("\\\\", "/"));
        fileEntity.setFileSize(file.length());
        fileEntity.setFileMd5(fileMd5);

        save(fileEntity);

        return ResultInfo.ok(fileName).message("上传文件成功");
    }
}
