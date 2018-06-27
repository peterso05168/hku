# ALTER TABLE `cpc_city`、`app_institution`

ALTER TABLE `cpc_city`
MODIFY COLUMN `cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `id`;

ALTER TABLE `app_institution`
MODIFY COLUMN `cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `id`;