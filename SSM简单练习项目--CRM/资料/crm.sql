/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2016-05-12 00:07:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_dict
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
  `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
  `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
  `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
  `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目代码(可为空)',
  `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
  `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
  `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dict
-- ----------------------------
INSERT INTO `base_dict` VALUES ('1', '001', '客户行业', '教育培训 ', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('10', '003', '公司性质', '民企', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('12', '004', '年营业额', '1-10万', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('13', '004', '年营业额', '10-20万', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('14', '004', '年营业额', '20-50万', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('15', '004', '年营业额', '50-100万', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('16', '004', '年营业额', '100-500万', null, '5', '1', null);
INSERT INTO `base_dict` VALUES ('17', '004', '年营业额', '500-1000万', null, '6', '1', null);
INSERT INTO `base_dict` VALUES ('18', '005', '客户状态', '基础客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('19', '005', '客户状态', '潜在客户', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('2', '001', '客户行业', '电子商务', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('20', '005', '客户状态', '成功客户', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('21', '005', '客户状态', '无效客户', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('22', '006', '客户级别', '普通客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('23', '006', '客户级别', 'VIP客户', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('24', '007', '商机状态', '意向客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('25', '007', '商机状态', '初步沟通', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('26', '007', '商机状态', '深度沟通', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('27', '007', '商机状态', '签订合同', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('3', '001', '客户行业', '对外贸易', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('30', '008', '商机类型', '新业务', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('31', '008', '商机类型', '现有业务', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('32', '009', '商机来源', '电话营销', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('33', '009', '商机来源', '网络营销', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('34', '009', '商机来源', '推广活动', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('4', '001', '客户行业', '酒店旅游', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('5', '001', '客户行业', '房地产', null, '5', '1', null);
INSERT INTO `base_dict` VALUES ('6', '002', '客户信息来源', '电话营销', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('7', '002', '客户信息来源', '网络营销', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('8', '003', '公司性质', '合资', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('9', '003', '公司性质', '国企', null, '2', '1', null);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
  `cust_create_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  `cust_zipcode` varchar(10) DEFAULT NULL,
  `cust_address` varchar(100) DEFAULT NULL,
  `cust_createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`cust_id`),
  KEY `FK_cst_customer_source` (`cust_source`),
  KEY `FK_cst_customer_industry` (`cust_industry`),
  KEY `FK_cst_customer_level` (`cust_level`),
  KEY `FK_cst_customer_user_id` (`cust_user_id`),
  KEY `FK_cst_customer_create_id` (`cust_create_id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('14', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:01');
INSERT INTO `customer` VALUES ('15', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:01');
INSERT INTO `customer` VALUES ('16', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:01');
INSERT INTO `customer` VALUES ('17', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:02');
INSERT INTO `customer` VALUES ('22', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:03');
INSERT INTO `customer` VALUES ('24', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:03');
INSERT INTO `customer` VALUES ('25', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:03');
INSERT INTO `customer` VALUES ('26', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:03');
INSERT INTO `customer` VALUES ('28', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:04');
INSERT INTO `customer` VALUES ('29', '令狐冲', null, null, '7', '1', '23', '任盈盈', '0108888886', '13888888886', '6123456', '北京三里桥6', '2016-04-08 16:32:04');
INSERT INTO `customer` VALUES ('30', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:04');
INSERT INTO `customer` VALUES ('31', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:04');
INSERT INTO `customer` VALUES ('33', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:04');
INSERT INTO `customer` VALUES ('34', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:05');
INSERT INTO `customer` VALUES ('35', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:05');
INSERT INTO `customer` VALUES ('36', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:05');
INSERT INTO `customer` VALUES ('37', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:05');
INSERT INTO `customer` VALUES ('38', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:05');
INSERT INTO `customer` VALUES ('39', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:06');
INSERT INTO `customer` VALUES ('40', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:06');
INSERT INTO `customer` VALUES ('41', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:06');
INSERT INTO `customer` VALUES ('42', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:06');
INSERT INTO `customer` VALUES ('43', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:06');
INSERT INTO `customer` VALUES ('44', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:07');
INSERT INTO `customer` VALUES ('45', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:07');
INSERT INTO `customer` VALUES ('46', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:07');
INSERT INTO `customer` VALUES ('47', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:07');
INSERT INTO `customer` VALUES ('48', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:07');
INSERT INTO `customer` VALUES ('49', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:07');
INSERT INTO `customer` VALUES ('50', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:08');
INSERT INTO `customer` VALUES ('51', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:08');
INSERT INTO `customer` VALUES ('52', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:08');
INSERT INTO `customer` VALUES ('53', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:08');
INSERT INTO `customer` VALUES ('54', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:08');
INSERT INTO `customer` VALUES ('55', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:08');
INSERT INTO `customer` VALUES ('56', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:09');
INSERT INTO `customer` VALUES ('57', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:09');
INSERT INTO `customer` VALUES ('58', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:09');
INSERT INTO `customer` VALUES ('59', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:29');
INSERT INTO `customer` VALUES ('60', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:29');
INSERT INTO `customer` VALUES ('61', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:29');
INSERT INTO `customer` VALUES ('62', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:29');
INSERT INTO `customer` VALUES ('63', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:30');
INSERT INTO `customer` VALUES ('64', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:30');
INSERT INTO `customer` VALUES ('65', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:30');
INSERT INTO `customer` VALUES ('66', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:30');
INSERT INTO `customer` VALUES ('67', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:30');
INSERT INTO `customer` VALUES ('68', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:30');
INSERT INTO `customer` VALUES ('69', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:31');
INSERT INTO `customer` VALUES ('70', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:31');
INSERT INTO `customer` VALUES ('71', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:31');
INSERT INTO `customer` VALUES ('72', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:31');
INSERT INTO `customer` VALUES ('73', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:31');
INSERT INTO `customer` VALUES ('74', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:32');
INSERT INTO `customer` VALUES ('75', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:32');
INSERT INTO `customer` VALUES ('76', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:32');
INSERT INTO `customer` VALUES ('77', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:32');
INSERT INTO `customer` VALUES ('78', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:32');
INSERT INTO `customer` VALUES ('79', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:32');
INSERT INTO `customer` VALUES ('80', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:33');
INSERT INTO `customer` VALUES ('81', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:33');
INSERT INTO `customer` VALUES ('82', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:33');
INSERT INTO `customer` VALUES ('83', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:33');
INSERT INTO `customer` VALUES ('84', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:33');
INSERT INTO `customer` VALUES ('85', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:33');
INSERT INTO `customer` VALUES ('86', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:34');
INSERT INTO `customer` VALUES ('87', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:34');
INSERT INTO `customer` VALUES ('88', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:34');
INSERT INTO `customer` VALUES ('89', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:34');
INSERT INTO `customer` VALUES ('90', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:34');
INSERT INTO `customer` VALUES ('91', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:34');
INSERT INTO `customer` VALUES ('92', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:35');
INSERT INTO `customer` VALUES ('93', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:35');
INSERT INTO `customer` VALUES ('94', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:35');
INSERT INTO `customer` VALUES ('95', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:35');
INSERT INTO `customer` VALUES ('96', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:35');
INSERT INTO `customer` VALUES ('97', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:36');
INSERT INTO `customer` VALUES ('98', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:36');
INSERT INTO `customer` VALUES ('99', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:36');
INSERT INTO `customer` VALUES ('100', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:36');
INSERT INTO `customer` VALUES ('101', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:36');
INSERT INTO `customer` VALUES ('102', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:36');
INSERT INTO `customer` VALUES ('103', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:37');
INSERT INTO `customer` VALUES ('104', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:37');
INSERT INTO `customer` VALUES ('105', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:37');
INSERT INTO `customer` VALUES ('106', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:37');
INSERT INTO `customer` VALUES ('107', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:37');
INSERT INTO `customer` VALUES ('108', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:38');
INSERT INTO `customer` VALUES ('109', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:38');
INSERT INTO `customer` VALUES ('110', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:38');
INSERT INTO `customer` VALUES ('111', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:38');
INSERT INTO `customer` VALUES ('112', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:38');
INSERT INTO `customer` VALUES ('113', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:38');
INSERT INTO `customer` VALUES ('114', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:39');
INSERT INTO `customer` VALUES ('115', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:39');
INSERT INTO `customer` VALUES ('116', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:39');
INSERT INTO `customer` VALUES ('117', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:39');
INSERT INTO `customer` VALUES ('118', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:39');
INSERT INTO `customer` VALUES ('119', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:40');
INSERT INTO `customer` VALUES ('120', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:40');
INSERT INTO `customer` VALUES ('121', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:40');
INSERT INTO `customer` VALUES ('122', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:40');
INSERT INTO `customer` VALUES ('123', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:40');
INSERT INTO `customer` VALUES ('124', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:40');
INSERT INTO `customer` VALUES ('125', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:41');
INSERT INTO `customer` VALUES ('126', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:41');
INSERT INTO `customer` VALUES ('127', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:41');
INSERT INTO `customer` VALUES ('128', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:41');
INSERT INTO `customer` VALUES ('129', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:41');
INSERT INTO `customer` VALUES ('130', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:42');
INSERT INTO `customer` VALUES ('131', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:42');
INSERT INTO `customer` VALUES ('132', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:42');
INSERT INTO `customer` VALUES ('133', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:42');
INSERT INTO `customer` VALUES ('134', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:42');
INSERT INTO `customer` VALUES ('135', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:42');
INSERT INTO `customer` VALUES ('136', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:43');
INSERT INTO `customer` VALUES ('137', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:43');
INSERT INTO `customer` VALUES ('138', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:43');
INSERT INTO `customer` VALUES ('139', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:43');
INSERT INTO `customer` VALUES ('140', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:43');
INSERT INTO `customer` VALUES ('141', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:44');
INSERT INTO `customer` VALUES ('142', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:44');
INSERT INTO `customer` VALUES ('143', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:44');
INSERT INTO `customer` VALUES ('144', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:44');
INSERT INTO `customer` VALUES ('145', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:44');
INSERT INTO `customer` VALUES ('146', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:44');
INSERT INTO `customer` VALUES ('147', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:45');
INSERT INTO `customer` VALUES ('148', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:45');
INSERT INTO `customer` VALUES ('149', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:45');
INSERT INTO `customer` VALUES ('150', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:45');
INSERT INTO `customer` VALUES ('151', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:45');
INSERT INTO `customer` VALUES ('152', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:46');
INSERT INTO `customer` VALUES ('153', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:46');
INSERT INTO `customer` VALUES ('154', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:46');
INSERT INTO `customer` VALUES ('155', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:46');
INSERT INTO `customer` VALUES ('156', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:46');
INSERT INTO `customer` VALUES ('157', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:46');
INSERT INTO `customer` VALUES ('158', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:47');
INSERT INTO `customer` VALUES ('159', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:47');
INSERT INTO `customer` VALUES ('160', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:47');
INSERT INTO `customer` VALUES ('161', '马云', null, null, '6', '2', '22', '马化腾', '0108888887', '13888888888', '123456', '北京三里桥', '2016-04-08 16:32:47');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` varchar(32) NOT NULL COMMENT '用户账号',
  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', 'm0003', '小军', '123', '1');
INSERT INTO `sys_user` VALUES ('6', 'm0001', '小红', '123', '1');
INSERT INTO `sys_user` VALUES ('7', 'm0001', '小明', '123', '1');
INSERT INTO `sys_user` VALUES ('8', 'm0001', '小红', '123', '1');
