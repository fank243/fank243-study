/*
 Navicat Premium Data Transfer

 Source Server         : vbox-mysql
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 192.168.31.120:3306
 Source Schema         : jboot

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 13/09/2020 18:30:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_logdto
-- ----------------------------
DROP TABLE IF EXISTS `app_logdto`;
CREATE TABLE `app_logdto` (
  `id` bigint(20) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `log_level` varchar(255) DEFAULT NULL,
  `log_type` varchar(255) DEFAULT NULL,
  `log_desc` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `device_type` int(11) DEFAULT NULL,
  `device_number` varchar(255) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `request_ip` varchar(255) DEFAULT NULL,
  `request_ip_addr` varchar(255) DEFAULT NULL,
  `request_header` varchar(255) DEFAULT NULL,
  `request_body` varchar(255) DEFAULT NULL,
  `request_time` datetime DEFAULT NULL,
  `response_status` int(11) DEFAULT NULL,
  `response_body` varchar(255) DEFAULT NULL,
  `response_time` datetime DEFAULT NULL,
  `result_code` int(11) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_logdto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for email_recorddto
-- ----------------------------
DROP TABLE IF EXISTS `email_recorddto`;
CREATE TABLE `email_recorddto` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of email_recorddto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
COMMIT;

-- ----------------------------
-- Table structure for push_recorddto
-- ----------------------------
DROP TABLE IF EXISTS `push_recorddto`;
CREATE TABLE `push_recorddto` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `device_type` varchar(255) DEFAULT NULL,
  `device_token` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of push_recorddto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_recorddto
-- ----------------------------
DROP TABLE IF EXISTS `sms_recorddto`;
CREATE TABLE `sms_recorddto` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sms_recorddto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_logdto
-- ----------------------------
DROP TABLE IF EXISTS `sys_logdto`;
CREATE TABLE `sys_logdto` (
  `id` bigint(20) NOT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `log_level` varchar(255) DEFAULT NULL,
  `log_type` varchar(255) DEFAULT NULL,
  `log_desc` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `request_ip` varchar(255) DEFAULT NULL,
  `request_ip_addr` varchar(255) DEFAULT NULL,
  `request_header` varchar(255) DEFAULT NULL,
  `request_body` varchar(255) DEFAULT NULL,
  `response_status` int(11) DEFAULT NULL,
  `response_body` varchar(255) DEFAULT NULL,
  `result_code` int(11) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `rqeuest_header` varchar(255) DEFAULT NULL,
  `request_time` datetime DEFAULT NULL,
  `response_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_logdto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permissiondto
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissiondto`;
CREATE TABLE `sys_permissiondto` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pid_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `external` bit(1) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permissiondto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user_event_bean
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_event_bean`;
CREATE TABLE `sys_user_event_bean` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_event_bean
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user_eventdto
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_eventdto`;
CREATE TABLE `sys_user_eventdto` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_eventdto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_3rd_pconline
-- ----------------------------
DROP TABLE IF EXISTS `tb_3rd_pconline`;
CREATE TABLE `tb_3rd_pconline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `addr` varchar(50) NOT NULL DEFAULT '' COMMENT '详细地址(市级，国外为0)',
  `pro_code` int(6) NOT NULL DEFAULT '0' COMMENT '行政区划代码(省级，国外为999999)',
  `pro` varchar(30) NOT NULL DEFAULT '' COMMENT '行政区划名称(省级，国外为0)',
  `city_code` int(6) NOT NULL DEFAULT '0' COMMENT '行政区划代码(市级，国外为0)',
  `city` varchar(30) NOT NULL DEFAULT '' COMMENT '行政区划名称(市级，国外为0)',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='太平洋IP地址库';

-- ----------------------------
-- Records of tb_3rd_pconline
-- ----------------------------
BEGIN;
INSERT INTO `tb_3rd_pconline` VALUES (1, '36.157.195.84', '湖南省长沙市 移通', 430000, '湖南省', 430100, '长沙市', '2020-03-27 22:02:17');
INSERT INTO `tb_3rd_pconline` VALUES (2, '36.157.208.128', '湖南省长沙市 移通', 430000, '湖南省', 430100, '长沙市', '2020-04-01 22:25:27');
INSERT INTO `tb_3rd_pconline` VALUES (3, '111.22.182.125', '湖南省岳阳市 移通', 430000, '湖南省', 430600, '岳阳市', '2020-04-28 22:26:24');
INSERT INTO `tb_3rd_pconline` VALUES (4, '111.22.182.228', '湖南省岳阳市 移通', 430000, '湖南省', 430600, '岳阳市', '2020-07-18 13:20:57');
INSERT INTO `tb_3rd_pconline` VALUES (5, '111.22.182.254', '湖南省岳阳市 移通', 430000, '湖南省', 430600, '岳阳市', '2020-07-24 23:37:16');
INSERT INTO `tb_3rd_pconline` VALUES (6, '111.22.182.78', '湖南省岳阳市 移通', 430000, '湖南省', 430600, '岳阳市', '2020-09-12 22:03:32');
COMMIT;

-- ----------------------------
-- Table structure for tb_log_app
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_app`;
CREATE TABLE `tb_log_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `log_level` varchar(10) NOT NULL DEFAULT '' COMMENT '日志级别',
  `log_type` varchar(20) NOT NULL DEFAULT '' COMMENT '日志类别',
  `log_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '日志描述',
  `class_name` varchar(255) NOT NULL DEFAULT '' COMMENT '类名',
  `method_name` varchar(255) NOT NULL DEFAULT '' COMMENT '方法名',
  `device_type` varchar(20) DEFAULT '' COMMENT '设备类型',
  `device_number` varchar(48) DEFAULT '' COMMENT '设备号',
  `session_id` varchar(48) NOT NULL DEFAULT '' COMMENT 'Session ID',
  `request_id` varchar(48) NOT NULL DEFAULT '' COMMENT 'Request ID',
  `request_uri` varchar(48) NOT NULL DEFAULT '' COMMENT '请求URI',
  `request_method` varchar(48) NOT NULL DEFAULT '' COMMENT '请求URI',
  `request_ip` varchar(46) DEFAULT '' COMMENT '请求IP',
  `request_ip_addr` varchar(46) DEFAULT '' COMMENT '请求IP归属地',
  `request_header` longtext COMMENT '请求头参数',
  `request_body` longtext COMMENT '请求体参数',
  `request_time` timestamp NULL DEFAULT NULL COMMENT '请求时间',
  `response_status` int(3) NOT NULL DEFAULT '200' COMMENT '响应状态码',
  `response_body` longtext COMMENT '响应参数',
  `response_time` timestamp NULL DEFAULT NULL COMMENT '响应时间',
  `result_code` int(4) NOT NULL DEFAULT '0' COMMENT 'Result Code',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='App请求响应日志记录表';

-- ----------------------------
-- Records of tb_log_app
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_log_system
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_system`;
CREATE TABLE `tb_log_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '管理员ID',
  `log_level` varchar(10) NOT NULL DEFAULT '' COMMENT '日志级别',
  `log_type` varchar(20) NOT NULL DEFAULT '' COMMENT '日志类别',
  `log_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '日志描述',
  `class_name` varchar(255) NOT NULL DEFAULT '' COMMENT '类名',
  `method_name` varchar(255) NOT NULL DEFAULT '' COMMENT '方法名',
  `session_id` varchar(48) NOT NULL DEFAULT '' COMMENT 'Session ID',
  `request_id` varchar(48) NOT NULL DEFAULT '' COMMENT 'Request ID',
  `request_uri` varchar(48) NOT NULL DEFAULT '' COMMENT '请求URI',
  `request_method` varchar(48) NOT NULL DEFAULT '' COMMENT '请求URI',
  `request_ip` varchar(46) DEFAULT '' COMMENT '请求IP',
  `request_ip_addr` varchar(46) DEFAULT '' COMMENT '请求IP归属地',
  `request_header` longtext COMMENT '请求头参数',
  `request_body` longtext COMMENT '请求体参数',
  `request_time` timestamp NULL DEFAULT NULL COMMENT '请求时间',
  `response_status` int(3) NOT NULL DEFAULT '200' COMMENT '响应状态码',
  `response_body` longtext COMMENT '响应参数',
  `response_time` timestamp NULL DEFAULT NULL COMMENT '响应时间',
  `result_code` int(4) NOT NULL DEFAULT '0' COMMENT 'Result Code',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='系统请求响应日志记录表';

-- ----------------------------
-- Records of tb_log_system
-- ----------------------------
BEGIN;
INSERT INTO `tb_log_system` VALUES (1, 1, 'INFO', 'LOGIN', '登录系统', 'com.fank243.springboot.admin.controller.IndexController', 'login', '3a51ed3e-3c02-4b64-897e-11583fdd22cb', '2c017e37859b4630a2230cd45fcd2489', '/login', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"46\",\"referer\":\"http://localhost:8900/login.html\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; shiroCookie=3a51ed3e-3c02-4b64-897e-11583fdd22cb; rememberMe=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\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"password\":\"123456\",\"username\":\"admin\",\"rememberMe\":\"true\"}', '2020-07-24 23:40:20', 200, '{\"msg\":\"请求成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595605220011}', '2020-07-24 23:40:20', 200, '2020-07-24 23:40:20');
INSERT INTO `tb_log_system` VALUES (2, 1, 'INFO', 'LOGIN', '登录系统', 'com.fank243.springboot.admin.controller.IndexController', 'login', '8c7ebc8c-d6fb-460c-a5bd-ffda817e62c2', 'e6a1b19b0a0f486fb534247bf4d30d80', '/login', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"30\",\"referer\":\"http://localhost:8900/login.html\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; shiroCookie=8c7ebc8c-d6fb-460c-a5bd-ffda817e62c2\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"password\":\"123456\",\"username\":\"admin\"}', '2020-07-24 23:41:12', 200, '{\"msg\":\"请求成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595605272406}', '2020-07-24 23:41:12', 200, '2020-07-24 23:41:12');
INSERT INTO `tb_log_system` VALUES (3, 1, 'INFO', 'LOGIN', '登录系统', 'com.fank243.springboot.admin.controller.IndexController', 'login', 'a24130a2-4836-404f-9b12-fe4226dc12f6', '5c028ae5d2184f8a8546cc7447b705d4', '/login', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"30\",\"referer\":\"http://localhost:8900/login.html\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"password\":\"123456\",\"username\":\"admin\"}', '2020-07-24 23:55:59', 200, '{\"msg\":\"请求成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595606159487}', '2020-07-24 23:55:59', 200, '2020-07-24 23:56:08');
INSERT INTO `tb_log_system` VALUES (4, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '30662523-0d9e-498e-836a-45f78a4010eb', 'ef29e9aef5d4447baf9cb9980943e2c9', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=fc0c09bb-c0ad-492c-b268-14de34e9cf1a\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{}', '2020-07-25 00:17:50', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595607471239}', '2020-07-25 00:17:51', 200, '2020-07-25 00:17:51');
INSERT INTO `tb_log_system` VALUES (5, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '30662523-0d9e-498e-836a-45f78a4010eb', 'fdb0f940b9744b3493303609a0a60c21', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=30662523-0d9e-498e-836a-45f78a4010eb\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{}', '2020-07-25 00:17:53', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595607473439}', '2020-07-25 00:17:53', 200, '2020-07-25 00:17:53');
INSERT INTO `tb_log_system` VALUES (6, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '6b946aaa-62d3-48ab-b965-6664c1338d4b', '17b49cdefe5942a3bb9af8f5c3bd9968', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=c52aebf7-62b2-4480-957d-773d49f380c3\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:27:42', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608062405}', '2020-07-25 00:27:42', 200, '2020-07-25 00:27:42');
INSERT INTO `tb_log_system` VALUES (7, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '6b946aaa-62d3-48ab-b965-6664c1338d4b', '0431383fa43e4988b0acb9f0586ff54f', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=6b946aaa-62d3-48ab-b965-6664c1338d4b\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:27:43', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608063542}', '2020-07-25 00:27:44', 200, '2020-07-25 00:27:43');
INSERT INTO `tb_log_system` VALUES (8, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '6b946aaa-62d3-48ab-b965-6664c1338d4b', 'fd89faf5449b46cf9f0d99bfbabf4ff0', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=6b946aaa-62d3-48ab-b965-6664c1338d4b\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:28:05', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608084818}', '2020-07-25 00:28:05', 200, '2020-07-25 00:28:11');
INSERT INTO `tb_log_system` VALUES (9, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '3d576004-1fcb-47f3-bec6-512bc36d057f', 'd523399242f64ffabc869674a52666fb', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=3d576004-1fcb-47f3-bec6-512bc36d057f\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:29:10', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608150947}', '2020-07-25 00:29:11', 200, '2020-07-25 00:29:17');
INSERT INTO `tb_log_system` VALUES (10, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '61ed3040-7071-4eac-a7ae-479764a92141', '93d15f734e8a4a2c961ba08d48e3472d', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=3d576004-1fcb-47f3-bec6-512bc36d057f\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:30:35', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608236174}', '2020-07-25 00:30:36', 200, '2020-07-25 00:30:36');
INSERT INTO `tb_log_system` VALUES (11, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '61ed3040-7071-4eac-a7ae-479764a92141', 'd26ff5d1c6cd4eac8fdb832f47654339', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=rK4A6zOja+OfAOBCUu+pT3BQ5++INWDHeW4tj4o3/HYE1HpLoL4terLklHX21fUXSzJK33v6Np84pOlfUHCC97Hx54oOfO5c28+sHToIm9ZOCltPqQyXOEhMvEUNqyuDyde/VFWsFSkAIDzJ1BVO58ro4uYjnfk5zpUZdE972APddawcQs8iQGMvQC4UUA8HL41SzwE2Q8Sun6gfa6pc9pAtVRs4+zQSasf+C5MEcqKeyzi5nasOtfcydRWI3wV/XPRvuAov4w2LdX7NBNtmza1N1AdBsSE6gqpnaw1aKyrFAOjd55+gRLnYdrZ9wk8VFW6xr9Y/7i+B5YOaY0xhh6OIwAdzXmHX4pnVPk1MHKi8cC/Uu1rM6bolP0d4T3LBzxOBLo89HIp5qL5Uzp1frWWO+3PLU+BhVc1fG/nOENinSfDigcb0C0TCxf3JKzQ2x6IOxfsAHU8vlzNkfHnaUnEP4sZTX0A/CZnuigkXbaRFND1CKc+DPvfUpza+0UH63nmoq46h9SgFCcLV2dTKDSuoH6t3YT/W7vcdxLxx5r5CflfcABVCAwZHIkRdAPxC5hqPeyAD37GNIunTT1pFjOknmW/kwPHc3/mjVD3TQ1rnT6vYdMGm1PM0PQ0GZHP80gxXtLU863o8eWyyG5cwHQFutwu61eajwyPNd+Xr2dszM3n1v52KMOTqbB4JJXFPH4UaPs9s8RaeMeQHfglrG2KYe1ksVEKDGFx/5XBV/wh0FGvhMdxdQcyq2DtqVsfYu81Mkf+cNfg9ZiXRn4ZX+707HwPegzpZA2qY4PB5nQNaHSKrwmtQEaSN7HG7ENLw8A2CRXJ60AlQe6PoYTMo+uEpQjqkWPnnwe+2/lX16zZPvDZhd3KoeN5uj4U2Qb0xYbmGE4RqaOA42CbV8u+tihzFa9IES4R6moXh5mKI4DS0dwWBM9EjAdSW99sw7eWDTAz1HuDoncu2hs6MsqrAt4TAczY6TisJDVDsR+cEdImeP5K5UCCoBsffGwDfLl1tuLHHIxtHySJ6Js80gEHMtBCkwDRIb/JwOAy89XcIjoY9uthPR7B5Kk+DNZwwck1VpPKwzuJ9uNUFXjvJDuyO9PyVWW4DTvCchxH9LuZ6ULPNCmPmaIO4NpkKbv0eYTOZYFTuaMmzy/5zPtzbloSTi77JSGGbTv3NMDjnb8NphqsfbhDmXacUSvFdpXviQM5XDMPwgHFLlSXMmbCMitIizwhM7X+QJ/FoVF9xuTLz51oSmnM4m7W5cq0bnpJcBLU4sRTIWcebxLaOB/hRcJej2sByTbI4qTtQMrT9QoU0voW7+GrGBxtQYf+ID1Lmv739GNcwgnlexOFAhYd6SgUPfEYck3iygq0TBWcab0KLMt/JEFqhJcMUdWXtzRqiylBuryxnBLhpvN8/YNgdQ1MQvzk/YHW1QbpYHjh9YBp7YsG1b2LILaR0x5csLMtaY96UUdRfWGXPCf/4gs233z7PPNmYjE6jI6UH7D6kG3lzOifKJzLiHoFgBgpRHdVjHeVIDHmoppZ5IVpIRcvexvxN8sAutRgzN8EwZkbSUmg5HNd5N3zVEYIx3aPYttdqbBxP70Z2TBIyVnLX7zzG7fSqlvT48RjDoSo64UE5zbhs/XeW6KsmYuzV2lng/uL30Vw1tJBz2YAkyqM9Q/BC2DFIOFlEZTQHmVI/k8uz9JowrljJeH6gBdQtyytVSYp5Ud/7JG76fFgLBwhOVgxB4kcx5twOeCLfEfV4Qb3XU/VIcmphGRkfFqAByPgoWPOnunqiCIWCVqxEcMRSocCqZBFKqHjT0i6gPU3imLGRzWORtoV6M8TtMbQbaI7cpbyQJTOm05vT+34VrVInCJkR8Kb6IlULeMROt60E9jX3rMfAZAv3Ygh6W5lul59J8rlVCS6vx8mXO/YLFU/aNnEEJQuzzPIUHJcUI3UnLuAjWH1mMo9gq1AxeQhQXSPnKPrYh/NMFNCh9q4fQIaW7NUV6+Ssjw3w7rLPqB5+7l1UxfyTxW6LF0B9M9dWS3BuaSbk/nwNVnySqqtibF/1H+kMsH43huMzKNN697WYX3zRhBnWiBE3Io1pDDvVtW8JyBfml1hRh3628xSHd5LxTOFY9OFU75iGGSHA3vtb7z4r4WQkiaObAz1QruvquaZGZQnIWvzSAAHOq27fQGymA5dGfkwafAXJiUn7qir+0fHU3UIdGO+VcJftgc67885lvhCkaQ9XWveG6tjjed4b28DbCeuCFYcGZ3N9QyWbm8LJr04Mxg82rWqo3YU9QQzgMWpTOaibKJXF3fbirDCoIRjD0x5ZpxcbmJVJeu54kx+EcgIfVjqJMQc9vt7UKV2rfwEdnRRH5O0NXx+ac/CKCRBMv0OKjoWZJFFIKYD9764F5vcF1vqYFpTIFtGkthHNpBkHRH8AA8Zb7Fn3CtpRqQ2eAyGX0AhMjSxyersU6KkdcVP3vP8dK0X1C9GHJDNFuWJ4nWKda8V1ykUwaZCl2jNQoCSZb5PZ22WjRrT+ODMVX+/KRJCcDORnv7RTTHHRmqhG3WVhm39kJskH2hbky+GFxQFAthLdOKaXABK9I3qNhzPaR83Z0l3ezHloDqcY02VWZ6O8/yPAePtmrz1FY6pkJ14rouUCgvLyz2lKRWmJWR7ncl4t5O45T0DLmkAKTS0R868juzNlji1nuegSEST+k4b/VrudlvmJqxu4ypIMwIlW74+cwGKRpUwe4Rbr9d6iUsKMmaV/Ja4hYCsrynB02R5UZIu8DK9UMnS8xOk0be/7lkI0Yo7xx0JtQ9EvTmQvSR3G7rA49dbMAsk9z6UZinrwVkl0aERzeYyUApxhn+hwLfYSd9og7KwMqJImSraK0MeFlQ3NqvGxjfoaZMfqGiEw5Nv8NKi9LHXQ/jtf0USuSe3QGF9jGHtp+rwOj9kyQm+BiQePZHDJ75HeHbexXY02jOn9V70PsJXSQdW66NrRMW285hxqU4IixVVpV2ulWBJHxpN0goMPfcg64f8sf+2xrxmd73j/90GuJEixfBh1XA2ahayCwicdQQsbEsBWI7uNUbJa2YTqDMuWRdzAE9DAfHp/pP0VCAJ1VCHXMZ4YP15cFydTK5v+ISUi62iPACZoeFgq8Ph6evYDttFFgaNOnY4mmiJo1SRgF3SNn4PuzpGHWl7VNiGHGbS++nnfD69s7eNe9VkQvpXaG6XOnFx8zscKiBEG/+DCLQptVzbhU4tDzLZEogqyrXSyzhWTwAvwjIS0EBgjiQYEjMGxgKRJC3xLh7e3Hg1qHfQSFYVPKTFoJxRyVOW6u02JU1XPe41FXakGk3kHeqtInT+z+2pqVBCYuoNDf9c3U/C3+ceyCFw5G8XjKXUg0PYGOFhXHBXaSkk/lV5kh2uvjTPZcEPxrpNm9/MQ++sNWrTjnq61rBVF+4HrTWPEkK4cD/LzHpOqh4x5jQYs2QszmJrgd8nOpUJ7wB/14iG62X73meLOlOYCRBEMYBP0wsCUIwdWC2PTX9CjG4fw1XcsN0CUGJ5+tr+fiiswdrPtTbeXbDIhBvCu5NHczSw4siHAYN2bOEQQ9jTe3vUBwjt8kYtti91tlMA4pSTzzLLXyVoLHE8RKKyQ42Cmlox3yqs4VWvlvRPO5+34GODTePImm1tDdfH5lz1xBtDG8+/fOVlDBTQSS2UsQqmDWezZtxY6vM1/IVTj+wzwSJORQEZ2D6vOBuKyjbKXgrFd3j3PfolBxEJmxOQ3TfwXVOSlUd0Ztw3mM4nV//sC+b5/9iTp7cvn6QiY8R6tW9r5X76j1jORIx7ICgO8YNo=; shiroCookie=61ed3040-7071-4eac-a7ae-479764a92141\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:31:01', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608293299}', '2020-07-25 00:31:33', 200, '2020-07-25 00:31:33');
INSERT INTO `tb_log_system` VALUES (12, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '61ed3040-7071-4eac-a7ae-479764a92141', 'e409c2454fa541948a3e5213e31472e3', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=61ed3040-7071-4eac-a7ae-479764a92141\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:31:55', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608333909}', '2020-07-25 00:32:14', 200, '2020-07-25 00:32:14');
INSERT INTO `tb_log_system` VALUES (13, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '8babd308-7032-4ac7-aaf2-3f1deaa171f3', 'f9f79f04ecd74ee1b6424e498532854b', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=rK4A6zOja+OfAOBCUu+pT3BQ5++INWDHeW4tj4o3/HYE1HpLoL4terLklHX21fUXSzJK33v6Np84pOlfUHCC97Hx54oOfO5c28+sHToIm9ZOCltPqQyXOEhMvEUNqyuDyde/VFWsFSkAIDzJ1BVO58ro4uYjnfk5zpUZdE972APddawcQs8iQGMvQC4UUA8HL41SzwE2Q8Sun6gfa6pc9pAtVRs4+zQSasf+C5MEcqKeyzi5nasOtfcydRWI3wV/XPRvuAov4w2LdX7NBNtmza1N1AdBsSE6gqpnaw1aKyrFAOjd55+gRLnYdrZ9wk8VFW6xr9Y/7i+B5YOaY0xhh6OIwAdzXmHX4pnVPk1MHKi8cC/Uu1rM6bolP0d4T3LBzxOBLo89HIp5qL5Uzp1frWWO+3PLU+BhVc1fG/nOENinSfDigcb0C0TCxf3JKzQ2x6IOxfsAHU8vlzNkfHnaUnEP4sZTX0A/CZnuigkXbaRFND1CKc+DPvfUpza+0UH63nmoq46h9SgFCcLV2dTKDSuoH6t3YT/W7vcdxLxx5r5CflfcABVCAwZHIkRdAPxC5hqPeyAD37GNIunTT1pFjOknmW/kwPHc3/mjVD3TQ1rnT6vYdMGm1PM0PQ0GZHP80gxXtLU863o8eWyyG5cwHQFutwu61eajwyPNd+Xr2dszM3n1v52KMOTqbB4JJXFPH4UaPs9s8RaeMeQHfglrG2KYe1ksVEKDGFx/5XBV/wh0FGvhMdxdQcyq2DtqVsfYu81Mkf+cNfg9ZiXRn4ZX+707HwPegzpZA2qY4PB5nQNaHSKrwmtQEaSN7HG7ENLw8A2CRXJ60AlQe6PoYTMo+uEpQjqkWPnnwe+2/lX16zZPvDZhd3KoeN5uj4U2Qb0xYbmGE4RqaOA42CbV8u+tihzFa9IES4R6moXh5mKI4DS0dwWBM9EjAdSW99sw7eWDTAz1HuDoncu2hs6MsqrAt4TAczY6TisJDVDsR+cEdImeP5K5UCCoBsffGwDfLl1tuLHHIxtHySJ6Js80gEHMtBCkwDRIb/JwOAy89XcIjoY9uthPR7B5Kk+DNZwwck1VpPKwzuJ9uNUFXjvJDuyO9PyVWW4DTvCchxH9LuZ6ULPNCmPmaIO4NpkKbv0eYTOZYFTuaMmzy/5zPtzbloSTi77JSGGbTv3NMDjnb8NphqsfbhDmXacUSvFdpXviQM5XDMPwgHFLlSXMmbCMitIizwhM7X+QJ/FoVF9xuTLz51oSmnM4m7W5cq0bnpJcBLU4sRTIWcebxLaOB/hRcJej2sByTbI4qTtQMrT9QoU0voW7+GrGBxtQYf+ID1Lmv739GNcwgnlexOFAhYd6SgUPfEYck3iygq0TBWcab0KLMt/JEFqhJcMUdWXtzRqiylBuryxnBLhpvN8/YNgdQ1MQvzk/YHW1QbpYHjh9YBp7YsG1b2LILaR0x5csLMtaY96UUdRfWGXPCf/4gs233z7PPNmYjE6jI6UH7D6kG3lzOifKJzLiHoFgBgpRHdVjHeVIDHmoppZ5IVpIRcvexvxN8sAutRgzN8EwZkbSUmg5HNd5N3zVEYIx3aPYttdqbBxP70Z2TBIyVnLX7zzG7fSqlvT48RjDoSo64UE5zbhs/XeW6KsmYuzV2lng/uL30Vw1tJBz2YAkyqM9Q/BC2DFIOFlEZTQHmVI/k8uz9JowrljJeH6gBdQtyytVSYp5Ud/7JG76fFgLBwhOVgxB4kcx5twOeCLfEfV4Qb3XU/VIcmphGRkfFqAByPgoWPOnunqiCIWCVqxEcMRSocCqZBFKqHjT0i6gPU3imLGRzWORtoV6M8TtMbQbaI7cpbyQJTOm05vT+34VrVInCJkR8Kb6IlULeMROt60E9jX3rMfAZAv3Ygh6W5lul59J8rlVCS6vx8mXO/YLFU/aNnEEJQuzzPIUHJcUI3UnLuAjWH1mMo9gq1AxeQhQXSPnKPrYh/NMFNCh9q4fQIaW7NUV6+Ssjw3w7rLPqB5+7l1UxfyTxW6LF0B9M9dWS3BuaSbk/nwNVnySqqtibF/1H+kMsH43huMzKNN697WYX3zRhBnWiBE3Io1pDDvVtW8JyBfml1hRh3628xSHd5LxTOFY9OFU75iGGSHA3vtb7z4r4WQkiaObAz1QruvquaZGZQnIWvzSAAHOq27fQGymA5dGfkwafAXJiUn7qir+0fHU3UIdGO+VcJftgc67885lvhCkaQ9XWveG6tjjed4b28DbCeuCFYcGZ3N9QyWbm8LJr04Mxg82rWqo3YU9QQzgMWpTOaibKJXF3fbirDCoIRjD0x5ZpxcbmJVJeu54kx+EcgIfVjqJMQc9vt7UKV2rfwEdnRRH5O0NXx+ac/CKCRBMv0OKjoWZJFFIKYD9764F5vcF1vqYFpTIFtGkthHNpBkHRH8AA8Zb7Fn3CtpRqQ2eAyGX0AhMjSxyersU6KkdcVP3vP8dK0X1C9GHJDNFuWJ4nWKda8V1ykUwaZCl2jNQoCSZb5PZ22WjRrT+ODMVX+/KRJCcDORnv7RTTHHRmqhG3WVhm39kJskH2hbky+GFxQFAthLdOKaXABK9I3qNhzPaR83Z0l3ezHloDqcY02VWZ6O8/yPAePtmrz1FY6pkJ14rouUCgvLyz2lKRWmJWR7ncl4t5O45T0DLmkAKTS0R868juzNlji1nuegSEST+k4b/VrudlvmJqxu4ypIMwIlW74+cwGKRpUwe4Rbr9d6iUsKMmaV/Ja4hYCsrynB02R5UZIu8DK9UMnS8xOk0be/7lkI0Yo7xx0JtQ9EvTmQvSR3G7rA49dbMAsk9z6UZinrwVkl0aERzeYyUApxhn+hwLfYSd9og7KwMqJImSraK0MeFlQ3NqvGxjfoaZMfqGiEw5Nv8NKi9LHXQ/jtf0USuSe3QGF9jGHtp+rwOj9kyQm+BiQePZHDJ75HeHbexXY02jOn9V70PsJXSQdW66NrRMW285hxqU4IixVVpV2ulWBJHxpN0goMPfcg64f8sf+2xrxmd73j/90GuJEixfBh1XA2ahayCwicdQQsbEsBWI7uNUbJa2YTqDMuWRdzAE9DAfHp/pP0VCAJ1VCHXMZ4YP15cFydTK5v+ISUi62iPACZoeFgq8Ph6evYDttFFgaNOnY4mmiJo1SRgF3SNn4PuzpGHWl7VNiGHGbS++nnfD69s7eNe9VkQvpXaG6XOnFx8zscKiBEG/+DCLQptVzbhU4tDzLZEogqyrXSyzhWTwAvwjIS0EBgjiQYEjMGxgKRJC3xLh7e3Hg1qHfQSFYVPKTFoJxRyVOW6u02JU1XPe41FXakGk3kHeqtInT+z+2pqVBCYuoNDf9c3U/C3+ceyCFw5G8XjKXUg0PYGOFhXHBXaSkk/lV5kh2uvjTPZcEPxrpNm9/MQ++sNWrTjnq61rBVF+4HrTWPEkK4cD/LzHpOqh4x5jQYs2QszmJrgd8nOpUJ7wB/14iG62X73meLOlOYCRBEMYBP0wsCUIwdWC2PTX9CjG4fw1XcsN0CUGJ5+tr+fiiswdrPtTbeXbDIhBvCu5NHczSw4siHAYN2bOEQQ9jTe3vUBwjt8kYtti91tlMA4pSTzzLLXyVoLHE8RKKyQ42Cmlox3yqs4VWvlvRPO5+34GODTePImm1tDdfH5lz1xBtDG8+/fOVlDBTQSS2UsQqmDWezZtxY6vM1/IVTj+wzwSJORQEZ2D6vOBuKyjbKXgrFd3j3PfolBxEJmxOQ3TfwXVOSlUd0Ztw3mM4nV//sC+b5/9iTp7cvn6QiY8R6tW9r5X76j1jORIx7ICgO8YNo=; shiroCookie=61ed3040-7071-4eac-a7ae-479764a92141\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:33:11', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608422637}', '2020-07-25 00:33:43', 200, '2020-07-25 00:33:43');
INSERT INTO `tb_log_system` VALUES (14, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '8babd308-7032-4ac7-aaf2-3f1deaa171f3', '4aa5c396bee44d7cb6a3f2c761934960', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=8babd308-7032-4ac7-aaf2-3f1deaa171f3\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:33:45', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608453026}', '2020-07-25 00:34:13', 200, '2020-07-25 00:34:13');
INSERT INTO `tb_log_system` VALUES (15, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '8babd308-7032-4ac7-aaf2-3f1deaa171f3', 'a95586e860c64f3bbb3d36e6d463e5e7', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=rK4A6zOja+OfAOBCUu+pT3BQ5++INWDHeW4tj4o3/HYE1HpLoL4terLklHX21fUXSzJK33v6Np84pOlfUHCC97Hx54oOfO5c28+sHToIm9ZOCltPqQyXOEhMvEUNqyuDyde/VFWsFSkAIDzJ1BVO58ro4uYjnfk5zpUZdE972APddawcQs8iQGMvQC4UUA8HL41SzwE2Q8Sun6gfa6pc9pAtVRs4+zQSasf+C5MEcqKeyzi5nasOtfcydRWI3wV/XPRvuAov4w2LdX7NBNtmza1N1AdBsSE6gqpnaw1aKyrFAOjd55+gRLnYdrZ9wk8VFW6xr9Y/7i+B5YOaY0xhh6OIwAdzXmHX4pnVPk1MHKi8cC/Uu1rM6bolP0d4T3LBzxOBLo89HIp5qL5Uzp1frWWO+3PLU+BhVc1fG/nOENinSfDigcb0C0TCxf3JKzQ2x6IOxfsAHU8vlzNkfHnaUnEP4sZTX0A/CZnuigkXbaRFND1CKc+DPvfUpza+0UH63nmoq46h9SgFCcLV2dTKDSuoH6t3YT/W7vcdxLxx5r5CflfcABVCAwZHIkRdAPxC5hqPeyAD37GNIunTT1pFjOknmW/kwPHc3/mjVD3TQ1rnT6vYdMGm1PM0PQ0GZHP80gxXtLU863o8eWyyG5cwHQFutwu61eajwyPNd+Xr2dszM3n1v52KMOTqbB4JJXFPH4UaPs9s8RaeMeQHfglrG2KYe1ksVEKDGFx/5XBV/wh0FGvhMdxdQcyq2DtqVsfYu81Mkf+cNfg9ZiXRn4ZX+707HwPegzpZA2qY4PB5nQNaHSKrwmtQEaSN7HG7ENLw8A2CRXJ60AlQe6PoYTMo+uEpQjqkWPnnwe+2/lX16zZPvDZhd3KoeN5uj4U2Qb0xYbmGE4RqaOA42CbV8u+tihzFa9IES4R6moXh5mKI4DS0dwWBM9EjAdSW99sw7eWDTAz1HuDoncu2hs6MsqrAt4TAczY6TisJDVDsR+cEdImeP5K5UCCoBsffGwDfLl1tuLHHIxtHySJ6Js80gEHMtBCkwDRIb/JwOAy89XcIjoY9uthPR7B5Kk+DNZwwck1VpPKwzuJ9uNUFXjvJDuyO9PyVWW4DTvCchxH9LuZ6ULPNCmPmaIO4NpkKbv0eYTOZYFTuaMmzy/5zPtzbloSTi77JSGGbTv3NMDjnb8NphqsfbhDmXacUSvFdpXviQM5XDMPwgHFLlSXMmbCMitIizwhM7X+QJ/FoVF9xuTLz51oSmnM4m7W5cq0bnpJcBLU4sRTIWcebxLaOB/hRcJej2sByTbI4qTtQMrT9QoU0voW7+GrGBxtQYf+ID1Lmv739GNcwgnlexOFAhYd6SgUPfEYck3iygq0TBWcab0KLMt/JEFqhJcMUdWXtzRqiylBuryxnBLhpvN8/YNgdQ1MQvzk/YHW1QbpYHjh9YBp7YsG1b2LILaR0x5csLMtaY96UUdRfWGXPCf/4gs233z7PPNmYjE6jI6UH7D6kG3lzOifKJzLiHoFgBgpRHdVjHeVIDHmoppZ5IVpIRcvexvxN8sAutRgzN8EwZkbSUmg5HNd5N3zVEYIx3aPYttdqbBxP70Z2TBIyVnLX7zzG7fSqlvT48RjDoSo64UE5zbhs/XeW6KsmYuzV2lng/uL30Vw1tJBz2YAkyqM9Q/BC2DFIOFlEZTQHmVI/k8uz9JowrljJeH6gBdQtyytVSYp5Ud/7JG76fFgLBwhOVgxB4kcx5twOeCLfEfV4Qb3XU/VIcmphGRkfFqAByPgoWPOnunqiCIWCVqxEcMRSocCqZBFKqHjT0i6gPU3imLGRzWORtoV6M8TtMbQbaI7cpbyQJTOm05vT+34VrVInCJkR8Kb6IlULeMROt60E9jX3rMfAZAv3Ygh6W5lul59J8rlVCS6vx8mXO/YLFU/aNnEEJQuzzPIUHJcUI3UnLuAjWH1mMo9gq1AxeQhQXSPnKPrYh/NMFNCh9q4fQIaW7NUV6+Ssjw3w7rLPqB5+7l1UxfyTxW6LF0B9M9dWS3BuaSbk/nwNVnySqqtibF/1H+kMsH43huMzKNN697WYX3zRhBnWiBE3Io1pDDvVtW8JyBfml1hRh3628xSHd5LxTOFY9OFU75iGGSHA3vtb7z4r4WQkiaObAz1QruvquaZGZQnIWvzSAAHOq27fQGymA5dGfkwafAXJiUn7qir+0fHU3UIdGO+VcJftgc67885lvhCkaQ9XWveG6tjjed4b28DbCeuCFYcGZ3N9QyWbm8LJr04Mxg82rWqo3YU9QQzgMWpTOaibKJXF3fbirDCoIRjD0x5ZpxcbmJVJeu54kx+EcgIfVjqJMQc9vt7UKV2rfwEdnRRH5O0NXx+ac/CKCRBMv0OKjoWZJFFIKYD9764F5vcF1vqYFpTIFtGkthHNpBkHRH8AA8Zb7Fn3CtpRqQ2eAyGX0AhMjSxyersU6KkdcVP3vP8dK0X1C9GHJDNFuWJ4nWKda8V1ykUwaZCl2jNQoCSZb5PZ22WjRrT+ODMVX+/KRJCcDORnv7RTTHHRmqhG3WVhm39kJskH2hbky+GFxQFAthLdOKaXABK9I3qNhzPaR83Z0l3ezHloDqcY02VWZ6O8/yPAePtmrz1FY6pkJ14rouUCgvLyz2lKRWmJWR7ncl4t5O45T0DLmkAKTS0R868juzNlji1nuegSEST+k4b/VrudlvmJqxu4ypIMwIlW74+cwGKRpUwe4Rbr9d6iUsKMmaV/Ja4hYCsrynB02R5UZIu8DK9UMnS8xOk0be/7lkI0Yo7xx0JtQ9EvTmQvSR3G7rA49dbMAsk9z6UZinrwVkl0aERzeYyUApxhn+hwLfYSd9og7KwMqJImSraK0MeFlQ3NqvGxjfoaZMfqGiEw5Nv8NKi9LHXQ/jtf0USuSe3QGF9jGHtp+rwOj9kyQm+BiQePZHDJ75HeHbexXY02jOn9V70PsJXSQdW66NrRMW285hxqU4IixVVpV2ulWBJHxpN0goMPfcg64f8sf+2xrxmd73j/90GuJEixfBh1XA2ahayCwicdQQsbEsBWI7uNUbJa2YTqDMuWRdzAE9DAfHp/pP0VCAJ1VCHXMZ4YP15cFydTK5v+ISUi62iPACZoeFgq8Ph6evYDttFFgaNOnY4mmiJo1SRgF3SNn4PuzpGHWl7VNiGHGbS++nnfD69s7eNe9VkQvpXaG6XOnFx8zscKiBEG/+DCLQptVzbhU4tDzLZEogqyrXSyzhWTwAvwjIS0EBgjiQYEjMGxgKRJC3xLh7e3Hg1qHfQSFYVPKTFoJxRyVOW6u02JU1XPe41FXakGk3kHeqtInT+z+2pqVBCYuoNDf9c3U/C3+ceyCFw5G8XjKXUg0PYGOFhXHBXaSkk/lV5kh2uvjTPZcEPxrpNm9/MQ++sNWrTjnq61rBVF+4HrTWPEkK4cD/LzHpOqh4x5jQYs2QszmJrgd8nOpUJ7wB/14iG62X73meLOlOYCRBEMYBP0wsCUIwdWC2PTX9CjG4fw1XcsN0CUGJ5+tr+fiiswdrPtTbeXbDIhBvCu5NHczSw4siHAYN2bOEQQ9jTe3vUBwjt8kYtti91tlMA4pSTzzLLXyVoLHE8RKKyQ42Cmlox3yqs4VWvlvRPO5+34GODTePImm1tDdfH5lz1xBtDG8+/fOVlDBTQSS2UsQqmDWezZtxY6vM1/IVTj+wzwSJORQEZ2D6vOBuKyjbKXgrFd3j3PfolBxEJmxOQ3TfwXVOSlUd0Ztw3mM4nV//sC+b5/9iTp7cvn6QiY8R6tW9r5X76j1jORIx7ICgO8YNo=; shiroCookie=8babd308-7032-4ac7-aaf2-3f1deaa171f3\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:34:27', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608472162}', '2020-07-25 00:34:32', 200, '2020-07-25 00:34:32');
INSERT INTO `tb_log_system` VALUES (16, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', '8babd308-7032-4ac7-aaf2-3f1deaa171f3', 'e81fd8c871674714a142f9b86bbee4b8', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=8babd308-7032-4ac7-aaf2-3f1deaa171f3\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '', '2020-07-25 00:35:05', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608515562}', '2020-07-25 00:35:16', 200, '2020-07-25 00:35:15');
INSERT INTO `tb_log_system` VALUES (17, 1, 'INFO', 'SYS_SET', '修改站点配置', 'com.fank243.springboot.admin.controller.system.SysConfigController', 'modify', 'abec86bb-b6fc-4c16-aa1a-23840684c75f', '7591866d84e24bfcbc977964dc6b4490', '/admin/site/modify', 'POST', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"251\",\"referer\":\"http://localhost:8900/admin/site\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=7917bb31-38ec-42bc-a94e-22057feb75ea\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/json; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"site_name\":\"JBoot\",\"site_url\":\"https://www.fank243.com\",\"yapi_access_token\":\"d98d81c9980727a11fbf3cc1b7726e5d\",\"file_size\":\"2\",\"mine_type\":\"png|gif|jpg|jpeg|gif|bmp\",\"default_pwd\":\"123456\",\"site_copyright\":\"©2020 JBoot All rights resered\",\"type\":1}', '2020-07-25 00:42:21', 200, '{\"msg\":\"修改配置成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595608958790}', '2020-07-25 00:42:39', 200, '2020-07-25 00:42:39');
INSERT INTO `tb_log_system` VALUES (18, 1, 'INFO', 'SYS_USER', '修改管理员状态', 'com.fank243.springboot.admin.controller.system.SysUserController', 'modifyStatus', '498b0975-d1ce-4330-82db-157688aca414', 'cd7afc7427294814a9ce448a8f5b50ef', '/admin/sysuser/modify-status/1', 'PUT', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"0\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=715b35bc-14b7-414b-9e12-5631ad4f5d3a\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', NULL, '2020-07-25 00:46:53', 200, '{\"msg\":\"修改管理员状态成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595609213307}', '2020-07-25 00:46:53', 200, '2020-07-25 00:46:53');
INSERT INTO `tb_log_system` VALUES (19, 1, 'INFO', 'SYS_USER', '修改管理员状态', 'com.fank243.springboot.admin.controller.system.SysUserController', 'modifyStatus', '498b0975-d1ce-4330-82db-157688aca414', '3b19d3f37c5a4717a0528e84d114b5bd', '/admin/sysuser/modify-status/1', 'PUT', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"0\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=498b0975-d1ce-4330-82db-157688aca414\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', NULL, '2020-07-25 00:46:58', 200, '{\"msg\":\"修改管理员状态成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595609218035}', '2020-07-25 00:46:58', 200, '2020-07-25 00:46:58');
INSERT INTO `tb_log_system` VALUES (20, 1, 'INFO', 'SYS_USER', '修改管理员状态', 'com.fank243.springboot.admin.controller.system.SysUserController', 'modifyStatus', 'ac963d75-64d6-4eb4-ba5a-33d8d571c6c3', '9ee9aeb3772440b595dd6fd6cd0d4a3f', '/admin/sysuser/modify-status/1', 'PUT', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"0\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=rK4A6zOja+OfAOBCUu+pT3BQ5++INWDHeW4tj4o3/HYE1HpLoL4terLklHX21fUXSzJK33v6Np84pOlfUHCC97Hx54oOfO5c28+sHToIm9ZOCltPqQyXOEhMvEUNqyuDyde/VFWsFSkAIDzJ1BVO58ro4uYjnfk5zpUZdE972APddawcQs8iQGMvQC4UUA8HL41SzwE2Q8Sun6gfa6pc9pAtVRs4+zQSasf+C5MEcqKeyzi5nasOtfcydRWI3wV/XPRvuAov4w2LdX7NBNtmza1N1AdBsSE6gqpnaw1aKyrFAOjd55+gRLnYdrZ9wk8VFW6xr9Y/7i+B5YOaY0xhh6OIwAdzXmHX4pnVPk1MHKi8cC/Uu1rM6bolP0d4T3LBzxOBLo89HIp5qL5Uzp1frWWO+3PLU+BhVc1fG/nOENinSfDigcb0C0TCxf3JKzQ2x6IOxfsAHU8vlzNkfHnaUnEP4sZTX0A/CZnuigkXbaRFND1CKc+DPvfUpza+0UH63nmoq46h9SgFCcLV2dTKDSuoH6t3YT/W7vcdxLxx5r5CflfcABVCAwZHIkRdAPxC5hqPeyAD37GNIunTT1pFjOknmW/kwPHc3/mjVD3TQ1rnT6vYdMGm1PM0PQ0GZHP80gxXtLU863o8eWyyG5cwHQFutwu61eajwyPNd+Xr2dszM3n1v52KMOTqbB4JJXFPH4UaPs9s8RaeMeQHfglrG2KYe1ksVEKDGFx/5XBV/wh0FGvhMdxdQcyq2DtqVsfYu81Mkf+cNfg9ZiXRn4ZX+707HwPegzpZA2qY4PB5nQNaHSKrwmtQEaSN7HG7ENLw8A2CRXJ60AlQe6PoYTMo+uEpQjqkWPnnwe+2/lX16zZPvDZhd3KoeN5uj4U2Qb0xYbmGE4RqaOA42CbV8u+tihzFa9IES4R6moXh5mKI4DS0dwWBM9EjAdSW99sw7eWDTAz1HuDoncu2hs6MsqrAt4TAczY6TisJDVDsR+cEdImeP5K5UCCoBsffGwDfLl1tuLHHIxtHySJ6Js80gEHMtBCkwDRIb/JwOAy89XcIjoY9uthPR7B5Kk+DNZwwck1VpPKwzuJ9uNUFXjvJDuyO9PyVWW4DTvCchxH9LuZ6ULPNCmPmaIO4NpkKbv0eYTOZYFTuaMmzy/5zPtzbloSTi77JSGGbTv3NMDjnb8NphqsfbhDmXacUSvFdpXviQM5XDMPwgHFLlSXMmbCMitIizwhM7X+QJ/FoVF9xuTLz51oSmnM4m7W5cq0bnpJcBLU4sRTIWcebxLaOB/hRcJej2sByTbI4qTtQMrT9QoU0voW7+GrGBxtQYf+ID1Lmv739GNcwgnlexOFAhYd6SgUPfEYck3iygq0TBWcab0KLMt/JEFqhJcMUdWXtzRqiylBuryxnBLhpvN8/YNgdQ1MQvzk/YHW1QbpYHjh9YBp7YsG1b2LILaR0x5csLMtaY96UUdRfWGXPCf/4gs233z7PPNmYjE6jI6UH7D6kG3lzOifKJzLiHoFgBgpRHdVjHeVIDHmoppZ5IVpIRcvexvxN8sAutRgzN8EwZkbSUmg5HNd5N3zVEYIx3aPYttdqbBxP70Z2TBIyVnLX7zzG7fSqlvT48RjDoSo64UE5zbhs/XeW6KsmYuzV2lng/uL30Vw1tJBz2YAkyqM9Q/BC2DFIOFlEZTQHmVI/k8uz9JowrljJeH6gBdQtyytVSYp5Ud/7JG76fFgLBwhOVgxB4kcx5twOeCLfEfV4Qb3XU/VIcmphGRkfFqAByPgoWPOnunqiCIWCVqxEcMRSocCqZBFKqHjT0i6gPU3imLGRzWORtoV6M8TtMbQbaI7cpbyQJTOm05vT+34VrVInCJkR8Kb6IlULeMROt60E9jX3rMfAZAv3Ygh6W5lul59J8rlVCS6vx8mXO/YLFU/aNnEEJQuzzPIUHJcUI3UnLuAjWH1mMo9gq1AxeQhQXSPnKPrYh/NMFNCh9q4fQIaW7NUV6+Ssjw3w7rLPqB5+7l1UxfyTxW6LF0B9M9dWS3BuaSbk/nwNVnySqqtibF/1H+kMsH43huMzKNN697WYX3zRhBnWiBE3Io1pDDvVtW8JyBfml1hRh3628xSHd5LxTOFY9OFU75iGGSHA3vtb7z4r4WQkiaObAz1QruvquaZGZQnIWvzSAAHOq27fQGymA5dGfkwafAXJiUn7qir+0fHU3UIdGO+VcJftgc67885lvhCkaQ9XWveG6tjjed4b28DbCeuCFYcGZ3N9QyWbm8LJr04Mxg82rWqo3YU9QQzgMWpTOaibKJXF3fbirDCoIRjD0x5ZpxcbmJVJeu54kx+EcgIfVjqJMQc9vt7UKV2rfwEdnRRH5O0NXx+ac/CKCRBMv0OKjoWZJFFIKYD9764F5vcF1vqYFpTIFtGkthHNpBkHRH8AA8Zb7Fn3CtpRqQ2eAyGX0AhMjSxyersU6KkdcVP3vP8dK0X1C9GHJDNFuWJ4nWKda8V1ykUwaZCl2jNQoCSZb5PZ22WjRrT+ODMVX+/KRJCcDORnv7RTTHHRmqhG3WVhm39kJskH2hbky+GFxQFAthLdOKaXABK9I3qNhzPaR83Z0l3ezHloDqcY02VWZ6O8/yPAePtmrz1FY6pkJ14rouUCgvLyz2lKRWmJWR7ncl4t5O45T0DLmkAKTS0R868juzNlji1nuegSEST+k4b/VrudlvmJqxu4ypIMwIlW74+cwGKRpUwe4Rbr9d6iUsKMmaV/Ja4hYCsrynB02R5UZIu8DK9UMnS8xOk0be/7lkI0Yo7xx0JtQ9EvTmQvSR3G7rA49dbMAsk9z6UZinrwVkl0aERzeYyUApxhn+hwLfYSd9og7KwMqJImSraK0MeFlQ3NqvGxjfoaZMfqGiEw5Nv8NKi9LHXQ/jtf0USuSe3QGF9jGHtp+rwOj9kyQm+BiQePZHDJ75HeHbexXY02jOn9V70PsJXSQdW66NrRMW285hxqU4IixVVpV2ulWBJHxpN0goMPfcg64f8sf+2xrxmd73j/90GuJEixfBh1XA2ahayCwicdQQsbEsBWI7uNUbJa2YTqDMuWRdzAE9DAfHp/pP0VCAJ1VCHXMZ4YP15cFydTK5v+ISUi62iPACZoeFgq8Ph6evYDttFFgaNOnY4mmiJo1SRgF3SNn4PuzpGHWl7VNiGHGbS++nnfD69s7eNe9VkQvpXaG6XOnFx8zscKiBEG/+DCLQptVzbhU4tDzLZEogqyrXSyzhWTwAvwjIS0EBgjiQYEjMGxgKRJC3xLh7e3Hg1qHfQSFYVPKTFoJxRyVOW6u02JU1XPe41FXakGk3kHeqtInT+z+2pqVBCYuoNDf9c3U/C3+ceyCFw5G8XjKXUg0PYGOFhXHBXaSkk/lV5kh2uvjTPZcEPxrpNm9/MQ++sNWrTjnq61rBVF+4HrTWPEkK4cD/LzHpOqh4x5jQYs2QszmJrgd8nOpUJ7wB/14iG62X73meLOlOYCRBEMYBP0wsCUIwdWC2PTX9CjG4fw1XcsN0CUGJ5+tr+fiiswdrPtTbeXbDIhBvCu5NHczSw4siHAYN2bOEQQ9jTe3vUBwjt8kYtti91tlMA4pSTzzLLXyVoLHE8RKKyQ42Cmlox3yqs4VWvlvRPO5+34GODTePImm1tDdfH5lz1xBtDG8+/fOVlDBTQSS2UsQqmDWezZtxY6vM1/IVTj+wzwSJORQEZ2D6vOBuKyjbKXgrFd3j3PfolBxEJmxOQ3TfwXVOSlUd0Ztw3mM4nV//sC+b5/9iTp7cvn6QiY8R6tW9r5X76j1jORIx7ICgO8YNo=; shiroCookie=ac963d75-64d6-4eb4-ba5a-33d8d571c6c3\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"id\":\"1\"}', '2020-07-25 00:49:27', 200, '{\"msg\":\"修改管理员状态成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595609367899}', '2020-07-25 00:49:28', 200, '2020-07-25 00:49:28');
INSERT INTO `tb_log_system` VALUES (21, 1, 'INFO', 'SYS_USER', '修改管理员状态', 'com.fank243.springboot.admin.controller.system.SysUserController', 'modifyStatus', 'ac963d75-64d6-4eb4-ba5a-33d8d571c6c3', '1434966e1eb44afaadf21aca91ceb3d3', '/admin/sysuser/modify-status/1', 'PUT', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"0\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=ac963d75-64d6-4eb4-ba5a-33d8d571c6c3\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"id\":\"1\"}', '2020-07-25 00:49:29', 200, '{\"msg\":\"修改管理员状态成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595609369353}', '2020-07-25 00:49:29', 200, '2020-07-25 00:49:29');
INSERT INTO `tb_log_system` VALUES (22, 1, 'INFO', 'SYS_USER', '重置管理员密码', 'com.fank243.springboot.admin.controller.system.SysUserController', 'resetPassword', '997d5391-864b-4ada-b3b7-a70f10ba30d0', '0473420ea3174977aff2d60f2ec0b8d4', '/admin/sysuser/reset-password/1', 'PUT', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"0\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=997d5391-864b-4ada-b3b7-a70f10ba30d0\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"id\":\"1\"}', '2020-07-25 00:52:01', 200, '{\"msg\":\"重置管理员登录密码成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595609521945}', '2020-07-25 00:52:02', 200, '2020-07-25 00:52:02');
INSERT INTO `tb_log_system` VALUES (23, 1, 'WARN', 'SYS_USER', '删除管理员', 'com.fank243.springboot.admin.controller.system.SysUserController', 'delete', 'c1466692-c4f1-4ce4-b87e-7de9cdb33683', '28d1447b7e104febb0eea1b910fd7dbf', '/admin/sysuser/delete/1', 'DELETE', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=c1466692-c4f1-4ce4-b87e-7de9cdb33683\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"id\":\"1\"}', '2020-07-25 00:53:39', 200, '{\"msg\":\"删除管理员成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595609625315}', '2020-07-25 00:53:45', 200, '2020-07-25 00:53:45');
INSERT INTO `tb_log_system` VALUES (24, 1, 'INFO', 'SYS_USER', '修改管理员状态', 'com.fank243.springboot.admin.controller.system.SysUserController', 'modifyStatus', '275aa881-ca25-434d-8a3a-271641af9c5e', 'e11e0bca8ecc4b3294450028eb488383', '/admin/sysuser/modify-status/3', 'PUT', '111.22.182.254', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"0\",\"referer\":\"http://localhost:8900/admin/sysuser\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=275aa881-ca25-434d-8a3a-271641af9c5e\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"id\":\"3\"}', '2020-07-25 01:04:43', 200, '{\"msg\":\"修改管理员状态成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1595610284224}', '2020-07-25 01:04:44', 200, '2020-07-25 01:04:44');
INSERT INTO `tb_log_system` VALUES (25, 1, 'INFO', 'SYS_USER', '修改管理员', 'com.fank243.springboot.admin.controller.system.SysUserController', 'addOrModify', '0a6cf6eb-0947-4a8c-ab44-d654d3809a53', '36b7dffec5854fc4b7c272dd58555890', '/admin/sysuser/addOrModify', 'POST', '111.22.182.78', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"107\",\"referer\":\"http://localhost:8900/admin/sysuser/add\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=0a6cf6eb-0947-4a8c-ab44-d654d3809a53\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"roles\":[],\"mobile\":\"13212345678\",\"loginErrCount\":0,\"realname\":\"李思\",\"isDeleted\":false,\"status\":0,\"username\":\"测试\",\"wxNumber\":\"\"} 1', '2020-09-13 17:20:39', 200, '{\"msg\":\"用户名不合法\",\"code\":201,\"payload\":\"\",\"success\":false,\"timestamp\":1599988839335}', '2020-09-13 17:20:39', 201, '2020-09-13 05:59:26');
INSERT INTO `tb_log_system` VALUES (26, 1, 'INFO', 'SYS_USER', '修改管理员', 'com.fank243.springboot.admin.controller.system.SysUserController', 'addOrModify', '0a6cf6eb-0947-4a8c-ab44-d654d3809a53', '9c2bfb8a4d9c4f25bf75fffe836e184a', '/admin/sysuser/addOrModify', 'POST', '111.22.182.78', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"95\",\"referer\":\"http://localhost:8900/admin/sysuser/add\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=0a6cf6eb-0947-4a8c-ab44-d654d3809a53\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"roles\":[],\"mobile\":\"13212345678\",\"loginErrCount\":0,\"realname\":\"李思\",\"isDeleted\":false,\"status\":0,\"username\":\"lisi11\",\"wxNumber\":\"\"} 1', '2020-09-13 17:20:45', 200, '{\"msg\":\"手机号码已存在\",\"code\":201,\"payload\":\"\",\"success\":false,\"timestamp\":1599988845703}', '2020-09-13 17:20:46', 201, '2020-09-13 05:59:33');
INSERT INTO `tb_log_system` VALUES (27, 1, 'INFO', 'SYS_USER', '修改管理员', 'com.fank243.springboot.admin.controller.system.SysUserController', 'addOrModify', '0a6cf6eb-0947-4a8c-ab44-d654d3809a53', '823b3015ced044c4a42ea47d8f21e672', '/admin/sysuser/addOrModify', 'POST', '111.22.182.78', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"95\",\"referer\":\"http://localhost:8900/admin/sysuser/add\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=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; shiroCookie=0a6cf6eb-0947-4a8c-ab44-d654d3809a53\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"roles\":[],\"mobile\":\"13412345678\",\"loginErrCount\":0,\"realname\":\"李思\",\"isDeleted\":false,\"status\":0,\"username\":\"lisi11\",\"wxNumber\":\"\"} 1', '2020-09-13 17:20:52', 200, '{\"msg\":\"添加管理员成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1599988852194}', '2020-09-13 17:20:52', 200, '2020-09-13 05:59:40');
INSERT INTO `tb_log_system` VALUES (28, 1, 'INFO', 'SYS_USER', '修改管理员', 'com.fank243.springboot.admin.controller.system.SysUserController', 'addOrModify', '0a6cf6eb-0947-4a8c-ab44-d654d3809a53', '9aeb5dfccb8645c18eb79dfa7ae85ac1', '/admin/sysuser/addOrModify', 'POST', '111.22.182.78', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"105\",\"referer\":\"http://localhost:8900/admin/sysuser/add\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN; rememberMe=Mhhozo12k0dAOWNEpcmSyQzq6Rh2Rqojqot367dGxtmZIuJC0yxRba+gTwp6v8nCz6PTy0+k6v+tjk2Lq/YQgm2NeX/uHBEPkrOlxwvObiMRQ/lMLEsaY4+ndDC6U8fZg/ajp1ZFxUEDSZYWl/kEg1he77kqB3IZEHFg27ElMztDADQx1i4pYa6p3dm66VWnCiAGNF5fSe+tXwMz2GugJLeTgeSPO5xo/uI6DOYlf6cgz9ibCLxgm3SAFyviNDvPgV6ULlRUPObtqMQlbIQ3EVhovvlEPM44/5ZLQ5OrxXARfoF1MlPY65EjbtqKi/PjFftcjksFxWNv+4OtfiSG9FYaB26hvG8UKYPMIDu0F1v9ykC1vXOxjcYw6xX3sm5MUZpcfzDpdy6jtBB2MI/trabmdcU7cMWhKfvCyaNCLYXHoiYOZ9mq2kUbR0yAEU37F6D8wh1StCiQyTIcJQB6vBKdDci2Hqfh9pXCpZJDG8Ox7/HOPM85QXi2B2b3tD4jr0kONPDbXuQSgCnYG6DTzWopCDgTo+lTs40Yn6xc9X+P4vK9IR5oXaxWG5ciDCudpOwLzQRub7ukZJZDw9th5TXaFQ+cdACnfE/oaaG/ShVP4VXfFcHKRd/E8un3seOKRWKz35Fft5w2p/brTRAhwrjy0illaMhQcfvyaT8BJ+8ntOqSl1dPiIC+UZZrme8eXdZx9WB90XlQ3+m4fx+fR1N4AVvHhJsqt0sZYKjQp6PG9WoCIao8OyR1SkIx0tf3aHI42dl4+sDnEimQhfROTh86S44kIK7I4SBnyG+imkjO/OhbpjVYjzbAM9jz+fvLH0po0CVkDUzNkRlyzBIufOEOkyJcwoig5zcPhw1mm0KP5XJrRGx3DEdLq4yPM0FxpdkY2eHMmw2dssI4gYFaZh5cMh/sZ6ejMexOmTl33S0u/djtFQNAOpyCgXAPwV521XXrpwZLSdp5+NLsioUad4l4Mu2UxyHRcGTP7VkwUA04hEtLmUbmv318s4ek+y3O8vhqIKCMysYoHd3bnQIJmallOFThjFyD5xJIS6IczBhEflRL0l5ZeNEgq9QGEXmu0V3qO5Gy+6oIGAM7lqzlJV7/tGuUce1sWixjJjCT9GlCUgITzaHgLNGrcFOfuYIkGyK2zjznxH6OmeLhm5F9aAQ7BAWWSHQK6r7dkFP9WQam7PuEXIap9yH6/FlEKd7wk4obdWQmn3E8VPf7I8nUddiuNCroh7KmUluV2pvRKH0pjY88Sv/UeCntkciLCvemODcTG5FENjdNY7yBVz2EgUZ+TkA4Kwb8dsaNd4vhxQqbiC/ylFuyYNYzpSKOAGkfMstDMV2r+xhaXN0eyd/VWLUgi3ckJ2cl8H8y9m5DIHdMlV6TwAiYT/TFdSK1gRCzxYfBSWNWgmOQqOsvRsJNjJwj//mAMFWErHhdZn0MlWcei/jDlxqXNYoJJnms7sdtxGGXoH6y7ZdFjJcVLbZi1ee9FuyCoxwafuAnBJ6W+XXvDY1CAGq7pCTjF+Rv2NtW1cx3Lo8YxHi46bcwZw0IRVraAA+3k1NVUqupBjMWVIkBjJ5bFsEL9USxj/gMAIwbAdeKzuyIgU/M8Z3NzbxzU6obm+cPxNh8NjqqTMmQIKU44T1LKt6WM5YIxGUAqJROWa4K5BMCX1we8oI15ejcvbJ+bjwsUBHuUqn60KwSZ7FedRsValWgxIUHBiaGH4jGqlMD2qxJjVMKpbXS9TDGpEf5HtkVmgVs+QaSO2uzIxOhI07XPeZ6+8bWpUJBlarpP7ksqvTE7zrvLRfZkWUIHAy0qtPeDsmwOHTyk25lxtUSqFCt3FaSTmn8/BhC3NVe7xWnRx/Vj/nwy+6V/+j7xfa7aek5++y8gtGqmL+Y11PiyWjauFtDroUgaL0u1hGHdulOP+fbQagyJqBF1VmBV1vvYLXGBzRhLxxlbn0xtX3SiinPIR59XlhXpKXzO2eOx4vFerk96+s3Yvf0GhwCcXJsV09Oma/9/lnKUPxxyqN+cb/gvG6xRemASUIjZ3bBKasVqY5SeMO0B5/A2SCfZoIOijtnMOQ/lX5D8xunJ2ycnH3PQ858H8n7uvBhaLYJcur03P3aqOs9fpws8U/UUD/RGMnaXI0dTQTKI7Hvj/OY3NS9woVnJU3bC7R98KwL+e5IjMQiN/EAAZrEUkdF9vD01SAiCyMUjBuf4Uva4C1/yLiuh6iUocgKSYv/fFQVXQZSZvJuPxhWpIw/N44YLBGcygL2J6bp2rkeztKRlFRgJBcOdb+dZCBaYzA758RDuczgxCBEndf7ILxcJkGI/pRQQCvWp0sZzpITlqhp8M6QpzlRkGjjT25vo28rUp/tzE460uxiXYR6Gn/JSv57YESUR7nTFthIFiO5HKq291DNAnhh9tFKQQFSDercgkCybrobeoUBno9ihdy+J59db3j14byBnMtjPFXhCRKv813YtBG2dgm0TRKURfSgj13cLc/yw/AA6YN0/9BOylFsOPAWXxseMqLYPHMRokH2oN92/j1l+m9kr401qbgUz7SZ2jiWFvwUhxYY3JM4drRoYylHbRCSXjBrQT7i9XxmZWkX8/SxVinkIIy30mBD+YIK7vts5Oz0OyfYd4CKtUZS/o4LZg2kp5qigLS3HnNYpF/1g0c9mhpnzZ5a3kRRDnW1kJpl9wAVeIZyAyWpz7UoOH6DIb6QdAGe9wB3EyfISPEw2Z6k24IB+m4Unvr5DjRpVYJNu8vn8cc/+KKfUR2eHn8LJV7CXZIW/3pZPVVqwBwUR3hHxhRsglnnH/FE+w6cMD/2jvh4XHhazzsru95sdcWOUsUUHJFavz/pH/0TGF4T4tEV2ql5O4wyefWkoGrIbS6kXxT+GdqKp7XUhzyDFTl/ieK2lGmFZVuw0s/KxRIGj6FXSOUqKAVXjgHq7B3W7B0T+Vb/QDpoGq4BlJ9uUi1NT7zuZHQEQb9A0mBKcI8kiaFviYRwzkbg2/tHW7GgQfK9KO0VYmSvA/QWX/TLxhrhCstBkJGWe9xIdo/Ml/Bnet3Dbj7FeQfmchmMZvgV6LVfy+ayBDPsW9FBx50AvifRu/3f6Ws7P3g1ctDyGe2Z3q0N5lee+sacQ8jtWO6aSey1mMsrmEZ43zEmIs0J3cjCb+GJvFtQ7SB0JPexLrTt1Ctc7aodXJbKH3pmQGpKlpa/HSoTL+XMhU7EGGlHurhjC/kDY4jgNQF6qOoBHun5LTRMPcNu9HBCo0YL+FzYZKnk8VP4qkrBaAo/7goIj09Q92KoZSSkqhur11FTFjvx/H9b88vwBDakBTIUWw2/+eoAOxSQrVZVf0PR+6mtE1Jv+kN2lFGK06hKTg7l0DMY0TQD+YhalJ3WydILTZdVxYuOoVSLFe4UkxdFNjaagQR2tT66Xrdr8iiy7/NHao2lzGpq+2itLe/GujwyuQlZmKI4owsNRUZtop7tA4b9KO1FE8lLzOn91FUqssypdRHZ/+xR5VYVbUQ3RtJhlL0z0NMTdWLob4hUxgH5lcEeO8ivvBoVmD1fyrA8xcLdSWIHHEUEbhIFq03uOgLQhbb39j2Rf6BG4GtJsYmRFynnzkZ8Dw4h+1ipXxauIBmNKYYH2zSNnD9/FtFzXVfTYFeJupaU4jl/tA5WB4WxeP47JPNJyvbl08zKQgo98lP+A4MkPWvhcqt8C6VkYR3Qb29QA7LvypOPxPFh7999aPmtOyohmpzUM5zn70T0cRsPCoDFF7qOmB64kEcThvRvRhsxI1SVqfnynNOKFSszbswD+mhkxXM+J3v4BmoFsAhFPJ17hbs=; shiroCookie=0a6cf6eb-0947-4a8c-ab44-d654d3809a53\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"roles\":[],\"mobile\":\"13288990099\",\"loginErrCount\":0,\"realname\":\"李思思\",\"isDeleted\":false,\"status\":1,\"username\":\"lisi001\",\"wxNumber\":\"\"} 2', '2020-09-13 17:21:40', 200, '{\"msg\":\"添加管理员成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1599988900704}', '2020-09-13 17:21:41', 200, '2020-09-13 06:00:33');
INSERT INTO `tb_log_system` VALUES (29, 1, 'INFO', 'LOGIN', '登录系统', 'com.fank243.springboot.admin.controller.IndexController', 'login', '0cca3cfb-29e3-4d1b-b81f-50aae5e75de2', 'd022fb9744b34d6a8ea4739adedbe015', '/login', 'POST', '111.22.182.78', '湖南省岳阳市 移通', '{\"sec-fetch-mode\":\"cors\",\"content-length\":\"46\",\"referer\":\"http://localhost:8900/login.html\",\"sec-fetch-site\":\"same-origin\",\"accept-language\":\"zh,en;q=0.9,zh-CN;q=0.8\",\"cookie\":\"_ga=GA1.1.363379067.1582545354; lang=zh-CN\",\"origin\":\"http://localhost:8900\",\"dnt\":\"1\",\"accept\":\"*/*\",\"host\":\"localhost:8900\",\"x-requested-with\":\"XMLHttpRequest\",\"connection\":\"keep-alive\",\"content-type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36\",\"sec-fetch-dest\":\"empty\"}', '{\"password\":\"123456\",\"rememberMe\":true,\"username\":\"admin\"}', '2020-09-13 17:51:02', 200, '{\"msg\":\"请求成功\",\"code\":200,\"payload\":\"\",\"success\":true,\"timestamp\":1599990662245}', '2020-09-13 17:51:02', 200, '2020-09-13 06:32:21');
COMMIT;

