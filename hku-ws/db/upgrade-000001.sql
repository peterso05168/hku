# update id to varchar 32

ALTER TABLE `adm_form` DROP FOREIGN KEY `scheme_admissionExercise`;

ALTER TABLE `adm_form_prog` DROP FOREIGN KEY `formProgramme_form`;
ALTER TABLE `adm_form_prog` DROP FOREIGN KEY `formProgramme_hkuProgramme`;
ALTER TABLE `adm_form_prog_req` DROP FOREIGN KEY `formProgReq_requirement`;
ALTER TABLE `adm_form_prog_req` DROP FOREIGN KEY `formProgRew_formProg`;

ALTER TABLE `app_acad_bg` DROP FOREIGN KEY `acadBg_city`;
ALTER TABLE `app_acad_bg` DROP FOREIGN KEY `acadBg_country`;
ALTER TABLE `app_acad_bg` DROP FOREIGN KEY `acadBg_institution`;
ALTER TABLE `app_acad_bg` DROP FOREIGN KEY `acadBg_province`;

ALTER TABLE `app_exp_and_achievements` DROP FOREIGN KEY `expAndAchievements_applcation`;

ALTER TABLE `app_institution` DROP FOREIGN KEY `institution_city`;
ALTER TABLE `app_institution` DROP FOREIGN KEY `institution_country`;
ALTER TABLE `app_institution` DROP FOREIGN KEY `institution_province`;

ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personFax_telNo`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personHome_telNo`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personMobile_telNo`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personNationality_city`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personNationality_country`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personNationality_province`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personResidence_country`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `personStudy_country`;
ALTER TABLE `app_personal_particulars` DROP FOREIGN KEY `person_institution`;

ALTER TABLE `app_prev_studies` DROP FOREIGN KEY `prevStudies_application`;
ALTER TABLE `app_prev_studies` DROP FOREIGN KEY `prevStudies_country`;
ALTER TABLE `app_prev_studies` DROP FOREIGN KEY `prevStudies_institution`;

ALTER TABLE `app_prog_requirement` DROP FOREIGN KEY `progRequirement_hkuProgramme`;
ALTER TABLE `app_prog_requirement` DROP FOREIGN KEY `progRequirement_requirement`;

ALTER TABLE `app_programme_choice` DROP FOREIGN KEY `programmeChoice_application`;
ALTER TABLE `app_programme_choice` DROP FOREIGN KEY `programmeChoice_hkuProgramme`;

ALTER TABLE `app_qualification` DROP FOREIGN KEY `qualification_application`;
ALTER TABLE `app_qualification` DROP FOREIGN KEY `qualification_examType`;

ALTER TABLE `app_qualification_rslt` DROP FOREIGN KEY `otherQualiRslt_otherQuali`;

ALTER TABLE `app_referee` DROP FOREIGN KEY `referee_reference`;

ALTER TABLE `app_reference` DROP FOREIGN KEY `reference_counselor`;

ALTER TABLE `app_req_doc` DROP FOREIGN KEY `reqDoc_application`;

ALTER TABLE `app_requirement_relationship` DROP FOREIGN KEY `requirementRelationShip_ChildRequirement`;
ALTER TABLE `app_requirement_relationship` DROP FOREIGN KEY `requirementRelationShip_ParentRequirement`;

ALTER TABLE `app_special_scheme` DROP FOREIGN KEY `specialScheme_application`;

ALTER TABLE `app_uploaded_doc` DROP FOREIGN KEY `uploadedDoc_reqDoc`;

ALTER TABLE `applicant_account` DROP FOREIGN KEY `userAccount_userInfo`;

ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_acadBg`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_acadQualHousingMgmt`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_acadQualNursing`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_admForm`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_others`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_person`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_reference`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_supportingDoc`;
ALTER TABLE `applicant_application` DROP FOREIGN KEY `application_userAccount`;

ALTER TABLE `applicant_msg` DROP FOREIGN KEY `applicantMsg_applicantAccount`;

ALTER TABLE `cpc_city` DROP FOREIGN KEY `city_country`;
ALTER TABLE `cpc_city` DROP FOREIGN KEY `city_province`;

ALTER TABLE `cpc_province` DROP FOREIGN KEY `province_country`;

ALTER TABLE `exam_grade` DROP FOREIGN KEY `examGrade_examType`;

ALTER TABLE `exam_subject` DROP FOREIGN KEY `examSubject_examType`;

ALTER TABLE `staff_info` DROP FOREIGN KEY `staffinfo_staffrole`;

ALTER TABLE `staff_prog` DROP FOREIGN KEY `staffProg_hkuProgramme`;
ALTER TABLE `staff_prog` DROP FOREIGN KEY `staffProg_staffInfo`;

ALTER TABLE `staff_role_privilege` DROP FOREIGN KEY `staffRolePrivilege_staffRolePrivilege`;
ALTER TABLE `staff_role_privilege` DROP FOREIGN KEY `staffRolePrivilege_staffrole`;

ALTER TABLE `adm_exe`
MODIFY COLUMN `adm_exe_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `adm_form`
MODIFY COLUMN `adm_form_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `adm_exe_id`  varchar(32) NOT NULL AFTER `adm_form_id`;

ALTER TABLE `adm_form_prog`
MODIFY COLUMN `adm_form_prog_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `adm_form_id`  varchar(32) NOT NULL AFTER `adm_form_prog_id`,
MODIFY COLUMN `app_hku_programme_id`  varchar(32) NOT NULL AFTER `adm_form_id`;

