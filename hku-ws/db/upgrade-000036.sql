# ALTER TABLE `staff_role_rel`

ALTER TABLE `staff_role_rel` ADD CONSTRAINT `staff_role_rel_info_key` FOREIGN KEY (`staff_info_id`) REFERENCES `staff_info` (`staff_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `staff_role_rel` ADD CONSTRAINT `staff_role_id_key` FOREIGN KEY (`staff_role_id`) REFERENCES `staff_role` (`staff_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;