# ALTER TABLE `app_best_exam_subj_rslt` ADD COLUMN `exam_grade_cd`

ALTER TABLE `app_best_exam_subj_rslt`
ADD COLUMN `exam_grade_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `exam_type_year`;