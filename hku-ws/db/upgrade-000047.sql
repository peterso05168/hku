# ALTER TABLE `staff_role`

ALTER TABLE `staff_role`
CHANGE COLUMN `status` `is_active`  bit(1) NULL DEFAULT b'1' AFTER `description`;
