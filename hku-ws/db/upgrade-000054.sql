# ALTER TABLE `cpgn`、`cpgn_centre`、`cpgn_crit_china`、`cpgn_crit_china_dtl`、`cpgn_crit_nj`、`cpgn_crit_nj_to_prog`、`cpgn_crit_nj_to_tag`、`cpgn_mapping`、`cpgn_session`、`cpgn_session_to_application`

ALTER TABLE `cpgn`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn`
MODIFY COLUMN `cpgn_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `cpgn_name`  varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_id`,
MODIFY COLUMN `cpgn_cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_name`,
MODIFY COLUMN `cpgn_dtl`  varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_cd`,
MODIFY COLUMN `status_cd`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_dtl`,
MODIFY COLUMN `mapped_by`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `status_cd`;

ALTER TABLE `cpgn_centre`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_centre`
MODIFY COLUMN `cpgn_centre_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_centre_id`,
MODIFY COLUMN `city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `province_id`,
MODIFY COLUMN `centre_name`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `city_id`,
MODIFY COLUMN `cpgn_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `centre_name`,
MODIFY COLUMN `centre_addr`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_id`;

ALTER TABLE `cpgn_centre` ADD CONSTRAINT `cpgn_centre_province_key` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_centre` ADD CONSTRAINT `cpgn_centre_city_key` FOREIGN KEY (`city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_centre` ADD CONSTRAINT `cpgn_centre_cpgn_key` FOREIGN KEY (`cpgn_id`) REFERENCES `cpgn` (`cpgn_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_china`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_crit_china`
MODIFY COLUMN `cpgn_crit_china_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `name`  varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_crit_china_id`,
MODIFY COLUMN `cpgn_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `selected_for_interview`;

ALTER TABLE `cpgn_crit_china` ADD CONSTRAINT `cpgn_crit_china_cpgn_key` FOREIGN KEY (`cpgn_id`) REFERENCES `cpgn` (`cpgn_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_china_dtl`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_crit_china_dtl`
MODIFY COLUMN `cpgn_crit_china_dtl_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_crit_china_dtl_id`,
MODIFY COLUMN `exam_type_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `province_id`,
MODIFY COLUMN `stream`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `exam_type_id`,
MODIFY COLUMN `cpgn_china_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cutoff_english`;

ALTER TABLE `cpgn_crit_china_dtl` ADD CONSTRAINT `cpgn_crit_china_dtl_province_key` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_china_dtl` ADD CONSTRAINT `cpgn_crit_china_dtl_exam_type_key` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_china_dtl` ADD CONSTRAINT `cpgn_crit_china_dtl_cpgn_crit_china_key` FOREIGN KEY (`cpgn_china_id`) REFERENCES `cpgn_crit_china` (`cpgn_crit_china_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_crit_nj`
MODIFY COLUMN `cpgn_crit_nj_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `country_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `is_not_studying`,
MODIFY COLUMN `province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `country_id`,
MODIFY COLUMN `city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `province_id`,
MODIFY COLUMN `institution_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `city_id`,
MODIFY COLUMN `cpgn_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `institution_id`;

ALTER TABLE `cpgn_crit_nj` ADD CONSTRAINT `cpgn_crit_nj_country_key` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj` ADD CONSTRAINT `cpgn_crit_nj_province_key` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj` ADD CONSTRAINT `cpgn_crit_nj_city_key` FOREIGN KEY (`city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj` ADD CONSTRAINT `cpgn_crit_nj_institution_key` FOREIGN KEY (`institution_id`) REFERENCES `app_institution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj` ADD CONSTRAINT `cpgn_crit_nj_cpgn_key` FOREIGN KEY (`cpgn_id`) REFERENCES `cpgn` (`cpgn_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj_to_prog`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_crit_nj_to_prog`
MODIFY COLUMN `cpgn_crit_nj_to_prog_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `cpgn_crit_nj_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_crit_nj_to_prog_id`,
MODIFY COLUMN `adm_form_prog_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_crit_nj_id`;

ALTER TABLE `cpgn_crit_nj_to_prog` ADD CONSTRAINT `cpgn_crit_nj_to_prog_cpgn_crit_nj_key` FOREIGN KEY (`cpgn_crit_nj_id`) REFERENCES `cpgn_crit_nj` (`cpgn_crit_nj_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj_to_prog` ADD CONSTRAINT `cpgn_crit_nj_to_prog_adm_form_prog_key` FOREIGN KEY (`adm_form_prog_id`) REFERENCES `adm_form_prog` (`adm_form_prog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_crit_nj_to_tag`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_crit_nj_to_tag`
MODIFY COLUMN `cpgn_crit_nj_to_tag_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `cpgn_crit_nj_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_crit_nj_to_tag_id`,
MODIFY COLUMN `tag_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_crit_nj_id`;

ALTER TABLE `cpgn_crit_nj_to_tag` ADD CONSTRAINT `cpgn_crit_nj_to_tag_cpgn_crit_nj_key` FOREIGN KEY (`cpgn_crit_nj_id`) REFERENCES `cpgn_crit_nj` (`cpgn_crit_nj_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_mapping`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_mapping`
MODIFY COLUMN `cpgn_mapping_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `study_province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_mapping_id`,
MODIFY COLUMN `study_city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `study_province_id`,
MODIFY COLUMN `interview_province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `study_city_id`,
MODIFY COLUMN `interview_city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `interview_province_id`;

ALTER TABLE `cpgn_mapping` ADD CONSTRAINT `cpgn_mapping_study_province_key` FOREIGN KEY (`study_province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_mapping` ADD CONSTRAINT `cpgn_mapping_study_city_key` FOREIGN KEY (`study_city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_mapping` ADD CONSTRAINT `cpgn_mapping_interview_province_key` FOREIGN KEY (`interview_province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_mapping` ADD CONSTRAINT `cpgn_mapping_interview_city_key` FOREIGN KEY (`interview_city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_session`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_session`
MODIFY COLUMN `cpgn_session_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `cpgn_centre_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `reserved_quota`,
MODIFY COLUMN `day_name`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `cpgn_centre_id`;

ALTER TABLE `cpgn_session` ADD CONSTRAINT `cpgn_session_cpgn_centre_key` FOREIGN KEY (`cpgn_centre_id`) REFERENCES `cpgn_centre` (`cpgn_centre_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_session_to_application`
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cpgn_session_to_application`
MODIFY COLUMN `cpgn_session_applicant_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `cpgn_session_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_session_applicant_id`,
MODIFY COLUMN `applicant_application_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cpgn_session_id`;

ALTER TABLE `cpgn_session_to_application` ADD CONSTRAINT `cpgn_session_to_application_cpgn_session_key` FOREIGN KEY (`cpgn_session_id`) REFERENCES `cpgn_session` (`cpgn_session_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpgn_session_to_application` ADD CONSTRAINT `cpgn_session_to_application_applicant_application_key` FOREIGN KEY (`applicant_application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;