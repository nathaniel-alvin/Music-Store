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
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_order_id` int NOT NULL,
  `item_id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_item_FK` (`customer_order_id`),
  KEY `order_item_FK_1` (`item_id`),
  CONSTRAINT `order_item_FK` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`),
  CONSTRAINT `order_item_FK_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,3,750.00,1),(2,1,5,400.00,2),(3,1,8,230.00,1),(4,1,4,1100.00,1),(5,1,1,1000.00,1),(6,1,5,400.00,1),(7,1,6,400.00,2),(14,1,3,500.00,1),(15,1,5,400.00,2),(16,1,7,400.00,1),(20,8,4,1100.00,1),(21,9,1,1000.00,3),(22,9,6,400.00,1),(23,9,5,400.00,2),(24,9,3,500.00,3),(25,9,4,2200.00,2),(26,9,7,200.00,1),(27,11,2,100.00,2),(28,11,3,250.00,1),(29,11,2,200.00,2),(30,11,3,250.00,1),(31,11,4,1100.00,2),(32,11,2,200.00,2),(33,11,4,1100.00,1),(34,11,5,200.00,1),(35,11,2,100.00,1),(36,11,5,200.00,1),(37,11,7,400.00,2),(38,11,3,500.00,2),(39,11,4,1100.00,1),(40,11,5,200.00,1),(41,11,3,500.00,2),(42,11,5,200.00,1),(43,11,6,400.00,2),(44,11,3,500.00,2),(45,11,5,200.00,1),(46,11,6,200.00,1),(47,11,11,1200.00,1),(48,11,4,1100.00,1),(49,11,1,2000.00,2),(50,11,5,400.00,2),(51,11,7,400.00,2),(52,11,10,300.00,1),(53,11,2,200.00,2),(54,11,5,400.00,2),(55,11,6,600.00,3),(56,11,4,1100.00,1),(57,21,4,2200.00,2),(58,21,6,200.00,1),(59,21,1,2000.00,2),(60,22,4,5500.00,5),(61,22,6,600.00,3),(62,22,5,400.00,2),(63,22,8,230.00,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-20 18:04:17
