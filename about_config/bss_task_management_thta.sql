CREATE DATABASE  IF NOT EXISTS `bss_thta` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bss_thta`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bss_thta
-- ------------------------------------------------------
-- Server version	5.5.59

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bs_board`
--

DROP TABLE IF EXISTS `bs_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bs_board` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_title` varchar(100) NOT NULL,
  `reg_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bs_board`
--

LOCK TABLES `bs_board` WRITE;
/*!40000 ALTER TABLE `bs_board` DISABLE KEYS */;
INSERT INTO `bs_board` VALUES (5,'Create board by UserId 4','20/02/2019 13:18:17'),(6,'Create board by UserId 1','20/02/2019 13:19:03'),(7,'Create board by TeamId 3','20/02/2019 13:19:15'),(9,'this board id 9','20/02/2019 13:21:07'),(10,'Test board create by User id','21/02/2019 10:09:35'),(11,'Test board create by team id','21/02/2019 10:11:12');
/*!40000 ALTER TABLE `bs_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bs_team`
--

DROP TABLE IF EXISTS `bs_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bs_team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(100) NOT NULL,
  `team_desc` varchar(1000) DEFAULT NULL,
  `reg_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bs_team`
--

LOCK TABLES `bs_team` WRITE;
/*!40000 ALTER TABLE `bs_team` DISABLE KEYS */;
INSERT INTO `bs_team` VALUES (1,'hello','hello desc','19/02/2019 17:01:59'),(2,'hello','hello desc','19/02/2019 17:04:57'),(3,'Hello',NULL,'19/02/2019 18:55:01'),(6,'Test Team 2 modify team_id 6','Test Team 2 modify team_id 6','20/02/2019 12:36:11'),(7,'Test Team 1','Test Team 1 description','20/02/2019 13:16:06'),(8,'create team user 3','create team test1','21/02/2019 11:01:16');
/*!40000 ALTER TABLE `bs_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bs_user`
--

DROP TABLE IF EXISTS `bs_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bs_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_pwd` varchar(45) NOT NULL,
  `reg_date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bs_user`
--

LOCK TABLES `bs_user` WRITE;
/*!40000 ALTER TABLE `bs_user` DISABLE KEYS */;
INSERT INTO `bs_user` VALUES (3,'Hay','hay@gmail.com','123456','2019-02-19 13:29:11'),(4,'aye','aye@gmail.com','123456','19/02/2019 14:59:38'),(5,'thi','thi@gmail.com','123',NULL),(6,'ei','ei@gmail.com','123','20/02/2019 11:14:23');
/*!40000 ALTER TABLE `bs_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_team_board`
--

DROP TABLE IF EXISTS `user_team_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_team_board` (
  `user_id` int(11) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  `board_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_team_board`
--

LOCK TABLES `user_team_board` WRITE;
/*!40000 ALTER TABLE `user_team_board` DISABLE KEYS */;
INSERT INTO `user_team_board` VALUES (3,6,NULL),(4,7,NULL),(4,NULL,5),(6,NULL,6),(NULL,7,9),(3,NULL,10),(NULL,7,11),(NULL,7,13),(3,8,NULL),(4,9,NULL);
/*!40000 ALTER TABLE `user_team_board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-21 19:41:26
