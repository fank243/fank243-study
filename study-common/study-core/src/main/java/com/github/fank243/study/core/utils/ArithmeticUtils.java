package com.github.fank243.study.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.fank243.study.core.constants.Constants;

/**
 * 算术运算工具类
 * 
 * @author FanWeiJie
 * @since 2022-05-19 19:38:09
 */
public class ArithmeticUtils {

    /**
     * 格式化数值，根据小数位是否能够格式化整数形式
     * 
     * @param val 考虑到精度问题，不可直接使用double构造
     * @return 格式化后的数值
     */
    public static String format(String val) {
        return format(val, 0);
    }

    /**
     * 格式化数值，根据小数位是否能够格式化整数形式<br>
     * 示例：100 => 100， 100.00 => 100， 100.12 => 100.12
     * 
     * @param val 考虑到精度问题，不可直接使用double构造
     * @param scale 精度，四舍五入算法
     * @return 格式化后的数值
     */
    public static String format(String val, int scale) {
        BigDecimal bigDecimal = new BigDecimal(val);
        String str = bigDecimal.toPlainString();
        if (str.contains(Constants.PUNCTUATION_POINT)) {
            String[] spilt = str.split("\\.");
            long num = Long.parseLong(spilt[1]);
            if (num == 0) {
                return spilt[0];
            }
        }
        return scale > 0 ? bigDecimal.setScale(scale, RoundingMode.HALF_UP).toPlainString()
            : bigDecimal.toPlainString();
    }

    public static void main(String[] args) {
        double value = 100.30;
        System.out.println(format(String.valueOf(value)));
    }
}
