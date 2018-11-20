CREATE DATABASE  IF NOT EXISTS `theater` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `theater`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: theater
-- ------------------------------------------------------
-- Server version	5.7.23-log

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
-- Table structure for table `listscreentobranch`
--

DROP TABLE IF EXISTS `listscreentobranch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listscreentobranch` (
  `movieBranchNo` int(10) DEFAULT NULL,
  `movieNo` int(10) DEFAULT NULL,
  KEY `movieBranchNo` (`movieBranchNo`),
  KEY `movieNo` (`movieNo`),
  CONSTRAINT `listscreentobranch_ibfk_1` FOREIGN KEY (`movieBranchNo`) REFERENCES `theater` (`branchNo`),
  CONSTRAINT `listscreentobranch_ibfk_2` FOREIGN KEY (`movieNo`) REFERENCES `movie` (`movieNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listscreentobranch`
--

LOCK TABLES `listscreentobranch` WRITE;
/*!40000 ALTER TABLE `listscreentobranch` DISABLE KEYS */;
/*!40000 ALTER TABLE `listscreentobranch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pw` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birth` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phoneNum` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `movieNO` int(10) NOT NULL,
  `movieName` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `directorName` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `summary` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `showTime` int(10) DEFAULT NULL,
  `releaseDate` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rating` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perforMername` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `genre` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`movieNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payNo` int(10) NOT NULL,
  `type` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`payNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `resNo` int(10) NOT NULL,
  `memberId` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payNo` int(10) DEFAULT NULL,
  `seatNo` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `movieNo` int(10) DEFAULT NULL,
  `movieSchedule` int(10) DEFAULT NULL,
  `bookingTime` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bookingDay` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `screenNum` int(10) DEFAULT NULL,
  `price` int(10) DEFAULT NULL,
  `ispayment` bit(1) DEFAULT NULL,
  PRIMARY KEY (`resNo`),
  KEY `memberId` (`memberId`),
  KEY `payNo` (`payNo`),
  KEY `seatNo` (`seatNo`),
  KEY `movieNo` (`movieNo`),
  KEY `movieSchedule` (`movieSchedule`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`payNo`) REFERENCES `payment` (`payNo`),
  CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`seatNo`) REFERENCES `seat` (`seatNo`),
  CONSTRAINT `reservation_ibfk_4` FOREIGN KEY (`movieNo`) REFERENCES `movie` (`movieNO`),
  CONSTRAINT `reservation_ibfk_5` FOREIGN KEY (`movieSchedule`) REFERENCES `schedule` (`schNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `schNo` int(10) NOT NULL,
  `startTime` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `endTime` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `screeningDate` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`schNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `screen`
--

DROP TABLE IF EXISTS `screen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `screen` (
  `screenNo` int(10) NOT NULL,
  `branchNo` int(10) DEFAULT NULL,
  `schNo` int(10) DEFAULT NULL,
  `totalSeatNum` int(10) DEFAULT NULL,
  `leftSeatNum` int(10) DEFAULT NULL,
  PRIMARY KEY (`screenNo`),
  KEY `schNo` (`schNo`),
  KEY `branchNo` (`branchNo`),
  CONSTRAINT `screen_ibfk_1` FOREIGN KEY (`schNo`) REFERENCES `schedule` (`schNo`),
  CONSTRAINT `screen_ibfk_2` FOREIGN KEY (`branchNo`) REFERENCES `theater` (`branchNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `screen`
--

LOCK TABLES `screen` WRITE;
/*!40000 ALTER TABLE `screen` DISABLE KEYS */;
/*!40000 ALTER TABLE `screen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat` (
  `seatNo` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `screenNo` int(10) DEFAULT NULL,
  `seatType` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isSeat` bit(1) DEFAULT NULL,
  PRIMARY KEY (`seatNo`),
  KEY `screenNo` (`screenNo`),
  CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`screenNo`) REFERENCES `screen` (`screenNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theater`
--

DROP TABLE IF EXISTS `theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theater` (
  `branchNo` int(10) NOT NULL,
  `screenNum` int(10) DEFAULT NULL,
  `address` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tel` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `branchName` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`branchNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theater`
--

LOCK TABLES `theater` WRITE;
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-20 21:10:38
