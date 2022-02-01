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
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `manufacturer_name` varchar(255) NOT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'yamaha','Indonesia',NULL),(2,'guilde','United States',NULL),(3,'eastman','Belgium',NULL),(4,'fender','Italy',NULL),(5,'fender','Spain',NULL),(6,'deering','United States',NULL),(7,'gold tone','United States',NULL),(8,'simmons drums','United Kingdom','Beginning with the first commercially available electronic drum sets in the 1980s, Simmons has helped define the look, sound and feel of popular music for nearly four decades, with a full line of electronic kits and drum amplifiers for every budget. '),(9,'rogue ','United States','There\'s something to be said about a person who takes up an interest in playing music. Whether you\'re a budding mandolinist, and professional banjo player or a local guitar hero, taking up an instrument is proof that you have a yearning for artistic expression, and thanks to dedicated musical instrument brands like Rogue, there is no telling what kind of greatness you can aspire to as a performer. Since 1983, Rogue\'s reputation in the music world has been growing steadily amongst beginners and professionals alike. Today, the Rogue name is renowned and respected by musicians around the globe for their excellent range of top quality musical instruments, amplifiers and effects pedals. Rogue\'s experienced team of designers take great pride in appealing to musicians from all walks of life, and throughout their massive catalog, you\'ll find an extensive selection of instruments to choose from. One of Rogue\'s most popular options is the Rogue Rocketeer electric guitar pack. Featuring all the essentials to get a future superstar off on the right foot, this package includes the RR100 double cutaway guitar, a gig bag, strap, cable, picks and a Rogue G10 solid-state guitar amp. Simply put, it\'s an ideal kit for any youngster who\'s interested in taking up the electric guitar. Rogue also manufactures remarkable lap steel guitars, and the Rogue RLS01 lap steel guitar is greatly recommended. Containing a hardwood body and neck with position markers, volume and tone controls, and a single-coil pickups, this phenomenally priced lap steel guitar blends with a wide variety of musical genres, including country, Hawaiian and even the blues. In the market for an effects pedal? The Rogue Blues distortion pedal is more than capable of giving you a warm overdrive distortion, similar to the classic rock tones that emerged from the 60\'s and 70\'s. Compact, durable and easy-to-use, this pedal also lets you overdrive your favorite tube amp, providing you with a rich tone with plenty of sustain. In order to showcase your strongest musical attributes, you need equipment that\'s crafted by experts. For that, you don\'t need to look any further than Rogues family of products, where every instrument, amplifier and effects pedal is constructed with your ambitions in mind. '),(10,'traps','United States',NULL),(11,'hofner','German','Karl Höfner GmbH & Co. KG is a German (originally Austro-Bohemian) manufacturer of musical instruments, with one division that manufactures guitars and basses, and another that manufactures other string instruments, such as violins, violas, cellos, double basses and bows for stringed instruments.');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-01 22:43:59
