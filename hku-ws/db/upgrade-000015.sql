# ALTER TABLE `app_counselor`

ALTER TABLE `app_counselor`
DROP COLUMN `counselor_name`,
ADD COLUMN `email`  varchar(120) NULL AFTER `counselor_id`,
ADD COLUMN `surname`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `counselor_cd`,
ADD COLUMN `given_name`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `surname`;