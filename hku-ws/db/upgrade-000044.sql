# ALTER TABLE `app_acad_bg`

ALTER TABLE `app_acad_bg`
MODIFY COLUMN `programme_type_cd`  varchar(20) NULL AFTER `institution_id`,
MODIFY COLUMN `programme_title`  varchar(300) NULL AFTER `programme_type_cd`,
MODIFY COLUMN `date_of_admission_to_prog`  date NULL AFTER `programme_title`,
MODIFY COLUMN `current_yr_of_study`  varchar(20) NULL AFTER `date_of_admission_to_prog`,
MODIFY COLUMN `expected_date_of_grad`  date NULL AFTER `current_yr_of_study`,
MODIFY COLUMN `latest_cumulative_gpa`  varchar(20) NULL AFTER `expected_date_of_grad`,
MODIFY COLUMN `max_gpa`  varchar(20) NULL AFTER `latest_cumulative_gpa`,
MODIFY COLUMN `highest_qualification_cd`  varchar(10) NULL AFTER `post_edu_yrs`,
MODIFY COLUMN `is_not_studing`  bit(1) NULL AFTER `study_mode_cd`,
MODIFY COLUMN `is_partcp_next_njcee`  bit(1) NULL AFTER `is_not_studing`;