# ALTER TABLE `app_acad_qual_housing_mgmt` and create app_acad_qual_others,app_trans_credits

ALTER TABLE `app_acad_qual_housing_mgmt`
ADD COLUMN `period_from`  date NOT NULL AFTER `acad_qual_housing_mgmt_id`,
ADD COLUMN `period_to`  date NOT NULL AFTER `period_from`,
ADD COLUMN `hm_award_date`  date NOT NULL AFTER `period_to`;

CREATE TABLE `app_acad_qual_others` (
`app_acad_qual_others_id`  varchar(32) NOT NULL ,
`degree_title`  varchar(50) NULL ,
`course_duration`  varchar(20) NULL ,
`major_subject`  varchar(80) NULL ,
`honours`  varchar(40) NULL ,
`award_institution`  varchar(100) NULL ,
`other_award_date`  date NULL ,
`acad_qual_housing_mgmt_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`app_acad_qual_others_id`),
CONSTRAINT `acad_qual_housing_mgmt_key` FOREIGN KEY (`acad_qual_housing_mgmt_id`) REFERENCES `app_acad_qual_housing_mgmt` (`acad_qual_housing_mgmt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `app_trans_credits` (
`app_trans_credits_id`  varchar(32) NOT NULL ,
`year_of_study`  varchar(50) NULL ,
`curriculum`  varchar(100) NULL ,
`is_apply_dir_entry`  bit(1) NOT NULL DEFAULT 0 ,
`is_apply_trans_credits`  bit(1) NOT NULL DEFAULT 0 ,
`application_id`  varchar(32) NULL ,
PRIMARY KEY (`app_trans_credits_id`),
CONSTRAINT `applicant_application_key` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;