# ALTER TABLE `app_programme_choice`

ALTER TABLE `app_programme_choice`
ADD COLUMN `is_withdrawn`  bit(1) NOT NULL DEFAULT 0 AFTER `offer_status_cd`;