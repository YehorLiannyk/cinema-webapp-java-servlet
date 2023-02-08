-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `films` (
  `film_id` int NOT NULL AUTO_INCREMENT,
  `film_name` varchar(120) NOT NULL,
  `description` varchar(800) DEFAULT NULL,
  `poster_url` varchar(2000) NOT NULL DEFAULT 'https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg',
  `duration` int NOT NULL,
  PRIMARY KEY (`film_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES (2,'Пухнасті бешкетники','Один король ведмедів на ім\'я Бантура присвоїв собі те, що за фактом йому не належить – це магічний Камінь Води. Цей артефакт завжди був у лісі, завдяки якому струмок завжди був сповнений цілющої рідини. Зараз же без Каменя води в потічку майже немає, він буквально із дня на день пересохне зовсім. Щоб виправити цю ситуацію, потрібно, щоб хтось вирушив до лігва Бантури та забрав у нього камінь. На це погодилася смілива їжачиха Латте та її друг білченя Т\'юм. На їх шляху зустрінеться чимало пригод, і найбільше клопоту їм принесуть ватажок банди вовків Лупо та Рись. Це буде справжнє випробування для дружби Латте та Т\'юма.','https://kinoafisha.ua/upload/2019/07/films/8906/20fejso5ejik-latte-i-magicseskii-kamen.jpg',81),(3,'Швидка','Щоб врятувати дружину від хвороби, заслужений ветеран звертається по допомогу до прийомного брата злочинця. Пограбувати банк та зірвати багатомільйонний куш видається вдалою ідеєю… Та коли все піде не за планом, героям не залишиться нічого іншого, крім як викрасти швидку допомогу з двома заручниками всередині.','https://woodmallcinema.com/storage/app/uploads/public/ba3/865/a70/thumb__450_0_0_0_auto.jpg',136),(4,'Бетмен',NULL,'https://woodmallcinema.com/storage/app/uploads/public/8a2/3e7/b0e/thumb__450_0_0_0_auto.jpg',176),(5,'Побачення в Парижі','Вінченцо (Серджо Кастеллітто) – власник книжкового магазину у Парижі. Тут усе рідше щось купують, надаючи перевагу читанню за допомогою гаджетів. Тим часом Вінченцо навіть власного телефону не має. Він живе над магазином разом зі своєю донькою. Останні чотири роки вона прикута до інвалідного візка унаслідок нещасного випадку. Відтоді дівчина не розмовляє, а батько намагається її розрадити. Усталене та неквапливе життя Вінченцо перевертається з ніг на голову з появою у магазині акторки Іоланти (Береніс Бежо). Ця ексцентрична жінка, здавалося б, мала викликати роздратування. Вінченцо звик до тиші, і цю традицію варто поважати. Іоланта спробує поділитися з ним своїм поглядом на життя.','https://woodmallcinema.com/storage/app/uploads/public/bba/5eb/117/thumb__450_0_0_0_auto.jpg',98),(25,'Гра тіней','','https://woodmallcinema.com/storage/app/uploads/public/e19/ec4/a50/thumb__450_0_0_0_auto.jpg',108),(26,'Світ Юрського періоду 3: Домініон','','https://woodmallcinema.com/storage/app/uploads/public/4d7/699/e54/thumb__450_0_0_0_auto.jpg',120),(29,'Панама','','https://woodmallcinema.com/storage/app/uploads/public/edd/3c9/a54/thumb__450_0_0_0_auto.jpg',94),(30,'Поганці','«Поганці» — американський комп\'ютерно-анімаційний комедійний фільм про пограбування, створений кіностудією DreamWorks Animation та Scholastic Entertainment. Фільм заснований на популярній серії дитячих книг, написаних Аароном Блабеєм.','https://woodmallcinema.com/storage/app/uploads/public/54c/93d/8a6/thumb__450_0_0_0_auto.jpg',100),(32,'Фантастичні звірі: Таємниці Дамблдора','У 1932 році любитель магічних істот на ім\'я Ньют Саламандер знаходиться в місті Квейлін, розташованому в Китаї. Він приймає пологи у драконоподібного Цилиня, здатного бачити душу людини та події майбутнього. На світ з\'являються близнюки, але в цей момент нападають поплічники Геллерта Ґріндельвальда, очолювані підлим Кріденсом Бербоуном і вбивають матір, забравши одного з дитинчат. Про наявність другого лиходії не здогадуються, а головний герой умудряється втекти разом із новонародженим. Коли антагоніст отримує звіра, то вбиває, забравши надприродний дар передбачення.','https://woodmallcinema.com/storage/app/uploads/public/1bb/c7a/67d/thumb__450_0_0_0_auto.jpg',142);
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `films_genres`
--

DROP TABLE IF EXISTS `films_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `films_genres` (
  `film_id` int NOT NULL,
  `genre_id` int NOT NULL,
  KEY `film_id_genre_idx` (`film_id`),
  KEY `genre_id_film_idx` (`genre_id`),
  CONSTRAINT `film_id_genre` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `genre_id_film` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films_genres`
--

LOCK TABLES `films_genres` WRITE;
/*!40000 ALTER TABLE `films_genres` DISABLE KEYS */;
INSERT INTO `films_genres` VALUES (2,1),(2,7),(2,8),(2,9),(3,3),(3,4),(3,5),(4,4),(4,5),(4,6),(5,1),(5,2),(25,10),(25,3),(26,7),(29,6),(29,4),(30,9),(30,1),(30,7),(30,8),(32,7),(32,8),(32,19);
/*!40000 ALTER TABLE `films_genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `free_seats`
--

DROP TABLE IF EXISTS `free_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `free_seats` (
  `session_seat_id` int NOT NULL AUTO_INCREMENT,
  `session_id` int NOT NULL,
  `seat_id` int NOT NULL,
  PRIMARY KEY (`session_seat_id`),
  KEY `seat_session_id_idx` (`session_id`),
  KEY `free_seat_id_idx` (`seat_id`),
  CONSTRAINT `free_seat_id` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `seat_session_id` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`session_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=641 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `free_seats`
--

LOCK TABLES `free_seats` WRITE;
/*!40000 ALTER TABLE `free_seats` DISABLE KEYS */;
INSERT INTO `free_seats` VALUES (341,24,1),(342,24,2),(343,24,3),(344,24,4),(345,24,5),(346,24,6),(347,24,7),(348,24,8),(349,24,9),(350,24,10),(351,24,11),(352,24,12),(353,24,13),(354,24,14),(357,24,17),(358,24,18),(359,24,19),(360,24,20),(361,24,21),(362,24,22),(363,24,23),(364,24,24),(365,24,25),(366,24,26),(367,24,27),(368,24,28),(369,24,29),(370,24,30),(371,25,1),(372,25,2),(373,25,3),(374,25,4),(375,25,5),(376,25,6),(377,25,7),(378,25,8),(379,25,9),(380,25,10),(381,25,11),(382,25,12),(383,25,13),(384,25,14),(385,25,15),(386,25,16),(387,25,17),(388,25,18),(389,25,19),(390,25,20),(391,25,21),(392,25,22),(393,25,23),(394,25,24),(395,25,25),(396,25,26),(397,25,27),(398,25,28),(399,25,29),(400,25,30),(401,26,1),(402,26,2),(403,26,3),(404,26,4),(405,26,5),(406,26,6),(407,26,7),(408,26,8),(409,26,9),(410,26,10),(411,26,11),(412,26,12),(413,26,13),(414,26,14),(415,26,15),(416,26,16),(417,26,17),(418,26,18),(419,26,19),(420,26,20),(421,26,21),(422,26,22),(423,26,23),(424,26,24),(425,26,25),(426,26,26),(427,26,27),(428,26,28),(429,26,29),(430,26,30),(431,27,1),(432,27,2),(433,27,3),(434,27,4),(435,27,5),(436,27,6),(437,27,7),(438,27,8),(439,27,9),(440,27,10),(441,27,11),(442,27,12),(443,27,13),(444,27,14),(445,27,15),(446,27,16),(447,27,17),(448,27,18),(449,27,19),(450,27,20),(451,27,21),(452,27,22),(453,27,23),(454,27,24),(455,27,25),(456,27,26),(457,27,27),(458,27,28),(459,27,29),(460,27,30),(461,28,1),(462,28,2),(463,28,3),(464,28,4),(465,28,5),(466,28,6),(467,28,7),(468,28,8),(469,28,9),(470,28,10),(471,28,11),(472,28,12),(473,28,13),(474,28,14),(475,28,15),(476,28,16),(477,28,17),(478,28,18),(479,28,19),(480,28,20),(481,28,21),(482,28,22),(483,28,23),(484,28,24),(485,28,25),(486,28,26),(487,28,27),(488,28,28),(489,28,29),(490,28,30),(491,29,1),(492,29,2),(493,29,3),(494,29,4),(495,29,5),(496,29,6),(497,29,7),(498,29,8),(499,29,9),(500,29,10),(501,29,11),(502,29,12),(503,29,13),(504,29,14),(505,29,15),(506,29,16),(507,29,17),(508,29,18),(509,29,19),(510,29,20),(511,29,21),(512,29,22),(513,29,23),(514,29,24),(515,29,25),(516,29,26),(517,29,27),(518,29,28),(519,29,29),(520,29,30),(521,30,1),(522,30,2),(523,30,3),(524,30,4),(525,30,5),(526,30,6),(527,30,7),(528,30,8),(529,30,9),(530,30,10),(531,30,11),(532,30,12),(533,30,13),(534,30,14),(535,30,15),(536,30,16),(537,30,17),(538,30,18),(539,30,19),(540,30,20),(541,30,21),(542,30,22),(543,30,23),(544,30,24),(545,30,25),(546,30,26),(547,30,27),(548,30,28),(549,30,29),(550,30,30),(551,31,1),(552,31,2),(553,31,3),(554,31,4),(555,31,5),(556,31,6),(557,31,7),(558,31,8),(559,31,9),(560,31,10),(561,31,11),(562,31,12),(563,31,13),(564,31,14),(565,31,15),(566,31,16),(567,31,17),(568,31,18),(569,31,19),(570,31,20),(571,31,21),(572,31,22),(573,31,23),(574,31,24),(575,31,25),(576,31,26),(577,31,27),(578,31,28),(579,31,29),(580,31,30),(581,32,1),(582,32,2),(583,32,3),(584,32,4),(585,32,5),(586,32,6),(587,32,7),(588,32,8),(589,32,9),(590,32,10),(591,32,11),(592,32,12),(593,32,13),(594,32,14),(595,32,15),(596,32,16),(597,32,17),(598,32,18),(599,32,19),(600,32,20),(601,32,21),(602,32,22),(603,32,23),(604,32,24),(605,32,25),(606,32,26),(607,32,27),(608,32,28),(609,32,29),(610,32,30),(611,33,1),(612,33,2),(613,33,3),(614,33,4),(615,33,5),(616,33,6),(617,33,7),(618,33,8),(619,33,9),(620,33,10),(621,33,11),(622,33,12),(623,33,13),(624,33,14),(625,33,15),(626,33,16),(627,33,17),(628,33,18),(629,33,19),(630,33,20),(631,33,21),(632,33,22),(633,33,23),(634,33,24),(635,33,25),(636,33,26),(637,33,27),(638,33,28),(639,33,29),(640,33,30);
/*!40000 ALTER TABLE `free_seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(45) NOT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE KEY `genre_name_UNIQUE` (`genre_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (15,'Історична драма'),(9,'Анімація'),(10,'Біографічний'),(6,'Бойовик'),(12,'Вестерн'),(13,'Детектив'),(14,'Дитяче'),(11,'Документальне кіно'),(3,'Драма'),(5,'Екшн'),(20,'Жахи'),(1,'Комедія'),(4,'Кримінал'),(16,'Мелодрама'),(17,'Музичний фільм'),(7,'Пригоди'),(2,'Романтика'),(8,'Сімейний'),(18,'Трилер'),(19,'Фентезі');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'admin'),(1,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `row_number` int NOT NULL,
  `place_number` int NOT NULL,
  PRIMARY KEY (`seat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,2,1),(12,2,2),(13,2,3),(14,2,4),(15,2,5),(16,2,6),(17,2,7),(18,2,8),(19,2,9),(20,2,10),(21,3,1),(22,3,2),(23,3,3),(24,3,4),(25,3,5),(26,3,6),(27,3,7),(28,3,8),(29,3,9),(30,3,10);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessions` (
  `session_id` int NOT NULL AUTO_INCREMENT,
  `film_id` int NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `ticket_price` decimal(10,2) NOT NULL,
  `free_seats` int NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `film_id_session_idx` (`film_id`),
  CONSTRAINT `film_id_session` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` VALUES (24,26,'2023-06-07','12:00:00',340.00,28),(25,25,'2023-06-09','12:00:00',220.00,30),(26,5,'2023-06-15','17:45:00',120.00,30),(27,4,'2023-06-07','19:00:00',370.00,30),(28,3,'2023-06-24','09:00:00',190.00,30),(29,3,'2023-06-22','15:15:00',350.00,30),(30,2,'2023-06-15','16:15:00',200.00,30),(31,5,'2023-06-15','14:00:00',220.00,30),(32,25,'2023-06-09','18:00:00',250.00,30),(33,26,'2023-06-09','12:11:00',290.00,30);
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `session_id` int NOT NULL,
  `user_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `ticket_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `session_id_ticket_idx` (`session_id`),
  KEY `user_id_ticket_idx` (`user_id`),
  KEY `seat_id_ticket_idx` (`seat_id`),
  CONSTRAINT `seat_id_ticket` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `session_id_ticket` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`session_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_id_ticket` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (55,24,57,15,340.00),(56,24,57,16,340.00);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL DEFAULT '1',
  `first_name` varchar(45) NOT NULL,
  `second_name` varchar(45) NOT NULL,
  `email` varchar(320) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone_number` varchar(13) DEFAULT NULL,
  `notification` tinyint NOT NULL DEFAULT '1',
  `salt` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_role_id_idx` (`role_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (55,2,'Yehor','Liannyk','admin@test.com','iCXKHlYT6GNwwFby3eWafsE3ib74eTG5MpMOupXJ2Lk=','',1,'5WF5hlyTxUYMEz2NxibYMOUCz0njay'),(57,1,'Max','Bondarenko','user@test.com','WvOaKWhYE58fFWTx91vzFzrROQIcthiayUyMzv75ayo=','380679999999',1,'00sghQaHyIeDSi6x7ryV2zHypQmTRZ');
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

-- Dump completed on 2023-02-08 15:08:43
