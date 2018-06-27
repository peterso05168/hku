# ALTER TABLE `app_others`

ALTER TABLE `app_others`
ADD COLUMN `disability_type_cd`  varchar(20) NULL AFTER `others_id`,
ADD COLUMN `deg_of_impairmnt`  varchar(30) NULL AFTER `disability_type_cd`,
ADD COLUMN `description`  varchar(400) NULL AFTER `deg_of_impairmnt`,
ADD COLUMN `uni_no`  varchar(30) NULL AFTER `description`,
ADD COLUMN `de_reg`  bit(1) NOT NULL DEFAULT 0 AFTER `uni_no`,
ADD COLUMN `de_reg_prog`  varchar(80) NULL AFTER `de_reg`,
ADD COLUMN `de_reg_year`  int(4) NULL AFTER `de_reg_prog`,
ADD COLUMN `discontinued`  bit(1) NOT NULL DEFAULT 0 AFTER `de_reg_year`,
ADD COLUMN `discon_prog`  varchar(80) NULL AFTER `discontinued`,
ADD COLUMN `discon_year`  int(4) NULL AFTER `discon_prog`;