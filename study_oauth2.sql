-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 43.136.134.51    Database: study-oauth2
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
-- Table structure for table `tb_oauth_access_token`
--

DROP TABLE IF EXISTS `tb_oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_oauth_access_token` (
  `id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `open_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_oauth_access_token`
--

LOCK TABLES `tb_oauth_access_token` WRITE;
/*!40000 ALTER TABLE `tb_oauth_access_token` DISABLE KEYS */;
INSERT INTO `tb_oauth_access_token` VALUES ('1467694816183320577','1','st_RXddT6Cau9lZf3zdhX_799obQA4MVPLWxwThBw7yFJRZYEXqVmLLter'),('1524311804212928513','1524311652144201729','st_wf9aFBASpx6mY4hUd9_qYfvywrvJMtyXLaoUNwj1zGGmciPCvwCTJAK');
/*!40000 ALTER TABLE `tb_oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_oauth_client`
--

DROP TABLE IF EXISTS `tb_oauth_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_oauth_client` (
  `client_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `client_secret` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `resource_ids` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scope` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `grant_types` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_oauth_client`
--

LOCK TABLES `tb_oauth_client` WRITE;
/*!40000 ALTER TABLE `tb_oauth_client` DISABLE KEYS */;
INSERT INTO `tb_oauth_client` VALUES ('1000','qRDt8Gx0Bzj1M5Sel6h2JYHXZP7sKbadF9iLAyEpOUurCgfm4nNIk3vcoWQVTwRkMn5522T5s1WIXnii2qJwagZdFf6605XedTvGbVj9IeKuU8Uw5H355rmWqf36EOdV','','userInfo','code,password','http://baidu.com',NULL);
/*!40000 ALTER TABLE `tb_oauth_client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-26 15:55:16
