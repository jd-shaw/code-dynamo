/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : code-dynamo

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 18/07/2023 11:54:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for iam_client
-- ----------------------------
DROP TABLE IF EXISTS `iam_client`;
CREATE TABLE `iam_client` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enable` int NOT NULL,
  `is_system` int NOT NULL,
  `login_type_ids` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_client
-- ----------------------------
BEGIN;
INSERT INTO `iam_client` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `code`, `description`, `enable`, `is_system`, `login_type_ids`, `name`) VALUES ('1', NULL, NULL, NULL, NULL, 0, 'admin', NULL, 1, 1, '1D290c9sw35o1b4S7k1D7sx05K', 'PC_Admin');
COMMIT;

-- ----------------------------
-- Table structure for iam_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `iam_data_scope`;
CREATE TABLE `iam_data_scope` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_data_scope
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_data_scope_dept
-- ----------------------------
DROP TABLE IF EXISTS `iam_data_scope_dept`;
CREATE TABLE `iam_data_scope_dept` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `data_scope_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dept_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_data_scope_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_data_scope_user
-- ----------------------------
DROP TABLE IF EXISTS `iam_data_scope_user`;
CREATE TABLE `iam_data_scope_user` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `data_scope_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_data_scope_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_dept
-- ----------------------------
DROP TABLE IF EXISTS `iam_dept`;
CREATE TABLE `iam_dept` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dept_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fax` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `org_category` int DEFAULT NULL,
  `org_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort_no` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_login_type
-- ----------------------------
DROP TABLE IF EXISTS `iam_login_type`;
CREATE TABLE `iam_login_type` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `captcha` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enable` int DEFAULT '0',
  `is_system` int DEFAULT '0',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pwd_err_num` int DEFAULT '0',
  `timeout` bigint DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_login_type
-- ----------------------------
BEGIN;
INSERT INTO `iam_login_type` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `captcha`, `code`, `description`, `enable`, `is_system`, `name`, `pwd_err_num`, `timeout`, `type`) VALUES ('1D290c9sw35o1b4S7k1D7sx05K', NULL, NULL, NULL, NULL, 0, 0, 'password', NULL, 1, 0, '账号密码登陆', -1, 120, 'password');
COMMIT;

