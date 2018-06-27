# ALTER TABLE `app_programme_choice`

ALTER TABLE `app_programme_choice` DROP FOREIGN KEY `programmeChoice_hkuProgramme`;

ALTER TABLE `app_programme_choice`
DROP COLUMN `hku_programme_id`,
ADD COLUMN `adm_form_prog_id`  varchar(32) NULL AFTER `is_withdrawn_approved`;

ALTER TABLE `app_programme_choice` ADD CONSTRAINT `app_programme_choice_adm_form_prog_key` FOREIGN KEY (`adm_form_prog_id`) REFERENCES `adm_form_prog` (`adm_form_prog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;