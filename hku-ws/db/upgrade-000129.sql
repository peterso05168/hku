# ALTER TABLE adm_anncmnt

UPDATE adm_anncmnt SET type_cd = 'PAYMENT_FAIL' WHERE type_cd = 'PAYMENT';

INSERT INTO adm_anncmnt (id, type_cd,subject,content,create_date,modify_date) VALUES (REPLACE(UUID(),"-",""), 'PAYMENT_SUCCESS','Charge for Payment Letter','The Application Fee payment for Application (Application No) is successful.\r\n\r\nPlease click here to upload supporting document.\r\n\r\nRegards,',NOW(),NOW());