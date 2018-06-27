# ALTER TABLE app_programme_choice

ALTER TABLE `app_programme_choice`
ADD COLUMN `assigned_to`  varchar(32) NULL AFTER `assigned_quota_mainland`,
ADD COLUMN `uni_comp_req`  bit(1) NOT NULL DEFAULT 0 AFTER `assigned_to`;

ALTER TABLE `app_programme_choice` ADD CONSTRAINT `assigned_to_staff_key` FOREIGN KEY (`assigned_to`) REFERENCES `staff_info` (`staff_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;