/*
 Navicat Premium Data Transfer

 Source Server         : 魏浩铭
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : visitor

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 27/12/2022 17:15:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply_info
-- ----------------------------
DROP TABLE IF EXISTS `apply_info`;
CREATE TABLE `apply_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问申请ID',
  `start_time` datetime NULL DEFAULT NULL COMMENT '访问申请开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '访问申请结束时间',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '访问申请的部门ID',
  `apply_info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申请详情',
  `create_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `applicant_id` bigint NULL DEFAULT NULL COMMENT '申请人ID',
  `principal_id` bigint NULL DEFAULT NULL COMMENT '负责人ID',
  `apply_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申请状态 0-待审核 1-审核中 2-已通过 3-驳回',
  `company_id` bigint NOT NULL COMMENT '企业ID(申请的)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of apply_info
-- ----------------------------
INSERT INTO `apply_info` VALUES (6, '2022-09-14 08:30:00', '2022-09-14 16:30:00', 1, 'bbb', '2022-09-21 11:19:10', 1, 1, '1', 1);
INSERT INTO `apply_info` VALUES (14, '2022-12-26 12:02:00', '2022-12-29 00:00:00', 1, '测试内容', '2022-12-26 12:02:31', 9, 1, '2', 1);
INSERT INTO `apply_info` VALUES (8, '2022-09-14 08:30:00', '2022-09-14 16:30:00', 1, 'user1', '2022-12-25 23:54:44', 1, 2, '2', 2);
INSERT INTO `apply_info` VALUES (9, '2022-12-26 10:38:00', '2022-12-27 00:00:00', 1, 'test', '2022-12-26 10:39:42', 1, 1, '2', 3);
INSERT INTO `apply_info` VALUES (15, '2022-12-27 00:00:00', '2022-12-30 00:00:00', 1, '测试操作', '2022-12-26 12:09:05', 9, 1, '2', 2);
INSERT INTO `apply_info` VALUES (16, '2022-12-30 00:00:00', '2022-12-31 00:00:00', 1, '用户测试', '2022-12-26 12:17:49', 2, 2, '2', 3);

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  `company_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `company_region` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '地区 如:四川省/成都市/双流区',
  `company_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '详细地址',
  `company_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系电话',
  `company_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '企业信息',
  `company_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '介绍',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (1, '测试公司1', 'aaaaa', 'aaaaa', '13417197335', '', '', '2022-09-14 17:50:26');
INSERT INTO `company_info` VALUES (2, '管理公司', 'bbbbb', 'bbbbb', '18483411137', '', '', '2022-09-14 17:50:26');
INSERT INTO `company_info` VALUES (3, '开发公司', 'ccccc', 'ccccc', '13484611156', '', '', '2022-09-14 17:50:26');

-- ----------------------------
-- Table structure for info_review
-- ----------------------------
DROP TABLE IF EXISTS `info_review`;
CREATE TABLE `info_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '审核ID',
  `reviewer_id` bigint NULL DEFAULT NULL COMMENT '审核人ID',
  `visit_id` bigint NULL DEFAULT NULL COMMENT '访问申请ID',
  `status` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '状态 1-进行中 2-已完成 3-驳回',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of info_review
-- ----------------------------
INSERT INTO `info_review` VALUES (12, 1, 7, '2', '完成申请，请明天到企业', '2022-09-24 21:41:52');
INSERT INTO `info_review` VALUES (10, 1, 6, '1', '正在审核中，请等待', '2022-09-24 22:05:17');
INSERT INTO `info_review` VALUES (11, 2, 7, '2', '完成申请，请明天到企业', '2022-09-24 21:41:52');

-- ----------------------------
-- Table structure for info_score
-- ----------------------------
DROP TABLE IF EXISTS `info_score`;
CREATE TABLE `info_score`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评分ID',
  `applicant_id` bigint NULL DEFAULT NULL COMMENT '访问人ID',
  `visit_id` bigint NULL DEFAULT NULL COMMENT '访问申请信息ID',
  `score` int NULL DEFAULT NULL COMMENT '分数',
  `suggest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户建议',
  `is_use` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '是否采用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评分时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of info_score
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '用户管理-密码字符范围', 'sys.account.chrtype', '0', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）');
INSERT INTO `sys_config` VALUES (6, '用户管理-初始密码修改策略', 'sys.account.initPasswordModify', '0', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (7, '用户管理-账号密码更新周期', 'sys.account.passwordValidateDays', '0', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (8, '主框架页-菜单导航显示风格', 'sys.index.menuStyle', 'default', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）');
INSERT INTO `sys_config` VALUES (9, '主框架页-是否开启页脚', 'sys.index.footer', 'true', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '是否开启底部页脚显示（true显示，false隐藏）');
INSERT INTO `sys_config` VALUES (10, '主框架页-是否开启页签', 'sys.index.tagsView', 'true', 'Y', 'admin', '2022-08-31 10:27:32', '', NULL, '是否开启菜单多页签显示（true显示，false隐藏）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门信息ID',
  `dept_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `dept_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门编码',
  `parent_dept_id` bigint NULL DEFAULT NULL COMMENT '上级部门ID',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门状态（0正常 1停用）',
  `unit` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '所属单位',
  `level` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '级别 1-最高级别 以此类推',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '开发部门', '001', 0, '0', '', '1', '', 1, '2022-09-14 11:21:58');
INSERT INTO `sys_dept` VALUES (2, '测试部门', '002', 0, '0', '', '1', '', 1, '2022-09-14 11:21:58');
INSERT INTO `sys_dept` VALUES (3, '管理部门', '003', 0, '0', '', '1', '', 1, '2022-09-14 11:21:58');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-08-31 10:27:32', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-08-31 10:27:31', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1061 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '1', '', 'fa fa-gear', 'admin', '2022-08-31 10:13:23', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '1', '', 'fa fa-video-camera', 'admin', '2022-08-31 10:13:23', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '1', '', 'fa fa-bars', 'admin', '2022-08-31 10:13:23', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', 'menuBlank', 'C', '0', '1', '', 'fa fa-location-arrow', 'admin', '2022-08-31 10:13:23', '', NULL, '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', '1', 'system:user:view', 'fa fa-user-o', 'admin', '2022-08-31 10:13:23', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', '1', 'system:role:view', 'fa fa-user-secret', 'admin', '2022-08-31 10:13:23', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', '1', 'system:menu:view', 'fa fa-th-list', 'admin', '2022-08-31 10:13:23', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', '1', 'system:dept:view', 'fa fa-outdent', 'admin', '2022-08-31 10:13:23', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', '1', 'system:post:view', 'fa fa-address-card-o', 'admin', '2022-08-31 10:13:23', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', '1', 'system:dict:view', 'fa fa-bookmark-o', 'admin', '2022-08-31 10:13:23', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', '1', 'system:config:view', 'fa fa-sun-o', 'admin', '2022-08-31 10:13:23', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', '1', 'system:notice:view', 'fa fa-bullhorn', 'admin', '2022-08-31 10:13:23', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '1', '', 'fa fa-pencil-square-o', 'admin', '2022-08-31 10:13:23', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', '1', 'monitor:online:view', 'fa fa-user-circle', 'admin', '2022-08-31 10:13:23', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', '', 'C', '0', '1', 'monitor:job:view', 'fa fa-tasks', 'admin', '2022-08-31 10:13:23', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', '1', 'monitor:data:view', 'fa fa-bug', 'admin', '2022-08-31 10:13:23', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, '/monitor/server', '', 'C', '0', '1', 'monitor:server:view', 'fa fa-server', 'admin', '2022-08-31 10:13:23', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, '/monitor/cache', '', 'C', '0', '1', 'monitor:cache:view', 'fa fa-cube', 'admin', '2022-08-31 10:13:23', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, '/tool/build', '', 'C', '0', '1', 'tool:build:view', 'fa fa-wpforms', 'admin', '2022-08-31 10:13:23', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', '1', 'tool:gen:view', 'fa fa-code', 'admin', '2022-08-31 10:13:23', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', '1', 'tool:swagger:view', 'fa fa-gg', 'admin', '2022-08-31 10:13:23', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', '2022-08-31 10:13:23', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', '1', 'monitor:logininfor:view', 'fa fa-file-image-o', 'admin', '2022-08-31 10:13:23', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', '1', 'system:user:list', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', '1', 'system:user:add', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', '1', 'system:user:edit', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', '1', 'system:user:remove', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', '1', 'system:user:export', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', '1', 'system:user:import', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', '1', 'system:user:resetPwd', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', '1', 'system:role:list', '#', 'admin', '2022-08-31 10:13:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', '1', 'system:role:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', '1', 'system:role:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', '1', 'system:role:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', '1', 'system:role:export', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', '1', 'system:menu:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', '1', 'system:menu:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', '1', 'system:menu:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', '1', 'system:menu:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', '1', 'system:dept:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', '1', 'system:dept:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', '1', 'system:dept:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', '1', 'system:dept:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', '1', 'system:post:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', '1', 'system:post:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', '1', 'system:post:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', '1', 'system:post:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', '1', 'system:post:export', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', '1', 'system:dict:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', '1', 'system:dict:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', '1', 'system:dict:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', '1', 'system:dict:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', '1', 'system:dict:export', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', '1', 'system:config:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', '1', 'system:config:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', '1', 'system:config:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', '1', 'system:config:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', '1', 'system:config:export', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', '1', 'system:notice:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', '1', 'system:notice:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', '1', 'system:notice:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', '1', 'system:notice:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', '1', 'monitor:operlog:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', '1', 'monitor:operlog:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', '1', 'monitor:operlog:detail', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', '1', 'monitor:operlog:export', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', '1', 'monitor:logininfor:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', '1', 'monitor:logininfor:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', '1', 'monitor:logininfor:export', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '账户解锁', 501, 4, '#', '', 'F', '0', '1', 'monitor:logininfor:unlock', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '在线查询', 109, 1, '#', '', 'F', '0', '1', 'monitor:online:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '批量强退', 109, 2, '#', '', 'F', '0', '1', 'monitor:online:batchForceLogout', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '单条强退', 109, 3, '#', '', 'F', '0', '1', 'monitor:online:forceLogout', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务查询', 110, 1, '#', '', 'F', '0', '1', 'monitor:job:list', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务新增', 110, 2, '#', '', 'F', '0', '1', 'monitor:job:add', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务修改', 110, 3, '#', '', 'F', '0', '1', 'monitor:job:edit', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '任务删除', 110, 4, '#', '', 'F', '0', '1', 'monitor:job:remove', '#', 'admin', '2022-08-31 10:13:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '状态修改', 110, 5, '#', '', 'F', '0', '1', 'monitor:job:changeStatus', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '任务详细', 110, 6, '#', '', 'F', '0', '1', 'monitor:job:detail', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '任务导出', 110, 7, '#', '', 'F', '0', '1', 'monitor:job:export', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成查询', 115, 1, '#', '', 'F', '0', '1', 'tool:gen:list', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '生成修改', 115, 2, '#', '', 'F', '0', '1', 'tool:gen:edit', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '生成删除', 115, 3, '#', '', 'F', '0', '1', 'tool:gen:remove', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '预览代码', 115, 4, '#', '', 'F', '0', '1', 'tool:gen:preview', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '生成代码', 115, 5, '#', '', 'F', '0', '1', 'tool:gen:code', '#', 'admin', '2022-08-31 10:13:25', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2022-10-01 企业访客管理系统新版本发布啦', '2', '新版本内容', '0', 'admin', '2022-08-31 10:28:06', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2022-12-20 企业访客管理系统凌晨维护', '1', '维护内容', '0', 'admin', '2022-12-19 10:28:06', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 151 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '操作日志', 9, 'com.project.controller.SysOperlogController.clean()', 'POST', 1, NULL, NULL, '/api/operlog/clean', '192.168.174.1', NULL, '', '{\"code\":200,\"description\":\"\",\"message\":\"清空成功\"}', 0, NULL, '2022-09-05 16:00:07');
INSERT INTO `sys_oper_log` VALUES (2, '角色管理', 1, 'com.project.controller.SysRoleController.insert()', 'POST', 1, NULL, NULL, '/api/role/insert', '192.168.174.1', NULL, '{\"admin\":false,\"createBy\":\"系统管理员\",\"createTime\":1662364828353,\"delFlag\":\"0\",\"deptIds\":[],\"flag\":false,\"menuIds\":[1,2,3,4],\"remark\":\"\",\"roleId\":112,\"roleKey\":\"cccc\",\"roleName\":\"cccc\",\"roleSort\":\"1\",\"status\":\"0\"}', '{\"code\":200,\"data\":112,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-05 16:00:28');
INSERT INTO `sys_oper_log` VALUES (3, '角色管理', 3, 'com.project.controller.SysRoleController.remove()', 'POST', 1, NULL, NULL, '/api/role/remove', '192.168.174.1', NULL, '{\"ids\":[\"112\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 16:00:40');
INSERT INTO `sys_oper_log` VALUES (4, '角色管理', 3, 'com.project.controller.SysRoleController.remove()', 'POST', 1, NULL, NULL, '/api/role/remove', '192.168.174.1', NULL, '{\"ids\":[\"112\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 16:06:24');
INSERT INTO `sys_oper_log` VALUES (5, '角色管理', 3, 'com.project.controller.SysRoleController.remove()', 'POST', 1, NULL, NULL, '/api/role/remove', '192.168.174.1', NULL, '{\"ids\":[\"112\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 16:33:56');
INSERT INTO `sys_oper_log` VALUES (6, '角色管理', 3, 'com.project.controller.SysRoleController.remove()', 'POST', 1, NULL, NULL, '/api/role/remove', '192.168.174.1', NULL, '{\"ids\":[\"112\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 16:33:57');
INSERT INTO `sys_oper_log` VALUES (7, '角色管理', 3, 'com.project.controller.SysRoleController.remove()', 'POST', 1, NULL, NULL, '/api/role/remove', '192.168.174.1', NULL, '{\"ids\":[\"112\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 16:39:50');
INSERT INTO `sys_oper_log` VALUES (8, '角色管理', 3, 'com.project.controller.SysRoleController.remove()', 'POST', 1, NULL, NULL, '/api/role/remove', '192.168.174.1', NULL, '{\"ids\":[\"112\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 16:43:58');
INSERT INTO `sys_oper_log` VALUES (9, '通知公告', 1, 'com.project.controller.SysNoticeController.insert()', 'POST', 1, NULL, NULL, '/api/notice/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"status\":\"0\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-05 17:08:17');
INSERT INTO `sys_oper_log` VALUES (10, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"status\":\"0\"}', '{\"code\":50020,\"data\":\"修改失败\",\"description\":\"\",\"message\":\"修改失败\"}', 0, NULL, '2022-09-05 17:08:54');
INSERT INTO `sys_oper_log` VALUES (11, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369058735}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-05 17:10:58');
INSERT INTO `sys_oper_log` VALUES (12, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":10,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369066202}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:11:06');
INSERT INTO `sys_oper_log` VALUES (13, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":10,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"管理员\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369095237}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:11:35');
INSERT INTO `sys_oper_log` VALUES (14, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":10,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"管理员\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369124376}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:12:04');
INSERT INTO `sys_oper_log` VALUES (15, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":10,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"aaa\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369127729}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:12:07');
INSERT INTO `sys_oper_log` VALUES (16, '通知公告', 1, 'com.project.controller.SysNoticeController.insert()', 'POST', 1, NULL, NULL, '/api/notice/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662369146694,\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"remark\":\"aaa\",\"status\":\"0\"}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-05 17:12:26');
INSERT INTO `sys_oper_log` VALUES (17, '通知公告', 1, 'com.project.controller.SysNoticeController.insert()', 'POST', 1, NULL, NULL, '/api/notice/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662369155115,\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"remark\":\"aaa\",\"status\":\"0\"}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-05 17:12:35');
INSERT INTO `sys_oper_log` VALUES (18, '通知公告', 1, 'com.project.controller.SysNoticeController.insert()', 'POST', 1, NULL, NULL, '/api/notice/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662369180437,\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"remark\":\"aaa\",\"status\":\"0\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-05 17:13:00');
INSERT INTO `sys_oper_log` VALUES (19, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":10,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"aaa\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369198837}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:13:18');
INSERT INTO `sys_oper_log` VALUES (20, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":11,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"aaa\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369209974}', '{\"code\":200,\"data\":11,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:13:29');
INSERT INTO `sys_oper_log` VALUES (21, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":11,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"bbb\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369212933}', '{\"code\":200,\"data\":11,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:13:32');
INSERT INTO `sys_oper_log` VALUES (22, '通知公告', 2, 'com.project.controller.SysNoticeController.update()', 'POST', 1, NULL, NULL, '/api/notice/update', '192.168.174.1', NULL, '{\"noticeContent\":\"版本更新啦\",\"noticeId\":11,\"noticeTitle\":\"版本更新\",\"noticeType\":\"2\",\"remark\":\"bbb\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662369422706}', '{\"code\":200,\"data\":11,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-05 17:17:02');
INSERT INTO `sys_oper_log` VALUES (23, '通知公告', 1, 'com.project.controller.SysNoticeController.insert()', 'POST', 1, NULL, NULL, '/api/notice/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662369437519,\"noticeContent\":\"版本更新啦\",\"noticeTitle\":\"版本更新\",\"noticeType\":\"1\",\"remark\":\"aaa\",\"status\":\"0\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-05 17:17:17');
INSERT INTO `sys_oper_log` VALUES (24, '通知公告', 3, 'com.project.controller.SysNoticeController.remove()', 'POST', 1, NULL, NULL, '/api/notice/remove', '192.168.174.1', NULL, '\"{\\\"ids\\\":\\\"10,11,12\\\"}\"', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'{\"ids\":\"10\'\r\n### The error may exist in file [D:\\CodeProject\\VisitorSystem\\target\\classes\\mapper\\SysNoticeMapper.xml]\r\n### The error may involve com.project.mapper.SysNoticeMapper.deleteNoticeByIds-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_notice where notice_id in           (               ?          ,              ?          ,              ?          )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'{\"ids\":\"10\'\n; Data truncation: Truncated incorrect DOUBLE value: \'{\"ids\":\"10\'; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'{\"ids\":\"10\'', '2022-09-05 17:18:09');
INSERT INTO `sys_oper_log` VALUES (25, '通知公告', 3, 'com.project.controller.SysNoticeController.remove()', 'POST', 1, NULL, NULL, '/api/notice/remove', '192.168.174.1', NULL, '{\"ids\":[\"10,11,12\"]}', '{\"code\":200,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-05 17:18:47');
INSERT INTO `sys_oper_log` VALUES (26, '通知公告', 3, 'com.project.controller.SysNoticeController.remove()', 'POST', 1, NULL, NULL, '/api/notice/remove', '192.168.174.1', NULL, '{\"ids\":[\"10,11,12\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败\"}', 0, NULL, '2022-09-05 17:18:51');
INSERT INTO `sys_oper_log` VALUES (27, '通知公告', 3, 'com.project.controller.SysNoticeController.remove()', 'POST', 1, NULL, NULL, '/api/notice/remove', '192.168.174.1', NULL, '{\"ids\":[\"10,11,12\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败,已删除或没有该公告\"}', 0, NULL, '2022-09-05 17:19:48');
INSERT INTO `sys_oper_log` VALUES (28, '菜单管理', 1, 'com.project.controller.SysMenuController.insert()', 'POST', 1, NULL, NULL, '/api/menu/insert', '192.168.174.1', NULL, '{\"children\":[],\"createBy\":\"系统管理员\",\"menuName\":\"\",\"visible\":\"\"}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'menu_name\' doesn\'t have a default value\r\n### The error may exist in file [D:\\CodeProject\\VisitorSystem\\target\\classes\\mapper\\SysMenuMapper.xml]\r\n### The error may involve com.project.mapper.SysMenuMapper.insertMenu-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_menu(                                visible,                     create_by,    create_time   )values(                                ?,                     ?,    sysdate()   )\r\n### Cause: java.sql.SQLException: Field \'menu_name\' doesn\'t have a default value\n; Field \'menu_name\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'menu_name\' doesn\'t have a default value', '2022-09-06 10:27:03');
INSERT INTO `sys_oper_log` VALUES (29, '菜单管理', 1, 'com.project.controller.SysMenuController.insert()', 'POST', 1, NULL, NULL, '/api/menu/insert', '192.168.174.1', NULL, '{\"children\":[],\"createBy\":\"系统管理员\",\"createTime\":1662432383967,\"icon\":\"\",\"isRefresh\":\"\",\"menuName\":\"test1\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"target\":\"\",\"url\":\"\",\"visible\":\"\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 10:46:24');
INSERT INTO `sys_oper_log` VALUES (30, '菜单管理', 1, 'com.project.controller.SysMenuController.insert()', 'POST', 1, NULL, NULL, '/api/menu/insert', '192.168.174.1', NULL, '{\"children\":[],\"createBy\":\"系统管理员\",\"createTime\":1662432676504,\"icon\":\"\",\"isRefresh\":\"\",\"menuName\":\"test1\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"url\":\"\",\"visible\":\"\"}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'visitor.sys_menu_entity\' doesn\'t exist\r\n### The error may exist in com/project/mapper/SysMenuMapper.java (best guess)\r\n### The error may involve com.project.mapper.SysMenuMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu_entity  ( menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon,  create_by, create_time,   remark )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?,   ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'visitor.sys_menu_entity\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'visitor.sys_menu_entity\' doesn\'t exist', '2022-09-06 10:51:16');
INSERT INTO `sys_oper_log` VALUES (31, '菜单管理', 1, 'com.project.controller.SysMenuController.insert()', 'POST', 1, NULL, NULL, '/api/menu/insert', '192.168.174.1', NULL, '{\"children\":[],\"createBy\":\"系统管理员\",\"createTime\":1662432715828,\"icon\":\"\",\"isRefresh\":\"\",\"menuId\":2001,\"menuName\":\"test1\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"url\":\"\",\"visible\":\"\"}', '{\"code\":200,\"data\":2001,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 10:51:55');
INSERT INTO `sys_oper_log` VALUES (32, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"\",\"menuName\":\"test1\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"url\":\"\",\"visible\":\"\"}', '{\"data\":\"修改菜单\'test1\'失败，菜单名称已存在\",\"description\":\"\"}', 0, NULL, '2022-09-06 10:57:23');
INSERT INTO `sys_oper_log` VALUES (33, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"\",\"menuName\":\"test2\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"updateBy\":\"系统管理员\",\"updateTime\":1662433050575,\"url\":\"\",\"visible\":\"\"}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-06 10:57:30');
INSERT INTO `sys_oper_log` VALUES (34, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"\",\"menuId\":2001,\"menuName\":\"test2\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"updateBy\":\"系统管理员\",\"updateTime\":1662433062937,\"url\":\"\",\"visible\":\"\"}', '{\"code\":200,\"data\":2001,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 10:57:42');
INSERT INTO `sys_oper_log` VALUES (35, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"1\",\"menuId\":2001,\"menuName\":\"test2\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"#\",\"updateBy\":\"系统管理员\",\"updateTime\":1662433077156,\"url\":\"\",\"visible\":\"\"}', '{\"code\":200,\"data\":2001,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 10:57:57');
INSERT INTO `sys_oper_log` VALUES (36, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"1\",\"menuId\":2001,\"menuName\":\"test2\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"updateBy\":\"系统管理员\",\"updateTime\":1662433095331,\"url\":\"#\",\"visible\":\"\"}', '{\"code\":200,\"data\":2001,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 10:58:15');
INSERT INTO `sys_oper_log` VALUES (37, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"1\",\"menuName\":\"test2\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"url\":\"\",\"visible\":\"\"}', '{\"data\":\"修改菜单\'test2\'失败，菜单名称已存在\",\"description\":\"\"}', 0, NULL, '2022-09-06 10:59:22');
INSERT INTO `sys_oper_log` VALUES (38, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"1\",\"menuName\":\"test1\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"updateBy\":\"系统管理员\",\"updateTime\":1662433168490,\"url\":\"\",\"visible\":\"\"}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-06 10:59:28');
INSERT INTO `sys_oper_log` VALUES (39, '菜单管理', 2, 'com.project.controller.SysMenuController.update()', 'POST', 1, NULL, NULL, '/api/menu/update', '192.168.174.1', NULL, '{\"children\":[],\"icon\":\"\",\"isRefresh\":\"1\",\"menuId\":2001,\"menuName\":\"test1\",\"menuType\":\"C\",\"orderNum\":\"1\",\"parentId\":0,\"perms\":\"\",\"remark\":\"\",\"target\":\"\",\"updateBy\":\"系统管理员\",\"updateTime\":1662433174373,\"url\":\"\",\"visible\":\"\"}', '{\"code\":200,\"data\":2001,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 10:59:34');
INSERT INTO `sys_oper_log` VALUES (40, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'GET', 1, NULL, NULL, '/api/menu/remove/2001', '192.168.174.1', NULL, '2001', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除菜单管理信息成功\"}', 0, NULL, '2022-09-06 11:01:27');
INSERT INTO `sys_oper_log` VALUES (41, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'GET', 1, NULL, NULL, '/api/menu/remove/2001', '192.168.174.1', NULL, '2001', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-06 11:02:06');
INSERT INTO `sys_oper_log` VALUES (42, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'GET', 1, NULL, NULL, '/api/menu/remove/2001', '192.168.174.1', NULL, '2001', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"该菜单不存在\"}', 0, NULL, '2022-09-06 11:03:06');
INSERT INTO `sys_oper_log` VALUES (43, '字典类型', 1, 'com.project.controller.SysDictTypeController.insert()', 'POST', 1, NULL, NULL, '/api/dict/insert', '192.168.174.1', NULL, '{\"dictName\":\"aaaa\",\"dictType\":\"sys_user_sex\",\"status\":\"0\"}', '{\"data\":\"新增字典\'aaaa\'失败，字典类型已存在\",\"description\":\"\"}', 0, NULL, '2022-09-06 15:36:14');
INSERT INTO `sys_oper_log` VALUES (44, '字典类型', 1, 'com.project.controller.SysDictTypeController.insert()', 'POST', 1, NULL, NULL, '/api/dict/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"dictName\":\"aaaa\",\"dictType\":\"aaaa\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 15:36:20');
INSERT INTO `sys_oper_log` VALUES (45, '字典类型', 1, 'com.project.controller.SysDictTypeController.insert()', 'POST', 1, NULL, NULL, '/api/dict/insert', '192.168.174.1', NULL, '{\"dictName\":\"aaaa\",\"dictType\":\"aaaa\",\"remark\":\"aaa\",\"status\":\"0\"}', '{\"data\":\"新增字典\'aaaa\'失败，字典类型已存在\",\"description\":\"\"}', 0, NULL, '2022-09-06 15:37:18');
INSERT INTO `sys_oper_log` VALUES (46, '字典类型', 1, 'com.project.controller.SysDictTypeController.insert()', 'POST', 1, NULL, NULL, '/api/dict/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662449843667,\"dictName\":\"bbb\",\"dictType\":\"bbb\",\"remark\":\"aaa\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 15:37:23');
INSERT INTO `sys_oper_log` VALUES (47, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"dictName\":\"bbb\",\"dictType\":\"bbb\",\"remark\":\"\",\"status\":\"0\"}', '{\"data\":\"修改字典\'bbb\'失败，字典类型已存在\",\"description\":\"\"}', 0, NULL, '2022-09-06 15:40:38');
INSERT INTO `sys_oper_log` VALUES (48, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"dictName\":\"bbb\",\"dictType\":\"bbbb\",\"remark\":\"\",\"status\":\"0\",\"updateTime\":1662450067567}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-06 15:41:07');
INSERT INTO `sys_oper_log` VALUES (49, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"dictId\":101,\"dictName\":\"bbb\",\"dictType\":\"bbbb\",\"remark\":\"\",\"status\":\"0\",\"updateTime\":1662450076681}', '{\"code\":200,\"data\":101,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 15:41:16');
INSERT INTO `sys_oper_log` VALUES (50, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"dictId\":101,\"dictName\":\"bbb\",\"dictType\":\"bbbb\",\"remark\":\"\",\"status\":\"0\",\"updateTime\":1662450111322}', '{\"code\":200,\"data\":101,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 15:41:51');
INSERT INTO `sys_oper_log` VALUES (51, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"dictId\":101,\"dictName\":\"bbb\",\"dictType\":\"bbbb\",\"remark\":\"\",\"status\":\"0\",\"updateTime\":1662450135752}', '{\"code\":200,\"data\":101,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 15:42:15');
INSERT INTO `sys_oper_log` VALUES (52, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"dictId\":101,\"dictName\":\"bbb\",\"dictType\":\"bbbb\",\"remark\":\"\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662450245957}', '{\"code\":200,\"data\":101,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 15:44:06');
INSERT INTO `sys_oper_log` VALUES (53, '字典类型', 2, 'com.project.controller.SysDictTypeController.update()', 'POST', 1, NULL, NULL, '/api/dict/update', '192.168.174.1', NULL, '{\"dictId\":101,\"dictName\":\"bbb\",\"dictType\":\"bbbb\",\"remark\":\"aaa\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662450254594}', '{\"code\":200,\"data\":101,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 15:44:14');
INSERT INTO `sys_oper_log` VALUES (54, '字典类型', 3, 'com.project.controller.SysDictTypeController.remove()', 'POST', 1, NULL, NULL, '/api/dict/remove', '192.168.174.1', NULL, '{\"ids\":[\"100，101\"]}', NULL, 1, '', '2022-09-06 15:45:34');
INSERT INTO `sys_oper_log` VALUES (55, '字典类型', 9, 'com.project.controller.SysDictTypeController.refreshCache()', 'GET', 1, NULL, NULL, '/api/dict/refreshCache', '192.168.174.1', NULL, '', '{\"code\":200,\"description\":\"\",\"message\":\"刷新字典缓存成功\"}', 0, NULL, '2022-09-06 15:47:30');
INSERT INTO `sys_oper_log` VALUES (56, '字典类型', 3, 'com.project.controller.SysDictTypeController.remove()', 'POST', 1, NULL, NULL, '/api/dict/remove', '192.168.174.1', NULL, '{\"ids\":[\"100，101\"]}', NULL, 1, '', '2022-09-06 15:57:18');
INSERT INTO `sys_oper_log` VALUES (57, '字典类型', 3, 'com.project.controller.SysDictTypeController.remove()', 'POST', 1, NULL, NULL, '/api/dict/remove', '192.168.174.1', NULL, '{\"ids\":[\"100，101\"]}', NULL, 1, '', '2022-09-06 15:57:45');
INSERT INTO `sys_oper_log` VALUES (58, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662451723813,\"cssClass\":\"\",\"dictLabel\":\"\",\"dictType\":\"\",\"dictValue\":\"\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"\",\"status\":\"\"}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:08:43');
INSERT INTO `sys_oper_log` VALUES (59, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662451807202,\"cssClass\":\"\",\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:10:07');
INSERT INTO `sys_oper_log` VALUES (60, '字典数据', 2, 'com.project.controller.SysDictDataController.update()', 'POST', 1, NULL, NULL, '/api/dict/data/update', '192.168.174.1', NULL, '{\"cssClass\":\"\",\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662451966468}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-06 16:12:46');
INSERT INTO `sys_oper_log` VALUES (61, '字典数据', 2, 'com.project.controller.SysDictDataController.update()', 'POST', 1, NULL, NULL, '/api/dict/data/update', '192.168.174.1', NULL, '{\"cssClass\":\"asdafa\",\"dictCode\":101,\"dictLabel\":\"bbbb\",\"dictSort\":1,\"dictType\":\"sys_a\",\"dictValue\":\"aaaa\",\"isDefault\":\"\",\"listClass\":\"asdadas\",\"remark\":\"test\",\"status\":\"0\",\"updateBy\":\"系统管理员\",\"updateTime\":1662451994493}', '{\"code\":200,\"data\":101,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-06 16:13:14');
INSERT INTO `sys_oper_log` VALUES (62, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662452036791,\"cssClass\":\"\",\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:13:56');
INSERT INTO `sys_oper_log` VALUES (63, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662452040591,\"cssClass\":\"\",\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:14:00');
INSERT INTO `sys_oper_log` VALUES (64, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662452056160,\"cssClass\":\"\",\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\"}', '{\"code\":200,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:14:16');
INSERT INTO `sys_oper_log` VALUES (65, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662452102089,\"cssClass\":\"\",\"dictCode\":105,\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\"}', '{\"code\":200,\"data\":105,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:15:02');
INSERT INTO `sys_oper_log` VALUES (66, '字典数据', 3, 'com.project.controller.SysDictDataController.remove()', 'POST', 1, NULL, NULL, '/api/dict/data/remove', '192.168.174.1', NULL, '{\"ids\":[\"101,102,103\"]}', '{\"code\":200,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-06 16:15:14');
INSERT INTO `sys_oper_log` VALUES (67, '字典数据', 3, 'com.project.controller.SysDictDataController.remove()', 'POST', 1, NULL, NULL, '/api/dict/data/remove', '192.168.174.1', NULL, '{\"ids\":[\"104,105\"]}', '{\"code\":200,\"data\":\"104,105\",\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-06 16:15:52');
INSERT INTO `sys_oper_log` VALUES (68, '字典数据', 1, 'com.project.controller.SysDictDataController.insert()', 'POST', 1, NULL, NULL, '/api/dict/data/insert', '192.168.174.1', NULL, '{\"createBy\":\"系统管理员\",\"createTime\":1662452165263,\"cssClass\":\"\",\"dictCode\":106,\"dictLabel\":\"aaaa\",\"dictSort\":1,\"dictType\":\"sys_d\",\"dictValue\":\"hhhh\",\"isDefault\":\"\",\"listClass\":\"\",\"remark\":\"test\",\"status\":\"0\"}', '{\"code\":200,\"data\":106,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-06 16:16:05');
INSERT INTO `sys_oper_log` VALUES (69, '字典类型', 3, 'com.project.controller.SysDictTypeController.remove()', 'POST', 1, NULL, NULL, '/api/dict/remove', '192.168.174.1', NULL, '{\"ids\":[\"100，101\"]}', NULL, 1, '', '2022-09-06 16:16:18');
INSERT INTO `sys_oper_log` VALUES (70, '字典类型', 3, 'com.project.controller.SysDictTypeController.remove()', 'POST', 1, NULL, NULL, '/api/dict/remove', '192.168.174.1', NULL, '{\"ids\":[\"100，101\"]}', NULL, 1, '', '2022-09-06 16:22:45');
INSERT INTO `sys_oper_log` VALUES (71, '字典类型', 3, 'com.project.controller.SysDictTypeController.remove()', 'POST', 1, NULL, NULL, '/api/dict/remove', '192.168.174.1', NULL, '{\"ids\":[\"100,101\"]}', '{\"code\":200,\"description\":\"\",\"message\":\"批量删除字典类型成功\"}', 0, NULL, '2022-09-06 16:22:58');
INSERT INTO `sys_oper_log` VALUES (72, '部门管理', 1, 'com.project.controller.SysDeptController.insert()', 'POST', 1, NULL, NULL, '/api/dept/insert', '192.168.174.1', NULL, '{\"deptCode\":\"001\",\"deptName\":\"aaaa\",\"level\":\"1\",\"parentDeptId\":0,\"unit\":\"\"}', '{\"code\":40101,\"data\":\"无权限\",\"description\":\"\",\"message\":\"仅限管理员可操作\"}', 0, NULL, '2022-09-14 11:20:02');
INSERT INTO `sys_oper_log` VALUES (73, '部门管理', 1, 'com.project.controller.SysDeptController.insert()', 'POST', 1, NULL, NULL, '/api/dept/insert', '192.168.174.1', NULL, '{\"deptCode\":\"001\",\"deptName\":\"aaaa\",\"level\":\"1\",\"parentDeptId\":0,\"unit\":\"\"}', '{\"code\":40101,\"data\":\"无权限\",\"description\":\"\",\"message\":\"仅限管理员可操作\"}', 0, NULL, '2022-09-14 11:20:10');
INSERT INTO `sys_oper_log` VALUES (74, '部门管理', 1, 'com.project.controller.SysDeptController.insert()', 'POST', 1, NULL, NULL, '/api/dept/insert', '192.168.174.1', NULL, '{\"createTime\":1663125717573,\"createUserId\":1,\"deptCode\":\"001\",\"deptId\":1,\"deptName\":\"aaaa\",\"level\":\"1\",\"parentDeptId\":0,\"remark\":\"\",\"status\":\"0\",\"unit\":\"\"}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 11:21:57');
INSERT INTO `sys_oper_log` VALUES (75, '部门管理', 2, 'com.project.controller.SysDeptController.update()', 'POST', 1, NULL, NULL, '/api/dept/update', '192.168.174.1', NULL, '{\"deptCode\":\"001\",\"deptName\":\"aaaa\",\"level\":\"1\",\"parentDeptId\":0,\"remark\":\"\",\"status\":\"0\",\"unit\":\"\"}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-14 11:22:30');
INSERT INTO `sys_oper_log` VALUES (76, '部门管理', 2, 'com.project.controller.SysDeptController.update()', 'POST', 1, NULL, NULL, '/api/dept/update', '192.168.174.1', NULL, '{\"deptCode\":\"001\",\"deptId\":1,\"deptName\":\"开发部门\",\"level\":\"1\",\"parentDeptId\":0,\"remark\":\"\",\"status\":\"0\",\"unit\":\"\"}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-14 11:22:50');
INSERT INTO `sys_oper_log` VALUES (77, '部门管理', 1, 'com.project.controller.SysDeptController.insert()', 'POST', 1, NULL, NULL, '/api/dept/insert', '192.168.174.1', NULL, '{\"createTime\":1663126014985,\"createUserId\":1,\"deptCode\":\"001\",\"deptId\":2,\"deptName\":\"aaaa\",\"level\":\"1\",\"parentDeptId\":0,\"remark\":\"\",\"status\":\"0\",\"unit\":\"\"}', '{\"code\":200,\"data\":2,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 11:26:54');
INSERT INTO `sys_oper_log` VALUES (78, '部门管理', 3, 'com.project.controller.SysDeptController.remove()', 'POST', 1, NULL, NULL, '/api/dept/remove', '192.168.174.1', NULL, '{\"deptId\":[\"2\"]}', '{\"code\":200,\"data\":2,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-14 11:55:21');
INSERT INTO `sys_oper_log` VALUES (79, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663128258731,\"id\":1}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:04:18');
INSERT INTO `sys_oper_log` VALUES (80, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663129137529,\"id\":2}', '{\"code\":200,\"data\":2,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:18:57');
INSERT INTO `sys_oper_log` VALUES (81, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663129307745,\"id\":3}', '{\"code\":200,\"data\":3,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:21:47');
INSERT INTO `sys_oper_log` VALUES (82, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663129383992,\"id\":4}', '{\"code\":200,\"data\":4,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:23:04');
INSERT INTO `sys_oper_log` VALUES (83, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663129563521,\"id\":5}', '{\"code\":200,\"data\":5,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:26:03');
INSERT INTO `sys_oper_log` VALUES (84, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663129836445,\"id\":6}', '{\"code\":200,\"data\":6,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:30:36');
INSERT INTO `sys_oper_log` VALUES (85, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130165653,\"id\":7}', '{\"code\":200,\"data\":7,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:36:05');
INSERT INTO `sys_oper_log` VALUES (86, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130294011,\"id\":8}', '{\"code\":200,\"data\":8,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:38:14');
INSERT INTO `sys_oper_log` VALUES (87, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130335975,\"id\":9}', '{\"code\":200,\"data\":9,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:38:56');
INSERT INTO `sys_oper_log` VALUES (88, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130369178,\"id\":10}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:39:29');
INSERT INTO `sys_oper_log` VALUES (89, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130394257}', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\r\n### The error may exist in com/project/mapper/CompanyInfoMapper.java (best guess)\r\n### The error may involve com.project.mapper.CompanyInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO company_info  ( company_name, company_region, company_address, company_phone, company_info, company_introduction, create_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\n; Data truncation: Data too long for column \'company_name\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1', '2022-09-14 12:39:54');
INSERT INTO `sys_oper_log` VALUES (90, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130443133}', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\r\n### The error may exist in com/project/mapper/CompanyInfoMapper.java (best guess)\r\n### The error may involve com.project.mapper.CompanyInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO company_info  ( company_name, company_region, company_address, company_phone, company_info, company_introduction, create_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\n; Data truncation: Data too long for column \'company_name\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1', '2022-09-14 12:40:43');
INSERT INTO `sys_oper_log` VALUES (91, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130450240,\"id\":11}', '{\"code\":200,\"data\":11,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:40:50');
INSERT INTO `sys_oper_log` VALUES (92, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130475246,\"id\":12}', '{\"code\":200,\"data\":12,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:41:15');
INSERT INTO `sys_oper_log` VALUES (93, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130502020,\"id\":13}', '{\"code\":200,\"data\":13,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:41:42');
INSERT INTO `sys_oper_log` VALUES (94, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130530044}', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\r\n### The error may exist in com/project/mapper/CompanyInfoMapper.java (best guess)\r\n### The error may involve com.project.mapper.CompanyInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO company_info  ( company_name, company_region, company_address, company_phone, company_info, company_introduction, create_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\n; Data truncation: Data too long for column \'company_name\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1', '2022-09-14 12:42:10');
INSERT INTO `sys_oper_log` VALUES (95, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130637761}', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\r\n### The error may exist in com/project/mapper/CompanyInfoMapper.java (best guess)\r\n### The error may involve com.project.mapper.CompanyInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO company_info  ( company_name, company_region, company_address, company_phone, company_info, company_introduction, create_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\n; Data truncation: Data too long for column \'company_name\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1', '2022-09-14 12:43:57');
INSERT INTO `sys_oper_log` VALUES (96, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663130719092}', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\r\n### The error may exist in com/project/mapper/CompanyInfoMapper.java (best guess)\r\n### The error may involve com.project.mapper.CompanyInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO company_info  ( company_name, company_region, company_address, company_phone, company_info, company_introduction, create_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\n; Data truncation: Data too long for column \'company_name\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1', '2022-09-14 12:45:19');
INSERT INTO `sys_oper_log` VALUES (97, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663131070017}', NULL, 1, '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\r\n### The error may exist in com/project/mapper/CompanyInfoMapper.java (best guess)\r\n### The error may involve com.project.mapper.CompanyInfoMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO company_info  ( company_name, company_region, company_address, company_phone, company_info, company_introduction, create_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1\n; Data truncation: Data too long for column \'company_name\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'company_name\' at row 1', '2022-09-14 12:51:10');
INSERT INTO `sys_oper_log` VALUES (98, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663131072812,\"id\":14}', '{\"code\":200,\"data\":14,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 12:51:12');
INSERT INTO `sys_oper_log` VALUES (99, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663140615125,\"id\":15}', '{\"code\":200,\"data\":15,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 15:30:15');
INSERT INTO `sys_oper_log` VALUES (100, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"\",\"companyPhone\":\"\",\"companyRegion\":\"\",\"createTime\":1663141236042,\"id\":16}', '{\"code\":200,\"data\":16,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 15:40:36');
INSERT INTO `sys_oper_log` VALUES (101, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"aaaaa\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaa\",\"companyPhone\":\"13417197335\",\"companyRegion\":\"aaaaa\",\"createTime\":1663149025618,\"id\":17}', '{\"code\":200,\"data\":17,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 17:50:25');
INSERT INTO `sys_oper_log` VALUES (102, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"aaaaa\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaa\",\"companyPhone\":\"13417197335\",\"companyRegion\":\"aaaaa\",\"createTime\":1663149109913,\"id\":18}', '{\"code\":200,\"data\":18,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 17:51:49');
INSERT INTO `sys_oper_log` VALUES (103, '公司管理', 2, 'com.project.controller.CompanyInfoController.update()', 'POST', 1, NULL, NULL, '/api/company/update', '192.168.174.1', NULL, '{\"companyAddress\":\"bbbb\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"bbbb\",\"companyPhone\":\"13417197335\",\"companyRegion\":\"bbbb\"}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-14 17:52:14');
INSERT INTO `sys_oper_log` VALUES (104, '公司管理', 2, 'com.project.controller.CompanyInfoController.update()', 'POST', 1, NULL, NULL, '/api/company/update', '192.168.174.1', NULL, '{\"companyAddress\":\"bbbb\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"bbbb\",\"companyPhone\":\"13417197335\",\"companyRegion\":\"bbbb\",\"id\":18}', '{\"code\":200,\"data\":18,\"description\":\"\",\"message\":\"修改成功\"}', 0, NULL, '2022-09-14 17:52:31');
INSERT INTO `sys_oper_log` VALUES (105, '公司信息管理', 3, 'com.project.controller.CompanyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/company/remove', '192.168.174.1', NULL, '{\"companyId\":[\"18\"]}', '{\"code\":200,\"data\":18,\"description\":\"\",\"message\":\"删除失败\"}', 0, NULL, '2022-09-14 17:59:20');
INSERT INTO `sys_oper_log` VALUES (106, '公司管理', 1, 'com.project.controller.CompanyInfoController.insert()', 'POST', 1, NULL, NULL, '/api/company/insert', '192.168.174.1', NULL, '{\"companyAddress\":\"aaaaa\",\"companyInfo\":\"\",\"companyIntroduction\":\"\",\"companyName\":\"aaaa\",\"companyPhone\":\"13417197335\",\"companyRegion\":\"aaaaa\",\"createTime\":1663149597787,\"id\":19}', '{\"code\":200,\"data\":19,\"description\":\"\",\"message\":\"新增成功\"}', 0, NULL, '2022-09-14 17:59:57');
INSERT INTO `sys_oper_log` VALUES (107, '公司信息管理', 3, 'com.project.controller.CompanyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/company/remove', '192.168.174.1', NULL, '{\"companyId\":[\"18\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败\"}', 0, NULL, '2022-09-14 17:59:59');
INSERT INTO `sys_oper_log` VALUES (108, '公司信息管理', 3, 'com.project.controller.CompanyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/company/remove', '192.168.174.1', NULL, '{\"companyId\":[\"19\"]}', '{\"code\":200,\"data\":19,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-14 18:00:05');
INSERT INTO `sys_oper_log` VALUES (109, '部门管理', 2, 'com.project.controller.SysDeptController.update()', 'POST', 1, NULL, NULL, '/api/dept/update', '192.168.174.1', NULL, '{\"deptCode\":\"001\",\"deptId\":18,\"deptName\":\"开发部门\",\"level\":\"1\",\"parentDeptId\":0,\"remark\":\"\",\"status\":\"0\",\"unit\":\"\"}', '{\"code\":50020,\"data\":\"修改失败\",\"description\":\"\",\"message\":\"修改失败\"}', 0, NULL, '2022-09-14 18:00:49');
INSERT INTO `sys_oper_log` VALUES (110, '公司信息管理', 3, 'com.project.controller.CompanyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/company/remove', '192.168.174.1', NULL, '{\"companyId\":[\"19\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败\"}', 0, NULL, '2022-09-14 18:08:17');
INSERT INTO `sys_oper_log` VALUES (111, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{\"applyId\":[\"1\"]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-14 20:20:41');
INSERT INTO `sys_oper_log` VALUES (112, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"2\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 16:24:07');
INSERT INTO `sys_oper_log` VALUES (113, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"5\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:00:25');
INSERT INTO `sys_oper_log` VALUES (114, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:09:37');
INSERT INTO `sys_oper_log` VALUES (115, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:18:50');
INSERT INTO `sys_oper_log` VALUES (116, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:19:03');
INSERT INTO `sys_oper_log` VALUES (117, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:20:43');
INSERT INTO `sys_oper_log` VALUES (118, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:23:08');
INSERT INTO `sys_oper_log` VALUES (119, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:31:36');
INSERT INTO `sys_oper_log` VALUES (120, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:31:56');
INSERT INTO `sys_oper_log` VALUES (121, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:34:51');
INSERT INTO `sys_oper_log` VALUES (122, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:40:07');
INSERT INTO `sys_oper_log` VALUES (123, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:40:59');
INSERT INTO `sys_oper_log` VALUES (124, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:47:26');
INSERT INTO `sys_oper_log` VALUES (125, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"6\"]}', NULL, 1, 'Cannot invoke \"com.project.model.entity.InfoReviewEntity.getVisitId()\" because \"infoReviewEntity\" is null', '2022-09-24 17:49:48');
INSERT INTO `sys_oper_log` VALUES (126, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-09-24 17:49:58');
INSERT INTO `sys_oper_log` VALUES (127, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"7\"]}', '{\"code\":200,\"data\":7,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-24 17:50:38');
INSERT INTO `sys_oper_log` VALUES (128, '信息审核管理', 3, 'com.project.controller.InfoReviewController.remove()', 'POST', 1, NULL, NULL, '/api/infoReview/remove', '192.168.56.1', NULL, '{\"id\":[\"4\"]}', '{\"code\":200,\"data\":4,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-09-24 21:45:50');
INSERT INTO `sys_oper_log` VALUES (129, '信息审核管理', 3, 'com.project.controller.InfoReviewController.removes()', 'POST', 1, NULL, NULL, '/api/infoReview/removes', '192.168.56.1', NULL, '{\"visitIds\":[\"\"]}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \')\' at line 1\r\n### The error may exist in com/project/mapper/ApplyInfoMapper.java (best guess)\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: DELETE FROM apply_info WHERE id IN ( )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \')\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \')\' at line 1', '2022-09-24 21:47:47');
INSERT INTO `sys_oper_log` VALUES (130, '信息审核管理', 3, 'com.project.controller.InfoReviewController.removes()', 'POST', 1, NULL, NULL, '/api/infoReview/removes', '192.168.56.1', NULL, '{\"visitIds\":[\"4,5\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败,该审核表不存在或已被删除\"}', 0, NULL, '2022-09-24 21:50:30');
INSERT INTO `sys_oper_log` VALUES (131, '信息审核管理', 3, 'com.project.controller.InfoReviewController.removes()', 'POST', 1, NULL, NULL, '/api/infoReview/removes', '192.168.56.1', NULL, '{\"visitIds\":[\"4,5\"]}', '{\"code\":200,\"data\":2,\"description\":\"\",\"message\":\"批量删除成功\"}', 0, NULL, '2022-09-24 21:55:34');
INSERT INTO `sys_oper_log` VALUES (132, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'POST', 1, NULL, NULL, '/api/menu/remove', '192.168.174.1', NULL, '{\"menuId\":[\"2001\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"该菜单不存在\"}', 0, NULL, '2022-11-01 10:30:38');
INSERT INTO `sys_oper_log` VALUES (133, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'POST', 1, NULL, NULL, '/api/menu/remove', '192.168.174.1', NULL, '{\"menuId\":[\"2001\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"该菜单不存在\"}', 0, NULL, '2022-11-01 11:17:53');
INSERT INTO `sys_oper_log` VALUES (134, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'POST', 1, NULL, NULL, '/api/menu/remove', '192.168.174.1', NULL, '{\"menuId\":[\"1\"]}', '{\"data\":\"存在子菜单,不允许删除\",\"description\":\"\"}', 0, NULL, '2022-11-01 11:19:02');
INSERT INTO `sys_oper_log` VALUES (135, '菜单管理', 3, 'com.project.controller.SysMenuController.remove()', 'POST', 1, NULL, NULL, '/api/menu/remove', '192.168.174.1', NULL, '{\"menuId\":[\"2001\"]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"该菜单不存在\"}', 0, NULL, '2022-11-01 11:20:00');
INSERT INTO `sys_oper_log` VALUES (136, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.removes()', 'POST', 1, NULL, NULL, '/api/apply/removes', '192.168.174.1', NULL, '', NULL, 1, 'nested exception is org.apache.ibatis.builder.BuilderException: The expression \'array\' evaluated to a null value.', '2022-12-26 10:56:43');
INSERT INTO `sys_oper_log` VALUES (137, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.removes()', 'POST', 1, NULL, NULL, '/api/apply/removes', '192.168.174.1', NULL, '', NULL, 1, 'nested exception is org.apache.ibatis.builder.BuilderException: The expression \'array\' evaluated to a null value.', '2022-12-26 10:56:49');
INSERT INTO `sys_oper_log` VALUES (138, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '\"{\\\"applyId\\\":10}\"', NULL, 1, 'For input string: \"{\"applyId\":10}\"', '2022-12-26 11:30:20');
INSERT INTO `sys_oper_log` VALUES (139, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '\"{\\\"applyId\\\":10}\"', NULL, 1, 'For input string: \"{\"applyId\":10}\"', '2022-12-26 11:30:37');
INSERT INTO `sys_oper_log` VALUES (140, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-12-26 11:39:21');
INSERT INTO `sys_oper_log` VALUES (141, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{\"id\":1}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败,该访问申请不存在或已被删除\"}', 0, NULL, '2022-12-26 11:39:44');
INSERT INTO `sys_oper_log` VALUES (142, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{\"ids\":[1,2]}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-12-26 11:42:43');
INSERT INTO `sys_oper_log` VALUES (143, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.removes()', 'POST', 1, NULL, NULL, '/api/apply/removes', '192.168.174.1', NULL, '{\"ids\":[1,2]}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败,该访问申请不存在或已被删除\"}', 0, NULL, '2022-12-26 11:43:17');
INSERT INTO `sys_oper_log` VALUES (144, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-12-26 11:44:59');
INSERT INTO `sys_oper_log` VALUES (145, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{}', '{\"code\":40001,\"data\":\"请求数据为空\",\"description\":\"\",\"message\":\"Id为空\"}', 0, NULL, '2022-12-26 11:45:36');
INSERT INTO `sys_oper_log` VALUES (146, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{\"id\":1}', '{\"code\":50030,\"data\":\"删除失败\",\"description\":\"\",\"message\":\"删除失败,该访问申请不存在或已被删除\"}', 0, NULL, '2022-12-26 11:51:20');
INSERT INTO `sys_oper_log` VALUES (147, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{\"id\":10}', '{\"code\":200,\"data\":10,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-12-26 11:51:24');
INSERT INTO `sys_oper_log` VALUES (148, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.remove()', 'POST', 1, NULL, NULL, '/api/apply/remove', '192.168.174.1', NULL, '{\"id\":11}', '{\"code\":200,\"data\":11,\"description\":\"\",\"message\":\"删除成功\"}', 0, NULL, '2022-12-26 11:53:24');
INSERT INTO `sys_oper_log` VALUES (149, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.removes()', 'POST', 1, NULL, NULL, '/api/apply/removes', '192.168.174.1', NULL, '{\"ids\":[12,7]}', '{\"code\":200,\"data\":2,\"description\":\"\",\"message\":\"批量删除成功\"}', 0, NULL, '2022-12-26 11:53:44');
INSERT INTO `sys_oper_log` VALUES (150, '访问申请管理', 3, 'com.project.controller.ApplyInfoController.removes()', 'POST', 1, NULL, NULL, '/api/apply/removes', '192.168.174.1', NULL, '{\"ids\":[13]}', '{\"code\":200,\"data\":1,\"description\":\"\",\"message\":\"批量删除成功\"}', 0, NULL, '2022-12-26 12:08:24');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `data_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '数据类型',
  `parent_resource_id` bigint NULL DEFAULT NULL COMMENT '上级资源ID',
  `resource_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '资源类型 1-系统资源 2-普通资源',
  `resource_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '资源名称',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `resource_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '资源URL',
  `photo_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片URL',
  `resource_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '资源编码',
  `authorization_status` int NULL DEFAULT NULL COMMENT '授权状态 0-未授权 1-已授权',
  `status` int NULL DEFAULT NULL COMMENT '状态 0-正常 1-停用',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2022-08-31 10:15:50', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2022-08-31 10:15:50', '', NULL, '普通角色');
INSERT INTO `sys_role` VALUES (106, '部门经理', 'manager', 1, '1', '0', '0', '系统管理员', '2022-09-05 02:00:14', '系统管理员', '2022-09-05 02:06:24', '部门经理');
INSERT INTO `sys_role` VALUES (112, 'cccc', 'cccc', 1, '1', '0', '2', '系统管理员', '2022-09-05 08:00:28', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1061);
INSERT INTO `sys_role_menu` VALUES (106, 1);
INSERT INTO `sys_role_menu` VALUES (106, 2);
INSERT INTO `sys_role_menu` VALUES (106, 4);
INSERT INTO `sys_role_menu` VALUES (106, 100);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `resource_id` bigint NULL DEFAULT NULL COMMENT '资源ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `permission_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限类型 1-可读可写 2-只读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '姓名',
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '性别 0-未知 1-男 2-女',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '身份证号',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '电话',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `user_account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '账号',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户类型 0-默认 1-管理员 2-公司高管',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `user_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0-正常 1-停用',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(逻辑删除) 0-否 1-是',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, NULL, '系统管理员', '', '', '', '', 'admin', 'f70f60917542e6d0ae6887956925c7c4', '1', '2022-08-22 21:02:51', '2022-08-22 21:02:51', '0', 0);
INSERT INTO `sys_user` VALUES (2, NULL, '用户001', '1', '', '', '', 'user1', 'f70f60917542e6d0ae6887956925c7c4', '0', '2022-08-22 13:41:26', '2022-08-22 13:41:26', '0', 0);
INSERT INTO `sys_user` VALUES (5, NULL, '用户001', '1', '', '', '', 'user1', 'f70f60917542e6d0ae6887956925c7c4', '0', '2022-08-22 13:41:26', '2022-08-22 13:41:26', '0', 1);
INSERT INTO `sys_user` VALUES (9, NULL, 'test', '2', '533001199004232129', '15756465936', '1349769271@qq.com', 'test', 'f70f60917542e6d0ae6887956925c7c4', '0', '2022-12-26 10:51:06', '2022-12-26 10:51:06', '0', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (6, 2);
INSERT INTO `sys_user_role` VALUES (7, 2);
INSERT INTO `sys_user_role` VALUES (8, 2);
INSERT INTO `sys_user_role` VALUES (9, 2);

SET FOREIGN_KEY_CHECKS = 1;
