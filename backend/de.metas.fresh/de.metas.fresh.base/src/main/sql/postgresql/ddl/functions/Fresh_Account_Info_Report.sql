-- Function: report.fresh_account_info_report_sub(numeric, date, date, numeric, character varying, character varying, character varying, numeric)

-- DROP FUNCTION report.fresh_account_info_report_sub(numeric, date, date, numeric, character varying, character varying, character varying, numeric);
-- DROP FUNCTION IF EXISTS report.fresh_account_info_report_sub(numeric,date, date, numeric, character varying, character varying, character varying, numeric);

DROP FUNCTION IF EXISTS report.fresh_account_info_report_sub(numeric,
                                                             numeric,
                                                             numeric,
                                                             numeric,
                                                             numeric,
                                                             character varying,
                                                             character varying,
                                                             character varying,
                                                             numeric)
;

DROP FUNCTION IF EXISTS report.fresh_account_info_report_sub(numeric,
                                                             numeric,
                                                             date,
                                                             date,
                                                             numeric,
                                                             numeric,
                                                             numeric,
                                                             character varying,
                                                             character varying,
                                                             character varying,
                                                             numeric)
;

DROP FUNCTION IF EXISTS report.fresh_account_info_report_sub(numeric,
                                                             numeric,
                                                             date,
                                                             date,
                                                             numeric,
                                                             numeric,
                                                             numeric,
                                                             character varying,
                                                             character varying,
                                                             character varying,
                                                             numeric)
;


DROP FUNCTION IF EXISTS report.fresh_account_info_report_sub(
    IN account_start_id      numeric, --$1
    IN account_end_id        numeric, --$2
    IN C_Period_Start_ID     numeric, --$3
    IN C_Period_End_ID       numeric, --$4
    IN StartDate             date, --$5
    IN EndDate               date, --$6
    IN c_activity_id         numeric, --$7
    IN displayvoiddocuments  character varying, --$8
    IN showcurrencyexchange  character varying, --$9
    IN showonlyemptyactivity character varying, --$10
    IN ad_org_id             numeric) --$11
;

CREATE OR REPLACE FUNCTION report.fresh_account_info_report_sub(
    IN account_start_id      numeric, --$1
    IN account_end_id        numeric, --$2
    IN C_Period_Start_ID     numeric, --$3
    IN C_Period_End_ID       numeric, --$4
    IN StartDate             date, --$5
    IN EndDate               date, --$6
    IN c_activity_id         numeric, --$7
    IN displayvoiddocuments  character varying, --$8
    IN showcurrencyexchange  character varying, --$9
    IN showonlyemptyactivity character varying, --$10
    IN ad_org_id             numeric) --$11
    RETURNS TABLE
            (
                dateacct               date,
                fact_acct_id           numeric,
                bp_name                text,
                description            text,
                account2_id            text,
                a_value                text,
                amtsourcedr            numeric,
                amtsourcecr            numeric,
                amtacctdr              numeric,
                amtacctcr              numeric,
                amtacctdrend           numeric,
                amtacctcrend           numeric,
                saldo                  numeric,
                carrysaldo             numeric,
                param_acct_value       text,
                param_acct_name        text,
                param_end_date         date,
                param_start_date       date,
                param_activity_value   text,
                param_activity_name    text,
                docstatus              text,
                conversionmultiplyrate numeric,
                eurosaldo              numeric,
                containseur            boolean,
                ad_org_id              numeric,
                vat_code               text,
                tax_rate_name          text
            )
AS
$$
SELECT fa.DateAcct::Date,
       fa.Fact_Acct_ID,
       COALESCE(
               REPLACE(SUBSTRING(jl.description FOR POSITION('/' IN jl.description)), '/', ''),
               bp.name
           )                                                                                                                                       AS BP_Name,
       COALESCE(
               REPLACE(SUBSTRING(jl.description FROM POSITION('/' IN jl.description)), '/', ''),
               COALESCE(tbl.name || ' ', '') || fa.Description
           )                                                                                                                                       AS Description,
       (CASE
            WHEN
                    (SELECT COUNT(0)
                     FROM Fact_Acct fa2
                     WHERE fa.ad_table_id = fa2.ad_table_id
                       AND fa2.Record_ID = fa.Record_ID
                       AND fa.Fact_Acct_id != fa2.Fact_Acct_id
                       AND (CASE WHEN fa.amtacctdr != 0 THEN fa2.amtacctcr != 0 WHEN fa.amtacctcr != 0 THEN fa2.amtacctdr != 0 ELSE FALSE END)
                       AND fa2.isActive = 'Y')
                    = 1 THEN (SELECT ev2.value || ' ' || ev2.name
                              FROM Fact_Acct fa2
                                       INNER JOIN C_ElementValue ev2 ON fa2.Account_ID = ev2.C_ElementValue_ID AND ev2.isActive = 'Y'
                              WHERE fa.ad_table_id = fa2.ad_table_id
                                AND fa2.Record_ID = fa.Record_ID
                                AND fa.Fact_Acct_id != fa2.Fact_Acct_id
                                AND (CASE WHEN fa.amtacctdr != 0 THEN fa2.amtacctcr != 0 WHEN fa.amtacctcr != 0 THEN fa2.amtacctdr != 0 ELSE FALSE END)
                                AND fa2.isActive = 'Y')
                        ELSE ''
        END)                                                                                                                                       AS Account2_ID, -- this selects the name and value of one or no element value, that is matching with the current fact_acct (see when you press verbucht on your docu there is more than 1 line). Later shall be changed in some way so it can selece more, but currently we cannot associate more

       a.value                                                                                                                                     AS A_Value,
       fa.amtsourcedr,
       fa.amtsourcecr,
       fa.AmtAcctDr,
       fa.AmtAcctCr,
       SUM(fa.AmtAcctDr) OVER (PARTITION BY param_acct_value)                                                                                      AS AmtAcctDrEnd,
       SUM(fa.AmtAcctCr) OVER (PARTITION BY param_acct_value)                                                                                      AS AmtAcctCrEnd,
       CarryBalance + SUM(Balance) OVER (
           PARTITION BY fa.Account_ID
           ORDER BY fa.Account_ID, fa.DateAcct, fa.Fact_Acct_ID
           )                                                                                                                                       AS IterativeBalance,
       CarryBalance,
       param_acct_value,
       param_acct_name,

       COALESCE($6::date, (SELECT enddate::date FROM C_Period WHERE C_Period_ID = $4 AND isActive = 'Y'))                                          AS Param_End_Date,
       COALESCE($5::date, (SELECT startdate::date FROM C_Period WHERE C_Period_ID = $3 AND isActive = 'Y'))                                        AS Param_Start_Date,

       pa.value                                                                                                                                    AS Param_Activity_Value,
       pa.Name                                                                                                                                     AS Param_Activit_Name,
       fa.docStatus,
       ConversionMultiplyRate,
       CASE WHEN $9 = 'Y' AND ConversionMultiplyRate IS NOT NULL THEN ConversionMultiplyRate * (CarryBalance + SUM(Balance) OVER ()) ELSE NULL END AS EuroSaldo,
       containsEUR,
       fa.ad_org_id,
       fa.vat_code,
       fa.tax_rate_name
