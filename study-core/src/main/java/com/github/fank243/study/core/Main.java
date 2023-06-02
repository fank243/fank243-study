package com.github.fank243.study.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 测试专用
 * 
 * @author FanWeiJie
 * @since 2022-10-03 02:35:28
 */
public class Main {
    public static final String REGEX_CAR_NUM = "[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼,]";

    public static void main(String[] args) {
        String str = "京A0429X京A1114挂,京A12345京A0429X京A1114挂,京A12345京A0429X京A1114挂京A1234挂";
        str = str.replaceAll(",", "");

        List<String> list = new ArrayList<>();
        dd(list, str);
        System.out.println(list.stream().distinct().collect(Collectors.toList()));

    }

    public static void dd(List<String> list, String str) {
        int index = ReUtil.lastIndexOf(REGEX_CAR_NUM, str).start();
        String replace = StrUtil.replace(str, index, index, ",");
        for (String str2 : replace.split(",")) {
            int count = ReUtil.count(REGEX_CAR_NUM, str2);
            if (count > 1) {
                dd(list, str2);
            } else {
                list.add(str2);
            }
        }
    }
}
