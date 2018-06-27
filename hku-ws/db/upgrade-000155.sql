# ALTER TABLE `app_programme_choice`、`general_ref_cd`

UPDATE app_programme_choice ap SET ap.offer_status_cd ='SUBMITTED' WHERE ap.offer_status_cd ='SUBMMITED';
UPDATE general_ref_cd gc SET gc.cd = 'SUBMITTED' WHERE gc.type = 'CHOICESTATUS' AND gc.cd = 'SUBMMITED';

ALTER TABLE `app_programme_choice`
CHANGE COLUMN `condional_type` `conditional_type`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `offer_type`;

