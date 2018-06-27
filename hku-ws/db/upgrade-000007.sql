# ALTER TABLE `applicant_account`

ALTER TABLE `applicant_account`
DROP COLUMN `password_salt`,
CHANGE COLUMN `password_hash` `password`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `person_email`;