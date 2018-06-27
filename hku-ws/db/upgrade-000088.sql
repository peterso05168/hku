# ALTER TABLE `tag`

ALTER TABLE `tag`
CHANGE COLUMN `desc` `tag_desc`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `tag_id`;