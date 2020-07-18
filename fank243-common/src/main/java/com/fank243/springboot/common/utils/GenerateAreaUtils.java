package com.fank243.springboot.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 国家行政区代码JSON文件生成
 *
 * @author FanWeiJie
 * @date 2019-11-30 17:39:23
 */
@Slf4j
public class GenerateAreaUtils {

    private static Pattern areaCodePattern = Pattern.compile("[^0-9]");
    private static Pattern areaNamePattern = Pattern.compile("[^\\u4E00-\\u9FA5]");

    /** 直辖市、台港澳地区 **/
    private static List<String> cityList =
        Arrays.asList("110000", "120000", "310000", "500000", "710000", "810000", "820000");

    public static void main(String[] args) throws IOException {
        String dir = "/home/jason/develop/workspace/idea/fank/springboot/fank243-common/src/main/resources";
        File srcFile = new File(dir + "/area-202002.txt");
        if (!srcFile.exists()) {
            System.out.println("缺少源文件[area-202002.txt]");
            System.exit(-1);
        }

        String content = FileUtils.readFileToString(srcFile, StandardCharsets.UTF_8);
        String[] split = content.split("\n");

        JSONArray array = new JSONArray();

        // 省、自治区、直辖市
        for (String line : split) {
            String code = areaCodePattern.matcher(line).replaceAll("");
            String name = areaNamePattern.matcher(line).replaceAll("");

            if (code.matches("\\d{2}0000")) {
                JSONObject provJson = new JSONObject();
                provJson.put("code", Integer.parseInt(code));
                provJson.put("name", name);
                array.add(provJson);
            }
        }

        // 直辖市、城市
        for (Object obj : array) {
            JSONObject provJson = (JSONObject)obj;
            String provCode = provJson.getString("code");
            JSONArray cityArray = new JSONArray();
            for (String line : split) {
                String code = areaCodePattern.matcher(line).replaceAll("");
                String name = areaNamePattern.matcher(line).replaceAll("");
                if (code.equalsIgnoreCase(provCode) && !cityList.contains(code)) {
                    continue;
                }
                if (code.startsWith(provCode.substring(0, 2)) && code.endsWith("00")) {
                    JSONObject cityJson = new JSONObject();
                    cityJson.put("code", Integer.parseInt(code));
                    cityJson.put("name", name);
                    cityArray.add(cityJson);
                }
            }
            provJson.put("city", cityArray);
        }

        // 省市
        FileUtils.writeStringToFile(new File(dir + "/area-202002-city.json"), array.toString(), StandardCharsets.UTF_8);

        // 区县
        for (Object obj : array) {
            JSONObject provJson = (JSONObject)obj;
            JSONArray cityArray = provJson.getJSONArray("city");
            if (cityArray == null || cityArray.size() <= 0) {
                continue;
            }
            for (Object cityObj : cityArray) {
                JSONObject cityJson = (JSONObject)cityObj;
                String cityCode = cityJson.getString("code");
                JSONArray areaArray = new JSONArray();
                for (String line : split) {
                    String code = areaCodePattern.matcher(line).replaceAll("");
                    String name = areaNamePattern.matcher(line).replaceAll("");
                    if (code.equalsIgnoreCase(cityCode)) {
                        continue;
                    }
                    String subCode = code.substring(0, 2) + "0000";
                    if (cityList.contains(subCode)) {
                        if (code.startsWith(cityCode.substring(0, 2))) {
                            JSONObject areaJson = new JSONObject();
                            areaJson.put("code", Integer.parseInt(code));
                            areaJson.put("name", name);
                            areaArray.add(areaJson);
                        }
                    } else {
                        if (code.startsWith(cityCode.substring(0, 4))) {
                            JSONObject areaJson = new JSONObject();
                            areaJson.put("code", Integer.parseInt(code));
                            areaJson.put("name", name);
                            areaArray.add(areaJson);
                        }
                    }
                }
                cityJson.put("area", areaArray);
            }
            provJson.put("city", cityArray);
        }

        // 省市县
        FileUtils.writeStringToFile(new File(dir + "/area-202002.json"), array.toJSONString(), StandardCharsets.UTF_8);
    }
}
