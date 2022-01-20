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
-- Table structure for table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instrument` (
  `id` int NOT NULL AUTO_INCREMENT,
  `instrument_name` varchar(255) NOT NULL,
  `manufacturer_id` int NOT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `instrument_FK` (`manufacturer_id`),
  KEY `instrument_FK_1` (`category`),
  CONSTRAINT `instrument_FK` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` VALUES (2,'YAMAHA LL16D ARE',1,'Acoustic Guitar','Yamaha Acoustic Guitar L Series'),(3,'Yamaha GL1 Guitalele Acoustic Guitar',1,'Acoustic Guitar','This is a 433mm scale ukulele style nylon string guitar. Despite its compact body, it is designed as authentic nylon string guitar. Take this genuine guitar to anywhere you go. Specifications : Color/Finish Body Finish Matte Control Interface String Strings Scale 433 mm (17\") Design/Architecture Detail Body Depth 70-70 mm (2 13/16\"-2 13/16\") Finger Board Width (Nut/Body) 48 mm (1 7/8\") Material Top Spruce Back Meranti Side/Rib Meranti Neck Nato Finger Board Sonokeling Bridge Sonokeling'),(4,'Yamaha APX600M Acoustic Electric Guitar',1,'Acoustic Guitar','APX600 series–introducing versions with a matte/doff finish. The APX\'s slim body combines comfort, easy high fret access, and natural acoustic tones, resulting in the APX600M\'s playability that\'s perfect for guitarists on stage.'),(5,'Guild M-120E Acoustic Guitar',2,'Acoustic Guitar','The M-120 E features a solid mahogany top, back and sides that produce warm, balanced sound with excellent projection and distinctive presence. The 24 3/4” scale length and slim satin-finish mahogany neck impart effortless playability and exceptional feel, and the onboard Fishman® Sonitone™ pickup system delivers clear and natural amplified sound. Other features include a rosewood fingerboard and bridge and mother-of pearl rosette and inlays. Available in Natural or Cherry Red.'),(6,'Yamaha Pacifica 112J BL Electric Guitar',1,'Electric Guitar','Lightweight alder-body electric that is great for emerging players. Smooth, C-shaped maple neck and rosewood fingerboard is instantly playable.'),(7,'Yamaha Pacifica 112J YNS Electric Guitar',1,'Electric Guitar','Lightweight alder-body electric that is great for emerging players. Smooth, C-shaped maple neck and rosewood fingerboard is instantly playable. Trio of versatile pickups let you dial in a variety of tones from clean highs to punchy midrange to low-end growl.'),(8,'Yamaha Pacifica 112J RM',1,'Electric Guitar','Lightweight alder-body electric that is great for emerging players. Smooth, C-shaped maple neck and rosewood fingerboard is instantly playable.'),(9,'Yamaha TRBX174 Electric Bass',1,'Electric Bass','Dengan perubahan pada nut mount, konstruksi back cover dan bentuk head TRBX. Sehingga para pemula bisa merasakan high-end image dari TRBX pada seri ini. Tersedia warna Black, Red Metallic, Blue Metallic and Violin Sunburst. Tentunya bass terbaru ini dilengkapi dengan fitur terbaru yang akan menjawab harapan Anda akan sebuah elektrik bass idaman'),(10,'Yamaha BB234 Electric Bass',1,'Electric Bass','The introductory model of the hardest working bass in the business: the Yamaha BB series. Solid Alder Body.'),(11,'Yamaha BB235 Electric Bass',1,'Electric Bass','Solid Alder Body. Custom V3 Pickups (Ceramic)'),(12,'Yamaha MX88 Synthesizer',1,'Stage Piano','The MX88 features a Graded Hammer Standard (GHS) weighted action. GHS weighted action has a heavier response in the low keys and a lighter response in the high keys. This provides realistic acoustic piano touch and response.'),(13,'Yamaha MODX8 Synthesizer',1,'Stage Piano','Expressive 88-key Graded Hammer Standard Action keyboard. Motion Control Synthesis unifies and controls AWM2 and FM-X sound engines'),(14,'Yamaha YC-73 Stage Keyboard',1,'Stage Piano','Dirancang untuk para pemain keyboard panggung, Yamaha YC Series menghadirkan perangkat Organ Virtual Circuitry Modeling (VCM) yang baru dilengkapi dengan drawbar fisik, real-time control yang ekstensif, dan suara Piano Akustik/Elektrik serta FM synth yang autentik. Dengan tiga model yang dapat dipilih, stage keyboard YC hadir untuk memenuhi kebutuhan setiap panggung dan setiap pemain keyboard. '),(15,'Eastman MD315 F Style ',3,'Mandoline','Eastman MD315 Mandoline: Neck Material:Maple , Fingerboard:Ebony , Fingerboard Radius:12\" , Nut:Bone 1 3/32\", Fretwire:23 Jescar FW37053 , Scale Length:13 7/8\" , Body Style:F-Style w/ F-Holes '),(16,'Fender California Coast Venice Soprano Ukulele',4,'Ukulele','Fender California Coast Venice Soprano Ukulele, Inspired by the entertaining sights and unique, carefree spirit of Venice, California, we created a soprano ukulele that captures its vibe—the Venice Ukulele. Thanks to its compact, comfortable body size, the Venice travels easily from the beach to the studio or jam room while retaining the classic, light sound that made the ukulele a \"must-have\" for today\'s players.'),(17,'Fender Fullerton Jazzmaster Uke, Olympic White',5,'Ukulele','Tipping the hat to Fender’s iconic guitar body shapes, the Fullerton Series ukuleles are nothing short of electric. The Fullerton Jazzmaster departs from traditional ukulele construction and aesthetics, while remaining faithful to Fender’s history. The Jazzmaster ukulele’s pickguard, signature finish color options, and 4-in-line headstock can only be categorized as quintessentially Fender. The Fullerton Jazzmaster is the perfect choice for the ukulele player looking to inject the spirit of rock ’n roll into every performance.'),(18,'Deering Goodtime 5-String',6,'Banjo','If you want a top quality reasonably priced banjo, this is the one. It\'s made right here in America at the Deering Banjo factory. The Goodtime openback banjo is ideal for traveling, camping, hiking, or taking to the beach. It provides a vibrant singing banjo tone. The Goodtime banjo is light enough for children to hold and play and is the perfect banjo for a child to start out with. More people succeed in learning to play when they start out on a Goodtime banjo.'),(19,'Deering Goodtime Americana Openback 5 String',6,'Banjo','The Deering Goodtime Americana Banjo is a step up from the original Goodtime banjo. The biggest change is it has a 12\" head rather than the standard 11\", this allows for more resonance in the bass notes and gives it a more defined sound. The Americana is great for playing old time music, as the style is similar to that of banjos from back in that era. It is the perfect banjo for pairing with vocals and other band scenarios as it has a more laid-back tone, that will fit in the mix without overpowering your vocals.'),(20,'Gold Tone CC-50 Cripple Creek Openback 5-String',7,'Banjo','The Cripple Creek CC-50 is an entry-level banjo designed for easy learning, specifically designed to play effortlessly, sound great and be affordable. The openback CC-50 is ideal for \"old time\" or folk-style banjo playing. The hard maple neck and maple rim, guitar-style tuners, geared fifth-string tuning peg, single coordinator rod, straight-line tailpiece, rosewood fingerboard and rolled brass tone ring combine to make a lightweight, all-wood instrument that\'s ideal for the student or for experienced picker in need of a second banjo. Completely set up at the Gold Tone factory in Florida, The CC-50\'s price includes a gig bag for easy transport. Available as left-handed model.');
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;
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