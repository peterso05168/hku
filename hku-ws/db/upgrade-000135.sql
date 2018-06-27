# ALTER TABLE `applicant_application`、INSERT INTO general_ref_cd data

ALTER TABLE `applicant_application`
CHANGE COLUMN `ccai_interview` `ccai_g_interview`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL AFTER `eng_req`,
ADD COLUMN `ccai_i_interview`  varchar(10) NULL AFTER `ccai_g_interview`;

INSERT INTO general_ref_cd VALUES (REPLACE(UUID(),"-",""),'OPQUALIFICATION','SAT','SAT',NULL,now(),0);
INSERT INTO general_ref_cd VALUES (REPLACE(UUID(),"-",""),'OPQUALIFICATION','GCE','GCE',NULL,now(),0);
INSERT INTO general_ref_cd VALUES (REPLACE(UUID(),"-",""),'OPQUALIFICATION','IB42','IB42',NULL,now(),0);
INSERT INTO general_ref_cd VALUES (REPLACE(UUID(),"-",""),'OPQUALIFICATION','IB45','IB45',NULL,now(),0);