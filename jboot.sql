/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.31.116:3306
 Source Schema         : jboot

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 29/03/2020 19:58:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for email_recorddto
-- ----------------------------
DROP TABLE IF EXISTS `email_recorddto`;
CREATE TABLE `email_recorddto`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for push_recorddto
-- ----------------------------
DROP TABLE IF EXISTS `push_recorddto`;
CREATE TABLE `push_recorddto`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `device_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `device_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_recorddto
-- ----------------------------
DROP TABLE IF EXISTS `sms_recorddto`;
CREATE TABLE `sms_recorddto`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permissiondto
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissiondto`;
CREATE TABLE `sys_permissiondto`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pid_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `external` bit(1) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `available` bit(1) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_event_bean
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_event_bean`;
CREATE TABLE `sys_user_event_bean`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_eventdto
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_eventdto`;
CREATE TABLE `sys_user_eventdto`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_3rd_pconline
-- ----------------------------
DROP TABLE IF EXISTS `tb_3rd_pconline`;
CREATE TABLE `tb_3rd_pconline`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
  `addr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '详细地址(市级，国外为0)',
  `pro_code` int(6) NOT NULL DEFAULT 0 COMMENT '行政区划代码(省级，国外为999999)',
  `pro` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '行政区划名称(省级，国外为0)',
  `city_code` int(6) NOT NULL DEFAULT 0 COMMENT '行政区划代码(市级，国外为0)',
  `city` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '行政区划名称(市级，国外为0)',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '太平洋IP地址库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_3rd_pconline
-- ----------------------------
INSERT INTO `tb_3rd_pconline` VALUES (1, '36.157.195.84', '湖南省长沙市 移通', 430000, '湖南省', 430100, '长沙市', '2020-03-27 22:02:17');

-- ----------------------------
-- Table structure for tb_record_email
-- ----------------------------
DROP TABLE IF EXISTS `tb_record_email`;
CREATE TABLE `tb_record_email`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '电子邮箱地址',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮件主题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'HTML内容',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
  `ip_addr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'IP归属地',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电子邮件发送记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_record_push
-- ----------------------------
DROP TABLE IF EXISTS `tb_record_push`;
CREATE TABLE `tb_record_push`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `device_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '设备类型(0：未知，1：安卓，2：苹果)',
  `device_token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '推送设备号',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '通知主题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '通知内容',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
  `ip_addr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'IP归属地',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '推送通知发送记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_record_sms