FROM (SELECT fa.Account_ID,
             fa.C_Activity_ID,
             fa.description,
             DateAcct,
             Fact_Acct_ID,
             AD_Table_ID,
             Line_ID,
             amtsourcedr,
             amtsourcecr,
             AmtAcctDr,
             AmtAcctCr,
             fa.C_BPartner_ID,
             ev.value                           AS Param_Acct_Value,
             ev.name                            AS Param_Acct_Name,
             fa.Record_ID,
             COALESCE(AmtAcctDr - AmtAcctCr, 0) AS Balance,
             (de_metas_acct.acctbalanceuntildate
                 (ev.C_ElementValue_ID,
                  acs.C_AcctSchema_ID,
                  COALESCE($5::date, (SELECT startdate::date FROM C_Period WHERE C_Period_ID = $3)),
                  $11)).Balance
                                                AS CarryBalance,
             fa.DocStatus,
             --currencyrate returns the multiplyrate of the conversion rate for: currency from , currency to, date, conversion type, client id and org id
             currencyrate(
                     c.c_currency_id,
                     (SELECT C_Currency_ID FROM C_Currency WHERE ISO_Code = 'EUR' AND isActive = 'Y'),
                     COALESCE($6::date, (SELECT enddate::date FROM C_Period WHERE C_Period_ID = $4 AND isActive = 'Y')),
                     (SELECT C_ConversionType_ID FROM C_ConversionType WHERE value = 'P' AND isActive = 'Y'),
                     ci.AD_Client_ID, ci.ad_org_id)
                                                AS ConversionMultiplyRate,
             CASE
                 WHEN $9 = 'N' -- we don't need to check if the elementValue has a foreign currency

                     THEN FALSE
                     ELSE -- check if the element value is set to show the Internation currency and if this currency is EURO. Convert to EURO in this case
                     (
                         EXISTS
                             (SELECT 1 FROM C_ElementValue elv WHERE ev.C_ElementValue_ID = elv.C_ElementValue_ID AND elv.ShowIntCurrency = 'Y' AND elv.Foreign_Currency_ID = (SELECT C_Currency_ID FROM C_Currency WHERE ISO_Code = 'EUR' AND isActive = 'Y') AND elv.isActive = 'Y')
                         )
             END
                                                AS containsEUR,
             fa.ad_org_id,
             fa.vatcode                                                                       AS vat_code,
             (SELECT t.name FROM c_tax t WHERE fa.c_tax_id = t.c_tax_id AND t.isactive = 'Y') AS tax_rate_name
      FROM (SELECT ev.C_ElementValue_ID, ev.value, ev.name, ev.ad_client_id
            FROM C_ElementValue ev
                     JOIN C_ElementValue ev_from ON ev_from.C_ElementValue_ID = $1 AND ev_from.isActive = 'Y'
                     JOIN C_ElementValue ev_to ON ev_to.C_ElementValue_ID = $2 AND ev_to.isActive = 'Y'
            WHERE ev.value >= ev_from.value
              AND ev.value <= ev_to.value
              AND CHAR_LENGTH(ev.value) >= CHAR_LENGTH(ev_from.value)
              AND CHAR_LENGTH(ev.value) <= CHAR_LENGTH(ev_to.value)
              AND ev.isActive = 'Y' --getting elements between the selected values

           ) ev

               LEFT OUTER JOIN Fact_Acct fa ON fa.Account_ID = ev.C_ElementValue_ID
          AND DateAcct >= COALESCE($5::date, (SELECT startdate::date FROM C_Period WHERE C_Period_ID = $3 AND isActive = 'Y'))
          AND DateAcct <= COALESCE($6::date, (SELECT enddate::date FROM C_Period WHERE C_Period_ID = $4 AND isActive = 'Y'))
          AND (CASE WHEN $7 IS NOT NULL THEN COALESCE(C_Activity_ID = $7, FALSE) ELSE TRUE END) -- this used to be COALESCE( C_Activity_ID = $7, true) and it was showing the empty ones too when activity id was set
          AND fa.isActive = 'Y'

          --taking the currency of the client to convert it into euro
               LEFT OUTER JOIN AD_ClientInfo ci ON ci.AD_Client_ID = ev.ad_client_id AND ci.isActive = 'Y'
               LEFT OUTER JOIN C_AcctSchema acs ON acs.C_AcctSchema_ID = ci.C_AcctSchema1_ID AND acs.isActive = 'Y'
               LEFT OUTER JOIN C_Currency c ON acs.C_Currency_ID = c.C_Currency_ID AND c.isActive = 'Y'

               LEFT OUTER JOIN C_Period period_LastYearEnd ON (period_LastYearEnd.C_Period_ID = report.Get_Predecessor_Period_Recursive($3, (SELECT PeriodNo::int FROM C_Period WHERE C_Period_ID = $3 AND isActive = 'Y'))) AND period_LastYearEnd.isActive = 'Y'
      WHERE fa.postingtype IN ('A', 'Y') -- task 09804 don't show/sum other than current (A) -- now we have other current Y (year end)
     ) fa
         LEFT OUTER JOIN GL_JournalLine jl ON fa.Line_ID = jl.GL_JournalLine_ID AND fa.AD_Table_ID = (SELECT Get_Table_ID('GL_Journal')) AND jl.isActive = 'Y'
         LEFT OUTER JOIN AD_Table tbl ON fa.AD_Table_ID = tbl.AD_Table_ID AND tbl.isActive = 'Y'
         LEFT OUTER JOIN C_BPartner bp ON fa.C_BPartner_ID = bp.C_BPartner_ID AND bp.isActive = 'Y'
         LEFT OUTER JOIN C_Activity a ON fa.C_Activity_ID = a.C_Activity_ID AND a.isActive = 'Y'
         LEFT OUTER JOIN C_Activity pa ON COALESCE(pa.C_Activity_ID = $7, FALSE) AND pa.isActive = 'Y'

