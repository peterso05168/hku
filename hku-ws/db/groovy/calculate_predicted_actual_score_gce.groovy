import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.Connection

def hkuSql =new Sql(connection)
         
def sql = '''

CREATE PROCEDURE calculate_predicted_actual_score_GCE()
BEGIN
	DECLARE applicationid VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE best_exam_subj_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE done_application INT DEFAULT 0;
	DECLARE examTypeId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE application_list CURSOR FOR (SELECT a.application_id FROM applicant_application a);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_application = 1;
	SET examTypeId = (SELECT a.exam_type_id FROM exam_type a WHERE a.exam_cd = 'GCE');

	OPEN application_list;

	items : LOOP
		FETCH application_list INTO applicationid;
	IF done_application = 1 THEN
		LEAVE items;
	END IF;

		BEGIN
			DECLARE qualification_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE subjectDesc VARCHAR(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE examTypeMonth INT;
			DECLARE examTypeYear INT;
			DECLARE examGradeCd VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE rsltType VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE seq_no INT DEFAULT 0;
			DECLARE number INT DEFAULT 1;
			DECLARE done_qualification INT DEFAULT 0;
			DECLARE qualification_rslt_list CURSOR FOR (
				SELECT c.app_qualification_rslt_id,d.exam_subject_desc,b.exam_type_month,b.exam_type_year,
				IF(c.achieved_grade_cd IS NOT NULL AND c.predicted_grade_cd IS NOT NULL AND c.achieved_grade_cd !='' AND c.predicted_grade_cd!='',
						IF(e.comparable_value>=f.comparable_value,c.achieved_grade_cd,c.predicted_grade_cd),
						IF(c.achieved_grade_cd IS NOT NULL AND c.achieved_grade_cd != '',c.achieved_grade_cd,c.predicted_grade_cd)),
				IF(c.achieved_grade_cd IS NOT NULL AND c.predicted_grade_cd IS NOT NULL AND c.achieved_grade_cd !='' AND c.predicted_grade_cd!='',
						IF(e.comparable_value >= f.comparable_value,'Achieved','Predicted'),
						IF(c.achieved_grade_cd IS NOT NULL AND c.achieved_grade_cd != '','Achieved','Predicted'))
				FROM app_qualification b
				INNER JOIN app_qualification_rslt c ON b.app_qualification_id = c.app_qualification_id
				INNER JOIN exam_subject d ON d.exam_subject_id = c.exam_subject_id AND c.exam_level = d.exam_level
				LEFT JOIN exam_grade e ON e.exam_type_id = b.exam_type_id AND e.grade_cd = c.achieved_grade_cd AND c.exam_level = e.exam_level
				LEFT JOIN exam_grade f ON f.exam_type_id = b.exam_type_id AND f.grade_cd = c.predicted_grade_cd AND c.exam_level = f.exam_level
				WHERE b.exam_type_id = examTypeId 
				AND b.application_id = applicationid 
				AND b.is_delete_approved = 0 
				AND c.is_delete_approved = 0 
				GROUP BY d.exam_subject_desc,d.exam_board,d.exam_level
				ORDER BY IF(c.achieved_grade_cd IS NOT NULL AND c.predicted_grade_cd IS NOT NULL AND c.achieved_grade_cd !='' AND c.predicted_grade_cd!='',
						IF(e.comparable_value>=f.comparable_value,e.comparable_value,f.comparable_value),
						IF(c.achieved_grade_cd IS NOT NULL AND c.achieved_grade_cd != '',e.comparable_value,f.comparable_value)) DESC LIMIT 5);
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_qualification = 1;

			DELETE FROM app_best_exam_subj_rslt WHERE app_best_exam_subj_id = (
				SELECT app_best_exam_subj_id FROM app_best_exam_subj WHERE application_id = applicationid 
				AND exam_type_id = examTypeId AND calculate_type = 'Predicted and Actual'
			);
			DELETE FROM app_best_exam_subj WHERE application_id = applicationid AND exam_type_id = examTypeId AND calculate_type = 'Predicted and Actual';

			OPEN qualification_rslt_list;
				qualification_rslt_items : LOOP
					FETCH qualification_rslt_list INTO qualification_rslt_id,subjectDesc,examTypeMonth,examTypeYear,examGradeCd,rsltType;

			IF done_qualification = 1 THEN
				LEAVE qualification_rslt_items;
			END IF;

			IF number = 1 THEN
				SET best_exam_subj_id = replace(uuid(), '-', '');
				INSERT INTO app_best_exam_subj (app_best_exam_subj_id,exam_type_id,application_id,calculate_type) VALUES (best_exam_subj_id,examTypeId,applicationid,'Predicted and Actual');
			END IF;

			SET number = number + 1;

			SET seq_no = seq_no + 1;
			SET @best_exam_subj_rslt_id = replace(uuid(), '-', '');

			INSERT INTO app_best_exam_subj_rslt (app_best_exam_subj_rslt_id,app_best_exam_subj_id,app_qualification_rslt_id,seq_no,exam_subject_desc,exam_type_month,exam_type_year,exam_grade_cd,rslt_type) 
						VALUES (@best_exam_subj_rslt_id,best_exam_subj_id,qualification_rslt_id,seq_no,subjectDesc,examTypeMonth,examTypeYear,examGradeCd,rsltType);

			END LOOP qualification_rslt_items;
			CLOSE qualification_rslt_list;
		END;

	END LOOP items;
	CLOSE application_list;
	COMMIT;
END

'''

hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_predicted_actual_score_GCE')
hkuSql.execute(sql)