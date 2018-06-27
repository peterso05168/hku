# ALTER TABLE `staff_info`

ALTER TABLE `staff_info`
CHANGE COLUMN `status` `is_active`  bit(1) NULL DEFAULT NULL AFTER `staff_uid`;