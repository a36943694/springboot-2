/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.101
Source Server Version : 80012
Source Host           : 192.168.1.101:3306
Source Database       : isms

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-09-03 18:26:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', null, '1', '2018-08-22 10:47:44');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@rlkj', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES ('2', 'test1', '0ce7a17ffdeaa57477a28e9c8d8362b4c4eeb5fad22ce6c7cb136e9b779770e8', 'dob3NtD0NtDPyMv38Idz', 'ljl@qq.com', '18719273844', '1', '1', '2018-08-22 10:46:39');
INSERT INTO `sys_user` VALUES ('4', 'test02', '749cdfba60b280bf4942f17827c18ebd1022da0a7e069dcc49ea12d47af94254', 'aCgzUV6ZSRh1RyAcXHYa', '44444@qq.com', '15023263563', '1', '2', '2018-08-30 07:58:42');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '1');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '79ccac28ca7288dacf0f10140274894d', '2018-09-11 15:20:51', '2018-09-01 03:20:51');
INSERT INTO `sys_user_token` VALUES ('2', '5db02c1d06dc9eaab7dbfca47576b6dd', '2018-09-09 22:51:25', '2018-08-22 10:51:25');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '13708558822', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2018-08-21 12:37:41');
INSERT INTO `tb_user` VALUES ('2', '13666175740', '13666175740', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '2018-08-21 09:46:08');

-- ----------------------------
-- Table structure for t_biz_mainworkflow_inst
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_mainworkflow_inst`;
CREATE TABLE `t_biz_mainworkflow_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worksheet_id` int(11) DEFAULT NULL COMMENT '工作票id',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `step_name` varchar(255) DEFAULT NULL COMMENT '流程步骤名称',
  `state` int(2) DEFAULT NULL COMMENT '0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主流程实例表';

-- ----------------------------
-- Records of t_biz_mainworkflow_inst
-- ----------------------------

-- ----------------------------
-- Table structure for t_biz_safecontrol_list
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_safecontrol_list`;
CREATE TABLE `t_biz_safecontrol_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worksheet_id` int(11) DEFAULT NULL COMMENT '工作票id',
  `subworkflow_tmpl_id` int(11) DEFAULT NULL COMMENT '子流程模板id',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `content` varchar(500) DEFAULT NULL COMMENT '安全措施内容',
  `safe_point` varchar(100) DEFAULT NULL COMMENT '安全措施关键字',
  `check_mode` int(2) DEFAULT NULL COMMENT '1 - 图片;  2 - 视频;  3 - 手工',
  `photo_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '照片保存路径',
  `video_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '视频保存路径',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='安全措施清单表';

-- ----------------------------
-- Table structure for t_biz_staff_list
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_staff_list`;
CREATE TABLE `t_biz_staff_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worksheet_id` int(11) DEFAULT NULL COMMENT '工作票id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '人员名称',
  `work_position` int(2) DEFAULT NULL COMMENT '1 - 工作负责人;  2 - 施工人员',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='工作人员配置表';


-- ----------------------------
-- Table structure for t_biz_subworkflow_inst
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_subworkflow_inst`;
CREATE TABLE `t_biz_subworkflow_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worksheet_id` int(11) DEFAULT NULL COMMENT '工作票id',
  `main_flow_id` int(11) DEFAULT NULL COMMENT '主流程id',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `sub_step_name` varchar(255) DEFAULT NULL COMMENT '子流程步骤',
  `state` int(2) DEFAULT NULL COMMENT '0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常',
  `work_prompt` varchar(500) DEFAULT NULL COMMENT '工作内容',
  `safe_point` varchar(100) DEFAULT NULL COMMENT '工作内容关键字',
  `check_mode` int(2) DEFAULT NULL COMMENT '1 - 图片;  2 - 视频;  3 - 手工',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '照片保存路径',
  `video_path` varchar(255) DEFAULT NULL COMMENT '视频保存路径',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子流程实例表';

-- ----------------------------
-- Table structure for t_biz_worksheet
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_worksheet`;
CREATE TABLE `t_biz_worksheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sheet_no` varchar(255) DEFAULT NULL COMMENT '工作票编号',
  `workplace` varchar(100) DEFAULT NULL COMMENT '工作单位',
  `work_station` varchar(255) DEFAULT NULL COMMENT '工作的变、配电站名称',
  `work_group` varchar(100) DEFAULT NULL COMMENT '班组',
  `plan_start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '工作计划开始时间',
  `plan_end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '工作计划结束时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `state` int(2) DEFAULT NULL COMMENT '0 - 未开始;  1 - 进行中;  2 - 已完成;',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '纸质拍照图片地址',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='工作票配置表';


-- ----------------------------
-- Table structure for t_biz_worktask_list
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_worktask_list`;
CREATE TABLE `t_biz_worktask_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worksheet_id` int(11) DEFAULT NULL COMMENT '工作票id',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `work_adress_devices` varchar(500) DEFAULT NULL COMMENT '工作地点及设务名称',
  `content` varchar(500) DEFAULT NULL COMMENT '工作内容',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='工作任务清单表';


