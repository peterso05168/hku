# ALTER TABLE `app_req_doc` ADD COLUMN `app_qualification_id` AS FOREIGN KEY

ALTER TABLE `app_req_doc`
ADD COLUMN `app_qualification_id`  varchar(32) NULL AFTER `req_doc_conf_id`;

ALTER TABLE `app_req_doc` ADD CONSTRAINT `reqDoc_qualification` FOREIGN KEY (`app_qualification_id`) REFERENCES `app_qualification` (`app_qualification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;