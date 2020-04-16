/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17 : Database - spacecloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spacecloud` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spacecloud`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员信息的id',
  `name` varchar(100) DEFAULT NULL COMMENT '管理员账户昵称',
  `admin_account` varchar(100) NOT NULL COMMENT '管理员登录账号',
  `password` varchar(100) NOT NULL COMMENT '账号密码',
  `phone` varchar(100) DEFAULT NULL COMMENT '电话',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_account` (`admin_account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`name`,`admin_account`,`password`,`phone`,`address`,`email`,`create_date`) values (1,'少年','admin','123456','18851177031','江苏南京','spacevast@163.com','2020-03-05 17:35:31');

/*Table structure for table `t_ip_address` */

DROP TABLE IF EXISTS `t_ip_address`;

CREATE TABLE `t_ip_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'IP地址的ip',
  `public_ip` varchar(100) NOT NULL COMMENT '公网ip',
  `country` varchar(100) DEFAULT NULL COMMENT 'ip地址所在国家',
  `ip_login` varchar(11) NOT NULL COMMENT 'ip地址的登录账号',
  `ip_password` varchar(11) NOT NULL COMMENT 'ip地址的登录密码',
  `ip_status` int(11) NOT NULL COMMENT 'ip地址的状态,0代表未开启，1代表已被占用，2代表开始但无人使用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `public_ip` (`public_ip`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_ip_address` */

insert  into `t_ip_address`(`id`,`public_ip`,`country`,`ip_login`,`ip_password`,`ip_status`,`create_date`) values (1,'122.193.16.101','中国','root','toor',1,'2020-04-02 16:58:36'),(2,'122.193.16.102','中国','root','123456',1,'2020-04-02 16:59:34'),(3,'122.193.16.103','中国','root','123456',1,'2020-04-02 16:59:59'),(4,'122.193.16.104','中国','root','123456',1,'2020-04-02 17:01:19'),(5,'122.193.16.105','中国','root','123456',1,'2020-04-02 17:01:46'),(6,'122.193.16.106','中国','root','123456',1,'2020-04-02 17:01:59'),(7,'122.193.16.107','中国','root','123456',1,'2020-04-02 17:02:12'),(8,'122.193.16.108','中国','root','123456',1,'2020-04-02 17:02:32'),(9,'122.193.16.109','中国','root','123456',1,'2020-04-02 17:03:08'),(10,'122.193.16.114','中国','root','123456',1,'2020-04-02 17:03:18'),(11,'122.193.16.110','中国','root','toor',1,'2020-04-07 15:57:12'),(12,'122.193.16.111','中国','root','toor',2,'2020-04-07 15:57:34'),(13,'122.193.16.112','中国','root','toor',2,'2020-04-07 15:58:04'),(14,'122.193.16.113','中国','root','toor',2,'2020-04-07 15:58:23'),(16,'122.193.16.115','中国','root','toor',2,'2020-04-07 16:01:13'),(17,'122.193.16.116','中国','root','toor',2,'2020-04-07 19:47:17'),(18,'122.193.16.117','美国','root','123456',2,'2020-04-07 19:47:40');

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `ip_address_id` int(11) NOT NULL COMMENT 'ip地址表的id',
  `order_id` bigint(30) NOT NULL COMMENT 'order的id',
  `product_status` int(11) NOT NULL COMMENT '状态，1正常，0到期了',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `sort_id` int(11) NOT NULL COMMENT '大分类sort的id',
  `user_account` varchar(100) NOT NULL COMMENT '用户登录名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ip_address_id` (`ip_address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

insert  into `t_product`(`id`,`ip_address_id`,`order_id`,`product_status`,`start_date`,`end_date`,`sort_id`,`user_account`,`create_date`) values (1,1,1516,1,'2020-03-29 16:53:38','2020-09-25 16:53:38',1,'user','2020-04-02 17:06:43'),(2,2,1514,1,'2020-04-02 17:15:31','2020-09-29 17:15:31',1,'qimowei','2020-04-02 17:15:48'),(4,3,1517,1,'2020-04-03 20:26:17','2020-09-30 20:26:17',2,'user','2020-04-03 20:26:28'),(5,4,1522,1,'2020-04-03 20:27:19','2020-09-30 20:27:19',2,'user','2020-04-03 20:27:31'),(6,5,1523,1,'2020-04-03 20:27:41','2020-09-30 20:27:41',3,'user','2020-04-03 20:27:58'),(7,6,1524,1,'2020-04-03 20:28:03','2020-09-30 20:28:03',3,'user','2020-04-03 20:28:11'),(8,7,1525,1,'2020-04-03 20:28:40','2020-09-30 20:28:40',3,'user','2020-04-03 20:28:44'),(9,8,1526,1,'2020-04-03 20:29:06','2020-09-30 20:29:06',1,'user','2020-04-03 20:29:11'),(10,9,1527,1,'2020-04-03 20:29:21','2020-09-30 20:29:21',1,'user','2020-04-03 20:29:25'),(11,10,1531,1,'2020-04-06 23:48:09','2020-10-06 23:48:09',1,'user','2020-04-06 23:48:09'),(12,11,1532,1,'2020-04-07 15:58:54','2023-04-07 15:58:54',1,'user','2020-04-07 15:58:54');

