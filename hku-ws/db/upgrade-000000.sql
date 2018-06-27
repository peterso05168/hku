# update cpc_country、cpc_province、cpc_city all column

ALTER TABLE `cpc_country`
CHANGE COLUMN `country_id` `id`  bigint(20) NOT NULL FIRST ,
CHANGE COLUMN `country_cd` `cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `id`,
CHANGE COLUMN `country_desc` `desc`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cd`,
CHANGE COLUMN `country_desc_chi` `desc_chi`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `desc`;

ALTER TABLE `cpc_province`
CHANGE COLUMN `province_id` `id`  bigint(20) NOT NULL FIRST ,
CHANGE COLUMN `province_cd` `cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `id`,
CHANGE COLUMN `province_desc` `desc`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cd`,
CHANGE COLUMN `province_desc_chi` `desc_chi`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `desc`;

ALTER TABLE `cpc_city`
CHANGE COLUMN `city_id` `id`  bigint(20) NOT NULL FIRST ,
CHANGE COLUMN `city_cd` `cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `id`,
CHANGE COLUMN `city_desc` `desc`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cd`,
CHANGE COLUMN `city_desc_chi` `desc_chi`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `desc`;