# ALTER TABLE `applicant_anncmnt` MODIFY COLUMN `type_cd`

ALTER TABLE `applicant_anncmnt`
MODIFY COLUMN `type_cd`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `id`;