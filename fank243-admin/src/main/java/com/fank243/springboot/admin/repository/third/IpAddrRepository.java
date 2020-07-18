package com.fank243.springboot.admin.repository.third;

import com.fank243.springboot.core.entity.ipaddr.IpAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 太平洋IP地址库
 * 
 * @author FanWeiJie
 * @date 2020-03-08 17:13:52
 */
@Repository
public interface IpAddrRepository extends JpaRepository<IpAddr, Long> {

    IpAddr findByIp(String ip);
}
