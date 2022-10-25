
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop table if exists `hrsys_client`;
CREATE TABLE `hrsys_client`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '客户主键',
  `client_type` varchar(255) NULL COMMENT '客户类型（S:供应商，C:用工单位）',
  `client_name` varchar(80) NOT NULL COMMENT '客户名称',
  `client_address` varchar(255) NULL COMMENT '客户地址',
  `contact` varchar(30) NULL COMMENT '联系人',
  `contact_phone` varchar(13) NULL COMMENT '联系人电话',
  `remark` varchar(255) NULL COMMENT '备注',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0正常 1停用）',
  `group_id` bigint NULL COMMENT '小组ID',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT='客户';

drop table if exists `hrsys_client_contract`;
CREATE TABLE `hrsys_client_contract`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` bigint NOT NULL,
  `contract_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists `hrsys_contract`;
CREATE TABLE `hrsys_contract`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '合同主键',,
  `type` varchar(255) NULL COMMENT '合同类型',
  `sign_date` datetime NULL COMMENT '签订日期',
  `valid_date` datetime NULL COMMENT '生效日期',
  `end_date` datetime NULL COMMENT '终止日期',
  `remark` varchar(255) NULL COMMENT '合同摘要',
	`status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0正常 1停用）',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT='合同';

drop table if exists `hrsys_group`;
CREATE TABLE `hrsys_group`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '小组主键',
  `group_name` varchar(80) NULL COMMENT '合同类型',
  `group_leader_id` bigint NULL COMMENT '小组长id',
  `group_user_ids` varchar(120) NULL COMMENT '小组成员列表id',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT='小组';

ALTER TABLE `hrsys_client` ADD CONSTRAINT `fk_hrsys_client_hrsys_group_1` FOREIGN KEY (`group_id`) REFERENCES `hrsys_group` (`id`);
ALTER TABLE `hrsys_client_contract` ADD CONSTRAINT `fk_hrsys_client_contract_hrsys_contract_1` FOREIGN KEY (`contract_id`) REFERENCES `hrsys_contract` (`id`);
ALTER TABLE `hrsys_client_contract` ADD CONSTRAINT `fk_hrsys_client_contract_hrsys_client_1` FOREIGN KEY (`client_id`) REFERENCES `hrsys_client` (`id`);

SET FOREIGN_KEY_CHECKS = 1;