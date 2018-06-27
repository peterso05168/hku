# ALTER TABLE `adm_scoring_formula` ADD COLUMN `exam_board` AND `exam_level`

ALTER TABLE `adm_scoring_formula`
ADD COLUMN `exam_board`  varchar(50) NULL AFTER `including`,
ADD COLUMN `exam_level`  varchar(60) NOT NULL AFTER `exam_board`;