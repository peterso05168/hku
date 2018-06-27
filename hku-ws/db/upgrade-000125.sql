# ALTER TABLE `app_programme_choice`

ALTER TABLE `app_programme_choice`
ADD COLUMN `assigned_quota_local`  int(4) NULL AFTER `sis_number`,
ADD COLUMN `assigned_quota_overseas`  int(4) NULL AFTER `assigned_quota_local`,
ADD COLUMN `assigned_quota_mainland`  int(4) NULL AFTER `assigned_quota_overseas`;



