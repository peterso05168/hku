# ALTER TABLE `exam_subject`、`exam_grade`

ALTER TABLE `exam_subject`
ADD COLUMN `exam_board`  varchar(50) NULL AFTER `exam_level`;

ALTER TABLE `exam_grade`
ADD COLUMN `exam_level`  varchar(30) NULL AFTER `comparable_value`;
