# ALTER TABLE `app_programme_choice`

ALTER TABLE `app_programme_choice`
ADD COLUMN `reply_deadline`  datetime NULL AFTER `other_interview`,
ADD COLUMN `replied_on`  datetime NULL AFTER `reply_deadline`;






