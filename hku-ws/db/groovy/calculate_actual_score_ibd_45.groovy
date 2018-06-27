import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.Connection

def hkuSql =new Sql(connection)
         
def sql = '''

CREATE PROCEDURE calculate_actual_score_IBD_45()
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
			DECLARE totalRslt INT;
			DECLARE ee_tok INT;
			DECLARE done_qualification INT DEFAULT 0;
			DECLARE qualification_rslt_list CURSOR FOR (
				SELECT a.app_qualification_id,MAX(a.total_rslt) FROM (
					SELECT a.app_qualification_id,SUM(b.comparable_value) AS total_rslt FROM (
						SELECT b.app_qualification_id,c.achieved_grade_cd AS grade_cd
						FROM app_qualification b
						INNER JOIN app_qualification_rslt c ON b.app_qualification_id = c.app_qualification_id
						INNER JOIN exam_subject d ON d.exam_subject_id = c.exam_subject_id
						INNER JOIN exam_type e ON e.exam_type_id = d.exam_type_id
						INNER JOIN exam_grade f ON f.exam_type_id = e.exam_type_id 
						AND f.grade_cd = IF(c.achieved_grade_cd IS NULL,c.predicted_grade_cd,c.achieved_grade_cd)
						WHERE b.application_id = applicationid 
						AND b.exam_type_id = examTypeId 
						AND b.ib_achieved_rslt_grade_cd != 'N - No grade' 
						AND b.is_delete_approved = 0 
						AND c.is_delete_approved = 0 
					) a INNER JOIN exam_grade b ON a.grade_cd = b.grade_cd WHERE b.exam_type_id = examTypeId 
					GROUP BY a.app_qualification_id
				) a
			);
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_qualification = 1;

			DELETE FROM app_ibd_best_exam WHERE application_id = applicationid 
			AND calculate_type = 'Actual' AND out_of = '45';

			OPEN qualification_rslt_list;
				qualification_rslt_items : LOOP
					FETCH qualification_rslt_list INTO qualification_id, totalRslt;

			SELECT IF(a.ib_achieved_rslt_grade_cd IS NULL OR a.ib_achieved_rslt_grade_cd = '' OR a.ib_achieved_rslt_grade_cd = 'N - No grade',-1,a.ib_achieved_rslt_grade_cd) 
			INTO ee_tok 
			FROM app_qualification a WHERE a.app_qualification_id = qualification_id;

			IF done_qualification = 1 OR qualification_id is NULL OR totalRslt IS NULL OR ee_tok = -1 THEN
				LEAVE qualification_rslt_items;
			END IF;
			SELECT totalRslt + ee_tok INTO totalRslt;

			SET @ibd_best_exam_id = replace(uuid(), '-', '');
			INSERT INTO app_ibd_best_exam (app_ibd_best_exam_id,application_id,app_qualification_id,total_rslt,calculate_type,out_of) 
						VALUES (@ibd_best_exam_id,applicationid,qualification_id,totalRslt,'Actual','45');

			END LOOP qualification_rslt_items;
			CLOSE qualification_rslt_list;
		END;

	END LOOP items;
	CLOSE application_list;
	COMMIT;
END

'''

hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_score_IBD')
hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_actual_score_IBD_45')
hkuSql.execute(sql)