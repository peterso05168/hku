# ALTER TABLE `applicant_info` and app_acad_bg to add column

ALTER TABLE `applicant_info`
MODIFY COLUMN `passport_no`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `date_of_birth`,
MODIFY COLUMN `hkid`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `passport_no`;

ALTER TABLE `app_acad_bg`
ADD COLUMN `is_not_studing`  bit(1) NOT NULL AFTER `study_mode_cd`,
ADD COLUMN `is_partcp_next_njcee`  bit(1) NOT NULL AFTER `is_not_studing`;