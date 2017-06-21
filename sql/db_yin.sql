/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_yin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-21 16:14:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for charge_project
-- ----------------------------
DROP TABLE IF EXISTS `charge_project`;
CREATE TABLE `charge_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cp_name` varchar(255) DEFAULT NULL,
  `create_user_name` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `paymoney` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charge_project
-- ----------------------------

-- ----------------------------
-- Table structure for rsorders
-- ----------------------------
DROP TABLE IF EXISTS `rsorders`;
CREATE TABLE `rsorders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cp_name` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `paymoney` varchar(255) DEFAULT NULL,
  `paytime` datetime DEFAULT NULL,
  `payuser` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `terminal` varchar(255) DEFAULT NULL,
  `tradeno` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rsorders
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '', '用户管理', '0', '0/', 'userInfo:view2', 'menu', 'userInfo/view2');
INSERT INTO `sys_permission` VALUES ('2', '', '微信用户管理', '0', '0/', 'weixinUserInfo:view', 'button', 'weixinUserInfo/view');
INSERT INTO `sys_permission` VALUES ('3', '', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', '', 'VIP会员', 'vip');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKomxrs8a388bknvhjokh440waq` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`),
  CONSTRAINT `FKgkmyslkrfeyn9ukmolvek8b8f` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `city_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0', 'admin', 'yinleilei');

-- ----------------------------
-- Table structure for weixin_user_info
-- ----------------------------
DROP TABLE IF EXISTS `weixin_user_info`;
CREATE TABLE `weixin_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `head_img_url` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) NOT NULL,
  `province` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  `subscribe` int(11) NOT NULL,
  `subscribe_time` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `subscribe_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7h6ovp1rc7tgt809jfgfdjhtv` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_user_info
-- ----------------------------
INSERT INTO `weixin_user_info` VALUES ('5', '西安', '中国', 'http://wx.qlogo.cn/mmopen/Q3auHgzwzM5NCSicX1sRRILaon2ru7f7enqkQYYGSxFebeHOdDUr9CtPjoN6heyHghH7uaePelOicOTibCicia3yYu0IiccfoCFZrU7Zt1bKwoDLw/0', 'zh_CN', '꧁ ༺以梦༒为马༻ ꧂', 'o7rY8wNsmDyYF-76OgdATGyWiJLI', '陕西', '1', '1', '1495691297', '1', '2017-06-21 14:23:01');
