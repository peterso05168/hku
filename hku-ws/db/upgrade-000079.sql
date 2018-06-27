# ALTER TABLE `app_uploaded_doc` AND `app_req_doc_conf` ADD COLUMN

ALTER TABLE `app_uploaded_doc`
ADD COLUMN `display_file_name`  varchar(200) NULL AFTER `file_name`;

ALTER TABLE `app_req_doc_conf`
ADD COLUMN `tool_tip_msg`  varchar(500) NULL AFTER `req_doc_name_chi`;