/*Table structure for table `t_product_param` */

DROP TABLE IF EXISTS `t_product_param`;

CREATE TABLE `t_product_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cputype` varchar(50) DEFAULT NULL COMMENT 'cpu型号',
  `cpu` int(11) DEFAULT NULL COMMENT 'cpu核数/核',
  `ram` int(11) DEFAULT NULL COMMENT '内存大小-int型/GB',
  `bandwidth` int(11) DEFAULT NULL COMMENT '带宽/Mbps',
  `sysdisk` int(11) DEFAULT NULL COMMENT '系统盘/GB',
  `datadisk` int(11) DEFAULT NULL COMMENT '数据盘/GB',
  `traffic` int(11) DEFAULT NULL COMMENT '流量/GB',
  `sql_size` int(11) DEFAULT NULL COMMENT '数据库大小/MB',
  `product_type_id` int(11) NOT NULL COMMENT '具体商品的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_product_param` */

insert  into `t_product_param`(`id`,`cputype`,`cpu`,`ram`,`bandwidth`,`sysdisk`,`datadisk`,`traffic`,`sql_size`,`product_type_id`) values (1,NULL,NULL,NULL,2,NULL,3,50,300,1),(2,NULL,NULL,NULL,3,NULL,1,20,200,2),(3,NULL,NULL,NULL,1,NULL,3,20,300,3),(4,'E5-2620v3',2,2,4,40,60,NULL,NULL,4),(5,'E5-2620v3',6,5,7,40,220,NULL,NULL,5),(6,NULL,2,2,3,40,120,NULL,NULL,6),(7,NULL,4,4,5,40,200,NULL,NULL,7),(8,NULL,16,16,10,20,500,NULL,NULL,8);

/*Table structure for table `t_product_price` */

DROP TABLE IF EXISTS `t_product_price`;

CREATE TABLE `t_product_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品价格id',
  `price` double DEFAULT NULL COMMENT '商品的价格-rmb/月',
  `product_type_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_product_price` */

insert  into `t_product_price`(`id`,`price`,`product_type_id`) values (1,9.9,1),(2,49,2),(3,99,3),(4,119,4),(5,444,5),(6,222,6),(7,400,7),(8,1470,8);

/*Table structure for table `t_product_room` */

DROP TABLE IF EXISTS `t_product_room`;

CREATE TABLE `t_product_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机房的id',
  `country` varchar(200) NOT NULL COMMENT '机房所在国家',
  `city` varchar(200) DEFAULT NULL COMMENT '机房所在城市',
  `product_type_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_product_room` */

insert  into `t_product_room`(`id`,`country`,`city`,`product_type_id`) values (1,'中国','贵州',1),(2,'中国','香港',2),(3,'美国','纽约',3),(4,'中国','上海',4),(5,'中国','南京',5),(6,'中国','北京',6),(7,'中国','贵州',7),(8,'美国','洛杉矶',8);

/*Table structure for table `t_product_system` */

DROP TABLE IF EXISTS `t_product_system`;

CREATE TABLE `t_product_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统的id',
  `sys_type` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '系统的类型',
  `sys_version` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '系统类型的版本',
  `status` int(11) DEFAULT NULL COMMENT '状态,启用/禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_product_system` */

insert  into `t_product_system`(`id`,`sys_type`,`sys_version`,`status`) values (1,'CentOs','7.6 64-bit',1),(2,'CentOs','7.4 64-bit',1),(3,'CentOs','7.2 64-bit',1),(4,'CentOs','6.5 64-bit',1),(5,'CentOs','6.8 64-bit',1),(6,'CentOs','6.8 32-bit',1),(7,'Windows','2016 标准版 64位中文',1),(8,'Windows','2016 数据中心版 R2 64位中文',1),(9,'Windows','2012 标准版 R2 64位中文',1),(10,'Windows','2012 数据中心版 R2 64位中文',1),(11,'Windows','2008 标准版 R2 64位中文',1),(12,'Windows','2008 数据中心版 R2 64位中文',1),(13,'Debian','9.5 64-bit',1),(14,'Debian','7.11 64-bit',1),(15,'Debian','8.7 32-bit',1),(16,'Debian','8.7 64-bit',1),(17,'Debian','7.8 64-bit',1),(18,'Ubuntu','18.04 64-bit',1),(19,'Ubuntu','18.04 32-bit',1),(20,'Ubuntu','16.04 64-bit',1),(21,'Ubuntu','16.04 32-bit',1),(22,'Ubuntu','14.04 64-bit',1),(23,'Ubuntu','14.04 32-bit',1);

