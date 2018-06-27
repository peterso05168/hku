# ALTER TABLE `adm_form_prog` and create table adm_scoring_formula、adm_gps_scoring_subject、adm_gpa_formula_itm、scholar、scholar_dtl、scholar_dtl_requirement

ALTER TABLE `adm_form_prog`
ADD COLUMN `quota_local`  int(4) NULL AFTER `app_hku_programme_id`,
ADD COLUMN `quota_overseas`  int(4) NULL AFTER `quota_local`,
ADD COLUMN `level_of_entry`  int(1) NULL AFTER `quota_overseas`,
ADD COLUMN `quota_mainland`  int(4) NULL AFTER `level_of_entry`;

CREATE TABLE `adm_scoring_formula` (
`adm_scoring_formula_id`  varchar(32) NOT NULL ,
`formula_name`  varchar(80) NULL ,
`description`  varchar(120) NULL ,
`formula_type`  varchar(3) NULL ,
`adm_form_prog_id`  varchar(32) NOT NULL ,
`exam_type_id`  varchar(32) NOT NULL ,
`including`  int(11) NULL ,
PRIMARY KEY (`adm_scoring_formula_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `adm_gps_scoring_subject` (
`adm_gps_scoring_subject_id`  varchar(32) NOT NULL ,
`adm_scoring_formula_id`  varchar(32) NOT NULL ,
`exam_subject_id`  varchar(32) NOT NULL ,
`type`  varchar(2) NULL ,
PRIMARY KEY (`adm_gps_scoring_subject_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `adm_gpa_formula_itm` (
`adm_gpa_formula_itm_id`  varchar(32) NOT NULL ,
`max_cgpa`  decimal(3,2) NULL ,
`min_year_one_cgpa`  decimal(3,2) NULL ,
`min_final_year_cgpa`  decimal(3,2) NULL ,
`is_deleted`  bit(1) NOT NULL DEFAULT 0 ,
`adm_scoring_formula_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`adm_gpa_formula_itm_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `scholar` (
`scholar_id`  varchar(32) NOT NULL ,
`name`  varchar(120) NULL ,
`status`  varchar(30) NULL ,
PRIMARY KEY (`scholar_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `scholar_dtl` (
`scholar_dtl_id`  varchar(32) NOT NULL ,
`tier`  varchar(10) NULL ,
`amount`  int(10) NULL ,
`letter_content`  varchar(500) NULL ,
`scholar_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`scholar_dtl_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `scholar_dtl_requirement` (
`scholar_dtl_requirement_id`  varchar(32) NOT NULL ,
`scholar_dtl_id`  varchar(32) NOT NULL ,
`requirement_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`scholar_dtl_requirement_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;