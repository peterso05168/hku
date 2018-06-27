# ALTER TABLE `applicant_info`

ALTER TABLE `applicant_info`
ADD COLUMN `is_not_studing`  bit(1) NULL DEFAULT NULL AFTER `study_city_id`;
