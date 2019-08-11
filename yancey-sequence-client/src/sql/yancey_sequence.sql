
-- ----------------------------
--  Table structure for `sequence_daybyday`
-- ----------------------------
DROP TABLE IF EXISTS `sequence_daybyday`;
CREATE TABLE `sequence_daybyday` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(20) NOT NULL,
  `app_value` bigint(20) NOT NULL,
  `create_day` varchar(8) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Ix_appName_createDay` (`app_name`,`create_day`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sequence_global`
-- ----------------------------
DROP TABLE IF EXISTS `sequence_global`;
CREATE TABLE `sequence_global` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(20) NOT NULL,
  `app_value` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Ix_appName` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
