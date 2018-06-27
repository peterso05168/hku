# ALTER TABLE `app_acad_bg`

ALTER TABLE `app_acad_bg`
ADD COLUMN `final_rslt`  varchar(15) NULL AFTER `study_country_others`,
ADD COLUMN `not_gpa`  bit(1) NULL AFTER `final_rslt`;
