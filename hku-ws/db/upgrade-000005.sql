# ALTER TABLE `applicant_info`

ALTER TABLE `applicant_info`
CHANGE COLUMN `home_tel_no` `home_tel_id`  varchar(32) NULL DEFAULT NULL AFTER `study_country_id`,
CHANGE COLUMN `mobile_tel_no` `mobile_tel_id`  varchar(32) NULL DEFAULT NULL AFTER `home_tel_id`;