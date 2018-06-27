# ALTER TABLE `app_req_doc` AND `app_uploaded_doc`

ALTER TABLE `app_req_doc`
DROP COLUMN `submission_date`,
DROP COLUMN `remark`;

ALTER TABLE `app_uploaded_doc`
ADD COLUMN `submission_date`  datetime NULL DEFAULT NULL AFTER `active`,
ADD COLUMN `remark`  varchar(60) NULL DEFAULT NULL AFTER `submission_date`;