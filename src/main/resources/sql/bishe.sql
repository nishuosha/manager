-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: bishe
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) DEFAULT NULL,
  `adminpwd` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `registertime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'t1','qwer','321','1074118678@qq.com',NULL),(2,'t2','test','312','1074118678@qq.com',NULL),(3,'t3','test','123','1074118678@qq.com','2018-05-08 11:07:50'),(4,'t4','test',NULL,'1074118678@qq.com','2018-05-10 15:29:31'),(5,'t5','test',NULL,'1074118678@qq.com',NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `park`
--

DROP TABLE IF EXISTS `park`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `park` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `enterCount` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `free` int(11) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sponsor` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `id_idx` (`sponsor`),
  CONSTRAINT `id` FOREIGN KEY (`sponsor`) REFERENCES `admin` (`aid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `park`
--

LOCK TABLES `park` WRITE;
/*!40000 ALTER TABLE `park` DISABLE KEYS */;
INSERT INTO `park` VALUES (1,'test','测试',4,5,5,25,25,NULL,NULL,1),(2,'test2','test2',4,6,6,36,36,NULL,NULL,1),(3,'test3','test3',3,5,5,25,25,NULL,NULL,2),(4,'测试','北京',4,5,5,25,0,'2018-05-08 17:12:58','环境良好',1),(5,'13','123',3,6,6,36,0,'2018-05-08 17:15:33','666',1),(6,'t3','didu',4,7,7,49,0,'2018-05-08 17:19:23','777',2);
/*!40000 ALTER TABLE `park` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` varchar(20) DEFAULT NULL,
  `reservetime` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `adminid` int(11) DEFAULT NULL,
  `parkid` int(11) DEFAULT NULL,
  `parknum` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `userid_idx` (`userid`),
  KEY `adminid_idx` (`adminid`),
  KEY `parkid_idx` (`parkid`),
  CONSTRAINT `adminid` FOREIGN KEY (`adminid`) REFERENCES `admin` (`aid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `parkid` FOREIGN KEY (`parkid`) REFERENCES `park` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (7,'2018-05-08 16:53:09','2018-05-01','test',1,NULL,1,3,'已过期'),(8,'2018-05-09 19:56:44','2018-05-02','test',1,NULL,1,2,'已过期'),(9,'2018-05-09 19:58:28','2018-05-03','test',1,NULL,1,7,'已过期'),(10,'2018-05-10 12:09:57','2018-05-04','test',1,NULL,1,4,'已过期'),(11,'2018-05-10 12:18:48','2018-05-05','',1,NULL,1,5,'已过期');
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `userpwd` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `registertime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','test','123','1074118678@qq.com',NULL),(2,'zhanghao','test','123456','1074118678@qq.com','2018-05-08 11:03:26'),(3,'zhanghao68','test','123456','1074118678@qq.com','2018-05-08 11:07:14'),(4,'zhanghao67','test','123456','1074118678@qq.com','2018-05-09 14:28:30'),(5,'nicai','nicai','1234','1074118678@qq.com','2018-05-10 15:27:02');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-10 21:03:54
