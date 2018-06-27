# ALTER TABLE `app_qualification_rslt`

ALTER TABLE `app_qualification_rslt`
ADD COLUMN `is_deleted`  bit(1) NOT NULL DEFAULT 0 AFTER `predicted_grade_others`;