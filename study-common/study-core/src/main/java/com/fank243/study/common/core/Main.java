package com.fank243.study.common.core;

import cn.hutool.crypto.SecureUtil;

/**
 * 测试专用
 * 
 * @author FanWeiJie
 * @since 2022-10-03 02:35:28
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("123456"));
    }
}
