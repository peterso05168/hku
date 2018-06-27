# ALTER TABLE `exam_subject`

ALTER TABLE `exam_subject` 
ADD COLUMN `exam_level` VARCHAR(30) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NOT NULL AFTER `exam_type_id`;
