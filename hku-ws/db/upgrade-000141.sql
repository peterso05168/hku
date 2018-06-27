# ALTER TABLE `adm_scoring_formula` MODIFY COLUMN `exam_level`

ALTER TABLE `adm_scoring_formula`
MODIFY COLUMN `exam_level`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `exam_board`;