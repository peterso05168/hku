# CREATE TABLE `app_progress`

CREATE TABLE `app_progress` (
`app_progress_id`  varchar(32) NOT NULL ,
`application_id`  varchar(32) NOT NULL ,
`prsnal_part`  bit(1) NOT NULL DEFAULT 0 ,
`acad_bg`  bit(1) NOT NULL DEFAULT 0 ,
`other_quali`  bit(1) NOT NULL DEFAULT 0 ,
`choice_of_curri`  bit(1) NOT NULL DEFAULT 0 ,
`exp_and_achi`  bit(1) NOT NULL DEFAULT 0 ,
`reference`  bit(1) NOT NULL DEFAULT 0 ,
`others`  bit(1) NOT NULL DEFAULT 0 ,
`create_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`create_date`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`update_by`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`update_date`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`version`  int(11) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`app_progress_id`),
CONSTRAINT `progress_application_key` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;