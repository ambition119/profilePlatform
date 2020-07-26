USE user_profile;
DROP TABLE IF EXISTS `user_label_info`;
CREATE TABLE `user_label_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `label_id` bigint(64) NOT NULL COMMENT '标签id',
  `label_root_id` bigint(64) DEFAULT NULL COMMENT '标签分类id,即第一级id',
  `label_parent_id` bigint(64) DEFAULT NULL COMMENT '标签父id',
  `label_en_name` varchar(128) NOT NULL COMMENT '标签英文名称',
  `label_name` varchar(128) NOT NULL COMMENT '标签名称',
  `label_own_app_num` bigint(64) NOT NULL DEFAULT '1' COMMENT '标签所属产品编号',
  `label_version` int(4) NOT NULL DEFAULT '1' COMMENT '标签版本',
  `label_level` varchar(8) NOT NULL DEFAULT '01' COMMENT '标签层级，01，02，03，04级',
  `label_desc` text COMMENT '标签描述',
  `label_work_fun` text COMMENT '标签加工逻辑',
  `label_value_desc` text COMMENT '标签值说明',
  `label_effect` text COMMENT '标签意义或者作用',
  `label_example` text COMMENT '标签示例',
  `label_online_time` varchar(24) DEFAULT NULL COMMENT '标签上线时间',
  `label_owner` varchar(128) DEFAULT NULL COMMENT '标签操作人',
  `label_owner_email` varchar(128) DEFAULT NULL COMMENT '标签操作人邮箱',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0:新增，1:更新，2:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `label_id_uk` (`label_id`,`label_version`,`label_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签信息表';

-- 单用户的标签表
DROP TABLE IF EXISTS `user_label_ids`;
CREATE TABLE `user_label_ids` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `label_collector_ids` varchar(128) NOT NULL COMMENT '用户的标签id集合，使用逗号分隔',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0:新增，1:更新，2:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签集合表';

-- 单标签的用户表
DROP TABLE IF EXISTS `label_user_ids`;
CREATE TABLE `label_user_ids` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `label_id` bigint(20) NOT NULL COMMENT '标签主键id',
  `user_collector_ids` varchar(256) NOT NULL COMMENT '标签的用户id集合，使用逗号分隔',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0:新增，1:更新，2:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签集合表';

-- 自定义标签规则集合
DROP TABLE IF EXISTS `user_label_collector`;
CREATE TABLE `user_label_collector` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `label_collector_name` varchar(128) NOT NULL COMMENT '标签集合名称',
  `label_collector_ids` varchar(256) NOT NULL COMMENT '标签id集合，使用逗号分隔',
  `label_collector_owner` varchar(128) DEFAULT NULL COMMENT '标签操作人',
  `label_collector_owner_email` varchar(128) DEFAULT NULL COMMENT '标签操作人邮箱',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0:新增，1:更新，2:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签集合表';







-- 后续开发使用
DROP TABLE IF EXISTS `user_profile.user_app_info`;
CREATE TABLE `user_profile.user_app_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `label_own_app_num` bigint(64) NOT NULL DEFAULT '1' COMMENT '标签所属产品编号',
  `label_own_app_name` varchar(128) NOT NULL COMMENT '标签所属产品名称',
  `label_desc` varchar(256) COMMENT '备注',
  `label_owner` varchar(128) DEFAULT NULL COMMENT '操作人',
  `label_owner_email` varchar(128) DEFAULT NULL COMMENT '操作人邮箱',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0:新增，1:更新，2:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `label_own_app_num_uk` (`label_own_app_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app名称信息表';
