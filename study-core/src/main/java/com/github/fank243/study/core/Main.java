package com.github.fank243.study.core;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.github.fank243.study.core.constants.Constants;

import cn.hutool.core.collection.CollUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * 测试专用
 * 
 * @author FanWeiJie
 * @since 2022-10-03 02:35:28
 */
public class Main {

    public static void main(String[] args) {
        // JSONObject json = new JSONObject();
        // json.set("title", "擦第三方");
        // json.set("number", "1");
        // json.set("amount", "1000.231");
        //
        // Test test = JSONUtil.toBean(json, Test.class);
        // ResultInfo<?> result = ValidationUtils.validate(test);
        // System.out.println(result);
        List<String> list = Constants.FILE_PREFIX_NOT_LOGIN;
        String[] array = "/files/static/45c37cb1-8557-4ab4-b5e3-32a2e304afe2.png".split("/");

        boolean b = CollUtil.containsAny(Constants.FILE_PREFIX_NOT_LOGIN, Arrays.asList(array));
        System.out.println(b);
    }

    @Data
    static class Test {

        @NotBlank(message = "标题不能为空")
        private String title;

        @Positive(message = "number必须为正数")
        private Integer number;

        @Positive(message = "amount必须为正数")
        private BigDecimal amount;
    }
}
