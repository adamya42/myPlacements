-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: placements
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `code` tinyint(4) NOT NULL AUTO_INCREMENT,
  `meaning` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'Male'),(2,'Female'),(3,'Others'),(4,'Not Filled');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (111);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level2`
--

DROP TABLE IF EXISTS `level2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level2` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Designation` varchar(45) DEFAULT NULL,
  `EmployeeId` varchar(45) DEFAULT NULL,
  `PhoneNo` bigint(10) DEFAULT '0',
  `DOB` datetime DEFAULT NULL,
  `Gender` tinyint(4) DEFAULT '4',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UserId_UNIQUE` (`UserId`),
  KEY `fk_UserId_Userid_idx` (`UserId`),
  CONSTRAINT `fk_UserId_Userid` FOREIGN KEY (`UserId`) REFERENCES `users` (`Userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level2`
--

LOCK TABLES `level2` WRITE;
/*!40000 ALTER TABLE `level2` DISABLE KEYS */;
INSERT INTO `level2` VALUES (0,8,'Manikandan R',NULL,NULL,0,NULL,1),(17,16,NULL,NULL,NULL,0,NULL,4);
/*!40000 ALTER TABLE `level2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level4`
--

DROP TABLE IF EXISTS `level4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level4` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Designation` varchar(45) DEFAULT NULL,
  `PhoneNo` bigint(10) DEFAULT '0',
  `EmployeeId` varchar(45) DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `Gender` tinyint(4) DEFAULT '4',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UserId_UNIQUE` (`UserId`),
  KEY `fk_Userid_userid_idx` (`UserId`),
  CONSTRAINT `fk_Userid` FOREIGN KEY (`UserId`) REFERENCES `users` (`Userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level4`
--

LOCK TABLES `level4` WRITE;
/*!40000 ALTER TABLE `level4` DISABLE KEYS */;
INSERT INTO `level4` VALUES (0,10,'Aayush Jaiswal','Progeram Chair',7782456789,'10062','1985-02-17 05:30:00',1),(38,37,NULL,NULL,0,NULL,NULL,4),(100,99,NULL,NULL,0,NULL,NULL,4);
/*!40000 ALTER TABLE `level4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level6`
--

DROP TABLE IF EXISTS `level6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level6` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `RegistrationId` varchar(45) DEFAULT NULL,
  `PhoneNo` bigint(10) DEFAULT '0',
  `School` varchar(45) DEFAULT NULL,
  `Branch` varchar(45) DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `Gender` tinyint(4) DEFAULT '4',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UserId_UNIQUE` (`UserId`),
  KEY `userid_idx` (`UserId`),
  CONSTRAINT `userid` FOREIGN KEY (`UserId`) REFERENCES `users` (`Userid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level6`
--

LOCK TABLES `level6` WRITE;
/*!40000 ALTER TABLE `level6` DISABLE KEYS */;
INSERT INTO `level6` VALUES (0,6,'Rahul Raj','17BCE10062',9595454585,'SCSE','Core','1998-04-03 00:00:00',1);
/*!40000 ALTER TABLE `level6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `RoleId` int(11) NOT NULL,
  `RoleAs` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'administrator'),(2,'Placement Cordinator'),(4,'Department Cordinator'),(6,'Student');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schools`
--

DROP TABLE IF EXISTS `schools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schools` (
  `Id` int(11) NOT NULL,
  `SchoolName` varchar(45) DEFAULT NULL,
  `SchoolCode` varchar(45) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  `ModifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schools`
--

LOCK TABLES `schools` WRITE;
/*!40000 ALTER TABLE `schools` DISABLE KEYS */;
/*!40000 ALTER TABLE `schools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userotp`
--

DROP TABLE IF EXISTS `userotp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userotp` (
  `UserOTPId` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `OTP` int(11) DEFAULT NULL,
  `ValidTill` datetime DEFAULT NULL,
  `IsOTPVerified` int(11) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`UserOTPId`),
  KEY `fk_userid_otp_idx` (`UserId`),
  CONSTRAINT `fk_userid_otp` FOREIGN KEY (`UserId`) REFERENCES `users` (`Userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userotp`
--

LOCK TABLES `userotp` WRITE;
/*!40000 ALTER TABLE `userotp` DISABLE KEYS */;
/*!40000 ALTER TABLE `userotp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Userid` int(11) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `RoleId` int(11) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  `ModifiedBy` int(11) DEFAULT NULL,
  `IsActive` bit(1) NOT NULL DEFAULT b'0',
  `IsVerified` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`Userid`),
  KEY `fk__idx` (`RoleId`),
  CONSTRAINT `fk_Roleid_role` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'adamya.bagaria2017@vitbhopal.ac.in','GFJ76',0,'2019-12-12 22:16:38',0,'2019-12-13 15:02:24',0,_binary '\0',_binary '\0'),(6,'rahul.raj2017@vitbhopal.ac.in','4V9S1',6,'2019-12-13 22:58:20',0,'2019-12-13 23:01:06',0,_binary '\0',_binary '\0'),(8,'manikandan.r@vitbhopal.ac.in','7UJ4F',2,'2019-12-17 14:13:10',NULL,'2019-12-17 14:13:10',NULL,_binary '\0',_binary '\0'),(10,'aayush1512jais@gmail.com','VFCAR',4,'2020-04-08 16:33:26',NULL,'2020-04-11 13:58:19',NULL,_binary '',_binary ''),(16,'aayuhjaiswal911@gmail.com','FCM0V',2,'2020-04-15 18:21:55',NULL,'2020-04-15 18:21:55',NULL,_binary '\0',_binary '\0'),(37,'aayush.jaiswal2017@vitbhopal.ac.in','BSLMM',4,'2020-05-03 14:19:40',NULL,'2020-05-03 14:19:40',NULL,_binary '\0',_binary '\0'),(99,'shivani.sharma2017@vitbhopal.ac.in','LZU2W',4,'2020-05-10 14:32:39',NULL,'2020-05-10 14:32:39',NULL,_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-26  8:08:12
