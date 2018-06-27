# ALTER TABLE `app_qualification_rslt`

ALTER TABLE `app_qualification_rslt`
ADD COLUMN `is_delete_approved`  bit(1) NOT NULL DEFAULT 0 AFTER `is_deleted`;