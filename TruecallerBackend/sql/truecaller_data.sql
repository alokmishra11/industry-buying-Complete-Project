-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: truecaller
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'6525665165',0,1),(2,'3456578390',0,2),(3,'9846565165',0,3),(4,'7864554225',0,4),(5,'4544564255',0,5),(6,'8746545656',0,6),(7,'9846511566',0,7),(8,'2222222222',0,8),(9,'9846516556',0,NULL),(10,'9816165155',0,NULL),(11,'9865165165',0,NULL),(12,'9846511566',0,NULL);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Aman','aman@gmail.com','$2a$10$GD9N12O0HKdJNST3s/Xn9.4Am2BOaGAFn3PI4iiJtBiBqUQ93OK0a'),(2,'Alok','m@gmail.com','$2a$10$GD9N12O0HKdJNST3s/Xn9.4Am2BOaGAFn3PI4iiJtBiBqUQ93OK0a'),(3,'Azad','az@gmail.com','$2a$10$GD9N12O0HKdJNST3s/Xn9.4Am2BOaGAFn3PI4iiJtBiBqUQ93OK0a'),(4,'Mayank','m@g.com','$2a$10$GD9N12O0HKdJNST3s/Xn9.4Am2BOaGAFn3PI4iiJtBiBqUQ93OK0a'),(5,'Ashish',NULL,'$2a$10$lMnBufrcbUi5T2/5a9LktudgHIXlHT2I6nmfmQvQdWbCKw.qoWtkC'),(6,'Gaurav',NULL,'$2a$10$305vUUuaXkugCFbEX6rkuupSwR7xfb9kz7NS4XcD09XVMXJ1vmzvm'),(7,'Vibhav',NULL,'$2a$10$AwPiPv8Ejr5zrX2Xhv65ceLhCgLMF/RN.c8p64XoOg16YH0BI3H2m'),(8,'Nitin',NULL,'$2a$10$dWXnfqOjzepr5hVJcf1Lp.KaiF.vHq9bTfRTfBb1LnOWlE1m8qa.2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usercontact`
--

LOCK TABLES `usercontact` WRITE;
/*!40000 ALTER TABLE `usercontact` DISABLE KEYS */;
INSERT INTO `usercontact` VALUES (1,1,3,'janardan'),(2,1,4,'Office'),(3,1,5,'Work'),(5,2,5,'Brother'),(6,2,1,'Sister'),(7,3,2,'Mom'),(8,3,6,'Dad'),(9,4,1,'Brother'),(10,5,7,'Aunt'),(11,5,9,'Taxi'),(12,7,1,'Driver'),(13,4,3,'Uncle');
/*!40000 ALTER TABLE `usercontact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-18 19:24:26