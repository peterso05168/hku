# ALTER TABLE `general_ref_cd` ADD COLUMN `version`

ALTER TABLE `general_ref_cd`
ADD COLUMN `version`  int(11) NOT NULL AFTER `expiration_date`;