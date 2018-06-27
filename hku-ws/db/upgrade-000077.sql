# ALTER TABLE `cpgn_crit_china_dtl`

ALTER TABLE `cpgn_crit_china_dtl`
ADD COLUMN `no_elig_appl`  int(5) NULL AFTER `cutoff_english`,
ADD COLUMN `pct_elig_appl`  int(3) NULL AFTER `no_elig_appl`;