/*Table structure for table `t_product_type` */

DROP TABLE IF EXISTS `t_product_type`;

CREATE TABLE `t_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) NOT NULL COMMENT '产品名称',
  `status` int(11) DEFAULT NULL COMMENT '产品状态',
  `sort_id` int(11) DEFAULT NULL COMMENT '产品父分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_product_type` */

insert  into `t_product_type`(`id`,`name`,`status`,`sort_id`) values (1,'国内主机',1,1),(2,'香港主机',1,1),(3,'美国主机',1,1),(4,'个人型VPS',1,2),(5,'企业型VPS',1,2),(6,'太空云服务器经济型',1,3),(7,'太空云服务器基础型',1,3),(8,'太空云服务器升级型',1,3);

/*Table structure for table `t_sort` */

DROP TABLE IF EXISTS `t_sort`;

CREATE TABLE `t_sort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_sort` */

insert  into `t_sort`(`id`,`name`) values (1,'主机'),(2,'VPS'),(3,'云服务器');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户信息id',
  `name` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `user_account` varchar(100) NOT NULL COMMENT '用户登陆账号',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `identity` varchar(100) DEFAULT NULL COMMENT '用户身份证',
  `qq_num` varchar(100) DEFAULT NULL COMMENT 'QQ',
  `phone` varchar(100) NOT NULL COMMENT '用户手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `city` varchar(100) DEFAULT NULL COMMENT '用户所在城市',
  `address` varchar(100) DEFAULT NULL COMMENT '用户具体地址',
  `signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `create_date` datetime DEFAULT NULL COMMENT '账户创建时间',
  `balance` double DEFAULT NULL COMMENT '用户余额',
  `all_pay` double DEFAULT NULL COMMENT '消费金额',
  `endpay_date` datetime DEFAULT NULL COMMENT '最后交易时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`,`user_account`,`password`,`sex`,`identity`,`qq_num`,`phone`,`email`,`city`,`address`,`signature`,`create_date`,`balance`,`all_pay`,`endpay_date`) values (1,'水豚少年','qimowei','123456',0,'321322199919991998','1261431538','18851177777','spacevast@163.com','江苏省-苏州市-姑苏区','迎春路1001号','继续努力把','2020-03-24 15:21:01',5304.63,888.67,'2020-03-29 15:47:10'),(2,'user测试','user','123456',2,'321322199919991990','123123123','18851111111','123@qq.com','江苏省-南京市-秦淮区','御道街29号','永不言弃','2020-03-28 23:04:46',44784.96,16613.68,'2020-04-07 15:58:54');

/*Table structure for table `t_user_business` */

DROP TABLE IF EXISTS `t_user_business`;

CREATE TABLE `t_user_business` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '交易记录id',
  `order_id` bigint(30) NOT NULL COMMENT '订单id，链接order表或者自定义',
  `product_type_id` int(11) DEFAULT NULL COMMENT '消费的产品名称',
  `buy_type` varchar(100) DEFAULT NULL COMMENT '交易类型',
  `buy_money` double NOT NULL COMMENT '交易金额',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `buy_date` datetime NOT NULL COMMENT '交易时间',
  `user_account` varchar(100) NOT NULL COMMENT '用户登录名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `t_user_business` */

