# ALTER TABLE `audit_log`

ALTER TABLE `audit_log`
ADD COLUMN `status_cd`  varchar(50) NOT NULL AFTER `type_cd`;