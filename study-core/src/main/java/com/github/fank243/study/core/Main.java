package com.github.fank243.study.core;

import java.math.BigDecimal;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.utils.ValidationUtils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
        JSONObject json = new JSONObject();
        json.set("title", "擦第三方");
        json.set("number", "1");
        json.set("amount", "1000.231");

        Test test = JSONUtil.toBean(json, Test.class);
        ResultInfo<?> result = ValidationUtils.validate(test);
        System.out.println(result);
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
