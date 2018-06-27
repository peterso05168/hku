# ALTER TABLE `applicant_info` add key

ALTER TABLE `applicant_info` ADD CONSTRAINT `home_tel_no_key` FOREIGN KEY (`home_tel_id`) REFERENCES `app_tel_no` (`tel_no_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `applicant_info` ADD CONSTRAINT `mobile_tel_no_key` FOREIGN KEY (`mobile_tel_id`) REFERENCES `app_tel_no` (`tel_no_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;