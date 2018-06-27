# CREATE TABLE `best_exam_subjects` AND ALTER TABLE `applicant_application` ADD COLUMN `best_three_gce` AND `best_four_gce` AS FOREIGN KEY

CREATE TABLE `best_exam_subjects` (
`best_exam_subjects_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`exam_subject_1_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL ,
`exam_subject_2_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL ,
`exam_subject_3_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL ,
`exam_subject_4_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL ,
PRIMARY KEY (`best_exam_subjects_id`),
CONSTRAINT `bestExamSubject_examSubject_1` FOREIGN KEY (`exam_subject_1_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `bestExamSubject_examSubject_2` FOREIGN KEY (`exam_subject_2_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `bestExamSubject_examSubject_3` FOREIGN KEY (`exam_subject_3_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `bestExamSubject_examSubject_4` FOREIGN KEY (`exam_subject_4_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

ALTER TABLE `applicant_application`
ADD COLUMN `best_three_gce`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `ccai_interview`,
ADD COLUMN `best_four_gce`  varchar(32) NULL AFTER `best_three_gce`;

ALTER TABLE `applicant_application` ADD CONSTRAINT `application_bestExamSubjects_best3Gce` FOREIGN KEY (`best_three_gce`) REFERENCES `best_exam_subjects` (`best_exam_subjects_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `applicant_application` ADD CONSTRAINT `application_bestExamSubjects_best4Gce` FOREIGN KEY (`best_four_gce`) REFERENCES `best_exam_subjects` (`best_exam_subjects_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;