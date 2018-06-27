# ALTER TABLE `applicant_application`、 DROP TABLE best_exam_subjects and CREATE TABLE `app_best_exam_subj` and `app_best_exam_subj_rslt`

ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_bestExamSubjects_best3Gce`;

ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_bestExamSubjects_best4Gce`;

ALTER TABLE `applicant_application`
DROP COLUMN `best_three_gce`,
DROP COLUMN `best_four_gce`;

DROP TABLE best_exam_subjects;

CREATE TABLE `app_best_exam_subj` (
`app_best_exam_subj_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`exam_type_id`  varchar(32) NOT NULL ,
`application_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`app_best_exam_subj_id`),
CONSTRAINT `app_best_exam_subj_exam_type_key` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `app_best_exam_subj_application_key` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `app_best_exam_subj_rslt` (
`app_best_exam_subj_rslt_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`app_best_exam_subj_id`  varchar(32) NOT NULL ,
`app_qualification_rslt_id`  varchar(32) NOT NULL ,
`seq_no`  int(2) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`app_best_exam_subj_rslt_id`),
CONSTRAINT `app_best_exam_subj_rslt_app_qualification_rslt_key` FOREIGN KEY (`app_qualification_rslt_id`) REFERENCES `app_qualification_rslt` (`app_qualification_rslt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;