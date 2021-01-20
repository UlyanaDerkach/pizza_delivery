-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: pizza_delivery
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `category`
--
CREATE DATABASE IF NOT EXISTS PIZZA_DELIVERY CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use pizza_delivery;
DROP TABLE IF EXISTS category;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE category (
  category_id bigint NOT NULL AUTO_INCREMENT,
  category_name varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (category_id),
  UNIQUE KEY category_name (category_name)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES category WRITE;
/*!40000 ALTER TABLE category DISABLE KEYS */;
INSERT INTO category VALUES (2,'Beverages'),(3,'Desserts'),(1,'Pizza');
/*!40000 ALTER TABLE category ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS order_base;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE order_base (
  order_id bigint NOT NULL AUTO_INCREMENT,
  order_date date NOT NULL,
  order_time time NOT NULL,
  delivery_date date NOT NULL,
  delivery_time time NOT NULL,
  total_sum int NOT NULL,
  user_id bigint NOT NULL,
  status_id bigint NOT NULL,
  PRIMARY KEY (order_id),
  KEY user_id (user_id),
  KEY status_id (status_id),
  CONSTRAINT order_ibfk_1 FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT order_ibfk_2 FOREIGN KEY (status_id) REFERENCES status_dict (status_id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES order_base WRITE;
/*!40000 ALTER TABLE order_base DISABLE KEYS */;
INSERT INTO order_base VALUES (36,'2021-01-17','14:58:00','2021-01-18','18:00:00',5300,4,3),(37,'2021-01-17','14:59:03','2021-01-19','20:00:00',750,3,2);
/*!40000 ALTER TABLE order_base ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS order_details;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE order_details (
  details_id bigint NOT NULL AUTO_INCREMENT,
  order_id bigint NOT NULL,
  product_id bigint NOT NULL,
  product_amount int unsigned NOT NULL,
  total_cost decimal(8,2) NOT NULL,
  PRIMARY KEY (details_id),
  KEY order_id (order_id),
  KEY product_id (product_id),
  CONSTRAINT order_details_ibfk_1 FOREIGN KEY (order_id) REFERENCES order_base (order_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT order_details_ibfk_2 FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES order_details WRITE;
/*!40000 ALTER TABLE order_details DISABLE KEYS */;
INSERT INTO order_details VALUES (35,36,1,2,4100.00),(36,36,9,2,1200.00),(37,37,14,1,750.00);
/*!40000 ALTER TABLE order_details ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS product;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE product (
  product_id bigint NOT NULL AUTO_INCREMENT,
  product_name varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  details tinytext COLLATE utf8_unicode_ci,
  price int NOT NULL,
  category_id bigint NOT NULL,
  picture_path varchar(100)  COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (product_id),
  KEY category_id (category_id),
  CONSTRAINT product_ibfk_2 FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES product WRITE;
/*!40000 ALTER TABLE product DISABLE KEYS */;
INSERT INTO product VALUES (1,'Пепперони','увеличенная порция моцареллы, томатный соус',2050,1,'pepperoni.jpg'),
(2,'Классический молочный коктейль','Напиток из молока и мороженого',850,3,'classic_milkshake.jpg'),
(3,'Кофе Американо','Горячий напиток на основе эспрессо, 0,3 л',450,2,'americano.jpg'),
(4,'Цезарь','Свежие листья салата айсберг, цыплёнок, томаты черри, сыры чеддар и пармезан, моцарелла, сливочный соус, соус цезарь',2250,1,'ceazar_pizza.jpg'),
(5,'Четыре сезона','Моцарелла, ветчина из цыпленка, пепперони из цыпленка, кубики брынзы, томаты, шампиньоны, томатный соус, итальянские травы',2050,1,'four_seasons.jpg'),
(6,'Маргарита','Увеличенная порция моцареллы, томаты, итальянские травы, томатный соус',1750,1,'margarita.jpg'),
(7,'Четыре сыра','Моцарелла, сыры чеддер и пармезан, сыр блю чиз, сливочный соус',2250,1,'four_cheese.jpg'),
(8,'Pepsi','0,5 л',300.00,2,'pepsi.jpg'),
(9,'Облепиховый пунш','Согревающий напиток с сиропом из ягод облепихи и лимонного сока, 0,3 л',600,2,'oblepiha.jpg'),
(10,'Кофе Ванильный капучино','Горячий напиток на основе эспрессо со вспененным молоком и ванильным сиропом, 0,3 л',700,2,'capuchino.jpg'),
(11,'Клубничный лимонад','Газированный напиток с клубничным пюре и сиропом мандарина, 0,32 л',650,2,'strawberry_lemonade.jpg'),
(12,'Пряный латте','Горячий напиток на основе эспрессо с увеличенной порцией молока, корицей и сиропом с медово-пряным вкусом, 0,3 л',700,2,'latte.jpg'),
(13,'Молочный коктейль с печеньем Орео','Напиток из молока и мороженого с добавлением дробленого печенья «Орео»',950,3,'oreo.jpg'),
(14,'Чизкейк Нью-Йорк','Классический американский творожный десерт',750,3,'new_york.jpg'),
(15,'Клубничный пончик','Нежный пончик с клубничной начинкой и разноцветной посыпкой',550,3,'strawberry_donut.jpg'),
(16,'Маффин Три шоколада','Десерт из натурального какао, нежной начинки на основе шоколада и кубиков белого и молочного шоколада',450,3,'muffin.jpg'),
(17,'Фондан','Два десерта с хрустящей корочкой и топлёной шоколадной начинкой',1600,3,'fondan.jpg');
/*!40000 ALTER TABLE product ENABLE KEYS */;
UNLOCK TABLES;

-- Table structure for table `status_dict`
--

DROP TABLE IF EXISTS status_dict;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE status_dict (
  status_id bigint NOT NULL AUTO_INCREMENT,
  status_name varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (status_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_dict`
--

LOCK TABLES status_dict WRITE;
/*!40000 ALTER TABLE status_dict DISABLE KEYS */;
INSERT INTO status_dict VALUES (1,'ACCEPTED'),(2,'FORMED'),(3,'ON THE WAY'),(4,'DELIVERED');
/*!40000 ALTER TABLE status_dict ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS user_info;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE user_info (
  user_id bigint NOT NULL AUTO_INCREMENT,
  is_admin tinyint(1) NOT NULL,
  is_deleted tinyint(1) NOT NULL,
  first_name varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  last_name varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  email varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  phone varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  birth_date datetime NOT NULL,
  address varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  login varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  password_hash varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY email (email),
  UNIQUE KEY login (login)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES user_info WRITE;
/*!40000 ALTER TABLE user_info DISABLE KEYS */;
INSERT INTO user_info VALUES (1,1,0,'Tom','Riddle','tom.riddle@gmail.com','+7(775)587-49-85','1939-12-01 00:00:00','Godric\'s Hollow village, 25/1','tom_riddle','7488e331b8b64e5794da3fa4eb10ad5d'),
(2,0,0,'Иван','Иванов','ivanov@gmail.com','+7(775)587-47-78','1965-01-12 00:00:00','Ленина, 48/3, 87','ivan_ivanov','fd6a0c91f291541eedb243cb436aaf42'),
(3,0,0,'Анна','Громова','anna@gmail.com','+77478547856','1993-01-11 00:00:00','ул. Нефтянников, 28/1, кв. 58','anna93','72e1091218ccd87e31a3ed8329ea4920'),
(4,0,0,'Alan','Rickman','alan@gmail.ru','+77775432148','1987-01-22 00:00:00','Wall Street, 55, 11','alan87','fcea920f7412b5da7be0cf42b8c93759');
/*!40000 ALTER TABLE user_info ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

--
-- Final view structure for view `user_order_history`
--

/*!50001 DROP VIEW IF EXISTS `user_order_history`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW user_order_history AS select 
order_base.order_id AS order_id,
order_base.user_id AS user_id,
product.product_name AS product_name,
order_details.product_amount AS product_amount,
product.picture_path AS picture_path,
order_base.delivery_date AS delivery_date,
order_base.delivery_time AS delivery_time 
from (((order_base join order_details on((order_base.order_id = order_details.order_id))) 
join product on((order_details.product_id = product.product_id)))) */;

/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-19  8:29:00
