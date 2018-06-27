# ALTER TABLE `app_qualification`、`app_qualification_rslt`、`exam_grade`、`general_ref_cd`

ALTER TABLE `app_qualification`
MODIFY COLUMN `ib_achieved_rslt_grade_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `ib_session_no`,
MODIFY COLUMN `ib_predicted_rslt_grade_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `ib_achieved_rslt`;

ALTER TABLE `app_qualification_rslt`
CHANGE COLUMN `exam_subject_cd` `exam_subject_desc`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `app_qualification_id`,
CHANGE COLUMN `level_cd` `exam_level`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `exam_subject_desc`,
MODIFY COLUMN `achieved_grade_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `exam_level`,
MODIFY COLUMN `predicted_grade_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `achieved_grade_others`;

ALTER TABLE `exam_grade`
MODIFY COLUMN `grade_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `exam_type_id`;

ALTER TABLE `general_ref_cd`
MODIFY COLUMN `cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL AFTER `type`;