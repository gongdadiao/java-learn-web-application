/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1_root_root
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : 127.0.0.1:3306
 Source Schema         : learn

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `action_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '前端页面操作表主键id',
  `action_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '前端操作的名字',
  `action_description` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面操作的描述',
  `default_check` tinyint(1) NOT NULL DEFAULT 0 COMMENT '当前操作是否需要校验,true为1,0为false',
  PRIMARY KEY (`action_id`) USING BTREE,
  UNIQUE INDEX `action_name`(`action_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '前端操作比如增删改查等的权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES (1, 'add', '新增', 0);
INSERT INTO `action` VALUES (2, 'query', '查询', 0);
INSERT INTO `action` VALUES (3, 'get', '详情', 0);
INSERT INTO `action` VALUES (4, 'update', '修改', 0);
INSERT INTO `action` VALUES (5, 'delete', '删除', 0);
INSERT INTO `action` VALUES (6, 'import', '导入', 0);
INSERT INTO `action` VALUES (7, 'export', '导出', 0);

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `exam_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '考试表的主键',
  `exam_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '考试名称',
  `exam_avatar` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试的预览图',
  `exam_description` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试描述',
  `exam_questions_url` varchar(256)  CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '试卷的url',
  `exam_answers_url`  varchar(256)  CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '试卷的答案的url',
  `exam_score` int(11) NOT NULL DEFAULT 0 COMMENT '当前考试的总分数',
  `exam_score_danxuan` int(11) NOT NULL DEFAULT 0 COMMENT '单选题的分数',
  `exam_score_duoxuan` int(11) NOT NULL DEFAULT 0 COMMENT '多选题的分数',
  `exam_score_panduan` int(11) NOT NULL DEFAULT 0 COMMENT '判断题的分数',
  `exam_score_tiankong` int(11) NOT NULL DEFAULT 0 COMMENT '填空的分数',
  `exam_score_wenda` int(11) NOT NULL DEFAULT 0 COMMENT '问答的分数',
  `exam_creator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '考试创建者的用户id',
  `exam_time_limit` int(11) NOT NULL DEFAULT 0 COMMENT '考试的时间限制，单位为分钟',
  `exam_start_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '考试有效期开始时间',
  `exam_end_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '考试有效期结束时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '考试的详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('139883c96ec44c6c9c5c2b4b85a7b54a', 'Angular学习', 'https://i.loli.net/2019/11/02/Pda9TEVIXyeptZo.gif', 'Angular小测验', '','', 90, 5, 10, 20,5,5, 'a1b661031adf4a8f969f1869d479fe74', 90, '2019-06-22 12:24:20', '2019-06-22 12:24:20', '2019-06-22 12:24:20', '2019-11-02 16:23:36');
INSERT INTO `exam` VALUES ('258bf9410e854f34bba9c9a08f4dd313', 'Ant Design Pro，Ant最佳实践', 'https://i.loli.net/2019/11/02/sVrTotyQUXEx6ic.jpg', 'Ant最佳实践','','' , 100, 10, 10, 10,5,5, '68042014e23c4ebea7234cb9c77cee5c', 150, '2019-06-22 12:24:20', '2019-06-22 12:24:20', '2019-06-22 12:24:20', '2019-11-02 16:23:58');
INSERT INTO `exam` VALUES ('303efbfe2e5f460c909e935345424244', '梁山广创建的考试1', 'https://i.loli.net/2019/11/02/gIqpGQ8J5B4rVYD.gif', '就是測試下','','' , 45, 5, 5, 5,1,1, 'a1b661031adf4a8f969f1869d479fe74', 90, '2019-10-19 12:08:53', '2019-10-19 12:08:53', '2019-10-19 12:08:53', '2019-11-02 16:24:00');

-- ----------------------------
-- Table structure for exam_record
-- ----------------------------
DROP TABLE IF EXISTS `exam_record`;
CREATE TABLE `exam_record`  (
  `exam_record_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '考试记录表的主键',
  `exam_joiner_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '考试参与者的用户id',
  `exam_join_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '参加考试的时间',
  `exam_time_cost` int(11) NULL DEFAULT 0 COMMENT '完成考试所用的时间,单位分钟',
  `exam_join_score` int(11) NOT NULL DEFAULT 0 COMMENT '参与考试的实际得分',
  `exam_option_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户做出的选择地址，或者批改的结果',
  `exam_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '试卷的id',
  `exam_or_homework` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是考试还是作业',
  `exam_status`varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是考完了还是等待批改',
  PRIMARY KEY (`exam_record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '考试记录表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for page
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page`  (
  `page_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '前端页面表主键id',
  `page_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '页面的名称,要唯一',
  `page_description` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面的功能性描述',
  `action_ids` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面对应的操作权限列表，用-连接action的id',
  PRIMARY KEY (`page_id`) USING BTREE,
  UNIQUE INDEX `page_name`(`page_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '前端页面表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of page
-- ----------------------------
INSERT INTO `page` VALUES (1, 'dashboard', '仪表盘', '1-2-3-4-5');
INSERT INTO `page` VALUES (2, 'exam-card', '考试列表', '1-6-3-4');
INSERT INTO `page` VALUES (3, 'exam-record-list', '考试记录', '1-6-3-4');
INSERT INTO `page` VALUES (4, 'question-admin', '问题管理', '1-6-3-4');
INSERT INTO `page` VALUES (5, 'exam-table-list', '考试管理', '1-6-3-4');
INSERT INTO `page` VALUES (6, 'user', '个人页', '1-6-3-4-5-7');
INSERT INTO `page` VALUES (7, 'resource-list', '资源列表', '1-6-3-4-5-7');
INSERT INTO `page` VALUES (8, 'resource-add', '添加资源', '1-6-3-4-5-7');

-- ----------------------------
-- Table structure for theme
-- ----------------------------
DROP TABLE IF EXISTS  `theme`;
CREATE TABLE `theme`(
`theme_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主题的主键',
`theme_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主体名称',
`theme_seq` INT DEFAULT 0,
`theme_description` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主题的描述',
PRIMARY KEY (`theme_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '主题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of theme
-- ----------------------------

INSERT INTO `theme` VALUES (1,'JAVA平台与面向对象基础（一）',1,'');
INSERT INTO `theme` VALUES (2,'JAVA平台与面向对象基础（二）',10,'');
INSERT INTO `theme` VALUES (3,'JAVA平台与面向对象基础（三）',20,'');
INSERT INTO `theme` VALUES (4,'JAVA GUI编程（一）',30,'');
INSERT INTO `theme` VALUES (5,'JAVA GUI编程（二）',40,'');
INSERT INTO `theme` VALUES (6,'输入输出流与文件操作',50,'');
INSERT INTO `theme` VALUES (7,'多线程编程与网络编程',60,'');
INSERT INTO `theme` VALUES (8,'期末测试',70,'');


-- -------------------------------------
-- Table structure for question_type
-- -------------------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type`  (
  `question_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目类型表的主键',
  `question_type_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '题目类型名称',
  `question_type_description` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目类型的描述',
  PRIMARY KEY (`question_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '问题类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_type
-- ----------------------------
INSERT INTO `question_type` VALUES (1, 'danxuan', '单选题');
INSERT INTO `question_type` VALUES (2, 'duoxuan', '多选题');
INSERT INTO `question_type` VALUES (3, 'panduan', '判断题');
INSERT INTO `question_type` VALUES (4, 'tiankong', '填空题');
INSERT INTO `question_type` VALUES (5, 'wenda', '问答题');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `question_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目表的主键',
  `question_type_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '题目类型名称',
  `question_description` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目的描述',
  `question_content`  varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目内容',
  `question_answers` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目答案',
  `question_selections` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目选项(多选，单选)',
  `question_option_split`  varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '#$' COMMENT '题目选项分隔符',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '问题表' ROW_FORMAT = Dynamic;

-- -----------------------------------
-- Records of question
-- -----------------------------------
INSERT INTO `question` VALUES (4,'danxuan','对学生基础的考察','一加一等于几？','B:2','A:1#$B:2#$C:3','#$');
INSERT INTO `question` VALUES (5,'duoxuan','对学生基础的考察','请选ABC？','A:1#$B:2#$C:3','A:1#$B:2#$C:3#$D:7','#$');
INSERT INTO `question` VALUES (6,'panduan','对学生基础的考察','1+1=2是对是错啊？','right','','');
INSERT INTO `question` VALUES (7,'tiankong','对学生基础的考察','我们()应该？','大家','','');
INSERT INTO `question` VALUES (8,'wenda','对学生基础的考察','小狗是一种什么生物？','','','');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表主键id',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_description` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色的描述',
  `role_detail` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色的详细功能阐述',
  `role_page_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '当前角色所能访问的页面的id集合',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员', '拥有教师和学生的所有权限', '1-2-3-4-5-6');
INSERT INTO `role` VALUES (2, 'teacher', '教师', '出题、组试卷、管理学生和试卷', '1-2-3-4-5-6-8');
INSERT INTO `role` VALUES (3, 'student', '学生', '参与考试，查看分数', '1-2-3-6-7');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id,主键，字符串型',
  `user_username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `user_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户昵称',
  `user_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户秘密',
  `user_role_id` int(11) NOT NULL COMMENT '当前用户的角色的id',
  `user_avatar` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户的头像地址',
  `user_description` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户的自我描述',
  `user_email` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户手机号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_username`(`user_username`) USING BTREE,
  UNIQUE INDEX `user_email`(`user_email`) USING BTREE,
  UNIQUE INDEX `user_phone`(`user_phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('68042014e23c4ebea7234cb9c77cee5c', 'teacher', '暮雪飞扬', 'YWRtaW4xMjM=', 2, 'https://i.loli.net/2019/11/02/OUfHKhMSwRv1ntX.jpg', '快乐就好', '1648266192@qq.com', '15261897332', '2019-05-06 18:03:27', '2019-11-02 16:37:20');
INSERT INTO `user` VALUES ('79392778a90d4639a297dbd0bae0f779', 'student', '红领巾', 'YWRtaW4xMjM=', 3, 'https://i.loli.net/2019/11/02/rCHKVJd4jTovzW9.jpg', '好好学习，天天向上', 'liangshanguang@huawei.com', '17712345678', '2019-05-06 18:07:14', '2019-11-02 16:37:31');
INSERT INTO `user` VALUES ('a1b661031adf4a8f969f1869d479fe74', 'admin', '西门吹雪', 'YWRtaW4xMjM=', 1, 'https://i.loli.net/2019/11/02/DvPiSRJrzoH1tkZ.gif', '绳锯木断，水滴石穿', 'liangshanguang2@gmail.com', '17601324488', '2019-05-06 17:57:44', '2019-11-02 16:37:37');


-- -----------------------------
--
-- ------------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `res_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '资源id,主键，字符串型',
  `res_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '资源名',
  `res_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '资源描述',
  `res_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '资源链接',
  `res_type`varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL  NOT NULL COMMENT '资源类型',
  `res_theme_id` int(11) NOT NULL COMMENT '主题id,主键，字符串型',
  `res_seq` int(11) NOT NULL COMMENT '资源序列',
  `res_creator_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建者的',
  `res_avatar` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图像的地址',
  `res_upload_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    PRIMARY KEY (`res_id`) USING BTREE
)ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '资源列表' ROW_FORMAT = Dynamic;




SET FOREIGN_KEY_CHECKS = 1;


