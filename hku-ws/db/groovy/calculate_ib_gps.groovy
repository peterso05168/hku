import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.Connection

def hkuSql =new Sql(connection)
         
def sql = '''

CREATE PROCEDURE calculate_IB_GPS()
BEGIN
DECLARE progChoiceId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE formProgId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE applicationId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE done_prog_choice INT DEFAULT 0;
	DECLARE prog_choice_list CURSOR FOR(SELECT c.programme_choice_id,c.adm_form_prog_id,c.application_id FROM app_programme_choice c where c.is_withdrawn_approved = 0);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_prog_choice = 1;

	OPEN prog_choice_list;
	prog_choice_items:LOOP
		FETCH prog_choice_list INTO progChoiceId,formProgId,applicationId;
	IF done_prog_choice = 1 THEN
		LEAVE prog_choice_items;
	END IF;

	BEGIN
		DECLARE qualificationId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
		DECLARE examTypeId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
		DECLARE done_qualification INT DEFAULT 0;
		DECLARE qualification_list CURSOR FOR(SELECT aq.app_qualification_id,aq.exam_type_id FROM app_qualification aq INNER JOIN exam_type et ON aq.exam_type_id = et.exam_type_id
		AND (et.exam_cd = 'IBD' OR et.exam_cd = 'IBC') WHERE aq.application_id = applicationId AND aq.is_delete_approved = 0);
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_qualification = 1;

		OPEN qualification_list;
		qualification_items:LOOP
			FETCH qualification_list INTO qualificationId,examTypeId;
		IF done_qualification = 1 THEN
			LEAVE qualification_items;
		END IF;

		BEGIN
			DECLARE exam_in_subject_count INT DEFAULT 0;
			DECLARE exam_any_subject_count INT DEFAULT 0;
			DECLARE formula_in_subject_count INT DEFAULT 0;
			DECLARE formula_any_subject_count INT DEFAULT 0;
			DECLARE scoringFormulaId VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;

			SELECT COUNT(*) INTO exam_in_subject_count FROM app_qualification_rslt aqr 
			INNER JOIN adm_scoring_formula sf ON sf.adm_form_prog_id = formProgId AND sf.exam_type_id = examTypeId AND sf.formula_type = 'GPS' AND sf.exam_level = aqr.exam_level
			INNER JOIN adm_gps_scoring_subject ags ON ags.exam_subject_id = aqr.exam_subject_id AND ags.type = 'IN' AND ags.adm_scoring_formula_id = sf.adm_scoring_formula_id
			WHERE aqr.app_qualification_id = qualificationId AND aqr.is_delete_approved = 0;

			SELECT COUNT(*) INTO exam_any_subject_count FROM app_qualification_rslt aqr 
			INNER JOIN adm_scoring_formula sf ON sf.adm_form_prog_id = formProgId AND sf.exam_type_id = examTypeId AND sf.formula_type = 'GPS' AND sf.exam_level = aqr.exam_level
			AND aqr.exam_subject_id NOT IN (SELECT ags.exam_subject_id FROM adm_gps_scoring_subject ags where ags.adm_scoring_formula_id = sf.adm_scoring_formula_id)
			WHERE aqr.app_qualification_id = qualificationId AND aqr.is_delete_approved = 0;

			SELECT COUNT(*) INTO formula_in_subject_count FROM adm_gps_scoring_subject ags 
			INNER JOIN adm_scoring_formula sf ON sf.adm_scoring_formula_id = ags.adm_scoring_formula_id 
			AND sf.exam_type_id = examTypeId AND sf.adm_form_prog_id = formProgId  
			WHERE ags.type = 'IN';

			SELECT sf.including INTO formula_any_subject_count FROM adm_scoring_formula sf 
			WHERE sf.adm_form_prog_id = formProgId AND sf.exam_type_id = examTypeId;
			
			SELECT sf.adm_scoring_formula_id INTO scoringFormulaId FROM adm_scoring_formula sf 
			WHERE sf.adm_form_prog_id = formProgId AND sf.exam_type_id = examTypeId;

			IF exam_in_subject_count = formula_in_subject_count AND exam_any_subject_count >= formula_any_subject_count AND scoringFormulaId IS NOT NULL THEN
				BEGIN
					DECLARE done_in_rslt_value INT DEFAULT 0;
					DECLARE in_rslt_value INT DEFAULT 0;
					DECLARE exam_rslt INT DEFAULT 0;
					DECLARE gps_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

					DECLARE in_rslt_value_list CURSOR FOR (SELECT eg.comparable_value FROM app_qualification_rslt aqr 
					INNER JOIN adm_scoring_formula sf ON sf.adm_form_prog_id = formProgId AND sf.exam_type_id = examTypeId
					AND sf.formula_type = 'GPS' AND sf.exam_level = aqr.exam_level
					INNER JOIN adm_gps_scoring_subject ags ON ags.exam_subject_id = aqr.exam_subject_id AND ags.type = 'IN' AND ags.adm_scoring_formula_id = sf.adm_scoring_formula_id
					INNER JOIN exam_grade eg ON eg.exam_type_id = examTypeId
					AND eg.grade_cd = IF(aqr.achieved_grade_cd !='' AND aqr.achieved_grade_cd IS NOT NULL,
					aqr.achieved_grade_cd,IF(aqr.predicted_grade_cd !='' AND aqr.predicted_grade_cd IS NOT NULL,aqr.predicted_grade_cd,NULL)) 
					WHERE aqr.app_qualification_id = qualificationId AND aqr.is_delete_approved = 0);
					DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_in_rslt_value = 1;

					OPEN in_rslt_value_list;
					in_rslt_items : LOOP
					FETCH in_rslt_value_list INTO in_rslt_value;
					IF done_in_rslt_value = 1 THEN
						LEAVE in_rslt_items;
					END IF;						
						SET exam_rslt = exam_rslt + in_rslt_value;
					END LOOP in_rslt_items;
					CLOSE in_rslt_value_list;
				
					BEGIN
						DECLARE done_any_rslt_value INT DEFAULT 0;
						DECLARE any_rslt_value INT DEFAULT 0;

						DECLARE any_rslt_value_list CURSOR FOR (SELECT eg.comparable_value FROM app_qualification_rslt aqr 
						INNER JOIN adm_scoring_formula sf ON sf.adm_form_prog_id = formProgId AND sf.exam_type_id = examTypeId
						AND sf.formula_type = 'GPS' AND sf.exam_level = aqr.exam_level
						AND aqr.exam_subject_id NOT IN (SELECT ags.exam_subject_id FROM adm_gps_scoring_subject ags where ags.adm_scoring_formula_id = sf.adm_scoring_formula_id)
						INNER JOIN exam_grade eg ON eg.exam_type_id = examTypeId 
						AND eg.grade_cd = IF(aqr.achieved_grade_cd !='' AND aqr.achieved_grade_cd IS NOT NULL,
						aqr.achieved_grade_cd,IF(aqr.predicted_grade_cd !='' AND aqr.predicted_grade_cd IS NOT NULL,aqr.predicted_grade_cd,NULL))
						WHERE aqr.app_qualification_id = qualificationId AND aqr.is_delete_approved = 0
						ORDER BY eg.comparable_value DESC LIMIT formula_any_subject_count);
						DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_any_rslt_value = 1;

						OPEN any_rslt_value_list;
						any_rslt_items : LOOP
						FETCH any_rslt_value_list INTO any_rslt_value;
						IF done_any_rslt_value = 1 THEN
							LEAVE any_rslt_items;
						END IF;						
							SET exam_rslt = exam_rslt + any_rslt_value;
						END LOOP any_rslt_items;
						CLOSE any_rslt_value_list;
						
						SELECT pc.app_prog_choice_gps_rslt_id INTO gps_rslt_id FROM app_prog_choice_gps_rslt pc 
						WHERE pc.adm_scoring_formula_id = scoringFormulaId AND pc.app_programme_choice_id = progChoiceId
						AND pc.app_qualification_id = qualificationId;
						IF (gps_rslt_id IS NOT NULL) THEN
							DELETE FROM app_prog_choice_gps_rslt WHERE app_prog_choice_gps_rslt_id = gps_rslt_id;
						END IF;

						SET gps_rslt_id = replace(uuid(), '-', '');
						INSERT INTO app_prog_choice_gps_rslt (app_prog_choice_gps_rslt_id,app_programme_choice_id,
						adm_scoring_formula_id,app_qualification_id,gps_rslt) VALUES (gps_rslt_id,progChoiceId,scoringFormulaId,qualificationId,exam_rslt);   
					END;
				END;
			END IF;
		END;
		END LOOP qualification_items;
		CLOSE qualification_list;
	END;
  END LOOP prog_choice_items;
  CLOSE prog_choice_list;
  COMMIT;
END

'''

hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_IB_GPS')
hkuSql.execute(sql)