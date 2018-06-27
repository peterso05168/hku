# ALTER TABLE `app_req_doc_conf` ADD COLUMN `meet_req_bit`

ALTER TABLE `app_req_doc_conf`
MODIFY COLUMN `req_doc_cd`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `req_doc_type`;

