# ALTER TABLE `app_qualification`

ALTER TABLE `app_qualification`
CHANGE COLUMN `njcee_exam_place_cd` `njcee_exam_province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `ib_predicted_rslt`;

ALTER TABLE `app_qualification` 
ADD CONSTRAINT `qualification_njcee_province_id` FOREIGN KEY (`njcee_exam_province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;