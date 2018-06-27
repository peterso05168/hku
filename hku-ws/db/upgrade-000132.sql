# ALTER TABLE applicant_anncmnt

ALTER TABLE `applicant_anncmnt`
ADD COLUMN `application_id`  varchar(32) NULL AFTER `is_read`;

ALTER TABLE `applicant_anncmnt` ADD CONSTRAINT `anncmnt_application_key` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;