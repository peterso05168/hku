# ALTER TABLE `applicant_info` and `app_personal_particulars`

ALTER TABLE `applicant_info`
ADD COLUMN `sex`  char(1) NOT NULL AFTER `residence_country_id`,
ADD COLUMN `date_of_birth`  date NULL AFTER `sex`,
ADD COLUMN `passport_no`  varchar(50) NOT NULL AFTER `date_of_birth`,
ADD COLUMN `hkid`  varchar(20) NOT NULL AFTER `passport_no`,
ADD COLUMN `correspondence_addr`  varchar(500) NULL AFTER `hkid`;

ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personStudy_country`;

ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `person_institution`;

ALTER TABLE `app_personal_particulars`
DROP COLUMN `study_country_id`,
DROP COLUMN `institution_id`,
DROP COLUMN `recv_info_ind`,
CHANGE COLUMN `person_id` `personal_paricular_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
CHANGE COLUMN `alter_email` `email`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `personal_paricular_id`,
CHANGE COLUMN `nationality_province_id` `residence_province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `nationality_country_id`,
CHANGE COLUMN `nationality_city_id` `residence_city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `residence_province_id`,
ADD COLUMN `hkid`  varchar(20) NOT NULL AFTER `correspondence_addr`,
ADD COLUMN `passport_no`  varchar(50) NOT NULL AFTER `hkid`,
ADD COLUMN `national_id_card`  varchar(50) NULL AFTER `passport_no`;