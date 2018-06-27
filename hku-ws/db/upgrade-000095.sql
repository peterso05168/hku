# ALTER TABLE `app_programme_choice` ADD COLUMN `meet_req_bit`

ALTER TABLE `app_programme_choice`
ADD COLUMN `meet_req_bit`  bit(1) NULL AFTER `adm_form_prog_id`;