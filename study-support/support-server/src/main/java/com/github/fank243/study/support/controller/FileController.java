package com.github.fank243.study.support.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.RepeatSubmit;
import com.github.fank243.study.core.base.BaseController;
import com.github.fank243.study.core.constants.Constants;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.support.domain.dto.FileDTO;
import com.github.fank243.study.support.service.FileService;

import cn.hutool.core.io.FileTypeUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
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
    public ResultInfo<?> upload(@RequestParam("file") MultipartFile multipartFile,
        @NotBlank(message = "文件根目录不能为空") String fileDir) throws IOException {
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

        FileDTO fileDTO =
            FileDTO.builder().fileDir(fileDir).fileName(fileName).fileType(fileType).fileSuffix(fileSuffix).build();

        return fileService.saveFile(multipartFile.getInputStream(), fileDTO);
    }
}