-- ----------------------------
-- Table structure for iam_perm_menu
-- ----------------------------
DROP TABLE IF EXISTS `iam_perm_menu`;
CREATE TABLE `iam_perm_menu` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `admin` bit(1) NOT NULL,
  `client_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `effect` bit(1) NOT NULL,
  `hidden` bit(1) NOT NULL,
  `hidden_header_content` bit(1) NOT NULL,
  `hide_children_in_menu` bit(1) NOT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keep_alive` bit(1) NOT NULL,
  `menu_type` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perm_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort_no` double DEFAULT NULL,
  `target_outside` bit(1) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_perm_menu
-- ----------------------------
BEGIN;
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('106H8y9o6r4a8S6x4t295B7x8U', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:50:42.577000', NULL, '2023-07-18 10:50:42.577000', 0, b'0', 'admin', '/modules/system/loginType/LoginTypeList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'LoginTypeList', '1w6B889Y6448875I9O6k3z6C9Z', '/system/loginType', NULL, '', NULL, 2, b'0', '登录方式');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('146D8I9I6A4i8O457Y6g9D4k5q', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:47:56.945000', NULL, '2023-07-18 10:47:56.945000', 0, b'0', 'admin', '/modules/system/dept/DeptList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'DeptList', '1W6i8Y9M6A4B8F3p4A9m5a4o49', '/system/user/dept', NULL, '', NULL, 1, b'0', '部门管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('156X8J9s6J4G8z2r969S0T0w2y', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:44:59.002000', NULL, '2023-07-18 10:44:59.002000', 0, b'0', 'admin', '/modules/system/path/PermPathList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'PermPathList', '1V6p8F9X644U8I1r8r28360F95', '/system/permission/path', NULL, '', NULL, 1, b'0', '请求权限管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1L6r809J6N4A8D2N3R458V0R95', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:43:54.809000', NULL, '2023-07-18 10:43:54.809000', 0, b'0', 'admin', '/modules/system/role/RoleList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'RoleList', '1V6p8F9X644U8I1r8r28360F95', '/system/permission/role', NULL, '', NULL, 0, b'0', '角色管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1O6K849r6B4Q5r2v6z3U0x6a8j', NULL, NULL, NULL, NULL, 0, b'0', 'admin', 'Layout', b'0', b'0', b'0', b'0', 'ant-design:setting-outlined', b'1', 0, 'System', NULL, '/system', NULL, '/system1/client', NULL, -99999, b'0', '系统管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1Q6j88976W4j8o507t0o766Q07', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:49:30.758000', NULL, '2023-07-18 10:49:30.758000', 0, b'0', 'admin', '/modules/system/param/SystemParamList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'SystemParamList', '1q6j8w9X6U4T8j5904314z4i93', '/system/config/param', NULL, '', NULL, 3, b'0', '系统参数');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1q6j8w9X6U4T8j5904314z4i93', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:48:23.449000', NULL, '2023-07-18 10:48:23.449000', 0, b'0', 'admin', 'Layout', b'0', b'0', b'0', b'0', '', b'1', 1, 'SystemConfig', '1O6K849r6B4Q5r2v6z3U0x6a8j', '/system/config', NULL, '', NULL, 3, b'0', '系统配置');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1r618R9868488S629m585H0Z8n', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:51:35.508000', NULL, '2023-07-18 10:51:35.508000', 0, b'0', 'admin', 'Layout', b'0', b'0', b'0', b'0', 'ant-design:monitor-outlined', b'1', 0, 'Monitor', NULL, '/monitor', NULL, '', NULL, 1, b'0', '系统监控');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1r6W8F9f6H4G807e1c82468O8Y', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:51:58.488000', NULL, '2023-07-18 10:51:58.488000', 0, b'0', 'admin', '', b'0', b'0', b'0', b'0', '', b'1', 1, 'ApiSwagger', '1r618R9868488S629m585H0Z8n', 'http://127.0.0.1:8000/doc.html', NULL, '', NULL, 0, b'0', '接口文档');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1S6e8N976v4V8i7r9a5y2G4C4D', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:53:15.244000', NULL, '2023-07-18 10:53:15.244000', 0, b'0', 'admin', '/modules/monitor/redis/RedisInfoMonitor.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'RedisInfoMonitor', '1r618R9868488S629m585H0Z8n', '/monitor/redis', NULL, '', NULL, 3, b'0', 'Redis监控');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1T6X879J6v4y8I6R13924L983y', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:50:19.493000', NULL, '2023-07-18 10:50:19.493000', 0, b'0', 'admin', '/modules/system/client/ClientList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'ClientList', '1w6B889Y6448875I9O6k3z6C9Z', '/system/client', NULL, '', NULL, 0, b'0', '终端管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1V6p8F9X644U8I1r8r28360F95', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:43:02.309000', NULL, '2023-07-18 10:43:02.309000', 0, b'0', 'admin', 'Layout', b'0', b'0', b'0', b'0', '', b'1', 1, 'Permission', '1O6K849r6B4Q5r2v6z3U0x6a8j', '/system/permission', NULL, '', NULL, 1, b'0', '权限管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1w6B889Y6448875I9O6k3z6C9Z', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:49:56.369000', NULL, '2023-07-18 10:49:56.369000', 0, b'0', 'admin', 'Layout', b'0', b'0', b'0', b'0', '', b'1', 1, 'Auth', '1O6K849r6B4Q5r2v6z3U0x6a8j', '/system/auth', NULL, '', NULL, 4, b'0', '认证管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1W6C8T9y6i4D8e4x5y5a7O2e3b', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:47:35.723000', NULL, '2023-07-18 10:47:35.723000', 0, b'0', 'admin', '/modules/system/user/UserList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'UserList', '1W6i8Y9M6A4B8F3p4A9m5a4o49', '/system/user/info', NULL, '', NULL, 0, b'0', '用户管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1W6i8Y9M6A4B8F3p4A9m5a4o49', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:45:49.544000', NULL, '2023-07-18 10:45:49.544000', 0, b'0', 'admin', 'Layout', b'0', b'0', b'0', b'0', '', b'1', 1, 'UserAuth', '1O6K849r6B4Q5r2v6z3U0x6a8j', '/system/user', NULL, '', NULL, 2, b'0', '用户信息');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1W6T8b9U6r4h865C4W712K7x41', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:49:07.273000', NULL, '2023-07-18 10:49:07.273000', 0, b'0', 'admin', '/modules/system/quartz/QuartzJobList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'QuartzJobList', '1q6j8w9X6U4T8j5904314z4i93', '/system/config/quartz', NULL, '', NULL, 1, b'0', '定时任务');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1Y6a8S9R6H468L3s2r4N194x04', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:45:24.140000', NULL, '2023-07-18 10:45:24.140000', 0, b'0', 'admin', '/modules/system/scope/DataScopeList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'DataScopeList', '1V6p8F9X644U8I1r8r28360F95', '/system/permission/scope', NULL, '', NULL, 2, b'0', '数据范围权限');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1y6M8K9F6U488K5r2S7Q8o338f', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:48:47.837000', NULL, '2023-07-18 10:48:47.837000', 0, b'0', 'admin', '/modules/system/dict/DictList.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'DictList', '1q6j8w9X6U4T8j5904314z4i93', '/system/config/dict', NULL, '', NULL, 0, b'0', '数据字典');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1z698v9W6b4L5r286N3x0Y7c5f', NULL, NULL, NULL, NULL, 0, b'0', 'admin', '/modules/system/menu/MenuList.vue', b'0', b'0', b'0', b'0', NULL, b'1', 1, 'MenuList', '1O6K849r6B4Q5r2v6z3U0x6a8j', '/system/menu', NULL, NULL, NULL, 0, b'0', '菜单管理');
INSERT INTO `iam_perm_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_code`, `component`, `effect`, `hidden`, `hidden_header_content`, `hide_children_in_menu`, `icon`, `keep_alive`, `menu_type`, `name`, `parent_id`, `path`, `perm_code`, `redirect`, `remark`, `sort_no`, `target_outside`, `title`) VALUES ('1z6q8d9Q6h40837j6Z952s4t2N', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 10:52:49.242000', NULL, '2023-07-18 10:52:49.242000', 0, b'0', 'admin', '/modules/monitor/system/SystemInfoMonitor.vue', b'0', b'0', b'0', b'0', '', b'1', 1, 'SystemInfoMonitor', '1r618R9868488S629m585H0Z8n', '/monitor/sysinfo', NULL, '', NULL, 1, b'0', '系统信息');
COMMIT;

-- ----------------------------
-- Table structure for iam_perm_path
-- ----------------------------
DROP TABLE IF EXISTS `iam_perm_path`;
CREATE TABLE `iam_perm_path` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `generate` bit(1) NOT NULL,
  `group_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `request_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_perm_path
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_role
-- ----------------------------
DROP TABLE IF EXISTS `iam_role`;
CREATE TABLE `iam_role` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `internal` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_role
-- ----------------------------
BEGIN;
INSERT INTO `iam_role` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `code`, `internal`, `name`, `remark`) VALUES ('1C6k8V9F6f4F9S6K4V5Q1o3s4w', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:07:25.134000', NULL, '2023-07-18 11:07:25.134000', 0, 'system', b'0', 'system', '系统用户');
INSERT INTO `iam_role` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `code`, `internal`, `name`, `remark`) VALUES ('1j6d8R9J6W4c9p6Q3T8M9o5L7a', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:07:18.956000', NULL, '2023-07-18 11:07:18.956000', 0, 'admin', b'0', 'admin', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for iam_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `iam_role_menu`;
CREATE TABLE `iam_role_menu` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `client_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `permission_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `iam_role_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `client_code`, `permission_id`, `role_id`) VALUES ('242bbd96-570c-445b-82cc-fd5f4f223fc7', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:51:55.464000', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:51:55.464000', 0, NULL, NULL, NULL);
INSERT INTO `iam_role_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `client_code`, `permission_id`, `role_id`) VALUES ('88e3719b-b56c-4d72-9c36-4eb67bc87393', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:51:55.464000', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:51:55.464000', 0, NULL, NULL, NULL);
INSERT INTO `iam_role_menu` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `client_code`, `permission_id`, `role_id`) VALUES ('f98d4534-d966-4753-843e-631db1a5906b', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:51:55.464000', '1F628c9X5u9o1b4S7k1D6S905K', '2023-07-18 11:51:55.464000', 0, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for iam_role_path
-- ----------------------------
DROP TABLE IF EXISTS `iam_role_path`;
CREATE TABLE `iam_role_path` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `permission_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_role_path
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_user_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `iam_user_data_scope`;
CREATE TABLE `iam_user_data_scope` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `data_scope_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_user_data_scope
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `iam_user_dept`;
CREATE TABLE `iam_user_dept` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `dept_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_user_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iam_user_expand_info
-- ----------------------------
DROP TABLE IF EXISTS `iam_user_expand_info`;
CREATE TABLE `iam_user_expand_info` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `current_login_time` datetime(6) DEFAULT NULL,
  `initial_password` bit(1) NOT NULL,
  `last_change_password_time` datetime(6) DEFAULT NULL,
  `last_login_time` datetime(6) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_user_expand_info
