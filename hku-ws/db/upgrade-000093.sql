# ALTER TABLE `app_qualification_rslt`、`exam_subject`、`exam_grade`

ALTER TABLE `exam_subject`
MODIFY COLUMN `exam_level`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `exam_type_id`;

ALTER TABLE `exam_grade`
MODIFY COLUMN `exam_level`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `comparable_value`;

ALTER TABLE `app_qualification_rslt`
MODIFY COLUMN `exam_level`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `exam_subject_id`;


