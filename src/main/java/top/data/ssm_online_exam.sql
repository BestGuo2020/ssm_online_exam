/*
 Navicat Premium Data Transfer

 Source Server         : mysql123
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : ssm_online_exam

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 11/06/2021 17:17:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `classCode` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '8位的班级码',
  `className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名',
  `classDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级描述',
  `belongTeacher` int(255) NOT NULL COMMENT '属于哪个老师',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `classCode`(`classCode`) USING BTREE,
  INDEX `classes_ibfk_1`(`belongTeacher`) USING BTREE,
  CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`belongTeacher`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES (15, '75384419', '软件4班 - J2EE架设', '本课程是信息技术学院计算机科学与技术专业本科生必修的一门专业课。J2EE核心是一组技术规范，它包含具有共同标准及规格的各类组件、服务架构及技术层次。\n本课程主要介绍Servlet/JavaBean/Jsp和Struts2/Hibernate/Spring技术，学生学习并熟练应用其中一种技术开发应用程序。通过本课程的学习，一方面使学生掌握J2EE的核心知识，掌握J2EE应用程序的开发方式、组装和部署，另一方面为学生的毕业和将来的就业做好充分的准备', 1);
INSERT INTO `classes` VALUES (16, '70337390', '软件4班 - Java编程基础', '本课程的先修课程：最好是学过一门程序设计语言（如C、C++、C#、Python、VB等任何一门语言）。\n本课程是在已有的基础上让学习者能够以Java语言编写具有一定规模、综合性的应用程序。对后面的操作系统、编译原理、数据库等课程来说，该课程是一个承上启下的课程。', 1);
INSERT INTO `classes` VALUES (17, '67426732', '软件4班-操作系统1', '软件4班-操作系统-wenhong2020-1', 1);
INSERT INTO `classes` VALUES (18, '67426734', '软件4班-操作系统2', '软件4班-操作系统-wenhong2020-2', 1);
INSERT INTO `classes` VALUES (19, '67426736', '软件4班-操作系统3', '软件4班-操作系统-wenhong2020-3', 1);
INSERT INTO `classes` VALUES (20, '67426737', '软件4班-操作系统4', '软件4班-操作系统-wenhong2020-4', 1);
INSERT INTO `classes` VALUES (21, '67426738', '软件4班-操作系统5', '软件4班-操作系统-wenhong2020-5', 1);
INSERT INTO `classes` VALUES (22, '67426739', '软件4班-操作系统6', '软件4班-操作系统-wenhong2020-6', 1);
INSERT INTO `classes` VALUES (23, '67426740', '软件4班-操作系统7', '软件4班-操作系统-wenhong2020-7', 1);
INSERT INTO `classes` VALUES (25, '67426742', '软件4班-操作系统9', '软件4班-操作系统-wenhong2020-9', 1);
INSERT INTO `classes` VALUES (26, '67426743', '软件4班-操作系统10', '软件4班-操作系统-wenhong2020-10', 1);
INSERT INTO `classes` VALUES (27, '67426744', '软件4班-操作系统11', '软件4班-操作系统-wenhong2020-11', 1);
INSERT INTO `classes` VALUES (28, '67426745', '软件4班-操作系统12', '软件4班-操作系统-wenhong2020-12', 1);
INSERT INTO `classes` VALUES (29, '67426746', '软件4班-操作系统13', '软件4班-操作系统-wenhong2020-13', 1);
INSERT INTO `classes` VALUES (30, '67426747', '软件4班-操作系统14', '软件4班-操作系统-wenhong2020-14', 1);
INSERT INTO `classes` VALUES (31, '67426748', '软件4班-操作系统15', '软件4班-操作系统-wenhong2020-15', 1);
INSERT INTO `classes` VALUES (36, '43872406', 'java编程基础', 'java编程基础java编程基础java编程基础java编程基础', 9);
INSERT INTO `classes` VALUES (37, '98744388', 'java基础', 'java基础java基础java基础java基础', 10);

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES (1, '小强', 12, '123456789');
INSERT INTO `demo` VALUES (2, '小花', 18, '111222333');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `examName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试名称',
  `startTime` datetime NOT NULL COMMENT '考试开始时间',
  `stopTime` datetime NOT NULL COMMENT '考试结束时间',
  `selectOne` double(3, 1) NOT NULL COMMENT '单选题分数',
  `selectMore` double(3, 1) NOT NULL COMMENT '多选题分数',
  `score` double(3, 1) DEFAULT NULL COMMENT '总分',
  `qList` varchar(12000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '题目列表，题号之间是用逗号隔开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (90, '风风光光挺好听11', '2021-06-10 00:00:00', '2021-06-11 00:00:00', 2.0, 2.0, 30.0, '95,61,70,72,67,80,64,82,94,62,74,97,98,96,78');
INSERT INTO `exam` VALUES (91, '风风光光挺好听1', '2021-06-10 23:17:00', '2021-06-10 23:59:59', 2.0, 2.0, 18.0, '182,185,197,188,187,186,189,195,202');
INSERT INTO `exam` VALUES (92, 'ggtgtrg', '2021-06-11 00:00:00', '2021-07-11 00:00:00', 2.0, 2.0, NULL, NULL);
INSERT INTO `exam` VALUES (93, 'revrfeffrf', '2021-06-11 00:00:00', '2021-06-13 00:00:00', 2.0, 1.0, 24.0, '204,201,187,182,208,181,186,191,203,189,195,209,202,198');
INSERT INTO `exam` VALUES (94, 'java期末测试3', '2021-06-11 00:00:00', '2021-06-11 23:00:00', 2.0, 2.0, 24.0, '213,214,215,220,224,227,230,231,228,225,232,239');
INSERT INTO `exam` VALUES (95, 'ajfsff112', '2021-06-11 08:00:00', '2021-06-11 10:00:00', 2.0, 2.0, 32.0, '221,235,230,220,214,234,212,222,217,218,216,238,228,232,225,229');
INSERT INTO `exam` VALUES (96, '风风光光挺好听111', '2021-06-11 00:00:00', '2021-07-11 00:00:00', 2.0, 2.0, 12.0, '215,216,217,221,226,225');

-- ----------------------------
-- Table structure for exam_class
-- ----------------------------
DROP TABLE IF EXISTS `exam_class`;
CREATE TABLE `exam_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `classId` int(11) NOT NULL COMMENT '班级id',
  `examId` int(11) NOT NULL COMMENT '考试id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `classId`(`classId`) USING BTREE,
  INDEX `examId`(`examId`) USING BTREE,
  CONSTRAINT `exam_class_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `exam_class_ibfk_2` FOREIGN KEY (`examId`) REFERENCES `exam` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_class
-- ----------------------------
INSERT INTO `exam_class` VALUES (89, 16, 90);
INSERT INTO `exam_class` VALUES (90, 36, 91);
INSERT INTO `exam_class` VALUES (91, 36, 92);
INSERT INTO `exam_class` VALUES (92, 36, 93);
INSERT INTO `exam_class` VALUES (93, 37, 94);
INSERT INTO `exam_class` VALUES (94, 37, 95);
INSERT INTO `exam_class` VALUES (95, 37, 96);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `questionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `isMulti` tinyint(1) NOT NULL COMMENT '是否为多选？1为是，0为否',
  `option1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项A',
  `option2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项B',
  `option3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项C',
  `option4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项D',
  `option5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项E',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正确选项',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '解析',
  `belongClass` int(255) NOT NULL COMMENT '属于那个班级',
  `level` int(255) NOT NULL COMMENT '难度系数：1.简单、2.中等、3.困难',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question_ibfk_1`(`belongClass`) USING BTREE,
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`belongClass`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 258 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (2, '34+12=?', 0, '22', '12', '34', '46', '', 'D', '34+12=46', 15, 1);
INSERT INTO `question` VALUES (3, '34-12=?', 0, '22', '12', '34', '46', '', 'A', '1+1=2', 15, 1);
INSERT INTO `question` VALUES (6, '平年一年多少天？', 0, '366', '365', '364', '363', '', 'B', '无', 15, 1);
INSERT INTO `question` VALUES (26, '1×1=?', 0, '1', '1', '1', '4', NULL, 'A', '1×1=1', 15, 1);
INSERT INTO `question` VALUES (27, '1×2=?', 0, '1', '1', '1', '4', '5', 'B', '1×2=2', 15, 1);
INSERT INTO `question` VALUES (28, '1×3=?', 0, '1', '1', '1', NULL, NULL, 'C', '1×3=3', 15, 1);
INSERT INTO `question` VALUES (58, 'JAVA所定义的版本中不包括：（）', 0, 'JAVA2 EE', 'JAVA2 Card', 'JAVA2 ME', ' JAVA2 HE', 'JAVA2 SE ', 'D', '无', 16, 1);
INSERT INTO `question` VALUES (59, '下列说法正确的是（  ）', 0, 'JAVA程序的main方法必须写在类里面', 'AVA程序中可以有多个main方法', 'A和a在java中是同一个变量', 'JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来', NULL, 'A', '无', 16, 1);
INSERT INTO `question` VALUES (60, '变量命名规范说法正确的是（  ）', 0, '变量由字母、下划线、数字、$符号随意组成', '变量不能以数字作为开头', 'A和a在java中是同一个变量', ' 不同类型的变量，可以起相同的名字', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (61, '下列javaDoc注释正确的是（  ） ', 0, '/*我爱北京天安门*/', '//我爱北京天安门*/', ' /**我爱北京天安门*/', ' /*我爱北京天安门**/', NULL, 'C', '无', 16, 1);
INSERT INTO `question` VALUES (62, '为一个boolean类型变量赋值时，可以使用(  )方式', 0, ' boolean = 1', 'boolean a = (9 >= 10)', 'boolean a=\"真\"', ' boolean a = = false', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (63, '以下(  )不是合法的标识符', 0, 'STRING', 'x3x', 'void', 'de$f ', NULL, 'C', '无', 16, 1);
INSERT INTO `question` VALUES (64, '表达式(11+3*8)/4%3的值是(  )', 0, '31', '0', '1', '2', NULL, 'D', '无', 16, 1);
INSERT INTO `question` VALUES (65, '（  ）表达式不可以作为循环条件', 0, 'i++; ', 'i>5', 'bEqual = str.equals(\"q\")', 'count = = i  ', NULL, 'A', '无', 16, 1);
INSERT INTO `question` VALUES (66, '运算符优先级别排序正确的是（）', 0, '由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符', '由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符', '由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符', '', '', 'A', '无', 16, 2);
INSERT INTO `question` VALUES (67, '下列值不为true的表达式有（ ）', 0, '\"john\" = = \"john\"', '\"john\".equals(\"john\")', ' \"john\" = \"john\" ', '\"john\".equals(new String(\"john\")) ', NULL, 'C', '无', 16, 2);
INSERT INTO `question` VALUES (68, '下列输出结果是（ ）\nInt  a = 0 ; while ( a < 5 ) {     \n    switch(a) {     \n         case 0: \n         case 3 : a = a + 2;     \n         case 1 :     \n         case 2 : a = a + 3;     default : a = a + 5;     \n   } \n} \nSystem.out.print ( a ) ;', 0, '0', '5', '10', '其他', NULL, 'C', '无', 16, 2);
INSERT INTO `question` VALUES (69, '下列代码输出结果是(  ) \nint i = 10; \nwhile ( i > 0 ){  \n     i = i + 1;   \n     if ( i = =10 ){     \n           break;   \n     }\n}', 0, 'while循环执行10次', '死循环', '循环一次都不执行', '循环执行一次', NULL, 'B', '无', 16, 2);
INSERT INTO `question` VALUES (70, '下面有关for循环的描述正确的是（ ）', 0, ' for循环体语句中，可以包含多条语句，但要用大括号括起来', ' for循环只能用于循环次数已经确定的情况', '在for循环中，不能使用break语句跳出循环', 'for循环是先执行循环体语句，后进行条件判断', NULL, 'A', '无', 16, 2);
INSERT INTO `question` VALUES (71, '下列（  ）属于引用数据类型（选择两项)', 1, 'String', ' char', '用户自定义的Student类类型 ', ' int ', NULL, 'AC', '无', 16, 1);
INSERT INTO `question` VALUES (72, '对象的特征在类中表示为变量，称为类的（  ）', 0, '对象', ' 属性', '方法 ', '数据类型', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (73, '在java中下列关于自动类型转换说法正确的是（ ）', 0, '基本数据类型和String相加结果一定是字符串型', 'char类型和int类型相加结果一定是字符', ' double类型可以自动转换为int', 'char + int + double +\"\" 结果一定是double', NULL, 'A', '无', 16, 2);
INSERT INTO `question` VALUES (74, '关于类的描叙正确的是（ ）（选择两项）', 1, '在类中定义的变量称为类的成员变量，在别的类中可以直接使用', '局部变量的作用范围仅仅在定义它的方法内，或者是在定义它的控制流块中', '使用别的类的方法仅仅需要引用方法的名字即可', '一个类的方法使用该类的另一个方法时可以直接引用方法名', NULL, 'BD', '无', 16, 2);
INSERT INTO `question` VALUES (75, '下列关于字符串的描叙中错误的是（ ）（选择两项', 1, '字符串是对象', ' String对象存储字符串的效率比StringBuffer高 ', '可以使用StringBuffer sb=\"这里是字符串\"声明并初始化StringBuffer对象sb', ' String类提供了许多用来操作字符串的方法：连接，提取，查询等', NULL, 'BC', '无', 16, 2);
INSERT INTO `question` VALUES (76, '以下（）代码，能够对数组正确初始化（或者是默认初始化', 0, ' int[] a', ' a = {1, 2, 3, 4, 5}', 'int[] a = new int[5]{1, 2, 3, 4, 5}', ' int[] a = new int[5]', NULL, 'D', '无', 16, 1);
INSERT INTO `question` VALUES (77, 'score是一个整数数组，有五个元素，已经正确初始化并赋值，仔细阅读下面代码，程序运行结果是（ ）\ntemp = score[0]; \nfor (int index = 1;index < 5;index++) {\n     if (score[index] < temp) {\n         temp = score[index];\n     } \n}', 0, '求最大数', '求最小数', '找到数组最后一个元素', '编译出错 ', '', 'B', '无', 16, 2);
INSERT INTO `question` VALUES (78, '下面关于数组的说法中，错误的是（ ）（选择两项）', 1, '在类中声明一个整数数组作为成员变量，如果没有给它赋值，数值元素值为空', '数组可以在内存空间连续存储任意一组数据', '数组必须先声明，然后才能使用', '数组本身是一个对象', NULL, 'AB', '无', 16, 2);
INSERT INTO `question` VALUES (79, '在Java中,关于构造方法，下列说法错误的是( )', 0, ' 构造方法的名称必须与类名相同', '构造方法可以带参数', ' 构造方法不可以重载', '构造方法绝对不能有返回值', NULL, 'C', '无', 16, 2);
INSERT INTO `question` VALUES (80, '执行下面代码结果（  ）    \nfor(int i=0;;) {\n        System.out.println(\"这是 \"+i);\n        break；\n}', 0, '语法错误，缺少表达式2和表达式3 ', '死循环', ' 程序什么都不输出', '输出：这是0 ', NULL, 'D', '无', 16, 1);
INSERT INTO `question` VALUES (81, '下面代码输出结果是（ ）。\nint i=0,s=0;\ndo{\n          if (i%2 = = 0 ){\n                    i++;\n                    continue; \n          }\n                 i++;\n                 s = s + i;\n} while (i<7);\nSystem.out.println(s);', 0, '16', '12', '28', '21', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (82, '下面（ ）不是String类提供的合法的方法', 0, ' equals(String)', ' trim() ', 'append()    StringBuffer', 'indexOf()', NULL, 'C', '无', 16, 1);
INSERT INTO `question` VALUES (83, '在JAVA中，以下（ ）类的对象以键-值的方式存储对象', 0, ' java.util.List', ' java.util.ArrayList', ' java.util.HashMap ', 'java.util.LinkedList', NULL, 'C', '无', 16, 1);
INSERT INTO `question` VALUES (84, '给定如下所示的JAVA代码，则运行时，会产生（ ）类型的异常  String s = null;  s.concat(\"abc\");', 0, 'ArithmeticException', 'NullPointerException', 'IOException', 'ClassNotFoundExceptio', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (85, '给定java代码如下所示，在A处新增下列（ ）方法，是对cal方法的重载(选择两项) \npublic class Test {  \n        public void cal(int x, int y, int z) {  } \n } ', 1, 'public int cal(int x,int y,float z){return 0;}', 'public int cal(int x,int y,int z){return 0;}', 'public void cal(int x,int z){} ', 'public viod cal(int z,int y,int x){} ', NULL, 'AC', '无', 16, 2);
INSERT INTO `question` VALUES (86, '给定如下Java 程序代码片段，编译运行这段代码，结果是（ ）。    \njava.util.HashMap map = new java.util.HashMap();   \nmap.put(\"name\",null);      \nmap.put(\"name\",\"Jack\");      \nSystem.out.println(map.get(\"name\")); ', 0, 'null', 'Jack', 'nullJack', '运行时出现异常', '', 'B', '无', 16, 2);
INSERT INTO `question` VALUES (87, '下面选项中，(  )是Java 关键字。', 0, 'then', 'continue', 'java', 'PUBLIC', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (88, '下面语句中，正确的是(  )', 0, 'boolean b=”true”;', 'double x=2.5f;', 'char c=”A”;', 'float y=0.8d;', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (89, '设有定义“int k=3;” ，语法正确且值为true 的表达式是(  )', 0, 'k=3', 'k++>3;', 'k--==3&&k++==3;', 'k++==3||++k>3;', NULL, 'D', '无', 16, 1);
INSERT INTO `question` VALUES (90, '设有定义：String s=“World”;，下列语句错误的是(  )', 0, 'int m=s.indexOf(‘r’);', 'char c=s.charAt(0);', 'int n=s.length();', 'String str=s.append(‘2’);', NULL, 'D', '无', 16, 1);
INSERT INTO `question` VALUES (91, '假设在Java 源程序文件“MyClass.java”中只含有一个类，而且这个类必须能够被位于个庞大的软件系统中的所有Java 类访问到，那么下面(   )声明有可能是符合要求的类声明。', 0, 'private class MyClass extends Object', 'public class myclass extends Object', 'public class MyClass', 'class MyClass extends Object', NULL, 'C', '无', 16, 1);
INSERT INTO `question` VALUES (92, '在Java 中，用package 语句说明一个包时，该包的层次结构必须是(  )', 0, '与文件目录的层次相同', '与文件的结构相同', '与文件类型相同', '与文件大小相同', NULL, 'A', '无', 16, 1);
INSERT INTO `question` VALUES (93, '下面关于数组的说法，错误的是(  )', 0, '数组是最简单的复合数据类型，是一系列数据的集合', '声明数组时，必须分配内存', '数组的元素可以是值(基本数据类型)、对象或其他数组', '一个数组中的所有值都必须是相同的类型', NULL, 'B', '无', 16, 1);
INSERT INTO `question` VALUES (94, '下面关于方法的说法，错误的是(  )', 0, 'Java 中的方法参数传递时传值调用，而不是地址调用', '方法体是对方法的实现，包括变量声明和Java 的合法语句', '如果程序定义了一个或多个构造方法，在创建对象时，也可以用系统自动生成空的构造方法', '类的私有方法不能被其子类直接访问', NULL, 'C', '无', 16, 1);
INSERT INTO `question` VALUES (95, '下面关于内部类的说法，错误的是(  )', 0, '内部类不能有自己的成员方法和成员变量', '内部类可用abstract 修饰定义为抽象类，也可以用private 或protected 定义', '内部类可作为其他类的成员，而且可访问它所在类的成员', NULL, NULL, 'A', '无', 16, 1);
INSERT INTO `question` VALUES (96, 'Java中的集合类包括ArrayList、LinkedList、HashMap等类，下列关于集合类描述正确的是（    ）', 1, 'ArrayList和LinkedList均实现了List接口', 'ArrayList的查询速度比LinkedList快', '添加和删除元素时，ArrayList的表现更佳', 'HashMap实现Map接口，它允许任何类型的键和值对象，并允许将null用作键或值', NULL, 'ABD', '无', 16, 2);
INSERT INTO `question` VALUES (97, '下面关于HashMap和Hashtable的区别，说法正确的是(    ) ', 1, 'Hashtable线程不安全，效率高', 'HashMap线程安全，效率低', 'HashMap允许null键和值', 'Hashtable不允许null键和值', NULL, 'CD', '无', 16, 2);
INSERT INTO `question` VALUES (98, '关于HashMap集合说法正确的是(    ) ', 1, 'HashMap集合是双列集合', 'HashMap集合不允许存储重复键', 'HashMap集合不允许存储重复值', 'HashMap集合线程是安全的', NULL, 'AB', '无', 16, 2);
INSERT INTO `question` VALUES (99, 'List集合的遍历方式有如下哪几种 (    )', 1, 'Iterator迭代器实现', '增强for循环实现', 'get()和size()方法结合实现', 'get()和length()方法结合实现', NULL, 'ABC', '无', 16, 2);
INSERT INTO `question` VALUES (100, '下面集合中属于线程安全的类是（   ）', 1, 'ArrayList ', 'Vector', 'HashMap', 'Hashtable', NULL, 'BD', '无', 16, 2);
INSERT INTO `question` VALUES (101, '有关线程的叙述（）是对的', 1, '一旦一个线程被创建，它就立即开始运行。', '使用start()方法可以使一个线程成为可运行的，但是它不一定立即开始运行。', '如果复用一个线程，可以调用再次调用start方法，使已经结束的线程复活。', 'join方法,可使当前线程阻塞，直到thread线程运行结束', NULL, 'BCD', '无', 16, 2);
INSERT INTO `question` VALUES (102, '下面哪些是实现线程同步的方式（ ）', 1, 'Synchronized 关键字修饰方法', 'Synchronized修饰代码块', '调用wait方法协调线程', '调用notify方协调线程', NULL, 'AB', '无', 16, 2);
INSERT INTO `question` VALUES (103, 'HashSet去重复，在HashSet内部需要调用Object的哪些方法', 1, 'hashCode方法。', 'toString方法。', 'clone方法', 'equals方法', NULL, 'AD', '无', 16, 2);
INSERT INTO `question` VALUES (181, '1+2=？', 0, '1', '2', '3', '4', '', 'C', '1+2=3', 36, 1);
INSERT INTO `question` VALUES (182, 'JAVA所定义的版本中不包括：（）', 0, 'JAVA2 EE', 'JAVA2 Card', 'JAVA2 ME', ' JAVA2 HE', 'JAVA2 SE ', 'D', '无', 36, 1);
INSERT INTO `question` VALUES (183, '下列说法正确的是（  ）', 0, 'JAVA程序的main方法必须写在类里面', 'AVA程序中可以有多个main方法', 'A和a在java中是同一个变量', 'JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来', NULL, 'A', '无', 36, 1);
INSERT INTO `question` VALUES (184, '变量命名规范说法正确的是（  ）', 0, '变量由字母、下划线、数字、$符号随意组成', '变量不能以数字作为开头', 'A和a在java中是同一个变量', ' 不同类型的变量，可以起相同的名字', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (185, '下列javaDoc注释正确的是（  ） ', 0, '/*我爱北京天安门*/', '//我爱北京天安门*/', ' /**我爱北京天安门*/', ' /*我爱北京天安门**/', NULL, 'C', '无', 36, 1);
INSERT INTO `question` VALUES (186, '为一个boolean类型变量赋值时，可以使用(  )方式', 0, ' boolean = 1', 'boolean a = (9 >= 10)', 'boolean a=\"真\"', ' boolean a = = false', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (187, '以下(  )不是合法的标识符', 0, 'STRING', 'x3x', 'void', 'de$f ', NULL, 'C', '无', 36, 1);
INSERT INTO `question` VALUES (188, '表达式(11+3*8)/4%3的值是(  )', 0, '31', '0', '1', '2', NULL, 'D', '无', 36, 1);
INSERT INTO `question` VALUES (189, '（  ）表达式不可以作为循环条件', 0, 'i++; ', 'i>5', 'bEqual = str.equals(\"q\")', 'count = = i  ', NULL, 'A', '无', 36, 1);
INSERT INTO `question` VALUES (190, '运算符优先级别排序正确的是（）', 0, '由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符', '由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符', NULL, '由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符', NULL, 'A', '无', 36, 2);
INSERT INTO `question` VALUES (191, '下列值不为true的表达式有（ ）', 0, '\"john\" = = \"john\"', '\"john\".equals(\"john\")', ' \"john\" = \"john\" ', '\"john\".equals(new String(\"john\")) ', NULL, 'C', '无', 36, 2);
INSERT INTO `question` VALUES (192, '下列输出结果是（ ）\nInt  a = 0 ; while ( a < 5 ) {     \n    switch(a) {     \n         case 0: \n         case 3 : a = a + 2;     \n         case 1 :     \n         case 2 : a = a + 3;     default : a = a + 5;     \n   } \n} \nSystem.out.print ( a ) ;', 0, '0', '5', '10', '其他', NULL, 'C', '无', 36, 2);
INSERT INTO `question` VALUES (193, '下列代码输出结果是(  ) \nint i = 10; \nwhile ( i > 0 ){  \n     i = i + 1;   \n     if ( i = =10 ){     \n           break;   \n     }\n}', 0, 'while循环执行10次', '死循环', '循环一次都不执行', '循环执行一次', NULL, 'B', '无', 36, 2);
INSERT INTO `question` VALUES (194, '下面有关for循环的描述正确的是（ ）', 0, ' for循环体语句中，可以包含多条语句，但要用大括号括起来', ' for循环只能用于循环次数已经确定的情况', '在for循环中，不能使用break语句跳出循环', 'for循环是先执行循环体语句，后进行条件判断', NULL, 'A', '无', 36, 2);
INSERT INTO `question` VALUES (195, '下列（  ）属于引用数据类型（选择两项)', 1, 'String', ' char', '用户自定义的Student类类型 ', ' int ', NULL, 'AC', '无', 36, 1);
INSERT INTO `question` VALUES (196, '对象的特征在类中表示为变量，称为类的（  ）', 0, '对象', ' 属性', '方法 ', '数据类型', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (197, '在java中下列关于自动类型转换说法正确的是（ ）', 0, '基本数据类型和String相加结果一定是字符串型', 'char类型和int类型相加结果一定是字符', ' double类型可以自动转换为int', 'char + int + double +\"\" 结果一定是double', NULL, 'A', '无', 36, 2);
INSERT INTO `question` VALUES (198, '关于类的描叙正确的是（ ）（选择两项）', 1, '在类中定义的变量称为类的成员变量，在别的类中可以直接使用', '局部变量的作用范围仅仅在定义它的方法内，或者是在定义它的控制流块中', '使用别的类的方法仅仅需要引用方法的名字即可', '一个类的方法使用该类的另一个方法时可以直接引用方法名', NULL, 'BD', '无', 36, 2);
INSERT INTO `question` VALUES (199, '下列关于字符串的描叙中错误的是（ ）（选择两项', 1, '字符串是对象', ' String对象存储字符串的效率比StringBuffer高 ', '可以使用StringBuffer sb=\"这里是字符串\"声明并初始化StringBuffer对象sb', ' String类提供了许多用来操作字符串的方法：连接，提取，查询等', NULL, 'BC', '无', 36, 2);
INSERT INTO `question` VALUES (200, '以下（）代码，能够对数组正确初始化（或者是默认初始化', 0, ' int[] a', ' a = {1, 2, 3, 4, 5}', 'int[] a = new int[5]{1, 2, 3, 4, 5}', ' int[] a = new int[5]', NULL, 'D', '无', 36, 1);
INSERT INTO `question` VALUES (201, 'score是一个整数数组，有五个元素，已经正确初始化并赋值，仔细阅读下面代码，程序运行结果是（ ） temp = score[0]; for (int index = 1;index < 5;index++) {     if (score[index] < temp) {         temp = score[index];     } }', 0, '求最大数', '求最小数', '找到数组最后一个元素', '编译出错 ', NULL, 'B', '无', 36, 2);
INSERT INTO `question` VALUES (202, '下面关于数组的说法中，错误的是（ ）（选择两项）', 1, '在类中声明一个整数数组作为成员变量，如果没有给它赋值，数值元素值为空', '数组可以在内存空间连续存储任意一组数据', '数组必须先声明，然后才能使用', '数组本身是一个对象', NULL, 'AB', '无', 36, 2);
INSERT INTO `question` VALUES (203, '在Java中,关于构造方法，下列说法错误的是( )', 0, ' 构造方法的名称必须与类名相同', '构造方法可以带参数', ' 构造方法不可以重载', '构造方法绝对不能有返回值', NULL, 'C', '无', 36, 2);
INSERT INTO `question` VALUES (204, '执行下面代码结果（  ）    \nfor(int i=0;;) {\n        System.out.println(\"这是 \"+i);\n        break；\n}', 0, '语法错误，缺少表达式2和表达式3 ', '死循环', ' 程序什么都不输出', '输出：这是0 ', NULL, 'D', '无', 36, 1);
INSERT INTO `question` VALUES (205, '下面代码输出结果是（ ）。\nint i=0,s=0;\ndo{\n          if (i%2 = = 0 ){\n                    i++;\n                    continue; \n          }\n                 i++;\n                 s = s + i;\n} while (i<7);\nSystem.out.println(s);', 0, '16', '12', '28', '21', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (206, '下面（ ）不是String类提供的合法的方法', 0, ' equals(String)', ' trim() ', 'append()    StringBuffer', 'indexOf()', NULL, 'C', '无', 36, 1);
INSERT INTO `question` VALUES (207, '在JAVA中，以下（ ）类的对象以键-值的方式存储对象', 0, ' java.util.List', ' java.util.ArrayList', ' java.util.HashMap ', 'java.util.LinkedList', NULL, 'C', '无', 36, 1);
INSERT INTO `question` VALUES (208, '给定如下所示的JAVA代码，则运行时，会产生（ ）类型的异常  String s = null;  s.concat(\"abc\");', 0, 'ArithmeticException', 'NullPointerException', 'IOException', 'ClassNotFoundExceptio', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (209, '给定java代码如下所示，在A处新增下列（ ）方法，是对cal方法的重载(选择两项) \npublic class Test {  \n        public void cal(int x, int y, int z) {  } \n } ', 1, 'public int cal(int x,int y,float z){return 0;}', 'public int cal(int x,int y,int z){return 0;}', 'public void cal(int x,int z){} ', 'public viod cal(int z,int y,int x){} ', NULL, ' AC', '无', 36, 2);
INSERT INTO `question` VALUES (210, '给定如下Java 程序代码片段，编译运行这段代码，结果是（ ）。    java.util.HashMap map = new java.util.HashMap();   map.put(\"name\",null);      map.put(\"name\",\"Jack\");      System.out.println(map.get(\"name\")); ', 0, 'null', 'Jack', 'nullJack', '运行时出现异常', NULL, 'B', '无', 36, 2);
INSERT INTO `question` VALUES (211, '7+8=？', 0, '13', '14', '15', '12', '', 'C', '7+8=15', 37, 1);
INSERT INTO `question` VALUES (212, 'JAVA所定义的版本中不包括：（）', 0, 'JAVA2 EE', 'JAVA2 Card', 'JAVA2 ME', ' JAVA2 HE', 'JAVA2 SE ', 'D', '无', 37, 1);
INSERT INTO `question` VALUES (213, '下列说法正确的是（  ）', 0, 'JAVA程序的main方法必须写在类里面', 'AVA程序中可以有多个main方法', 'A和a在java中是同一个变量', 'JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来', NULL, 'A', '无', 37, 1);
INSERT INTO `question` VALUES (214, '变量命名规范说法正确的是（  ）', 0, '变量由字母、下划线、数字、$符号随意组成', '变量不能以数字作为开头', 'A和a在java中是同一个变量', ' 不同类型的变量，可以起相同的名字', NULL, 'B', '无', 37, 1);
INSERT INTO `question` VALUES (215, '下列javaDoc注释正确的是（  ） ', 0, '/*我爱北京天安门*/', '//我爱北京天安门*/', ' /**我爱北京天安门*/', ' /*我爱北京天安门**/', NULL, 'C', '无', 37, 1);
INSERT INTO `question` VALUES (216, '为一个boolean类型变量赋值时，可以使用(  )方式', 0, ' boolean = 1', 'boolean a = (9 >= 10)', 'boolean a=\"真\"', ' boolean a = = false', NULL, 'B', '无', 37, 1);
INSERT INTO `question` VALUES (217, '以下(  )不是合法的标识符', 0, 'STRING', 'x3x', 'void', 'de$f ', NULL, 'C', '无', 37, 1);
INSERT INTO `question` VALUES (218, '表达式(11+3*8)/4%3的值是(  )', 0, '31', '0', '1', '2', NULL, 'D', '无', 37, 1);
INSERT INTO `question` VALUES (219, '（  ）表达式不可以作为循环条件', 0, 'i++; ', 'i>5', 'bEqual = str.equals(\"q\")', 'count = = i  ', NULL, 'A', '无', 37, 1);
INSERT INTO `question` VALUES (220, '运算符优先级别排序正确的是（）', 0, '由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符', '由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符', NULL, '由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符', NULL, 'A', '无', 37, 2);
INSERT INTO `question` VALUES (221, '下列值不为true的表达式有（ ）', 0, '\"john\" = = \"john\"', '\"john\".equals(\"john\")', ' \"john\" = \"john\" ', '\"john\".equals(new String(\"john\")) ', NULL, 'C', '无', 37, 2);
INSERT INTO `question` VALUES (222, '下列输出结果是（ ）\nInt  a = 0 ; while ( a < 5 ) {     \n    switch(a) {     \n         case 0: \n         case 3 : a = a + 2;     \n         case 1 :     \n         case 2 : a = a + 3;     default : a = a + 5;     \n   } \n} \nSystem.out.print ( a ) ;', 0, '0', '5', '10', '其他', NULL, 'C', '无', 37, 2);
INSERT INTO `question` VALUES (223, '下列代码输出结果是(  ) \nint i = 10; \nwhile ( i > 0 ){  \n     i = i + 1;   \n     if ( i = =10 ){     \n           break;   \n     }\n}', 0, 'while循环执行10次', '死循环', '循环一次都不执行', '循环执行一次', NULL, 'B', '无', 37, 2);
INSERT INTO `question` VALUES (224, '下面有关for循环的描述正确的是（ ）', 0, ' for循环体语句中，可以包含多条语句，但要用大括号括起来', ' for循环只能用于循环次数已经确定的情况', '在for循环中，不能使用break语句跳出循环', 'for循环是先执行循环体语句，后进行条件判断', NULL, 'A', '无', 37, 2);
INSERT INTO `question` VALUES (225, '下列（  ）属于引用数据类型（选择两项)', 1, 'String', ' char', '用户自定义的Student类类型 ', ' int ', NULL, 'AC', '无', 37, 1);
INSERT INTO `question` VALUES (226, '对象的特征在类中表示为变量，称为类的（  ）', 0, '对象', ' 属性', '方法 ', '数据类型', NULL, 'B', '无', 37, 1);
INSERT INTO `question` VALUES (227, '在java中下列关于自动类型转换说法正确的是（ ）', 0, '基本数据类型和String相加结果一定是字符串型', 'char类型和int类型相加结果一定是字符', ' double类型可以自动转换为int', 'char + int + double +\"\" 结果一定是double', NULL, 'A', '无', 37, 2);
INSERT INTO `question` VALUES (228, '关于类的描叙正确的是（ ）（选择两项）', 1, '在类中定义的变量称为类的成员变量，在别的类中可以直接使用', '局部变量的作用范围仅仅在定义它的方法内，或者是在定义它的控制流块中', '使用别的类的方法仅仅需要引用方法的名字即可', '一个类的方法使用该类的另一个方法时可以直接引用方法名', NULL, 'BD', '无', 37, 2);
INSERT INTO `question` VALUES (229, '下列关于字符串的描叙中错误的是（ ）（选择两项', 1, '字符串是对象', ' String对象存储字符串的效率比StringBuffer高 ', '可以使用StringBuffer sb=\"这里是字符串\"声明并初始化StringBuffer对象sb', ' String类提供了许多用来操作字符串的方法：连接，提取，查询等', NULL, 'BC', '无', 37, 2);
INSERT INTO `question` VALUES (230, '以下（）代码，能够对数组正确初始化（或者是默认初始化', 0, ' int[] a', ' a = {1, 2, 3, 4, 5}', 'int[] a = new int[5]{1, 2, 3, 4, 5}', ' int[] a = new int[5]', NULL, 'D', '无', 37, 1);
INSERT INTO `question` VALUES (231, 'score是一个整数数组，有五个元素，已经正确初始化并赋值，仔细阅读下面代码，程序运行结果是（ ） temp = score[0]; for (int index = 1;index < 5;index++) {     if (score[index] < temp) {         temp = score[index];     } }', 0, '求最大数', '求最小数', '找到数组最后一个元素', '编译出错 ', NULL, 'B', '无', 37, 2);
INSERT INTO `question` VALUES (232, '下面关于数组的说法中，错误的是（ ）（选择两项）', 1, '在类中声明一个整数数组作为成员变量，如果没有给它赋值，数值元素值为空', '数组可以在内存空间连续存储任意一组数据', '数组必须先声明，然后才能使用', '数组本身是一个对象', NULL, 'AB', '无', 37, 2);
INSERT INTO `question` VALUES (233, '在Java中,关于构造方法，下列说法错误的是( )', 0, ' 构造方法的名称必须与类名相同', '构造方法可以带参数', ' 构造方法不可以重载', '构造方法绝对不能有返回值', NULL, 'C', '无', 37, 2);
INSERT INTO `question` VALUES (234, '执行下面代码结果（  ）    \nfor(int i=0;;) {\n        System.out.println(\"这是 \"+i);\n        break；\n}', 0, '语法错误，缺少表达式2和表达式3 ', '死循环', ' 程序什么都不输出', '输出：这是0 ', NULL, 'D', '无', 37, 1);
INSERT INTO `question` VALUES (235, '下面代码输出结果是（ ）。\nint i=0,s=0;\ndo{\n          if (i%2 = = 0 ){\n                    i++;\n                    continue; \n          }\n                 i++;\n                 s = s + i;\n} while (i<7);\nSystem.out.println(s);', 0, '16', '12', '28', '21', NULL, 'B', '无', 37, 1);
INSERT INTO `question` VALUES (236, '下面（ ）不是String类提供的合法的方法', 0, ' equals(String)', ' trim() ', 'append()    StringBuffer', 'indexOf()', NULL, 'C', '无', 37, 1);
INSERT INTO `question` VALUES (237, '在JAVA中，以下（ ）类的对象以键-值的方式存储对象', 0, ' java.util.List', ' java.util.ArrayList', ' java.util.HashMap ', 'java.util.LinkedList', NULL, 'C', '无', 37, 1);
INSERT INTO `question` VALUES (238, '给定如下所示的JAVA代码，则运行时，会产生（ ）类型的异常  String s = null;  s.concat(\"abc\");', 0, 'ArithmeticException', 'NullPointerException', 'IOException', 'ClassNotFoundExceptio', NULL, 'B', '无', 37, 1);
INSERT INTO `question` VALUES (239, '给定java代码如下所示，在A处新增下列（ ）方法，是对cal方法的重载(选择两项) \npublic class Test {  \n        public void cal(int x, int y, int z) {  } \n } ', 1, 'public int cal(int x,int y,float z){return 0;}', 'public int cal(int x,int y,int z){return 0;}', 'public void cal(int x,int z){} ', 'public viod cal(int z,int y,int x){} ', NULL, ' AC', '无', 37, 2);
INSERT INTO `question` VALUES (240, '给定如下Java 程序代码片段，编译运行这段代码，结果是（ ）。    java.util.HashMap map = new java.util.HashMap();   map.put(\"name\",null);      map.put(\"name\",\"Jack\");      System.out.println(map.get(\"name\")); ', 0, 'null', 'Jack', 'nullJack', '运行时出现异常', NULL, 'B', '无', 37, 2);
INSERT INTO `question` VALUES (241, '下面选项中，(  )是Java 关键字。', 0, 'then', 'continue', 'java', 'PUBLIC', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (242, '下面语句中，正确的是(  )', 0, 'boolean b=”true”;', 'double x=2.5f;', 'char c=”A”;', 'float y=0.8d;', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (243, '设有定义“int k=3;” ，语法正确且值为true 的表达式是(  )', 0, 'k=3', 'k++>3;', 'k--==3&&k++==3;', 'k++==3||++k>3;', NULL, 'D', '无', 36, 1);
INSERT INTO `question` VALUES (244, '设有定义：String s=“World”;，下列语句错误的是(  )', 0, 'int m=s.indexOf(‘r’);', 'char c=s.charAt(0);', 'int n=s.length();', 'String str=s.append(‘2’);', NULL, 'D', '无', 36, 1);
INSERT INTO `question` VALUES (245, '假设在Java 源程序文件“MyClass.java”中只含有一个类，而且这个类必须能够被位于个庞大的软件系统中的所有Java 类访问到，那么下面(   )声明有可能是符合要求的类声明。', 0, 'private class MyClass extends Object', 'public class myclass extends Object', 'public class MyClass', 'class MyClass extends Object', NULL, 'C', '无', 36, 1);
INSERT INTO `question` VALUES (246, '在Java 中，用package 语句说明一个包时，该包的层次结构必须是(  )', 0, '与文件目录的层次相同', '与文件的结构相同', '与文件类型相同', '与文件大小相同', NULL, 'A', '无', 36, 1);
INSERT INTO `question` VALUES (247, '下面关于数组的说法，错误的是(  )', 0, '数组是最简单的复合数据类型，是一系列数据的集合', '声明数组时，必须分配内存', '数组的元素可以是值(基本数据类型)、对象或其他数组', '一个数组中的所有值都必须是相同的类型', NULL, 'B', '无', 36, 1);
INSERT INTO `question` VALUES (248, '下面关于方法的说法，错误的是(  )', 0, 'Java 中的方法参数传递时传值调用，而不是地址调用', '方法体是对方法的实现，包括变量声明和Java 的合法语句', '如果程序定义了一个或多个构造方法，在创建对象时，也可以用系统自动生成空的构造方法', '类的私有方法不能被其子类直接访问', NULL, 'C', '无', 36, 1);
INSERT INTO `question` VALUES (249, '下面关于内部类的说法，错误的是(  )', 0, '内部类不能有自己的成员方法和成员变量', '内部类可用abstract 修饰定义为抽象类，也可以用private 或protected 定义', '内部类可作为其他类的成员，而且可访问它所在类的成员', NULL, NULL, 'A', '无', 36, 1);
INSERT INTO `question` VALUES (250, 'Java中的集合类包括ArrayList、LinkedList、HashMap等类，下列关于集合类描述正确的是（    ）', 1, 'ArrayList和LinkedList均实现了List接口', 'ArrayList的查询速度比LinkedList快', '添加和删除元素时，ArrayList的表现更佳', 'HashMap实现Map接口，它允许任何类型的键和值对象，并允许将null用作键或值', NULL, 'ABD', '无', 36, 2);
INSERT INTO `question` VALUES (251, '下面关于HashMap和Hashtable的区别，说法正确的是(    ) ', 1, 'Hashtable线程不安全，效率高', 'HashMap线程安全，效率低', 'HashMap允许null键和值', 'Hashtable不允许null键和值', NULL, 'CD', '无', 36, 2);
INSERT INTO `question` VALUES (252, '关于HashMap集合说法正确的是(    ) ', 1, 'HashMap集合是双列集合', 'HashMap集合不允许存储重复键', 'HashMap集合不允许存储重复值', 'HashMap集合线程是安全的', NULL, 'AB', '无', 36, 2);
INSERT INTO `question` VALUES (253, 'List集合的遍历方式有如下哪几种 (    )', 1, 'Iterator迭代器实现', '增强for循环实现', 'get()和size()方法结合实现', 'get()和length()方法结合实现', NULL, 'ABC', '无', 36, 2);
INSERT INTO `question` VALUES (254, '下面集合中属于线程安全的类是（   ）', 1, 'ArrayList ', 'Vector', 'HashMap', 'Hashtable', NULL, 'BD', '无', 36, 2);
INSERT INTO `question` VALUES (255, '有关线程的叙述（）是对的', 1, '一旦一个线程被创建，它就立即开始运行。', '使用start()方法可以使一个线程成为可运行的，但是它不一定立即开始运行。', '如果复用一个线程，可以调用再次调用start方法，使已经结束的线程复活。', 'join方法,可使当前线程阻塞，直到thread线程运行结束', NULL, 'BCD', '无', 36, 2);
INSERT INTO `question` VALUES (256, '下面哪些是实现线程同步的方式（ ）', 1, 'Synchronized 关键字修饰方法', 'Synchronized修饰代码块', '调用wait方法协调线程', '调用notify方协调线程', NULL, 'AB', '无', 36, 2);
INSERT INTO `question` VALUES (257, 'HashSet去重复，在HashSet内部需要调用Object的哪些方法', 1, 'hashCode方法。', 'toString方法。', 'clone方法', 'equals方法', NULL, 'AD', '无', 36, 2);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `examId` int(11) NOT NULL COMMENT '考试id',
  `stuId` int(11) NOT NULL COMMENT '学生id',
  `answer` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '选择题答案记录',
  `wrong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '错误的题号',
  `correct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '正确的题号',
  `score` double(3, 1) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `record_ibfk_2`(`stuId`) USING BTREE,
  INDEX `examId`(`examId`) USING BTREE,
  CONSTRAINT `record_ibfk_2` FOREIGN KEY (`stuId`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `record_ibfk_3` FOREIGN KEY (`examId`) REFERENCES `exam_class` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (22, 91, 17, 'C,C,D,C,C,B,C,AC,ACD', '182,197,188,189,202', '185,187,186,195', 8.0);
INSERT INTO `record` VALUES (23, 91, 13, 'D,C,A,B,C,B,A,AC,ACD', '188,202', '182,185,197,187,186,189,195', 14.0);
INSERT INTO `record` VALUES (24, 91, 9, 'D,C,A,A,C,B,A,AC,AB', '188', '182,185,197,187,186,189,195,202', 16.0);
INSERT INTO `record` VALUES (25, 90, 8, 'B,C,C,C,B,D,B,B,B,B,BC,D,BC,ABC,ABC', '95,70,72,67,64,82,94,74,97,98,96,78', '61,80,62', 6.0);
INSERT INTO `record` VALUES (26, 91, 8, 'D,C,A,B,C,B,A,AC,AB', '188', '182,185,197,187,186,189,195,202', 16.0);
INSERT INTO `record` VALUES (27, 91, 10, 'D,B,A,B,C,C,C,BC,AB', '185,188,186,189,195', '182,197,187,202', 8.0);
INSERT INTO `record` VALUES (28, 94, 20, 'D,A,C, ,C,A,C,B,BC,BC,AB,AB', '213,214,220,224,230,228,225,239', '215,227,231,232', 8.0);
INSERT INTO `record` VALUES (29, 93, 11, 'A,B,C,B, , , , , ,B, , , , ', '204,182,208,181,186,191,203,189,195,209,202,198', '201,187', 4.0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `stuId` int(11) DEFAULT NULL COMMENT '学号（可选填项）',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `gender` tinyint(255) NOT NULL COMMENT '性别：1为男，2为女',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'gwh@qq.com', 2012114413, 'gwh', '1234567', 1);
INSERT INTO `student` VALUES (2, '11', 111, '111', '11', 1);
INSERT INTO `student` VALUES (3, '22', 22, '22', '22', 2);
INSERT INTO `student` VALUES (4, 'gjj@qq.com', 1, '1', '123456', 1);
INSERT INTO `student` VALUES (5, 'gjj2@qq.com', 1, '1', '1', 2);
INSERT INTO `student` VALUES (6, 'heguo1@foxmail.com', 2212122, '赫赫', '123456', 1);
INSERT INTO `student` VALUES (7, 'luotianyu@123.com', 43432443, '罗天宇', '123456', 1);
INSERT INTO `student` VALUES (8, 'luotianyi@126.com', 93874263, '洛天依', '123456', 1);
INSERT INTO `student` VALUES (9, '114514@163.com', 114514, '田所浩二', '123345', 1);
INSERT INTO `student` VALUES (10, '1919810@qq.com', 1919810, '朴秀', '123456', 1);
INSERT INTO `student` VALUES (11, 'sunxiaochuan@163.com', 80001234, '孙笑川', '123456', 1);
INSERT INTO `student` VALUES (12, 'heimaojingzhang@giao.com', 12342322, 'giao桑', '1q2w3e', 1);
INSERT INTO `student` VALUES (13, 'yaobaiyang@gui.com', 34234345, '摇摆杨', '1a2s3d4f', 1);
INSERT INTO `student` VALUES (14, 'laoba@qq.com', 857478752, '老八', '1a2s3d', 1);
INSERT INTO `student` VALUES (15, 'xiaohehe@qq.com', 372846192, 'xiaohehe', '123456', 1);
INSERT INTO `student` VALUES (17, 'xiaohehe2@qq.com', 324234345, 'xiaohehe2', '123456', 1);
INSERT INTO `student` VALUES (20, 'xiaohehe3@qq.com', 41834762, 'xiaohehe3', '123456', 1);

-- ----------------------------
-- Table structure for student_class
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `stuId` int(11) NOT NULL COMMENT '学生id',
  `classId` int(11) NOT NULL COMMENT '班级id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stuId`(`stuId`) USING BTREE,
  INDEX `student_class_ibfk_2`(`classId`) USING BTREE,
  CONSTRAINT `student_class_ibfk_1` FOREIGN KEY (`stuId`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_class_ibfk_2` FOREIGN KEY (`classId`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_class
-- ----------------------------
INSERT INTO `student_class` VALUES (7, 7, 15);
INSERT INTO `student_class` VALUES (8, 8, 15);
INSERT INTO `student_class` VALUES (9, 9, 15);
INSERT INTO `student_class` VALUES (10, 10, 15);
INSERT INTO `student_class` VALUES (11, 11, 15);
INSERT INTO `student_class` VALUES (12, 13, 15);
INSERT INTO `student_class` VALUES (18, 2, 16);
INSERT INTO `student_class` VALUES (19, 3, 16);
INSERT INTO `student_class` VALUES (20, 4, 16);
INSERT INTO `student_class` VALUES (21, 5, 16);
INSERT INTO `student_class` VALUES (57, 7, 16);
INSERT INTO `student_class` VALUES (63, 6, 16);
INSERT INTO `student_class` VALUES (76, 1, 17);
INSERT INTO `student_class` VALUES (77, 1, 16);
INSERT INTO `student_class` VALUES (78, 1, 15);
INSERT INTO `student_class` VALUES (79, 8, 16);
INSERT INTO `student_class` VALUES (80, 1, 18);
INSERT INTO `student_class` VALUES (83, 17, 36);
INSERT INTO `student_class` VALUES (84, 17, 16);
INSERT INTO `student_class` VALUES (85, 13, 36);
INSERT INTO `student_class` VALUES (86, 9, 36);
INSERT INTO `student_class` VALUES (87, 8, 36);
INSERT INTO `student_class` VALUES (88, 11, 36);
INSERT INTO `student_class` VALUES (89, 10, 36);
INSERT INTO `student_class` VALUES (90, 14, 36);
INSERT INTO `student_class` VALUES (92, 20, 37);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户自增id',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `teacherId` int(11) DEFAULT NULL COMMENT '教师工号（可选填项）',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `gender` int(4) NOT NULL DEFAULT 3 COMMENT '性别：1为男，2为女',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'gwh@qq.com', NULL, 'wenhong2020', '1q2w3e', 0);
INSERT INTO `teacher` VALUES (2, 'gwh1@qq.com', NULL, '11', '11', 1);
INSERT INTO `teacher` VALUES (3, '1', 1, '1', '1', 2);
INSERT INTO `teacher` VALUES (5, 'gjj@qq.com', 1, '1', '1', 1);
INSERT INTO `teacher` VALUES (9, 'hehe1@qq.com', 378367617, 'hehe1', '123456', 1);
INSERT INTO `teacher` VALUES (10, 'hehe2@qq.com', 12342234, 'hehe2', '123456', 1);

SET FOREIGN_KEY_CHECKS = 1;
