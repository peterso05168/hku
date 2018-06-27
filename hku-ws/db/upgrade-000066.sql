# ALTER TABLE `applicant_anncmnt` AND CREATE TABLE `adm_anncmnt`

ALTER TABLE `applicant_anncmnt` ADD COLUMN `is_read`  bit(1) NOT NULL AFTER `applicant_account_id`;

CREATE TABLE `adm_anncmnt` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`type_cd`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`subject`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`content`  varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_date`  datetime NULL DEFAULT NULL ,
`modify_date`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
);