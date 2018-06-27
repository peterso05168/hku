# ALTER TABLE `app_prog_choice_gps_rslt` 

ALTER TABLE `app_prog_choice_gps_rslt`
ADD COLUMN `app_qualification_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `app_programme_choice_id`;

ALTER TABLE `app_prog_choice_gps_rslt` ADD CONSTRAINT `gps_app_qualification_id_key` FOREIGN KEY (`app_qualification_id`) REFERENCES `app_qualification` (`app_qualification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;