# ALTER TABLE `applicant_info`

ALTER TABLE `applicant_info`
MODIFY COLUMN `sex`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `residence_country_id`;