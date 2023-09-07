-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: apjobdb
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
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate` (
  `id` int NOT NULL,
  `school_name` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `location_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`location_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `user_id_candidate` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (11,'Đại Học Mở HCM','2000-02-25',1);
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_recruitment`
--

DROP TABLE IF EXISTS `candidate_recruitment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_recruitment` (
  `candidate_id` int NOT NULL,
  `recruitment_id` int NOT NULL,
  `employer_id` int NOT NULL,
  `link_cv` varchar(200) NOT NULL,
  `text_mail` varchar(200) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`candidate_id`,`recruitment_id`,`employer_id`),
  KEY `recruitment_id_idx` (`recruitment_id`),
  KEY `employerID_recruitment_candidate_idx` (`employer_id`),
  CONSTRAINT `cadidateID_recruitment ` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `employerID_recruitment_candidate` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  CONSTRAINT `recruitment_id` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment_news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_recruitment`
--

LOCK TABLES `candidate_recruitment` WRITE;
/*!40000 ALTER TABLE `candidate_recruitment` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate_recruitment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_tag`
--

DROP TABLE IF EXISTS `candidate_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_tag` (
  `candidate_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `tag_name` varchar(100) NOT NULL,
  PRIMARY KEY (`candidate_id`,`tag_id`),
  KEY `candidate_tagID_idx` (`tag_id`),
  CONSTRAINT `candidate_tagID` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `candidateID_tag` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_tag`
--

LOCK TABLES `candidate_tag` WRITE;
/*!40000 ALTER TABLE `candidate_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(100) NOT NULL,
  `tax_code` int NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tax_code_UNIQUE` (`tax_code`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Hải Âu Company','Nguyễn Kiệm',345879126,'haiaucompany@haiau.com','02825612548','Công ty cung cấp giấy ','https://res.cloudinary.com/de3ifjaz1/image/upload/v1694097606/jbjlbmuai52ly5qoarha.jpg'),(2,'An Nhiên Solution Tech','Võ Văn Tần',321584784,'annhientech@annhien.com','02896325874','Công ty quy mô 200+ nhân viên. Cung cấp giải pháp công nghệ','https://res.cloudinary.com/de3ifjaz1/image/upload/v1694100425/k5lddmipgbyraj0xw16n.jpg');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_tag`
--

DROP TABLE IF EXISTS `company_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_tag` (
  `company_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `tag_name` varchar(100) NOT NULL,
  PRIMARY KEY (`company_id`,`tag_id`),
  KEY `company_tagID_idx` (`tag_id`),
  CONSTRAINT `company_tagID` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `companyID_tag` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_tag`
--

LOCK TABLES `company_tag` WRITE;
/*!40000 ALTER TABLE `company_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv`
--

DROP TABLE IF EXISTS `cv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link_cv` varchar(200) DEFAULT NULL,
  `name_cv` varchar(45) NOT NULL,
  `candidate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `candidate_id_cv_idx` (`candidate_id`),
  CONSTRAINT `candidate_id_cv` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv`
--

LOCK TABLES `cv` WRITE;
/*!40000 ALTER TABLE `cv` DISABLE KEYS */;
/*!40000 ALTER TABLE `cv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` int NOT NULL,
  `company_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id_idx` (`company_id`),
  CONSTRAINT `company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (3,1),(9,2);
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Hồ Chí Minh'),(2,'Hà Nội'),(3,'Đà Nẵng'),(4,'Đồng Nai'),(5,'Long An'),(6,'Bình Dương'),(7,'Lâm Đồng');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `candidate_id` int NOT NULL,
  `company_id` int NOT NULL,
  `comment` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id_idx` (`company_id`),
  KEY `candidate_cv_idx` (`candidate_id`),
  CONSTRAINT `candidate_cv` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `company_id_rating` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruitment_news`
--

DROP TABLE IF EXISTS `recruitment_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `job_vacancy` varchar(45) NOT NULL,
  `salary` varchar(45) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `deadline` datetime NOT NULL,
  `created_date` datetime NOT NULL,
  `employer_id` int NOT NULL,
  `location_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employer_id_idx` (`employer_id`),
  KEY `location_id_recruitment_idx` (`location_id`),
  CONSTRAINT `employer_id` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  CONSTRAINT `location_id_recruitment` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruitment_news`
--

LOCK TABLES `recruitment_news` WRITE;
/*!40000 ALTER TABLE `recruitment_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `recruitment_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'IT - Phần mềm'),(2,'Developer'),(3,'DepOps'),(4,'Software Engineer '),(5,'AI'),(6,'Phần cứng'),(7,'IT - Security '),(8,'Bảo mật'),(9,'Cloud'),(10,'RPA'),(11,'Kế toán'),(12,'Kiểm toán'),(13,'Part - time '),(14,'Full - time'),(15,'Remote'),(16,'Freelance '),(17,'Design'),(18,'Back - End '),(19,'Front - End '),(20,'UX/UI'),(21,'Tester'),(22,'Automation test'),(23,'Manual test'),(24,'sales'),(25,'Nhân viên kinh doanh'),(26,'C#'),(27,'C++'),(28,'Python'),(29,'Java'),(30,'Javascript '),(31,'PHP'),(32,'HTML/CSS'),(33,'VB'),(34,'Ruby'),(35,'Android'),(36,'Flutter '),(37,'IOS'),(38,'SpringMVC'),(39,'SpringBoot'),(40,'ReactJS'),(41,'NodeJS'),(42,'Tư vấn'),(43,'Consultant '),(44,'Data Engineer '),(45,'Senior'),(46,'Middle'),(47,'Junior'),(48,'Master'),(49,'intern'),(50,'Fresher'),(51,'Marketing ');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `user_role` varchar(15) NOT NULL,
  `active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Thanh Hoang','ntthoang@gmail.com','$2a$10$X.KW3LkZP1xueOetbq1PLuOS5xmdi6Em5DtDIMJ5DwWWnE.gXgyBq','0789706126','https://res.cloudinary.com/de3ifjaz1/image/upload/v1694081389/lpml3iswg7ai45toisf7.jpg','ADMIN',_binary ''),(3,'Tuyển dụng nhân tài Hải Âu','tuyendung@haiau.com','$2a$10$fJi0KtEgrzNB9Dv8XVIVU.k/GYh8hCOx.iGLd1zrLAgj0NtWbI44i','0794581256','https://res.cloudinary.com/de3ifjaz1/image/upload/v1694097605/n7ukac3if0qlgipcdsqm.jpg','EMPLOYER',_binary '\0'),(9,'Tuyển dụng An Nhiên','tuyendungannhien@annhien.com','$2a$10$G3GjJiSG.yybzYkLCeUqq.RcrS/i9hxQRP0xFLQkoC/5zbwbjwHu2','077845962','https://res.cloudinary.com/de3ifjaz1/image/upload/v1694100422/vc3zedlwvctcyqcpqgu8.jpg','EMPLOYER',_binary ''),(11,'Huệ Chi','huechi@gmail.com','$2a$10$VlIelvgSDEdTmG8oh6znI.zdmLsDruJpcE0MWytNdfSSGvl9egWoy','0778456278','https://res.cloudinary.com/de3ifjaz1/image/upload/v1694107072/i7owhuwhcx0xv8qtehs0.jpg','CANDIDATE',_binary '');
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

-- Dump completed on 2023-09-08  2:07:55
