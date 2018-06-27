# CREATE TABLE `app_prog_choice_gps_rslt`

CREATE TABLE `app_prog_choice_gps_rslt` (
`app_prog_choice_gps_rslt_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
`adm_scoring_formula_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL ,
`app_programme_choice_id`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL ,
`gps_rslt`  varchar(10) NULL ,
PRIMARY KEY (`app_prog_choice_gps_rslt_id`),
CONSTRAINT `gps_adm_scoring_formula_key` FOREIGN KEY (`adm_scoring_formula_id`) REFERENCES `adm_scoring_formula` (`adm_scoring_formula_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `gps_app_programme_choice_key` FOREIGN KEY (`app_programme_choice_id`) REFERENCES `app_programme_choice` (`programme_choice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

