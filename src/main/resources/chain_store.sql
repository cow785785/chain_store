CREATE TABLE IF NOT EXISTS `members` (
  `id` varchar(36) NOT NULL,
  `useraccount` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `registration_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `products` (
  `id` varchar(36) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `id` varchar(36) NOT NULL,
  `order_number` varchar(20) DEFAULT NULL,
  `member_id` varchar(36) DEFAULT NULL,
  `products_id` varchar(36) DEFAULT NULL,
  `useraccount` varchar(50) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `delivery_address` varchar(100) DEFAULT NULL,
  `order_status` varchar(50) DEFAULT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `products_id` (`products_id`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
); 
