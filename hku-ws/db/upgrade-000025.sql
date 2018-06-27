# ALTER TABLE `adm_form`

ALTER TABLE `adm_form`
ADD COLUMN `desc`  varchar(80) NULL AFTER `adm_exe_id`,
ADD COLUMN `desc_chi`  varchar(80) NULL AFTER `desc`;