-- 2022-08-19T13:33:54.250Z
INSERT INTO C_ProjectType (AD_Client_ID,AD_Org_ID,C_ProjectType_ID,Created,CreatedBy,IsActive,Name,ProjectCategory,R_StatusCategory_ID,Updated,UpdatedBy) VALUES (1000000,1000000,540009,TO_TIMESTAMP('2022-08-19 16:33:54','YYYY-MM-DD HH24:MI:SS'),100,'Y','Default WorkOrderJob','N',540009,TO_TIMESTAMP('2022-08-19 16:33:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2022-08-19T13:33:55.279Z
UPDATE C_ProjectType SET ProjectCategory='W',Updated=TO_TIMESTAMP('2022-08-19 16:33:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ProjectType_ID=540009
;

-- 2022-08-19T13:34:03.248Z
UPDATE C_ProjectType SET AD_Sequence_ProjectValue_ID=556001,Updated=TO_TIMESTAMP('2022-08-19 16:34:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ProjectType_ID=540009
;
