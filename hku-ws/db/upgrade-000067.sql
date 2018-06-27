# ALTER TABLE `applicant_anncmnt` ADD COLUMN `application_no`

ALTER TABLE `applicant_anncmnt`
ADD COLUMN `application_no`  varchar(10) NULL DEFAULT NULL AFTER `applicant_account_id`;