ALTER TABLE `adm_form_prog_req`
MODIFY COLUMN `adm_form_prog_req_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `adm_form_prog_id`  varchar(32) NULL DEFAULT NULL AFTER `adm_form_prog_req_id`,
MODIFY COLUMN `app_requirement_id`  varchar(32) NULL DEFAULT NULL AFTER `adm_form_prog_id`;

ALTER TABLE `app_acad_bg`
MODIFY COLUMN `acad_bg_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `country_id`  varchar(32) NOT NULL AFTER `acad_bg_id`,
MODIFY COLUMN `province_id`  varchar(32) NULL DEFAULT NULL AFTER `country_id`,
MODIFY COLUMN `city_id`  varchar(32) NOT NULL AFTER `province_id`,
MODIFY COLUMN `institution_id`  varchar(32) NOT NULL AFTER `city_id`;

ALTER TABLE `app_acad_qual_housing_mgmt`
MODIFY COLUMN `acad_qual_housing_mgmt_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_acad_qual_nursing`
MODIFY COLUMN `acad_qual_nursing_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_counselor`
MODIFY COLUMN `counselor_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_exp_and_achievements`
MODIFY COLUMN `exp_and_achievements_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `application_id`  varchar(32) NOT NULL AFTER `exp_and_achievements_id`;

ALTER TABLE `app_faculty`
MODIFY COLUMN `faculty_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_hku_programme`
MODIFY COLUMN `hku_programme_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_institution`
MODIFY COLUMN `institution_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `city_id`  varchar(32) NULL DEFAULT NULL AFTER `institution_desc`,
MODIFY COLUMN `province_id`  varchar(32) NULL DEFAULT NULL AFTER `city_id`,
MODIFY COLUMN `country_id`  varchar(32) NULL DEFAULT NULL AFTER `province_id`;

