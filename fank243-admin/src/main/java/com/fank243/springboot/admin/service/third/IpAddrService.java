package com.fank243.springboot.admin.service.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.repository.third.IpAddrRepository;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import com.fank243.springboot.third.service.ipaddr.IpQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 太平洋IP地址库
 * 
 * @author FanWeiJie
 * @date 2020-03-08 17:13:52
 */
@Slf4j
@Service
public class IpAddrService {

    @Resource
    private IpAddrRepository repository;
    @Resource
    private IpQueryService ipQueryService;

    public IpAddr findByIp(String ip) {
        return repository.findByIp(ip);
    }

    @Transactional(rollbackFor = Exception.class)
    public IpAddr getIpAddr(String ip) {
        ResultInfo result = ipQueryService.getIpAddr(ip);
        if (!result.isSuccess()) {
            log.debug("获取IP归属地失败 {}", ip);
            return null;
        }
        JSONObject json = JSON.parseObject(String.valueOf(result.getPayload()));
        ip = json.getString("ip");
        int proCode = json.getInteger("proCode");
        String pro = json.getString("pro");
        int cityCode = json.getInteger("cityCode");
        String city = json.getString("city");
        String addr = json.getString("addr");

        IpAddr ipAddr = new IpAddr();
        ipAddr.setIp(ip);
        ipAddr.setProCode(proCode);
        ipAddr.setPro(pro);
        ipAddr.setCityCode(cityCode);
        ipAddr.setCity(city);
        ipAddr.setAddr(addr);
        IpAddr ipAddr1 = findByIp(ip);
        if (ipAddr1 != null) {
            return ipAddr;
        }

        repository.save(ipAddr);

        return ipAddr;
    }

}
