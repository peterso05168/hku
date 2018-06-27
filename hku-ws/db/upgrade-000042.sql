# create table cpgn、cpgn_crit_nj、cpgn_centre、cpgn_crit_china、cpgn_session、cpgn_crit_china_dtl、cpgn_crit_nj_to_tag、cpgn_crit_nj_to_prog、cpgn_session_to_application、cpgn_mapping

CREATE TABLE `cpgn` (
`cpgn_id`  varchar(32) NOT NULL ,
`cpgn_name`  varchar(80) NULL ,
`cpgn_cd`  varchar(10) NULL ,
`cpgn_dtl`  varchar(300) NULL ,
`status_cd`  varchar(30) NULL ,
`mapped_by`  varchar(10) NULL ,
PRIMARY KEY (`cpgn_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_crit_nj` (
`cpgn_crit_nj_id`  varchar(32) NOT NULL ,
`is_not_studying`  bit(1) NOT NULL DEFAULT 0 ,
`country_id`  varchar(32) NULL ,
`province_id`  varchar(32) NULL ,
`city_id`  varchar(32) NULL ,
`institution_id`  varchar(32) NULL ,
`cpgn_id`  varchar(32) NOT NULL ,
`selected_for_interview`  bit(1) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`cpgn_crit_nj_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_centre` (
`cpgn_centre_id`  varchar(32) NOT NULL ,
`province_id`  varchar(32) NULL ,
`city_id`  varchar(32) NULL ,
`centre_name`  varchar(60) NULL ,
`cpgn_id`  varchar(32) NOT NULL ,
`centre_addr`  varchar(200) NULL ,
PRIMARY KEY (`cpgn_centre_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_crit_china` (
`cpgn_crit_china_id`  varchar(32) NOT NULL ,
`name`  varchar(80) NULL ,
`selected_for_interview`  bit(1) NULL DEFAULT 0 ,
`cpgn_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`cpgn_crit_china_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_session` (
`cpgn_session_id`  varchar(32) NOT NULL ,
`session_datetime`  datetime NULL ,
`assigned_quota`  int(11) NULL ,
`reserved_quota`  int(11) NULL ,
`cpgn_centre_id`  varchar(32) NOT NULL ,
`day_name`  varchar(30) NULL ,
PRIMARY KEY (`cpgn_session_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_crit_china_dtl` (
`cpgn_crit_china_dtl_id`  varchar(32) NOT NULL ,
`province_id`  varchar(32) NULL ,
`exam_type_id`  varchar(32) NULL ,
`stream`  varchar(10) NULL ,
`cutoff_total`  int(11) NULL ,
`cutoff_english`  int(11) NULL ,
`cpgn_china_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`cpgn_crit_china_dtl_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_crit_nj_to_tag` (
`cpgn_crit_nj_to_tag_id`  varchar(32) NOT NULL ,
`cpgn_crit_nj_id`  varchar(32) NOT NULL ,
`tag_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`cpgn_crit_nj_to_tag_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_crit_nj_to_prog` (
`cpgn_crit_nj_to_prog_id`  varchar(32) NOT NULL ,
`cpgn_crit_nj_id`  varchar(32) NOT NULL ,
`adm_form_prog_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`cpgn_crit_nj_to_prog_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_session_to_application` (
`cpgn_session_applicant_id`  varchar(32) NOT NULL ,
`cpgn_session_id`  varchar(32) NOT NULL ,
`applicant_application_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`cpgn_session_applicant_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cpgn_mapping` (
`cpgn_mapping_id`  varchar(32) NOT NULL ,
`study_province_id`  varchar(32) NULL ,
`study_city_id`  varchar(32) NULL ,
`interview_province_id`  varchar(32) NULL ,
`interview_city_id`  varchar(32) NULL ,
PRIMARY KEY (`cpgn_mapping_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci;