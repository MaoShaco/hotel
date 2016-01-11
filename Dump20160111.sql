-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(63) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (3,'ROLE_ADMIN'),(2,'ROLE_HOTEL_MANAGER'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `state` tinyint(1) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_fk0` (`profile_id`),
  CONSTRAINT `booking_fk0` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (4,'2016-11-27','2016-11-29',1,3),(15,'2016-01-06','2016-01-29',1,15),(16,'2016-01-08','2016-01-28',1,15),(18,'2016-01-08','2016-01-15',0,15),(19,'2016-02-05','2016-02-20',0,15),(20,'2016-01-29','2016-01-30',0,4);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_rooms`
--

DROP TABLE IF EXISTS `booking_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_rooms` (
  `rooms_id` int(11) NOT NULL,
  `bookings_id` int(11) NOT NULL,
  KEY `booking_rooms_fk0` (`rooms_id`),
  KEY `booking_rooms_fk1` (`bookings_id`),
  CONSTRAINT `booking_rooms_fk0` FOREIGN KEY (`rooms_id`) REFERENCES `room` (`id`),
  CONSTRAINT `booking_rooms_fk1` FOREIGN KEY (`bookings_id`) REFERENCES `booking` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_rooms`
--

LOCK TABLES `booking_rooms` WRITE;
/*!40000 ALTER TABLE `booking_rooms` DISABLE KEYS */;
INSERT INTO `booking_rooms` VALUES (7,4),(9,4),(9,15),(7,16),(3,16),(2,18),(9,19),(3,20);
/*!40000 ALTER TABLE `booking_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(127) NOT NULL,
  `address` varchar(255) NOT NULL,
  `rating` int(11) NOT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hotel_fk0` (`manager_id`),
  CONSTRAINT `hotel_fk0` FOREIGN KEY (`manager_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Fortune','Zaryca 4, Grodno',5,15);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `profile_fk0` (`authority_id`),
  CONSTRAINT `profile_fk0` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'Pedro Carvalho','pedro','$2a$10$Qy5aM56tG1tGy6fvXMD0H./so5nh/sA5n21pj3aYLGwLP6en7LlSm','pedro@email.com',3),(3,'Tiago Pereira','tiago','$2a$10$Qy5aM56tG1tGy6fvXMD0H./so5nh/sA5n21pj3aYLGwLP6en7LlSm','tiago@email.com',1),(4,'Luis','luis','$2a$10$Qy5aM56tG1tGy6fvXMD0H./so5nh/sA5n21pj3aYLGwLP6en7LlSm','luis@gmail.com',1),(6,'Bradley','bread','$2a$10$Qy5aM56tG1tGy6fvXMD0H./so5nh/sA5n21pj3aYLGwLP6en7LlSm','bread@email.com',3),(15,'Admin Andrew','admin','$2a$10$VNNK8UNYE.DdBLJiLcoaJ.pYU11wkK1UyLIdeN0k3i7HdxHrRRkmm','admin@gmail.com',2),(16,'Cooler','Cooler','$2a$10$Qy5aM56tG1tGy6fvXMD0H./so5nh/sA5n21pj3aYLGwLP6en7LlSm','Cooler@mail.ru',1),(17,'Andrew','andrew','$2a$10$Qy5aM56tG1tGy6fvXMD0H./so5nh/sA5n21pj3aYLGwLP6en7LlSm','maoshac@gmail.com',1);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `floor` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_fk0` (`type_id`),
  KEY `room_fk1` (`hotel_id`),
  CONSTRAINT `room_fk0` FOREIGN KEY (`type_id`) REFERENCES `room_type` (`id`),
  CONSTRAINT `room_fk1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (2,1,102,2,1,75),(3,1,103,3,1,100),(6,2,201,2,1,75),(7,2,202,3,1,100),(8,2,203,4,1,200),(9,2,204,1,1,50),(10,2,205,2,1,75),(12,1,110,2,1,100),(13,2,220,3,1,200);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_days_reserved`
--

DROP TABLE IF EXISTS `room_days_reserved`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_days_reserved` (
  `room_id` int(11) DEFAULT NULL,
  `days_reserved_key` date DEFAULT NULL,
  `days_reserved` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`days_reserved`),
  KEY `room_days_reserved_room_id_fk` (`room_id`),
  CONSTRAINT `room_days_reserved_room_id_fk` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_days_reserved`
--

LOCK TABLES `room_days_reserved` WRITE;
/*!40000 ALTER TABLE `room_days_reserved` DISABLE KEYS */;
INSERT INTO `room_days_reserved` VALUES (9,'2016-01-10',120),(9,'2016-01-15',121),(9,'2016-01-06',122),(9,'2016-01-11',123),(9,'2016-01-16',124),(9,'2016-01-21',125),(9,'2016-01-26',126),(9,'2016-01-17',127),(9,'2016-01-22',128),(9,'2016-01-27',129),(9,'2016-01-07',130),(9,'2016-01-12',131),(9,'2016-01-23',132),(9,'2016-01-28',133),(9,'2016-01-08',134),(9,'2016-01-13',135),(9,'2016-01-18',136),(9,'2016-01-09',137),(9,'2016-01-14',138),(9,'2016-01-19',139),(9,'2016-01-24',140),(9,'2016-01-29',141),(9,'2016-01-20',142),(9,'2016-01-25',143),(3,'2016-01-10',144),(3,'2016-01-15',145),(3,'2016-01-11',146),(3,'2016-01-16',147),(3,'2016-01-21',148),(3,'2016-01-26',149),(3,'2016-01-17',150),(3,'2016-01-22',151),(3,'2016-01-27',152),(3,'2016-01-12',153),(3,'2016-01-23',154),(3,'2016-01-28',155),(3,'2016-01-08',156),(3,'2016-01-13',157),(3,'2016-01-18',158),(3,'2016-01-09',159),(3,'2016-01-14',160),(3,'2016-01-19',161),(3,'2016-01-24',162),(3,'2016-01-20',163),(3,'2016-01-25',164),(7,'2016-01-10',165),(7,'2016-01-15',166),(7,'2016-01-11',167),(7,'2016-01-16',168),(7,'2016-01-21',169),(7,'2016-01-26',170),(7,'2016-01-17',171),(7,'2016-01-22',172),(7,'2016-01-27',173),(7,'2016-01-12',174),(7,'2016-01-23',175),(7,'2016-01-28',176),(7,'2016-01-08',177),(7,'2016-01-13',178),(7,'2016-01-18',179),(7,'2016-01-09',180),(7,'2016-01-14',181),(7,'2016-01-19',182),(7,'2016-01-24',183),(7,'2016-01-20',184),(7,'2016-01-25',185),(8,'2016-01-23',186),(8,'2016-01-28',187),(8,'2016-01-21',188),(8,'2016-01-26',189),(8,'2016-01-24',190),(8,'2016-01-29',191),(8,'2016-01-25',192),(8,'2016-01-30',193),(8,'2016-01-22',194),(8,'2016-01-27',195),(2,'2016-01-08',196),(2,'2016-01-13',197),(2,'2016-01-10',198),(2,'2016-01-15',199),(2,'2016-01-11',200),(2,'2016-01-09',201),(2,'2016-01-14',202),(2,'2016-01-12',203),(9,'2016-02-05',204),(9,'2016-02-10',205),(9,'2016-02-15',206),(9,'2016-02-20',207),(9,'2016-02-08',208),(9,'2016-02-13',209),(9,'2016-02-18',210),(9,'2016-02-11',211),(9,'2016-02-16',212),(9,'2016-02-06',213),(9,'2016-02-07',214),(9,'2016-02-12',215),(9,'2016-02-17',216),(9,'2016-02-14',217),(9,'2016-02-19',218),(9,'2016-02-09',219),(3,'2016-01-29',220),(3,'2016-01-30',221);
/*!40000 ALTER TABLE `room_days_reserved` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `occupancy` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES (1,'Single',1),(2,'Double',2),(3,'Studio',2),(4,'Presidential Suite',3);
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-11 14:33:12
