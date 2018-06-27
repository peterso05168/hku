# ALTER TABLE `applicant_anncmnt` MODIFY COLUMN `file_name` AND `file_path`

ALTER TABLE `app_uploaded_doc`
MODIFY COLUMN `file_name`  varchar(200) NOT NULL AFTER `req_doc_id`,
MODIFY COLUMN `file_path`  varchar(500) NOT NULL AFTER `file_name`;