WHERE CASE
          WHEN ($8 = 'Y') THEN
              1 = 1
                          ELSE
              (
                  fa.DocStatus NOT IN ('CL', 'VO', 'RE')
                  )
      END

  AND fa.ad_org_id = $11

  AND CASE
          WHEN ($10 = 'N') THEN
              1 = 1
                           ELSE
              (
                  fa.C_Activity_ID IS NULL
                  )
      END

ORDER BY fa.Param_Acct_Value,
         fa.Account_ID,
         fa.DateAcct::Date,
         fa.Fact_Acct_ID
$$
    LANGUAGE sql STABLE
;


-- Function: report.fresh_account_info_report(numeric, date, date, numeric, character varying, character varying, character varying, numeric)

DROP FUNCTION IF EXISTS report.fresh_account_info_report(numeric,
                                                         date,
                                                         date,
                                                         numeric,
                                                         character varying,
                                                         character varying,
                                                         character varying,
                                                         numeric)
;

DROP FUNCTION IF EXISTS report.fresh_account_info_report(numeric,
                                                         numeric,
                                                         numeric,
                                                         numeric,
                                                         numeric,
                                                         character varying,
                                                         character varying,
                                                         character varying,
                                                         numeric)
;

DROP FUNCTION IF EXISTS  report.fresh_account_info_report(
    IN account_from_id       numeric,
    IN account_to_id         numeric,
    IN C_Period_Start_ID     numeric,
    IN C_Period_End_ID       numeric,
    IN StartDate             date,
    IN EndDate               date,
    IN c_activity_id         numeric,
    IN displayvoiddocuments  character varying,
    IN showcurrencyexchange  character varying,
    IN showonlyemptyactivity character varying,
    IN ad_org_id             numeric)
;

CREATE OR REPLACE FUNCTION report.fresh_account_info_report(
    IN account_from_id       numeric,
    IN account_to_id         numeric,
    IN C_Period_Start_ID     numeric,
    IN C_Period_End_ID       numeric,
    IN StartDate             date,
    IN EndDate               date,
    IN c_activity_id         numeric,
    IN displayvoiddocuments  character varying,
    IN showcurrencyexchange  character varying,
    IN showonlyemptyactivity character varying,
    IN ad_org_id             numeric)
    RETURNS TABLE
            (
<<<<<<< HEAD
                dateacct             date,
                fact_acct_id         numeric,
                bp_name              text,
                description          text,
                account2_id          text,
                a_value              text,
                amtsourcedr          numeric,
                amtsourcecr          numeric,
                amtacctdr            numeric,
                amtacctcr            numeric,
                saldo                numeric,
                param_acct_value     text,
                param_acct_name      text,
                param_end_date       date,
                param_start_date     date,
                param_activity_value text,
                param_activity_name  text,
                overallcount         bigint,
                unionorder           integer,
                docstatus            text,
                eurosaldo            numeric,
                containseur          boolean,
                ad_org_id            numeric,
                vat_code             text,
                tax_rate_name        text
            )
AS
$$
SELECT DateAcct,
       Fact_Acct_ID,
       BP_Name,
       Description,
       Account2_ID,
       a_Value,
       amtsourcedr,
       amtsourcecr,
       AmtAcctDr,
       AmtAcctCr,
       Saldo,
       Param_Acct_Value,
       Param_Acct_Name,
       Param_End_Date,
       Param_Start_Date,
       Param_Activity_Value,
       Param_Activity_Name,
       COUNT(0) OVER () AS overallcount,
       2                AS UnionOrder,
       DocStatus,
       NULL::numeric,
       NULL::boolean,
       ad_org_id,
       vat_code,
       tax_rate_name
FROM report.fresh_Account_Info_Report_Sub($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11)
WHERE Fact_Acct_ID IS NOT NULL
UNION ALL
SELECT DISTINCT NULL::date,
                NULL::numeric,
                NULL,
                'Anfangssaldo',
                NULL::text,
                NULL::text,
                NULL::numeric,
                NULL::numeric,
                NULL::numeric,
                NULL::numeric,
                CarrySaldo,
                Param_Acct_Value,
                Param_Acct_Name,
                Param_End_Date,
                Param_Start_Date,
                Param_Activity_Value,
                Param_Activity_Name,
                COUNT(0) OVER () AS overallcount,
                1,
                NULL::text,
                NULL::numeric,
                NULL::boolean,
                ad_org_id,
                NULL::text,
                NULL::text
