# ALTER TABLE `staff_info`、staff_role_rel、staff_privilege and CREATE TABLE `staff_role_rel`

ALTER TABLE `staff_info` DROP FOREIGN KEY `staffinfo_staffrole`;

ALTER TABLE `staff_info`
DROP COLUMN `staff_role_id`,
CHANGE COLUMN `given_name` `username`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `email`,
CHANGE COLUMN `surname` `staff_hku_no`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `username`,
CHANGE COLUMN `staff_no` `staff_uid`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `staff_hku_no`;

CREATE TABLE `staff_role_rel` (
`staff_role_rel_id`  varchar(32) NOT NULL ,
`staff_info_id`  varchar(32) NOT NULL ,
`staff_role_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`staff_role_rel_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `staff_role_privilege`
MODIFY COLUMN `staff_role_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `staff_role_privilege_id`,
MODIFY COLUMN `staff_privilege_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `staff_role_id`;

ALTER TABLE `staff_privilege`
ADD COLUMN `module`  varchar(60) NULL AFTER `staff_privilege_id`,
ADD COLUMN `module_cd`  varchar(10) NULL AFTER `module`,
ADD COLUMN `privilege`  int(11) NULL AFTER `module_cd`,
ADD COLUMN `privilege_desc`  varchar(45) NULL AFTER `privilege`;