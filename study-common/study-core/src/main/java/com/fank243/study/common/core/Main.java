package com.fank243.study.common.core;

import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 测试专用
 * 
 * @author FanWeiJie
 * @since 2022-10-03 02:35:28
 */
public class Main {

    public static void main(String[] args) {
          SpelExpressionParser parser = new SpelExpressionParser();
          parser.parseExpression("'删除了管理员【' + #findUsername(#ids) + '】'");
    }
}
