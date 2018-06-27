# ALTER TABLE `app_acad_qual_others`、`app_acad_qual_housing_mgmt`、`app_acad_qual_housing_mgmt_pq`、`app_acad_qual_housing_mgmt_rwe`、`app_acad_qual_nursing`、`app_acad_qual_nursing_nr`、`app_acad_qual_nursing_prq`、`app_acad_qual_nursing_exp`

ALTER TABLE `app_acad_qual_others` RENAME TO `app_acad_qual_housing_mgmt_others`;

ALTER TABLE `app_acad_qual_housing_mgmt`
ADD COLUMN `position_held`  varchar(50) NULL AFTER `hm_award_date`,
ADD COLUMN `starting_date`  date NULL AFTER `position_held`,
ADD COLUMN `name_and_addr`  varchar(400) NULL AFTER `starting_date`;

CREATE TABLE `app_acad_qual_housing_mgmt_pq` (
`acad_qual_housing_mgmt_pq_id`  varchar(32) NOT NULL ,
`type_of_mem`  varchar(120) NULL ,
`abbre`  varchar(20) NULL ,
`award_institution`  varchar(150) NULL ,
`date_of_award`  date NULL ,
`app_acad_qual_housing_mgmt_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`acad_qual_housing_mgmt_pq_id`),
CONSTRAINT `acad_qual_housing_mgmt_pq_key` FOREIGN KEY (`app_acad_qual_housing_mgmt_id`) REFERENCES `app_acad_qual_housing_mgmt` (`acad_qual_housing_mgmt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `app_acad_qual_housing_mgmt_rwe` (
`app_acad_qual_housing_mgmt_rwe_id`  varchar(32) NOT NULL ,
`appointment`  varchar(60) NULL ,
`date_from`  date NULL ,
`date_to`  date NULL ,
`name_of_organization`  varchar(150) NULL ,
`nature_of_duties`  varchar(200) NULL ,
`app_acad_qual_housing_mgmt_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`app_acad_qual_housing_mgmt_rwe_id`),
CONSTRAINT `acad_qual_housing_mgmt_rwe_key` FOREIGN KEY (`app_acad_qual_housing_mgmt_id`) REFERENCES `app_acad_qual_housing_mgmt` (`acad_qual_housing_mgmt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `app_acad_qual_nursing`
ADD COLUMN `spec_responsibilty`  varchar(120) NULL AFTER `acad_qual_nursing_id`;

CREATE TABLE `app_acad_qual_nursing_nr` (
`app_acad_qual_nursing_nr_id`  varchar(32) NOT NULL ,
`nursing_school`  varchar(150) NULL ,
`date_from`  date NULL ,
`date_to`  date NULL ,
`gen_or_psy`  bit(1) NULL DEFAULT 0 ,
`date_of_reg_and_ia`  date NULL ,
`reg_status`  varchar(20) NULL ,
`app_acad_qual_nursing_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`app_acad_qual_nursing_nr_id`),
CONSTRAINT `app_acad_qual_nursing_nr_key` FOREIGN KEY (`app_acad_qual_nursing_id`) REFERENCES `app_acad_qual_nursing` (`acad_qual_nursing_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `app_acad_qual_nursing_prq` (
`app_acad_qual_nursing_prq_id`  varchar(32) NOT NULL ,
`type_of_mem`  varchar(120) NULL ,
`date_from`  date NULL ,
`date_to`  date NULL ,
`abbre`  varchar(20) NULL ,
`date_of_award`  date NULL ,
`award_institution`  varchar(150) NULL ,
`app_acad_qual_nursing_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`app_acad_qual_nursing_prq_id`),
CONSTRAINT `app_acad_qual_nursing_prq_key` FOREIGN KEY (`app_acad_qual_nursing_id`) REFERENCES `app_acad_qual_nursing` (`acad_qual_nursing_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `app_acad_qual_nursing_exp` (
`app_acad_qual_nursing_exp_id`  varchar(32) NOT NULL ,
`date_from`  date NULL ,
`date_to`  date NULL ,
`mode`  varchar(2) NULL ,
`name_of_institute`  varchar(150) NULL ,
`position_held`  varchar(100) NULL ,
`aow_nod`  varchar(150) NULL ,
`exp_type`  varchar(1) NULL ,
`app_acad_qual_nursing_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`app_acad_qual_nursing_exp_id`),
CONSTRAINT `app_acad_qual_nursing_exp_key` FOREIGN KEY (`app_acad_qual_nursing_id`) REFERENCES `app_acad_qual_nursing` (`acad_qual_nursing_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


