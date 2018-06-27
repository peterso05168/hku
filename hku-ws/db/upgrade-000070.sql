# ALTER TABLE `cpgn_crit_nj`、cpgn_crit_china   DROP TABLE cpgn_crit_nj_to_tag  CREATE TABLE `tag`

ALTER TABLE `cpgn_crit_nj`
ADD COLUMN `name`  varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `cpgn_crit_nj_id`;

ALTER TABLE `cpgn_crit_china`
CHANGE COLUMN `selected_for_interview` `selected_for_shortlist`  bit(1) NULL DEFAULT b'0' AFTER `name`;

ALTER TABLE `cpgn_crit_nj`
CHANGE COLUMN `selected_for_interview` `selected_for_shortlist`  bit(1) NOT NULL DEFAULT b'0' AFTER `cpgn_id`;

DROP TABLE cpgn_crit_nj_to_tag;

CREATE TABLE `tag` (
`tag_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`desc`  varchar(50) NOT NULL ,
PRIMARY KEY (`tag_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;