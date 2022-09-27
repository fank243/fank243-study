-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 43.136.134.51    Database: study-system
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_sys_perm`
--

DROP TABLE IF EXISTS `tb_sys_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_perm` (
  `perm_id` bigint DEFAULT NULL,
  `pid` bigint DEFAULT NULL,
  `perm_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perm_uri` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perm_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perm_desc` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perm_type` int DEFAULT NULL,
  `is_external` tinyint DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `created_by` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_perm`
--

LOCK TABLES `tb_sys_perm` WRITE;
/*!40000 ALTER TABLE `tb_sys_perm` DISABLE KEYS */;
INSERT INTO `tb_sys_perm` VALUES (1,5,'admin:create','/system/admin/add','新增管理员','新增管理员',2,0,0,'','2021-08-10 23:31:14','','2021-08-10 23:31:12'),(2,5,'admin:modify','/system/admin/modify','修改管理员','修改管理员',2,0,0,'','2021-08-10 23:31:14','','2021-08-10 23:31:12'),(3,5,'admin:delete','/system/admin/delete','删除管理员','删除管理员',2,0,0,'','2021-08-10 23:31:14','','2021-08-10 23:31:12'),(4,5,'admin:status','/system/admin/modifyStatus','更新管理员状态','更新管理员状态',2,0,0,'1','2022-05-11 17:32:35','1','2022-05-11 17:32:38'),(5,6,'admin:list','/system/admin/page','用户管理','用户管理',1,0,0,' ','2022-06-28 14:20:47',' ','2022-06-28 14:20:51'),(6,0,'system:dir',' ','系统管理','系统管理',0,0,0,' ','2022-06-28 15:30:47',' ','2022-06-28 15:30:50'),(7,6,'perm:list','/system/perm/page','菜单管理','菜单管理',1,0,0,' ','2022-06-28 15:30:47',' ','2022-06-28 15:30:50');
/*!40000 ALTER TABLE `tb_sys_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_role`
--

DROP TABLE IF EXISTS `tb_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_role` (
  `role_id` bigint DEFAULT NULL,
  `role_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_name` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_desc` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `is_deleted` tinyint unsigned DEFAULT NULL,
  `created_by` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_role`
--

LOCK TABLES `tb_sys_role` WRITE;
/*!40000 ALTER TABLE `tb_sys_role` DISABLE KEYS */;
INSERT INTO `tb_sys_role` VALUES (1,'system','管理员',NULL,0,0,'','2021-08-10 23:31:14','','2021-08-10 23:31:12');
/*!40000 ALTER TABLE `tb_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_role_perm`
--

DROP TABLE IF EXISTS `tb_sys_role_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_role_perm` (
  `id` bigint DEFAULT NULL,
  `role_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perm_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_role_perm`
--

LOCK TABLES `tb_sys_role_perm` WRITE;
/*!40000 ALTER TABLE `tb_sys_role_perm` DISABLE KEYS */;
INSERT INTO `tb_sys_role_perm` VALUES (1,'1','1'),(3,'1','2'),(4,'1','3'),(5,'1','4'),(6,'1','5'),(7,'1','7');
/*!40000 ALTER TABLE `tb_sys_role_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_user`
--

DROP TABLE IF EXISTS `tb_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_user` (
  `user_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `login_err_count` int DEFAULT NULL,
  `login_lock_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_deleted` tinyint unsigned DEFAULT NULL,
  `created_by` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_user`
--

LOCK TABLES `tb_sys_user` WRITE;
/*!40000 ALTER TABLE `tb_sys_user` DISABLE KEYS */;
INSERT INTO `tb_sys_user` VALUES ('1524311652144201729','admin','','e10adc3949ba59abbe56e057f20f883e',0,0,NULL,'2022-09-26 14:06:51','10.23.164.204',0,'','2022-05-11 16:53:13','','2022-05-11 16:53:13'),('1524312015064743938','test','','4297f44b13955235245b2497399d7a93',0,0,NULL,NULL,'',0,'1524311652144201729','2022-05-11 16:54:39','','2022-09-26 09:56:23');
/*!40000 ALTER TABLE `tb_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_user_login_log`
--

DROP TABLE IF EXISTS `tb_sys_user_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_user_login_log` (
  `id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_ip` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `login_device` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_user_login_log`
--

LOCK TABLES `tb_sys_user_login_log` WRITE;
/*!40000 ALTER TABLE `tb_sys_user_login_log` DISABLE KEYS */;
INSERT INTO `tb_sys_user_login_log` VALUES ('1541299119236644865','1524311652144201729','2022-06-27 13:55:21','0:0:0:0:0:0:0:1',''),('1541299991412789249','1524311652144201729','2022-06-27 13:58:49','0:0:0:0:0:0:0:1',''),('1541303301687517185','1524311652144201729','2022-06-27 14:11:58','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541318387319730177','1524311652144201729','2022-06-27 15:11:54','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541319506955628546','1524311652144201729','2022-06-27 15:16:21','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541319769988820993','1524311652144201729','2022-06-27 15:17:24','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541323876405374978','1524311652144201729','2022-06-27 15:33:43','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541328078749855746','1524311652144201729','2022-06-27 15:50:25','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541329315683659778','1524311652144201729','2022-06-27 15:55:20','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541330044825661442','1524311652144201729','2022-06-27 15:58:14','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541340885109207041','1524311652144201729','2022-06-27 16:41:18','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541342444169814018','1524311652144201729','2022-06-27 16:47:30','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541342660168081409','1524311652144201729','2022-06-27 16:48:22','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541351569888993282','1524311652144201729','2022-06-27 17:23:46','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541351899192188929','1524311652144201729','2022-06-27 17:25:04','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541593402728583169','1524311652144201729','2022-06-28 09:24:43','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541598848860360706','1524311652144201729','2022-06-28 09:46:22','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541600289675087873','1524311652144201729','2022-06-28 09:52:05','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541600302450937857','1524311652144201729','2022-06-28 09:52:08','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541600687353827329','1524311652144201729','2022-06-28 09:53:40','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541600885157203969','1524311652144201729','2022-06-28 09:54:27','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541600960017141762','1524311652144201729','2022-06-28 09:54:45','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541601597572321282','1524311652144201729','2022-06-28 09:57:17','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541601945997348865','1524311652144201729','2022-06-28 09:58:40','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541602223282786306','1524311652144201729','2022-06-28 09:59:46','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541602312499826690','1524311652144201729','2022-06-28 10:00:08','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541602938692636673','1524311652144201729','2022-06-28 10:02:37','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541603175826001922','1524311652144201729','2022-06-28 10:03:33','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541603777318555649','1524311652144201729','2022-06-28 10:05:57','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541604413720301569','1524311652144201729','2022-06-28 10:08:29','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1541610089079181313','1524311652144201729','2022-06-28 10:31:02','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542029466609987585','1524311652144201729','2022-06-29 14:17:29','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542030662477361154','1524311652144201729','2022-06-29 14:22:14','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542032329004363777','1524311652144201729','2022-06-29 14:28:51','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542032400550801409','1524311652144201729','2022-06-29 14:29:09','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542032431995498498','1524311652144201729','2022-06-29 14:29:16','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542032856811384833','1524311652144201729','2022-06-29 14:30:57','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542033709194620929','1524311652144201729','2022-06-29 14:34:21','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542034621803868161','1524311652144201729','2022-06-29 14:37:58','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542035245270380546','1524311652144201729','2022-06-29 14:40:27','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542035670124015617','1524311652144201729','2022-06-29 14:42:08','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542036381851267073','1524311652144201729','2022-06-29 14:44:58','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542036861532844034','1524311652144201729','2022-06-29 14:46:52','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542037034333974530','1524311652144201729','2022-06-29 14:47:33','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542037516104314882','1524311652144201729','2022-06-29 14:49:28','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542044094542962690','1524311652144201729','2022-06-29 15:15:37','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542045557172224002','1524311652144201729','2022-06-29 15:21:25','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542046774829006850','1524311652144201729','2022-06-29 15:26:16','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542064315026677761','1524311652144201729','2022-06-29 16:35:58','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542078688470278146','1524311652144201729','2022-06-29 17:33:04','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1542078904791506945','1524311652144201729','2022-06-29 17:33:56','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.0'),('1553980749398970370','1524311652144201729','2022-08-01 13:47:37','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1553980821612302338','1524311652144201729','2022-08-01 13:47:54','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1553980837663903745','1524311652144201729','2022-08-01 13:47:58','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1566701170728697858','1524311652144201729','2022-09-05 16:14:02','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572042396260691969','1524311652144201729','2022-09-20 09:58:09','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572461794700062722','1524311652144201729','2022-09-21 13:44:41','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572462008987054081','1524311652144201729','2022-09-21 13:45:33','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572462357869260802','1524311652144201729','2022-09-21 13:46:56','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572462462831718401','1524311652144201729','2022-09-21 13:47:21','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572463763867070466','1524311652144201729','2022-09-21 13:52:31','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572463832670433281','1524311652144201729','2022-09-21 13:52:47','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572463980519649282','1524311652144201729','2022-09-21 13:53:23','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572465926643150850','1524311652144201729','2022-09-21 14:01:07','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572466537585471489','1524311652144201729','2022-09-21 14:03:32','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572466830033317889','1524311652144201729','2022-09-21 14:04:42','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572467059369472001','1524311652144201729','2022-09-21 14:05:37','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572467219138899969','1524311652144201729','2022-09-21 14:06:15','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572468280796934146','1524311652144201729','2022-09-21 14:10:28','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572468329123704833','1524311652144201729','2022-09-21 14:10:39','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572468609315815425','1524311652144201729','2022-09-21 14:11:46','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572468757685125121','1524311652144201729','2022-09-21 14:12:22','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572470616307056642','1524311652144201729','2022-09-21 14:19:45','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572471027013304321','1524311652144201729','2022-09-21 14:21:23','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572471260971581442','1524311652144201729','2022-09-21 14:22:18','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572471670687973377','1524311652144201729','2022-09-21 14:23:56','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572472324722573314','1524311652144201729','2022-09-21 14:26:32','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572472578243072001','1524311652144201729','2022-09-21 14:27:32','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572472986394959874','1524311652144201729','2022-09-21 14:29:10','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572473278045941762','1524311652144201729','2022-09-21 14:30:19','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572473579851280385','1524311652144201729','2022-09-21 14:31:31','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572473683672870913','1524311652144201729','2022-09-21 14:31:56','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572474717988200450','1524311652144201729','2022-09-21 14:36:03','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572474953959796738','1524311652144201729','2022-09-21 14:36:59','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572475805537665025','1524311652144201729','2022-09-21 14:40:22','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572475927076012034','1524311652144201729','2022-09-21 14:40:51','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1572881470450126849','1524311652144201729','2022-09-22 17:32:20','0:0:0:0:0:0:0:1','PostmanRuntime/7.29.2'),('1574216068756987906','1524311652144201729','2022-09-26 09:55:33','10.23.164.204','Java/17.0.3'),('1574230238739111937','1524311652144201729','2022-09-26 10:51:51','10.23.164.204','Java/17.0.3'),('1574233270797250561','1524311652144201729','2022-09-26 11:03:54','10.23.164.204','Java/17.0.3'),('1574233993068982274','1524311652144201729','2022-09-26 11:06:46','10.23.164.204','Java/17.0.3'),('1574279311311609857','1524311652144201729','2022-09-26 14:06:51','10.23.164.204','Java/17.0.3');
/*!40000 ALTER TABLE `tb_sys_user_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_user_role`
--

DROP TABLE IF EXISTS `tb_sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sys_user_role` (
  `id` bigint DEFAULT NULL,
  `user_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_user_role`
--

LOCK TABLES `tb_sys_user_role` WRITE;
/*!40000 ALTER TABLE `tb_sys_user_role` DISABLE KEYS */;
INSERT INTO `tb_sys_user_role` VALUES (1,'1524311652144201729','1');
/*!40000 ALTER TABLE `tb_sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-26 15:55:28
