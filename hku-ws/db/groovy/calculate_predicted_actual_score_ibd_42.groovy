import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.Connection

def hkuSql =new Sql(connection)
         
def sql = '''

CREATE PROCEDURE calculate_predicted_actual_score_IBD_42()
BEGIN
	DECLARE applicationid VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE done_application INT DEFAULT 0;
	DECLARE examTypeId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE application_list CURSOR FOR (SELECT a.application_id FROM applicant_application a);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_application = 1;
	SET examTypeId = (SELECT a.exam_type_id FROM exam_type a WHERE a.exam_cd = 'IBD');

	OPEN application_list;

	items : LOOP
		FETCH application_list INTO applicationid;
	IF done_application = 1 THEN
		LEAVE items;
	END IF;

		BEGIN
			DECLARE qualification_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE total_rslt INT;
			DECLARE done_qualification INT DEFAULT 0;
			DECLARE qualification_rslt_list CURSOR FOR (
				SELECT a.app_qualification_id,
				SUM(
					IF (b.achieved_grade_cd IS NOT NULL AND b.predicted_grade_cd IS NOT NULL AND b.achieved_grade_cd != '' AND b.predicted_grade_cd != '',
							(IF(c.comparable_value>d.comparable_value,c.comparable_value,d.comparable_value)),
							IF((b.achieved_grade_cd IS NULL OR b.achieved_grade_cd = ''),d.comparable_value,c.comparable_value))
				) FROM app_qualification a 
					INNER JOIN app_qualification_rslt b ON a.app_qualification_id = b.app_qualification_id
					LEFT JOIN exam_grade c ON c.exam_type_id = a.exam_type_id AND c.grade_cd = b.achieved_grade_cd
					LEFT JOIN exam_grade d ON d.exam_type_id = a.exam_type_id AND d.grade_cd = b.predicted_grade_cd
				WHERE a.exam_type_id = examTypeId
				AND (
						((a.ib_achieved_rslt_grade_cd IS NULL OR a.ib_achieved_rslt_grade_cd = '') AND a.ib_predicted_rslt_grade_cd = 'N - No grade')
					OR ((a.ib_predicted_rslt_grade_cd IS NULL OR a.ib_predicted_rslt_grade_cd = '') AND a.ib_achieved_rslt_grade_cd = 'N - No grade')
					OR (a.ib_achieved_rslt_grade_cd = 'N - No grade' AND a.ib_predicted_rslt_grade_cd = 'N - No grade')
				)
				AND a.application_id = applicationid 
				AND a.is_delete_approved = 0 
				AND b.is_delete_approved = 0 
				GROUP BY a.app_qualification_id
				ORDER BY SUM(
					IF (b.achieved_grade_cd IS NOT NULL AND b.predicted_grade_cd IS NOT NULL AND b.achieved_grade_cd != '' AND b.predicted_grade_cd != '',
							(IF(c.comparable_value>d.comparable_value,c.comparable_value,d.comparable_value)),
							IF((b.achieved_grade_cd IS NULL OR b.achieved_grade_cd = ''),d.comparable_value,c.comparable_value))
				) DESC 
				LIMIT 1
			);
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_qualification = 1;

			DELETE FROM app_ibd_best_exam WHERE application_id = applicationid 
			AND calculate_type = 'Predicted and Actual' AND out_of = '42';

			OPEN qualification_rslt_list;
				qualification_rslt_items : LOOP
					FETCH qualification_rslt_list INTO qualification_id, total_rslt;

			IF done_qualification = 1 OR qualification_id is NULL OR total_rslt IS NULL THEN
				LEAVE qualification_rslt_items;
			END IF;

			SET @ibd_best_exam_id = replace(uuid(), '-', '');
			INSERT INTO app_ibd_best_exam (app_ibd_best_exam_id,application_id,app_qualification_id,total_rslt,calculate_type,out_of) 
						VALUES (@ibd_best_exam_id,applicationid,qualification_id,total_rslt,'Predicted and Actual','42');

			END LOOP qualification_rslt_items;
			CLOSE qualification_rslt_list;
		END;

	END LOOP items;
	CLOSE application_list;
	COMMIT;
END

'''

hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_predicted_actual_score_IBD_42')
hkuSql.execute(sql)