insert  into `t_user_business`(`id`,`order_id`,`product_type_id`,`buy_type`,`buy_money`,`remark`,`buy_date`,`user_account`) values (1,0,0,'充值',100,'充值','2020-03-27 10:48:29','qimowei'),(2,1514,1,'消费',40,'消费','2020-03-27 10:49:02','qimowei'),(3,1515,2,'消费',6666.6,'消费','2020-03-29 17:00:22','qimowei'),(4,1516,3,'消费',120,'消费','2020-03-29 17:02:00','user'),(6,1585641197310,0,'充值',50,'充值','2020-03-31 15:53:17','user'),(7,1585642985294,0,'充值',200,'充值','2020-03-31 16:23:05','user'),(8,1585642986193,0,'充值',200,'充值','2020-03-31 16:23:06','user'),(9,1585643530642,0,'充值',200,'充值','2020-03-31 16:32:11','user'),(10,1585643531502,0,'充值',200,'充值','2020-03-31 16:32:12','user'),(11,1585644924609,0,'充值',200,'充值','2020-03-31 16:55:25','user'),(12,1585644958347,0,'充值',300,'充值','2020-03-31 16:55:58','user'),(13,1585645906064,0,'充值',200,'充值','2020-03-31 17:11:46','user'),(14,1585645915296,0,'充值',200,'充值','2020-03-31 17:11:55','user'),(15,1585645915905,0,'充值',200,'充值','2020-03-31 17:11:56','user'),(16,1585645916060,0,'充值',200,'充值','2020-03-31 17:11:56','user'),(17,1585645916216,0,'充值',200,'充值','2020-03-31 17:11:56','user'),(18,1585645916350,0,'充值',200,'充值','2020-03-31 17:11:56','user'),(19,1585646333727,0,'充值',12.2,'充值','2020-03-31 17:18:54','user'),(20,1585651495851,0,'充值',10000,'充值','2020-03-31 18:44:56','user'),(21,1585657431292,0,'充值',10,'充值','2020-03-31 20:23:51','user'),(22,1585813731052,0,'充值',200,'充值','2020-04-02 15:48:51','user'),(23,1517,4,'充值',118.8,'消费','2020-04-03 20:35:04','user'),(24,1522,5,'消费',120,'消费','2020-04-03 20:35:40','user'),(25,1523,6,'消费',1200,'消费','2020-04-03 20:36:34','user'),(26,1524,7,'消费',33,'消费','2020-04-03 20:37:37','user'),(27,1525,8,'消费',1436.4,'消费','2020-04-03 20:38:13','user'),(28,1526,1,'消费',9.9,'消费','2020-04-03 20:38:38','user'),(29,1527,2,'消费',39.8,'消费','2020-04-03 20:40:04','user'),(31,1531,1,'消费',1199.4,'消费/主机/6个月','2020-04-06 23:48:09','user'),(40,1532,2,'消费',7196.4,'消费/香港主机/36个月','2020-04-07 15:58:54','user');

/*Table structure for table `t_user_order` */

DROP TABLE IF EXISTS `t_user_order`;

CREATE TABLE `t_user_order` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态，0代表已支付，1代表未支付',
  `product_type_id` int(11) NOT NULL COMMENT '商品名称id',
  `product_system_id` int(11) NOT NULL COMMENT '操作系统id',
  `order_date` datetime DEFAULT NULL COMMENT '消费时间,支付时间',
  `order_price` double DEFAULT NULL COMMENT '购买总价',
  `order_term` int(11) DEFAULT NULL COMMENT '购买期限/月',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_account` varchar(100) NOT NULL COMMENT '用户登录名',
  `create_date` datetime DEFAULT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1533 DEFAULT CHARSET=utf8;

/*Data for the table `t_user_order` */

insert  into `t_user_order`(`id`,`order_status`,`product_type_id`,`product_system_id`,`order_date`,`order_price`,`order_term`,`remark`,`user_account`,`create_date`) values (1514,0,1,1,'2020-03-27 11:16:18',20,2,NULL,'qimowei','2020-03-27 11:16:18'),(1515,1,2,2,NULL,1111,6,NULL,'qimowei','2020-03-29 15:47:10'),(1516,0,3,3,'2020-03-29 16:53:38',10,12,NULL,'user','2020-03-29 16:53:38'),(1517,0,4,4,'2020-04-03 20:21:53',9.9,12,'消费','user','2020-03-28 17:09:53'),(1522,0,5,5,'2020-04-03 20:22:17',20,6,'消费','user','2020-04-03 20:22:36'),(1523,0,6,6,'2020-04-03 20:22:59',100,12,'消费','user','2020-04-03 20:23:18'),(1524,0,7,7,'2020-04-03 20:23:30',11,3,'消费','user','2020-04-03 20:23:43'),(1525,0,8,8,'2020-04-03 20:23:56',39.9,36,'消费','user','2020-04-03 20:24:18'),(1526,0,1,1,'2020-04-03 20:24:50',9.9,1,'消费','user','2020-04-03 20:25:06'),(1527,0,2,2,'2020-04-03 20:25:26',19.9,2,'消费','user','2020-04-03 20:25:09'),(1528,1,2,2,'2020-04-03 20:25:26',19.9,2,'消费','user','2020-04-03 20:25:09'),(1531,0,1,1,'2020-04-06 23:48:09',199.9,6,'消费/主机/6个月','user','2020-04-06 23:33:53'),(1532,0,2,3,'2020-04-07 15:58:54',199.9,36,'消费/香港主机/36个月','user','2020-04-07 14:39:43');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
