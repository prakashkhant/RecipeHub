-- MySQL dump 10.13  Distrib 5.7.44, for Win64 (x86_64)
--
-- Host: localhost    Database: recipehub
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Table structure for table `activity_log`
--

DROP TABLE IF EXISTS `activity_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `activity` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `activity_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_log`
--

LOCK TABLES `activity_log` WRITE;
/*!40000 ALTER TABLE `activity_log` DISABLE KEYS */;
INSERT INTO `activity_log` VALUES (1,1,'Deleted user: john','2025-12-07 10:06:00'),(2,1,'User logged in','2025-12-07 10:26:22'),(3,1,'Changed role of: Maria Lopez to ADMIN','2025-12-07 10:26:41'),(4,1,'Changed role of: Maria Lopez to USER','2025-12-07 10:26:56'),(5,1,'User logged in','2025-12-07 10:28:51'),(6,1,'User logged in','2025-12-07 10:30:45'),(7,1,'User logged out','2025-12-07 10:30:53'),(8,1,'User logged in','2025-12-07 10:30:59'),(9,1,'User logged out','2025-12-07 10:31:11'),(10,1,'User logged in','2025-12-07 10:31:19'),(11,2,'User logged in','2025-12-07 10:36:53'),(12,2,'Liked recipe: Fruit Salad','2025-12-07 10:36:57'),(13,2,'Liked recipe: Aloo Paratha','2025-12-07 10:37:03'),(14,2,'User logged out','2025-12-07 10:37:06'),(15,5,'User logged in','2025-12-07 10:37:49'),(16,5,'Liked recipe: Veg Pulao','2025-12-07 10:41:19'),(17,1,'User logged in','2025-12-07 10:43:33'),(18,1,'Liked recipe: Cheese Sandwich','2025-12-07 10:43:47'),(19,1,'Liked recipe: Cheese Sandwich','2025-12-07 10:43:48'),(20,1,'Liked recipe: Cheese Sandwich','2025-12-07 10:43:49'),(21,1,'Liked recipe: Cheese Sandwich','2025-12-07 10:44:01'),(22,1,'Liked recipe: Cheese Sandwich','2025-12-07 10:44:02'),(23,1,'User logged in','2025-12-07 10:44:37'),(24,1,'Liked recipe: Fruit Salad','2025-12-07 10:44:40'),(25,1,'Unliked recipe: Fruit Salad','2025-12-07 10:44:43'),(26,2,'User logged in','2025-12-07 10:46:26'),(27,2,'Unliked recipe: Cheese Sandwich','2025-12-07 10:46:29'),(28,2,'Liked recipe: Cheese Sandwich','2025-12-07 10:46:32'),(29,2,'User logged out','2025-12-07 10:46:35'),(30,5,'User logged in','2025-12-07 10:46:42'),(31,2,'User logged in','2025-12-07 10:51:38'),(32,2,'User logged out','2025-12-07 10:51:50'),(33,1,'User logged in','2025-12-07 10:51:55'),(34,1,'User logged in','2025-12-07 11:02:20'),(35,1,'User logged in','2025-12-07 22:46:18'),(36,1,'User logged out','2025-12-07 22:46:47'),(37,1,'User logged in','2025-12-08 07:34:14'),(38,1,'User logged out','2025-12-08 07:36:05'),(39,2,'User logged in','2025-12-08 07:36:15'),(40,1,'User logged in','2025-12-08 23:42:41'),(41,1,'Unliked recipe: Fruit Salad','2025-12-08 23:42:44'),(42,1,'User logged out','2025-12-08 23:43:02'),(43,6,'Registered new account','2025-12-08 23:56:24'),(44,1,'User logged in','2025-12-09 00:01:07'),(45,1,'User logged out','2025-12-09 00:02:35'),(46,6,'User logged in','2025-12-09 00:02:51'),(47,2,'User logged in','2025-12-09 00:08:10'),(48,2,'User logged out','2025-12-09 00:08:36'),(49,1,'User logged in','2025-12-09 00:08:47'),(50,1,'User logged out','2025-12-09 00:09:01'),(51,2,'User logged in','2025-12-09 00:10:07'),(52,1,'User logged in','2025-12-09 00:15:53'),(53,1,'User logged in','2025-12-09 00:26:42'),(54,1,'User logged in','2025-12-09 00:29:13'),(55,1,'User logged in','2025-12-09 00:56:13'),(56,1,'Added a recipe: dAFAF','2025-12-09 00:57:11'),(57,1,'Deleted recipe: dAFAF','2025-12-09 00:57:55'),(58,1,'User logged in','2025-12-09 01:21:25'),(59,1,'User logged out','2025-12-09 01:21:47'),(60,1,'User logged in','2025-12-09 01:35:48'),(61,1,'User logged in','2025-12-09 01:40:14'),(62,1,'User logged out','2025-12-09 01:40:44'),(63,2,'User logged in','2025-12-09 01:42:51'),(64,2,'Liked recipe: Fruit Salad','2025-12-09 01:42:54'),(65,2,'User logged out','2025-12-09 01:43:09'),(66,2,'User logged in','2025-12-09 01:43:13'),(67,2,'Unliked recipe: Fruit Salad','2025-12-09 01:43:18'),(68,2,'User logged in','2025-12-09 04:17:53'),(69,2,'Liked recipe: Fruit Salad','2025-12-09 04:17:57'),(70,2,'Unliked recipe: Fruit Salad','2025-12-09 04:17:58'),(71,2,'Liked recipe: Kesari Sheera','2025-12-09 04:18:00'),(72,2,'User logged in','2025-12-09 04:24:27'),(73,2,'Liked recipe: Fruit Salad','2025-12-09 04:24:30'),(74,2,'Added a recipe: scsc','2025-12-09 04:24:57'),(75,2,'Updated recipe: scsc','2025-12-09 04:25:12'),(76,2,'Updated recipe: scsc','2025-12-09 04:25:48'),(77,2,'User logged out','2025-12-09 04:25:57'),(78,2,'User logged in','2025-12-09 04:26:07'),(79,2,'Added a recipe: cnnc','2025-12-09 04:26:38'),(80,2,'User logged in','2025-12-09 04:33:17'),(81,2,'Liked recipe: cnnc','2025-12-09 04:33:37'),(82,2,'Liked recipe: scsc','2025-12-09 04:33:38'),(83,2,'User logged in','2025-12-09 04:42:03'),(84,2,'Updated recipe: scsc','2025-12-09 04:42:34'),(85,2,'Updated recipe: cnnc','2025-12-09 04:42:43'),(86,2,'Commented on recipe: Aloo Paratha','2025-12-09 04:51:24'),(87,2,'User logged in','2025-12-09 04:56:24'),(88,2,'Updated recipe: scsc','2025-12-09 04:56:42'),(89,2,'Updated recipe: cnnc','2025-12-09 04:57:03'),(90,2,'Added a recipe: test1','2025-12-09 04:57:46'),(91,2,'User logged out','2025-12-09 04:57:52'),(92,5,'User logged in','2025-12-09 04:58:05'),(93,5,'Liked recipe: test1','2025-12-09 04:58:28'),(94,5,'Liked recipe: cnnc','2025-12-09 04:58:30'),(95,2,'User logged in','2025-12-09 05:05:48'),(96,2,'Updated recipe: scsc','2025-12-09 05:06:17'),(97,2,'Updated recipe: cnnc','2025-12-09 05:06:27'),(98,2,'Updated recipe: test1','2025-12-09 05:06:37'),(99,2,'User logged out','2025-12-09 05:07:02'),(100,2,'User logged in','2025-12-09 06:34:39'),(101,2,'Added a recipe: test2','2025-12-09 06:35:19'),(102,2,'Liked recipe: test2','2025-12-09 06:35:37'),(103,2,'User logged out','2025-12-09 06:35:41'),(104,2,'User logged in','2025-12-09 07:07:42'),(105,2,'User logged in','2025-12-09 07:14:17'),(106,7,'Registered new account','2025-12-09 07:17:00'),(107,7,'User logged in','2025-12-09 07:17:06'),(108,7,'Liked recipe: test1','2025-12-09 07:17:09'),(109,7,'User logged out','2025-12-09 07:17:15'),(110,7,'User logged in','2025-12-09 08:22:15'),(111,7,'Liked recipe: cnnc','2025-12-09 08:22:18'),(112,6,'User logged in','2025-12-09 08:33:59'),(113,6,'Liked recipe: cnnc','2025-12-09 08:34:08'),(114,7,'User logged in','2025-12-09 10:38:21'),(115,7,'Liked recipe: test2','2025-12-09 10:38:26'),(116,7,'Added a recipe: ijjkl','2025-12-09 10:39:07'),(117,7,'User logged out','2025-12-09 10:39:59'),(118,5,'User logged in','2025-12-09 10:40:08'),(119,6,'User logged in','2025-12-09 21:51:01'),(120,6,'Liked recipe: test1','2025-12-09 21:51:05'),(121,6,'Liked recipe: Cheese Sandwich','2025-12-09 21:51:07'),(122,6,'Added a recipe: test2','2025-12-09 21:51:39'),(123,6,'User logged out','2025-12-09 21:52:04'),(124,5,'User logged in','2025-12-09 21:52:12'),(125,5,'User logged in','2025-12-09 21:59:19'),(126,5,'User logged in','2025-12-09 22:06:06'),(127,5,'User logged in','2025-12-09 22:29:57'),(128,6,'User logged in','2025-12-09 23:21:11');
/*!40000 ALTER TABLE `activity_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `text` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `recipe_id` (`recipe_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,2,'Great recipe!','2025-11-22 04:05:43'),(2,2,3,'Very tasty.','2025-11-22 04:05:43'),(3,3,4,'Nice instructions.','2025-11-22 04:05:43'),(4,4,5,'Loved it.','2025-11-22 04:05:43'),(5,5,3,'Easy to follow.','2025-11-22 04:05:43'),(6,6,2,'Perfect taste!','2025-11-22 04:05:43'),(7,7,4,'Good one.','2025-11-22 04:05:43'),(8,8,1,'Amazing cake!','2025-11-22 04:05:43'),(13,25,3,'Very tasty paratha!','2025-12-07 12:37:57'),(14,26,4,'Loved the cheese!','2025-12-07 12:37:57'),(15,27,5,'Super healthy!','2025-12-07 12:37:57'),(16,28,1,'Nice aroma!','2025-12-07 12:37:57'),(17,29,2,'So yummy!','2025-12-07 12:37:57'),(18,30,4,'Crispy and spicy!','2025-12-07 12:37:57'),(19,31,5,'Refreshing drink!','2025-12-07 12:37:57'),(20,32,1,'Sweet and tasty.','2025-12-07 12:37:57'),(21,33,2,'Perfect chapati.','2025-12-07 12:37:57'),(22,34,3,'Soft and fluffy!','2025-12-07 12:37:57'),(23,35,5,'Nice breakfast!','2025-12-07 12:37:57'),(24,36,1,'Delicious curry!','2025-12-07 12:37:57'),(25,37,2,'Crispy and cheesy!','2025-12-07 12:37:57'),(26,38,3,'Very refreshing!','2025-12-07 12:37:57'),(27,39,4,'Crispy delight!','2025-12-07 12:37:57'),(28,25,2,'Good Recipes','2025-12-09 04:51:24');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredients` (
  `ingredient_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `unit` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'Salt','g'),(2,'Sugar','g'),(3,'Flour','g'),(4,'Milk','ml'),(5,'Butter','g'),(6,'Tomato','pcs'),(7,'Onion','pcs'),(8,'Oil','ml'),(9,'Rice','g'),(10,'Cheese','g');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `liked_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`recipe_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,4,'2025-11-22 04:05:53'),(2,5,'2025-11-22 04:05:53'),(3,1,'2025-12-06 23:48:22'),(3,4,'2025-11-22 04:05:53'),(3,5,'2025-12-06 23:00:49'),(4,1,'2025-12-07 00:24:33'),(4,2,'2025-11-22 04:05:53'),(4,5,'2025-12-06 23:00:52'),(5,5,'2025-11-22 04:05:53'),(6,3,'2025-11-22 04:05:53'),(7,1,'2025-12-06 11:23:58'),(7,2,'2025-11-22 04:05:53'),(8,2,'2025-12-06 11:23:59'),(8,5,'2025-12-06 10:50:12'),(9,2,'2025-11-22 04:05:53'),(10,3,'2025-11-22 04:05:53'),(25,1,'2025-12-07 07:59:30'),(26,1,'2025-12-07 10:44:02'),(26,2,'2025-12-07 10:46:32'),(26,3,'2025-12-07 12:37:46'),(26,6,'2025-12-09 21:51:07'),(27,2,'2025-12-09 04:24:30'),(27,4,'2025-12-07 12:37:46'),(29,1,'2025-12-07 12:37:46'),(30,2,'2025-12-07 10:06:52'),(30,3,'2025-12-07 12:37:46'),(31,1,'2025-12-07 07:59:28'),(31,4,'2025-12-07 12:37:46'),(32,5,'2025-12-07 12:37:46'),(33,1,'2025-12-07 12:37:46'),(35,4,'2025-12-07 12:37:46'),(37,1,'2025-12-07 12:37:46'),(39,3,'2025-12-07 12:37:46'),(42,5,'2025-12-09 04:58:30'),(42,7,'2025-12-09 08:22:18'),(43,5,'2025-12-09 04:58:28'),(43,6,'2025-12-09 21:51:05'),(43,7,'2025-12-09 07:17:09'),(44,2,'2025-12-09 06:35:37'),(44,7,'2025-12-09 10:38:26');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rating_id`),
  KEY `recipe_id` (`recipe_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE,
  CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (1,1,3,5,'2025-11-22 04:05:48'),(2,2,4,4,'2025-11-22 04:05:48'),(3,3,5,4,'2025-11-22 04:05:48'),(9,9,1,4,'2025-11-22 04:05:48'),(10,10,2,5,'2025-11-22 04:05:48');
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredients`
--

DROP TABLE IF EXISTS `recipe_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe_ingredients` (
  `recipe_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `quantity` decimal(10,2) NOT NULL,
  `unit_override` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`,`ingredient_id`),
  KEY `ingredient_id` (`ingredient_id`),
  CONSTRAINT `recipe_ingredients_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE,
  CONSTRAINT `recipe_ingredients_ibfk_2` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`ingredient_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredients`
--

LOCK TABLES `recipe_ingredients` WRITE;
/*!40000 ALTER TABLE `recipe_ingredients` DISABLE KEYS */;
INSERT INTO `recipe_ingredients` VALUES (1,9,200.00,NULL),(2,10,100.00,NULL),(3,6,2.00,'pcs'),(4,5,50.00,NULL),(5,6,3.00,'pcs'),(6,7,1.00,'pcs'),(7,8,20.00,'ml'),(8,2,150.00,NULL),(9,6,2.00,'pcs'),(10,4,200.00,NULL);
/*!40000 ALTER TABLE `recipe_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipes` (
  `recipe_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` text,
  `steps` text,
  `category` varchar(50) DEFAULT NULL,
  `difficulty` enum('EASY','MEDIUM','HARD') DEFAULT 'EASY',
  `image_url` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`recipe_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,1,'Veg Fried Rice  indian','Simple homemade fried rice.','1. Heat oil in pan.\r\n2. Add vegetables.\r\n3. Add cooked rice.\r\n4. Mix & fry for 5 minutes.\r\n5. Serve hot.','Veg','MEDIUM','img1.jpg','','2025-11-22 04:05:20'),(2,2,'Pasta Alfredo','Creamy white sauce pasta.','1. Boil pasta.\n2. Cook white sauce.\n3. Mix pasta and sauce.\n4. Add cheese.\n5. Serve warm.','Italian','MEDIUM','img2.jpg',NULL,'2025-11-22 04:05:20'),(3,3,'Masala Dosa','Crispy dosa with filling.','1. Prepare dosa batter.\n2. Heat tawa.\n3. Spread batter.\n4. Add masala filling.\n5. Fold and serve.','Indian','MEDIUM','img3.jpg',NULL,'2025-11-22 04:05:20'),(4,4,'Paneer Tikka','Smoky paneer starter.','1. Marinate paneer.\n2. Grill or cook in pan.\n3. Add spices.\n4. Serve with chutney.','Indian','HARD','img4.jpg',NULL,'2025-11-22 04:05:20'),(5,5,'Tomato Soup','Rich tomato soup.','1. Boil tomatoes.\n2. Blend into puree.\n3. Cook with spices.\n4. Add cream.\n5. Serve hot.','Continental','EASY','img5.jpg',NULL,'2025-11-22 04:05:20'),(6,3,'Veg Burger','Healthy homemade burger.','1. Make patty.\n2. Grill patty.\n3. Toast bun.\n4. Assemble burger with veggies.\n5. Serve.','American','MEDIUM','img6.jpg',NULL,'2025-11-22 04:05:20'),(7,3,'Hakka Noodles','Chinese-style noodles.','1. Boil noodles.\n2. Stir fry veggies.\n3. Add sauces.\n4. Toss noodles.\n5. Serve hot.','Chinese','MEDIUM','img7.jpg',NULL,'2025-11-22 04:05:20'),(8,5,'Chocolate Cake ','Soft fluffy cake.','1. Prepare cake batter.\r\n2. Bake in oven.\r\n3. Cool down.\r\n4. Add frosting.\r\n5. Slice & enjoy.','Veg','HARD','img8.jpg','','2025-11-22 04:05:20'),(9,4,'Green Salad','Fresh mixed salad.','1. Chop vegetables.\n2. Prepare dressing.\n3. Mix everything.\n4. Chill before serving.','International','EASY','img9.jpg',NULL,'2025-11-22 04:05:20'),(10,5,'Idli Sambhar indian','South Indian breakfast.','1. Make idli batter.\r\n2. Steam batter.\r\n3. Cook sambhar.\r\n4. Serve idli with sambhar & chutney.','Veg','EASY','img10.jpg','','2025-11-22 04:05:20'),(16,3,'Masala Maggi','A quick spicy Indian noodle snack made with vegetables.','1. Boil Maggi noodles.\n2. Fry onions, capsicum.\n3. Add masala and noodles.\n4. Cook for 2 minutes.','Veg','EASY','maggi.jpg',NULL,'2025-12-04 14:34:10'),(17,2,'Gujarati Khichdi','Light and easy-to-digest rice + dal meal.','1. Wash rice and dal.\n2. Pressure cook with spices.\n3. Serve with ghee.','Veg','EASY','khichdi.jpg',NULL,'2025-12-04 14:34:10'),(18,2,'Paneer Butter Masala','Creamy soft paneer curry loved by everyone!','1. Fry paneer.\n2. Cook gravy from tomato & cashew.\n3. Mix paneer & cream.','Veg','MEDIUM','paneer.jpg',NULL,'2025-12-04 14:34:10'),(25,1,'Aloo Paratha','Stuffed potato paratha.','1. Make dough.\n2. Prepare aloo stuffing.\n3. Roll and cook.','Indian','MEDIUM','aloo_paratha.jpg',NULL,'2025-12-07 12:37:23'),(26,2,'Cheese Sandwich','Quick cheesy snack.','1. Add cheese.\n2. Grill.\n3. Serve hot.','Fast Food','EASY','cheese_sandwich.jpg',NULL,'2025-12-07 12:37:23'),(27,3,'Fruit Salad','Healthy fruit mix.','1. Cut fruits.\n2. Mix.\n3. Chill.','Veg','EASY','fruit_salad.jpg',NULL,'2025-12-07 12:37:23'),(28,4,'Veg Pulao','Aromatic rice dish.','1. Cook rice.\n2. Add vegetables.\n3. Mix & serve.','Indian','MEDIUM','veg_pulao.jpg',NULL,'2025-12-07 12:37:23'),(29,5,'Paneer Roll','Tasty paneer wrap.','1. Cook paneer.\n2. Roll in roti.\n3. Serve.','Fast Food','EASY','paneer_roll.jpg',NULL,'2025-12-07 12:37:23'),(30,1,'Bhel Puri','Mumbai street food.','1. Mix sev & puffed rice.\n2. Add chutneys.','Indian','EASY','bhel_puri.jpg',NULL,'2025-12-07 12:37:23'),(31,2,'Cold Coffee','Chilled drink.','1. Blend coffee.\n2. Add ice.','Beverage','EASY','cold_coffee.jpg',NULL,'2025-12-07 12:37:23'),(32,3,'Kesari Sheera','Sweet dessert.','1. Roast semolina.\n2. Add sugar & ghee.','Dessert','MEDIUM','sheera.jpg',NULL,'2025-12-07 12:37:23'),(33,4,'Roti','Basic flatbread.','1. Knead dough.\n2. Roll & cook.','Indian','EASY','roti.jpg',NULL,'2025-12-07 12:37:23'),(34,5,'Eggless Muffins','Soft muffins.','1. Make batter.\n2. Bake.','Dessert','MEDIUM','muffins.jpg',NULL,'2025-12-07 12:37:23'),(35,1,'Poha','Flattened rice dish.','1. Wash poha.\n2. Cook with vegetables.','Indian','EASY','poha.jpg',NULL,'2025-12-07 12:37:23'),(36,2,'Chicken Curry','Spicy chicken curry.','1. Fry chicken.\n2. Make gravy.','Non-Veg','MEDIUM','chicken_curry.jpg',NULL,'2025-12-07 12:37:23'),(37,3,'Garlic Bread','Cheesy garlic bread.','1. Spread butter.\n2. Toast.','Fast Food','EASY','garlic_bread.jpg',NULL,'2025-12-07 12:37:23'),(38,4,'Milkshake','Fruit + milk blend.','1. Blend fruits.\n2. Serve chilled.','Beverage','EASY','milkshake.jpg',NULL,'2025-12-07 12:37:23'),(39,5,'Spring Rolls','Crispy rolls.','1. Fill veggies.\n2. Deep fry.','Chinese','MEDIUM','spring_roll.jpg',NULL,'2025-12-07 12:37:23'),(41,2,'scsc','scsccsc','knnc','Veg','HARD','bhel_puri.jpg','','2025-12-09 04:24:57'),(42,2,'cnnc','anncanc','nkanck','Non-Veg','EASY','cold_coffee.jpg','','2025-12-09 04:26:38'),(43,2,'test1','akdnanda','aa','Non-Veg','HARD','cake.jpeg','','2025-12-09 04:57:46'),(44,2,'test2','aac','aacac','Non-Veg','HARD','test_1.jpg','','2025-12-09 06:35:19'),(45,7,'ijjkl','llk','kjkn','Breakfast','MEDIUM','cheese_sandwich.jpg','','2025-12-09 10:39:07'),(46,6,'test2','all;axma','ad;la;l;MAM,.AM','Breakfast','MEDIUM','cake.jpeg','','2025-12-09 21:51:39');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps`
--

DROP TABLE IF EXISTS `steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steps` (
  `step_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `step_no` int(11) NOT NULL,
  `instruction` text NOT NULL,
  PRIMARY KEY (`step_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `steps_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps`
--

LOCK TABLES `steps` WRITE;
/*!40000 ALTER TABLE `steps` DISABLE KEYS */;
INSERT INTO `steps` VALUES (1,1,1,'Heat oil in a pan.'),(2,1,2,'Add veggies and fry.'),(3,2,1,'Boil pasta.'),(4,2,2,'Prepare Alfredo sauce.'),(5,3,1,'Make dosa batter.'),(6,4,1,'Marinate paneer.'),(7,5,1,'Boil tomatoes.'),(8,6,1,'Prepare burger patty.'),(9,7,1,'Boil noodles.'),(10,8,1,'Prepare cake batter.');
/*!40000 ALTER TABLE `steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(120) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER','DISABLED') DEFAULT 'USER',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `failed_attempts` int(11) DEFAULT '0',
  `lock_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john01','John Carter','john@gmail.com','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','ADMIN','2025-11-22 04:04:39',0,NULL),(2,'maria02','Maria Lopez','maria@gmail.com','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','USER','2025-11-22 04:04:39',0,NULL),(3,'rahul03','Rahul Shah','rahul@gmail.com','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','USER','2025-11-22 04:04:39',0,NULL),(4,'anita04','Anita Desai','anita@gmail.com','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','USER','2025-11-22 04:04:39',0,NULL),(5,'david05','David patel','david@gmail.com','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','ADMIN','2025-11-22 04:04:39',0,NULL),(6,'pk','pk','prakashkhant0234@gmail.com','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','USER','2025-12-08 23:56:24',0,NULL),(7,'sk','sk','prakashkhant9234@gmail.com','9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c','USER','2025-12-09 07:17:00',0,NULL);
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

-- Dump completed on 2025-12-10 11:08:53
