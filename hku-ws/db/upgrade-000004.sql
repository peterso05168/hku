# CREATE TABLE `applicant_anncmnt`

CREATE TABLE `applicant_anncmnt` (
`id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`type_cd`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`status_cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`issue_date`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`applicant_account_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `anncmnt_account` FOREIGN KEY (`applicant_account_id`) REFERENCES `applicant_account` (`applicant_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;