# ALTER TABLE `app_qualification_rslt`

ALTER TABLE `app_qualification_rslt`
CHANGE COLUMN `exam_subject_desc` `exam_subject_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `app_qualification_id`;

ALTER TABLE `app_qualification_rslt` ADD CONSTRAINT `app_otherQualiRslt_subject_id` FOREIGN KEY (`exam_subject_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE `app_njcee_scoring_system` (
`app_njcee_scoring_system_id`  varchar(32) NOT NULL ,
`province_id`  varchar(32) NULL ,
`stream`  varchar(10) NULL ,
`total_score`  varchar(3) NULL ,
PRIMARY KEY (`app_njcee_scoring_system_id`),
CONSTRAINT `njcee_scoring_system_province` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `app_njcee_subject_structure` (
`app_njcee_subject_structure_id`  varchar(32) NOT NULL ,
`app_njcee_scoring_system_id`  varchar(32) NULL ,
`subject_id`  varchar(32) NULL ,
`subject_score`  varchar(3) NULL ,
PRIMARY KEY (`app_njcee_subject_structure_id`),
CONSTRAINT `njcee_subject_structure_subject` FOREIGN KEY (`subject_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `njcee_subject_structure_system` FOREIGN KEY (`app_njcee_scoring_system_id`) REFERENCES `app_njcee_scoring_system` (`app_njcee_scoring_system_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;