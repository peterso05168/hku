# ALTER TABLE `app_hku_programme`

ALTER TABLE `app_hku_programme`
MODIFY COLUMN `faculty_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `enabled`;
