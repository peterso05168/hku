# ALTER TABLE `app_requirement_relationship`

ALTER TABLE `app_requirement_relationship` DROP FOREIGN KEY `requirementRelationShip_ParentRequirement`;

ALTER TABLE `app_requirement_relationship`
ADD COLUMN `app_requirement_relationship_id`  varchar(32) NOT NULL FIRST ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`app_requirement_relationship_id`);

ALTER TABLE `app_requirement_relationship` ADD CONSTRAINT `requirementRelationShip_ParentRequirement` FOREIGN KEY (`parent_requirement_id`) REFERENCES `app_requirement` (`app_requirement_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