-- ----------------------------
BEGIN;
INSERT INTO `iam_user_expand_info` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `avatar`, `birthday`, `current_login_time`, `initial_password`, `last_change_password_time`, `last_login_time`, `sex`) VALUES ('1F628c9X5u9o1b4S7k1D6S905K', NULL, '2023-07-17 18:57:51.788000', NULL, '2023-07-17 18:57:51.788000', 0, NULL, NULL, NULL, b'1', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for iam_user_info
-- ----------------------------
DROP TABLE IF EXISTS `iam_user_info`;
CREATE TABLE `iam_user_info` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `admin` bit(1) NOT NULL,
  `client_ids` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `register_time` datetime(6) DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_user_info
-- ----------------------------
BEGIN;
INSERT INTO `iam_user_info` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `admin`, `client_ids`, `email`, `name`, `password`, `phone`, `register_time`, `source`, `status`, `username`) VALUES ('1F628c9X5u9o1b4S7k1D6S905K', NULL, '2023-07-17 18:57:51.691000', NULL, '2023-07-17 18:57:51.691000', 0, b'1', NULL, NULL, 'admin', '60d261ba08f7c0dd8479b92c818c193a', NULL, '2023-07-17 18:57:51.695000', NULL, 1, 'admin');
COMMIT;

-- ----------------------------
-- Table structure for iam_user_role
-- ----------------------------
DROP TABLE IF EXISTS `iam_user_role`;
CREATE TABLE `iam_user_role` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of iam_user_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('quartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `cron` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job_class_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parameter` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of quartz_job
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job_log`;
CREATE TABLE `quartz_job_log` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `class_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `error_message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `handler_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `success` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of quartz_job_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for site_message
-- ----------------------------
DROP TABLE IF EXISTS `site_message`;
CREATE TABLE `site_message` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `cancel_time` datetime(6) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `efficient_time` date DEFAULT NULL,
  `receive_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `send_state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  `sender_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sender_time` datetime(6) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of site_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for site_message_user
-- ----------------------------
DROP TABLE IF EXISTS `site_message_user`;
CREATE TABLE `site_message_user` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `have_read` bit(1) NOT NULL,
  `message_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `read_time` datetime(6) DEFAULT NULL,
  `receive_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of site_message_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for starter_file_upload_info
-- ----------------------------
DROP TABLE IF EXISTS `starter_file_upload_info`;
CREATE TABLE `starter_file_upload_info` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `external_storage_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_size` bigint DEFAULT NULL,
  `file_suffix` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of starter_file_upload_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_app_version
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_version`;
CREATE TABLE `sys_app_version` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `app_version` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bundle_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_app_version
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `group_tag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dict_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dict_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort_no` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_key_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_key_value`;
CREATE TABLE `sys_key_value` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `value` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_key_value
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `update_by` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `version` int DEFAULT '0',
  `enable` bit(1) DEFAULT NULL,
  `internal` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `param_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
BEGIN;
INSERT INTO `sys_param` (`id`, `create_by`, `create_date`, `update_by`, `update_date`, `version`, `enable`, `internal`, `name`, `param_key`, `remark`, `type`, `value`) VALUES ('1', NULL, NULL, NULL, NULL, 0, b'1', b'1', '', 'WebsocketServerUrl', NULL, 1, 'ws://127.0.0.1:9999');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
