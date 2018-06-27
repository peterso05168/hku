# ALTER TABLE `staff_role`、app_requirement、app_institution、adm_form

ALTER TABLE `staff_role`
CHANGE COLUMN `desc` `description`  varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `role_name`;

ALTER TABLE `app_requirement`
CHANGE COLUMN `desc` `description`  varchar(240) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `type`;

ALTER TABLE `app_institution`
CHANGE COLUMN `desc` `description`  varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cd`;

ALTER TABLE `adm_form`
CHANGE COLUMN `desc` `description`  varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `adm_exe_id`;