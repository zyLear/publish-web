-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 pass_check 的数据库结构
CREATE DATABASE IF NOT EXISTS `pass_check` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `pass_check`;


-- 导出  表 pass_check.t_pass_check_code 结构
CREATE TABLE IF NOT EXISTS `t_pass_check_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_key` varchar(64) NOT NULL,
  `config_value` text NOT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_config_key` (`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pass_check.t_pass_check_code 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_pass_check_code` DISABLE KEYS */;
INSERT INTO `t_pass_check_code` (`id`, `config_key`, `config_value`, `remark`, `is_deleted`, `create_time`, `last_update_time`) VALUES
	(1, 'version_key', 'v1', NULL, 0, '2018-10-10 11:12:56', '2018-10-10 11:13:27'),
	(2, 'v1', 'mount -o remount rw /\r\nmkdir /zy\r\nls', NULL, 0, '2018-10-10 11:13:25', '2018-10-10 14:08:08');
/*!40000 ALTER TABLE `t_pass_check_code` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