FROM report.fresh_Account_Info_Report_Sub($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11)
UNION ALL
SELECT DISTINCT NULL::date,
                NULL::numeric,
                NULL,
                'Summe',
                NULL::text,
                NULL::text,
                NULL::numeric,
                NULL::numeric,
                AmtAcctDrEnd,
                AmtAcctCrEnd,
                CarrySaldo,
                Param_Acct_Value,
                Param_Acct_Name,
                Param_End_Date,
                Param_Start_Date,
                Param_Activity_Value,
                Param_Activity_Name,
                COUNT(0) OVER () AS overallcount,
                3,
                NULL::text,
                NULL::numeric,
                NULL::boolean,
                ad_org_id,
                NULL::text,
                NULL::text
FROM report.fresh_Account_Info_Report_Sub($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11)
UNION ALL
(SELECT DISTINCT NULL::date,
                 NULL::numeric,
                 NULL,
                 'Summe in EUR',
                 NULL::text,
                 NULL::text,
                 NULL::numeric,
                 NULL::numeric,
                 NULL::numeric,
                 NULL::numeric,
                 NULL::numeric,
                 Param_Acct_Value,
                 Param_Acct_Name,
                 Param_End_Date,
                 Param_Start_Date,
                 Param_Activity_Value,
                 Param_Activity_Name,
                 COUNT(0) OVER () AS overallcount,
                 4,
                 NULL::text,
                 EuroSaldo,
                 containsEUR,
                 ad_org_id,
                 NULL::text,
                 NULL::text
 FROM report.fresh_Account_Info_Report_Sub($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11)
 WHERE containsEUR = 'Y')
ORDER BY Param_Acct_Value, UnionOrder, DateAcct,
         Fact_Acct_ID
$$
    LANGUAGE sql STABLE
=======
                dateacct                date,
                fact_acct_id            numeric,
                bp_name                 text,
                description             text,
                account2_id             text,
                a_value                 text,
                amtsourcedr             numeric,
                amtsourcecr             numeric,
                source_currency         text,
                amtacctdr               numeric,
                amtacctcr               numeric,
                saldo                   numeric,
                param_acct_value        text,
                param_acct_name         text,
                param_end_date          date,
                param_start_date        date,
                param_activity_value    text,
                param_activity_name     text,
                OverallCount            bigint,
                UnionOrder              integer,
                DocStatus               text,
                EuroSaldo               numeric,
                ContainsEUR             boolean,
                ad_org_id               numeric,
                vat_code                text,
                tax_rate_name           text,
                --
                beginingbalance1        numeric,
                sourcebalance1          numeric,
                sourcebalance_currency1 text,
                beginingbalance2        numeric,
                sourcebalance2          numeric,
                sourcebalance_currency2 text,
                beginingbalance3        numeric,
                sourcebalance3          numeric,
                sourcebalance_currency3 text,
                beginingbalance4        numeric,
                sourcebalance4          numeric,
                sourcebalance_currency4 text,
                beginingbalance5        numeric,
                sourcebalance5          numeric,
                sourcebalance_currency5 text
            )
AS

$BODY$
DECLARE
    v_rowcount            numeric;
    v_StartDate_Effective date;
    v_EndDate_Effective   date;
    v_EUR_Currency_ID     numeric;
    v_AcctSchemaInfo      record;
    v_activityInfo        record;
