# CREATE PROCEDURE for calculate GCE and IB score

ALTER TABLE `app_best_exam_subj`
ADD COLUMN `calculate_type`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `application_id`;

ALTER TABLE `app_best_exam_subj_rslt`
ADD COLUMN `exam_subject_desc`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `seq_no`,
ADD COLUMN `exam_type_month`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `exam_subject_desc`,
ADD COLUMN `exam_type_year`  int(4) NULL AFTER `exam_type_month`;

CREATE TABLE `app_ibd_best_exam` (
  `app_ibd_best_exam_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `application_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `app_qualification_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_rslt` int(10) NOT NULL,
  `calculate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `out_of` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`app_ibd_best_exam_id`),
  KEY `ibdBestExam_qualification` (`app_qualification_id`),
  KEY `ibdBestExam_application` (`application_id`),
  CONSTRAINT `ibdBestExam_application` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ibdBestExam_qualification` FOREIGN KEY (`app_qualification_id`) REFERENCES `app_qualification` (`app_qualification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

groovy groovy/calculate_actual_score_gce.groovy;
groovy groovy/calculate_predicted_actual_score_gce.groovy;

groovy groovy/calculate_actual_score_ibd_42.groovy;
groovy groovy/calculate_actual_score_ibd_45.groovy;
groovy groovy/calculate_predicted_actual_score_ibd_42.groovy;
groovy groovy/calculate_predicted_actual_score_ibd_45.groovy;