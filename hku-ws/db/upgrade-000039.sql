# ALTER TABLE `scholar_dtl`、scholar_dtl_requirement、adm_scoring_formula、adm_gps_scoring_subject、adm_gpa_formula_itm

ALTER TABLE `scholar_dtl` ADD CONSTRAINT `scholar_dtl_scholar_key` FOREIGN KEY (`scholar_id`) REFERENCES `scholar` (`scholar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `scholar_dtl_requirement` ADD CONSTRAINT `schoar_dtl_requirement_scholar_dtl_key` FOREIGN KEY (`scholar_dtl_id`) REFERENCES `scholar_dtl` (`scholar_dtl_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_scoring_formula` ADD CONSTRAINT `adm_scoring_formula_form_prog_key` FOREIGN KEY (`adm_form_prog_id`) REFERENCES `adm_form_prog` (`adm_form_prog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_scoring_formula` ADD CONSTRAINT `adm_scoring_formula_exam_type_key` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_gps_scoring_subject` ADD CONSTRAINT `adm_gps_scoring_subject_formula_key` FOREIGN KEY (`adm_scoring_formula_id`) REFERENCES `adm_scoring_formula` (`adm_scoring_formula_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_gps_scoring_subject` ADD CONSTRAINT `adm_gps_scoring_subject_subject_key` FOREIGN KEY (`exam_subject_id`) REFERENCES `exam_subject` (`exam_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_gpa_formula_itm` ADD CONSTRAINT `adm_gpa_formula_itm_scoring_formula_key` FOREIGN KEY (`adm_scoring_formula_id`) REFERENCES `adm_scoring_formula` (`adm_scoring_formula_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;