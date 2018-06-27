# ALTER TABLE `app_special_scheme`

ALTER TABLE `app_special_scheme`
ADD COLUMN `spt_app_scheme`  varchar(100) NULL AFTER `application_id`,
ADD COLUMN `spt_sports`  varchar(40) NULL AFTER `spt_app_scheme`,
ADD COLUMN `spt_level`  varchar(150) NULL AFTER `spt_sports`,
ADD COLUMN `spt_level_others`  varchar(80) NULL AFTER `spt_level`,
ADD COLUMN `spt_hyperlink`  varchar(200) NULL AFTER `spt_level_others`;