ALTER TABLE `app_interview_section`
MODIFY COLUMN `app_interview_section_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_others`
MODIFY COLUMN `others_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_personal_particulars`
MODIFY COLUMN `person_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `nationality_country_id`  varchar(32) NOT NULL AFTER `sex`,
MODIFY COLUMN `nationality_province_id`  varchar(32) NULL DEFAULT NULL AFTER `nationality_country_id`,
MODIFY COLUMN `nationality_city_id`  varchar(32) NOT NULL AFTER `nationality_province_id`,
MODIFY COLUMN `residence_country_id`  varchar(32) NOT NULL AFTER `nationality_city_id`,
MODIFY COLUMN `study_country_id`  varchar(32) NOT NULL AFTER `residence_country_id`,
MODIFY COLUMN `home_tel_no_id`  varchar(32) NULL DEFAULT NULL AFTER `study_country_id`,
MODIFY COLUMN `mobile_tel_no_id`  varchar(32) NULL DEFAULT NULL AFTER `home_tel_no_id`,
MODIFY COLUMN `fax_no_id`  varchar(32) NULL DEFAULT NULL AFTER `mobile_tel_no_id`,
MODIFY COLUMN `institution_id`  varchar(32) NOT NULL AFTER `fax_no_id`;

ALTER TABLE `app_prev_studies`
MODIFY COLUMN `prev_studies_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `application_id`  varchar(32) NOT NULL AFTER `prev_studies_id`,
MODIFY COLUMN `institution_id`  varchar(32) NOT NULL AFTER `application_id`,
MODIFY COLUMN `country_id`  varchar(32) NOT NULL AFTER `programme_title`;

ALTER TABLE `app_prog_requirement`
MODIFY COLUMN `app_prog_requirement_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `app_hku_programme_id`  varchar(32) NOT NULL AFTER `app_prog_requirement_id`,
MODIFY COLUMN `app_requirement_id`  varchar(32) NOT NULL AFTER `app_hku_programme_id`;

ALTER TABLE `app_programme_choice`
MODIFY COLUMN `programme_choice_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `hku_programme_id`  varchar(32) NOT NULL AFTER `programme_choice_id`,
MODIFY COLUMN `application_id`  varchar(32) NOT NULL AFTER `first_choice`;

ALTER TABLE `app_qualification`
MODIFY COLUMN `app_qualification_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `application_id`  varchar(32) NOT NULL AFTER `app_qualification_id`,
MODIFY COLUMN `exam_type_id`  varchar(32) NOT NULL AFTER `date_of_release_of_rslt`;

ALTER TABLE `app_qualification_rslt`
MODIFY COLUMN `app_qualification_rslt_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `app_qualification_id`  varchar(32) NOT NULL AFTER `app_qualification_rslt_id`;

ALTER TABLE `app_referee`
MODIFY COLUMN `referee_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `reference_id`  varchar(32) NOT NULL AFTER `referee_id`;

ALTER TABLE `app_reference`
MODIFY COLUMN `reference_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `counselor_id`  varchar(32) NOT NULL AFTER `reference_id`;

ALTER TABLE `app_req_doc`
MODIFY COLUMN `app_req_doc_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `application_id`  varchar(32) NOT NULL AFTER `app_req_doc_id`;

ALTER TABLE `app_req_doc_conf`
MODIFY COLUMN `app_req_doc_conf_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_requirement`
MODIFY COLUMN `app_requirement_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_requirement_relationship`
MODIFY COLUMN `parent_requirement_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `child_requirement_id`  varchar(32) NOT NULL AFTER `parent_requirement_id`;

ALTER TABLE `app_special_scheme`
MODIFY COLUMN `special_scheme_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `application_id`  varchar(32) NULL DEFAULT NULL AFTER `special_scheme_cd`;

ALTER TABLE `app_tel_no`
MODIFY COLUMN `tel_no_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `app_uploaded_doc`
MODIFY COLUMN `app_uploaded_doc_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `req_doc_id`  varchar(32) NOT NULL AFTER `app_uploaded_doc_id`;

ALTER TABLE `applicant_account`
MODIFY COLUMN `applicant_account_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `user_info_id`  varchar(32) NOT NULL AFTER `password_salt`,
ADD COLUMN `reg_date`  datetime NULL AFTER `time_out_datetime`,
ADD COLUMN `activate_date`  datetime NULL AFTER `reg_date`,
ADD COLUMN `activate_code`  varchar(64) NULL AFTER `activate_date`;

