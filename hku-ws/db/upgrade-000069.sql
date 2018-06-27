# ALTER TABLE `applicant_application`

ALTER TABLE `applicant_application`
MODIFY COLUMN `status`  varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `application_no`;