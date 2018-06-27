# ALTER TABLE `app_prev_studies`、`app_exp_and_achievements`、`app_referee`

ALTER TABLE `app_prev_studies` DROP FOREIGN KEY `prevStudies_institution`;

ALTER TABLE `app_prev_studies`
CHANGE COLUMN `institution_id` `institution`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `application_id`,
ADD COLUMN `is_deleted`  bit(1) NOT NULL DEFAULT 0 AFTER `version`;

ALTER TABLE `app_exp_and_achievements`
ADD COLUMN `is_deleted`  bit(1) NOT NULL DEFAULT 0 AFTER `version`;

ALTER TABLE `app_referee`
ADD COLUMN `is_deleted`  bit(1) NOT NULL DEFAULT 0 AFTER `version`;