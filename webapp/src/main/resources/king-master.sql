/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : king-master

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2020-12-02 08:41:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dbinfo
-- ----------------------------
DROP TABLE IF EXISTS `dbinfo`;
CREATE TABLE `dbinfo` (
  `id` int(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dbinfo
-- ----------------------------
INSERT INTO `dbinfo` VALUES ('1', 'master');

-- ----------------------------
-- Table structure for em_attr_type
-- ----------------------------
DROP TABLE IF EXISTS `em_attr_type`;
CREATE TABLE `em_attr_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(100) NOT NULL COMMENT '产品属性类型',
  `value` int(11) DEFAULT '1' COMMENT '产品属性类型值{1-String,2-Integer,3-Double,4-Datetime}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_attr_type
-- ----------------------------
INSERT INTO `em_attr_type` VALUES ('1', 'Varchar', '1');
INSERT INTO `em_attr_type` VALUES ('2', 'Integer', '2');
INSERT INTO `em_attr_type` VALUES ('3', 'Double', '3');
INSERT INTO `em_attr_type` VALUES ('4', 'Datetime', '4');

-- ----------------------------
-- Table structure for em_base_params
-- ----------------------------
DROP TABLE IF EXISTS `em_base_params`;
CREATE TABLE `em_base_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fixed_current` decimal(15,4) DEFAULT NULL COMMENT '额定电流（A）',
  `fixed_voltage` decimal(15,4) DEFAULT NULL COMMENT '额定电压（V）',
  `fixed_speed` decimal(15,4) DEFAULT NULL COMMENT '额定转速（r/min）',
  `fixed_torque` decimal(15,4) DEFAULT NULL COMMENT '额定转矩（N·m）',
  `overload_capacity` decimal(10,2) DEFAULT NULL COMMENT '过载能力（倍）',
  `machine_length` decimal(10,2) DEFAULT NULL COMMENT '电机机身长度（m）',
  `machine_height` decimal(10,2) DEFAULT NULL COMMENT '电机横截面高度（m）',
  `machine_width` decimal(10,2) DEFAULT NULL COMMENT '电机横截面宽度（m）',
  `rotor_length` decimal(10,2) DEFAULT NULL COMMENT '转子轴前端长度（m）',
  `machine_weight` decimal(10,2) DEFAULT NULL COMMENT '电机重量（Kg）',
  `is_default` int(11) DEFAULT NULL COMMENT '是否默认 1-是 0-否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_base_params
-- ----------------------------
INSERT INTO `em_base_params` VALUES ('1', '0.8000', '380.0000', '1000.0000', '4.0000', '3.00', '0.19', '0.13', '0.12', '0.03', '6.00', '1');
INSERT INTO `em_base_params` VALUES ('3', '1.0000', '1.0000', '1.0000', '1.0000', '1.00', '1.00', '1.00', '1.00', '1.00', '1.00', '0');

-- ----------------------------
-- Table structure for em_data
-- ----------------------------
DROP TABLE IF EXISTS `em_data`;
CREATE TABLE `em_data` (
  `template_id` int(11) NOT NULL COMMENT '模板表ID',
  `data_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '数据时间',
  `speed` decimal(15,4) DEFAULT NULL COMMENT '转速',
  `speed_10` decimal(15,4) DEFAULT NULL COMMENT '转速10个值',
  `speed_empty` decimal(15,4) DEFAULT NULL COMMENT '空载转速',
  `speed_fix` decimal(15,4) DEFAULT NULL COMMENT '额定负载转速',
  `speed_forward` decimal(15,4) DEFAULT NULL COMMENT '正向转速',
  `speed_reverse` decimal(15,4) DEFAULT NULL COMMENT '反向转速',
  `speed_max` decimal(15,4) DEFAULT NULL COMMENT '最大转速',
  `speed_min` decimal(15,4) DEFAULT NULL COMMENT '最小转速',
  `speed_100` decimal(15,4) DEFAULT NULL COMMENT '转速100个值',
  `torque_100` decimal(15,4) DEFAULT NULL COMMENT '转矩100个值',
  `speed_setter` decimal(15,4) DEFAULT NULL COMMENT '转速设定值',
  `torque_overload` decimal(15,4) DEFAULT NULL COMMENT '负载转矩'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data
-- ----------------------------

-- ----------------------------
-- Table structure for em_data_constantload
-- ----------------------------
DROP TABLE IF EXISTS `em_data_constantload`;
CREATE TABLE `em_data_constantload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '所属项目ID',
  `speed_100` decimal(15,4) DEFAULT NULL COMMENT '转速（r/min）100个值',
  `torque_100` decimal(15,4) DEFAULT NULL COMMENT '转矩（N•m）100个值',
  `speed_setter` decimal(15,4) DEFAULT NULL COMMENT '转速设定值（r/min ）',
  `torque_overload` decimal(15,4) DEFAULT NULL COMMENT '负载转矩(T•m)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_constantload
-- ----------------------------
INSERT INTO `em_data_constantload` VALUES ('1', '1', '999.0000', '4.0000', '1000.0000', '4.0000');
INSERT INTO `em_data_constantload` VALUES ('2', '2', '999.0000', '4.0000', '1000.0000', '4.0000');
INSERT INTO `em_data_constantload` VALUES ('58', '4', '999.1000', '4.0101', '1000.0000', '4.0000');
INSERT INTO `em_data_constantload` VALUES ('59', '4', '1000.3135', '3.7604', null, null);
INSERT INTO `em_data_constantload` VALUES ('60', '4', '1001.9006', '3.7236', null, null);
INSERT INTO `em_data_constantload` VALUES ('61', '4', '1002.0031', '3.7274', null, null);
INSERT INTO `em_data_constantload` VALUES ('62', '4', '1001.9944', '4.3539', null, null);
INSERT INTO `em_data_constantload` VALUES ('63', '4', '999.3239', '3.7957', null, null);
INSERT INTO `em_data_constantload` VALUES ('64', '4', '999.5009', '4.4904', null, null);
INSERT INTO `em_data_constantload` VALUES ('65', '4', '999.8828', '4.4318', null, null);
INSERT INTO `em_data_constantload` VALUES ('66', '4', '999.3922', '3.8049', null, null);
INSERT INTO `em_data_constantload` VALUES ('67', '4', '999.1452', '3.9606', null, null);
INSERT INTO `em_data_constantload` VALUES ('68', '4', '999.1279', '3.7872', null, null);
INSERT INTO `em_data_constantload` VALUES ('69', '4', '999.5205', '4.1764', null, null);
INSERT INTO `em_data_constantload` VALUES ('70', '4', '999.7453', '3.7248', null, null);
INSERT INTO `em_data_constantload` VALUES ('71', '4', '999.2611', '4.1755', null, null);
INSERT INTO `em_data_constantload` VALUES ('72', '4', '999.4778', '4.5093', null, null);
INSERT INTO `em_data_constantload` VALUES ('73', '4', '999.0953', '5.0452', null, null);
INSERT INTO `em_data_constantload` VALUES ('74', '4', '999.1720', '5.0288', null, null);
INSERT INTO `em_data_constantload` VALUES ('75', '4', '999.9610', '5.0009', null, null);
INSERT INTO `em_data_constantload` VALUES ('76', '4', '999.0327', '5.1027', null, null);
INSERT INTO `em_data_constantload` VALUES ('77', '4', '999.2240', '4.9401', null, null);
INSERT INTO `em_data_constantload` VALUES ('78', '4', '999.1098', '4.8792', null, null);
INSERT INTO `em_data_constantload` VALUES ('79', '4', '999.2154', '5.0115', null, null);
INSERT INTO `em_data_constantload` VALUES ('80', '4', '999.0595', '4.3365', null, null);
INSERT INTO `em_data_constantload` VALUES ('81', '4', '999.9422', '4.5029', null, null);
INSERT INTO `em_data_constantload` VALUES ('82', '4', '999.4018', '4.6979', null, null);
INSERT INTO `em_data_constantload` VALUES ('83', '4', '999.6679', '4.4254', null, null);
INSERT INTO `em_data_constantload` VALUES ('84', '4', '1002.0725', '4.2849', null, null);
INSERT INTO `em_data_constantload` VALUES ('85', '4', '1002.4741', '3.8350', null, null);
INSERT INTO `em_data_constantload` VALUES ('86', '4', '1000.2944', '4.4968', null, null);
INSERT INTO `em_data_constantload` VALUES ('87', '4', '1000.8927', '4.9949', null, null);
INSERT INTO `em_data_constantload` VALUES ('88', '4', '1000.1258', '5.4269', null, null);
INSERT INTO `em_data_constantload` VALUES ('89', '4', '1000.4944', '5.0247', null, null);
INSERT INTO `em_data_constantload` VALUES ('90', '4', '1001.0644', '4.2289', null, null);
INSERT INTO `em_data_constantload` VALUES ('91', '4', '1000.1091', '4.5567', null, null);
INSERT INTO `em_data_constantload` VALUES ('92', '4', '1000.6256', '4.3596', null, null);
INSERT INTO `em_data_constantload` VALUES ('93', '4', '1001.6040', '4.5045', null, null);
INSERT INTO `em_data_constantload` VALUES ('94', '4', '999.2329', '4.8435', null, null);
INSERT INTO `em_data_constantload` VALUES ('95', '4', '999.2329', '4.9304', null, null);
INSERT INTO `em_data_constantload` VALUES ('96', '4', '1001.2146', '4.9701', null, null);
INSERT INTO `em_data_constantload` VALUES ('97', '4', '1000.1288', '4.8759', null, null);
INSERT INTO `em_data_constantload` VALUES ('98', '4', '999.9709', '4.9119', null, null);
INSERT INTO `em_data_constantload` VALUES ('99', '4', '1000.0554', '4.5706', null, null);
INSERT INTO `em_data_constantload` VALUES ('100', '4', '999.9481', '4.7238', null, null);
INSERT INTO `em_data_constantload` VALUES ('101', '4', '1000.2629', '4.1494', null, null);
INSERT INTO `em_data_constantload` VALUES ('102', '4', '1000.5556', '4.0452', null, null);
INSERT INTO `em_data_constantload` VALUES ('103', '4', '1000.1535', '3.7012', null, null);
INSERT INTO `em_data_constantload` VALUES ('104', '4', '999.8008', '4.4924', null, null);
INSERT INTO `em_data_constantload` VALUES ('105', '4', '1001.2355', '5.0821', null, null);
INSERT INTO `em_data_constantload` VALUES ('106', '4', '1000.8906', '4.1247', null, null);
INSERT INTO `em_data_constantload` VALUES ('107', '4', '999.9130', '4.4037', null, null);
INSERT INTO `em_data_constantload` VALUES ('108', '4', '1000.3857', '4.6764', null, null);
INSERT INTO `em_data_constantload` VALUES ('109', '4', '1001.5019', '3.9936', null, null);
INSERT INTO `em_data_constantload` VALUES ('110', '4', '1000.2942', '4.0845', null, null);
INSERT INTO `em_data_constantload` VALUES ('111', '4', '999.6331', '4.2987', null, null);
INSERT INTO `em_data_constantload` VALUES ('112', '3', '999.1000', '4.0101', '1000.0000', '4.0000');
INSERT INTO `em_data_constantload` VALUES ('113', '3', '1000.3135', '3.5604', null, null);
INSERT INTO `em_data_constantload` VALUES ('114', '3', '1001.9006', '3.6236', null, null);
INSERT INTO `em_data_constantload` VALUES ('115', '3', '1002.0031', '3.7274', null, null);
INSERT INTO `em_data_constantload` VALUES ('116', '3', '1003.9944', '4.3539', null, null);
INSERT INTO `em_data_constantload` VALUES ('117', '3', '998.3239', '3.6957', null, null);
INSERT INTO `em_data_constantload` VALUES ('118', '3', '997.5009', '4.4904', null, null);
INSERT INTO `em_data_constantload` VALUES ('119', '3', '993.8828', '4.4318', null, null);
INSERT INTO `em_data_constantload` VALUES ('120', '3', '999.3922', '3.8049', null, null);
INSERT INTO `em_data_constantload` VALUES ('121', '3', '999.1452', '3.9606', null, null);
INSERT INTO `em_data_constantload` VALUES ('122', '3', '980.1279', '3.6872', null, null);
INSERT INTO `em_data_constantload` VALUES ('123', '3', '986.5205', '4.1764', null, null);
INSERT INTO `em_data_constantload` VALUES ('124', '3', '987.7453', '3.7248', null, null);
INSERT INTO `em_data_constantload` VALUES ('125', '3', '988.2611', '4.1755', null, null);
INSERT INTO `em_data_constantload` VALUES ('126', '3', '989.4778', '4.5093', null, null);
INSERT INTO `em_data_constantload` VALUES ('127', '3', '991.0953', '5.0452', null, null);
INSERT INTO `em_data_constantload` VALUES ('128', '3', '997.1720', '5.0288', null, null);
INSERT INTO `em_data_constantload` VALUES ('129', '3', '990.9610', '6.0009', null, null);
INSERT INTO `em_data_constantload` VALUES ('130', '3', '990.0327', '5.1027', null, null);
INSERT INTO `em_data_constantload` VALUES ('131', '3', '991.2240', '4.9401', null, null);
INSERT INTO `em_data_constantload` VALUES ('132', '3', '992.1098', '4.8792', null, null);
INSERT INTO `em_data_constantload` VALUES ('133', '3', '999.2154', '5.0115', null, null);
INSERT INTO `em_data_constantload` VALUES ('134', '3', '987.0595', '4.3365', null, null);
INSERT INTO `em_data_constantload` VALUES ('135', '3', '999.9422', '4.5029', null, null);
INSERT INTO `em_data_constantload` VALUES ('136', '3', '999.4018', '4.6979', null, null);
INSERT INTO `em_data_constantload` VALUES ('137', '3', '998.6679', '4.4254', null, null);
INSERT INTO `em_data_constantload` VALUES ('138', '3', '1003.0725', '4.2849', null, null);
INSERT INTO `em_data_constantload` VALUES ('139', '3', '1002.4741', '3.8350', null, null);
INSERT INTO `em_data_constantload` VALUES ('140', '3', '1000.2944', '4.4968', null, null);
INSERT INTO `em_data_constantload` VALUES ('141', '3', '1000.8927', '4.9949', null, null);
INSERT INTO `em_data_constantload` VALUES ('142', '3', '1000.1258', '5.4269', null, null);
INSERT INTO `em_data_constantload` VALUES ('143', '3', '1000.4944', '5.0247', null, null);
INSERT INTO `em_data_constantload` VALUES ('144', '3', '1001.0644', '4.2289', null, null);
INSERT INTO `em_data_constantload` VALUES ('145', '3', '1000.1091', '4.5567', null, null);
INSERT INTO `em_data_constantload` VALUES ('146', '3', '1000.6256', '4.3596', null, null);
INSERT INTO `em_data_constantload` VALUES ('147', '3', '1003.6040', '4.5045', null, null);
INSERT INTO `em_data_constantload` VALUES ('148', '3', '998.2329', '4.8435', null, null);
INSERT INTO `em_data_constantload` VALUES ('149', '3', '998.2329', '4.9304', null, null);
INSERT INTO `em_data_constantload` VALUES ('150', '3', '1001.2146', '4.9701', null, null);
INSERT INTO `em_data_constantload` VALUES ('151', '3', '1000.1288', '4.8759', null, null);
INSERT INTO `em_data_constantload` VALUES ('152', '3', '999.9709', '4.9119', null, null);
INSERT INTO `em_data_constantload` VALUES ('153', '3', '1000.0554', '4.5706', null, null);
INSERT INTO `em_data_constantload` VALUES ('154', '3', '999.9481', '4.7238', null, null);
INSERT INTO `em_data_constantload` VALUES ('155', '3', '1000.2629', '4.1494', null, null);
INSERT INTO `em_data_constantload` VALUES ('156', '3', '1000.5556', '4.0452', null, null);
INSERT INTO `em_data_constantload` VALUES ('157', '3', '1000.1535', '3.7012', null, null);
INSERT INTO `em_data_constantload` VALUES ('158', '3', '999.8008', '4.4924', null, null);
INSERT INTO `em_data_constantload` VALUES ('159', '3', '1001.2355', '5.0821', null, null);
INSERT INTO `em_data_constantload` VALUES ('160', '3', '1000.8906', '4.1247', null, null);
INSERT INTO `em_data_constantload` VALUES ('161', '3', '999.9130', '4.4037', null, null);
INSERT INTO `em_data_constantload` VALUES ('162', '3', '1000.3857', '4.6764', null, null);
INSERT INTO `em_data_constantload` VALUES ('163', '3', '1001.5019', '3.9936', null, null);
INSERT INTO `em_data_constantload` VALUES ('164', '3', '1000.2942', '4.0845', null, null);
INSERT INTO `em_data_constantload` VALUES ('165', '3', '999.6331', '4.2987', null, null);

-- ----------------------------
-- Table structure for em_data_emptyload
-- ----------------------------
DROP TABLE IF EXISTS `em_data_emptyload`;
CREATE TABLE `em_data_emptyload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '所属项目ID',
  `speed_forward` decimal(15,4) DEFAULT NULL COMMENT '正向转速（r/min）',
  `speed_reverse` decimal(15,4) DEFAULT NULL COMMENT '反向转速（r/min）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_emptyload
-- ----------------------------
INSERT INTO `em_data_emptyload` VALUES ('1', '1', '0.0000', '0.0000');
INSERT INTO `em_data_emptyload` VALUES ('2', '1', '101.0000', '100.0000');
INSERT INTO `em_data_emptyload` VALUES ('3', '1', '270.0000', '270.0000');
INSERT INTO `em_data_emptyload` VALUES ('4', '1', '396.0000', '396.0000');
INSERT INTO `em_data_emptyload` VALUES ('5', '1', '463.0000', '464.0000');
INSERT INTO `em_data_emptyload` VALUES ('6', '1', '550.0000', '551.0000');
INSERT INTO `em_data_emptyload` VALUES ('7', '1', '676.0000', '677.0000');
INSERT INTO `em_data_emptyload` VALUES ('8', '1', '690.0000', '688.0000');
INSERT INTO `em_data_emptyload` VALUES ('9', '1', '719.0000', '719.0000');
INSERT INTO `em_data_emptyload` VALUES ('10', '1', '791.0000', '791.0000');
INSERT INTO `em_data_emptyload` VALUES ('11', '1', '838.0000', '837.0000');
INSERT INTO `em_data_emptyload` VALUES ('12', '1', '905.0000', '904.0000');
INSERT INTO `em_data_emptyload` VALUES ('13', '1', '935.0000', '935.0000');
INSERT INTO `em_data_emptyload` VALUES ('14', '1', '970.0000', '970.0000');
INSERT INTO `em_data_emptyload` VALUES ('15', '1', '1010.0000', '1011.0000');
INSERT INTO `em_data_emptyload` VALUES ('16', '1', '1081.0000', '1081.0000');
INSERT INTO `em_data_emptyload` VALUES ('17', '1', '1115.0000', '1114.0000');
INSERT INTO `em_data_emptyload` VALUES ('18', '1', '1127.0000', '1126.0000');
INSERT INTO `em_data_emptyload` VALUES ('19', '1', '1204.0000', '1203.0000');
INSERT INTO `em_data_emptyload` VALUES ('20', '1', '1231.0000', '1231.0000');
INSERT INTO `em_data_emptyload` VALUES ('21', '1', '1224.0000', '1226.0000');
INSERT INTO `em_data_emptyload` VALUES ('22', '1', '999.0000', '999.0000');
INSERT INTO `em_data_emptyload` VALUES ('23', '1', '1001.0000', '1000.0000');
INSERT INTO `em_data_emptyload` VALUES ('24', '1', '1000.0000', '999.0000');
INSERT INTO `em_data_emptyload` VALUES ('25', '1', '1000.0000', '999.0000');
INSERT INTO `em_data_emptyload` VALUES ('26', '1', '1001.0000', '1001.0000');
INSERT INTO `em_data_emptyload` VALUES ('27', '1', '1000.0000', '1000.0000');
INSERT INTO `em_data_emptyload` VALUES ('28', '2', '0.0000', '0.0000');
INSERT INTO `em_data_emptyload` VALUES ('29', '2', '101.0000', '100.0000');
INSERT INTO `em_data_emptyload` VALUES ('30', '2', '270.0000', '270.0000');
INSERT INTO `em_data_emptyload` VALUES ('31', '2', '396.0000', '396.0000');
INSERT INTO `em_data_emptyload` VALUES ('32', '2', '463.0000', '464.0000');
INSERT INTO `em_data_emptyload` VALUES ('33', '2', '550.0000', '551.0000');
INSERT INTO `em_data_emptyload` VALUES ('34', '2', '676.0000', '677.0000');
INSERT INTO `em_data_emptyload` VALUES ('35', '2', '690.0000', '688.0000');
INSERT INTO `em_data_emptyload` VALUES ('36', '2', '719.0000', '719.0000');
INSERT INTO `em_data_emptyload` VALUES ('37', '2', '791.0000', '791.0000');
INSERT INTO `em_data_emptyload` VALUES ('38', '2', '838.0000', '837.0000');
INSERT INTO `em_data_emptyload` VALUES ('39', '2', '905.0000', '904.0000');
INSERT INTO `em_data_emptyload` VALUES ('40', '2', '935.0000', '935.0000');
INSERT INTO `em_data_emptyload` VALUES ('41', '2', '970.0000', '970.0000');
INSERT INTO `em_data_emptyload` VALUES ('42', '2', '1010.0000', '1011.0000');
INSERT INTO `em_data_emptyload` VALUES ('43', '2', '1081.0000', '1081.0000');
INSERT INTO `em_data_emptyload` VALUES ('44', '2', '1115.0000', '1114.0000');
INSERT INTO `em_data_emptyload` VALUES ('45', '2', '1127.0000', '1126.0000');
INSERT INTO `em_data_emptyload` VALUES ('46', '2', '1204.0000', '1203.0000');
INSERT INTO `em_data_emptyload` VALUES ('47', '2', '1231.0000', '1231.0000');
INSERT INTO `em_data_emptyload` VALUES ('48', '2', '1224.0000', '1226.0000');
INSERT INTO `em_data_emptyload` VALUES ('49', '2', '999.0000', '999.0000');
INSERT INTO `em_data_emptyload` VALUES ('50', '2', '1001.0000', '1000.0000');
INSERT INTO `em_data_emptyload` VALUES ('51', '2', '1000.0000', '999.0000');
INSERT INTO `em_data_emptyload` VALUES ('52', '2', '1000.0000', '999.0000');
INSERT INTO `em_data_emptyload` VALUES ('53', '2', '1001.0000', '1001.0000');
INSERT INTO `em_data_emptyload` VALUES ('54', '2', '1000.0000', '1000.0000');
INSERT INTO `em_data_emptyload` VALUES ('109', '4', '0.0000', '0.0000');
INSERT INTO `em_data_emptyload` VALUES ('110', '4', '100.5040', '100.4422');
INSERT INTO `em_data_emptyload` VALUES ('111', '4', '270.3612', '270.1098');
INSERT INTO `em_data_emptyload` VALUES ('112', '4', '395.7295', '396.3569');
INSERT INTO `em_data_emptyload` VALUES ('113', '4', '463.2392', '464.1629');
INSERT INTO `em_data_emptyload` VALUES ('114', '4', '550.0070', '551.3739');
INSERT INTO `em_data_emptyload` VALUES ('115', '4', '676.1826', '676.7522');
INSERT INTO `em_data_emptyload` VALUES ('116', '4', '689.6781', '688.0547');
INSERT INTO `em_data_emptyload` VALUES ('117', '4', '718.9941', '718.7116');
INSERT INTO `em_data_emptyload` VALUES ('118', '4', '791.3030', '790.5414');
INSERT INTO `em_data_emptyload` VALUES ('119', '4', '837.5845', '837.1894');
INSERT INTO `em_data_emptyload` VALUES ('120', '4', '904.7127', '903.9335');
INSERT INTO `em_data_emptyload` VALUES ('121', '4', '935.0126', '935.4741');
INSERT INTO `em_data_emptyload` VALUES ('122', '4', '970.4914', '969.9375');
INSERT INTO `em_data_emptyload` VALUES ('123', '4', '1010.1731', '1010.6779');
INSERT INTO `em_data_emptyload` VALUES ('124', '4', '1080.9719', '1081.0765');
INSERT INTO `em_data_emptyload` VALUES ('125', '4', '1115.2211', '1114.0747');
INSERT INTO `em_data_emptyload` VALUES ('126', '4', '1126.6888', '1126.0415');
INSERT INTO `em_data_emptyload` VALUES ('127', '4', '1203.6349', '1203.0037');
INSERT INTO `em_data_emptyload` VALUES ('128', '4', '1231.0445', '1231.1796');
INSERT INTO `em_data_emptyload` VALUES ('129', '4', '1224.4960', '1225.8090');
INSERT INTO `em_data_emptyload` VALUES ('130', '4', '999.1101', '999.4436');
INSERT INTO `em_data_emptyload` VALUES ('131', '4', '1000.5020', '999.5489');
INSERT INTO `em_data_emptyload` VALUES ('132', '4', '999.7072', '999.1826');
INSERT INTO `em_data_emptyload` VALUES ('133', '4', '1000.2566', '999.3149');
INSERT INTO `em_data_emptyload` VALUES ('134', '4', '1000.8841', '1000.9258');
INSERT INTO `em_data_emptyload` VALUES ('135', '4', '999.8143', '1000.1755');
INSERT INTO `em_data_emptyload` VALUES ('136', '3', '0.0000', '0.0000');
INSERT INTO `em_data_emptyload` VALUES ('137', '3', '100.5040', '100.4422');
INSERT INTO `em_data_emptyload` VALUES ('138', '3', '270.3612', '270.1098');
INSERT INTO `em_data_emptyload` VALUES ('139', '3', '395.7295', '396.3569');
INSERT INTO `em_data_emptyload` VALUES ('140', '3', '463.2392', '464.1629');
INSERT INTO `em_data_emptyload` VALUES ('141', '3', '550.0070', '551.3739');
INSERT INTO `em_data_emptyload` VALUES ('142', '3', '676.1826', '676.7522');
INSERT INTO `em_data_emptyload` VALUES ('143', '3', '689.6781', '688.0547');
INSERT INTO `em_data_emptyload` VALUES ('144', '3', '718.9941', '718.7116');
INSERT INTO `em_data_emptyload` VALUES ('145', '3', '791.3030', '790.5414');
INSERT INTO `em_data_emptyload` VALUES ('146', '3', '837.5845', '837.1894');
INSERT INTO `em_data_emptyload` VALUES ('147', '3', '904.7127', '903.9335');
INSERT INTO `em_data_emptyload` VALUES ('148', '3', '935.0126', '935.4741');
INSERT INTO `em_data_emptyload` VALUES ('149', '3', '970.4914', '969.9375');
INSERT INTO `em_data_emptyload` VALUES ('150', '3', '1010.1731', '1010.6779');
INSERT INTO `em_data_emptyload` VALUES ('151', '3', '1080.9719', '1081.0765');
INSERT INTO `em_data_emptyload` VALUES ('152', '3', '1115.2211', '1114.0747');
INSERT INTO `em_data_emptyload` VALUES ('153', '3', '1126.6888', '1126.0415');
INSERT INTO `em_data_emptyload` VALUES ('154', '3', '1203.6349', '1203.0037');
INSERT INTO `em_data_emptyload` VALUES ('155', '3', '1231.0445', '1231.1796');
INSERT INTO `em_data_emptyload` VALUES ('156', '3', '1224.4960', '1225.8090');
INSERT INTO `em_data_emptyload` VALUES ('157', '3', '999.1101', '999.4436');
INSERT INTO `em_data_emptyload` VALUES ('158', '3', '1000.5020', '999.5489');
INSERT INTO `em_data_emptyload` VALUES ('159', '3', '999.7072', '999.1826');
INSERT INTO `em_data_emptyload` VALUES ('160', '3', '1000.2566', '999.3149');
INSERT INTO `em_data_emptyload` VALUES ('161', '3', '1000.8841', '1000.9258');
INSERT INTO `em_data_emptyload` VALUES ('162', '3', '999.8143', '1000.1755');

-- ----------------------------
-- Table structure for em_data_overload
-- ----------------------------
DROP TABLE IF EXISTS `em_data_overload`;
CREATE TABLE `em_data_overload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '所属项目ID',
  `speed_empty` decimal(15,4) DEFAULT NULL COMMENT '空载转速（r/min）',
  `speed_fixed_load` decimal(15,4) DEFAULT NULL COMMENT '额定负载转速（r/min）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_overload
-- ----------------------------
INSERT INTO `em_data_overload` VALUES ('1', '1', '1000.0000', '988.0000');
INSERT INTO `em_data_overload` VALUES ('2', '2', '1000.0000', '988.0000');
INSERT INTO `em_data_overload` VALUES ('5', '4', '1000.3000', '988.4000');
INSERT INTO `em_data_overload` VALUES ('6', '3', '1000.3000', '988.4000');

-- ----------------------------
-- Table structure for em_data_sin
-- ----------------------------
DROP TABLE IF EXISTS `em_data_sin`;
CREATE TABLE `em_data_sin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '所属项目ID',
  `speed_10` decimal(15,4) DEFAULT NULL COMMENT '转速（r/min）10个值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_sin
-- ----------------------------
INSERT INTO `em_data_sin` VALUES ('1', '1', '609.0000');
INSERT INTO `em_data_sin` VALUES ('2', '1', '1002.0000');
INSERT INTO `em_data_sin` VALUES ('3', '1', '1078.0000');
INSERT INTO `em_data_sin` VALUES ('4', '1', '1185.0000');
INSERT INTO `em_data_sin` VALUES ('5', '1', '1195.0000');
INSERT INTO `em_data_sin` VALUES ('6', '1', '1206.0000');
INSERT INTO `em_data_sin` VALUES ('7', '1', '984.0000');
INSERT INTO `em_data_sin` VALUES ('8', '1', '998.0000');
INSERT INTO `em_data_sin` VALUES ('9', '1', '1000.0000');
INSERT INTO `em_data_sin` VALUES ('10', '1', '983.0000');
INSERT INTO `em_data_sin` VALUES ('11', '2', '609.0000');
INSERT INTO `em_data_sin` VALUES ('12', '2', '1002.0000');
INSERT INTO `em_data_sin` VALUES ('13', '2', '1078.0000');
INSERT INTO `em_data_sin` VALUES ('14', '2', '1185.0000');
INSERT INTO `em_data_sin` VALUES ('15', '2', '1195.0000');
INSERT INTO `em_data_sin` VALUES ('16', '2', '1206.0000');
INSERT INTO `em_data_sin` VALUES ('17', '2', '984.0000');
INSERT INTO `em_data_sin` VALUES ('18', '2', '998.0000');
INSERT INTO `em_data_sin` VALUES ('19', '2', '1000.0000');
INSERT INTO `em_data_sin` VALUES ('20', '2', '983.0000');
INSERT INTO `em_data_sin` VALUES ('41', '4', '1004.2000');
INSERT INTO `em_data_sin` VALUES ('42', '4', '1002.0000');
INSERT INTO `em_data_sin` VALUES ('43', '4', '1002.3000');
INSERT INTO `em_data_sin` VALUES ('44', '4', '1000.7000');
INSERT INTO `em_data_sin` VALUES ('45', '4', '1000.1000');
INSERT INTO `em_data_sin` VALUES ('46', '4', '1003.0000');
INSERT INTO `em_data_sin` VALUES ('47', '4', '999.2000');
INSERT INTO `em_data_sin` VALUES ('48', '4', '998.3000');
INSERT INTO `em_data_sin` VALUES ('49', '4', '1000.2000');
INSERT INTO `em_data_sin` VALUES ('50', '4', '999.4000');
INSERT INTO `em_data_sin` VALUES ('51', '3', '609.2000');
INSERT INTO `em_data_sin` VALUES ('52', '3', '1002.0000');
INSERT INTO `em_data_sin` VALUES ('53', '3', '1078.3000');
INSERT INTO `em_data_sin` VALUES ('54', '3', '1184.7000');
INSERT INTO `em_data_sin` VALUES ('55', '3', '1195.1000');
INSERT INTO `em_data_sin` VALUES ('56', '3', '1206.0000');
INSERT INTO `em_data_sin` VALUES ('57', '3', '984.2000');
INSERT INTO `em_data_sin` VALUES ('58', '3', '998.3000');
INSERT INTO `em_data_sin` VALUES ('59', '3', '1000.2000');
INSERT INTO `em_data_sin` VALUES ('60', '3', '983.4000');

-- ----------------------------
-- Table structure for em_data_speed
-- ----------------------------
DROP TABLE IF EXISTS `em_data_speed`;
CREATE TABLE `em_data_speed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '所属项目ID',
  `speed_max` decimal(15,4) DEFAULT NULL COMMENT '最大转速（r/min）',
  `speed_min` decimal(15,4) DEFAULT NULL COMMENT '最小转速（r/min）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_speed
-- ----------------------------
INSERT INTO `em_data_speed` VALUES ('1', '1', '1000.0000', '100.0000');
INSERT INTO `em_data_speed` VALUES ('2', '2', '1000.0000', '100.0000');
INSERT INTO `em_data_speed` VALUES ('5', '4', '1000.0000', '100.0000');
INSERT INTO `em_data_speed` VALUES ('6', '3', '1000.0000', '100.0000');

-- ----------------------------
-- Table structure for em_data_step
-- ----------------------------
DROP TABLE IF EXISTS `em_data_step`;
CREATE TABLE `em_data_step` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '所属项目ID',
  `data_time` decimal(15,4) DEFAULT NULL COMMENT '数据时间(ms)',
  `speed` decimal(15,4) DEFAULT NULL COMMENT '转速',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1597 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_step
-- ----------------------------
INSERT INTO `em_data_step` VALUES ('1', '1', '0.0000', '0.0000');
INSERT INTO `em_data_step` VALUES ('2', '1', '3.0000', '100.0000');
INSERT INTO `em_data_step` VALUES ('3', '1', '7.0000', '330.0000');
INSERT INTO `em_data_step` VALUES ('4', '1', '8.0000', '411.0000');
INSERT INTO `em_data_step` VALUES ('5', '1', '11.0000', '598.0000');
INSERT INTO `em_data_step` VALUES ('6', '1', '12.0000', '674.0000');
INSERT INTO `em_data_step` VALUES ('7', '1', '12.0000', '675.0000');
INSERT INTO `em_data_step` VALUES ('8', '1', '14.0000', '794.0000');
INSERT INTO `em_data_step` VALUES ('9', '1', '15.0000', '850.0000');
INSERT INTO `em_data_step` VALUES ('10', '1', '16.0000', '851.0000');
INSERT INTO `em_data_step` VALUES ('11', '1', '17.0000', '957.0000');
INSERT INTO `em_data_step` VALUES ('12', '1', '18.0000', '958.0000');
INSERT INTO `em_data_step` VALUES ('13', '1', '18.0000', '1008.0000');
INSERT INTO `em_data_step` VALUES ('14', '1', '18.0000', '1009.0000');
INSERT INTO `em_data_step` VALUES ('15', '1', '21.0000', '1111.0000');
INSERT INTO `em_data_step` VALUES ('16', '1', '22.0000', '1180.0000');
INSERT INTO `em_data_step` VALUES ('17', '1', '22.0000', '1181.0000');
INSERT INTO `em_data_step` VALUES ('18', '1', '24.0000', '1212.0000');
INSERT INTO `em_data_step` VALUES ('19', '1', '25.0000', '1214.0000');
INSERT INTO `em_data_step` VALUES ('20', '1', '26.0000', '1214.0000');
INSERT INTO `em_data_step` VALUES ('21', '1', '27.0000', '1209.0000');
INSERT INTO `em_data_step` VALUES ('22', '1', '28.0000', '1202.0000');
INSERT INTO `em_data_step` VALUES ('23', '1', '28.0000', '1202.0000');
INSERT INTO `em_data_step` VALUES ('24', '1', '30.0000', '1184.0000');
INSERT INTO `em_data_step` VALUES ('25', '1', '30.0000', '1184.0000');
INSERT INTO `em_data_step` VALUES ('26', '1', '32.0000', '1163.0000');
INSERT INTO `em_data_step` VALUES ('27', '1', '32.0000', '1163.0000');
INSERT INTO `em_data_step` VALUES ('28', '1', '34.0000', '1139.0000');
INSERT INTO `em_data_step` VALUES ('29', '1', '34.0000', '1139.0000');
INSERT INTO `em_data_step` VALUES ('30', '1', '36.0000', '1128.0000');
INSERT INTO `em_data_step` VALUES ('31', '1', '37.0000', '1104.0000');
INSERT INTO `em_data_step` VALUES ('32', '1', '38.0000', '1104.0000');
INSERT INTO `em_data_step` VALUES ('33', '1', '39.0000', '1082.0000');
INSERT INTO `em_data_step` VALUES ('34', '1', '40.0000', '1082.0000');
INSERT INTO `em_data_step` VALUES ('35', '1', '40.0000', '1063.0000');
INSERT INTO `em_data_step` VALUES ('36', '1', '41.0000', '1063.0000');
INSERT INTO `em_data_step` VALUES ('37', '1', '42.0000', '1047.0000');
INSERT INTO `em_data_step` VALUES ('38', '1', '43.0000', '1047.0000');
INSERT INTO `em_data_step` VALUES ('39', '1', '44.0000', '1033.0000');
INSERT INTO `em_data_step` VALUES ('40', '1', '45.0000', '1033.0000');
INSERT INTO `em_data_step` VALUES ('41', '1', '46.0000', '1025.0000');
INSERT INTO `em_data_step` VALUES ('42', '1', '47.0000', '1025.0000');
INSERT INTO `em_data_step` VALUES ('43', '1', '48.0000', '1021.0000');
INSERT INTO `em_data_step` VALUES ('44', '1', '50.0000', '1015.0000');
INSERT INTO `em_data_step` VALUES ('45', '1', '50.0000', '1015.0000');
INSERT INTO `em_data_step` VALUES ('46', '1', '52.0000', '1009.0000');
INSERT INTO `em_data_step` VALUES ('47', '1', '52.0000', '1009.0000');
INSERT INTO `em_data_step` VALUES ('48', '1', '54.0000', '1005.0000');
INSERT INTO `em_data_step` VALUES ('49', '1', '55.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('50', '1', '56.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('51', '1', '56.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('52', '1', '58.0000', '997.0000');
INSERT INTO `em_data_step` VALUES ('53', '1', '58.0000', '997.0000');
INSERT INTO `em_data_step` VALUES ('54', '1', '60.0000', '997.0000');
INSERT INTO `em_data_step` VALUES ('55', '1', '60.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('56', '1', '62.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('57', '1', '65.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('58', '1', '66.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('59', '1', '66.0000', '996.0000');
INSERT INTO `em_data_step` VALUES ('60', '1', '68.0000', '996.0000');
INSERT INTO `em_data_step` VALUES ('61', '1', '68.0000', '996.0000');
INSERT INTO `em_data_step` VALUES ('62', '1', '70.0000', '998.0000');
INSERT INTO `em_data_step` VALUES ('63', '1', '71.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('64', '1', '72.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('65', '1', '73.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('66', '1', '74.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('67', '1', '75.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('68', '1', '76.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('69', '1', '77.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('70', '1', '79.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('71', '1', '80.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('72', '1', '81.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('73', '1', '82.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('74', '1', '83.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('75', '1', '84.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('76', '1', '84.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('77', '1', '86.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('78', '1', '86.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('79', '1', '88.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('80', '1', '89.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('81', '1', '90.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('82', '1', '92.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('83', '1', '93.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('84', '1', '94.0000', '1003.0000');
INSERT INTO `em_data_step` VALUES ('85', '1', '95.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('86', '1', '96.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('87', '1', '97.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('88', '1', '98.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('89', '1', '98.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('90', '1', '98.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('91', '1', '100.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('92', '1', '100.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('93', '1', '102.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('94', '1', '103.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('95', '1', '104.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('96', '1', '104.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('97', '1', '106.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('98', '1', '106.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('99', '1', '108.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('100', '1', '108.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('101', '1', '110.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('102', '1', '111.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('103', '1', '112.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('104', '1', '113.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('105', '1', '114.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('106', '1', '114.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('107', '1', '114.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('108', '1', '116.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('109', '1', '116.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('110', '1', '118.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('111', '1', '119.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('112', '1', '120.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('113', '1', '121.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('114', '1', '122.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('115', '1', '123.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('116', '1', '124.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('117', '1', '125.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('118', '1', '126.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('119', '1', '127.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('120', '1', '128.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('121', '1', '129.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('122', '1', '130.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('123', '1', '130.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('124', '1', '132.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('125', '1', '135.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('126', '1', '136.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('127', '1', '136.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('128', '1', '136.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('129', '1', '138.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('130', '1', '138.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('131', '1', '141.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('132', '1', '142.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('133', '1', '143.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('134', '1', '144.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('135', '1', '145.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('136', '1', '146.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('137', '1', '147.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('138', '1', '148.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('139', '1', '149.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('140', '1', '150.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('141', '1', '150.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('142', '1', '152.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('143', '1', '152.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('144', '1', '154.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('145', '1', '154.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('146', '1', '156.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('147', '1', '156.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('148', '1', '158.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('149', '1', '158.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('150', '1', '160.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('151', '1', '161.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('152', '1', '162.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('153', '1', '162.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('154', '1', '164.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('155', '1', '164.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('156', '1', '166.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('157', '1', '167.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('158', '1', '168.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('159', '1', '168.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('160', '1', '169.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('161', '1', '170.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('162', '1', '171.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('163', '1', '172.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('164', '1', '173.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('165', '1', '174.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('166', '1', '175.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('167', '1', '176.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('168', '1', '189.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('169', '1', '193.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('170', '1', '196.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('171', '1', '198.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('172', '1', '199.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('173', '1', '200.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('174', '1', '201.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('175', '1', '203.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('176', '1', '204.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('177', '1', '204.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('178', '1', '205.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('179', '1', '206.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('180', '1', '209.0000', '998.0000');
INSERT INTO `em_data_step` VALUES ('181', '1', '210.0000', '998.0000');
INSERT INTO `em_data_step` VALUES ('182', '1', '211.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('183', '1', '212.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('184', '1', '214.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('185', '1', '214.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('186', '1', '215.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('187', '1', '216.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('188', '1', '217.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('189', '1', '218.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('190', '1', '219.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('191', '1', '220.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('192', '1', '220.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('193', '1', '222.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('194', '1', '222.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('195', '1', '224.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('196', '1', '224.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('197', '1', '226.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('198', '1', '226.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('199', '1', '228.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('200', '1', '228.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('201', '1', '230.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('202', '1', '230.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('203', '1', '232.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('204', '1', '232.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('205', '1', '234.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('206', '1', '234.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('207', '1', '238.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('208', '1', '241.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('209', '1', '242.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('210', '1', '242.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('211', '1', '244.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('212', '1', '244.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('213', '1', '246.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('214', '1', '247.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('215', '1', '248.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('216', '1', '249.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('217', '1', '250.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('218', '1', '251.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('219', '1', '252.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('220', '1', '253.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('221', '1', '254.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('222', '1', '255.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('223', '1', '256.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('224', '1', '257.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('225', '1', '258.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('226', '1', '259.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('227', '1', '260.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('228', '1', '261.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('229', '1', '262.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('230', '1', '263.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('231', '1', '264.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('232', '1', '265.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('233', '1', '266.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('234', '1', '268.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('235', '1', '268.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('236', '1', '270.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('237', '1', '270.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('238', '1', '272.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('239', '1', '272.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('240', '1', '274.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('241', '1', '274.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('242', '1', '276.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('243', '1', '276.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('244', '1', '278.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('245', '1', '278.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('246', '1', '280.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('247', '1', '280.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('248', '1', '282.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('249', '1', '283.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('250', '1', '284.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('251', '1', '285.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('252', '1', '286.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('253', '1', '287.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('254', '1', '288.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('255', '1', '289.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('256', '1', '290.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('257', '1', '291.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('258', '1', '292.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('259', '1', '293.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('260', '1', '294.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('261', '1', '295.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('262', '1', '296.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('263', '1', '297.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('264', '1', '298.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('265', '1', '299.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('266', '1', '300.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('267', '2', '0.0000', '0.0000');
INSERT INTO `em_data_step` VALUES ('268', '2', '2.0000', '100.0000');
INSERT INTO `em_data_step` VALUES ('269', '2', '7.0000', '330.0000');
INSERT INTO `em_data_step` VALUES ('270', '2', '8.0000', '411.0000');
INSERT INTO `em_data_step` VALUES ('271', '2', '11.0000', '598.0000');
INSERT INTO `em_data_step` VALUES ('272', '2', '12.0000', '674.0000');
INSERT INTO `em_data_step` VALUES ('273', '2', '12.0000', '675.0000');
INSERT INTO `em_data_step` VALUES ('274', '2', '14.0000', '794.0000');
INSERT INTO `em_data_step` VALUES ('275', '2', '15.0000', '850.0000');
INSERT INTO `em_data_step` VALUES ('276', '2', '16.0000', '851.0000');
INSERT INTO `em_data_step` VALUES ('277', '2', '17.0000', '957.0000');
INSERT INTO `em_data_step` VALUES ('278', '2', '18.0000', '958.0000');
INSERT INTO `em_data_step` VALUES ('279', '2', '18.0000', '1008.0000');
INSERT INTO `em_data_step` VALUES ('280', '2', '18.0000', '1009.0000');
INSERT INTO `em_data_step` VALUES ('281', '2', '21.0000', '1111.0000');
INSERT INTO `em_data_step` VALUES ('282', '2', '22.0000', '1180.0000');
INSERT INTO `em_data_step` VALUES ('283', '2', '22.0000', '1181.0000');
INSERT INTO `em_data_step` VALUES ('284', '2', '24.0000', '1212.0000');
INSERT INTO `em_data_step` VALUES ('285', '2', '25.0000', '1214.0000');
INSERT INTO `em_data_step` VALUES ('286', '2', '26.0000', '1214.0000');
INSERT INTO `em_data_step` VALUES ('287', '2', '27.0000', '1209.0000');
INSERT INTO `em_data_step` VALUES ('288', '2', '28.0000', '1202.0000');
INSERT INTO `em_data_step` VALUES ('289', '2', '28.0000', '1202.0000');
INSERT INTO `em_data_step` VALUES ('290', '2', '30.0000', '1184.0000');
INSERT INTO `em_data_step` VALUES ('291', '2', '30.0000', '1184.0000');
INSERT INTO `em_data_step` VALUES ('292', '2', '32.0000', '1163.0000');
INSERT INTO `em_data_step` VALUES ('293', '2', '32.0000', '1163.0000');
INSERT INTO `em_data_step` VALUES ('294', '2', '34.0000', '1139.0000');
INSERT INTO `em_data_step` VALUES ('295', '2', '34.0000', '1139.0000');
INSERT INTO `em_data_step` VALUES ('296', '2', '36.0000', '1128.0000');
INSERT INTO `em_data_step` VALUES ('297', '2', '37.0000', '1104.0000');
INSERT INTO `em_data_step` VALUES ('298', '2', '38.0000', '1104.0000');
INSERT INTO `em_data_step` VALUES ('299', '2', '39.0000', '1082.0000');
INSERT INTO `em_data_step` VALUES ('300', '2', '40.0000', '1082.0000');
INSERT INTO `em_data_step` VALUES ('301', '2', '40.0000', '1063.0000');
INSERT INTO `em_data_step` VALUES ('302', '2', '41.0000', '1063.0000');
INSERT INTO `em_data_step` VALUES ('303', '2', '42.0000', '1047.0000');
INSERT INTO `em_data_step` VALUES ('304', '2', '43.0000', '1047.0000');
INSERT INTO `em_data_step` VALUES ('305', '2', '44.0000', '1033.0000');
INSERT INTO `em_data_step` VALUES ('306', '2', '45.0000', '1033.0000');
INSERT INTO `em_data_step` VALUES ('307', '2', '46.0000', '1025.0000');
INSERT INTO `em_data_step` VALUES ('308', '2', '47.0000', '1025.0000');
INSERT INTO `em_data_step` VALUES ('309', '2', '48.0000', '1021.0000');
INSERT INTO `em_data_step` VALUES ('310', '2', '50.0000', '1015.0000');
INSERT INTO `em_data_step` VALUES ('311', '2', '50.0000', '1015.0000');
INSERT INTO `em_data_step` VALUES ('312', '2', '52.0000', '1009.0000');
INSERT INTO `em_data_step` VALUES ('313', '2', '52.0000', '1009.0000');
INSERT INTO `em_data_step` VALUES ('314', '2', '54.0000', '1005.0000');
INSERT INTO `em_data_step` VALUES ('315', '2', '55.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('316', '2', '56.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('317', '2', '56.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('318', '2', '58.0000', '997.0000');
INSERT INTO `em_data_step` VALUES ('319', '2', '58.0000', '997.0000');
INSERT INTO `em_data_step` VALUES ('320', '2', '60.0000', '997.0000');
INSERT INTO `em_data_step` VALUES ('321', '2', '60.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('322', '2', '62.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('323', '2', '65.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('324', '2', '66.0000', '995.0000');
INSERT INTO `em_data_step` VALUES ('325', '2', '66.0000', '996.0000');
INSERT INTO `em_data_step` VALUES ('326', '2', '68.0000', '996.0000');
INSERT INTO `em_data_step` VALUES ('327', '2', '68.0000', '996.0000');
INSERT INTO `em_data_step` VALUES ('328', '2', '70.0000', '998.0000');
INSERT INTO `em_data_step` VALUES ('329', '2', '71.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('330', '2', '72.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('331', '2', '73.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('332', '2', '74.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('333', '2', '75.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('334', '2', '76.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('335', '2', '77.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('336', '2', '79.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('337', '2', '80.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('338', '2', '81.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('339', '2', '82.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('340', '2', '83.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('341', '2', '84.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('342', '2', '84.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('343', '2', '86.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('344', '2', '86.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('345', '2', '88.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('346', '2', '89.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('347', '2', '90.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('348', '2', '92.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('349', '2', '93.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('350', '2', '94.0000', '1003.0000');
INSERT INTO `em_data_step` VALUES ('351', '2', '95.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('352', '2', '96.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('353', '2', '97.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('354', '2', '98.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('355', '2', '98.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('356', '2', '98.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('357', '2', '100.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('358', '2', '100.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('359', '2', '102.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('360', '2', '103.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('361', '2', '104.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('362', '2', '104.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('363', '2', '106.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('364', '2', '106.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('365', '2', '108.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('366', '2', '108.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('367', '2', '110.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('368', '2', '111.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('369', '2', '112.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('370', '2', '113.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('371', '2', '114.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('372', '2', '114.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('373', '2', '114.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('374', '2', '116.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('375', '2', '116.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('376', '2', '118.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('377', '2', '119.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('378', '2', '120.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('379', '2', '121.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('380', '2', '122.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('381', '2', '123.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('382', '2', '124.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('383', '2', '125.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('384', '2', '126.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('385', '2', '127.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('386', '2', '128.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('387', '2', '129.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('388', '2', '130.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('389', '2', '130.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('390', '2', '132.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('391', '2', '135.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('392', '2', '136.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('393', '2', '136.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('394', '2', '136.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('395', '2', '138.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('396', '2', '138.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('397', '2', '141.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('398', '2', '142.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('399', '2', '143.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('400', '2', '144.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('401', '2', '145.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('402', '2', '146.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('403', '2', '147.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('404', '2', '148.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('405', '2', '149.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('406', '2', '150.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('407', '2', '150.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('408', '2', '152.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('409', '2', '152.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('410', '2', '154.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('411', '2', '154.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('412', '2', '156.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('413', '2', '156.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('414', '2', '158.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('415', '2', '158.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('416', '2', '160.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('417', '2', '161.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('418', '2', '162.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('419', '2', '162.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('420', '2', '164.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('421', '2', '164.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('422', '2', '166.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('423', '2', '167.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('424', '2', '168.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('425', '2', '168.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('426', '2', '169.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('427', '2', '170.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('428', '2', '171.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('429', '2', '172.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('430', '2', '173.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('431', '2', '174.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('432', '2', '175.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('433', '2', '176.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('434', '2', '189.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('435', '2', '193.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('436', '2', '196.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('437', '2', '198.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('438', '2', '199.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('439', '2', '200.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('440', '2', '201.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('441', '2', '203.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('442', '2', '204.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('443', '2', '204.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('444', '2', '205.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('445', '2', '206.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('446', '2', '209.0000', '998.0000');
INSERT INTO `em_data_step` VALUES ('447', '2', '210.0000', '998.0000');
INSERT INTO `em_data_step` VALUES ('448', '2', '211.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('449', '2', '212.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('450', '2', '214.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('451', '2', '214.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('452', '2', '215.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('453', '2', '216.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('454', '2', '217.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('455', '2', '218.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('456', '2', '219.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('457', '2', '220.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('458', '2', '220.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('459', '2', '222.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('460', '2', '222.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('461', '2', '224.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('462', '2', '224.0000', '1002.0000');
INSERT INTO `em_data_step` VALUES ('463', '2', '226.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('464', '2', '226.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('465', '2', '228.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('466', '2', '228.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('467', '2', '230.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('468', '2', '230.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('469', '2', '232.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('470', '2', '232.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('471', '2', '234.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('472', '2', '234.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('473', '2', '238.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('474', '2', '241.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('475', '2', '242.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('476', '2', '242.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('477', '2', '244.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('478', '2', '244.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('479', '2', '246.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('480', '2', '247.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('481', '2', '248.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('482', '2', '249.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('483', '2', '250.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('484', '2', '251.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('485', '2', '252.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('486', '2', '253.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('487', '2', '254.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('488', '2', '255.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('489', '2', '256.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('490', '2', '257.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('491', '2', '258.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('492', '2', '259.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('493', '2', '260.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('494', '2', '261.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('495', '2', '262.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('496', '2', '263.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('497', '2', '264.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('498', '2', '265.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('499', '2', '266.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('500', '2', '268.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('501', '2', '268.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('502', '2', '270.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('503', '2', '270.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('504', '2', '272.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('505', '2', '272.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('506', '2', '274.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('507', '2', '274.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('508', '2', '276.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('509', '2', '276.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('510', '2', '278.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('511', '2', '278.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('512', '2', '280.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('513', '2', '280.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('514', '2', '282.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('515', '2', '283.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('516', '2', '284.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('517', '2', '285.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('518', '2', '286.0000', '999.0000');
INSERT INTO `em_data_step` VALUES ('519', '2', '287.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('520', '2', '288.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('521', '2', '289.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('522', '2', '290.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('523', '2', '291.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('524', '2', '292.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('525', '2', '293.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('526', '2', '294.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('527', '2', '295.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('528', '2', '296.0000', '1000.0000');
INSERT INTO `em_data_step` VALUES ('529', '2', '297.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('530', '2', '298.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('531', '2', '299.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('532', '2', '300.0000', '1001.0000');
INSERT INTO `em_data_step` VALUES ('1065', '4', '0.0000', '0.0000');
INSERT INTO `em_data_step` VALUES ('1066', '4', '2.8700', '100.1087');
INSERT INTO `em_data_step` VALUES ('1067', '4', '7.0200', '330.1883');
INSERT INTO `em_data_step` VALUES ('1068', '4', '8.3600', '410.5946');
INSERT INTO `em_data_step` VALUES ('1069', '4', '11.3002', '597.6555');
INSERT INTO `em_data_step` VALUES ('1070', '4', '12.4900', '674.4049');
INSERT INTO `em_data_step` VALUES ('1071', '4', '12.5000', '675.0540');
INSERT INTO `em_data_step` VALUES ('1072', '4', '14.4900', '793.6980');
INSERT INTO `em_data_step` VALUES ('1073', '4', '15.4900', '850.0369');
INSERT INTO `em_data_step` VALUES ('1074', '4', '15.5000', '850.6192');
INSERT INTO `em_data_step` VALUES ('1075', '4', '17.4900', '957.0879');
INSERT INTO `em_data_step` VALUES ('1076', '4', '17.5000', '957.5882');
INSERT INTO `em_data_step` VALUES ('1077', '4', '18.4900', '1008.4573');
INSERT INTO `em_data_step` VALUES ('1078', '4', '18.5000', '1008.9651');
INSERT INTO `em_data_step` VALUES ('1079', '4', '20.6500', '1111.3642');
INSERT INTO `em_data_step` VALUES ('1080', '4', '22.4900', '1180.3107');
INSERT INTO `em_data_step` VALUES ('1081', '4', '22.5000', '1180.5974');
INSERT INTO `em_data_step` VALUES ('1082', '4', '24.4900', '1211.8142');
INSERT INTO `em_data_step` VALUES ('1083', '4', '25.4900', '1214.4557');
INSERT INTO `em_data_step` VALUES ('1084', '4', '25.5000', '1214.4572');
INSERT INTO `em_data_step` VALUES ('1085', '4', '27.4900', '1209.0326');
INSERT INTO `em_data_step` VALUES ('1086', '4', '28.4900', '1202.4135');
INSERT INTO `em_data_step` VALUES ('1087', '4', '28.5000', '1202.3474');
INSERT INTO `em_data_step` VALUES ('1088', '4', '30.4900', '1184.1952');
INSERT INTO `em_data_step` VALUES ('1089', '4', '30.5000', '1184.0996');
INSERT INTO `em_data_step` VALUES ('1090', '4', '32.4900', '1162.8002');
INSERT INTO `em_data_step` VALUES ('1091', '4', '32.5000', '1162.6723');
INSERT INTO `em_data_step` VALUES ('1092', '4', '34.4942', '1139.3353');
INSERT INTO `em_data_step` VALUES ('1093', '4', '34.5000', '1139.2509');
INSERT INTO `em_data_step` VALUES ('1094', '4', '35.5000', '1127.8025');
INSERT INTO `em_data_step` VALUES ('1095', '4', '37.4944', '1103.6958');
INSERT INTO `em_data_step` VALUES ('1096', '4', '37.5000', '1103.6457');
INSERT INTO `em_data_step` VALUES ('1097', '4', '39.4900', '1081.6261');
INSERT INTO `em_data_step` VALUES ('1098', '4', '39.5000', '1081.5418');
INSERT INTO `em_data_step` VALUES ('1099', '4', '40.0000', '1063.2484');
INSERT INTO `em_data_step` VALUES ('1100', '4', '41.0000', '1063.1501');
INSERT INTO `em_data_step` VALUES ('1101', '4', '42.0000', '1046.6015');
INSERT INTO `em_data_step` VALUES ('1102', '4', '43.0000', '1046.5544');
INSERT INTO `em_data_step` VALUES ('1103', '4', '44.0000', '1032.7445');
INSERT INTO `em_data_step` VALUES ('1104', '4', '45.0000', '1032.6941');
INSERT INTO `em_data_step` VALUES ('1105', '4', '46.0000', '1025.1609');
INSERT INTO `em_data_step` VALUES ('1106', '4', '47.0000', '1025.0936');
INSERT INTO `em_data_step` VALUES ('1107', '4', '48.0000', '1020.9909');
INSERT INTO `em_data_step` VALUES ('1108', '4', '50.4900', '1014.7925');
INSERT INTO `em_data_step` VALUES ('1109', '4', '50.5000', '1014.7623');
INSERT INTO `em_data_step` VALUES ('1110', '4', '52.4900', '1009.3071');
INSERT INTO `em_data_step` VALUES ('1111', '4', '52.5000', '1009.2575');
INSERT INTO `em_data_step` VALUES ('1112', '4', '53.5517', '1005.1345');
INSERT INTO `em_data_step` VALUES ('1113', '4', '55.4969', '1000.1641');
INSERT INTO `em_data_step` VALUES ('1114', '4', '55.5000', '1000.1584');
INSERT INTO `em_data_step` VALUES ('1115', '4', '56.5000', '999.2130');
INSERT INTO `em_data_step` VALUES ('1116', '4', '58.4900', '996.6960');
INSERT INTO `em_data_step` VALUES ('1117', '4', '58.5000', '996.6943');
INSERT INTO `em_data_step` VALUES ('1118', '4', '59.5000', '997.1855');
INSERT INTO `em_data_step` VALUES ('1119', '4', '60.5000', '995.4471');
INSERT INTO `em_data_step` VALUES ('1120', '4', '61.5000', '995.3522');
INSERT INTO `em_data_step` VALUES ('1121', '4', '65.4900', '995.4692');
INSERT INTO `em_data_step` VALUES ('1122', '4', '65.5000', '995.4691');
INSERT INTO `em_data_step` VALUES ('1123', '4', '66.5000', '995.8383');
INSERT INTO `em_data_step` VALUES ('1124', '4', '68.4900', '996.4861');
INSERT INTO `em_data_step` VALUES ('1125', '4', '68.5000', '996.4891');
INSERT INTO `em_data_step` VALUES ('1126', '4', '69.5000', '997.9730');
INSERT INTO `em_data_step` VALUES ('1127', '4', '71.4900', '998.7579');
INSERT INTO `em_data_step` VALUES ('1128', '4', '71.5000', '998.7643');
INSERT INTO `em_data_step` VALUES ('1129', '4', '73.4900', '998.8915');
INSERT INTO `em_data_step` VALUES ('1130', '4', '73.5000', '998.8953');
INSERT INTO `em_data_step` VALUES ('1131', '4', '75.4900', '999.4986');
INSERT INTO `em_data_step` VALUES ('1132', '4', '75.5000', '999.4919');
INSERT INTO `em_data_step` VALUES ('1133', '4', '77.4900', '1000.0244');
INSERT INTO `em_data_step` VALUES ('1134', '4', '79.4900', '1000.7019');
INSERT INTO `em_data_step` VALUES ('1135', '4', '79.5000', '1000.7258');
INSERT INTO `em_data_step` VALUES ('1136', '4', '81.4900', '999.9130');
INSERT INTO `em_data_step` VALUES ('1137', '4', '81.5000', '999.9177');
INSERT INTO `em_data_step` VALUES ('1138', '4', '83.4900', '999.5916');
INSERT INTO `em_data_step` VALUES ('1139', '4', '84.4900', '1000.7302');
INSERT INTO `em_data_step` VALUES ('1140', '4', '84.5000', '1000.7531');
INSERT INTO `em_data_step` VALUES ('1141', '4', '86.4900', '1000.8064');
INSERT INTO `em_data_step` VALUES ('1142', '4', '86.5000', '1000.8187');
INSERT INTO `em_data_step` VALUES ('1143', '4', '87.5000', '1001.4550');
INSERT INTO `em_data_step` VALUES ('1144', '4', '89.4900', '1002.1253');
INSERT INTO `em_data_step` VALUES ('1145', '4', '89.5000', '1002.1444');
INSERT INTO `em_data_step` VALUES ('1146', '4', '91.5900', '1002.4138');
INSERT INTO `em_data_step` VALUES ('1147', '4', '93.4516', '1002.1024');
INSERT INTO `em_data_step` VALUES ('1148', '4', '94.4847', '1002.7137');
INSERT INTO `em_data_step` VALUES ('1149', '4', '95.4900', '1002.0469');
INSERT INTO `em_data_step` VALUES ('1150', '4', '95.5000', '1002.0375');
INSERT INTO `em_data_step` VALUES ('1151', '4', '97.4900', '1001.6347');
INSERT INTO `em_data_step` VALUES ('1152', '4', '97.5000', '1001.6264');
INSERT INTO `em_data_step` VALUES ('1153', '4', '98.4900', '1000.5150');
INSERT INTO `em_data_step` VALUES ('1154', '4', '98.5000', '1000.5063');
INSERT INTO `em_data_step` VALUES ('1155', '4', '100.4900', '1000.8127');
INSERT INTO `em_data_step` VALUES ('1156', '4', '100.5000', '1000.8046');
INSERT INTO `em_data_step` VALUES ('1157', '4', '101.5000', '1000.6431');
INSERT INTO `em_data_step` VALUES ('1158', '4', '103.4900', '1000.2784');
INSERT INTO `em_data_step` VALUES ('1159', '4', '103.5000', '1000.2750');
INSERT INTO `em_data_step` VALUES ('1160', '4', '104.5000', '1001.4408');
INSERT INTO `em_data_step` VALUES ('1161', '4', '106.4900', '1001.1285');
INSERT INTO `em_data_step` VALUES ('1162', '4', '106.5000', '1001.1390');
INSERT INTO `em_data_step` VALUES ('1163', '4', '108.4900', '1000.6078');
INSERT INTO `em_data_step` VALUES ('1164', '4', '108.5000', '1000.6044');
INSERT INTO `em_data_step` VALUES ('1165', '4', '110.4900', '1000.9460');
INSERT INTO `em_data_step` VALUES ('1166', '4', '111.4900', '1001.0083');
INSERT INTO `em_data_step` VALUES ('1167', '4', '111.5000', '1001.0214');
INSERT INTO `em_data_step` VALUES ('1168', '4', '113.4900', '1000.9363');
INSERT INTO `em_data_step` VALUES ('1169', '4', '113.5000', '1000.9415');
INSERT INTO `em_data_step` VALUES ('1170', '4', '114.4949', '1002.0048');
INSERT INTO `em_data_step` VALUES ('1171', '4', '114.5000', '1002.0093');
INSERT INTO `em_data_step` VALUES ('1172', '4', '116.4900', '1001.0314');
INSERT INTO `em_data_step` VALUES ('1173', '4', '116.5000', '1001.0474');
INSERT INTO `em_data_step` VALUES ('1174', '4', '117.5000', '1000.8834');
INSERT INTO `em_data_step` VALUES ('1175', '4', '119.4949', '1000.1336');
INSERT INTO `em_data_step` VALUES ('1176', '4', '119.5000', '1000.1409');
INSERT INTO `em_data_step` VALUES ('1177', '4', '121.4900', '999.4350');
INSERT INTO `em_data_step` VALUES ('1178', '4', '121.5000', '999.4388');
INSERT INTO `em_data_step` VALUES ('1179', '4', '123.4900', '998.6652');
INSERT INTO `em_data_step` VALUES ('1180', '4', '123.5000', '998.6615');
INSERT INTO `em_data_step` VALUES ('1181', '4', '125.4900', '998.7738');
INSERT INTO `em_data_step` VALUES ('1182', '4', '125.5000', '998.7724');
INSERT INTO `em_data_step` VALUES ('1183', '4', '127.4900', '999.8851');
INSERT INTO `em_data_step` VALUES ('1184', '4', '127.5000', '999.8817');
INSERT INTO `em_data_step` VALUES ('1185', '4', '128.5854', '999.2978');
INSERT INTO `em_data_step` VALUES ('1186', '4', '130.4900', '999.6123');
INSERT INTO `em_data_step` VALUES ('1187', '4', '130.5000', '999.6101');
INSERT INTO `em_data_step` VALUES ('1188', '4', '132.5000', '1000.4992');
INSERT INTO `em_data_step` VALUES ('1189', '4', '135.4900', '1000.2846');
INSERT INTO `em_data_step` VALUES ('1190', '4', '135.5000', '1000.2758');
INSERT INTO `em_data_step` VALUES ('1191', '4', '136.4900', '1000.3371');
INSERT INTO `em_data_step` VALUES ('1192', '4', '136.5000', '1000.3502');
INSERT INTO `em_data_step` VALUES ('1193', '4', '138.4900', '999.7029');
INSERT INTO `em_data_step` VALUES ('1194', '4', '138.5000', '999.6989');
INSERT INTO `em_data_step` VALUES ('1195', '4', '141.4900', '1000.2921');
INSERT INTO `em_data_step` VALUES ('1196', '4', '141.5000', '1000.3101');
INSERT INTO `em_data_step` VALUES ('1197', '4', '143.4900', '1000.1839');
INSERT INTO `em_data_step` VALUES ('1198', '4', '143.5000', '1000.1878');
INSERT INTO `em_data_step` VALUES ('1199', '4', '145.4968', '1000.3860');
INSERT INTO `em_data_step` VALUES ('1200', '4', '145.5000', '1000.3855');
INSERT INTO `em_data_step` VALUES ('1201', '4', '147.4900', '1000.7540');
INSERT INTO `em_data_step` VALUES ('1202', '4', '147.5000', '1000.7385');
INSERT INTO `em_data_step` VALUES ('1203', '4', '149.4900', '1000.0368');
INSERT INTO `em_data_step` VALUES ('1204', '4', '149.5000', '1000.0535');
INSERT INTO `em_data_step` VALUES ('1205', '4', '150.5000', '999.1348');
INSERT INTO `em_data_step` VALUES ('1206', '4', '152.4900', '1000.2787');
INSERT INTO `em_data_step` VALUES ('1207', '4', '152.5000', '1000.2733');
INSERT INTO `em_data_step` VALUES ('1208', '4', '154.4949', '1000.8632');
INSERT INTO `em_data_step` VALUES ('1209', '4', '154.5000', '1000.8724');
INSERT INTO `em_data_step` VALUES ('1210', '4', '156.4900', '1000.1356');
INSERT INTO `em_data_step` VALUES ('1211', '4', '156.5000', '1000.1472');
INSERT INTO `em_data_step` VALUES ('1212', '4', '158.4900', '999.2601');
INSERT INTO `em_data_step` VALUES ('1213', '4', '158.5000', '999.2586');
INSERT INTO `em_data_step` VALUES ('1214', '4', '160.4900', '999.3427');
INSERT INTO `em_data_step` VALUES ('1215', '4', '161.4900', '999.7803');
INSERT INTO `em_data_step` VALUES ('1216', '4', '162.4900', '1000.2701');
INSERT INTO `em_data_step` VALUES ('1217', '4', '162.5000', '1000.2662');
INSERT INTO `em_data_step` VALUES ('1218', '4', '164.4900', '1001.0176');
INSERT INTO `em_data_step` VALUES ('1219', '4', '164.5000', '1001.0302');
INSERT INTO `em_data_step` VALUES ('1220', '4', '166.4900', '1000.1569');
INSERT INTO `em_data_step` VALUES ('1221', '4', '167.4900', '1000.0839');
INSERT INTO `em_data_step` VALUES ('1222', '4', '168.4900', '999.1466');
INSERT INTO `em_data_step` VALUES ('1223', '4', '168.5000', '999.1450');
INSERT INTO `em_data_step` VALUES ('1224', '4', '169.4949', '999.9847');
INSERT INTO `em_data_step` VALUES ('1225', '4', '169.5000', '999.9931');
INSERT INTO `em_data_step` VALUES ('1226', '4', '171.4900', '999.6480');
INSERT INTO `em_data_step` VALUES ('1227', '4', '171.5000', '999.6585');
INSERT INTO `em_data_step` VALUES ('1228', '4', '173.4900', '999.7783');
INSERT INTO `em_data_step` VALUES ('1229', '4', '173.5000', '999.7802');
INSERT INTO `em_data_step` VALUES ('1230', '4', '175.4968', '1000.2111');
INSERT INTO `em_data_step` VALUES ('1231', '4', '175.5000', '1000.2107');
INSERT INTO `em_data_step` VALUES ('1232', '4', '188.6100', '999.4372');
INSERT INTO `em_data_step` VALUES ('1233', '4', '192.9978', '999.7128');
INSERT INTO `em_data_step` VALUES ('1234', '4', '196.1000', '999.2582');
INSERT INTO `em_data_step` VALUES ('1235', '4', '197.6834', '1000.2373');
INSERT INTO `em_data_step` VALUES ('1236', '4', '199.4949', '1001.1304');
INSERT INTO `em_data_step` VALUES ('1237', '4', '199.5600', '1001.2117');
INSERT INTO `em_data_step` VALUES ('1238', '4', '200.9001', '1000.3264');
INSERT INTO `em_data_step` VALUES ('1239', '4', '203.4900', '999.9647');
INSERT INTO `em_data_step` VALUES ('1240', '4', '204.4949', '1000.7231');
INSERT INTO `em_data_step` VALUES ('1241', '4', '204.5000', '1000.7316');
INSERT INTO `em_data_step` VALUES ('1242', '4', '205.4968', '999.7426');
INSERT INTO `em_data_step` VALUES ('1243', '4', '205.5000', '999.7407');
INSERT INTO `em_data_step` VALUES ('1244', '4', '209.3501', '998.0911');
INSERT INTO `em_data_step` VALUES ('1245', '4', '210.4900', '998.3892');
INSERT INTO `em_data_step` VALUES ('1246', '4', '211.4900', '999.5714');
INSERT INTO `em_data_step` VALUES ('1247', '4', '211.5000', '999.5869');
INSERT INTO `em_data_step` VALUES ('1248', '4', '213.5785', '999.1379');
INSERT INTO `em_data_step` VALUES ('1249', '4', '214.5000', '999.7690');
INSERT INTO `em_data_step` VALUES ('1250', '4', '215.4900', '999.3040');
INSERT INTO `em_data_step` VALUES ('1251', '4', '215.5000', '999.2961');
INSERT INTO `em_data_step` VALUES ('1252', '4', '217.4900', '999.8553');
INSERT INTO `em_data_step` VALUES ('1253', '4', '217.5000', '999.8499');
INSERT INTO `em_data_step` VALUES ('1254', '4', '219.3509', '999.3666');
INSERT INTO `em_data_step` VALUES ('1255', '4', '220.4900', '999.2928');
INSERT INTO `em_data_step` VALUES ('1256', '4', '220.5000', '999.2857');
INSERT INTO `em_data_step` VALUES ('1257', '4', '222.4900', '999.9302');
INSERT INTO `em_data_step` VALUES ('1258', '4', '222.5000', '999.9250');
INSERT INTO `em_data_step` VALUES ('1259', '4', '224.4949', '1001.5619');
INSERT INTO `em_data_step` VALUES ('1260', '4', '224.5000', '1001.5691');
INSERT INTO `em_data_step` VALUES ('1261', '4', '226.4900', '1001.0495');
INSERT INTO `em_data_step` VALUES ('1262', '4', '226.5000', '1001.0639');
INSERT INTO `em_data_step` VALUES ('1263', '4', '228.4900', '1001.1629');
INSERT INTO `em_data_step` VALUES ('1264', '4', '228.5000', '1001.1712');
INSERT INTO `em_data_step` VALUES ('1265', '4', '230.4968', '1001.3592');
INSERT INTO `em_data_step` VALUES ('1266', '4', '230.5000', '1001.3566');
INSERT INTO `em_data_step` VALUES ('1267', '4', '232.4900', '1001.0804');
INSERT INTO `em_data_step` VALUES ('1268', '4', '232.5000', '1001.0637');
INSERT INTO `em_data_step` VALUES ('1269', '4', '234.4900', '1000.2504');
INSERT INTO `em_data_step` VALUES ('1270', '4', '234.5000', '1000.2668');
INSERT INTO `em_data_step` VALUES ('1271', '4', '237.7501', '999.3072');
INSERT INTO `em_data_step` VALUES ('1272', '4', '241.4321', '999.8371');
INSERT INTO `em_data_step` VALUES ('1273', '4', '242.4900', '1000.5500');
INSERT INTO `em_data_step` VALUES ('1274', '4', '242.5000', '1000.5462');
INSERT INTO `em_data_step` VALUES ('1275', '4', '244.4949', '1001.3275');
INSERT INTO `em_data_step` VALUES ('1276', '4', '244.5000', '1001.3366');
INSERT INTO `em_data_step` VALUES ('1277', '4', '246.4822', '1000.4770');
INSERT INTO `em_data_step` VALUES ('1278', '4', '247.4900', '1000.2416');
INSERT INTO `em_data_step` VALUES ('1279', '4', '247.5000', '1000.2262');
INSERT INTO `em_data_step` VALUES ('1280', '4', '249.4949', '1000.1486');
INSERT INTO `em_data_step` VALUES ('1281', '4', '249.5000', '1000.1569');
INSERT INTO `em_data_step` VALUES ('1282', '4', '251.4900', '999.2581');
INSERT INTO `em_data_step` VALUES ('1283', '4', '251.5000', '999.2712');
INSERT INTO `em_data_step` VALUES ('1284', '4', '253.4900', '999.0518');
INSERT INTO `em_data_step` VALUES ('1285', '4', '253.5000', '999.0486');
INSERT INTO `em_data_step` VALUES ('1286', '4', '254.7000', '999.9696');
INSERT INTO `em_data_step` VALUES ('1287', '4', '256.1302', '999.8410');
INSERT INTO `em_data_step` VALUES ('1288', '4', '257.4900', '1000.5425');
INSERT INTO `em_data_step` VALUES ('1289', '4', '257.5000', '1000.5346');
INSERT INTO `em_data_step` VALUES ('1290', '4', '259.4802', '1000.1968');
INSERT INTO `em_data_step` VALUES ('1291', '4', '260.4901', '999.7991');
INSERT INTO `em_data_step` VALUES ('1292', '4', '261.1800', '999.8397');
INSERT INTO `em_data_step` VALUES ('1293', '4', '261.5000', '1000.2808');
INSERT INTO `em_data_step` VALUES ('1294', '4', '263.4900', '1000.4034');
INSERT INTO `em_data_step` VALUES ('1295', '4', '263.5000', '1000.4052');
INSERT INTO `em_data_step` VALUES ('1296', '4', '264.6400', '1001.4579');
INSERT INTO `em_data_step` VALUES ('1297', '4', '266.4900', '1000.0677');
INSERT INTO `em_data_step` VALUES ('1298', '4', '268.4900', '999.7343');
INSERT INTO `em_data_step` VALUES ('1299', '4', '268.5000', '999.7369');
INSERT INTO `em_data_step` VALUES ('1300', '4', '270.4968', '999.8380');
INSERT INTO `em_data_step` VALUES ('1301', '4', '270.5000', '999.8364');
INSERT INTO `em_data_step` VALUES ('1302', '4', '272.4900', '1000.1002');
INSERT INTO `em_data_step` VALUES ('1303', '4', '272.5000', '1000.0878');
INSERT INTO `em_data_step` VALUES ('1304', '4', '274.4900', '998.8775');
INSERT INTO `em_data_step` VALUES ('1305', '4', '274.5000', '998.8936');
INSERT INTO `em_data_step` VALUES ('1306', '4', '276.4900', '999.7440');
INSERT INTO `em_data_step` VALUES ('1307', '4', '276.5000', '999.7586');
INSERT INTO `em_data_step` VALUES ('1308', '4', '278.4900', '999.9310');
INSERT INTO `em_data_step` VALUES ('1309', '4', '278.5000', '999.9334');
INSERT INTO `em_data_step` VALUES ('1310', '4', '280.4968', '1000.3137');
INSERT INTO `em_data_step` VALUES ('1311', '4', '280.5000', '1000.3134');
INSERT INTO `em_data_step` VALUES ('1312', '4', '282.4900', '1000.7470');
INSERT INTO `em_data_step` VALUES ('1313', '4', '283.4900', '999.8310');
INSERT INTO `em_data_step` VALUES ('1314', '4', '283.5000', '999.8265');
INSERT INTO `em_data_step` VALUES ('1315', '4', '285.4968', '999.3910');
INSERT INTO `em_data_step` VALUES ('1316', '4', '285.5000', '999.3891');
INSERT INTO `em_data_step` VALUES ('1317', '4', '287.4900', '999.8554');
INSERT INTO `em_data_step` VALUES ('1318', '4', '287.5000', '999.8489');
INSERT INTO `em_data_step` VALUES ('1319', '4', '289.4900', '999.7563');
INSERT INTO `em_data_step` VALUES ('1320', '4', '289.5000', '999.7750');
INSERT INTO `em_data_step` VALUES ('1321', '4', '291.4900', '1000.3846');
INSERT INTO `em_data_step` VALUES ('1322', '4', '291.5000', '1000.3962');
INSERT INTO `em_data_step` VALUES ('1323', '4', '293.4900', '999.6266');
INSERT INTO `em_data_step` VALUES ('1324', '4', '293.5000', '999.6224');
INSERT INTO `em_data_step` VALUES ('1325', '4', '295.4968', '999.7321');
INSERT INTO `em_data_step` VALUES ('1326', '4', '296.4900', '1000.2219');
INSERT INTO `em_data_step` VALUES ('1327', '4', '297.4900', '1000.5641');
INSERT INTO `em_data_step` VALUES ('1328', '4', '297.5000', '1000.5573');
INSERT INTO `em_data_step` VALUES ('1329', '4', '299.4949', '1001.0820');
INSERT INTO `em_data_step` VALUES ('1330', '4', '300.0000', '1000.6384');
INSERT INTO `em_data_step` VALUES ('1331', '3', '0.0000', '0.0000');
INSERT INTO `em_data_step` VALUES ('1332', '3', '2.8700', '100.1087');
INSERT INTO `em_data_step` VALUES ('1333', '3', '7.0200', '330.1883');
INSERT INTO `em_data_step` VALUES ('1334', '3', '8.3600', '410.5946');
INSERT INTO `em_data_step` VALUES ('1335', '3', '11.3002', '597.6555');
INSERT INTO `em_data_step` VALUES ('1336', '3', '12.4900', '674.4049');
INSERT INTO `em_data_step` VALUES ('1337', '3', '12.5000', '675.0540');
INSERT INTO `em_data_step` VALUES ('1338', '3', '14.4900', '793.6980');
INSERT INTO `em_data_step` VALUES ('1339', '3', '15.4900', '850.0369');
INSERT INTO `em_data_step` VALUES ('1340', '3', '15.5000', '850.6192');
INSERT INTO `em_data_step` VALUES ('1341', '3', '17.4900', '957.0879');
INSERT INTO `em_data_step` VALUES ('1342', '3', '17.5000', '957.5882');
INSERT INTO `em_data_step` VALUES ('1343', '3', '18.4900', '1008.4573');
INSERT INTO `em_data_step` VALUES ('1344', '3', '18.5000', '1008.9651');
INSERT INTO `em_data_step` VALUES ('1345', '3', '20.6500', '1111.3642');
INSERT INTO `em_data_step` VALUES ('1346', '3', '22.4900', '1180.3107');
INSERT INTO `em_data_step` VALUES ('1347', '3', '22.5000', '1180.5974');
INSERT INTO `em_data_step` VALUES ('1348', '3', '24.4900', '1211.8142');
INSERT INTO `em_data_step` VALUES ('1349', '3', '25.4900', '1214.4557');
INSERT INTO `em_data_step` VALUES ('1350', '3', '25.5000', '1214.4572');
INSERT INTO `em_data_step` VALUES ('1351', '3', '27.4900', '1209.0326');
INSERT INTO `em_data_step` VALUES ('1352', '3', '28.4900', '1202.4135');
INSERT INTO `em_data_step` VALUES ('1353', '3', '28.5000', '1202.3474');
INSERT INTO `em_data_step` VALUES ('1354', '3', '30.4900', '1184.1952');
INSERT INTO `em_data_step` VALUES ('1355', '3', '30.5000', '1184.0996');
INSERT INTO `em_data_step` VALUES ('1356', '3', '32.4900', '1162.8002');
INSERT INTO `em_data_step` VALUES ('1357', '3', '32.5000', '1162.6723');
INSERT INTO `em_data_step` VALUES ('1358', '3', '34.4942', '1139.3353');
INSERT INTO `em_data_step` VALUES ('1359', '3', '34.5000', '1139.2509');
INSERT INTO `em_data_step` VALUES ('1360', '3', '35.5000', '1127.8025');
INSERT INTO `em_data_step` VALUES ('1361', '3', '37.4944', '1103.6958');
INSERT INTO `em_data_step` VALUES ('1362', '3', '37.5000', '1103.6457');
INSERT INTO `em_data_step` VALUES ('1363', '3', '39.4900', '1081.6261');
INSERT INTO `em_data_step` VALUES ('1364', '3', '39.5000', '1081.5418');
INSERT INTO `em_data_step` VALUES ('1365', '3', '40.0000', '1063.2484');
INSERT INTO `em_data_step` VALUES ('1366', '3', '41.0000', '1063.1501');
INSERT INTO `em_data_step` VALUES ('1367', '3', '42.0000', '1046.6015');
INSERT INTO `em_data_step` VALUES ('1368', '3', '43.0000', '1046.5544');
INSERT INTO `em_data_step` VALUES ('1369', '3', '44.0000', '1032.7445');
INSERT INTO `em_data_step` VALUES ('1370', '3', '45.0000', '1032.6941');
INSERT INTO `em_data_step` VALUES ('1371', '3', '46.0000', '1025.1609');
INSERT INTO `em_data_step` VALUES ('1372', '3', '47.0000', '1025.0936');
INSERT INTO `em_data_step` VALUES ('1373', '3', '48.0000', '1020.9909');
INSERT INTO `em_data_step` VALUES ('1374', '3', '50.4900', '1014.7925');
INSERT INTO `em_data_step` VALUES ('1375', '3', '50.5000', '1014.7623');
INSERT INTO `em_data_step` VALUES ('1376', '3', '52.4900', '1009.3071');
INSERT INTO `em_data_step` VALUES ('1377', '3', '52.5000', '1009.2575');
INSERT INTO `em_data_step` VALUES ('1378', '3', '53.5517', '1005.1345');
INSERT INTO `em_data_step` VALUES ('1379', '3', '55.4969', '1000.1641');
INSERT INTO `em_data_step` VALUES ('1380', '3', '55.5000', '1000.1584');
INSERT INTO `em_data_step` VALUES ('1381', '3', '56.5000', '999.2130');
INSERT INTO `em_data_step` VALUES ('1382', '3', '58.4900', '996.6960');
INSERT INTO `em_data_step` VALUES ('1383', '3', '58.5000', '996.6943');
INSERT INTO `em_data_step` VALUES ('1384', '3', '59.5000', '997.1855');
INSERT INTO `em_data_step` VALUES ('1385', '3', '60.5000', '995.4471');
INSERT INTO `em_data_step` VALUES ('1386', '3', '61.5000', '995.3522');
INSERT INTO `em_data_step` VALUES ('1387', '3', '65.4900', '995.4692');
INSERT INTO `em_data_step` VALUES ('1388', '3', '65.5000', '995.4691');
INSERT INTO `em_data_step` VALUES ('1389', '3', '66.5000', '995.8383');
INSERT INTO `em_data_step` VALUES ('1390', '3', '68.4900', '996.4861');
INSERT INTO `em_data_step` VALUES ('1391', '3', '68.5000', '996.4891');
INSERT INTO `em_data_step` VALUES ('1392', '3', '69.5000', '997.9730');
INSERT INTO `em_data_step` VALUES ('1393', '3', '71.4900', '998.7579');
INSERT INTO `em_data_step` VALUES ('1394', '3', '71.5000', '998.7643');
INSERT INTO `em_data_step` VALUES ('1395', '3', '73.4900', '998.8915');
INSERT INTO `em_data_step` VALUES ('1396', '3', '73.5000', '998.8953');
INSERT INTO `em_data_step` VALUES ('1397', '3', '75.4900', '999.4986');
INSERT INTO `em_data_step` VALUES ('1398', '3', '75.5000', '999.4919');
INSERT INTO `em_data_step` VALUES ('1399', '3', '77.4900', '1000.0244');
INSERT INTO `em_data_step` VALUES ('1400', '3', '79.4900', '1000.7019');
INSERT INTO `em_data_step` VALUES ('1401', '3', '79.5000', '1000.7258');
INSERT INTO `em_data_step` VALUES ('1402', '3', '81.4900', '999.9130');
INSERT INTO `em_data_step` VALUES ('1403', '3', '81.5000', '999.9177');
INSERT INTO `em_data_step` VALUES ('1404', '3', '83.4900', '999.5916');
INSERT INTO `em_data_step` VALUES ('1405', '3', '84.4900', '1000.7302');
INSERT INTO `em_data_step` VALUES ('1406', '3', '84.5000', '1000.7531');
INSERT INTO `em_data_step` VALUES ('1407', '3', '86.4900', '1000.8064');
INSERT INTO `em_data_step` VALUES ('1408', '3', '86.5000', '1000.8187');
INSERT INTO `em_data_step` VALUES ('1409', '3', '87.5000', '1001.4550');
INSERT INTO `em_data_step` VALUES ('1410', '3', '89.4900', '1002.1253');
INSERT INTO `em_data_step` VALUES ('1411', '3', '89.5000', '1002.1444');
INSERT INTO `em_data_step` VALUES ('1412', '3', '91.5900', '1002.4138');
INSERT INTO `em_data_step` VALUES ('1413', '3', '93.4516', '1002.1024');
INSERT INTO `em_data_step` VALUES ('1414', '3', '94.4847', '1002.7137');
INSERT INTO `em_data_step` VALUES ('1415', '3', '95.4900', '1002.0469');
INSERT INTO `em_data_step` VALUES ('1416', '3', '95.5000', '1002.0375');
INSERT INTO `em_data_step` VALUES ('1417', '3', '97.4900', '1001.6347');
INSERT INTO `em_data_step` VALUES ('1418', '3', '97.5000', '1001.6264');
INSERT INTO `em_data_step` VALUES ('1419', '3', '98.4900', '1000.5150');
INSERT INTO `em_data_step` VALUES ('1420', '3', '98.5000', '1000.5063');
INSERT INTO `em_data_step` VALUES ('1421', '3', '100.4900', '1000.8127');
INSERT INTO `em_data_step` VALUES ('1422', '3', '100.5000', '1000.8046');
INSERT INTO `em_data_step` VALUES ('1423', '3', '101.5000', '1000.6431');
INSERT INTO `em_data_step` VALUES ('1424', '3', '103.4900', '1000.2784');
INSERT INTO `em_data_step` VALUES ('1425', '3', '103.5000', '1000.2750');
INSERT INTO `em_data_step` VALUES ('1426', '3', '104.5000', '1001.4408');
INSERT INTO `em_data_step` VALUES ('1427', '3', '106.4900', '1001.1285');
INSERT INTO `em_data_step` VALUES ('1428', '3', '106.5000', '1001.1390');
INSERT INTO `em_data_step` VALUES ('1429', '3', '108.4900', '1000.6078');
INSERT INTO `em_data_step` VALUES ('1430', '3', '108.5000', '1000.6044');
INSERT INTO `em_data_step` VALUES ('1431', '3', '110.4900', '1000.9460');
INSERT INTO `em_data_step` VALUES ('1432', '3', '111.4900', '1001.0083');
INSERT INTO `em_data_step` VALUES ('1433', '3', '111.5000', '1001.0214');
INSERT INTO `em_data_step` VALUES ('1434', '3', '113.4900', '1000.9363');
INSERT INTO `em_data_step` VALUES ('1435', '3', '113.5000', '1000.9415');
INSERT INTO `em_data_step` VALUES ('1436', '3', '114.4949', '1002.0048');
INSERT INTO `em_data_step` VALUES ('1437', '3', '114.5000', '1002.0093');
INSERT INTO `em_data_step` VALUES ('1438', '3', '116.4900', '1001.0314');
INSERT INTO `em_data_step` VALUES ('1439', '3', '116.5000', '1001.0474');
INSERT INTO `em_data_step` VALUES ('1440', '3', '117.5000', '1000.8834');
INSERT INTO `em_data_step` VALUES ('1441', '3', '119.4949', '1000.1336');
INSERT INTO `em_data_step` VALUES ('1442', '3', '119.5000', '1000.1409');
INSERT INTO `em_data_step` VALUES ('1443', '3', '121.4900', '999.4350');
INSERT INTO `em_data_step` VALUES ('1444', '3', '121.5000', '999.4388');
INSERT INTO `em_data_step` VALUES ('1445', '3', '123.4900', '998.6652');
INSERT INTO `em_data_step` VALUES ('1446', '3', '123.5000', '998.6615');
INSERT INTO `em_data_step` VALUES ('1447', '3', '125.4900', '998.7738');
INSERT INTO `em_data_step` VALUES ('1448', '3', '125.5000', '998.7724');
INSERT INTO `em_data_step` VALUES ('1449', '3', '127.4900', '999.8851');
INSERT INTO `em_data_step` VALUES ('1450', '3', '127.5000', '999.8817');
INSERT INTO `em_data_step` VALUES ('1451', '3', '128.5854', '999.2978');
INSERT INTO `em_data_step` VALUES ('1452', '3', '130.4900', '999.6123');
INSERT INTO `em_data_step` VALUES ('1453', '3', '130.5000', '999.6101');
INSERT INTO `em_data_step` VALUES ('1454', '3', '132.5000', '1000.4992');
INSERT INTO `em_data_step` VALUES ('1455', '3', '135.4900', '1000.2846');
INSERT INTO `em_data_step` VALUES ('1456', '3', '135.5000', '1000.2758');
INSERT INTO `em_data_step` VALUES ('1457', '3', '136.4900', '1000.3371');
INSERT INTO `em_data_step` VALUES ('1458', '3', '136.5000', '1000.3502');
INSERT INTO `em_data_step` VALUES ('1459', '3', '138.4900', '999.7029');
INSERT INTO `em_data_step` VALUES ('1460', '3', '138.5000', '999.6989');
INSERT INTO `em_data_step` VALUES ('1461', '3', '141.4900', '1000.2921');
INSERT INTO `em_data_step` VALUES ('1462', '3', '141.5000', '1000.3101');
INSERT INTO `em_data_step` VALUES ('1463', '3', '143.4900', '1000.1839');
INSERT INTO `em_data_step` VALUES ('1464', '3', '143.5000', '1000.1878');
INSERT INTO `em_data_step` VALUES ('1465', '3', '145.4968', '1000.3860');
INSERT INTO `em_data_step` VALUES ('1466', '3', '145.5000', '1000.3855');
INSERT INTO `em_data_step` VALUES ('1467', '3', '147.4900', '1000.7540');
INSERT INTO `em_data_step` VALUES ('1468', '3', '147.5000', '1000.7385');
INSERT INTO `em_data_step` VALUES ('1469', '3', '149.4900', '1000.0368');
INSERT INTO `em_data_step` VALUES ('1470', '3', '149.5000', '1000.0535');
INSERT INTO `em_data_step` VALUES ('1471', '3', '150.5000', '999.1348');
INSERT INTO `em_data_step` VALUES ('1472', '3', '152.4900', '1000.2787');
INSERT INTO `em_data_step` VALUES ('1473', '3', '152.5000', '1000.2733');
INSERT INTO `em_data_step` VALUES ('1474', '3', '154.4949', '1000.8632');
INSERT INTO `em_data_step` VALUES ('1475', '3', '154.5000', '1000.8724');
INSERT INTO `em_data_step` VALUES ('1476', '3', '156.4900', '1000.1356');
INSERT INTO `em_data_step` VALUES ('1477', '3', '156.5000', '1000.1472');
INSERT INTO `em_data_step` VALUES ('1478', '3', '158.4900', '999.2601');
INSERT INTO `em_data_step` VALUES ('1479', '3', '158.5000', '999.2586');
INSERT INTO `em_data_step` VALUES ('1480', '3', '160.4900', '999.3427');
INSERT INTO `em_data_step` VALUES ('1481', '3', '161.4900', '999.7803');
INSERT INTO `em_data_step` VALUES ('1482', '3', '162.4900', '1000.2701');
INSERT INTO `em_data_step` VALUES ('1483', '3', '162.5000', '1000.2662');
INSERT INTO `em_data_step` VALUES ('1484', '3', '164.4900', '1001.0176');
INSERT INTO `em_data_step` VALUES ('1485', '3', '164.5000', '1001.0302');
INSERT INTO `em_data_step` VALUES ('1486', '3', '166.4900', '1000.1569');
INSERT INTO `em_data_step` VALUES ('1487', '3', '167.4900', '1000.0839');
INSERT INTO `em_data_step` VALUES ('1488', '3', '168.4900', '999.1466');
INSERT INTO `em_data_step` VALUES ('1489', '3', '168.5000', '999.1450');
INSERT INTO `em_data_step` VALUES ('1490', '3', '169.4949', '999.9847');
INSERT INTO `em_data_step` VALUES ('1491', '3', '169.5000', '999.9931');
INSERT INTO `em_data_step` VALUES ('1492', '3', '171.4900', '999.6480');
INSERT INTO `em_data_step` VALUES ('1493', '3', '171.5000', '999.6585');
INSERT INTO `em_data_step` VALUES ('1494', '3', '173.4900', '999.7783');
INSERT INTO `em_data_step` VALUES ('1495', '3', '173.5000', '999.7802');
INSERT INTO `em_data_step` VALUES ('1496', '3', '175.4968', '1000.2111');
INSERT INTO `em_data_step` VALUES ('1497', '3', '175.5000', '1000.2107');
INSERT INTO `em_data_step` VALUES ('1498', '3', '188.6100', '999.4372');
INSERT INTO `em_data_step` VALUES ('1499', '3', '192.9978', '999.7128');
INSERT INTO `em_data_step` VALUES ('1500', '3', '196.1000', '999.2582');
INSERT INTO `em_data_step` VALUES ('1501', '3', '197.6834', '1000.2373');
INSERT INTO `em_data_step` VALUES ('1502', '3', '199.4949', '1001.1304');
INSERT INTO `em_data_step` VALUES ('1503', '3', '199.5600', '1001.2117');
INSERT INTO `em_data_step` VALUES ('1504', '3', '200.9001', '1000.3264');
INSERT INTO `em_data_step` VALUES ('1505', '3', '203.4900', '999.9647');
INSERT INTO `em_data_step` VALUES ('1506', '3', '204.4949', '1000.7231');
INSERT INTO `em_data_step` VALUES ('1507', '3', '204.5000', '1000.7316');
INSERT INTO `em_data_step` VALUES ('1508', '3', '205.4968', '999.7426');
INSERT INTO `em_data_step` VALUES ('1509', '3', '205.5000', '999.7407');
INSERT INTO `em_data_step` VALUES ('1510', '3', '209.3501', '998.0911');
INSERT INTO `em_data_step` VALUES ('1511', '3', '210.4900', '998.3892');
INSERT INTO `em_data_step` VALUES ('1512', '3', '211.4900', '999.5714');
INSERT INTO `em_data_step` VALUES ('1513', '3', '211.5000', '999.5869');
INSERT INTO `em_data_step` VALUES ('1514', '3', '213.5785', '999.1379');
INSERT INTO `em_data_step` VALUES ('1515', '3', '214.5000', '999.7690');
INSERT INTO `em_data_step` VALUES ('1516', '3', '215.4900', '999.3040');
INSERT INTO `em_data_step` VALUES ('1517', '3', '215.5000', '999.2961');
INSERT INTO `em_data_step` VALUES ('1518', '3', '217.4900', '999.8553');
INSERT INTO `em_data_step` VALUES ('1519', '3', '217.5000', '999.8499');
INSERT INTO `em_data_step` VALUES ('1520', '3', '219.3509', '999.3666');
INSERT INTO `em_data_step` VALUES ('1521', '3', '220.4900', '999.2928');
INSERT INTO `em_data_step` VALUES ('1522', '3', '220.5000', '999.2857');
INSERT INTO `em_data_step` VALUES ('1523', '3', '222.4900', '999.9302');
INSERT INTO `em_data_step` VALUES ('1524', '3', '222.5000', '999.9250');
INSERT INTO `em_data_step` VALUES ('1525', '3', '224.4949', '1001.5619');
INSERT INTO `em_data_step` VALUES ('1526', '3', '224.5000', '1001.5691');
INSERT INTO `em_data_step` VALUES ('1527', '3', '226.4900', '1001.0495');
INSERT INTO `em_data_step` VALUES ('1528', '3', '226.5000', '1001.0639');
INSERT INTO `em_data_step` VALUES ('1529', '3', '228.4900', '1001.1629');
INSERT INTO `em_data_step` VALUES ('1530', '3', '228.5000', '1001.1712');
INSERT INTO `em_data_step` VALUES ('1531', '3', '230.4968', '1001.3592');
INSERT INTO `em_data_step` VALUES ('1532', '3', '230.5000', '1001.3566');
INSERT INTO `em_data_step` VALUES ('1533', '3', '232.4900', '1001.0804');
INSERT INTO `em_data_step` VALUES ('1534', '3', '232.5000', '1001.0637');
INSERT INTO `em_data_step` VALUES ('1535', '3', '234.4900', '1000.2504');
INSERT INTO `em_data_step` VALUES ('1536', '3', '234.5000', '1000.2668');
INSERT INTO `em_data_step` VALUES ('1537', '3', '237.7501', '999.3072');
INSERT INTO `em_data_step` VALUES ('1538', '3', '241.4321', '999.8371');
INSERT INTO `em_data_step` VALUES ('1539', '3', '242.4900', '1000.5500');
INSERT INTO `em_data_step` VALUES ('1540', '3', '242.5000', '1000.5462');
INSERT INTO `em_data_step` VALUES ('1541', '3', '244.4949', '1001.3275');
INSERT INTO `em_data_step` VALUES ('1542', '3', '244.5000', '1001.3366');
INSERT INTO `em_data_step` VALUES ('1543', '3', '246.4822', '1000.4770');
INSERT INTO `em_data_step` VALUES ('1544', '3', '247.4900', '1000.2416');
INSERT INTO `em_data_step` VALUES ('1545', '3', '247.5000', '1000.2262');
INSERT INTO `em_data_step` VALUES ('1546', '3', '249.4949', '1000.1486');
INSERT INTO `em_data_step` VALUES ('1547', '3', '249.5000', '1000.1569');
INSERT INTO `em_data_step` VALUES ('1548', '3', '251.4900', '999.2581');
INSERT INTO `em_data_step` VALUES ('1549', '3', '251.5000', '999.2712');
INSERT INTO `em_data_step` VALUES ('1550', '3', '253.4900', '999.0518');
INSERT INTO `em_data_step` VALUES ('1551', '3', '253.5000', '999.0486');
INSERT INTO `em_data_step` VALUES ('1552', '3', '254.7000', '999.9696');
INSERT INTO `em_data_step` VALUES ('1553', '3', '256.1302', '999.8410');
INSERT INTO `em_data_step` VALUES ('1554', '3', '257.4900', '1000.5425');
INSERT INTO `em_data_step` VALUES ('1555', '3', '257.5000', '1000.5346');
INSERT INTO `em_data_step` VALUES ('1556', '3', '259.4802', '1000.1968');
INSERT INTO `em_data_step` VALUES ('1557', '3', '260.4901', '999.7991');
INSERT INTO `em_data_step` VALUES ('1558', '3', '261.1800', '999.8397');
INSERT INTO `em_data_step` VALUES ('1559', '3', '261.5000', '1000.2808');
INSERT INTO `em_data_step` VALUES ('1560', '3', '263.4900', '1000.4034');
INSERT INTO `em_data_step` VALUES ('1561', '3', '263.5000', '1000.4052');
INSERT INTO `em_data_step` VALUES ('1562', '3', '264.6400', '1001.4579');
INSERT INTO `em_data_step` VALUES ('1563', '3', '266.4900', '1000.0677');
INSERT INTO `em_data_step` VALUES ('1564', '3', '268.4900', '999.7343');
INSERT INTO `em_data_step` VALUES ('1565', '3', '268.5000', '999.7369');
INSERT INTO `em_data_step` VALUES ('1566', '3', '270.4968', '999.8380');
INSERT INTO `em_data_step` VALUES ('1567', '3', '270.5000', '999.8364');
INSERT INTO `em_data_step` VALUES ('1568', '3', '272.4900', '1000.1002');
INSERT INTO `em_data_step` VALUES ('1569', '3', '272.5000', '1000.0878');
INSERT INTO `em_data_step` VALUES ('1570', '3', '274.4900', '998.8775');
INSERT INTO `em_data_step` VALUES ('1571', '3', '274.5000', '998.8936');
INSERT INTO `em_data_step` VALUES ('1572', '3', '276.4900', '999.7440');
INSERT INTO `em_data_step` VALUES ('1573', '3', '276.5000', '999.7586');
INSERT INTO `em_data_step` VALUES ('1574', '3', '278.4900', '999.9310');
INSERT INTO `em_data_step` VALUES ('1575', '3', '278.5000', '999.9334');
INSERT INTO `em_data_step` VALUES ('1576', '3', '280.4968', '1000.3137');
INSERT INTO `em_data_step` VALUES ('1577', '3', '280.5000', '1000.3134');
INSERT INTO `em_data_step` VALUES ('1578', '3', '282.4900', '1000.7470');
INSERT INTO `em_data_step` VALUES ('1579', '3', '283.4900', '999.8310');
INSERT INTO `em_data_step` VALUES ('1580', '3', '283.5000', '999.8265');
INSERT INTO `em_data_step` VALUES ('1581', '3', '285.4968', '999.3910');
INSERT INTO `em_data_step` VALUES ('1582', '3', '285.5000', '999.3891');
INSERT INTO `em_data_step` VALUES ('1583', '3', '287.4900', '999.8554');
INSERT INTO `em_data_step` VALUES ('1584', '3', '287.5000', '999.8489');
INSERT INTO `em_data_step` VALUES ('1585', '3', '289.4900', '999.7563');
INSERT INTO `em_data_step` VALUES ('1586', '3', '289.5000', '999.7750');
INSERT INTO `em_data_step` VALUES ('1587', '3', '291.4900', '1000.3846');
INSERT INTO `em_data_step` VALUES ('1588', '3', '291.5000', '1000.3962');
INSERT INTO `em_data_step` VALUES ('1589', '3', '293.4900', '999.6266');
INSERT INTO `em_data_step` VALUES ('1590', '3', '293.5000', '999.6224');
INSERT INTO `em_data_step` VALUES ('1591', '3', '295.4968', '999.7321');
INSERT INTO `em_data_step` VALUES ('1592', '3', '296.4900', '1000.2219');
INSERT INTO `em_data_step` VALUES ('1593', '3', '297.4900', '1000.5641');
INSERT INTO `em_data_step` VALUES ('1594', '3', '297.5000', '1000.5573');
INSERT INTO `em_data_step` VALUES ('1595', '3', '299.4949', '1001.0820');
INSERT INTO `em_data_step` VALUES ('1596', '3', '300.0000', '1000.6384');

-- ----------------------------
-- Table structure for em_data_template
-- ----------------------------
DROP TABLE IF EXISTS `em_data_template`;
CREATE TABLE `em_data_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '模板名称',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_data_template
-- ----------------------------

-- ----------------------------
-- Table structure for em_index_category
-- ----------------------------
DROP TABLE IF EXISTS `em_index_category`;
CREATE TABLE `em_index_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL COMMENT '指标分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_index_category
-- ----------------------------
INSERT INTO `em_index_category` VALUES ('1', '动态控制性能');
INSERT INTO `em_index_category` VALUES ('2', '稳态控制性能');
INSERT INTO `em_index_category` VALUES ('3', '伺服电机本体设计');

-- ----------------------------
-- Table structure for em_index_detail
-- ----------------------------
DROP TABLE IF EXISTS `em_index_detail`;
CREATE TABLE `em_index_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `index_id` bigint(20) NOT NULL COMMENT '指标关联ID',
  `val` decimal(10,4) DEFAULT NULL COMMENT '自定义指标值',
  `weight` decimal(10,4) DEFAULT NULL COMMENT '自定义指标权重',
  `group_id` bigint(20) DEFAULT NULL COMMENT '自定义分组ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_index_detail
-- ----------------------------
INSERT INTO `em_index_detail` VALUES ('1', '1', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('2', '2', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('3', '3', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('4', '4', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('5', '5', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('6', '6', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('7', '7', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('8', '8', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('9', '9', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('10', '10', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('11', '11', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('12', '12', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('13', '13', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('14', '14', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('15', '15', '1.0000', '2.0000', '1');
INSERT INTO `em_index_detail` VALUES ('16', '16', '1.0000', '2.0000', '1');

-- ----------------------------
-- Table structure for em_index_formula
-- ----------------------------
DROP TABLE IF EXISTS `em_index_formula`;
CREATE TABLE `em_index_formula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL COMMENT '公式名称描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_index_formula
-- ----------------------------
INSERT INTO `em_index_formula` VALUES ('1', '归一化公式1，适用于调速范围、过载能力、功率密度');
INSERT INTO `em_index_formula` VALUES ('2', '归一化公式2，适用于公式1的其他指标');

-- ----------------------------
-- Table structure for em_index_group
-- ----------------------------
DROP TABLE IF EXISTS `em_index_group`;
CREATE TABLE `em_index_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL COMMENT '自定义指标分组名称',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_index_group
-- ----------------------------
INSERT INTO `em_index_group` VALUES ('1', '测试指标', '1', '2020-11-27 22:16:47');

-- ----------------------------
-- Table structure for em_index_template
-- ----------------------------
DROP TABLE IF EXISTS `em_index_template`;
CREATE TABLE `em_index_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL COMMENT '指标名称',
  `best_val` decimal(10,4) DEFAULT NULL COMMENT '最优指标',
  `min_val` decimal(10,4) DEFAULT NULL COMMENT '指标最小值',
  `max_val` decimal(10,4) DEFAULT NULL COMMENT '指标最大值',
  `weight` decimal(10,4) DEFAULT NULL COMMENT '默认权重',
  `unit` varchar(30) DEFAULT NULL COMMENT '单位',
  `category_id` int(11) DEFAULT NULL COMMENT '指标所属分类ID',
  `formula_id` int(11) DEFAULT NULL COMMENT '公式ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_index_template
-- ----------------------------
INSERT INTO `em_index_template` VALUES ('1', '调整时间', '0.0570', '0.0000', '100.0000', '0.0999', 'ms', '1', '2');
INSERT INTO `em_index_template` VALUES ('2', '峰值时间', '0.0530', '0.0000', '70.0000', '0.0828', 'ms', '1', '2');
INSERT INTO `em_index_template` VALUES ('3', '超调量', '0.0300', '0.0000', '30.0000', '0.0654', '%', '1', '2');
INSERT INTO `em_index_template` VALUES ('4', '转速调整率', '6.5400', '0.0000', '100.0000', '0.0657', '%', '1', '2');
INSERT INTO `em_index_template` VALUES ('5', '转速变化率', '6.5000', '0.0000', '100.0000', '0.0655', '%', '1', '2');
INSERT INTO `em_index_template` VALUES ('6', '正反转速差率', '5.4000', '0.0000', '100.0000', '0.0540', '%', '1', '2');
INSERT INTO `em_index_template` VALUES ('7', '调速范围', '0.0140', '2.0000', '25.0000', '0.0560', '倍', '2', '1');
INSERT INTO `em_index_template` VALUES ('8', '过载能力', '0.0300', '2.0000', '3.5000', '0.0467', '倍', '2', '1');
INSERT INTO `em_index_template` VALUES ('9', '转速平均误差', '0.0250', '0.0000', '10.0000', '0.0280', 'r/min', '2', '2');
INSERT INTO `em_index_template` VALUES ('10', '转矩平均误差', '0.0160', '0.0000', '2.0000', '0.0280', 'N·m', '2', '2');
INSERT INTO `em_index_template` VALUES ('11', '转速波动系数', '3.7000', '0.0000', '10.0000', '0.0374', '%', '2', '2');
INSERT INTO `em_index_template` VALUES ('12', '转矩波动系数', '2.3000', '0.0000', '30.0000', '0.0373', '%', '2', '2');
INSERT INTO `em_index_template` VALUES ('13', '功率密度', '0.0870', '0.0200', '0.1500', '0.1055', 'kw/kg', '3', '1');
INSERT INTO `em_index_template` VALUES ('14', '电机体积', '0.0700', '0.0000', '0.0100', '0.0833', 'm3', '3', '2');
INSERT INTO `em_index_template` VALUES ('15', '电机质量', '0.0380', '2.0000', '10.0000', '0.0611', 'kg', '3', '2');
INSERT INTO `em_index_template` VALUES ('16', '电机轴向尺寸', '0.0640', '0.1500', '0.5000', '0.0833', 'm', '3', '2');

-- ----------------------------
-- Table structure for em_product
-- ----------------------------
DROP TABLE IF EXISTS `em_product`;
CREATE TABLE `em_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '产品名称',
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_product
-- ----------------------------
INSERT INTO `em_product` VALUES ('1', '实验数据(1)', '1', '2020-11-24 15:33:29');
INSERT INTO `em_product` VALUES ('2', '实验数据(2)', '1', '2020-11-25 13:09:19');
INSERT INTO `em_product` VALUES ('3', '实验数据(3)', '1', '2020-11-25 13:22:07');
INSERT INTO `em_product` VALUES ('4', '实验数据1', '1', '2020-11-27 19:36:34');

-- ----------------------------
-- Table structure for em_product_attr
-- ----------------------------
DROP TABLE IF EXISTS `em_product_attr`;
CREATE TABLE `em_product_attr` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `unit` varchar(60) NOT NULL COMMENT '单位',
  `unit_desc` varchar(100) DEFAULT NULL COMMENT '单位中文描述',
  `type` bigint(20) NOT NULL COMMENT '属性类型外键',
  `min_coefficient` double(15,4) DEFAULT NULL COMMENT '相对最小单位换算系数',
  PRIMARY KEY (`id`),
  KEY `fk_em_product_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of em_product_attr
-- ----------------------------

-- ----------------------------
-- Table structure for kk_resident
-- ----------------------------
DROP TABLE IF EXISTS `kk_resident`;
CREATE TABLE `kk_resident` (
  `id` bigint(20) NOT NULL,
  `house_holder` varchar(64) DEFAULT NULL COMMENT '户主',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机',
  `building` varchar(64) DEFAULT NULL COMMENT '楼栋',
  `house_no` varchar(64) DEFAULT NULL COMMENT '房号',
  `area` double(10,2) DEFAULT NULL COMMENT '面积',
  `car_no` varchar(60) DEFAULT NULL COMMENT '车牌号',
  `id_card_no` varchar(60) DEFAULT NULL COMMENT '身份证',
  `remarks` varchar(255) DEFAULT NULL COMMENT '常驻人口、年龄、等等',
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kk_resident
-- ----------------------------

-- ----------------------------
-- Table structure for meter
-- ----------------------------
DROP TABLE IF EXISTS `meter`;
CREATE TABLE `meter` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meter
-- ----------------------------
INSERT INTO `meter` VALUES ('1', '表1');
INSERT INTO `meter` VALUES ('2', '表2');
INSERT INTO `meter` VALUES ('3', '表计3');
INSERT INTO `meter` VALUES ('4', '表计4');
INSERT INTO `meter` VALUES ('5', '表计5');
INSERT INTO `meter` VALUES ('6', '表计6');
INSERT INTO `meter` VALUES ('7', '表计7');
INSERT INTO `meter` VALUES ('8', '表计8');
INSERT INTO `meter` VALUES ('9', '表计9');
INSERT INTO `meter` VALUES ('10', '表计10');
INSERT INTO `meter` VALUES ('11', '表计11');

-- ----------------------------
-- Table structure for oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_client`;
CREATE TABLE `oauth2_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(100) DEFAULT NULL COMMENT '客戶端名稱',
  `client_id` varchar(100) DEFAULT NULL COMMENT '客戶端ID',
  `client_secret` varchar(100) DEFAULT NULL COMMENT '客户端安全key',
  PRIMARY KEY (`id`),
  KEY `idx_oauth2_client_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth2_client
-- ----------------------------
INSERT INTO `oauth2_client` VALUES ('1', 'oauth-client', 'c1ebe466-1cdc-4bd3-ab69-77c3561b9dee', 'd8346ea2-6017-43ed-ad68-19c0f971738b');
INSERT INTO `oauth2_client` VALUES ('2', 'kekexiaocheng', 'Igupf8ca', '3180d4fdc4733638bdc4296ef6cf47ed859dbe56');
INSERT INTO `oauth2_client` VALUES ('3', 'wasion', 'Eotyf0d4', '84ce88b2d9b974c41aa649df204f36e71fadaa7c');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('kingScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('kingScheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('kingScheduler', 'LAPTOP-3H9KE96D1577271528052', '1577271528216', '2000');
INSERT INTO `qrtz_scheduler_state` VALUES ('QuartzScheduler', 'LAPTOP-3H9KE96D1596678784737', '1596678961194', '2000');
INSERT INTO `qrtz_scheduler_state` VALUES ('QuartzScheduler', 'LAPTOP-3H9KE96D1596678794670', '1596678960976', '2000');
INSERT INTO `qrtz_scheduler_state` VALUES ('schedulerFactoryBean', 'LAPTOP-3H9KE96D1596678785549', '1596678961633', '2000');
INSERT INTO `qrtz_scheduler_state` VALUES ('schedulerFactoryBean', 'LAPTOP-3H9KE96D1596678795777', '1596678961816', '2000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_info`;
CREATE TABLE `sys_app_info` (
  `app_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `app_key` varchar(255) NOT NULL,
  `app_secret` varchar(1000) NOT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`app_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `project_name` varchar(100) DEFAULT NULL COMMENT '项目名',
  `value` varchar(100) NOT NULL COMMENT '配置值',
  `text` varchar(100) NOT NULL COMMENT '配置描述',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `content` varchar(2000) DEFAULT NULL COMMENT '存储超大数据',
  `use_flag` int(1) DEFAULT '1' COMMENT '是否启用',
  UNIQUE KEY `unique_sys_conf_text` (`text`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('KingSystem', '30', 'cache.redis.timeout', 'redis缓存超时', '', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_sn` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_no` int(4) NOT NULL COMMENT '分类',
  `dict_no` int(4) NOT NULL COMMENT '字典值',
  `dict_desc` varchar(120) NOT NULL COMMENT '字典描述',
  `disp_order` int(4) DEFAULT '0' COMMENT '字典排序',
  `parent_sn` varchar(255) NOT NULL COMMENT '父ID',
  `use_flag` varchar(255) DEFAULT '1' COMMENT '是否使用 0-未使用 1-使用',
  PRIMARY KEY (`dict_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('100', '100', '-1', '用户状态', '0', '0', '1');
INSERT INTO `sys_dict` VALUES ('101', '100', '0', '正常', '1', '100', '1');
INSERT INTO `sys_dict` VALUES ('102', '100', '1', '锁定', '2', '100', '1');
INSERT INTO `sys_dict` VALUES ('200', '200', '0', '设备类型', '0', '0', '1');
INSERT INTO `sys_dict` VALUES ('201', '200', '1', '笔记本电脑', '1', '200', '1');
INSERT INTO `sys_dict` VALUES ('202', '200', '2', '手机', '2', '200', '0');
INSERT INTO `sys_dict` VALUES ('300', '300', '-1', '资源类型', '0', '0', '1');
INSERT INTO `sys_dict` VALUES ('301', '300', '0', '菜单', '1', '300', '1');
INSERT INTO `sys_dict` VALUES ('302', '300', '1', 'TAB页', '2', '300', '1');
INSERT INTO `sys_dict` VALUES ('303', '300', '2', '按钮', '3', '300', '1');
INSERT INTO `sys_dict` VALUES ('310', '310', '-1', '是否使用', '0', '0', '1');
INSERT INTO `sys_dict` VALUES ('311', '311', '0', '停用', '1', '310', '1');
INSERT INTO `sys_dict` VALUES ('312', '312', '1', '使用', '2', '310', '1');

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '资源名称-唯一',
  `url` varchar(255) DEFAULT NULL,
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类型 0-菜单 1-tab页 2-按钮',
  `pid` bigint(11) NOT NULL,
  `permission` varchar(60) DEFAULT NULL COMMENT '权限标志（格式：模块_操作）',
  `icon` varchar(60) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '系统管理', '#', '0', '-1', '', '&#xe641;');
INSERT INTO `sys_resources` VALUES ('2', '用户管理', 'sys/user/toMain', '0', '1', '', '&#xe620;');
INSERT INTO `sys_resources` VALUES ('3', '角色管理', 'sys/role/toMain', '0', '1', '', '&#xe618;');
INSERT INTO `sys_resources` VALUES ('4', '资源管理', 'sys/resource/toMain', '0', '1', '', '&#xe62d;');
INSERT INTO `sys_resources` VALUES ('5', '新增', 'sys_user_add', '2', '2', 'user_add', '');
INSERT INTO `sys_resources` VALUES ('6', '修改', 'sys_user_update', '2', '2', 'user_update', '');
INSERT INTO `sys_resources` VALUES ('7', '删除', 'sys_user_delete', '2', '2', 'user_delete', '');
INSERT INTO `sys_resources` VALUES ('8', '查询', 'sys_user_query', '2', '2', 'user_query', '');
INSERT INTO `sys_resources` VALUES ('9', '第三方', '#', '0', '-1', '', '');
INSERT INTO `sys_resources` VALUES ('10', '客户端管理', 'sys/client/toMain', '0', '9', '', '');
INSERT INTO `sys_resources` VALUES ('15', '字典管理', 'sys/dict/toMain', '0', '1', null, '&#xe63b;');
INSERT INTO `sys_resources` VALUES ('16', '任务管理', 'sys/task/toMain', '0', '1', 'menu:taskMgr', '');
INSERT INTO `sys_resources` VALUES ('17', '游戏', '#', '0', '-1', 'GAME:DOUBLECOLORBALL', '&#xe61d;');
INSERT INTO `sys_resources` VALUES ('18', '双色球', 'game/doubleColorBall/toMain', '0', '17', 'GAME:DOUBLECOLORBALL', '');
INSERT INTO `sys_resources` VALUES ('19', '系统配置', 'sys/config/toMain', '0', '1', null, '&#xe605;');
INSERT INTO `sys_resources` VALUES ('20', '投票模板', 'game/voteTemplate/toMain', '0', '17', null, '&#xe60e;');
INSERT INTO `sys_resources` VALUES ('21', '投票组管理', 'game/voteItemGroup/toMain', '0', '17', null, '&#xe60e;');
INSERT INTO `sys_resources` VALUES ('22', '投票项管理', 'game/voteItem/toMain', '0', '17', null, '&#xe60e;');
INSERT INTO `sys_resources` VALUES ('23', '投票管理', 'game/vote/toMain', '0', '17', null, '&#xe60e;');
INSERT INTO `sys_resources` VALUES ('24', '投票统计', 'game/voteStatistics/toMain', '0', '17', null, '&#xe61d;');
INSERT INTO `sys_resources` VALUES ('27', '新增', 'sys_role_add', '2', '3', null, '');
INSERT INTO `sys_resources` VALUES ('28', '编辑', 'sys_role_update', '2', '3', null, '');
INSERT INTO `sys_resources` VALUES ('29', '删除', 'sys_role_delete', '2', '3', null, '');
INSERT INTO `sys_resources` VALUES ('30', '新增', 'sys_resource_add', '2', '4', null, '');
INSERT INTO `sys_resources` VALUES ('31', '编辑', 'sys_resource_update', '2', '4', null, '');
INSERT INTO `sys_resources` VALUES ('32', '删除', 'sys_resource_delete', '2', '4', null, '');
INSERT INTO `sys_resources` VALUES ('33', '授权', 'sys_role_auth', '2', '3', null, null);
INSERT INTO `sys_resources` VALUES ('34', 'Echarts', 'sys/echarts/toMain', '0', '1', null, '');
INSERT INTO `sys_resources` VALUES ('45', '伺服电机', '#', '0', '-1', null, '&#xe606;');
INSERT INTO `sys_resources` VALUES ('46', '基本参数', 'em/baseParams/toMain', '0', '45', null, '&#xe625;');
INSERT INTO `sys_resources` VALUES ('47', '指标管理', 'em/indexMgr/toMain', '0', '45', null, '&#xe6a9;');
INSERT INTO `sys_resources` VALUES ('48', '我的指标', 'em/myIndex/toMain', '0', '45', null, '&#xe66a;');
INSERT INTO `sys_resources` VALUES ('49', '性能评估', 'em/analysis/toMain', '0', '45', null, '&#xe7bf;');
INSERT INTO `sys_resources` VALUES ('50', '项目数据', 'em/product/toMain', '0', '45', null, '&#xe630;');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `role_id` bigint(11) NOT NULL,
  `res_id` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('1', '1');
INSERT INTO `sys_role_resources` VALUES ('1', '2');
INSERT INTO `sys_role_resources` VALUES ('1', '5');
INSERT INTO `sys_role_resources` VALUES ('1', '6');
INSERT INTO `sys_role_resources` VALUES ('1', '7');
INSERT INTO `sys_role_resources` VALUES ('1', '8');
INSERT INTO `sys_role_resources` VALUES ('1', '3');
INSERT INTO `sys_role_resources` VALUES ('1', '27');
INSERT INTO `sys_role_resources` VALUES ('1', '28');
INSERT INTO `sys_role_resources` VALUES ('1', '29');
INSERT INTO `sys_role_resources` VALUES ('1', '33');
INSERT INTO `sys_role_resources` VALUES ('1', '4');
INSERT INTO `sys_role_resources` VALUES ('1', '30');
INSERT INTO `sys_role_resources` VALUES ('1', '31');
INSERT INTO `sys_role_resources` VALUES ('1', '32');
INSERT INTO `sys_role_resources` VALUES ('1', '15');
INSERT INTO `sys_role_resources` VALUES ('1', '16');
INSERT INTO `sys_role_resources` VALUES ('1', '19');
INSERT INTO `sys_role_resources` VALUES ('1', '34');
INSERT INTO `sys_role_resources` VALUES ('1', '45');
INSERT INTO `sys_role_resources` VALUES ('1', '46');
INSERT INTO `sys_role_resources` VALUES ('1', '47');
INSERT INTO `sys_role_resources` VALUES ('1', '48');
INSERT INTO `sys_role_resources` VALUES ('1', '49');
INSERT INTO `sys_role_resources` VALUES ('1', '50');

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `group_name` varchar(64) DEFAULT NULL COMMENT '任务组名',
  `cron_express` varchar(120) NOT NULL COMMENT 'cron表达式',
  `description` varchar(120) DEFAULT NULL COMMENT '描述',
  `current_state` tinyint(1) NOT NULL COMMENT '当前状态 0-准备 1-开始 2-暂停 3-恢复 4-结束',
  `default_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认状态 0-准备 1-开始 2-暂停 3-恢复 4-结束',
  `last_time` bigint(11) DEFAULT NULL COMMENT '最后执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_log`;
CREATE TABLE `sys_task_log` (
  `id` bigint(11) NOT NULL,
  `task_id` bigint(11) NOT NULL COMMENT '任务编号',
  `before_state` tinyint(1) NOT NULL COMMENT '之前状态 0-准备 1-开始 2-暂停 3-恢复 4-结束',
  `current_state` tinyint(1) NOT NULL COMMENT '当前状态 0-准备 1-开始 2-暂停 3-恢复 4-结束',
  `action_time` bigint(11) NOT NULL COMMENT '执行的时间',
  `user_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '登录密码',
  `name` varchar(60) DEFAULT NULL COMMENT '用户真实姓名',
  `id_card_num` varchar(60) DEFAULT NULL COMMENT '用户身份证号',
  `state` int(1) DEFAULT '0' COMMENT '用户状态：0:正常状态,1：用户被锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `id_card_num` (`id_card_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '曹浩权', '43052119880510003X', '1');
INSERT INTO `sys_user` VALUES ('8', 'yangtw', 'e10adc3949ba59abbe56e057f20f883e', '杨廷伟', '4546546555', '0');
INSERT INTO `sys_user` VALUES ('9', 'caohq', 'e10adc3949ba59abbe56e057f20f883e', '曹操', '43565458565', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(11) NOT NULL,
  `rid` bigint(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('7', '2');
INSERT INTO `sys_user_role` VALUES ('8', '2');
INSERT INTO `sys_user_role` VALUES ('9', '3');

-- ----------------------------
-- Table structure for terminal
-- ----------------------------
DROP TABLE IF EXISTS `terminal`;
CREATE TABLE `terminal` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of terminal
-- ----------------------------

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) NOT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `start_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `template_id` bigint(20) NOT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) DEFAULT '0' COMMENT '0-未开始 1-进行中 2-暂停 3-结束 4-废弃',
  PRIMARY KEY (`id`),
  KEY `fk_vote_template_id` (`template_id`),
  CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`template_id`) REFERENCES `vote_template` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('4', '可可小城业主大会投票', '可可小城业主大会投票', '2020-04-18 03:29:01', '2020-04-19 00:00:00', '9', '1', '2020-04-18 03:29:01', '1');

-- ----------------------------
-- Table structure for vote_item
-- ----------------------------
DROP TABLE IF EXISTS `vote_item`;
CREATE TABLE `vote_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `value` varchar(60) NOT NULL,
  `description` varchar(120) DEFAULT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vote_item_group_id` (`group_id`),
  CONSTRAINT `vote_item_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `vote_item_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_item
-- ----------------------------
INSERT INTO `vote_item` VALUES ('19', '满意', '0', null, '12');
INSERT INTO `vote_item` VALUES ('20', '不满意', '1', null, '12');
INSERT INTO `vote_item` VALUES ('21', '肖四军', '0', null, '13');
INSERT INTO `vote_item` VALUES ('22', '陈湘玉', '1', null, '13');
INSERT INTO `vote_item` VALUES ('23', '杨廷伟', '2', null, '13');
INSERT INTO `vote_item` VALUES ('24', '郭兆福', '3', null, '13');
INSERT INTO `vote_item` VALUES ('25', '梁光明', '4', null, '13');

-- ----------------------------
-- Table structure for vote_item_group
-- ----------------------------
DROP TABLE IF EXISTS `vote_item_group`;
CREATE TABLE `vote_item_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '0-单选 1-多选',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_item_group
-- ----------------------------
INSERT INTO `vote_item_group` VALUES ('12', '物业满意度调查', '0');
INSERT INTO `vote_item_group` VALUES ('13', '楼栋长选举', '1');

-- ----------------------------
-- Table structure for vote_record
-- ----------------------------
DROP TABLE IF EXISTS `vote_record`;
CREATE TABLE `vote_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vote_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `vote_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_record
-- ----------------------------
INSERT INTO `vote_record` VALUES ('2', '4', '1', '2020-04-17 21:56:37');

-- ----------------------------
-- Table structure for vote_record_detail
-- ----------------------------
DROP TABLE IF EXISTS `vote_record_detail`;
CREATE TABLE `vote_record_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `item_ids` varchar(100) NOT NULL,
  `record_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_record_detail
-- ----------------------------
INSERT INTO `vote_record_detail` VALUES ('1', '12', '0', '2');
INSERT INTO `vote_record_detail` VALUES ('2', '13', '0,1,2,3,4', '2');

-- ----------------------------
-- Table structure for vote_template
-- ----------------------------
DROP TABLE IF EXISTS `vote_template`;
CREATE TABLE `vote_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_template
-- ----------------------------
INSERT INTO `vote_template` VALUES ('9', '物业满意度调查');

-- ----------------------------
-- Table structure for vote_template_group
-- ----------------------------
DROP TABLE IF EXISTS `vote_template_group`;
CREATE TABLE `vote_template_group` (
  `temp_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`temp_id`,`group_id`),
  KEY `fk_vote_group_id` (`group_id`),
  CONSTRAINT `vote_template_group_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `vote_item_group` (`id`),
  CONSTRAINT `vote_template_group_ibfk_2` FOREIGN KEY (`temp_id`) REFERENCES `vote_template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_template_group
-- ----------------------------
INSERT INTO `vote_template_group` VALUES ('9', '12');
INSERT INTO `vote_template_group` VALUES ('9', '13');
