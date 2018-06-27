# ALTER TABLE `app_personal_particulars`

ALTER TABLE `app_personal_particulars`
MODIFY COLUMN `residence_city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `residence_province_id`,
MODIFY COLUMN `residence_country_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `residence_city_id`;