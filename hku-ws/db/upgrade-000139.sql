# INSERT INTO general_ref_cd data

INSERT INTO general_ref_cd VALUES (REPLACE(UUID(),"-",""),'CHOICESTATUS','RFO','Ready for Offer',NULL,now(),0);
INSERT INTO general_ref_cd VALUES (REPLACE(UUID(),"-",""),'CHOICESTATUS','OAFA','Offer Assigned for Approval',NULL,now(),0);