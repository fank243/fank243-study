package com.fank243.study.file.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fank243.study.common.core.annotation.RepeatSubmit;
import com.fank243.study.common.core.base.BaseController;
import com.fank243.study.common.core.constants.Constants;
import com.fank243.study.common.core.constants.ServerConstants;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.file.domain.dto.FileDTO;
import com.fank243.study.file.service.FileService;

import cn.hutool.core.io.FileTypeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件表 控制器
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_FILE)
@RestController
public class FileController extends BaseController {

    @Resource
    private FileService fileService;

    /**
     * 文件管理 > 上传文件
     * 
     * @param multipartFile 文件
     * @return 上传结果
     */
    @RepeatSubmit
    @PostMapping("/upload")
    public ResultInfo<?> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error("【上传文件】读取上传文件流异常：{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        String fileType = FileTypeUtil.getType(inputStream, multipartFile.getOriginalFilename());
        if (!Constants.FILE_TYPE.contains(fileType)) {
            return ResultInfo.err400("不支持的文件类型");
        }
        long fileSize = multipartFile.getSize() / 1024 / 1024;
        if (Constants.FILE_TYPE_IMG.contains(fileType) && fileSize > Constants.FILE_SIZE_IMG) {
            return ResultInfo.err400("文件大小不能超过" + Constants.FILE_SIZE_IMG + "MB");
        } else if (Constants.FILE_TYPE_OFFICE.contains(fileType) && fileSize > Constants.FILE_SIZE_OFFICE) {
            return ResultInfo.err400("文件大小不能超过" + Constants.FILE_SIZE_OFFICE + "MB");
        } else if (fileSize > Constants.FILE_SIZE) {
            return ResultInfo.err400("文件大小不能超过" + Constants.FILE_SIZE + "MB");
        }
        String fileName = multipartFile.getOriginalFilename();

        assert fileName != null;
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        FileDTO fileDTO = FileDTO.builder().fileName(fileName).fileType(fileType).fileSuffix(fileSuffix).build();

        return fileService.saveFile(multipartFile.getInputStream(), fileDTO);
    }
}