-- ----------------------------
-- Table structure for tb_record_email
-- ----------------------------
DROP TABLE IF EXISTS `tb_record_email`;
CREATE TABLE `tb_record_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '电子邮箱地址',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '邮件主题',
  `content` longtext COMMENT 'HTML内容',
  `ip` varchar(46) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `ip_addr` varchar(30) DEFAULT '' COMMENT 'IP归属地',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子邮件发送记录表';

-- ----------------------------
-- Records of tb_record_email
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_record_push
-- ----------------------------
DROP TABLE IF EXISTS `tb_record_push`;
CREATE TABLE `tb_record_push` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `device_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '设备类型(0：未知，1：安卓，2：苹果)',
  `device_token` varchar(64) NOT NULL DEFAULT '' COMMENT '推送设备号',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '通知主题',
  `content` longtext COMMENT '通知内容',
  `ip` varchar(46) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `ip_addr` varchar(30) DEFAULT '' COMMENT 'IP归属地',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推送通知发送记录表';

-- ----------------------------
-- Records of tb_record_push
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_record_sms
-- ----------------------------
DROP TABLE IF EXISTS `tb_record_sms`;
CREATE TABLE `tb_record_sms` (
  `id` bigint(20) NOT NULL,
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `content` longtext COMMENT 'HTML内容',
  `ip` varchar(46) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `ip_addr` varchar(30) DEFAULT '' COMMENT 'IP归属地',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信发送记录表';

-- ----------------------------
-- Records of tb_record_sms
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE,
  KEY `FKn1fuq85qvjb8i00n532hji0k5` (`permission_id`) USING BTREE,
  CONSTRAINT `FKgtg5vqxwmv8e5bu48jr03986d` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`),
  CONSTRAINT `FKn1fuq85qvjb8i00n532hji0k5` FOREIGN KEY (`permission_id`) REFERENCES `tb_sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_permission` VALUES (1, 1);
INSERT INTO `tb_role_permission` VALUES (2, 1);
INSERT INTO `tb_role_permission` VALUES (1, 2);
INSERT INTO `tb_role_permission` VALUES (2, 2);
INSERT INTO `tb_role_permission` VALUES (1, 3);
INSERT INTO `tb_role_permission` VALUES (2, 3);
INSERT INTO `tb_role_permission` VALUES (1, 4);
INSERT INTO `tb_role_permission` VALUES (2, 4);
INSERT INTO `tb_role_permission` VALUES (1, 5);
INSERT INTO `tb_role_permission` VALUES (2, 5);
INSERT INTO `tb_role_permission` VALUES (1, 6);
INSERT INTO `tb_role_permission` VALUES (2, 6);
INSERT INTO `tb_role_permission` VALUES (1, 7);
INSERT INTO `tb_role_permission` VALUES (2, 7);
INSERT INTO `tb_role_permission` VALUES (1, 8);
INSERT INTO `tb_role_permission` VALUES (2, 8);
INSERT INTO `tb_role_permission` VALUES (1, 9);
INSERT INTO `tb_role_permission` VALUES (2, 9);
INSERT INTO `tb_role_permission` VALUES (1, 10);
INSERT INTO `tb_role_permission` VALUES (2, 10);
INSERT INTO `tb_role_permission` VALUES (1, 11);
INSERT INTO `tb_role_permission` VALUES (2, 11);
INSERT INTO `tb_role_permission` VALUES (1, 12);
INSERT INTO `tb_role_permission` VALUES (2, 12);
INSERT INTO `tb_role_permission` VALUES (1, 13);
INSERT INTO `tb_role_permission` VALUES (2, 13);
INSERT INTO `tb_role_permission` VALUES (1, 14);
INSERT INTO `tb_role_permission` VALUES (2, 14);
INSERT INTO `tb_role_permission` VALUES (1, 15);
INSERT INTO `tb_role_permission` VALUES (2, 15);
INSERT INTO `tb_role_permission` VALUES (1, 16);
INSERT INTO `tb_role_permission` VALUES (2, 16);
INSERT INTO `tb_role_permission` VALUES (1, 17);
INSERT INTO `tb_role_permission` VALUES (2, 17);
INSERT INTO `tb_role_permission` VALUES (1, 18);
INSERT INTO `tb_role_permission` VALUES (2, 18);
INSERT INTO `tb_role_permission` VALUES (1, 19);
INSERT INTO `tb_role_permission` VALUES (2, 19);
INSERT INTO `tb_role_permission` VALUES (1, 20);
INSERT INTO `tb_role_permission` VALUES (2, 20);
INSERT INTO `tb_role_permission` VALUES (1, 21);
INSERT INTO `tb_role_permission` VALUES (2, 21);
INSERT INTO `tb_role_permission` VALUES (1, 22);
INSERT INTO `tb_role_permission` VALUES (2, 22);
INSERT INTO `tb_role_permission` VALUES (1, 23);
INSERT INTO `tb_role_permission` VALUES (2, 23);
INSERT INTO `tb_role_permission` VALUES (1, 24);
INSERT INTO `tb_role_permission` VALUES (2, 24);
INSERT INTO `tb_role_permission` VALUES (1, 25);
INSERT INTO `tb_role_permission` VALUES (1, 26);
INSERT INTO `tb_role_permission` VALUES (1, 27);
INSERT INTO `tb_role_permission` VALUES (1, 28);
INSERT INTO `tb_role_permission` VALUES (1, 29);
INSERT INTO `tb_role_permission` VALUES (1, 30);
INSERT INTO `tb_role_permission` VALUES (1, 31);
INSERT INTO `tb_role_permission` VALUES (1, 32);
INSERT INTO `tb_role_permission` VALUES (2, 32);
INSERT INTO `tb_role_permission` VALUES (1, 33);
INSERT INTO `tb_role_permission` VALUES (2, 33);
INSERT INTO `tb_role_permission` VALUES (1, 34);
INSERT INTO `tb_role_permission` VALUES (2, 34);
INSERT INTO `tb_role_permission` VALUES (1, 35);
INSERT INTO `tb_role_permission` VALUES (1, 36);
INSERT INTO `tb_role_permission` VALUES (1, 37);
INSERT INTO `tb_role_permission` VALUES (1, 38);
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_config`;
CREATE TABLE `tb_sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_key` varchar(30) NOT NULL DEFAULT '' COMMENT '键',
  `sys_value` varchar(500) NOT NULL DEFAULT '' COMMENT '值',
  `description` varchar(30) DEFAULT '' COMMENT '描述',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_jusrsh41rp7cngcoxxgreodqm` (`sys_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统配置表';

-- ----------------------------
-- Records of tb_sys_config
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_config` VALUES (1, 'sms_sign_name', 'JBoot', '阿里云短信-签名名称', '2019-11-04 11:25:46', '2020-03-24 22:13:57');
INSERT INTO `tb_sys_config` VALUES (2, 'aliyun_access_secret', 'AAAAAAAAAAAAAAAAAA', '阿里云-AccessSecret', '2019-11-04 11:26:05', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (3, 'aliyun_access_key_id', 'BBBBBBBBBBBBBBBBB', '阿里云-AccessKeyId', '2019-11-04 11:26:13', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (4, 'email_smtp', 'smtp.exmail.qq.com', 'SMTP发送地址', '2019-11-04 11:27:31', '2019-11-23 17:29:05');
INSERT INTO `tb_sys_config` VALUES (5, 'email_port', '465', '发送端口', '2019-11-04 11:27:43', '2019-11-23 17:29:05');
INSERT INTO `tb_sys_config` VALUES (7, 'email_nickname', 'JBoot', '发送人昵称', '2019-11-04 11:27:53', '2020-03-24 22:14:34');
INSERT INTO `tb_sys_config` VALUES (8, 'email_username', 'JBoot@163.com', '登录账号', '2019-11-04 11:28:00', '2019-11-23 17:29:05');
INSERT INTO `tb_sys_config` VALUES (9, 'email_password', '123456', '登录密码', '2019-11-04 11:28:15', '2019-11-23 17:29:05');
INSERT INTO `tb_sys_config` VALUES (10, 'aliyun_endpoint', 'oss-cn-hangzhou.aliyuncs.com', '区域', '2019-11-08 16:13:47', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (11, 'aliyun_oss_bucket', 'xxx-bucket', 'OSS Bucket ', '2019-11-08 16:16:59', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (12, 'site_name', 'JBoot', '网站名称', '2019-11-23 16:56:58', '2020-03-24 22:14:06');
INSERT INTO `tb_sys_config` VALUES (13, 'site_url', 'https://www.fank243.com', '网站域名', '2019-11-23 16:57:13', '2020-03-24 22:14:16');
INSERT INTO `tb_sys_config` VALUES (14, 'site_copyright', '©2020 JBoot All rights resered', '版权信息', '2019-11-23 16:59:56', '2020-03-24 22:14:26');
INSERT INTO `tb_sys_config` VALUES (15, 'file_size', '2', '最大文件上传', '2019-11-23 17:00:19', '2020-03-07 15:00:14');
INSERT INTO `tb_sys_config` VALUES (16, 'mine_type', 'png|gif|jpg|jpeg|gif|bmp', '上传文件类型', '2019-11-23 17:00:36', '2020-03-07 15:00:14');
INSERT INTO `tb_sys_config` VALUES (17, 'default_pwd', '123456', '默认密码', '2019-11-23 17:00:58', '2020-03-24 22:14:42');
INSERT INTO `tb_sys_config` VALUES (18, 'sms_day_max', '15', '短信日发送量最大数', '2019-11-23 17:25:59', '2019-11-30 16:12:26');
INSERT INTO `tb_sys_config` VALUES (19, 'email_day_max', '25', '邮件日发送量最大数', '2019-11-23 17:26:07', '2019-11-23 17:29:05');
INSERT INTO `tb_sys_config` VALUES (20, 'android_version', '1.1.1', 'Android 版本号', '2019-11-30 15:41:36', '2020-03-07 15:00:23');
INSERT INTO `tb_sys_config` VALUES (21, 'android_download_url', 'https://www.aliyun.com/minisite/goods?userCode=bz1e7aji', 'Apk 下载地址', '2019-11-30 15:41:40', '2020-03-07 15:00:23');
INSERT INTO `tb_sys_config` VALUES (22, 'android_force_upgrade_version', '1.0.0,1.0.1', '强制升级版本号列表', '2019-11-30 15:42:10', '2020-03-07 15:00:23');
INSERT INTO `tb_sys_config` VALUES (23, 'ios_version', '1.1.0', 'IOS 版本号', '2019-11-30 15:42:15', '2020-03-07 15:00:23');
INSERT INTO `tb_sys_config` VALUES (24, 'ios_download_url', 'https://www.aliyun.com/minisite/goods?userCode=bz1e7aji', 'IOS 商城地址', '2019-11-30 15:42:17', '2020-03-07 15:00:23');
INSERT INTO `tb_sys_config` VALUES (25, 'ios_force_upgrade_version', '1.0.0,1.0.1', '强制升级版本号列表', '2019-11-30 15:43:22', '2020-03-07 15:00:23');
INSERT INTO `tb_sys_config` VALUES (26, 'ym_api_url', 'https://msgapi.umeng.com/api/send', '友盟Api URL', '2019-12-21 14:23:28', '2019-12-26 15:13:23');
INSERT INTO `tb_sys_config` VALUES (27, 'ym_android_app_key', 'AAAAAAAAAAAAAAAAAAAAAAA', '友盟AppKey', '2019-12-21 14:23:32', '2019-12-26 15:13:23');
INSERT INTO `tb_sys_config` VALUES (29, 'ym_android_master_secret', 'BBBBBBBBBBBBBBBBBBBBBBB', '友盟App Master Secret', '2019-12-21 14:23:47', '2019-12-26 15:13:23');
INSERT INTO `tb_sys_config` VALUES (30, 'ym_ios_app_key', 'AAAAAAAAAAAAAAAAAAAAAAA', '友盟AppKey', '2019-12-21 14:23:32', '2019-12-26 15:13:23');
INSERT INTO `tb_sys_config` VALUES (32, 'ym_ios_master_secret', 'BBBBBBBBBBBBBBBBBBBBBBB', '友盟App Master Secret', '2019-12-21 14:23:47', '2019-12-26 15:13:23');
INSERT INTO `tb_sys_config` VALUES (33, 'yapi_access_token', 'd98d81c9980727a11fbf3cc1b7726e5d', '授权凭证', '2020-01-08 16:22:31', '2020-03-07 15:00:14');
INSERT INTO `tb_sys_config` VALUES (34, 'aliyun_oss_domain', 'https://xxx-bucket.oss-cn-hangzhou.aliyuncs.com', 'OSS域名', '2020-01-08 16:22:31', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (35, 'aliyun_vod_domain', 'http://vod.xxx.com', '视频点播域名', '2020-01-08 16:22:31', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (36, 'aliyun_oss_callback', 'http://xxx.com/callback/ossReturnUrl', 'OSS回调地址', '2020-02-25 16:25:55', '2020-02-25 16:28:45');
INSERT INTO `tb_sys_config` VALUES (37, 'app_name', 'JBoot', 'App名称', '2020-03-07 14:52:12', '2020-03-07 15:00:23');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_dict_data`;
CREATE TABLE `tb_sys_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_label` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认（1是 0否）',
  `dict_sort` int(4) NOT NULL DEFAULT '0' COMMENT '字典排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `list_class` varchar(100) NOT NULL DEFAULT '' COMMENT '表格回显样式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- ----------------------------
-- Records of tb_sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_dict_data` VALUES (1, 'sys_user_status', '正常', '0', 1, 0, 0, '', '2020-09-12 22:23:17', '2020-09-12 22:23:17', '');
INSERT INTO `tb_sys_dict_data` VALUES (2, 'sys_user_status', '禁用', '1', 0, 0, 0, '', '2020-09-12 22:23:32', '2020-09-12 22:23:32', '');
INSERT INTO `tb_sys_dict_data` VALUES (4, 'user_status', '正常', '0', 1, 0, 0, '', '2020-09-12 22:52:44', '2020-09-12 22:52:44', '');
INSERT INTO `tb_sys_dict_data` VALUES (5, 'user_status', '禁用', '1', 0, 0, 0, '', '2020-09-12 22:52:45', '2020-09-12 22:52:45', 'warm');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_dict_type`;
CREATE TABLE `tb_sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_name` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='字典类别表';

-- ----------------------------
-- Records of tb_sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_dict_type` VALUES (1, 'sys_user_status', '管理员状态', 0, '', '2020-09-12 22:22:48', '2020-09-12 22:22:48');
INSERT INTO `tb_sys_dict_type` VALUES (2, 'user_status', '用户状态', 0, '', '2020-09-12 22:52:15', '2020-09-12 22:52:15');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_permission`;
CREATE TABLE `tb_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点ID(0：一级权限)',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '资源名称',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '资源类型(button,menu)',
  `permission` varchar(30) NOT NULL DEFAULT '' COMMENT '权限',
  `uri` varchar(255) DEFAULT '' COMMENT 'URI',
  `external` tinyint(1) unsigned DEFAULT '0' COMMENT '是否外部链接(0:否,1:是)',
  `icon` varchar(30) DEFAULT '' COMMENT '图标',
  `sort` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '序号',
  `available` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可用(0:否，1：是)',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of tb_sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_permission` VALUES (1, 0, '主页', 'MENU', '', '', 0, 'layui-icon-home', 0, 1, '2020-03-25 00:10:36', '2020-03-29 19:55:03');
INSERT INTO `tb_sys_permission` VALUES (2, 0, '用户管理', 'MENU', '', '', 0, 'layui-icon-username', 1, 1, '2020-03-25 00:12:02', '2020-07-18 15:06:48');
INSERT INTO `tb_sys_permission` VALUES (3, 2, '用户列表', 'MENU', 'user:query', '/admin/user', 0, 'layui-icon-user', 0, 1, '2020-03-25 00:12:36', '2020-07-18 15:30:53');
INSERT INTO `tb_sys_permission` VALUES (4, 2, '操作日志', 'MENU', 'user-event:query', '/admin/user-event', 0, 'layui-icon-about', 1, 1, '2020-03-25 23:34:37', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (5, 0, '系统设置', 'MENU', '', '', 0, 'layui-icon-set', 2, 1, '2020-03-25 00:10:48', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (6, 5, '管理员列表', 'MENU', 'sysuser:query', '/admin/sysuser', 0, 'layui-icon-user', 0, 1, '2020-03-25 00:13:19', '2020-07-18 15:31:32');
INSERT INTO `tb_sys_permission` VALUES (7, 5, '角色管理', 'MENU', 'role:query', '/admin/role', 0, 'layui-icon-group', 1, 1, '2020-03-25 00:13:31', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (8, 5, '权限管理', 'MENU', 'perm:query', '/admin/permission', 0, 'layui-icon-vercode', 2, 1, '2020-03-25 00:13:50', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (9, 5, '站点配置', 'MENU', 'site:query', '/admin/site', 0, 'layui-icon-website', 3, 1, '2020-03-25 00:13:57', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (10, 5, '操作日志', 'MENU', 'sysuser-event:query', '/admin/sysuser-event', 0, 'layui-icon-log', 4, 1, '2020-03-25 23:04:56', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (11, 6, '添加管理员', 'BUTTON', 'sysuser:create', '/admin/sysuser/add', 0, '', 0, 1, '2020-03-25 21:38:10', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (12, 6, '修改管理员', 'BUTTON', 'sysuser:update', '/admin/sysuser/modify', 0, '', 0, 1, '2020-03-25 21:38:52', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (13, 6, '删除管理员', 'BUTTON', 'sysuser:delete', '/admin/sysuser/delete', 0, '', 0, 1, '2020-03-25 21:39:50', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (14, 7, '添加角色', 'BUTTON', 'role:create', '/admin/role/add', 0, '', 0, 1, '2020-03-25 21:40:34', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (15, 7, '修改角色', 'BUTTON', 'role:update', '/admin/role/modify', 0, '', 0, 1, '2020-03-25 21:41:57', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (16, 7, '删除角色', 'BUTTON', 'role:delete', '/admin/role/delete', 0, '', 0, 1, '2020-03-25 21:41:16', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (17, 8, '添加权限', 'BUTTON', 'perm:create', '/admin/permisson/add', 0, '', 0, 1, '2020-03-25 21:41:42', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (18, 8, '修改权限', 'BUTTON', 'perm:update', '/admin/perm/modify', 0, '', 0, 1, '2020-03-25 21:42:31', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (19, 8, '删除权限', 'BUTTON', 'perm:delete', '/admin/permission/delete', 0, '', 0, 1, '2020-03-25 21:43:01', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (20, 3, '添加用户', 'BUTTON', 'user:create', '/admin/user/add', 0, '', 0, 1, '2020-03-25 21:43:25', '2020-07-18 15:31:56');
INSERT INTO `tb_sys_permission` VALUES (21, 3, '修改用户', 'BUTTON', 'user:update', '/admin/user/modify', 0, '', 0, 1, '2020-03-25 21:43:42', '2020-07-18 15:38:12');
INSERT INTO `tb_sys_permission` VALUES (22, 3, '删除用户', 'BUTTON', 'user:delete', '/admin/user/delete', 0, '', 0, 1, '2020-03-25 21:43:59', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (23, 7, '角色授权', 'BUTTON', 'role:auth', '/admin/role/authorize', 0, '', 0, 1, '2020-03-25 21:45:09', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (24, 9, '修改站点配置', 'BUTTON', 'site:update', '/admin/site/modify', 0, '', 0, 1, '2020-03-25 21:47:01', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (25, 0, '通知管理', 'MENU', '', '', 0, 'layui-icon-notice', 4, 1, '2020-03-26 22:46:17', '2020-07-18 15:31:12');
INSERT INTO `tb_sys_permission` VALUES (26, 25, '通知模板', 'MENU', 'template:query', '/admin/template', 0, '', 0, 1, '2020-03-26 22:53:15', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (27, 26, '修改通知模板', 'BUTTON', 'template:update', '/admin/template/modify', 0, '', 0, 1, '2020-03-26 22:53:27', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (28, 26, '删除通知模板', 'BUTTON', 'template:delete', '/admin/template/delete', 0, '', 0, 1, '2020-03-26 22:53:44', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (29, 25, '短信记录', 'MENU', 'smsrecord:query', '/admin/smsrecord', 0, '', 1, 1, '2020-03-26 22:54:06', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (30, 25, '邮件记录', 'MENU', 'emailrecord:query', '/admin/emailrecord', 0, '', 2, 1, '2020-03-26 22:54:23', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (31, 25, '推送记录', 'MENU', 'pushrecord:query', '/admin/pushrecord', 0, '', 2, 1, '2020-03-26 22:54:39', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (32, 1, '控制台', 'MENU', '', 'home/console.html', 0, 'layui-icon-home', 0, 1, '2020-07-18 15:47:55', '2020-07-18 17:13:43');
INSERT INTO `tb_sys_permission` VALUES (33, 34, '测试', 'MENU', '', '/admin/dashboard', 0, '', 0, 1, '2020-07-18 16:06:01', '2020-07-18 16:48:03');
INSERT INTO `tb_sys_permission` VALUES (34, 1, '二级菜单', 'MENU', '', '', 0, '', 1, 1, '2020-07-18 16:47:56', '2020-07-18 16:47:56');
INSERT INTO `tb_sys_permission` VALUES (35, 5, '系统日志', 'MENU', '', '', 0, 'layui-icon-log', 6, 1, '2020-07-24 23:37:15', '2020-07-24 23:40:59');
INSERT INTO `tb_sys_permission` VALUES (36, 35, '后台日志', 'MENU', '', '/admin/system/logger/sys/logIndex', 0, '', 0, 1, '2020-07-24 23:38:53', '2020-07-24 23:39:53');
INSERT INTO `tb_sys_permission` VALUES (37, 35, 'App日志', 'MENU', '', '/admin/system/logger/app/logIndex', 0, '', 1, 1, '2020-07-24 23:39:15', '2020-07-24 23:39:29');
INSERT INTO `tb_sys_permission` VALUES (38, 5, '字典管理', 'MENU', '', '/admin/system/dict/dictTypeIndex', 0, 'layui-icon-note', 3, 1, '2020-09-13 06:31:52', '2020-09-13 18:29:39');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(30) DEFAULT '' COMMENT '角色描述',
  `available` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可用(0:否，1：是)',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_role` VALUES (1, 'role:root', '超级管理员', 1, '2020-03-24 19:48:52', '2020-03-27 21:57:17');
INSERT INTO `tb_sys_role` VALUES (2, 'role:admin', '普通管理员', 1, '2020-03-24 19:48:52', '2020-03-27 22:40:55');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(20) DEFAULT '' COMMENT '姓名',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `wx_number` varchar(20) DEFAULT '' COMMENT '微信号码',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT '密码加盐',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0:正常，1：禁用，2：登录锁定)',
  `login_err_count` tinyint(2) NOT NULL DEFAULT '0' COMMENT '登录错误次数',
  `login_lock_time` timestamp NULL DEFAULT NULL COMMENT '登录锁定时间',
  `last_login_ip` varchar(50) DEFAULT '' COMMENT '最后登录IP',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录IP',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:未删除，1：已删除)',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  `last_login_ip_addr` varchar(30) DEFAULT '' COMMENT '最后登录IP归属地',
  `email` varchar(100) DEFAULT '' COMMENT '电子邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_h7ijwj639ve7gqr9se2yt6k08` (`username`) USING BTREE,
  UNIQUE KEY `UK_6kg2i0vryqmphpmk5011xkvnp` (`mobile`) USING BTREE,
  UNIQUE KEY `UK_pc4o8mw42f67tiapl134lnau5` (`realname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_user` VALUES (1, 'admin', '张三', '13212345678', '', '6e77092b64d18e570a9da7afa60058b9', 'F21iGN0Z9VQ1gN7Vr4jO', 0, 0, NULL, '111.22.182.78', '2020-09-13 17:51:02', 0, NULL, '2020-03-24 19:48:52', '2020-09-13 17:51:02', '湖南省岳阳市 移通', '');
INSERT INTO `tb_sys_user` VALUES (3, 'test0', 'asdf', '13212345679', '', 'c4c0b4ee2cc0a17b4791b318bf469c22', 'ZsViKkzFOJ7qH03po2au', 1, 0, NULL, NULL, NULL, 0, NULL, '2020-03-27 23:21:47', '2020-07-25 01:04:43', NULL, NULL);
INSERT INTO `tb_sys_user` VALUES (4, 'lisi11', '李思', '13412345678', '', '53571942854b6b969d14aa27b618ea88', 'OWTzd8MkEsZfijXXe2XD', 0, 0, NULL, NULL, NULL, 0, NULL, '2020-09-13 05:59:40', '2020-09-13 05:59:40', NULL, NULL);
INSERT INTO `tb_sys_user` VALUES (7, 'lisi001', '李思思', '13288990099', '', '60139bbebe0599d2740016a8fe27ad9b', 'KM69cnm3SIuDzK79eZgC', 1, 0, NULL, NULL, NULL, 0, NULL, '2020-09-13 06:00:32', '2020-09-13 06:00:32', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_user_event
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_event`;
CREATE TABLE `tb_sys_user_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '管理员ID',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '事件类型',
  `remark` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `ip` varchar(46) NOT NULL DEFAULT '' COMMENT '操作IP地址',
  `ip_addr` varchar(50) NOT NULL DEFAULT '' COMMENT '操作IP地址归属地',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=419 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='管理员操作事件表';

-- ----------------------------
-- Records of tb_sys_user_event
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_user_event` VALUES (1, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 19:48:58');
INSERT INTO `tb_sys_user_event` VALUES (2, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:36:54');
INSERT INTO `tb_sys_user_event` VALUES (3, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:43:09');
INSERT INTO `tb_sys_user_event` VALUES (4, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:45:01');
INSERT INTO `tb_sys_user_event` VALUES (5, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:47:40');
INSERT INTO `tb_sys_user_event` VALUES (6, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:49:23');
INSERT INTO `tb_sys_user_event` VALUES (7, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:51:13');
INSERT INTO `tb_sys_user_event` VALUES (8, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:54:30');
INSERT INTO `tb_sys_user_event` VALUES (9, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:56:37');
INSERT INTO `tb_sys_user_event` VALUES (10, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:58:20');
INSERT INTO `tb_sys_user_event` VALUES (11, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 21:59:16');
INSERT INTO `tb_sys_user_event` VALUES (12, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:00:25');
INSERT INTO `tb_sys_user_event` VALUES (13, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:03:33');
INSERT INTO `tb_sys_user_event` VALUES (14, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:06:21');
INSERT INTO `tb_sys_user_event` VALUES (15, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:06:36');
INSERT INTO `tb_sys_user_event` VALUES (16, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:09:56');
INSERT INTO `tb_sys_user_event` VALUES (17, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:13:38');
INSERT INTO `tb_sys_user_event` VALUES (18, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:15:12');
INSERT INTO `tb_sys_user_event` VALUES (19, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:15:37');
INSERT INTO `tb_sys_user_event` VALUES (20, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:31:29');
INSERT INTO `tb_sys_user_event` VALUES (21, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:35:47');
INSERT INTO `tb_sys_user_event` VALUES (22, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:41:58');
INSERT INTO `tb_sys_user_event` VALUES (23, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 22:54:05');
INSERT INTO `tb_sys_user_event` VALUES (24, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 23:23:25');
INSERT INTO `tb_sys_user_event` VALUES (25, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-24 23:59:22');
INSERT INTO `tb_sys_user_event` VALUES (26, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:07:24');
INSERT INTO `tb_sys_user_event` VALUES (27, 1, 'MENU_MNG', '修改权限[10:操作日志]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:10:11');
INSERT INTO `tb_sys_user_event` VALUES (28, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:10:23');
INSERT INTO `tb_sys_user_event` VALUES (29, 1, 'MENU_MNG', '修改权限[1:主页]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:10:36');
INSERT INTO `tb_sys_user_event` VALUES (30, 1, 'MENU_MNG', '修改权限[5:系统设置]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:10:48');
INSERT INTO `tb_sys_user_event` VALUES (31, 1, 'MENU_MNG', '修改权限[2:用户管理]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:12:02');
INSERT INTO `tb_sys_user_event` VALUES (32, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:12:07');
INSERT INTO `tb_sys_user_event` VALUES (33, 1, 'MENU_MNG', '修改权限[3:用户管理]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:12:36');
INSERT INTO `tb_sys_user_event` VALUES (34, 1, 'MENU_MNG', '修改权限[4:操作日志]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:13:00');
INSERT INTO `tb_sys_user_event` VALUES (35, 1, 'MENU_MNG', '修改权限[6:管理员]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:13:19');
INSERT INTO `tb_sys_user_event` VALUES (36, 1, 'MENU_MNG', '修改权限[7:角色管理]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:13:31');
INSERT INTO `tb_sys_user_event` VALUES (37, 1, 'MENU_MNG', '修改权限[8:权限管理]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:13:50');
INSERT INTO `tb_sys_user_event` VALUES (38, 1, 'MENU_MNG', '修改权限[9:站点配置]', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:13:57');
INSERT INTO `tb_sys_user_event` VALUES (39, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:14:02');
INSERT INTO `tb_sys_user_event` VALUES (40, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:26:52');
INSERT INTO `tb_sys_user_event` VALUES (41, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:27:04');
INSERT INTO `tb_sys_user_event` VALUES (42, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:32:26');
INSERT INTO `tb_sys_user_event` VALUES (43, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:33:09');
INSERT INTO `tb_sys_user_event` VALUES (44, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:33:43');
INSERT INTO `tb_sys_user_event` VALUES (45, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:35:08');
INSERT INTO `tb_sys_user_event` VALUES (46, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 00:35:56');
INSERT INTO `tb_sys_user_event` VALUES (47, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 19:35:37');
INSERT INTO `tb_sys_user_event` VALUES (48, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 19:40:13');
INSERT INTO `tb_sys_user_event` VALUES (49, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 20:08:50');
INSERT INTO `tb_sys_user_event` VALUES (50, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 20:10:03');
INSERT INTO `tb_sys_user_event` VALUES (51, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 20:11:54');
INSERT INTO `tb_sys_user_event` VALUES (52, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:32:35');
INSERT INTO `tb_sys_user_event` VALUES (53, 1, 'ROLE_MNG', '授权角色权限【2:role:admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:32:51');
INSERT INTO `tb_sys_user_event` VALUES (54, 1, 'MENU_MNG', '添加权限[11:添加管理员]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:38:10');
INSERT INTO `tb_sys_user_event` VALUES (55, 1, 'MENU_MNG', '添加权限[12:修改管理员]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:38:52');
INSERT INTO `tb_sys_user_event` VALUES (56, 1, 'MENU_MNG', '添加权限[13:删除管理员]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:39:50');
INSERT INTO `tb_sys_user_event` VALUES (57, 1, 'MENU_MNG', '添加权限[14:添加角色]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:40:34');
INSERT INTO `tb_sys_user_event` VALUES (58, 1, 'MENU_MNG', '添加权限[15:修改角色]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:40:54');
INSERT INTO `tb_sys_user_event` VALUES (59, 1, 'MENU_MNG', '添加权限[16:删除角色]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:41:16');
INSERT INTO `tb_sys_user_event` VALUES (60, 1, 'MENU_MNG', '添加权限[17:添加权限]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:41:42');
INSERT INTO `tb_sys_user_event` VALUES (61, 1, 'MENU_MNG', '修改权限[15:修改角色]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:41:57');
INSERT INTO `tb_sys_user_event` VALUES (62, 1, 'MENU_MNG', '添加权限[18:修改权限]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:42:31');
INSERT INTO `tb_sys_user_event` VALUES (63, 1, 'MENU_MNG', '添加权限[19:删除权限]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:43:01');
INSERT INTO `tb_sys_user_event` VALUES (64, 1, 'MENU_MNG', '添加权限[20:添加用户]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:43:25');
INSERT INTO `tb_sys_user_event` VALUES (65, 1, 'MENU_MNG', '添加权限[21:修改用户]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:43:42');
INSERT INTO `tb_sys_user_event` VALUES (66, 1, 'MENU_MNG', '添加权限[22:删除用户]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:43:59');
INSERT INTO `tb_sys_user_event` VALUES (67, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:44:20');
INSERT INTO `tb_sys_user_event` VALUES (68, 1, 'MENU_MNG', '添加权限[23:角色授权]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:45:09');
INSERT INTO `tb_sys_user_event` VALUES (69, 1, 'MENU_MNG', '添加权限[24:修改站点配置]', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:47:01');
INSERT INTO `tb_sys_user_event` VALUES (70, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:47:11');
INSERT INTO `tb_sys_user_event` VALUES (71, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:48:01');
INSERT INTO `tb_sys_user_event` VALUES (72, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:49:03');
INSERT INTO `tb_sys_user_event` VALUES (73, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:49:16');
INSERT INTO `tb_sys_user_event` VALUES (74, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:49:17');
INSERT INTO `tb_sys_user_event` VALUES (75, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:49:59');
INSERT INTO `tb_sys_user_event` VALUES (76, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:57:00');
INSERT INTO `tb_sys_user_event` VALUES (77, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:57:35');
INSERT INTO `tb_sys_user_event` VALUES (78, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 21:59:12');
INSERT INTO `tb_sys_user_event` VALUES (79, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:01:05');
INSERT INTO `tb_sys_user_event` VALUES (80, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:01:09');
INSERT INTO `tb_sys_user_event` VALUES (81, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:01:11');
INSERT INTO `tb_sys_user_event` VALUES (82, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:01:14');
INSERT INTO `tb_sys_user_event` VALUES (83, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:01:50');
INSERT INTO `tb_sys_user_event` VALUES (84, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:02:04');
INSERT INTO `tb_sys_user_event` VALUES (85, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:02:21');
INSERT INTO `tb_sys_user_event` VALUES (86, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:02:44');
INSERT INTO `tb_sys_user_event` VALUES (87, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:03:07');
INSERT INTO `tb_sys_user_event` VALUES (88, 1, 'SITE_MNG', '修改站点配置', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:03:09');
INSERT INTO `tb_sys_user_event` VALUES (89, 1, 'MENU_MNG', '修改权限[10:操作日志]', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:06:52');
INSERT INTO `tb_sys_user_event` VALUES (90, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:07:09');
INSERT INTO `tb_sys_user_event` VALUES (91, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:16:04');
INSERT INTO `tb_sys_user_event` VALUES (92, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:19:22');
INSERT INTO `tb_sys_user_event` VALUES (93, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:35:04');
INSERT INTO `tb_sys_user_event` VALUES (94, 1, 'MENU_MNG', '修改权限[10:操作日志]', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:41:22');
INSERT INTO `tb_sys_user_event` VALUES (95, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 22:41:50');
INSERT INTO `tb_sys_user_event` VALUES (96, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:04:30');
INSERT INTO `tb_sys_user_event` VALUES (97, 1, 'MENU_MNG', '修改权限[10:操作日志]', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:04:56');
INSERT INTO `tb_sys_user_event` VALUES (98, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:05:49');
INSERT INTO `tb_sys_user_event` VALUES (99, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:07:01');
INSERT INTO `tb_sys_user_event` VALUES (100, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:08:31');
INSERT INTO `tb_sys_user_event` VALUES (101, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:08:58');
INSERT INTO `tb_sys_user_event` VALUES (102, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:10:52');
INSERT INTO `tb_sys_user_event` VALUES (103, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:23:05');
INSERT INTO `tb_sys_user_event` VALUES (104, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:23:37');
INSERT INTO `tb_sys_user_event` VALUES (105, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:25:33');
INSERT INTO `tb_sys_user_event` VALUES (106, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:34:18');
INSERT INTO `tb_sys_user_event` VALUES (107, 1, 'MENU_MNG', '修改权限[4:操作日志]', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:34:37');
INSERT INTO `tb_sys_user_event` VALUES (108, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:34:45');
INSERT INTO `tb_sys_user_event` VALUES (109, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:35:46');
INSERT INTO `tb_sys_user_event` VALUES (110, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:49:52');
INSERT INTO `tb_sys_user_event` VALUES (111, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:52:21');
INSERT INTO `tb_sys_user_event` VALUES (112, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:53:59');
INSERT INTO `tb_sys_user_event` VALUES (113, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:55:52');
INSERT INTO `tb_sys_user_event` VALUES (114, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-25 23:59:09');
INSERT INTO `tb_sys_user_event` VALUES (115, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:00:04');
INSERT INTO `tb_sys_user_event` VALUES (116, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:03:40');
INSERT INTO `tb_sys_user_event` VALUES (117, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:08:58');
INSERT INTO `tb_sys_user_event` VALUES (118, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:10:45');
INSERT INTO `tb_sys_user_event` VALUES (119, 1, 'ROLE_MNG', '授权角色权限【2:role:admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:11:14');
INSERT INTO `tb_sys_user_event` VALUES (120, 1, 'ROLE_MNG', '授权角色权限【2:role:admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:11:30');
INSERT INTO `tb_sys_user_event` VALUES (121, 1, 'ROLE_MNG', '授权角色权限【2:role:admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:11:50');
INSERT INTO `tb_sys_user_event` VALUES (122, 1, 'ROLE_MNG', '授权角色权限【2:role:admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 00:15:07');
INSERT INTO `tb_sys_user_event` VALUES (123, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 20:25:58');
INSERT INTO `tb_sys_user_event` VALUES (124, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:37:58');
INSERT INTO `tb_sys_user_event` VALUES (125, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:44:57');
INSERT INTO `tb_sys_user_event` VALUES (126, 1, 'MENU_MNG', '添加权限[25:模板管理]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:45:24');
INSERT INTO `tb_sys_user_event` VALUES (127, 1, 'MENU_MNG', '修改权限[25:通知管理]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:46:09');
INSERT INTO `tb_sys_user_event` VALUES (128, 1, 'MENU_MNG', '修改权限[25:通知管理]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:46:17');
INSERT INTO `tb_sys_user_event` VALUES (129, 1, 'MENU_MNG', '添加权限[26:通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:46:46');
INSERT INTO `tb_sys_user_event` VALUES (130, 1, 'MENU_MNG', '修改权限[26:通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:46:57');
INSERT INTO `tb_sys_user_event` VALUES (131, 1, 'MENU_MNG', '添加权限[27:修改通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:48:24');
INSERT INTO `tb_sys_user_event` VALUES (132, 1, 'MENU_MNG', '修改权限[26:通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:48:44');
INSERT INTO `tb_sys_user_event` VALUES (133, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:48:55');
INSERT INTO `tb_sys_user_event` VALUES (134, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:49:09');
INSERT INTO `tb_sys_user_event` VALUES (135, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:49:14');
INSERT INTO `tb_sys_user_event` VALUES (136, 1, 'MENU_MNG', '添加权限[28:删除通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:49:52');
INSERT INTO `tb_sys_user_event` VALUES (137, 1, 'MENU_MNG', '添加权限[29:短信记录]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:50:54');
INSERT INTO `tb_sys_user_event` VALUES (138, 1, 'MENU_MNG', '添加权限[30:邮件记录]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:51:25');
INSERT INTO `tb_sys_user_event` VALUES (139, 1, 'MENU_MNG', '添加权限[31:推送记录]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:51:52');
INSERT INTO `tb_sys_user_event` VALUES (140, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:51:58');
INSERT INTO `tb_sys_user_event` VALUES (141, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:52:12');
INSERT INTO `tb_sys_user_event` VALUES (142, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:52:18');
INSERT INTO `tb_sys_user_event` VALUES (143, 1, 'MENU_MNG', '修改权限[26:通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:53:15');
INSERT INTO `tb_sys_user_event` VALUES (144, 1, 'MENU_MNG', '修改权限[27:修改通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:53:27');
INSERT INTO `tb_sys_user_event` VALUES (145, 1, 'MENU_MNG', '修改权限[28:删除通知模板]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:53:44');
INSERT INTO `tb_sys_user_event` VALUES (146, 1, 'MENU_MNG', '修改权限[29:短信记录]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:54:06');
INSERT INTO `tb_sys_user_event` VALUES (147, 1, 'MENU_MNG', '修改权限[30:邮件记录]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:54:23');
INSERT INTO `tb_sys_user_event` VALUES (148, 1, 'MENU_MNG', '修改权限[31:推送记录]', '0:0:0:0:0:0:0:1', '', '2020-03-26 22:54:39');
INSERT INTO `tb_sys_user_event` VALUES (149, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 23:33:46');
INSERT INTO `tb_sys_user_event` VALUES (150, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 23:35:20');
INSERT INTO `tb_sys_user_event` VALUES (151, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 23:37:12');
INSERT INTO `tb_sys_user_event` VALUES (152, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 23:41:29');
INSERT INTO `tb_sys_user_event` VALUES (153, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 23:51:32');
INSERT INTO `tb_sys_user_event` VALUES (154, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-26 23:53:46');
INSERT INTO `tb_sys_user_event` VALUES (155, 1, 'TEMPLATE_MNG', '修改模板状态成功[1:短信消息:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:19:30');
INSERT INTO `tb_sys_user_event` VALUES (156, 1, 'TEMPLATE_MNG', '修改模板状态成功[2:电子邮件:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:19:33');
INSERT INTO `tb_sys_user_event` VALUES (158, 1, 'TEMPLATE_MNG', '修改模板成功[1:未知:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:23:28');
INSERT INTO `tb_sys_user_event` VALUES (159, 1, 'TEMPLATE_MNG', '修改通知模板[1:未知:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:24:52');
INSERT INTO `tb_sys_user_event` VALUES (160, 1, 'TEMPLATE_MNG', '修改通知模板[1:null:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:28:29');
INSERT INTO `tb_sys_user_event` VALUES (161, 1, 'TEMPLATE_MNG', '修改通知模板状态[1:SMS:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:33:07');
INSERT INTO `tb_sys_user_event` VALUES (162, 1, 'TEMPLATE_MNG', '修改通知模板状态[2:EMAIL:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:33:10');
INSERT INTO `tb_sys_user_event` VALUES (163, 1, 'TEMPLATE_MNG', '修改通知模板状态[1:SMS:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:33:12');
INSERT INTO `tb_sys_user_event` VALUES (164, 1, 'TEMPLATE_MNG', '修改通知模板状态[2:EMAIL:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:33:14');
INSERT INTO `tb_sys_user_event` VALUES (165, 1, 'TEMPLATE_MNG', '修改通知模板状态[2:EMAIL:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:50:48');
INSERT INTO `tb_sys_user_event` VALUES (166, 1, 'TEMPLATE_MNG', '修改通知模板状态[2:EMAIL:发送验证码]', '0:0:0:0:0:0:0:1', '', '2020-03-27 20:50:49');
INSERT INTO `tb_sys_user_event` VALUES (167, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:30:57');
INSERT INTO `tb_sys_user_event` VALUES (168, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:32:49');
INSERT INTO `tb_sys_user_event` VALUES (169, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:38:28');
INSERT INTO `tb_sys_user_event` VALUES (170, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:39:02');
INSERT INTO `tb_sys_user_event` VALUES (171, 1, 'ADMIN_MNG', '修改管理员[1:admin]', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:39:17');
INSERT INTO `tb_sys_user_event` VALUES (172, 1, 'ADMIN_MNG', '修改管理员[1:admin]', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:39:44');
INSERT INTO `tb_sys_user_event` VALUES (173, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:52:05');
INSERT INTO `tb_sys_user_event` VALUES (174, 1, 'LOGIN', '管理员登录【1，admin】', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:56:34');
INSERT INTO `tb_sys_user_event` VALUES (175, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:56:41');
INSERT INTO `tb_sys_user_event` VALUES (176, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:56:44');
INSERT INTO `tb_sys_user_event` VALUES (177, 1, 'ROLE_MNG', '修改角色状态成功[1：role:root]', '0:0:0:0:0:0:0:1', '', '2020-03-27 21:56:45');
INSERT INTO `tb_sys_user_event` VALUES (178, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '0:0:0:0:0:0:0:1', '湖南省长沙市 移通', '2020-03-27 22:02:17');
INSERT INTO `tb_sys_user_event` VALUES (179, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '0:0:0:0:0:0:0:1', '湖南省长沙市 移通', '2020-03-27 22:02:18');
INSERT INTO `tb_sys_user_event` VALUES (180, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:06:52');
INSERT INTO `tb_sys_user_event` VALUES (181, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:06:54');
INSERT INTO `tb_sys_user_event` VALUES (182, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:40:54');
INSERT INTO `tb_sys_user_event` VALUES (183, 1, 'ROLE_MNG', '修改角色状态成功[2：role:admin]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:40:55');
INSERT INTO `tb_sys_user_event` VALUES (184, 1, 'ADMIN_MNG', '修改管理员[1:admin]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:43:47');
INSERT INTO `tb_sys_user_event` VALUES (185, 1, 'ADMIN_MNG', '修改管理员状态[1：张三]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:54:19');
INSERT INTO `tb_sys_user_event` VALUES (186, 1, 'ADMIN_MNG', '修改管理员状态[1：张三]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 22:54:24');
INSERT INTO `tb_sys_user_event` VALUES (187, 1, 'ADMIN_MNG', '重置管理员[1,张三]登录密码', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 23:19:41');
INSERT INTO `tb_sys_user_event` VALUES (188, 1, 'ADMIN_MNG', '添加管理员[3:test0]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 23:21:48');
INSERT INTO `tb_sys_user_event` VALUES (189, 1, 'ADMIN_MNG', '重置管理员[3,asdf]登录密码', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 23:27:23');
INSERT INTO `tb_sys_user_event` VALUES (190, 1, 'ADMIN_MNG', '删除管理员[3,asdf]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 23:27:29');
INSERT INTO `tb_sys_user_event` VALUES (191, 1, 'SITE_MNG', '修改站点配置', '36.157.195.84', '湖南省长沙市 移通', '2020-03-27 23:33:35');
INSERT INTO `tb_sys_user_event` VALUES (192, 1, 'LOGIN', '管理员登录【1，admin】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-28 21:32:53');
INSERT INTO `tb_sys_user_event` VALUES (193, 1, 'LOGIN', '管理员登录【1，admin】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-28 21:56:50');
INSERT INTO `tb_sys_user_event` VALUES (194, 1, 'LOGIN', '管理员登录【1，admin】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-28 22:02:37');
INSERT INTO `tb_sys_user_event` VALUES (195, 1, 'USER_MNG', '添加[null:null]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-28 22:50:41');
INSERT INTO `tb_sys_user_event` VALUES (196, 1, 'USER_MNG', '添加[null:null]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-28 22:58:41');
INSERT INTO `tb_sys_user_event` VALUES (197, 1, 'USER_MNG', '添加[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-28 23:49:04');
INSERT INTO `tb_sys_user_event` VALUES (198, 1, 'USER_MNG', '修改用户状态[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:06:12');
INSERT INTO `tb_sys_user_event` VALUES (199, 1, 'USER_MNG', '修改用户状态[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:06:16');
INSERT INTO `tb_sys_user_event` VALUES (200, 1, 'USER_MNG', '重置用户密码[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:06:57');
INSERT INTO `tb_sys_user_event` VALUES (201, 1, 'USER_MNG', '修改用户信息[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:14:35');
INSERT INTO `tb_sys_user_event` VALUES (202, 1, 'USER_MNG', '修改用户信息[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:16:13');
INSERT INTO `tb_sys_user_event` VALUES (203, 1, 'USER_MNG', '修改用户信息[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:16:26');
INSERT INTO `tb_sys_user_event` VALUES (204, 1, 'USER_MNG', '添加[3:test02]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:28:09');
INSERT INTO `tb_sys_user_event` VALUES (205, 1, 'USER_MNG', '添加[4:fank243dd]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:55:48');
INSERT INTO `tb_sys_user_event` VALUES (206, 1, 'USER_MNG', '修改用户信息[4:test03]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 00:55:55');
INSERT INTO `tb_sys_user_event` VALUES (207, 1, 'USER_MNG', '修改用户状态[4:test03]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 01:16:19');
INSERT INTO `tb_sys_user_event` VALUES (208, 1, 'USER_MNG', '修改用户状态[4:test03]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 01:16:20');
INSERT INTO `tb_sys_user_event` VALUES (209, 1, 'USER_MNG', '重置用户密码[4:test03]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 01:16:22');
INSERT INTO `tb_sys_user_event` VALUES (210, 1, 'MENU_MNG', '修改权限[25:通知管理]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 02:00:54');
INSERT INTO `tb_sys_user_event` VALUES (211, 1, 'MENU_MNG', '修改权限[25:通知管理]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 13:53:38');
INSERT INTO `tb_sys_user_event` VALUES (212, 1, 'MENU_MNG', '修改权限[25:通知管理]', '0:0:0:0:0:0:0:1', '', '2020-03-29 13:54:03');
INSERT INTO `tb_sys_user_event` VALUES (213, 1, 'MENU_MNG', '修改权限[25:通知管理]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 14:11:12');
INSERT INTO `tb_sys_user_event` VALUES (214, 1, 'MENU_MNG', '修改权限[25:通知管理]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:39:36');
INSERT INTO `tb_sys_user_event` VALUES (215, 1, 'PERM_MNG', '修改权限[25:通知管理]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:51:38');
INSERT INTO `tb_sys_user_event` VALUES (216, 1, 'PERM_MNG', '修改权限状态【1：主页】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:54:48');
INSERT INTO `tb_sys_user_event` VALUES (217, 1, 'PERM_MNG', '修改权限状态【1：主页】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:54:52');
INSERT INTO `tb_sys_user_event` VALUES (218, 1, 'PERM_MNG', '修改权限状态【1：主页】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:54:52');
INSERT INTO `tb_sys_user_event` VALUES (219, 1, 'PERM_MNG', '修改权限状态【1：主页】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:54:55');
INSERT INTO `tb_sys_user_event` VALUES (220, 1, 'PERM_MNG', '修改权限状态【1：主页】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:55:00');
INSERT INTO `tb_sys_user_event` VALUES (221, 1, 'PERM_MNG', '修改权限状态【1：主页】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:55:03');
INSERT INTO `tb_sys_user_event` VALUES (222, 1, 'PERM_MNG', '添加权限[32:test]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:55:12');
INSERT INTO `tb_sys_user_event` VALUES (223, 1, 'PERM_MNG', '删除权限【32：test】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-29 19:56:05');
INSERT INTO `tb_sys_user_event` VALUES (224, 1, 'USER_MNG', '重置用户密码[4:test03]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-30 23:26:39');
INSERT INTO `tb_sys_user_event` VALUES (225, 1, 'USER_MNG', '重置用户密码[2:test01]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-30 23:26:44');
INSERT INTO `tb_sys_user_event` VALUES (226, 1, 'SITE_MNG', '修改站点配置', '36.157.195.84', '湖南省长沙市 移通', '2020-03-30 23:26:52');
INSERT INTO `tb_sys_user_event` VALUES (227, 1, 'PERM_MNG', '添加权限[32:SDAF]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-30 23:27:10');
INSERT INTO `tb_sys_user_event` VALUES (228, 1, 'PERM_MNG', '修改权限[32:SDAF]', '36.157.195.84', '湖南省长沙市 移通', '2020-03-30 23:27:18');
INSERT INTO `tb_sys_user_event` VALUES (229, 1, 'PERM_MNG', '删除权限【32：SDAF】', '36.157.195.84', '湖南省长沙市 移通', '2020-03-30 23:27:23');
INSERT INTO `tb_sys_user_event` VALUES (230, 1, 'LOGIN', '管理员登录【1，admin】', '36.157.208.128', '湖南省长沙市 移通', '2020-04-01 22:25:28');
INSERT INTO `tb_sys_user_event` VALUES (231, 1, 'LOGIN', '管理员登录【1，admin】', '36.157.208.128', '湖南省长沙市 移通', '2020-04-01 22:26:02');
INSERT INTO `tb_sys_user_event` VALUES (232, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.125', '湖南省岳阳市 移通', '2020-04-28 22:26:25');
INSERT INTO `tb_sys_user_event` VALUES (233, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:20:57');
INSERT INTO `tb_sys_user_event` VALUES (234, 1, 'USER_MNG', '重置用户密码[2:test01]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:21:44');
INSERT INTO `tb_sys_user_event` VALUES (235, 1, 'USER_MNG', '修改用户状态[4:test03]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:21:51');
INSERT INTO `tb_sys_user_event` VALUES (236, 1, 'USER_MNG', '修改用户状态[4:test03]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:21:52');
INSERT INTO `tb_sys_user_event` VALUES (237, 1, 'USER_MNG', '修改用户状态[4:test03]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:28:44');
INSERT INTO `tb_sys_user_event` VALUES (238, 1, 'USER_MNG', '修改用户状态[2:test01]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:28:51');
INSERT INTO `tb_sys_user_event` VALUES (239, 1, 'USER_MNG', '修改用户状态[3:test02]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:43:24');
INSERT INTO `tb_sys_user_event` VALUES (240, 1, 'USER_MNG', '修改用户状态[3:test02]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:43:33');
INSERT INTO `tb_sys_user_event` VALUES (241, 1, 'USER_MNG', '修改用户状态[2:test01]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:43:36');
INSERT INTO `tb_sys_user_event` VALUES (242, 1, 'USER_MNG', '修改用户状态[3:test02]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:46:28');
INSERT INTO `tb_sys_user_event` VALUES (243, 1, 'USER_MNG', '修改用户状态[2:test01]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:46:31');
INSERT INTO `tb_sys_user_event` VALUES (244, 1, 'USER_MNG', '修改用户状态[2:test01]', '0:0:0:0:0:0:0:1', '', '2020-07-18 13:48:52');
INSERT INTO `tb_sys_user_event` VALUES (245, 1, 'USER_MNG', '修改用户状态[3:test02]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:49:01');
INSERT INTO `tb_sys_user_event` VALUES (246, 1, 'USER_MNG', '修改用户状态[3:test02]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 13:49:08');
INSERT INTO `tb_sys_user_event` VALUES (247, 1, 'PERM_MNG', '修改权限[25:通知管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 14:07:09');
INSERT INTO `tb_sys_user_event` VALUES (248, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 14:07:39');
INSERT INTO `tb_sys_user_event` VALUES (249, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 14:07:52');
INSERT INTO `tb_sys_user_event` VALUES (250, 1, 'ADMIN_MNG', '管理员修改个人信息[1:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 14:14:13');
INSERT INTO `tb_sys_user_event` VALUES (251, 1, 'PERM_MNG', '修改权限[3:用户管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:04:15');
INSERT INTO `tb_sys_user_event` VALUES (252, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:04:28');
INSERT INTO `tb_sys_user_event` VALUES (253, 1, 'PERM_MNG', '修改权限[3:用户管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:06:21');
INSERT INTO `tb_sys_user_event` VALUES (254, 1, 'PERM_MNG', '修改权限[2:用户管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:06:39');
INSERT INTO `tb_sys_user_event` VALUES (255, 1, 'PERM_MNG', '修改权限[2:用户管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:06:48');
INSERT INTO `tb_sys_user_event` VALUES (256, 1, 'PERM_MNG', '修改权限[3:用户管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:06:59');
INSERT INTO `tb_sys_user_event` VALUES (257, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:12:32');
INSERT INTO `tb_sys_user_event` VALUES (258, 1, 'PERM_MNG', '修改权限[3:用户管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:13:02');
INSERT INTO `tb_sys_user_event` VALUES (259, 1, 'PERM_MNG', '修改权限[3:用户列表]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:13:31');
INSERT INTO `tb_sys_user_event` VALUES (260, 1, 'PERM_MNG', '修改权限[3:用户列表]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:13:43');
INSERT INTO `tb_sys_user_event` VALUES (261, 1, 'PERM_MNG', '修改权限[3:用户列表]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:14:13');
INSERT INTO `tb_sys_user_event` VALUES (262, 1, 'PERM_MNG', '修改权限[3:用户列表]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:14:29');
INSERT INTO `tb_sys_user_event` VALUES (263, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:14:53');
INSERT INTO `tb_sys_user_event` VALUES (264, 1, 'PERM_MNG', '修改权限[3:用户列表]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:30:53');
INSERT INTO `tb_sys_user_event` VALUES (265, 1, 'PERM_MNG', '修改权限[25:通知管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:31:04');
INSERT INTO `tb_sys_user_event` VALUES (266, 1, 'PERM_MNG', '修改权限[25:通知管理]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:31:12');
INSERT INTO `tb_sys_user_event` VALUES (267, 1, 'PERM_MNG', '修改权限[6:管理员列表]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:31:31');
INSERT INTO `tb_sys_user_event` VALUES (268, 1, 'PERM_MNG', '修改权限[20:添加用户]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:31:55');
INSERT INTO `tb_sys_user_event` VALUES (269, 1, 'PERM_MNG', '修改权限[21:修改用户]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:32:10');
INSERT INTO `tb_sys_user_event` VALUES (270, 1, 'PERM_MNG', '修改权限[21:修改用户]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:35:28');
INSERT INTO `tb_sys_user_event` VALUES (271, 1, 'PERM_MNG', '修改权限[21:修改用户]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:38:02');
INSERT INTO `tb_sys_user_event` VALUES (272, 1, 'PERM_MNG', '修改权限[21:修改用户]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:38:12');
INSERT INTO `tb_sys_user_event` VALUES (273, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:45:24');
INSERT INTO `tb_sys_user_event` VALUES (274, 1, 'PERM_MNG', '添加权限[32:控制台]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:47:55');
INSERT INTO `tb_sys_user_event` VALUES (275, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:48:05');
INSERT INTO `tb_sys_user_event` VALUES (276, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:48:17');
INSERT INTO `tb_sys_user_event` VALUES (277, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:49:14');
INSERT INTO `tb_sys_user_event` VALUES (278, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:51:03');
INSERT INTO `tb_sys_user_event` VALUES (279, 1, 'PERM_MNG', '修改权限[32:控制台]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:52:54');
INSERT INTO `tb_sys_user_event` VALUES (280, 1, 'PERM_MNG', '修改权限[32:控制台]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:53:21');
INSERT INTO `tb_sys_user_event` VALUES (281, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 15:55:39');
INSERT INTO `tb_sys_user_event` VALUES (282, 1, 'PERM_MNG', '添加权限[33:测试]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:06:01');
INSERT INTO `tb_sys_user_event` VALUES (283, 1, 'PERM_MNG', '修改权限[33:测试]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:06:12');
INSERT INTO `tb_sys_user_event` VALUES (284, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:06:27');
INSERT INTO `tb_sys_user_event` VALUES (285, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:06:43');
INSERT INTO `tb_sys_user_event` VALUES (286, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:09:18');
INSERT INTO `tb_sys_user_event` VALUES (287, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:42:08');
INSERT INTO `tb_sys_user_event` VALUES (288, 1, 'PERM_MNG', '添加权限[34:二级菜单]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:47:56');
INSERT INTO `tb_sys_user_event` VALUES (289, 1, 'PERM_MNG', '修改权限[33:测试]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:48:03');
INSERT INTO `tb_sys_user_event` VALUES (290, 1, 'ROLE_MNG', '授权角色权限【2:role:admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:48:36');
INSERT INTO `tb_sys_user_event` VALUES (291, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:48:49');
INSERT INTO `tb_sys_user_event` VALUES (292, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:49:04');
INSERT INTO `tb_sys_user_event` VALUES (293, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 16:49:21');
INSERT INTO `tb_sys_user_event` VALUES (294, 1, 'PERM_MNG', '修改权限[32:控制台]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 17:12:55');
INSERT INTO `tb_sys_user_event` VALUES (295, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 17:13:06');
INSERT INTO `tb_sys_user_event` VALUES (296, 1, 'PERM_MNG', '修改权限[32:控制台]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 17:13:43');
INSERT INTO `tb_sys_user_event` VALUES (297, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-18 17:13:53');
INSERT INTO `tb_sys_user_event` VALUES (298, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:01:02');
INSERT INTO `tb_sys_user_event` VALUES (299, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:01:03');
INSERT INTO `tb_sys_user_event` VALUES (300, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:04:44');
INSERT INTO `tb_sys_user_event` VALUES (301, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:04:44');
INSERT INTO `tb_sys_user_event` VALUES (302, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:05:42');
INSERT INTO `tb_sys_user_event` VALUES (303, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:05:42');
INSERT INTO `tb_sys_user_event` VALUES (304, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:08:14');
INSERT INTO `tb_sys_user_event` VALUES (305, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:08:14');
INSERT INTO `tb_sys_user_event` VALUES (306, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:09:45');
INSERT INTO `tb_sys_user_event` VALUES (307, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:12:10');
INSERT INTO `tb_sys_user_event` VALUES (308, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:12:10');
INSERT INTO `tb_sys_user_event` VALUES (309, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:14:35');
INSERT INTO `tb_sys_user_event` VALUES (310, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:14:35');
INSERT INTO `tb_sys_user_event` VALUES (311, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:20:18');
INSERT INTO `tb_sys_user_event` VALUES (312, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:20:18');
INSERT INTO `tb_sys_user_event` VALUES (313, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:22:40');
INSERT INTO `tb_sys_user_event` VALUES (314, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:22:41');
INSERT INTO `tb_sys_user_event` VALUES (315, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:25:09');
INSERT INTO `tb_sys_user_event` VALUES (316, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:25:09');
INSERT INTO `tb_sys_user_event` VALUES (317, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:26:07');
INSERT INTO `tb_sys_user_event` VALUES (318, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:26:30');
INSERT INTO `tb_sys_user_event` VALUES (319, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:26:38');
INSERT INTO `tb_sys_user_event` VALUES (320, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:26:55');
INSERT INTO `tb_sys_user_event` VALUES (321, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:27:15');
INSERT INTO `tb_sys_user_event` VALUES (322, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:27:26');
INSERT INTO `tb_sys_user_event` VALUES (323, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:30:42');
INSERT INTO `tb_sys_user_event` VALUES (324, 1, 'ROLE_MNG', '修改角色[2:role:admin]', '111.22.182.228', '湖南省岳阳市 移通', '2020-07-23 00:30:51');
INSERT INTO `tb_sys_user_event` VALUES (325, 1, 'PERM_MNG', '添加权限[35:系统日志]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:37:16');
INSERT INTO `tb_sys_user_event` VALUES (326, 1, 'PERM_MNG', '添加权限[36:后台日志]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:38:53');
INSERT INTO `tb_sys_user_event` VALUES (327, 1, 'PERM_MNG', '添加权限[37:App日志]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:39:15');
INSERT INTO `tb_sys_user_event` VALUES (328, 1, 'PERM_MNG', '修改权限[37:App日志]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:39:29');
INSERT INTO `tb_sys_user_event` VALUES (329, 1, 'PERM_MNG', '修改权限[36:后台日志]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:39:53');
INSERT INTO `tb_sys_user_event` VALUES (330, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:40:04');
INSERT INTO `tb_sys_user_event` VALUES (331, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:40:16');
INSERT INTO `tb_sys_user_event` VALUES (332, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:40:19');
INSERT INTO `tb_sys_user_event` VALUES (333, 1, 'PERM_MNG', '修改权限[35:系统日志]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:40:59');
INSERT INTO `tb_sys_user_event` VALUES (334, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:41:10');
INSERT INTO `tb_sys_user_event` VALUES (335, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:41:12');
INSERT INTO `tb_sys_user_event` VALUES (336, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:42:14');
INSERT INTO `tb_sys_user_event` VALUES (337, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:42:17');
INSERT INTO `tb_sys_user_event` VALUES (338, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:42:19');
INSERT INTO `tb_sys_user_event` VALUES (339, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:42:20');
INSERT INTO `tb_sys_user_event` VALUES (340, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:42:56');
INSERT INTO `tb_sys_user_event` VALUES (341, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:43:03');
INSERT INTO `tb_sys_user_event` VALUES (342, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:45:06');
INSERT INTO `tb_sys_user_event` VALUES (343, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:45:36');
INSERT INTO `tb_sys_user_event` VALUES (344, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:45:56');
INSERT INTO `tb_sys_user_event` VALUES (345, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:46:45');
INSERT INTO `tb_sys_user_event` VALUES (346, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:47:52');
INSERT INTO `tb_sys_user_event` VALUES (347, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:49:28');
INSERT INTO `tb_sys_user_event` VALUES (348, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:51:29');
INSERT INTO `tb_sys_user_event` VALUES (349, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:51:47');
INSERT INTO `tb_sys_user_event` VALUES (350, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:52:25');
INSERT INTO `tb_sys_user_event` VALUES (351, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:53:32');
INSERT INTO `tb_sys_user_event` VALUES (352, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:54:17');
INSERT INTO `tb_sys_user_event` VALUES (353, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:55:40');
INSERT INTO `tb_sys_user_event` VALUES (354, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:55:59');
INSERT INTO `tb_sys_user_event` VALUES (355, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:57:28');
INSERT INTO `tb_sys_user_event` VALUES (356, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-24 23:59:29');
INSERT INTO `tb_sys_user_event` VALUES (357, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:01:54');
INSERT INTO `tb_sys_user_event` VALUES (358, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:09:22');
INSERT INTO `tb_sys_user_event` VALUES (359, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:09:23');
INSERT INTO `tb_sys_user_event` VALUES (360, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:10:14');
INSERT INTO `tb_sys_user_event` VALUES (361, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:10:16');
INSERT INTO `tb_sys_user_event` VALUES (362, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:11:14');
INSERT INTO `tb_sys_user_event` VALUES (363, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:11:15');
INSERT INTO `tb_sys_user_event` VALUES (364, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:11:35');
INSERT INTO `tb_sys_user_event` VALUES (365, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:11:36');
INSERT INTO `tb_sys_user_event` VALUES (366, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:13:38');
INSERT INTO `tb_sys_user_event` VALUES (367, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:13:54');
INSERT INTO `tb_sys_user_event` VALUES (368, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:15:16');
INSERT INTO `tb_sys_user_event` VALUES (369, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:17:51');
INSERT INTO `tb_sys_user_event` VALUES (370, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:17:53');
INSERT INTO `tb_sys_user_event` VALUES (371, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:19:24');
INSERT INTO `tb_sys_user_event` VALUES (372, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:19:55');
INSERT INTO `tb_sys_user_event` VALUES (373, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:27:42');
INSERT INTO `tb_sys_user_event` VALUES (374, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:27:43');
INSERT INTO `tb_sys_user_event` VALUES (375, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:28:04');
INSERT INTO `tb_sys_user_event` VALUES (376, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:28:32');
INSERT INTO `tb_sys_user_event` VALUES (377, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:28:42');
INSERT INTO `tb_sys_user_event` VALUES (378, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:29:10');
INSERT INTO `tb_sys_user_event` VALUES (379, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:30:36');
INSERT INTO `tb_sys_user_event` VALUES (380, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:31:33');
INSERT INTO `tb_sys_user_event` VALUES (381, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:32:13');
INSERT INTO `tb_sys_user_event` VALUES (382, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:33:42');
INSERT INTO `tb_sys_user_event` VALUES (383, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:34:13');
INSERT INTO `tb_sys_user_event` VALUES (384, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:34:32');
INSERT INTO `tb_sys_user_event` VALUES (385, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:35:15');
INSERT INTO `tb_sys_user_event` VALUES (386, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:41:36');
INSERT INTO `tb_sys_user_event` VALUES (387, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:41:57');
INSERT INTO `tb_sys_user_event` VALUES (388, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:41:59');
INSERT INTO `tb_sys_user_event` VALUES (389, 1, 'SITE_MNG', '修改站点配置', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:42:38');
INSERT INTO `tb_sys_user_event` VALUES (390, 1, 'ADMIN_MNG', '修改管理员状态[1：张三]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:46:53');
INSERT INTO `tb_sys_user_event` VALUES (391, 1, 'ADMIN_MNG', '修改管理员状态[1：张三]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:46:58');
INSERT INTO `tb_sys_user_event` VALUES (392, 1, 'ADMIN_MNG', '修改管理员状态[1：张三]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:49:27');
INSERT INTO `tb_sys_user_event` VALUES (393, 1, 'ADMIN_MNG', '修改管理员状态[1：张三]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:49:29');
INSERT INTO `tb_sys_user_event` VALUES (394, 1, 'ADMIN_MNG', '重置管理员[1,张三]登录密码', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:52:01');
INSERT INTO `tb_sys_user_event` VALUES (395, 1, 'ADMIN_MNG', '删除管理员[1,张三]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 00:53:45');
INSERT INTO `tb_sys_user_event` VALUES (396, 1, 'ADMIN_MNG', '修改管理员状态[3：asdf]', '111.22.182.254', '湖南省岳阳市 移通', '2020-07-25 01:04:44');
INSERT INTO `tb_sys_user_event` VALUES (397, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-12 22:03:32');
INSERT INTO `tb_sys_user_event` VALUES (398, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-12 22:05:46');
INSERT INTO `tb_sys_user_event` VALUES (399, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-12 22:17:45');
INSERT INTO `tb_sys_user_event` VALUES (400, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-12 23:45:13');
INSERT INTO `tb_sys_user_event` VALUES (401, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 00:41:06');
INSERT INTO `tb_sys_user_event` VALUES (402, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 00:45:01');
INSERT INTO `tb_sys_user_event` VALUES (403, 1, 'USER_MNG', '修改用户信息[3:test02]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 01:22:48');
INSERT INTO `tb_sys_user_event` VALUES (404, 1, 'USER_MNG', '修改用户信息[3:test02]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 01:22:55');
INSERT INTO `tb_sys_user_event` VALUES (405, 1, 'USER_MNG', '修改用户信息[3:test02]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 01:23:25');
INSERT INTO `tb_sys_user_event` VALUES (406, 1, 'USER_MNG', '修改用户信息[3:test02]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 01:24:40');
INSERT INTO `tb_sys_user_event` VALUES (407, 1, 'USER_MNG', '修改用户信息[4:test03]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 05:10:29');
INSERT INTO `tb_sys_user_event` VALUES (408, 1, 'USER_MNG', '修改用户信息[4:test03]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 05:11:55');
INSERT INTO `tb_sys_user_event` VALUES (409, 1, 'USER_MNG', '修改用户信息[3:test02]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 05:12:02');
INSERT INTO `tb_sys_user_event` VALUES (410, 1, 'USER_MNG', '修改用户信息[4:test03]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 05:12:09');
INSERT INTO `tb_sys_user_event` VALUES (411, 1, 'USER_MNG', '修改用户信息[4:test03]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 05:28:57');
INSERT INTO `tb_sys_user_event` VALUES (412, 1, 'ADMIN_MNG', '添加管理员[4:lisi11]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 05:59:40');
INSERT INTO `tb_sys_user_event` VALUES (413, 1, 'ADMIN_MNG', '添加管理员[7:lisi001]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 06:00:32');
INSERT INTO `tb_sys_user_event` VALUES (414, 1, 'PERM_MNG', '添加权限[38:字典管理]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 06:31:52');
INSERT INTO `tb_sys_user_event` VALUES (415, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 06:32:07');
INSERT INTO `tb_sys_user_event` VALUES (416, 1, 'ROLE_MNG', '授权角色权限【1:role:root】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 06:32:09');
INSERT INTO `tb_sys_user_event` VALUES (417, 1, 'LOGIN', '管理员登录【1，admin】', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 06:32:21');
INSERT INTO `tb_sys_user_event` VALUES (418, 1, 'PERM_MNG', '修改权限[38:字典管理]', '111.22.182.78', '湖南省岳阳市 移通', '2020-09-13 07:14:12');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_role`;
CREATE TABLE `tb_sys_user_role` (
  `sys_user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sys_user_id`,`role_id`) USING BTREE,
  KEY `FK2errn13lk10uamvqsc74vu6do` (`role_id`) USING BTREE,
  CONSTRAINT `FK2errn13lk10uamvqsc74vu6do` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`),
  CONSTRAINT `FKebdoymbjyjs6v94ocxjajplgq` FOREIGN KEY (`sys_user_id`) REFERENCES `tb_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_user_role` VALUES (1, 1);
INSERT INTO `tb_sys_user_role` VALUES (4, 1);
INSERT INTO `tb_sys_user_role` VALUES (3, 2);
INSERT INTO `tb_sys_user_role` VALUES (7, 2);
COMMIT;

-- ----------------------------
-- Table structure for tb_template_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_template_notice`;
CREATE TABLE `tb_template_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '模板类型(0：站内信，1：短信，2：电子邮件，3：推送消息，99：其他)',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '阿里云短信代码',
  `template_code` varchar(20) NOT NULL DEFAULT '' COMMENT '模板代码',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '模板名称',
  `title` varchar(30) DEFAULT '' COMMENT '主题',
  `content` longtext COMMENT '内容',
  `available` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用(0：否，1：是)',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  `aliyun_code` varchar(20) NOT NULL DEFAULT '' COMMENT '阿里云短信代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='通知模板表';

-- ----------------------------
-- Records of tb_template_notice
-- ----------------------------
BEGIN;
INSERT INTO `tb_template_notice` VALUES (1, 1, 'VER_CODE', 'SMS_123456', '发送验证码', NULL, '您的验证码为:#code#,该验证码15分钟内有效,请勿泄漏于他人', 1, '2019-11-25 11:47:27', '2020-03-27 20:48:28', 'SMS_123456');
INSERT INTO `tb_template_notice` VALUES (2, 2, 'VER_CODE', '', '发送验证码', '【JBoot】验证码', '<p>亲爱的用户：</p><p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;您的验证码为:#code#,该验证码15分钟内有效,请勿泄漏于他人</p>', 1, '2019-11-26 10:59:28', '2020-03-27 20:50:49', '');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '昵称',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `photo` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0：正常，1：禁用，1：登录锁定)',
  `login_err_count` tinyint(1) NOT NULL DEFAULT '0' COMMENT '连续登录错误次数',
  `login_lock_time` timestamp NULL DEFAULT NULL COMMENT '登录锁定时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近登录时间',
  `last_login_ip` varchar(46) DEFAULT '' COMMENT '最近登录IP',
  `last_login_ip_addr` varchar(30) DEFAULT '' COMMENT '最近登录IP归属地',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0：否，1：是)',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT '密码加盐',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`),
  UNIQUE KEY `UK_c4dxpoujf358m9ekdstivfqti` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (2, 'test01', '测试啊啊啊', '13212345679', '/images/photo.jpg', 0, 0, NULL, NULL, NULL, NULL, 0, NULL, '2020-03-28 23:49:03', '2020-07-18 13:48:22', '17eb6e826ed0ebde8150826e9d765513', 'QJqsyGo6j2SWPCD7f7p9');
INSERT INTO `tb_user` VALUES (3, 'test02', 'JBoot620266', '13212345670', '/images/photo.jpg', 1, 0, NULL, NULL, NULL, NULL, 0, NULL, '2020-03-29 00:28:09', '2020-09-13 16:36:54', 'f1463d3bd5f1a4b64c9b6d4cffbea6c3', 'CXGCnlaw0SjI72IaxLUI');
INSERT INTO `tb_user` VALUES (4, 'test03', 'JBoot336084', '13223242142', '/images/photo.jpg', 1, 0, NULL, NULL, NULL, NULL, 0, NULL, '2020-03-29 00:55:47', '2020-09-13 16:52:31', '3fc95675bba2b0df64bd7514d2b36fba', 'DA16XBzVlsGeC6IFZhER');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_bind
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_bind`;
CREATE TABLE `tb_user_bind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `open_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'OpenID',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '开放平台(0：微信，1：QQ，2：微博，3：支付宝)',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户登录绑定平台表';

-- ----------------------------
-- Records of tb_user_bind
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_user_event
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_event`;
CREATE TABLE `tb_user_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '事件类型',
  `device_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '设备类型(0：未知，1：安卓，2：苹果)',
  `device_number` varchar(50) NOT NULL DEFAULT '' COMMENT '设备唯一标识符',
  `remark` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `ip` varchar(46) NOT NULL DEFAULT '' COMMENT '操作IP地址',
  `ip_addr` varchar(50) NOT NULL DEFAULT '' COMMENT '操作IP地址归属地',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户操作事件表';

-- ----------------------------
-- Records of tb_user_event
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `realname` varchar(10) DEFAULT '' COMMENT '姓名',
  `id_card` varchar(18) DEFAULT '' COMMENT '身份证号码',
  `is_verify_realname` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已实名验证(0：未验证，1：已验证)',
  `email` varchar(100) DEFAULT '' COMMENT '电子邮件',
  `is_verify_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮箱是否已验证(0：未验证，1：已验证)',
  `gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：保密，1：男，2：女',
  `wx_number` varchar(20) DEFAULT '' COMMENT '微信号',
  `alipay_number` varchar(20) DEFAULT '' COMMENT '支付宝账号',
  `qq` varchar(11) DEFAULT '' COMMENT 'QQ号码',
  `weibo` varchar(20) DEFAULT '' COMMENT '微博账号',
  `source` tinyint(2) NOT NULL DEFAULT '0' COMMENT '注册来源(0：未知，1：安卓，2：苹果，3：微信，99：其他)',
  `ios_device_number` varchar(50) DEFAULT '' COMMENT 'IOS设备号',
  `android_device_number` varchar(50) DEFAULT '' COMMENT 'Android设备号',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期',
  `device_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '当前使用设备类型(0：未知，1：安卓，2：苹果，3：微信)',
  `device_token` varchar(64) DEFAULT '' COMMENT '设备识别码(推送Api提供)',
  `addr` varchar(200) DEFAULT '' COMMENT '当前住址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_kaam2e8lownim63k9ffvdhfxd` (`user_id`) USING BTREE,
  UNIQUE KEY `UK_4qomiubpnsspuhudrn6937grv` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_info` VALUES (1, 2, '13212345679', NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2020-03-28 23:49:03', '2020-03-29 00:16:26', 0, NULL, NULL);
INSERT INTO `tb_user_info` VALUES (2, 3, '13212345670', NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2020-03-29 00:28:09', '2020-09-13 05:12:02', 0, NULL, NULL);
INSERT INTO `tb_user_info` VALUES (3, 4, '13223242142', NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2020-03-29 00:55:47', '2020-09-13 05:28:57', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for templatedto
-- ----------------------------
DROP TABLE IF EXISTS `templatedto`;
CREATE TABLE `templatedto` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of templatedto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_event_bean
-- ----------------------------
DROP TABLE IF EXISTS `user_event_bean`;
CREATE TABLE `user_event_bean` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `device_type` int(11) DEFAULT NULL,
  `device_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_event_bean
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_eventdto
-- ----------------------------
DROP TABLE IF EXISTS `user_eventdto`;
CREATE TABLE `user_eventdto` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `device_type` int(11) DEFAULT NULL,
  `device_number` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_eventdto
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