-- ----------------------------
-- Table structure for t_cfg_mainworkflow_tmpl
-- ----------------------------
DROP TABLE IF EXISTS `t_cfg_mainworkflow_tmpl`;
CREATE TABLE `t_cfg_mainworkflow_tmpl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `step_name` varchar(255) DEFAULT NULL COMMENT '步骤名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='主流程模版表';

-- ----------------------------
-- Records of t_cfg_mainworkflow_tmpl
-- ----------------------------
INSERT INTO `t_cfg_mainworkflow_tmpl` VALUES ('1', '1', '工作票导入', '2018-08-30 14:52:05', '2018-08-30 14:52:08');
INSERT INTO `t_cfg_mainworkflow_tmpl` VALUES ('2', '2', '工作许可办理', '2018-08-30 14:54:39', '2018-08-30 14:54:41');
INSERT INTO `t_cfg_mainworkflow_tmpl` VALUES ('3', '3', '班前会', '2018-08-30 14:54:57', '2018-08-30 14:55:00');
INSERT INTO `t_cfg_mainworkflow_tmpl` VALUES ('4', '4', '工作监护及过程管控', '2018-08-30 14:55:17', '2018-08-30 14:55:19');
INSERT INTO `t_cfg_mainworkflow_tmpl` VALUES ('5', '5', '工作结束', '2018-08-30 14:55:48', '2018-08-30 14:55:50');
INSERT INTO `t_cfg_mainworkflow_tmpl` VALUES ('6', '6', '班后会', '2018-08-30 16:24:26', '2018-08-30 16:24:26');

-- ----------------------------
-- Table structure for t_cfg_subworkflow_tmpl
-- ----------------------------
DROP TABLE IF EXISTS `t_cfg_subworkflow_tmpl`;
CREATE TABLE `t_cfg_subworkflow_tmpl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `sub_step_name` varchar(255) DEFAULT NULL COMMENT '子流程步骤名称',
  `content` varchar(500) DEFAULT NULL COMMENT '步骤内容',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='子流程模版表';

-- ----------------------------
-- Records of t_cfg_subworkflow_tmpl
-- ----------------------------
INSERT INTO `t_cfg_subworkflow_tmpl` VALUES ('1', '1', '应拉断路器（开关）、隔离开关（刀闸）', null, null, '2018-09-03 18:08:07');
INSERT INTO `t_cfg_subworkflow_tmpl` VALUES ('2', '2', '应装接地线、应合接地刀闸（注明确实地点、名称及接地线编号*）', null, null, '2018-09-03 18:08:33');
INSERT INTO `t_cfg_subworkflow_tmpl` VALUES ('3', '3', '应设遮栏、应挂标示牌及防止二次回路误碰等措施', null, null, '2018-09-03 18:08:45');

-- ----------------------------
-- Table structure for t_cfg_workmoudle
-- ----------------------------
DROP TABLE IF EXISTS `t_cfg_workmoudle`;
CREATE TABLE `t_cfg_workmoudle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_type_id` int(11) DEFAULT NULL COMMENT '工作类型id',
  `seq` int(2) DEFAULT NULL COMMENT '排序',
  `content` varchar(500) DEFAULT NULL COMMENT '工作检查内容',
  `safe_point` varchar(100) DEFAULT NULL COMMENT '工作检查内容关键字',
  `check_mode` int(2) DEFAULT NULL COMMENT '1 - 图片;  2 - 视频;  3 - 手工',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作模型配置表';

-- ----------------------------
-- Table structure for t_cfg_worktype
-- ----------------------------
DROP TABLE IF EXISTS `t_cfg_worktype`;
CREATE TABLE `t_cfg_worktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作类型配置表';

-- ----------------------------
-- Table structure for t_event_inf
-- ----------------------------
DROP TABLE IF EXISTS `t_event_inf`;
CREATE TABLE `t_event_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_code` varchar(255) NOT NULL COMMENT '告警编码',
  `worksheet_id` int(11) DEFAULT NULL COMMENT '工作票id',
  `subworkflow_id` int(11) DEFAULT NULL COMMENT '子流程id',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `severity` varchar(20) DEFAULT NULL COMMENT '告警级别',
  `state` int(2) DEFAULT NULL COMMENT '处理状态:  0 - 未处理;  1 - 已处理',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警信息表';

