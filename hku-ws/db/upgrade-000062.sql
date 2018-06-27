# ALTER TABLE `cpgn`

ALTER TABLE `cpgn`
ADD COLUMN `country_id`  varchar(32) NULL AFTER `mapped_by`;

ALTER TABLE `cpgn` ADD CONSTRAINT `cpgn_country_key` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;