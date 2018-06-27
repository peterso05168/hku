# ALTER TABLE `applicant_application`

ALTER TABLE `applicant_application`
ADD COLUMN `scholar_heforshe`  bit(1) NULL DEFAULT 0 AFTER `adm_form_id`,
ADD COLUMN `scholar_uwc`  bit(1) NULL DEFAULT 0 AFTER `scholar_heforshe`,
ADD COLUMN `scholar_nigerian`  bit(1) NULL DEFAULT 0 AFTER `scholar_uwc`,
ADD COLUMN `scholar_vtp`  bit(1) NULL DEFAULT 0 AFTER `scholar_nigerian`,
ADD COLUMN `scholar_afl`  bit(1) NULL DEFAULT 0 AFTER `scholar_vtp`,
ADD COLUMN `scholar_hksar_gsft`  bit(1) NULL DEFAULT 0 AFTER `scholar_afl`,
ADD COLUMN `scholar_hksar_gsft_brs`  bit(1) NULL DEFAULT 0 AFTER `scholar_hksar_gsft`;