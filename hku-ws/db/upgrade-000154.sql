# ALTER TABLE `app_ibd_best_exam` ADD COLUMN `ee_tok`

ALTER TABLE `app_ibd_best_exam`
ADD COLUMN `ee_tok`  int(10) NULL AFTER `total_rslt`;

groovy groovy/calculate_actual_score_ibd_42.groovy;
groovy groovy/calculate_actual_score_ibd_45.groovy;
groovy groovy/calculate_predicted_actual_score_ibd_42.groovy;
groovy groovy/calculate_predicted_actual_score_ibd_45.groovy;