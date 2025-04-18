/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.support.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.fank243.kong.tool.result.ResultInfo;
import com.github.fank243.kong.core.annotation.RepeatSubmit;
import com.github.fank243.kong.core.base.BaseController;
import com.github.fank243.kong.core.constants.Constants;
import com.github.fank243.kong.core.constants.LogRecordType;
import com.github.fank243.kong.core.constants.ServerConstants;
import com.github.fank243.kong.core.utils.WebUtils;
import com.github.fank243.kong.support.domain.dto.FileDTO;
import com.github.fank243.kong.support.service.FileService;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件表 控制器
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_FILE)
@Controller
public class FileController extends BaseController {

    @Resource
    private FileService fileService;

    /**
     * 文件管理 > 上传文件
     *
     * @param multipartFile 文件
     * @return 上传结果
     */
    @LogRecord(type = LogRecordType.LOG_FILE, subType = "upload", bizNo = "{{#fileId}}",
        success = "上传文件【{{#fileName}}】成功，文件ID：{{#fileId}}", successCondition = "{{#_ret.success == true}}")
    @RepeatSubmit
    @ResponseBody
    @PostMapping("/upload")
    public ResultInfo<?> upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request)
        throws IOException {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error("【上传文件】读取上传文件流异常：{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        String filePrefix = request.getParameter("filePrefix"), loginId = request.getParameter("loginId");
        if (StrUtil.isNotBlank(filePrefix) && !Constants.FILE_PREFIX_ALL.contains(filePrefix)) {
            return ResultInfo.err400("不支持的文件前缀");
        }
        loginId = StrUtil.blankToDefault(loginId, StpUtil.getLoginIdAsString());

        String fileType = FileTypeUtil.getType(inputStream, multipartFile.getOriginalFilename());
        if (!Constants.FILE_TYPE.contains(fileType)) {
            return ResultInfo.err400("不支持的文件类型");
        }
        long fileSize = multipartFile.getSize() / 1024 / 1024;
        if (Constants.FILE_TYPE_IMAGE.contains(fileType) && fileSize > Constants.FILE_SIZE_IMG) {
            return ResultInfo.err400("文件大小不能超过" + Constants.FILE_SIZE_IMG + "MB");
        } else if (Constants.FILE_TYPE_OFFICE.contains(fileType) && fileSize > Constants.FILE_SIZE_OFFICE) {
            return ResultInfo.err400("文件大小不能超过" + Constants.FILE_SIZE_OFFICE + "MB");
        } else if (fileSize > Constants.FILE_SIZE) {
            return ResultInfo.err400("文件大小不能超过" + Constants.FILE_SIZE + "MB");
        }
        String fileName = multipartFile.getOriginalFilename();

        assert fileName != null;

        FileDTO fileDTO = FileDTO.builder().filePrefix(filePrefix).fileName(fileName).fileType(fileType)
            .fileType(fileType).loginId(loginId).build();

        ResultInfo<Long> result = fileService.saveFile(multipartFile.getInputStream(), fileDTO);
        if (!result.isSuccess()) {
            return result;
        }

        LogRecordContext.putVariable("fileId", result.getPayload());
        LogRecordContext.putVariable("fileName", fileDTO.getFileName());
        return result;
    }

    /**
     * 文件浏览|下载
     *
     * @param fileId 文件ID
     */
    @RequestMapping(value = "/{fileId}", method = {RequestMethod.GET, RequestMethod.POST})
    public void file(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) {
        try {
            File file = fileService.getFile(fileId);
            if (request.getParameterMap().containsKey("download")) {
                JakartaServletUtil.write(response, file);
            } else {
                WebUtils.write(response, file);
            }
        } catch (NotLoginException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            WebUtils.renderJson(response, ResultInfo.err401().error(e.toString()));
        } catch (NotPermissionException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            WebUtils.renderJson(response, ResultInfo.err403().error(e.toString()));
        } catch (Exception e) {
            log.error("文件失败：{}", e.getMessage(), e);
            WebUtils.renderJson(response, ResultInfo.err500("读取文件失败").error(e.toString()));
        }
    }
}