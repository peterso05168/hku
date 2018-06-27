# ALTER TABLE `app_acad_bg`、`app_prev_studies`

ALTER TABLE `app_acad_bg`
ADD COLUMN `type_of_education`  varchar(20) NULL AFTER `prog_type_others`,
ADD COLUMN `is_completed_study`  bit(1) NULL DEFAULT 0 AFTER `type_of_education`;

ALTER TABLE `app_prev_studies`
ADD COLUMN `type_of_education`  varchar(20) NULL AFTER `study_mode_cd`,
ADD COLUMN `is_completed_study`  bit NULL DEFAULT 0 AFTER `type_of_education`;