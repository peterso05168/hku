# ALTER TABLE `app_prev_studies`

ALTER TABLE `app_prev_studies`
DROP COLUMN `institution`,
DROP COLUMN `programme_duration`,
DROP COLUMN `date_of_admisssiont_to_prog`,
DROP COLUMN `programme_completed`,
DROP COLUMN `level_attained`,
DROP COLUMN `classification`,
DROP COLUMN `remark`,
MODIFY COLUMN `programme_type_cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `application_id`,
MODIFY COLUMN `programme_title`  varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `programme_type_cd`,
MODIFY COLUMN `country_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `programme_title`,
MODIFY COLUMN `date_of_award`  date NULL AFTER `country_id`,
ADD COLUMN `province_id`  varchar(32) NULL AFTER `country_id`,
ADD COLUMN `city_id`  varchar(32) NULL AFTER `province_id`,
ADD COLUMN `institution_id`  varchar(32) NULL AFTER `city_id`,
ADD COLUMN `study_country_others`  varchar(200) NULL AFTER `institution_id`,
ADD COLUMN `institution_others`  varchar(250) NULL AFTER `study_country_others`,
ADD COLUMN `prog_type_others`  varchar(180) NULL AFTER `institution_others`,
ADD COLUMN `study_from`  date NULL AFTER `prog_type_others`,
ADD COLUMN `study_to`  date NULL AFTER `study_from`,
ADD COLUMN `not_gpa`  bit(1) NULL AFTER `date_of_award`,
ADD COLUMN `final_rslt`  varchar(15) NULL AFTER `not_gpa`;

ALTER TABLE `app_prev_studies` ADD CONSTRAINT `prevStudies_province` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_prev_studies` ADD CONSTRAINT `prevStudies_city` FOREIGN KEY (`city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_prev_studies` ADD CONSTRAINT `prevStudies_institution` FOREIGN KEY (`institution_id`) REFERENCES `app_institution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;