-- ----------------------------
DROP TABLE IF EXISTS `tb_record_sms`;
CREATE TABLE `tb_record_sms`  (
  `id` bigint(20) NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'HTML内容',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址',
  `ip_addr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'IP归属地',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信发送记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FKn1fuq85qvjb8i00n532hji0k5`(`permission_id`) USING BTREE,
  CONSTRAINT `FKgtg5vqxwmv8e5bu48jr03986d` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKn1fuq85qvjb8i00n532hji0k5` FOREIGN KEY (`permission_id`) REFERENCES `tb_sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
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

-- ----------------------------
-- Table structure for tb_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_config`;
CREATE TABLE `tb_sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '键',
  `sys_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `description` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '描述',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_jusrsh41rp7cngcoxxgreodqm`(`sys_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_config
-- ----------------------------
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

-- ----------------------------
-- Table structure for tb_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_permission`;
CREATE TABLE `tb_sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父节点ID(0：一级权限)',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '资源名称',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '资源类型(button,menu)',
  `permission` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限',
  `uri` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'URI',
  `external` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否外部链接(0:否,1:是)',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图标',
  `sort` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '序号',
  `available` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可用(0:否，1：是)',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_permission
-- ----------------------------
INSERT INTO `tb_sys_permission` VALUES (1, 0, '主页', 'MENU', '', '', 0, 'layui-icon-home', 0, 1, '2020-03-25 00:10:36', '2020-03-29 19:55:03');
INSERT INTO `tb_sys_permission` VALUES (2, 0, '用户管理', 'MENU', '', '', 0, 'layui-icon-username', 1, 1, '2020-03-25 00:12:02', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (3, 2, '用户管理', 'MENU', 'user:query', '/admin/user', 0, 'layui-icon-user', 0, 1, '2020-03-25 00:12:36', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (4, 2, '操作日志', 'MENU', 'user-event:query', '/admin/user-event', 0, 'layui-icon-about', 1, 1, '2020-03-25 23:34:37', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (5, 0, '系统设置', 'MENU', '', '', 0, 'layui-icon-set', 2, 1, '2020-03-25 00:10:48', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (6, 5, '管理员', 'MENU', 'sysuser:query', '/admin/sysuser', 0, 'layui-icon-user', 0, 1, '2020-03-25 00:13:19', '2020-03-27 21:30:22');
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
INSERT INTO `tb_sys_permission` VALUES (20, 3, '添加用户', 'BUTTON', 'user:create', '/admin/user/add', 0, '', 0, 1, '2020-03-25 21:43:25', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (21, 3, '修改用户', 'BUTTON', 'user:update', '/admin/user/modify', 0, '', 0, 1, '2020-03-25 21:43:42', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (22, 3, '删除用户', 'BUTTON', 'user:delete', '/admin/user/delete', 0, '', 0, 1, '2020-03-25 21:43:59', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (23, 7, '角色授权', 'BUTTON', 'role:auth', '/admin/role/authorize', 0, '', 0, 1, '2020-03-25 21:45:09', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (24, 9, '修改站点配置', 'BUTTON', 'site:update', '/admin/site/modify', 0, '', 0, 1, '2020-03-25 21:47:01', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (25, 0, '通知管理', 'BUTTON', '', '', 0, 'layui-icon-notice', 4, 1, '2020-03-26 22:46:17', '2020-03-29 19:51:38');
INSERT INTO `tb_sys_permission` VALUES (26, 25, '通知模板', 'MENU', 'template:query', '/admin/template', 0, '', 0, 1, '2020-03-26 22:53:15', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (27, 26, '修改通知模板', 'BUTTON', 'template:update', '/admin/template/modify', 0, '', 0, 1, '2020-03-26 22:53:27', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (28, 26, '删除通知模板', 'BUTTON', 'template:delete', '/admin/template/delete', 0, '', 0, 1, '2020-03-26 22:53:44', '2020-03-27 21:30:34');
INSERT INTO `tb_sys_permission` VALUES (29, 25, '短信记录', 'MENU', 'smsrecord:query', '/admin/smsrecord', 0, '', 1, 1, '2020-03-26 22:54:06', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (30, 25, '邮件记录', 'MENU', 'emailrecord:query', '/admin/emailrecord', 0, '', 2, 1, '2020-03-26 22:54:23', '2020-03-27 21:30:22');
INSERT INTO `tb_sys_permission` VALUES (31, 25, '推送记录', 'MENU', 'pushrecord:query', '/admin/pushrecord', 0, '', 2, 1, '2020-03-26 22:54:39', '2020-03-27 21:30:22');

-- ----------------------------
-- Table structure for tb_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色描述',
  `available` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可用(0:否，1：是)',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
INSERT INTO `tb_sys_role` VALUES (1, 'role:root', '超级管理员', 1, '2020-03-24 19:48:52', '2020-03-27 21:57:17');
INSERT INTO `tb_sys_role` VALUES (2, 'role:admin', '普通管理员', 1, '2020-03-24 19:48:52', '2020-03-27 22:40:55');

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `wx_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微信号码',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码加盐',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态(0:正常，1：禁用，2：登录锁定)',
  `login_err_count` tinyint(2) NOT NULL DEFAULT 0 COMMENT '登录错误次数',
  `login_lock_time` timestamp(0) NULL DEFAULT NULL COMMENT '登录锁定时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录IP',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已删除(0:未删除，1：已删除)',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  `last_login_ip_addr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP归属地',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '电子邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_h7ijwj639ve7gqr9se2yt6k08`(`username`) USING BTREE,
  UNIQUE INDEX `UK_6kg2i0vryqmphpmk5011xkvnp`(`mobile`) USING BTREE,
  UNIQUE INDEX `UK_pc4o8mw42f67tiapl134lnau5`(`realname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
INSERT INTO `tb_sys_user` VALUES (1, 'admin', '张三', '13212345678', '', 'e4937899acc38c0778fe18f9bc463801', '5baUDLONss7vOwrumGtc', 0, 0, NULL, '36.157.195.84', '2020-03-28 22:02:37', 0, NULL, '2020-03-24 19:48:52', '2020-03-28 22:02:37', '湖南省长沙市 移通', '');
INSERT INTO `tb_sys_user` VALUES (3, 'test0', 'asdf', '13212345679', '', 'c4c0b4ee2cc0a17b4791b318bf469c22', 'ZsViKkzFOJ7qH03po2au', 0, 0, NULL, NULL, NULL, 1, '2020-03-27 23:27:29', '2020-03-27 23:21:47', '2020-03-27 23:27:29', NULL, NULL);

-- ----------------------------
-- Table structure for tb_sys_user_event
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_event`;
CREATE TABLE `tb_sys_user_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '管理员ID',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '事件类型',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作IP地址',
  `ip_addr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作IP地址归属地',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 224 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员操作事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_user_event
-- ----------------------------
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

-- ----------------------------
-- Table structure for tb_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_role`;
CREATE TABLE `tb_sys_user_role`  (
  `sys_user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sys_user_id`, `role_id`) USING BTREE,
  INDEX `FK2errn13lk10uamvqsc74vu6do`(`role_id`) USING BTREE,
  CONSTRAINT `FK2errn13lk10uamvqsc74vu6do` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKebdoymbjyjs6v94ocxjajplgq` FOREIGN KEY (`sys_user_id`) REFERENCES `tb_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sys_user_role
-- ----------------------------
INSERT INTO `tb_sys_user_role` VALUES (1, 1);
INSERT INTO `tb_sys_user_role` VALUES (3, 2);

-- ----------------------------
-- Table structure for tb_template_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_template_notice`;
CREATE TABLE `tb_template_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '模板类型(0：站内信，1：短信，2：电子邮件，3：推送消息，99：其他)',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '阿里云短信代码',
  `template_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '模板代码',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '模板名称',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `available` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否可用(0：否，1：是)',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  `aliyun_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '阿里云短信代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_template_notice
-- ----------------------------
INSERT INTO `tb_template_notice` VALUES (1, 1, 'VER_CODE', 'SMS_123456', '发送验证码', NULL, '您的验证码为:#code#,该验证码15分钟内有效,请勿泄漏于他人', 1, '2019-11-25 11:47:27', '2020-03-27 20:48:28', 'SMS_123456');
INSERT INTO `tb_template_notice` VALUES (2, 2, 'VER_CODE', '', '发送验证码', '【JBoot】验证码', '<p>亲爱的用户：</p><p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;您的验证码为:#code#,该验证码15分钟内有效,请勿泄漏于他人</p>', 1, '2019-11-26 10:59:28', '2020-03-27 20:50:49', '');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态(0：正常，1：禁用，1：登录锁定)',
  `login_err_count` tinyint(1) NOT NULL DEFAULT 0 COMMENT '连续登录错误次数',
  `login_lock_time` timestamp(0) NULL DEFAULT NULL COMMENT '登录锁定时间',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最近登录时间',
  `last_login_ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近登录IP',
  `last_login_ip_addr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近登录IP归属地',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已删除(0：否，1：是)',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码加盐',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_4wv83hfajry5tdoamn8wsqa6x`(`username`) USING BTREE,
  UNIQUE INDEX `UK_c4dxpoujf358m9ekdstivfqti`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (2, 'test01', '测试啊啊啊', '13212345679', '/images/photo.jpg', 0, 0, NULL, NULL, NULL, NULL, 0, NULL, '2020-03-28 23:49:03', '2020-03-29 00:16:26', 'a990e46ed485838f68baae313dd73e52', 'bKLo9iaJdrIMXH5gbfzD');
INSERT INTO `tb_user` VALUES (3, 'test02', 'JBoot620266', '13212345670', '/images/photo.jpg', 0, 0, NULL, NULL, NULL, NULL, 0, NULL, '2020-03-29 00:28:09', '2020-03-29 00:28:09', 'f1463d3bd5f1a4b64c9b6d4cffbea6c3', 'CXGCnlaw0SjI72IaxLUI');
INSERT INTO `tb_user` VALUES (4, 'test03', 'JBoot336084', '13223242142', '/images/photo.jpg', 0, 0, NULL, NULL, NULL, NULL, 0, NULL, '2020-03-29 00:55:47', '2020-03-29 01:16:22', '66dac145916f1023d079142882939bd6', 'tv7USZ0nPnHrnQufuRYZ');

-- ----------------------------
-- Table structure for tb_user_bind
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_bind`;
CREATE TABLE `tb_user_bind`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'OpenID',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '开放平台(0：微信，1：QQ，2：微博，3：支付宝)',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录绑定平台表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_event
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_event`;
CREATE TABLE `tb_user_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '事件类型',
  `device_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '设备类型(0：未知，1：安卓，2：苹果)',
  `device_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备唯一标识符',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作IP地址',
  `ip_addr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作IP地址归属地',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户操作事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `realname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '身份证号码',
  `is_verify_realname` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已实名验证(0：未验证，1：已验证)',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '电子邮件',
  `is_verify_email` tinyint(1) NOT NULL DEFAULT 0 COMMENT '邮箱是否已验证(0：未验证，1：已验证)',
  `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0：保密，1：男，2：女',
  `wx_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微信号',
  `alipay_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '支付宝账号',
  `qq` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'QQ号码',
  `weibo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微博账号',
  `source` tinyint(2) NOT NULL DEFAULT 0 COMMENT '注册来源(0：未知，1：安卓，2：苹果，3：微信，99：其他)',
  `ios_device_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'IOS设备号',
  `android_device_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'Android设备号',
  `gmt_created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  `device_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '当前使用设备类型(0：未知，1：安卓，2：苹果，3：微信)',
  `device_token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '设备识别码(推送Api提供)',
  `addr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '当前住址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_kaam2e8lownim63k9ffvdhfxd`(`user_id`) USING BTREE,
  UNIQUE INDEX `UK_4qomiubpnsspuhudrn6937grv`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES (1, 2, '13212345679', NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2020-03-28 23:49:03', '2020-03-29 00:16:26', 0, NULL, NULL);
INSERT INTO `tb_user_info` VALUES (2, 3, '13212345670', NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2020-03-29 00:28:09', '2020-03-29 00:28:09', 0, NULL, NULL);
INSERT INTO `tb_user_info` VALUES (3, 4, '13223242142', NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2020-03-29 00:55:47', '2020-03-29 00:55:55', 0, NULL, NULL);

-- ----------------------------
-- Table structure for templatedto
-- ----------------------------
DROP TABLE IF EXISTS `templatedto`;
CREATE TABLE `templatedto`  (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_event_bean
-- ----------------------------
DROP TABLE IF EXISTS `user_event_bean`;
CREATE TABLE `user_event_bean`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  `device_type` int(11) NULL DEFAULT NULL,
  `device_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_eventdto
-- ----------------------------
DROP TABLE IF EXISTS `user_eventdto`;
CREATE TABLE `user_eventdto`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `device_type` int(11) NULL DEFAULT NULL,
  `device_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
