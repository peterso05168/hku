# ALTER TABLE `app_acad_bg`

ALTER TABLE `app_acad_bg`
ADD COLUMN `study_country_others`  varchar(200) NULL AFTER `is_partcp_next_njcee`;

ALTER TABLE `app_acad_bg`
MODIFY COLUMN `country_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `acad_bg_id`,
MODIFY COLUMN `city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `province_id`,
MODIFY COLUMN `institution_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `city_id`;