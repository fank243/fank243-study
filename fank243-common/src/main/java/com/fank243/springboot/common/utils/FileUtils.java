package com.fank243.springboot.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;

/**
 * 通用工具类：常用文件操作工具
 *
 * @author FanWeiJie
 * @date 2015-2-12
 */
@Slf4j
public class FileUtils {

    /**
     * 获取classpath路径下的文件输入流
     *
     * @param fileName 文件名称
     * @return 文件输入流
     * @author FanWeiJie
     * @date 2015-2-12
     */
    public static InputStream getFileStream(String fileName) throws IOException {
        if (StringUtils.isBlank(fileName)) {
            log.error("the fileName is null");
        }
        return FileUtils.class.getResource(fileName).openStream();
    }

    /**
     * 获取classpath路径下的文件路径
     *
     * @param fileName 文件名称
     * @return 文件绝对路径
     * @author FanWeiJie
     * @date 2015-2-12
     */
    public static String getFilePath(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            log.error("The fileName is null");
            return null;
        }
        return FileUtils.class.getClassLoader().getResource(fileName).getPath();
    }

    /**
     * 写入文件到目标磁盘中
     *
     * @param in 文件输入流
     * @param targetPath 文件存放目标绝对路径(包含文件)
     * @author FanWeiJie
     * @date 2015-2-12
     */
    public static boolean writeFile(InputStream in, String targetPath) throws IOException {
        if (in == null) {
            log.error("The InputStream is null");
            return false;
        }
        if (StringUtils.isBlank(targetPath)) {
            log.error("The targetPath is null");
            return false;
        }
        OutputStream os = null;
        try {
            File file = new File(targetPath);
            if (file.isDirectory()) {
                log.error("The targetPath is Not a Directory");
                return false;
            }
            os = new FileOutputStream(file);
            int len;
            byte[] ch = new byte[1024];
            while ((len = in.read(ch)) != -1) {
                os.write(ch, 0, len);
            }

            os.flush();
        } catch (IOException e) {
            log.error("write file exception：{}", e);
            return false;
        } finally {
            close(os, null);
        }
        return true;
    }

    /**
     * 释放资源
     *
     * @author FanWeiJie
     * @date 2015-2-28
     */
    private static void close(OutputStream os, InputStream is) throws IOException {
        if (os != null) {
            os.close();
        }
        if (is != null) {
            is.close();
        }
    }

    /**
     * 获文件类型
     *
     * @param file 目标文件
     * @return MimeType
     * @author FanWeiJie
     * @date 2015-2-28
     */
    public static String getMimeType(File file) {
        return new MimetypesFileTypeMap().getContentType(file);
    }

    /**
     * 获取文件类型
     *
     * @param filePath 目标文件的绝对路径
     * @return MimeType
     * @author FanWeiJie
     * @date 2015-2-28
     */
    public static String getMimeType(String filePath) {
        return new MimetypesFileTypeMap().getContentType(filePath);
    }


}
