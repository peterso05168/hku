# ALTER TABLE `app_reference`, `app_referee` 

ALTER TABLE `app_reference` 
DROP FOREIGN KEY `reference_counselor`;

ALTER TABLE `app_reference` 
DROP COLUMN `counselor_id`,
DROP INDEX `reference_counselor_idx` ;

ALTER TABLE `app_referee` 
ADD COLUMN `counselor_id` VARCHAR(32) CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci NULL AFTER `is_deleted`,
ADD INDEX `referee_counselor_idx` (`counselor_id` ASC);

ALTER TABLE `app_referee` 
ADD CONSTRAINT `referee_counselor`
  FOREIGN KEY (`counselor_id`)
  REFERENCES `app_counselor` (`counselor_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

