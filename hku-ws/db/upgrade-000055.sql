# ALTER TABLE `app_req_doc`

ALTER TABLE `app_req_doc`
DROP COLUMN `req_doc_conf_type`,
DROP COLUMN `req_doc_conf_cd`,
ADD COLUMN `req_doc_conf_id`  varchar(32) NULL AFTER `remark`;

ALTER TABLE `app_req_doc` ADD CONSTRAINT `reqDoc_reqDocConf` FOREIGN KEY (`req_doc_conf_id`) REFERENCES `app_req_doc_conf` (`app_req_doc_conf_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;