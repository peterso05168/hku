import groovy.sql.Sql
import java.sql.DriverManager
import java.sql.Connection

def hkuSql =new Sql(connection)
         
def sql = '''

CREATE PROCEDURE calculate_gps()
BEGIN
	DECLARE prog_choice_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE application_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	DECLARE done_prog_choice INT DEFAULT 0;
	DECLARE prog_choice_list CURSOR FOR(SELECT c.programme_choice_id,c.application_id FROM app_programme_choice c where c.is_withdrawn = 0 and c.is_withdrawn_approved = 0);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_prog_choice = 1;
	
	OPEN prog_choice_list;
	prog_choice_items:LOOP
		FETCH prog_choice_list INTO prog_choice_id,application_id;
	IF done_prog_choice = 1 THEN
		LEAVE prog_choice_items;
	END IF;
	
	BEGIN
		DECLARE adm_soring_formuls_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
		DECLARE done_soring_formuls INT DEFAULT 0;
		DECLARE adm_soring_formuls_list CURSOR FOR (SELECT f.adm_scoring_formula_id FROM adm_scoring_formula f WHERE f.adm_form_prog_id = (
		SELECT pc.adm_form_prog_id FROM app_programme_choice pc WHERE pc.programme_choice_id = prog_choice_id) AND 
    f.formula_type = 'GPS' AND f.exam_type_id IN (SELECT et.exam_type_id FROM exam_type et WHERE et.exam_cd = 'GCE' OR et.exam_cd = 'IBD' OR et.exam_cd = 'IBC'));
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_soring_formuls = 1;
		
		OPEN adm_soring_formuls_list;
		soring_formuls_items:LOOP
			FETCH adm_soring_formuls_list INTO adm_soring_formuls_id;
		IF done_soring_formuls = 1 THEN 
			LEAVE soring_formuls_items;
		END IF;

		BEGIN
			DECLARE formula_exam_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE formula_exam_board VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE formula_exam_level VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE formula_including INT DEFAULT 0;
			DECLARE qualification_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE done_qualification INT DEFAULT 0;
			DECLARE qualification_exam_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
			DECLARE qualification_num INT DEFAULT 0;
			DECLARE qualification_id_count INT DEFAULT 0;
			DECLARE qualification_list CURSOR FOR (SELECT q.app_qualification_id FROM app_qualification q where 
			q.application_id = application_id AND q.is_deleted = 0 AND q.is_delete_approved = 0);
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_qualification = 1;
				
			SELECT sf.exam_type_id INTO formula_exam_id FROM adm_scoring_formula sf WHERE sf.adm_scoring_formula_id = adm_soring_formuls_id;
			SELECT sf.exam_board INTO formula_exam_board FROM adm_scoring_formula sf WHERE sf.adm_scoring_formula_id = adm_soring_formuls_id;
			SELECT sf.exam_level INTO formula_exam_level FROM adm_scoring_formula sf WHERE sf.adm_scoring_formula_id = adm_soring_formuls_id;
			SELECT sf.including INTO formula_including FROM adm_scoring_formula sf WHERE sf.adm_scoring_formula_id = adm_soring_formuls_id;
			SELECT count(*) INTO qualification_id_count FROM app_qualification q where q.application_id = application_id AND q.is_deleted = 0 AND q.is_delete_approved =0;
			
			OPEN qualification_list;
			WHILE qualification_num < qualification_id_count DO
				FETCH qualification_list INTO qualification_id;
				SET qualification_num = qualification_num + 1;
				SELECT q.exam_type_id INTO qualification_exam_id FROM app_qualification q WHERE q.app_qualification_id = qualification_id;

				IF qualification_exam_id = formula_exam_id THEN
					BEGIN
					 DECLARE gps_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE find_subject_count INT DEFAULT 0;
					 DECLARE in_subject_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE ex_subject_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE exam_subject_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE exam_subject_id_count INT DEFAULT 0;
					 DECLARE exam_subject_num INT DEFAULT 0;
					 DECLARE in_subject_id_count INT DEFAULT 0;
					 DECLARE in_subject_num INT DEFAULT 0;
					 DECLARE ex_subject_id_count INT DEFAULT 0;
					 DECLARE ex_subject_num INT DEFAULT 0;
					 DECLARE subject_count INT DEFAULT 0;
					 DECLARE exam_rslt INT DEFAULT 0;
					 DECLARE done_gps_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;
					 DECLARE any_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE any_rslt_num INT DEFAULT 0;
					 DECLARE exam_type_cd VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE exam_rslt_id VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE grade_cd VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
					 DECLARE done_any_rslt INT DEFAULT 0;

					 DECLARE exam_subject_id_list CURSOR FOR (SELECT qr.exam_subject_id,qr.app_qualification_rslt_id FROM app_qualification_rslt qr WHERE 
					 qr.app_qualification_id = qualification_id AND qr.is_deleted = 0 AND qr.is_delete_approved = 0);

					 DECLARE in_subject_id_list CURSOR FOR (SELECT g.exam_subject_id FROM adm_gps_scoring_subject g where 
					 g.adm_scoring_formula_id = adm_soring_formuls_id AND g.type = 'IN');

					 DECLARE ex_subject_id_list CURSOR FOR (SELECT g.exam_subject_id FROM adm_gps_scoring_subject g where 
					 g.adm_scoring_formula_id = adm_soring_formuls_id AND g.type = 'EX');

					 DECLARE any_rslt_id_list CURSOR FOR (SELECT qr.app_qualification_rslt_id FROM app_qualification_rslt qr INNER JOIN 
					 exam_grade eg ON eg.exam_type_id = qualification_exam_id AND qr.achieved_grade_cd = eg.grade_cd WHERE 
					 qr.app_qualification_id = qualification_id AND qr.is_deleted = 0 AND qr.is_delete_approved = 0 AND 
					 qr.achieved_grade_cd = (SELECT ega.grade_cd FROM exam_grade ega WHERE ega.exam_type_id = qualification_exam_id AND 
					 ega.grade_cd = qr.achieved_grade_cd GROUP BY ega.grade_cd)
					 AND qr.exam_subject_id NOT IN (SELECT g.exam_subject_id FROM adm_gps_scoring_subject g where 
					 g.adm_scoring_formula_id = adm_soring_formuls_id) AND qr.achieved_grade_cd !='others' AND qr.achieved_grade_cd !='' AND qr.achieved_grade_cd IS NOT NULL
					 GROUP BY qr.app_qualification_rslt_id ORDER BY eg.comparable_value DESC LIMIT formula_including);
					 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_any_rslt = 1;
											 
					 SELECT count(*) into exam_subject_id_count FROM app_qualification_rslt qr WHERE 
					 qr.app_qualification_id = qualification_id AND qr.is_deleted = 0 AND qr.is_delete_approved = 0;
					 SELECT count(*) into in_subject_id_count FROM adm_gps_scoring_subject g where 
					 g.adm_scoring_formula_id = adm_soring_formuls_id AND g.type = 'IN';
					 SELECT count(*) into ex_subject_id_count FROM adm_gps_scoring_subject g where 
					 g.adm_scoring_formula_id = adm_soring_formuls_id AND g.type = 'EX';

					 IF exam_subject_id_count > (in_subject_id_count + formula_including) THEN
						 OPEN in_subject_id_list;
						 WHILE in_subject_num < in_subject_id_count DO
								FETCH in_subject_id_list INTO in_subject_id;
								SET in_subject_num = in_subject_num + 1;
								
								OPEN exam_subject_id_list;
								WHILE exam_subject_num < exam_subject_id_count DO
									FETCH exam_subject_id_list INTO exam_subject_id,exam_rslt_id;
									SET exam_subject_num = exam_subject_num + 1;
									IF in_subject_id = exam_subject_id THEN
										SET exam_subject_num = exam_subject_id_count + 1;
										SET find_subject_count = find_subject_count + 1;
										SELECT et.exam_cd INTO exam_type_cd FROM exam_type et WHERE et.exam_type_id = qualification_exam_id;
										SELECT qr.achieved_grade_cd INTO grade_cd FROM app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = exam_rslt_id;

			              				IF exam_type_cd = 'GCE' AND grade_cd !='others' AND grade_cd !='' AND grade_cd IS NOT NULL THEN
											SET exam_rslt = exam_rslt + (SELECT eg.comparable_value FROM exam_grade eg WHERE 
											eg.exam_type_id = qualification_exam_id AND eg.grade_cd = (SELECT qr.achieved_grade_cd FROM 
											app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = exam_rslt_id) AND eg.exam_level = (
											SELECT qr.exam_level FROM app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = exam_rslt_id));
										
										ELSEIF (exam_type_cd = 'IBD' OR exam_type_cd = 'IBC') AND grade_cd !='' AND grade_cd IS NOT NULL THEN
											SET exam_rslt = exam_rslt + (SELECT eg.comparable_value FROM exam_grade eg WHERE 
											eg.exam_type_id = qualification_exam_id AND eg.grade_cd = (SELECT qr.achieved_grade_cd FROM 
											app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = exam_rslt_id));
										END IF;
									END IF;
								END WHILE;
								CLOSE exam_subject_id_list;
								SET exam_subject_num = 0;

								IF find_subject_count = in_subject_id_count THEN
									SET in_subject_num = in_subject_id_count + 1;
									SET find_subject_count = in_subject_id_count;
								END IF;
						 END WHILE;
						 CLOSE in_subject_id_list;

						 IF find_subject_count = in_subject_id_count THEN
							 SET subject_count = exam_subject_id_count;
							 OPEN ex_subject_id_list;
							 WHILE ex_subject_num < ex_subject_id_count DO
								 FETCH ex_subject_id_list INTO ex_subject_id;
								 SET ex_subject_num = ex_subject_num + 1;
									 
								 OPEN exam_subject_id_list;
								 WHILE exam_subject_num < exam_subject_id_count DO
									 FETCH exam_subject_id_list INTO exam_subject_id,exam_rslt_id;
									 SET exam_subject_num = exam_subject_num + 1;
									 
									 IF ex_subject_id = exam_subject_id THEN
										SET exam_subject_num = exam_subject_id_count + 1;
										SET subject_count = subject_count - 1;
									 END IF;
								 END WHILE;
								 CLOSE exam_subject_id_list;
								 SET exam_subject_num = 0;
										
							 END WHILE;
							 CLOSE ex_subject_id_list;

						 ELSE
							 SET exam_rslt = 0;
						 END IF;

						 IF subject_count >= (in_subject_id_count + formula_including) THEN
								OPEN any_rslt_id_list;
								any_rslt_items : LOOP
								FETCH any_rslt_id_list INTO any_rslt_id;
								IF done_any_rslt = 1 THEN
									LEAVE any_rslt_items;
								END IF;						
									SELECT et.exam_cd INTO exam_type_cd FROM exam_type et WHERE et.exam_type_id = qualification_exam_id;
									SELECT qr.achieved_grade_cd INTO grade_cd FROM app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = any_rslt_id;

									IF exam_type_cd = 'GCE' AND grade_cd !='others' AND grade_cd !='' AND grade_cd IS NOT NULL THEN
										SET exam_rslt = exam_rslt + (SELECT eg.comparable_value FROM exam_grade eg WHERE 
										eg.exam_type_id = qualification_exam_id AND eg.grade_cd = (SELECT qr.achieved_grade_cd FROM 
										app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = any_rslt_id) AND
										eg.exam_level = (SELECT qr.exam_level FROM app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = any_rslt_id));

									ELSEIF (exam_type_cd = 'IBD' OR exam_type_cd = 'IBC') AND grade_cd !='' AND grade_cd IS NOT NULL THEN
										SET exam_rslt = exam_rslt + (SELECT eg.comparable_value FROM exam_grade eg WHERE 
										eg.exam_type_id = qualification_exam_id AND eg.grade_cd = (SELECT qr.achieved_grade_cd FROM 
										app_qualification_rslt qr WHERE qr.app_qualification_rslt_id = any_rslt_id));
									END IF;
								END LOOP any_rslt_items;
								CLOSE any_rslt_id_list;							

								SELECT pc.app_prog_choice_gps_rslt_id INTO done_gps_rslt_id FROM app_prog_choice_gps_rslt pc 
								WHERE pc.adm_scoring_formula_id = adm_soring_formuls_id and pc.app_programme_choice_id = prog_choice_id;
								IF (done_gps_rslt_id IS NOT NULL) THEN
									DELETE FROM app_prog_choice_gps_rslt WHERE app_prog_choice_gps_rslt_id = done_gps_rslt_id;
								END IF;

								SET gps_rslt_id = replace(uuid(), '-', '');
								INSERT INTO app_prog_choice_gps_rslt (app_prog_choice_gps_rslt_id,app_programme_choice_id,
								adm_scoring_formula_id,gps_rslt) VALUES (gps_rslt_id,prog_choice_id,adm_soring_formuls_id,exam_rslt);   

							ELSE 
							  SET exam_rslt = 0;
						  END IF;
						  SET exam_rslt = 0;
					 END IF;
					END;
				  END IF; 
				END WHILE;
				CLOSE qualification_list;
			END;
			END LOOP soring_formuls_items;
			CLOSE adm_soring_formuls_list;
	END;
	END LOOP prog_choice_items;
	CLOSE prog_choice_list;
	COMMIT;
END;

'''

hkuSql.execute('DROP PROCEDURE IF EXISTS calculate_gps')
hkuSql.execute(sql)