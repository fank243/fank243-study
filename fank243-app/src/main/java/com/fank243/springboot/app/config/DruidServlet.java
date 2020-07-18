package com.fank243.springboot.app.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Druid 监控配置
 *
 * @author FanWeiJie
 * @date 2020-04-10 12:31:30
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "loginUsername", value = "admin"),
    @WebInitParam(name = "loginPassword", value = "admin")})
public class DruidServlet extends StatViewServlet {

}
