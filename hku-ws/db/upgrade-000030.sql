# ALTER TABLE `app_acad_bg`

ALTER TABLE `app_acad_bg`
MODIFY COLUMN `primary_edu_yrs`  int(10) NULL AFTER `max_gpa`,
MODIFY COLUMN `secondary_edu_yrs`  int(10) NULL AFTER `primary_edu_yrs`,
MODIFY COLUMN `post_edu_yrs`  int(10) NULL AFTER `secondary_edu_yrs`;