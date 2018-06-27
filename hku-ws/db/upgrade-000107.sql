# ALTER TABLE `adm_exe`

ALTER TABLE `adm_exe`
ADD COLUMN `adm_cycle_end_date`  date NULL AFTER `withdraw_prog_choice_end_date`,
ADD COLUMN `display_sixth_choice`  bit(1) NULL DEFAULT 0 AFTER `adm_cycle_end_date`;




