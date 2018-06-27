# ALTER TABLE `app_programme_choice` CHANGE COLUMN `meet_req_bit` `meet_req`

ALTER TABLE `app_programme_choice`
CHANGE COLUMN `meet_req_bit` `meet_req`  bit(1) NULL DEFAULT NULL AFTER `adm_form_prog_id`;