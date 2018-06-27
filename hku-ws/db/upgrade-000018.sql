# ALTER TABLE `app_personal_particulars`

ALTER TABLE `app_personal_particulars` 
CHANGE COLUMN `hkid` `hkid` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NULL ,
CHANGE COLUMN `passport_no` `passport_no` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NULL ;
