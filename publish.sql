-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.59 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 publish 的数据库结构
CREATE DATABASE IF NOT EXISTS `publish` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `publish`;


-- 导出  表 publish.t_article_content 结构
CREATE TABLE IF NOT EXISTS `t_article_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `css` text NOT NULL,
  `content` text NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 publish.t_lol_article 结构
CREATE TABLE IF NOT EXISTS `t_lol_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source_type` tinyint(4) NOT NULL,
  `title` varchar(256) NOT NULL,
  `article_category` tinyint(4) NOT NULL,
  `post_time` datetime DEFAULT NULL,
  `content_id` int(11) NOT NULL,
  `source_url` varchar(1024) NOT NULL,
  `page_view` int(11) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_source_type_title` (`source_type`,`title`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 publish.t_own_blog 结构
CREATE TABLE IF NOT EXISTS `t_own_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `content` longtext NOT NULL,
  `category` tinyint(4) NOT NULL,
  `page_view` int(11) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 publish.t_pubg_article 结构
CREATE TABLE IF NOT EXISTS `t_pubg_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source_type` tinyint(4) NOT NULL,
  `title` varchar(256) NOT NULL,
  `article_category` tinyint(4) NOT NULL,
  `post_time` datetime DEFAULT NULL,
  `content_id` int(11) NOT NULL,
  `source_url` varchar(1024) NOT NULL,
  `page_view` int(11) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_source_type_title` (`source_type`,`title`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
