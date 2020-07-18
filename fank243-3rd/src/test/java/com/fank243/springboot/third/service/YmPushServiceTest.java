package com.fank243.springboot.third.service;

import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.third.service.ym.YmPushService;
import org.junit.Before;
import org.junit.Test;

/**
 * U-Push Test
 * 
 * @author FanWeiJie
 * @date 2020-03-24 16:03:56
 */
public class YmPushServiceTest {

    private YmPushService ymPushService = new YmPushService();

    @Before
    public void init() {
        ymPushService.setYmApiUrl("https://msgapi.umeng.com/api/send");
        ymPushService.setAndroidAppKey("AAAAAAAAAAAAAAA");
        ymPushService.setAndroidMasterSecret("BBBBBBBBBBBBBBBBS");
    }

    @Test
    public void sendAndroidWithUnicast() {
        ResultInfo result = ymPushService.sendAndroidWithUnicast("AAAAAAAAAAAAAAAAA", "Test", "Test Test Test ...");
        System.out.println(result);
    }

    @Test
    public void sendAndroidWithListcast() {}

    @Test
    public void sendIosWithUnicast() {}

    @Test
    public void sendIosWithListcast() {}
}