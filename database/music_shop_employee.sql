-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: music_shop
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(100) NOT NULL,
  `join_date` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `date_of_birth` varchar(100) NOT NULL,
  `role_id` int NOT NULL,
  `branch_id` int NOT NULL,
  `phonenumber` int NOT NULL,
  `gender` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT '12345',
  PRIMARY KEY (`id`),
  KEY `employee_FK` (`role_id`),
  KEY `employee_FK_1` (`branch_id`),
  CONSTRAINT `employee_FK` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `employee_FK_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Eloise','2018-02-01','Bangka Belitung','23-02-1992',1,1,217258656,'Female','12345'),(4,'Arlo','2019-02-01','Lombok','03-04-1992',1,3,232878393,'Male','12345'),(5,'Kai','2019-02-01','Sumatera Utara','02-06-1992',1,4,361236232,'Female','12345'),(6,'Atticus','2020-02-02','Sulawesi Tenggara','26-12-1994',1,5,215806446,'Female','12345'),(7,'Silas','2019-02-02','Bali','24-10-1993',2,2,217233823,'Female','12345'),(8,'Finn','2018-03-02','Bangka Belitung','20-02-1995',2,1,243582507,'Male','12345'),(9,'Milo','2018-02-01','Bangka Belitung','08-08-1993',2,1,225403293,'Male','12345'),(10,'Felix','2018-02-14','Bangka Belitung','05-04-1994',2,1,313538134,'Male','12345'),(11,'Soren','2019-04-12','Bangka Belitung','13-02-1993',2,1,216328105,'Male','12345'),(12,'Leo','2019-04-12','Bangka Belitung','22-06-1993',2,1,248310181,'Male','12345'),(13,'Oscar','2019-06-12','Bali','24-05-1993',2,2,217207018,'Male','12345'),(14,'Hugo','2019-08-21','Bali','21-03-1992',2,2,215601524,'Male','12345'),(15,'Jasper','2019-09-21','Lombok','11-07-1991',2,3,274522067,'Male','12345'),(16,'Lily','2019-01-01','Lombok','08-02-1992',2,3,215550698,'Female','12345'),(17,'Ruby','2019-02-02','Sumatera Utara','14-03-1993',2,4,217197999,'Female','12345'),(18,'Eva','2020-02-01','Sulawesi Tenggara','15-03-1994',2,5,215277406,'Female','12345'),(19,'Kaia','2020-02-01','Sumatera Utara','04-01-1993',2,4,214209697,'Female','12345'),(20,'Jane','2020-04-02','Sulawesi Tenggara','01-04-1995',2,5,217657923,'Female','12345'),(21,'Rio','2020-04-03','Sulawesi tenggara','12-05-1991',2,5,217657956,'Male','23456');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-01 22:43:58
