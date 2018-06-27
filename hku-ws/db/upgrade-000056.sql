# ALTER TABLE `scholar`

ALTER TABLE `scholar`
ADD COLUMN `not_app_end_date`  bit(1) NULL DEFAULT 0 AFTER `status`,
ADD COLUMN `scholar_start_date`  date NULL AFTER `not_app_end_date`,
ADD COLUMN `scholar_end_date`  date NULL AFTER `scholar_start_date`;