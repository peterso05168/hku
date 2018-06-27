# ALTER TABLE `app_mfe_scheme`

ALTER TABLE `app_mfe_scheme`
MODIFY COLUMN `chinese`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `app_qualifications_id`,
MODIFY COLUMN `maths`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `chinese`,
MODIFY COLUMN `english`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `maths`,
MODIFY COLUMN `physics`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `english`,
MODIFY COLUMN `chemistry`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `physics`,
MODIFY COLUMN `biology`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `chemistry`,
MODIFY COLUMN `history`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `biology`,
MODIFY COLUMN `geography`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `history`,
MODIFY COLUMN `politics`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `geography`,
MODIFY COLUMN `technology`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `politics`,
MODIFY COLUMN `integrated_arts`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `technology`,
MODIFY COLUMN `integrated_science`  varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `integrated_arts`;

