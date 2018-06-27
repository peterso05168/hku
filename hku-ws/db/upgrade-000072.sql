# ALTER TABLE `applicant_info`

ALTER TABLE `applicant_info`
ADD COLUMN `study_province_id`  varchar(32) NULL AFTER `chinese_name`,
ADD COLUMN `study_city_id`  varchar(32) NULL AFTER `study_province_id`;
