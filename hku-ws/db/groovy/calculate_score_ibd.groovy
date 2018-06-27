import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.Connection

def hkuSql =new Sql(connection)
         
def sql = '''

CREATE PROCEDURE calculate_score_IBD()
BEGIN
	DECLARE application_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE best_exam_subj_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE done_application INT DEFAULT 0;
	DECLARE examTypeId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE application_list CURSOR FOR (SELECT a.application_id FROM applicant_application a);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_application = 1;
	SET examTypeId = (SELECT a.exam_type_id FROM exam_type a WHERE a.exam_cd = 'IBD');

	OPEN application_list;

	items : LOOP
		FETCH application_list INTO application_id;
	IF done_application = 1 THEN
		LEAVE items;
	END IF;

		BEGIN
			DECLARE qualification_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE seq_no INT DEFAULT 0;
			DECLARE number INT DEFAULT 1;
			DECLARE done_qualification INT DEFAULT 0;
			DECLARE qualification_rslt_list CURSOR FOR (SELECT c.app_qualification_rslt_id FROM app_qualification b
				INNER JOIN app_qualification_rslt c ON b.app_qualification_id = c.app_qualification_id
				INNER JOIN exam_subject d ON d.exam_subject_id = c.exam_subject_id
				INNER JOIN exam_type e ON e.exam_type_id = d.exam_type_id
				INNER JOIN exam_grade f ON f.exam_type_id = e.exam_type_id AND f.grade_cd = c.achieved_grade_cd AND c.exam_level = f.exam_level
				WHERE e.exam_type_id = examTypeId AND b.application_id = application_id AND b.last_updated_by != 'batchjob'
				GROUP BY c.app_qualification_rslt_id
				ORDER BY f.comparable_value DESC LIMIT 5);
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_qualification = 1;

			OPEN qualification_rslt_list;
				qualification_rslt_items : LOOP
					FETCH qualification_rslt_list INTO qualification_rslt_id;

			IF done_qualification = 1 THEN
				LEAVE qualification_rslt_items;
			END IF;

			IF number = 1 THEN
				SET best_exam_subj_id = replace(uuid(), '-', '');
				INSERT INTO app_best_exam_subj (app_best_exam_subj_id,exam_type_id,application_id) VALUES (best_exam_subj_id,examTypeId,application_id);
				UPDATE app_qualification SET last_updated_by = 'batchjob',last_updated_date = NOW() WHERE application_id = application_id AND exam_type_id = examTypeId;
			END IF;

			SET number = number + 1;

			SET seq_no = seq_no + 1;
			SET @best_exam_subj_rslt_id = replace(uuid(), '-', '');

			INSERT INTO app_best_exam_subj_rslt (app_best_exam_subj_rslt_id,app_best_exam_subj_id,app_qualification_rslt_id,seq_no) 
						VALUES (@best_exam_subj_rslt_id,best_exam_subj_id,qualification_rslt_id,seq_no);

			END LOOP qualification_rslt_items;
			CLOSE qualification_rslt_list;
		END;

	END LOOP items;
	CLOSE application_list;
	COMMIT;
END

'''

hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_score_IBD')
hkuSql.execute(sql)