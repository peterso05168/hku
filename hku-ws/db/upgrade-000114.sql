# ALTER TABLE `applicant_application`、 app_best_exam_subj_rslt

ALTER TABLE `app_best_exam_subj_rslt` ADD CONSTRAINT `app_best_exam_subj_rslt_app_best_exam_subj_key` FOREIGN KEY (`app_best_exam_subj_id`) REFERENCES `app_best_exam_subj` (`app_best_exam_subj_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `applicant_application`
DROP COLUMN `best_three_sat`,
DROP COLUMN `best_three_ielts`,
DROP COLUMN `best_three_ib`,
DROP COLUMN `best_four_sat`,
DROP COLUMN `best_four_ielts`,
DROP COLUMN `best_four_ib`;