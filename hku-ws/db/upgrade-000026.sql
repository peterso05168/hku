# CREATE TABLE `audit_log`

CREATE TABLE `audit_log` (
`id`  varchar(32) NOT NULL ,
`applicant_account_id`  varchar(32) NULL ,
`msg`  varchar(200) NOT NULL ,
`level`  int(1) NOT NULL ,
`type_cd`  varchar(20) NOT NULL ,
`create_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`create_date`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`update_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`update_date`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`version`  int(11) NOT NULL DEFAULT 0 ,
`is_deleted`  bit(1) NOT NULL DEFAULT b'0' ,
PRIMARY KEY (`id`),
CONSTRAINT `applicant_account_log_key` FOREIGN KEY (`applicant_account_id`) REFERENCES `applicant_account` (`applicant_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;