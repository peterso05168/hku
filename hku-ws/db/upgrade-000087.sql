# create TABLE `applicant_to_tag`

CREATE TABLE `applicant_to_tag` (
`applicant_to_tag_id`  varchar(32) NOT NULL ,
`applicant_info_id`  varchar(32) NOT NULL ,
`tag_id`  varchar(32) NOT NULL ,
PRIMARY KEY (`applicant_to_tag_id`),
CONSTRAINT `applicant_to_tag_applicant_info_key` FOREIGN KEY (`applicant_info_id`) REFERENCES `applicant_info` (`applicant_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `applicant_to_tag_tag_key` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;