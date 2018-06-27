# ALTER TABLE app_programme_choice

ALTER TABLE `app_programme_choice`
ADD COLUMN `offer_type`  varchar(20) NULL AFTER `uni_comp_req`,
ADD COLUMN `condional_type`  varchar(50) NULL AFTER `offer_type`;

