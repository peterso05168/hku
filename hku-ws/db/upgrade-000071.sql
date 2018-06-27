# CREATE TABLE `cpgn_crit_to_tag`

CREATE TABLE `cpgn_crit_to_tag` (
`cpgn_crit_to_tag_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`type`  varchar(10) NOT NULL ,
`cpgn_crit_id`  varchar(32) NOT NULL ,
`tag_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`cpgn_crit_to_tag_id`),
CONSTRAINT `cpgn_crit_to_tag_tag_key` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;