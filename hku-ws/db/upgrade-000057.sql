# ALTER TABLE `app_req_doc`

ALTER TABLE `app_req_doc`
MODIFY COLUMN `status_cd`  varchar(50) NULL DEFAULT NULL AFTER `application_id`;