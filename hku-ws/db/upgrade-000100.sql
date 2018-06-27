# ALTER TABLE `applicant_application` and `app_programme_choice`

ALTER TABLE `applicant_application`
DROP COLUMN `interview_score`,
ADD COLUMN `eng_req`  bit(1) NOT NULL DEFAULT 0 AFTER `status`,
ADD COLUMN `ccai_interview`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL AFTER `eng_req`;

ALTER TABLE `app_programme_choice`
ADD COLUMN `prog_req`  bit(1) NOT NULL DEFAULT 0 AFTER `meet_req_bit`,
ADD COLUMN `select_for_interview`  bit(1) NOT NULL DEFAULT 0 AFTER `prog_req`,
ADD COLUMN `prog_interview_score`  varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL AFTER `select_for_interview`,
ADD COLUMN `other_interview`  varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' AFTER `prog_interview_score`;