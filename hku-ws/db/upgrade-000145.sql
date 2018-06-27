# Modify PROCEDURE for score calculation job

ALTER TABLE `app_ibd_best_exam`
MODIFY COLUMN `total_rslt`  int(10) NULL AFTER `app_qualification_id`;

groovy groovy/calculate_actual_score_ibd_42.groovy;
groovy groovy/calculate_actual_score_ibd_45.groovy;
groovy groovy/calculate_predicted_actual_score_ibd_42.groovy;
groovy groovy/calculate_predicted_actual_score_ibd_45.groovy;