ALTER TABLE `applicant_application`
DROP COLUMN `acad_qual_nursing`,
MODIFY COLUMN `application_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `person_id`  varchar(32) NOT NULL AFTER `application_id`,
MODIFY COLUMN `acad_bg_id`  varchar(32) NOT NULL AFTER `person_id`,
MODIFY COLUMN `reference_id`  varchar(32) NOT NULL AFTER `acad_bg_id`,
MODIFY COLUMN `others_id`  varchar(32) NOT NULL AFTER `reference_id`,
MODIFY COLUMN `supporting_doc_id`  varchar(32) NOT NULL AFTER `others_id`,
MODIFY COLUMN `applicant_account_id`  varchar(32) NOT NULL AFTER `supporting_doc_id`,
MODIFY COLUMN `acad_qual_housing_mgmt_id`  varchar(32) NULL DEFAULT NULL AFTER `applicant_account_id`,
MODIFY COLUMN `adm_form_id`  varchar(32) NOT NULL AFTER `actual_grade`;

ALTER TABLE `applicant_info`
MODIFY COLUMN `applicant_info_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `nationality_country_id`  varchar(32) NOT NULL AFTER `given_name`,
MODIFY COLUMN `nationality_province_id`  varchar(32) NULL DEFAULT NULL AFTER `nationality_country_id`,
MODIFY COLUMN `nationality_city_id`  varchar(32) NULL DEFAULT NULL AFTER `nationality_province_id`,
MODIFY COLUMN `study_country_id`  varchar(32) NULL DEFAULT NULL AFTER `nationality_city_id`,
MODIFY COLUMN `institution_id`  varchar(32) NULL DEFAULT NULL AFTER `mobile_tel_no`;

ALTER TABLE `applicant_msg`
MODIFY COLUMN `applicant_msg_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `applicant_account_id`  varchar(32) NULL DEFAULT NULL AFTER `timestamp`;

ALTER TABLE `cpc_city`
MODIFY COLUMN `id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `province_id`  varchar(32) NULL DEFAULT NULL AFTER `desc_chi`,
MODIFY COLUMN `country_id`  varchar(32) NOT NULL AFTER `province_id`;

ALTER TABLE `cpc_country`
MODIFY COLUMN `id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `cpc_province`
MODIFY COLUMN `id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `country_id`  varchar(32) NOT NULL AFTER `desc_chi`;

ALTER TABLE `exam_grade`
MODIFY COLUMN `exam_grade_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `exam_type_id`  varchar(32) NOT NULL AFTER `exam_grade_id`;

ALTER TABLE `exam_subject`
MODIFY COLUMN `exam_subject_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `exam_type_id`  varchar(32) NOT NULL AFTER `sub_subject`;

ALTER TABLE `exam_type`
MODIFY COLUMN `exam_type_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `general_ref_cd`
MODIFY COLUMN `general_ref_cd_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `staff_info`
MODIFY COLUMN `staff_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `staff_role_id`  varchar(32) NOT NULL AFTER `status`;

ALTER TABLE `staff_privilege`
MODIFY COLUMN `staff_privilege_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `staff_prog`
MODIFY COLUMN `staff_prog_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `staff_id`  varchar(32) NOT NULL AFTER `staff_prog_id`,
MODIFY COLUMN `hku_programme_id`  varchar(32) NOT NULL AFTER `staff_id`;

ALTER TABLE `staff_role`
MODIFY COLUMN `staff_role_id`  varchar(32) NOT NULL FIRST ;

ALTER TABLE `staff_role_privilege`
MODIFY COLUMN `staff_role_privilege_id`  varchar(32) NOT NULL FIRST ,
MODIFY COLUMN `staff_role_id`  varchar(32) NULL DEFAULT NULL AFTER `staff_role_privilege_id`,
MODIFY COLUMN `staff_privilege_id`  varchar(32) NULL DEFAULT NULL AFTER `staff_role_id`;

ALTER TABLE `app_institution`
CHANGE COLUMN `institution_id` `id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL FIRST ,
CHANGE COLUMN `institution_cd` `cd`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `id`,
CHANGE COLUMN `institution_desc` `desc`  varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `cd`;