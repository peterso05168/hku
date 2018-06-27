# CREATE TABLE `app_mfe_scheme`

CREATE TABLE `app_mfe_scheme` (
`app_mfe_scheme_id`  varchar(32) NOT NULL ,
`app_qualifications_id`  varchar(32) NOT NULL ,
`chinese`  varchar(2) NULL ,
`maths`  varchar(2) NULL ,
`english`  varchar(2) NULL ,
`physics`  varchar(2) NULL ,
`chemistry`  varchar(2) NULL ,
`biology`  varchar(2) NULL ,
`history`  varchar(2) NULL ,
`geography`  varchar(2) NULL ,
`politics`  varchar(2) NULL ,
`technology`  varchar(2) NULL ,
`integrated_arts`  varchar(2) NULL ,
`integrated_science`  varchar(2) NULL ,
`total`  varchar(10) NULL ,
`rank`  varchar(10) NULL ,
`total_students`  varchar(10) NULL ,
`integrated_rank`  varchar(10) NULL ,
`year_semester`  int(1) NULL ,
PRIMARY KEY (`app_mfe_scheme_id`),
CONSTRAINT `mefScheme_qualification` FOREIGN KEY (`app_qualifications_id`) REFERENCES `app_qualification` (`app_qualification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
