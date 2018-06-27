# ALTER TABLE `app_others`

ALTER TABLE `app_others`
ADD COLUMN `discon_uni_no`  varchar(30) NULL AFTER `discon_year`,
ADD COLUMN `not_success_app_submit`  bit(1) NULL DEFAULT 0 AFTER `discon_uni_no`,
ADD COLUMN `not_success_app_year`  int(4) NULL AFTER `not_success_app_submit`,
ADD COLUMN `not_success_app_uni_no`  varchar(30) NULL AFTER `not_success_app_year`;