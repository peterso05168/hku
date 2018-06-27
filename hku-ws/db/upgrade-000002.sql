# creare FOREIGN KEY

ALTER TABLE `app_special_scheme`
MODIFY COLUMN `special_scheme_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `special_scheme_cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `special_scheme_id`,
MODIFY COLUMN `application_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `special_scheme_cd`,
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `applicant_info`
MODIFY COLUMN `applicant_info_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
MODIFY COLUMN `alternate_email`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `applicant_info_id`,
MODIFY COLUMN `surname`  varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `alternate_email`,
MODIFY COLUMN `given_name`  varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `surname`,
MODIFY COLUMN `nationality_country_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `given_name`,
MODIFY COLUMN `nationality_province_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `nationality_country_id`,
MODIFY COLUMN `nationality_city_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `nationality_province_id`,
MODIFY COLUMN `study_country_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `nationality_city_id`,
MODIFY COLUMN `institution_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `mobile_tel_no`,
MODIFY COLUMN `institution_others`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `institution_id`,
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `adm_form` ADD CONSTRAINT `scheme_admissionExercise` FOREIGN KEY (`adm_exe_id`) REFERENCES `adm_exe` (`adm_exe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_form_prog` ADD CONSTRAINT `formProgramme_form` FOREIGN KEY (`adm_form_id`) REFERENCES `adm_form` (`adm_form_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `adm_form_prog` ADD CONSTRAINT `formProgramme_hkuProgramme` FOREIGN KEY (`app_hku_programme_id`) REFERENCES `app_hku_programme` (`hku_programme_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `adm_form_prog_req` ADD CONSTRAINT `formProgReq_requirement` FOREIGN KEY (`app_requirement_id`) REFERENCES `app_requirement` (`app_requirement_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `adm_form_prog_req` ADD CONSTRAINT `formProgRew_formProg` FOREIGN KEY (`adm_form_prog_id`) REFERENCES `adm_form_prog` (`adm_form_prog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_acad_bg` ADD CONSTRAINT `acadBg_city` FOREIGN KEY (`city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_acad_bg` ADD CONSTRAINT `acadBg_country` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_acad_bg` ADD CONSTRAINT `acadBg_institution` FOREIGN KEY (`institution_id`) REFERENCES `app_institution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_acad_bg` ADD CONSTRAINT `acadBg_province` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_exp_and_achievements` ADD CONSTRAINT `expAndAchievements_applcation` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_institution` ADD CONSTRAINT `institution_city` FOREIGN KEY (`city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_institution` ADD CONSTRAINT `institution_country` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_institution` ADD CONSTRAINT `institution_province` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personFax_telNo` FOREIGN KEY (`fax_no_id`) REFERENCES `app_tel_no` (`tel_no_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personHome_telNo` FOREIGN KEY (`home_tel_no_id`) REFERENCES `app_tel_no` (`tel_no_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personMobile_telNo` FOREIGN KEY (`mobile_tel_no_id`) REFERENCES `app_tel_no` (`tel_no_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personNationality_city` FOREIGN KEY (`nationality_city_id`) REFERENCES `cpc_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personNationality_country` FOREIGN KEY (`nationality_country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personNationality_province` FOREIGN KEY (`nationality_province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personResidence_country` FOREIGN KEY (`residence_country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `personStudy_country` FOREIGN KEY (`study_country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_personal_particulars` ADD CONSTRAINT `person_institution` FOREIGN KEY (`institution_id`) REFERENCES `app_institution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_prev_studies` ADD CONSTRAINT `prevStudies_application` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_prev_studies` ADD CONSTRAINT `prevStudies_country` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_prev_studies` ADD CONSTRAINT `prevStudies_institution` FOREIGN KEY (`institution_id`) REFERENCES `app_institution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_prog_requirement` ADD CONSTRAINT `progRequirement_hkuProgramme` FOREIGN KEY (`app_hku_programme_id`) REFERENCES `app_hku_programme` (`hku_programme_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_prog_requirement` ADD CONSTRAINT `progRequirement_requirement` FOREIGN KEY (`app_requirement_id`) REFERENCES `app_requirement` (`app_requirement_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_programme_choice` ADD CONSTRAINT `programmeChoice_application` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_programme_choice` ADD CONSTRAINT `programmeChoice_hkuProgramme` FOREIGN KEY (`hku_programme_id`) REFERENCES `app_hku_programme` (`hku_programme_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_qualification` ADD CONSTRAINT `qualification_application` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_qualification` ADD CONSTRAINT `qualification_examType` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_qualification_rslt` ADD CONSTRAINT `otherQualiRslt_otherQuali` FOREIGN KEY (`app_qualification_id`) REFERENCES `app_qualification` (`app_qualification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_referee` ADD CONSTRAINT `referee_reference` FOREIGN KEY (`reference_id`) REFERENCES `app_reference` (`reference_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_reference` ADD CONSTRAINT `reference_counselor` FOREIGN KEY (`counselor_id`) REFERENCES `app_counselor` (`counselor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_req_doc` ADD CONSTRAINT `reqDoc_application` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_requirement_relationship` ADD CONSTRAINT `requirementRelationShip_ChildRequirement` FOREIGN KEY (`child_requirement_id`) REFERENCES `app_requirement` (`app_requirement_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `app_requirement_relationship` ADD CONSTRAINT `requirementRelationShip_ParentRequirement` FOREIGN KEY (`parent_requirement_id`) REFERENCES `app_requirement` (`app_requirement_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_special_scheme` ADD CONSTRAINT `specialScheme_application` FOREIGN KEY (`application_id`) REFERENCES `applicant_application` (`application_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_uploaded_doc` ADD CONSTRAINT `uploadedDoc_reqDoc` FOREIGN KEY (`req_doc_id`) REFERENCES `app_req_doc` (`app_req_doc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `applicant_account` ADD CONSTRAINT `userAccount_userInfo` FOREIGN KEY (`user_info_id`) REFERENCES `applicant_info` (`applicant_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `applicant_application` ADD CONSTRAINT `application_acadBg` FOREIGN KEY (`acad_bg_id`) REFERENCES `app_acad_bg` (`acad_bg_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_acadQualHousingMgmt` FOREIGN KEY (`acad_qual_housing_mgmt_id`) REFERENCES `app_acad_qual_housing_mgmt` (`acad_qual_housing_mgmt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_admForm` FOREIGN KEY (`adm_form_id`) REFERENCES `adm_form` (`adm_form_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_others` FOREIGN KEY (`others_id`) REFERENCES `app_others` (`others_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_person` FOREIGN KEY (`person_id`) REFERENCES `app_personal_particulars` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_reference` FOREIGN KEY (`reference_id`) REFERENCES `app_reference` (`reference_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_supportingDoc` FOREIGN KEY (`supporting_doc_id`) REFERENCES `app_req_doc` (`app_req_doc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `applicant_application` ADD CONSTRAINT `application_userAccount` FOREIGN KEY (`applicant_account_id`) REFERENCES `applicant_account` (`applicant_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `applicant_msg` ADD CONSTRAINT `applicantMsg_applicantAccount` FOREIGN KEY (`applicant_account_id`) REFERENCES `applicant_account` (`applicant_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpc_city` ADD CONSTRAINT `city_country` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `cpc_city` ADD CONSTRAINT `city_province` FOREIGN KEY (`province_id`) REFERENCES `cpc_province` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `cpc_province` ADD CONSTRAINT `province_country` FOREIGN KEY (`country_id`) REFERENCES `cpc_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `exam_grade` ADD CONSTRAINT `examGrade_examType` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `exam_subject` ADD CONSTRAINT `examSubject_examType` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `staff_info` ADD CONSTRAINT `staffinfo_staffrole` FOREIGN KEY (`staff_role_id`) REFERENCES `staff_role` (`staff_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `staff_prog` ADD CONSTRAINT `staffProg_hkuProgramme` FOREIGN KEY (`hku_programme_id`) REFERENCES `app_hku_programme` (`hku_programme_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `staff_prog` ADD CONSTRAINT `staffProg_staffInfo` FOREIGN KEY (`staff_id`) REFERENCES `staff_info` (`staff_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `staff_role_privilege` ADD CONSTRAINT `staffRolePrivilege_staffRolePrivilege` FOREIGN KEY (`staff_privilege_id`) REFERENCES `staff_privilege` (`staff_privilege_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `staff_role_privilege` ADD CONSTRAINT `staffRolePrivilege_staffrole` FOREIGN KEY (`staff_role_id`) REFERENCES `staff_role` (`staff_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;