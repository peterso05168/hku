# ALTER TABLE `app_personal_particulars`

ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personFax_telNo`;

ALTER TABLE `app_personal_particulars`
DROP COLUMN `fax_no_id`,
ADD COLUMN `not_provide_id`  bit(1) NULL AFTER `req_visa_ind`;
