# update version type to int

ALTER TABLE `app_acad_bg`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_counselor`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_exp_and_achievements`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_faculty`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_personal_particulars`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_prev_studies`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_programme_choice`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_referee`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_reference`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;

ALTER TABLE `app_tel_no`
MODIFY COLUMN `version`  int(11) NOT NULL DEFAULT 0 AFTER `update_date`;