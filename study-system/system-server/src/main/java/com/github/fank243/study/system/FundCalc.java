package com.github.fank243.study.system;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class FundCalc {

    public static void main(String[] args) {
        // 3年
        ExcelReader excelReader3Year = ExcelUtil.getReader(new File("D:\\我的文档\\4433规则.xlsx"), 0);
        List<List<Object>> read3Year = excelReader3Year.read(1);
        List<Map<String, Object>> fundCodeList3Year = new ArrayList<>();
        read3Year.forEach(objects -> {
            Map<String, Object> map = new HashMap<>(3);
            map.put("code", String.format("%06d", Integer.parseInt(objects.get(0) + "")));
            map.put("name", objects.get(1));
            map.put("apr", NumberUtil.decimalFormat("#.##%", Convert.toDouble(objects.get(12), 0D)));
            fundCodeList3Year.add(map);
        });

        // 1年
        ExcelReader excelReader1Year = ExcelUtil.getReader(new File("D:\\我的文档\\4433规则.xlsx"), 1);
        List<List<Object>> read1Year = excelReader1Year.read(1);
        List<Map<String, Object>> fundCodeList1Year = new ArrayList<>();
        read1Year.forEach(objects -> {
            Map<String, Object> map = new HashMap<>(3);
            map.put("code", String.format("%06d", Integer.parseInt(objects.get(0) + "")));
            map.put("name", objects.get(1));
            map.put("apr", NumberUtil.decimalFormat("#.##%", Convert.toDouble(objects.get(10), 0D)));
            fundCodeList1Year.add(map);
        });

        // 6个月
        ExcelReader excelReader6Month = ExcelUtil.getReader(new File("D:\\我的文档\\4433规则.xlsx"), 1);
        List<List<Object>> read6Month = excelReader6Month.read(1);
        List<Map<String, Object>> fundCodeList6Month = new ArrayList<>();
        read6Month.forEach(objects -> {
            Map<String, Object> map = new HashMap<>(3);
            map.put("code", String.format("%06d", Integer.parseInt(objects.get(0) + "")));
            map.put("name", objects.get(1));
            map.put("apr", NumberUtil.decimalFormat("#.##%", Convert.toDouble(objects.get(9), 0D)));
            fundCodeList6Month.add(map);
        });

        // 3个月
        ExcelReader excelReader3Month = ExcelUtil.getReader(new File("D:\\我的文档\\4433规则.xlsx"), 1);
        List<List<Object>> read3Month = excelReader3Month.read(1);
        List<Map<String, Object>> fundCodeList3Month = new ArrayList<>();
        read3Month.forEach(objects -> {
            Map<String, Object> map = new HashMap<>(3);
            map.put("code", String.format("%06d", Integer.parseInt(objects.get(0) + "")));
            map.put("name", objects.get(1));
            map.put("apr", NumberUtil.decimalFormat("#.##%", Convert.toDouble(objects.get(8), 0D)));
            fundCodeList3Month.add(map);
        });

        List<Map<String, Object>> fundList = new ArrayList<>();
        for (Map<String, Object> year3 : fundCodeList3Year) {
            String year3Code = year3.get("code") + "";
            String year3Name = year3.get("name") + "";
            String year3apr = year3.get("apr") + "";
            for (Map<String, Object> year1 : fundCodeList1Year) {
                String year1Code = year1.get("code") + "";
                String year1apr = year1.get("apr") + "";
                for (Map<String, Object> month6 : fundCodeList6Month) {
                    String month6Code = month6.get("code") + "";
                    String month6apr = month6.get("apr") + "";
                    for (Map<String, Object> month3 : fundCodeList3Month) {
                        String month3Code = month3.get("code") + "";
                        String month3apr = month3.get("apr") + "";
                        if (year3Code.equals(year1Code) && year1Code.equals(month6Code)
                            && month6Code.equals(month3Code)) {
                            Map<String, Object> map = new HashMap<>(2);
                            map.put("code", year3Code);
                            map.put("name", year3Name);
                            map.put("year3apr", year3apr);
                            map.put("year1apr", year1apr);
                            map.put("month6apr", month6apr);
                            map.put("month3apr", month3apr);
                            if (!fundList.contains(map)) {
                                fundList.add(map);
                            }
                        }
                    }
                }
            }
        }
        fundList = fundList.stream().distinct().collect(Collectors.toList());
        fundList = fundList.stream().filter(stringObjectMap -> {
            Object aprObj = stringObjectMap.get("year1apr");
            return Convert.toDouble(aprObj, 0D) > 4;
        }).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(fundList));

        fundList.forEach(stringObjectMap -> {
            System.out.println(stringObjectMap.get("code") + "\t" + stringObjectMap.get("name") + "\t"
                + stringObjectMap.get("year3apr") + "\t" + stringObjectMap.get("year1apr") + "\t"
                + stringObjectMap.get("month6apr") + "\t" + stringObjectMap.get("month3apr"));
        });
    }
}
