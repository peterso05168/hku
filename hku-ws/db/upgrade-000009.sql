# ALTER TABLE `applicant_application`

ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_supportingDoc`;

ALTER TABLE `applicant_application`
DROP COLUMN `supporting_doc_id`;