# ALTER TABLE `app_programme_choice` and `app_qualification`

ALTER TABLE `app_programme_choice`
ADD COLUMN `last_updated_by`  varchar(50) NULL AFTER `wdra_cd`,
ADD COLUMN `last_updated_date`  datetime NULL AFTER `last_updated_by`;

ALTER TABLE `app_qualification`
ADD COLUMN `last_updated_by`  varchar(50) NULL AFTER `is_delete_approved`;