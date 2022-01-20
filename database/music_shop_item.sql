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
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `instrument_id` int NOT NULL,
  `serial_number` varchar(38) NOT NULL DEFAULT (uuid()),
  `description` text,
  `year_of_production` int NOT NULL,
  `country_id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `item_FK` (`instrument_id`),
  KEY `item_FK_1` (`country_id`),
  CONSTRAINT `item_FK` FOREIGN KEY (`instrument_id`) REFERENCES `instrument` (`id`),
  CONSTRAINT `item_FK_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,2,'34829b63-7463-11ec-a405-7a79191e984f','YAMAHA L series LL16D',2018,1,1000.00,2),(2,3,'6f4fb1e4-7517-11ec-89ea-7a79191e984f','Yamaha GL1',2019,1,100.00,20),(3,4,'6f71e056-7517-11ec-89ea-7a79191e984f','Yamaha APX600M ',2018,1,250.00,10),(4,5,'e1a00e97-7517-11ec-89ea-7a79191e984f','Guild M-120E',2020,2,1100.00,5),(5,6,'5751fc67-7518-11ec-89ea-7a79191e984f','Yamaha Pacifica 112J BL',2012,1,200.00,10),(6,7,'57c6d8b8-751a-11ec-89ea-7a79191e984f','Yamaha Pacifica 112J YNS',2012,1,200.00,10),(7,8,'57d2b4e7-751a-11ec-89ea-7a79191e984f','Yamaha Pacifica 112J RM',2012,1,200.00,10),(8,9,'57e24433-751a-11ec-89ea-7a79191e984f','Yamaha TRBX174',2019,1,230.00,7),(9,10,'57f54f2f-751a-11ec-89ea-7a79191e984f','Yamaha BB234',2020,1,300.00,7),(10,11,'5804cace-751a-11ec-89ea-7a79191e984f','Yamaha BB235',2020,1,300.00,7),(11,12,'58128eef-751a-11ec-89ea-7a79191e984f','Yamaha MX88 ',2020,1,1200.00,3),(12,13,'5817d8c6-751a-11ec-89ea-7a79191e984f','Yamaha MODX8',2020,1,2000.00,3),(13,14,'581f25d4-751a-11ec-89ea-7a79191e984f','Yamaha YC-73 ',2021,1,3100.00,3),(14,15,'3e01abb5-798f-11ec-8555-7a79191e984f','Eastman MD315 F-Style',2020,8,883.79,5),(15,16,'3e0cc7d9-798f-11ec-8555-7a79191e984f','Fender California Coast Venice Soprano',2017,9,63.38,9),(16,17,'3e1d8905-798f-11ec-8555-7a79191e984f','Fender Fullerton Jazzmaster Uke, Olympic White',2020,10,195.63,3),(17,18,'44c5c54f-7990-11ec-8555-7a79191e984f','Deering Goodtime 5 String',2010,2,529.00,4),(18,19,'44d72ffa-7990-11ec-8555-7a79191e984f','Deering Goodtime Americana Banjo 5 String',2010,2,599.00,2),(19,20,'44dedbf0-7990-11ec-8555-7a79191e984f','Gold Tone CC-50 Cripple Creek ',2010,2,399.99,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-20 18:04:16
