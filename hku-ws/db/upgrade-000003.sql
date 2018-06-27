# ADD COLUMN `residence_country_id`

ALTER TABLE `applicant_info`
ADD COLUMN `residence_country_id`  varchar(32) NULL AFTER `receive_info_flag`;