BEGIN
    --
    -- Get effective Start and End dates
    IF (p_StartDate IS NOT NULL) THEN
        v_StartDate_Effective = p_StartDate;
    ELSIF (p_C_Period_Start_ID IS NOT NULL) THEN
        SELECT p.startdate::date INTO v_StartDate_Effective FROM C_Period p WHERE C_Period_ID = p_C_Period_Start_ID;
    END IF;

    IF (p_EndDate IS NOT NULL) THEN
        v_EndDate_Effective = p_EndDate;
    ELSIF (p_C_Period_End_ID IS NOT NULL) THEN
        SELECT p.enddate::date INTO v_EndDate_Effective FROM C_Period p WHERE C_Period_ID = p_C_Period_End_ID;
    END IF;

    --
    -- Get EUR currency
    SELECT C_Currency_ID INTO v_EUR_Currency_ID FROM C_Currency WHERE ISO_Code = 'EUR' AND isActive = 'Y';

    --
    -- Get Accounting Schema Info
    SELECT acs.c_acctschema_id,
           acs.c_currency_id,
           currencyrate(
                   acs.c_currency_id, -- acct currency
                   v_EUR_Currency_ID,
                   v_EndDate_Effective,
                   (SELECT C_ConversionType_ID FROM C_ConversionType WHERE value = 'P' AND isActive = 'Y'),
                   ci.AD_Client_ID, ci.ad_org_id)
               AS acct2eur_MultiplyRate
    INTO v_AcctSchemaInfo
    FROM AD_Client c
             LEFT OUTER JOIN AD_ClientInfo ci ON ci.AD_Client_ID = c.ad_client_id
             LEFT OUTER JOIN C_AcctSchema acs ON acs.C_AcctSchema_ID = ci.C_AcctSchema1_ID
    WHERE c.AD_Client_ID = 1000000;

    --
    -- Get filter by Activity Info
    SELECT a.c_activity_id, a.value, a.name
    INTO v_activityInfo
    FROM c_activity a
    WHERE a.c_activity_id = p_c_activity_id;


    RAISE NOTICE 'Date=%->%, AcctSchema=%, EUR_Currency_ID=%, Param_Activity=%',
        v_StartDate_Effective, v_EndDate_Effective, v_AcctSchemaInfo,v_EUR_Currency_ID, v_activityInfo;


    --
    -- Getting elements between the selected values
    DROP TABLE IF EXISTS tmp_accounts;
    CREATE TEMPORARY TABLE tmp_accounts AS
    SELECT ev.C_ElementValue_ID, ev.value, ev.name
    FROM C_ElementValue ev
             JOIN C_ElementValue ev_from ON ev_from.C_ElementValue_ID = $1 AND ev_from.isActive = 'Y'
             JOIN C_ElementValue ev_to ON ev_to.C_ElementValue_ID = $2 AND ev_to.isActive = 'Y'
    WHERE ev.value >= ev_from.value
      AND ev.value <= ev_to.value
      AND CHAR_LENGTH(ev.value) >= CHAR_LENGTH(ev_from.value)
      AND CHAR_LENGTH(ev.value) <= CHAR_LENGTH(ev_to.value)
      AND ev.isActive = 'Y';
    GET DIAGNOSTICS v_rowcount = ROW_COUNT;
    RAISE NOTICE 'Selected % accounts', v_rowcount;

    --
    -- Compute Beginning Balance for each account
    DROP TABLE IF EXISTS tmp_beginning_balances;
    CREATE TEMPORARY TABLE tmp_beginning_balances AS
    SELECT a.C_ElementValue_ID,
           (de_metas_acct.acctBalanceUntilDate(a.C_ElementValue_ID, v_AcctSchemaInfo.C_AcctSchema_ID, v_StartDate_Effective, p_AD_Org_ID)).Balance
               AS Beginning_Balance,
           de_metas_acct.sourceBalanceUntilDate(a.C_ElementValue_ID, v_AcctSchemaInfo.C_AcctSchema_ID, v_StartDate_Effective, p_ad_org_id)
               AS Beginning_SourceBalance
    FROM tmp_accounts a;
    GET DIAGNOSTICS v_rowcount = ROW_COUNT;
    RAISE NOTICE 'Computed beginning balances for % accounts', v_rowcount;
    CREATE UNIQUE INDEX ON tmp_beginning_balances (C_ElementValue_ID);


    --
    -- Prepare actual report
    DROP TABLE IF EXISTS tmp_report;
    CREATE TEMPORARY TABLE tmp_report
    AS
    SELECT fa.DateAcct::Date,
           fa.Fact_Acct_ID,
           COALESCE(
                   REPLACE(SUBSTRING(jl.description FOR POSITION('/' IN jl.description)), '/', ''),
                   bp.name
               )                                                                                                           AS BP_Name,
           COALESCE(
                   REPLACE(SUBSTRING(jl.description FROM POSITION('/' IN jl.description)), '/', ''),
                   COALESCE(tbl.name || ' ', '') || fa.Description
               )                                                                                                           AS Description,

           -- this selects the name and value of one or no element value, that is matching with the current fact_acct (see when you press verbucht on your docu there is more than 1 line). Later shall be changed in some way so it can selece more, but currently we cannot associate more
           (CASE
                WHEN
                        (SELECT COUNT(0)
                         FROM Fact_Acct fa2
                         WHERE fa.ad_table_id = fa2.ad_table_id
                           AND fa2.Record_ID = fa.Record_ID
                           AND fa.Fact_Acct_id != fa2.Fact_Acct_id
                           AND (CASE WHEN fa.amtacctdr != 0 THEN fa2.amtacctcr != 0 WHEN fa.amtacctcr != 0 THEN fa2.amtacctdr != 0 ELSE FALSE END)
                           AND fa2.isActive = 'Y')
                        = 1 THEN (SELECT ev2.value || ' ' || ev2.name
                                  FROM Fact_Acct fa2
                                           INNER JOIN C_ElementValue ev2 ON fa2.Account_ID = ev2.C_ElementValue_ID AND ev2.isActive = 'Y'
                                  WHERE fa.ad_table_id = fa2.ad_table_id
                                    AND fa2.Record_ID = fa.Record_ID
                                    AND fa.Fact_Acct_id != fa2.Fact_Acct_id
                                    AND (CASE WHEN fa.amtacctdr != 0 THEN fa2.amtacctcr != 0 WHEN fa.amtacctcr != 0 THEN fa2.amtacctdr != 0 ELSE FALSE END)
                                    AND fa2.isActive = 'Y')
                            ELSE ''
            END)                                                                                                           AS Account2_ID,

           CAST(a.value AS text)                                                                                           AS Activity_Value,
           fa.AmtSourceDr,
           fa.AmtSourceCr,
           fa.AmtAcctDr,
           fa.AmtAcctCr,
           SUM(fa.AmtAcctDr) OVER (PARTITION BY fa.Account_ID ORDER BY fa.DateAcct, fa.Fact_Acct_ID)                       AS AmtAcctDrEnd,
           SUM(fa.AmtAcctCr) OVER (PARTITION BY fa.Account_ID ORDER BY fa.DateAcct, fa.Fact_Acct_ID)                       AS AmtAcctCrEnd,
           fa.Beginning_Balance + SUM(LineBalance) OVER (PARTITION BY fa.Account_ID ORDER BY fa.DateAcct, fa.Fact_Acct_ID) AS Rolling_Balance,
           fa.Beginning_Balance                                                                                            AS Beginning_Balance,
           CAST(fa.param_acct_value AS text),
           CAST(fa.param_acct_name AS text),

           v_activityInfo.Value::text                                                                                      AS Param_Activity_Value,
           v_activityInfo.Name::text                                                                                       AS Param_Activity_Name,
           fa.DocStatus::text,
           v_AcctSchemaInfo.acct2eur_MultiplyRate                                                                          AS ConversionMultiplyRate,
           (CASE
                WHEN p_showcurrencyexchange = 'Y' AND v_AcctSchemaInfo.acct2eur_MultiplyRate IS NOT NULL
                    THEN v_AcctSchemaInfo.acct2eur_MultiplyRate * (fa.Beginning_Balance + SUM(LineBalance) OVER ())
            END)                                                                                                           AS Rolling_Balance_EUR,
           fa.ContainsEUR,
           fa.AD_Org_ID,
           fa.vat_code::text,
           fa.tax_rate_name::text,
           fa.account_id,
           fa.source_currency_id                                                                                           AS source_currency_id,
           (SELECT cy.iso_code FROM c_currency cy WHERE cy.c_currency_id = fa.source_currency_id)::text                    AS source_currency,
           de_metas_acct.SourceBalanceAmt_add(
                   fa.Beginning_SourceBalance,
                   SUM(de_metas_acct.to_SourceBalanceAmt(fa.LineSourceBalance, fa.source_currency_id))
                   OVER (PARTITION BY fa.Account_ID ORDER BY fa.DateAcct, fa.Fact_Acct_ID)
               )
                                                                                                                           AS Rolling_SourceBalance,
           fa.Beginning_SourceBalance                                                                                      AS Beginning_SourceBalance,
           NULL::numeric                                                                                                   AS beginingbalance1,
           NULL::numeric                                                                                                   AS sourcebalance1,
           NULL::text                                                                                                      AS sourcebalance_currency1,
           NULL::numeric                                                                                                   AS beginingbalance2,
           NULL::numeric                                                                                                   AS sourcebalance2,
           NULL::text                                                                                                      AS sourcebalance_currency2,
           NULL::numeric                                                                                                   AS beginingbalance3,
           NULL::numeric                                                                                                   AS sourcebalance3,
           NULL::text                                                                                                      AS sourcebalance_currency3,
           NULL::numeric                                                                                                   AS beginingbalance4,
           NULL::numeric                                                                                                   AS sourcebalance4,
           NULL::text                                                                                                      AS sourcebalance_currency4,
           NULL::numeric                                                                                                   AS beginingbalance5,
           NULL::numeric                                                                                                   AS sourcebalance5,
           NULL::text                                                                                                      AS sourcebalance_currency5
    FROM (SELECT fa.Account_ID,
                 ev.value                                                                                                        AS Param_Acct_Value,
                 ev.name                                                                                                         AS Param_Acct_Name,
                 fa.C_Activity_ID,
                 fa.description,
                 fa.DateAcct,
                 fa.Fact_Acct_ID,
                 fa.AD_Table_ID,
                 fa.Record_ID,
                 fa.Line_ID,
                 fa.C_BPartner_ID,
                 fa.amtsourcedr,
                 fa.amtsourcecr,
                 fa.c_currency_id                                                                                                AS source_currency_id,
                 fa.AmtAcctDr,
                 fa.AmtAcctCr,
                 COALESCE(fa.AmtAcctDr - fa.AmtAcctCr, 0)                                                                        AS LineBalance,
                 COALESCE(fa.AmtSourceDr - fa.AmtSourceCr, 0)                                                                    AS LineSourceBalance,
                 (SELECT Beginning_Balance FROM tmp_beginning_balances b WHERE b.C_ElementValue_ID = ev.C_ElementValue_ID)       AS Beginning_Balance,
                 (SELECT Beginning_SourceBalance FROM tmp_beginning_balances b WHERE b.C_ElementValue_ID = ev.C_ElementValue_ID) AS Beginning_SourceBalance,
                 fa.DocStatus,
                 CASE
                     WHEN p_showcurrencyexchange = 'N' -- we don't need to check if the elementValue has a foreign currency
                         THEN FALSE
                         ELSE -- check if the element value is set to show the Internation currency and if this currency is EURO. Convert to EURO in this case
                         (
                             EXISTS
                                 (SELECT 1 FROM C_ElementValue elv WHERE ev.C_ElementValue_ID = elv.C_ElementValue_ID AND elv.ShowIntCurrency = 'Y' AND elv.Foreign_Currency_ID = v_EUR_Currency_ID AND elv.isActive = 'Y')
                             )
                 END                                                                                                             AS ContainsEUR,
                 fa.ad_org_id,
                 fa.vatcode                                                                                                      AS vat_code,
                 (SELECT t.name FROM c_tax t WHERE fa.c_tax_id = t.c_tax_id)                                                     AS tax_rate_name

          FROM tmp_accounts ev
                   LEFT OUTER JOIN Fact_Acct fa ON fa.Account_ID = ev.C_ElementValue_ID
              AND fa.DateAcct >= v_StartDate_Effective
              AND fa.DateAcct <= v_EndDate_Effective
              AND fa.ad_org_id = p_ad_org_id
              AND (p_c_activity_id IS NULL OR fa.c_activity_id = p_c_activity_id)
              AND (CASE WHEN (p_displayvoiddocuments = 'Y') THEN TRUE ELSE fa.DocStatus NOT IN ('CL', 'VO', 'RE') END)
              AND (CASE WHEN (p_showonlyemptyactivity = 'N') THEN TRUE ELSE fa.C_Activity_ID IS NULL END)
          WHERE --
                fa.PostingType IN ('A', 'Y') -- task 09804 don't show/sum other than current (A) -- now we have other current Y (year end)
         ) fa
             LEFT OUTER JOIN GL_JournalLine jl ON fa.Line_ID = jl.GL_JournalLine_ID AND fa.AD_Table_ID = (SELECT Get_Table_ID('GL_Journal')) AND jl.isActive = 'Y'
             LEFT OUTER JOIN AD_Table tbl ON fa.AD_Table_ID = tbl.AD_Table_ID AND tbl.isActive = 'Y'
             LEFT OUTER JOIN C_BPartner bp ON fa.C_BPartner_ID = bp.C_BPartner_ID AND bp.isActive = 'Y'
             LEFT OUTER JOIN C_Activity a ON fa.C_Activity_ID = a.C_Activity_ID AND a.isActive = 'Y';
    GET DIAGNOSTICS v_rowcount = ROW_COUNT;
    RAISE NOTICE 'Created tmp_reports with % rows', v_rowcount;

    --
    --
    -- Assamble the final result
    <<RESULT_TABLE>>
    BEGIN
        RETURN QUERY
            --
            -- 2
            (SELECT r.DateAcct                                                                                                   AS DateAcct,
                    r.Fact_Acct_ID                                                                                               AS Fact_Acct_ID,
                    r.BP_Name                                                                                                    AS BP_Name,
                    r.Description                                                                                                AS Description,
                    r.Account2_ID                                                                                                AS Account2_ID,
                    r.Activity_Value                                                                                             AS a_value,
                    r.amtsourcedr                                                                                                AS amtsourcedr,
                    r.amtsourcecr                                                                                                AS amtsourcecr,
                    r.source_currency                                                                                            AS source_currency,
                    r.AmtAcctDr                                                                                                  AS AmtAcctDr,
                    r.AmtAcctCr                                                                                                  AS AmtAcctCr,
                    r.Rolling_Balance                                                                                            AS Saldo,
                    r.Param_Acct_Value                                                                                           AS Param_Acct_Value,
                    r.Param_Acct_Name                                                                                            AS Param_Acct_Name,
                    v_EndDate_Effective                                                                                          AS Param_End_Date,
                    v_StartDate_Effective                                                                                        AS Param_Start_Date,
                    r.Param_Activity_Value                                                                                       AS Param_Activity_Value,
                    r.Param_Activity_Name                                                                                        AS Param_Activity_Name,
                    COUNT(0) OVER ()                                                                                             AS OverallCount,
                    2                                                                                                            AS UnionOrder,
                    r.DocStatus                                                                                                  AS DocStatus,
                    NULL::numeric                                                                                                AS EuroSaldo,
                    NULL::boolean                                                                                                AS ContainsEUR,
                    r.ad_org_id                                                                                                  AS ad_org_id,
                    r.vat_code                                                                                                   AS vat_code,
                    r.tax_rate_name                                                                                              AS tax_rate_name,
                    --
                    NULL::numeric                                                                                                AS beginingbalance1,
                    (r.Rolling_SourceBalance).balance1                                                                           AS sourcebalance1,
                    (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Rolling_SourceBalance).c_currency_id1)::text AS sourcebalance_currency1,
                    NULL::numeric                                                                                                AS beginingbalance2,
                    (r.Rolling_SourceBalance).balance2                                                                           AS sourcebalance2,
                    (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Rolling_SourceBalance).c_currency_id2)::text AS sourcebalance_currency2,
                    NULL::numeric                                                                                                AS beginingbalance3,
                    (r.Rolling_SourceBalance).balance3                                                                           AS sourcebalance3,
                    (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Rolling_SourceBalance).c_currency_id3)::text AS sourcebalance_currency3,
                    NULL::numeric                                                                                                AS beginingbalance4,
                    (r.Rolling_SourceBalance).balance4                                                                           AS sourcebalance4,
                    (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Rolling_SourceBalance).c_currency_id4)::text AS sourcebalance_currency4,
                    NULL::numeric                                                                                                AS beginingbalance5,
                    (r.Rolling_SourceBalance).balance5                                                                           AS sourcebalance5,
                    (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Rolling_SourceBalance).c_currency_id5)::text AS sourcebalance_currency5
             FROM tmp_report r
             WHERE r.Fact_Acct_ID IS NOT NULL
             ORDER BY r.Param_Acct_Value,
                      r.Account_ID,
                      r.DateAcct,
                      r.Fact_Acct_ID)
            --
            -- 1
            --
            UNION ALL
            (SELECT DISTINCT NULL::date                                  AS DateAcct,
                             NULL::numeric                               AS Fact_Acct_ID,
                             NULL                                        AS BP_Name,
                             'Anfangssaldo'                              AS Description,
                             NULL::text                                  AS account2_id,
                             NULL::text                                  AS a_value,
                             NULL::numeric                               AS amtsourcedr,
                             NULL::numeric                               AS amtsourcecr,
                             NULL::text                                  AS source_currency,
                             NULL::numeric                               AS amtacctdr,
                             NULL::numeric                               AS amtacctcr,
                             r.Beginning_Balance                         AS Saldo,
                             r.Param_Acct_Value                          AS Param_Acct_Value,
                             r.Param_Acct_Name                           AS Param_Acct_Name,
                             v_EndDate_Effective                         AS Param_End_Date,
                             v_StartDate_Effective                       AS Param_Start_Date,
                             r.Param_Activity_Value                      AS Param_Activity_Value,
                             r.Param_Activity_Name                       AS Param_Activity_Name,
                             COUNT(0) OVER ()                            AS OverallCount,
                             1                                           AS UnionOrder,
                             NULL::text                                  AS DocStatus,
                             NULL::numeric                               AS EuroSaldo,
                             NULL::boolean                               AS ContainsEUR,
                             r.ad_org_id                                 AS ad_org_id,
                             NULL::text                                  AS vat_code,
                             NULL::text                                  AS tax_rate_name,
                             --
                             (r.Beginning_SourceBalance).balance1        AS beginingbalance1,
                             NULL::numeric                               AS sourcebalance1,
                             (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Beginning_SourceBalance).c_currency_id1)::text AS sourcebalance_currency1,

                             (r.Beginning_SourceBalance).balance2        AS beginingbalance2,
                             NULL::numeric                               AS sourcebalance2,
                             (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Beginning_SourceBalance).c_currency_id2)::text AS sourcebalance_currency2,

                             (r.Beginning_SourceBalance).balance3        AS beginingbalance3,
                             NULL::numeric                               AS sourcebalance3,
                             (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Beginning_SourceBalance).c_currency_id3)::text AS sourcebalance_currency3,

                             (r.Beginning_SourceBalance).balance4        AS beginingbalance4,
                             NULL::numeric                               AS sourcebalance4,
                             (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Beginning_SourceBalance).c_currency_id4)::text AS sourcebalance_currency4,

                             (r.Beginning_SourceBalance).balance2        AS beginingbalance5,
                             NULL::numeric                               AS sourcebalance5,
                             (SELECT c.iso_code FROM c_currency c WHERE c.c_currency_id = (r.Beginning_SourceBalance).c_currency_id5)::text AS sourcebalance_currency5
             FROM tmp_report r)
            --
            -- 3
            --
            UNION ALL
            (SELECT DISTINCT NULL::date             AS DateAcct,
                             NULL::numeric          AS fact_acct_id,
                             NULL                   AS BP_Name,
                             'Summe'                AS description,
                             NULL::text             AS account2_id,
                             NULL::text             AS a_value,
                             NULL::numeric          AS amtsourcedr,
                             NULL::numeric          AS amtsourcecr,
                             NULL::text             AS source_currency,
                             r.AmtAcctDrEnd         AS amtacctdr,
                             r.AmtAcctCrEnd         AS amtacctcr,
                             r.Beginning_Balance    AS saldo,
                             r.Param_Acct_Value     AS Param_Acct_Value,
                             r.Param_Acct_Name      AS Param_Acct_Name,
                             v_EndDate_Effective    AS Param_End_Date,
                             v_StartDate_Effective  AS Param_Start_Date,
                             r.Param_Activity_Value AS Param_Activity_Value,
                             r.Param_Activity_Name  AS Param_Activity_Name,
                             COUNT(0) OVER ()       AS OverallCount,
                             3                      AS UnionOrder,
                             NULL::text             AS DocStatus,
                             NULL::numeric          AS EuroSaldo,
                             NULL::boolean          AS ContainsEUR,
                             r.ad_org_id            AS ad_org_id,
                             NULL::text             AS vat_code,
                             NULL::text             AS tax_rate_name,
                             --
                             NULL::numeric          AS beginingbalance1,
                             NULL::numeric          AS sourcebalance1,
                             NULL::text             AS sourcebalance_currency1,
                             NULL::numeric          AS beginingbalance2,
                             NULL::numeric          AS sourcebalance2,
                             NULL::text             AS sourcebalance_currency2,
                             NULL::numeric          AS beginingbalance3,
                             NULL::numeric          AS sourcebalance3,
                             NULL::text             AS sourcebalance_currency3,
                             NULL::numeric          AS beginingbalance4,
                             NULL::numeric          AS sourcebalance4,
                             NULL::text             AS sourcebalance_currency4,
                             NULL::numeric          AS beginingbalance5,
                             NULL::numeric          AS sourcebalance5,
                             NULL::text             AS sourcebalance_currency5
             FROM tmp_report r)
            --
            -- 4
            --
            UNION ALL
            (SELECT DISTINCT NULL::date             AS DateAcct,
                             NULL::numeric          AS fact_acct_id,
                             NULL                   AS BP_Name,
                             'Summe in EUR'         AS Description,
                             NULL::text             AS account2_id,
                             NULL::text             AS a_value,
                             NULL::numeric          AS amtsourcedr,
                             NULL::numeric          AS amtsourcecr,
                             NULL::text             AS source_currency,
                             NULL::numeric          AS amtacctdr,
                             NULL::numeric          AS amtacctcr,
                             NULL::numeric          AS Saldo,
                             r.Param_Acct_Value     AS Param_Acct_Value,
                             r.Param_Acct_Name      AS Param_Acct_Name,
                             v_EndDate_Effective    AS Param_End_Date,
                             v_StartDate_Effective  AS Param_Start_Date,
                             r.Param_Activity_Value AS Param_Activity_Value,
                             r.Param_Activity_Name  AS Param_Activity_Name,
                             COUNT(0) OVER ()       AS OverallCount,
                             4                      AS UnionOrder,
                             NULL::text             AS DocStatus,
                             r.Rolling_Balance_EUR  AS EuroSaldo,
                             r.containsEUR          AS ContainsEUR,
                             r.ad_org_id            AS ad_org_id,
                             NULL::text             AS vat_code,
                             NULL::text             AS tax_rate_name,
                             --
                             NULL::numeric          AS beginingbalance1,
                             NULL::numeric          AS sourcebalance1,
                             NULL::text             AS sourcebalance_currency1,
                             NULL::numeric          AS beginingbalance2,
                             NULL::numeric          AS sourcebalance2,
                             NULL::text             AS sourcebalance_currency2,
                             NULL::numeric          AS beginingbalance3,
                             NULL::numeric          AS sourcebalance3,
                             NULL::text             AS sourcebalance_currency3,
                             NULL::numeric          AS beginingbalance4,
                             NULL::numeric          AS sourcebalance4,
                             NULL::text             AS sourcebalance_currency4,
                             NULL::numeric          AS beginingbalance5,
                             NULL::numeric          AS sourcebalance5,
                             NULL::text             AS sourcebalance_currency5
             FROM tmp_report r
             WHERE r.ContainsEUR = 'Y')
            ORDER BY Param_Acct_Value, UnionOrder, DateAcct, Fact_Acct_ID;
    END RESULT_TABLE;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
>>>>>>> c394ff70a04 (Show begining balance in source currency in Accounting Info report (#15628))
;
