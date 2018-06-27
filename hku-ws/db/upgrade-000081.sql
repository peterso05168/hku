# ALTER TABLE `cpgn_centre`

ALTER TABLE `cpgn_centre`
ADD COLUMN `mapped`  bit(1) NOT NULL DEFAULT 1 AFTER `centre_addr`;