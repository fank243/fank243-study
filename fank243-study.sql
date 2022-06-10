/*
 Navicat Premium Data Transfer

 Source Server         : mysql@localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : fank243-study

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 31/05/2022 09:14:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_oauth_access_token`;
CREATE TABLE `tb_oauth_access_token`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `open_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'OpenID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统管理员角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_oauth_access_token
-- ----------------------------
INSERT INTO `tb_oauth_access_token` VALUES ('1467694816183320577', '1', 'st_RXddT6Cau9lZf3zdhX_799obQA4MVPLWxwThBw7yFJRZYEXqVmLLter');
INSERT INTO `tb_oauth_access_token` VALUES ('1524311804212928513', '1524311652144201729', 'st_wf9aFBASpx6mY4hUd9_qYfvywrvJMtyXLaoUNwj1zGGmciPCvwCTJAK');

-- ----------------------------
-- Table structure for tb_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `tb_oauth_client`;
CREATE TABLE `tb_oauth_client`  (
  `client_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'AppID',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'AppSecret',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '资源ID集合(英文逗号分割)',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '授权范围',
  `grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权类型',
  `redirect_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '重定向URL',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他信息'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '授权客户端表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_oauth_client
-- ----------------------------
INSERT INTO `tb_oauth_client` VALUES ('1000', 'qRDt8Gx0Bzj1M5Sel6h2JYHXZP7sKbadF9iLAyEpOUurCgfm4nNIk3vcoWQVTwRkMn5522T5s1WIXnii2qJwagZdFf6605XedTvGbVj9IeKuU8Uw5H355rmWqf36EOdV', '', 'userInfo', 'code,password', 'http://baidu.com', NULL);

-- ----------------------------
-- Table structure for tb_sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_perm`;
CREATE TABLE `tb_sys_perm`  (
  `perm_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限ID',
  `perm_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限代码',
  `perm_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限地址',
  `perm_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '权限描述',
  `created_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `last_modified_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '最近修改人',
  `last_modified_date` datetime NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`perm_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`perm_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_sys_perm
-- ----------------------------
INSERT INTO `tb_sys_perm` VALUES ('1', 'system:create', '/system/admin/add', '新增管理员', '', '2021-08-10 23:31:14', '', '2021-08-10 23:31:12');
INSERT INTO `tb_sys_perm` VALUES ('2', 'system:modify', '/system/admin/modify', '修改管理员', '', '2021-08-10 23:31:14', '', '2021-08-10 23:31:12');
INSERT INTO `tb_sys_perm` VALUES ('3', 'system:delete', '/system/admin/delete', '删除管理员', '', '2021-08-10 23:31:14', '', '2021-08-10 23:31:12');
INSERT INTO `tb_sys_perm` VALUES ('4', 'system:status', '/system/admin/modifyStatus', '更新管理员状态', '1', '2022-05-11 17:32:35', '1', '2022-05-11 17:32:38');

-- ----------------------------
-- Table structure for tb_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色ID',
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色代码',
  `role_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '角色描述',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除(0：未删除，1：已删除)',
  `created_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `last_modified_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '最近修改人',
  `last_modified_date` datetime NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
INSERT INTO `tb_sys_role` VALUES ('1', 'system', '管理员', NULL, 0, '', '2021-08-10 23:31:14', '', '2021-08-10 23:31:12');

-- ----------------------------
-- Table structure for tb_sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role_perm`;
CREATE TABLE `tb_sys_role_perm`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色ID',
  `perm_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统角色权限关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_sys_role_perm
-- ----------------------------
INSERT INTO `tb_sys_role_perm` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user`  (
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码',
  `is_deleted` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已删除(0：未删除，1：已删除)',
  `created_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `last_modified_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '最近修改人',
  `last_modified_date` datetime NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
INSERT INTO `tb_sys_user` VALUES ('1524311652144201729', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0, '', '2022-05-11 16:53:13', '', '2022-05-11 16:53:13');
INSERT INTO `tb_sys_user` VALUES ('1524312015064743938', 'test', 'e10adc3949ba59abbe56e057f20f883e', 0, '1524311652144201729', '2022-05-11 16:54:39', '1524311652144201729', '2022-05-11 16:54:39');

-- ----------------------------
-- Table structure for tb_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_role`;
CREATE TABLE `tb_sys_user_role`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统管理员角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_sys_user_role
-- ----------------------------
INSERT INTO `tb_sys_user_role` VALUES ('', '1524311652144201729', '1');

SET FOREIGN_KEY_CHECKS = 1;
