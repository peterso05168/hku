# ALTER TABLE `applicant_application`

ALTER TABLE `applicant_application`
ADD COLUMN `acad_qual_nursing_id`  varchar(32) NULL AFTER `acad_qual_housing_mgmt_id`;

ALTER TABLE `applicant_application` ADD CONSTRAINT `application_AcadQualNursing` FOREIGN KEY (`acad_qual_nursing_id`) REFERENCES `app_acad_qual_nursing` (`acad_qual_nursing_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

