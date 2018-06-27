# ALTER TABLE `best_exam_subjects` ADD COLUMN `exam_type_id` AS FOREIGN KEY

ALTER TABLE `best_exam_subjects`
ADD COLUMN `exam_type_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `best_exam_subjects_id`;

ALTER TABLE `best_exam_subjects` ADD CONSTRAINT `bestExamSubject_examType` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;