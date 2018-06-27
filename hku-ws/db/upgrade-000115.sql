# ALTER TABLE `applicant_anncmnt` ADD COLUMN `msg_content`

ALTER TABLE `applicant_anncmnt`
ADD COLUMN `msg_content`  varchar(1000) NULL AFTER `value`;