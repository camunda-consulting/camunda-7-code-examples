/* 
Camunda Version: 7.3.2-ee; Oracle 12c tested
State as of: 23.02.2015 
S. Hellmann, M. Krassmann: Silpion; T. Hirsch for Haspa; Ingo Richtsmeier for Camunda;

DOC.:
--------------------------------------------------------------------------------------
Create/Replace  ROLLB_ARCHIVE_CAMUNDA_HISTORY StoreProcedure -function for ROLLBACK (RESTORE)
of archived Camunda history tables.
*/

WHENEVER SQLERROR EXIT SQL.SQLCODE;
SET SERVEROUTPUT ON;


CREATE OR REPLACE TYPE TextArrayType IS TABLE OF VARCHAR2(80);
/
 
CREATE OR REPLACE FUNCTION ROLLB_ARCHIVE_CAMUNDA_HISTORY(IN_executionId_from IN NUMBER, 
														    IN_executionId_til IN NUMBER DEFAULT 0,
														    IN_maxProcessInstances IN NUMBER DEFAULT 10)
RETURN NVARCHAR2
AS

 PRAGMA AUTONOMOUS_TRANSACTION;
 P_archiveTablesArray TextArrayType;
 P_piProcessed number;          /* Number of PI's worked out */
 P_tableName NVARCHAR2(80);     /* temp tableName */
 P_tableFields NVARCHAR2(500);  /* temp für alle tableFields einer Tabelle */
 P_query VARCHAR2(600);         /* temp query */
 P_startDate DATE;              /* start Timestamp */
 P_executionDuration number;
 P_result NVARCHAR2(400);       /* Result as String (for information) */ 

BEGIN
/* -------------------------------------------------
State as of: 23.02.2015 
S. Hellmann, M. Krassmann: Silpion; T. Hirsch for Haspa;
-- 
OLLBACK (RESTORE) of archived Camunda history tables:
	
   Pre-CONDITION:
     Create scripts executed:
      STEP-1__CamundaArchiving_MODIFY_Indexes_History_CamundaEdition.sql (Optional)
      STEP-2__CamundaArchiving_CREATE_Archive_Tables_CamundaEdition.sql
	  
   PARAMS:
     IN_executionId_from    : Rollback incl. with this STAT_EXECUTION_ID (use 0 for all)
	 
     IN_executionId_til     : Rollback until incl. STAT_EXECUTION_ID  (DEFAULT=0)
     							( -1 : use only IN_executionId_from)
     							( 0  : unlimited use range: >= IN_executionId_from 
								
     IN_maxProcessInstances : limit max. number of ProcessInstances 
							  (DEFAULT=10 for testing and to prevent archiving-rollback desaster ;-)
							   ( 0 : unlimited, all ) 
     
	HOW TO EXECUTE:  
		SET SERVEROUTPUT ON;
		DBMS_OUTPUT.enable;
	    select ROLLB_ARCHIVE_CAMUNDA_HISTORY(4711, -1, 10) from DUAL; -- restore max. 10 PI's for STAT_EXECUTION_ID = 4711
	    --DBMS_OUTPUT.disable;
	    SET SERVEROUTPUT OFF;
	
	--CHECK BEFORE/AFTER: possible data range (candidates)
   	   select count(*), min(END_TIME_), max(END_TIME_), STAT_EXECUTION_ID 
   	   from ARCHIVE_ACT_HI_PROCINST where
   	   STAT_EXECUTION_ID >= 4711
   	   --STAT_EXECUTION_ID between 4711 AND 4712;
   	   --1=1; -- alle
       group by STAT_EXECUTION_ID ORDER BY STAT_EXECUTION_ID;

   	   
    EXECUTE-Examples:
	    -- select ROLLB_ARCHIVE_CAMUNDA_HISTORY(4711, 4713, 10) from DUAL; -- Max 10 PI's, STAT_EXECUTION_ID range [4711 ..4713]
	    -- select ROLLB_ARCHIVE_CAMUNDA_HISTORY(4711, -1, 0) from DUAL;    -- all PI's to STAT_EXECUTION_ID = 4711
	    -- select ROLLB_ARCHIVE_CAMUNDA_HISTORY(4711, -1, 10) from DUAL;   -- Max 10 PI's to STAT_EXECUTION_ID = 4711
		-- select ROLLB_ARCHIVE_CAMUNDA_HISTORY(4711, 0, 10) from DUAL;    -- Max 10 PI's for STAT_EXECUTION_ID >= 4711
		-- select ROLLB_ARCHIVE_CAMUNDA_HISTORY(4711, 0, 0) from DUAL;     -- unlimit PI's for STAT_EXECUTION_ID >= 4711
		-- select ROLLB_ARCHIVE_CAMUNDA_HISTORY(0, 0, 0) from DUAL;        -- ALL


-----------------------------------------------------------------------*/
	

  P_archiveTablesArray := TextArrayType('ACT_HI_PROCINST', 'ACT_HI_ACTINST', 'ACT_HI_TASKINST', 'ACT_HI_VARINST', 'ACT_HI_DETAIL', 
											'ACT_HI_COMMENT', 'ACT_HI_ATTACHMENT', 'ACT_HI_OP_LOG', 'ACT_HI_INCIDENT');
                      
	/* START TRANSACTION */
	P_startDate := sysdate;
	
	dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]:  START EXECUTION: ' || to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') ||
	                       '; PARAMS: IN_executionId_from: ' || IN_executionId_from || 
	                       '; IN_executionId_til: ' || IN_executionId_til ||
	                       '; IN_maxProcessInstances: ' || IN_maxProcessInstances );
															
	/* 1. Truncate TMP_ARCHIVING_PROCINST */
	dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]:  Delete TMP_ARCHIVING_PROCINST ...' );
	DELETE TMP_ARCHIVING_PROCINST;
	
	/* 2. Fill TMP_ARCHIVING_PROCINST with candidates: */
	IF IN_executionId_til = -1 THEN /* IN_executionId_from only */
		P_query:= '	WHERE STAT_EXECUTION_ID = ' || IN_executionId_from;
    
	ELSIF IN_executionId_til = 0 THEN /* all from IN_executionId_from */
		P_query:= ' WHERE STAT_EXECUTION_ID >= ' || IN_executionId_from;
    
    ELSE /* between IN_executionId_from AND IN_executionId_til */
  		P_query:= ' WHERE STAT_EXECUTION_ID between '|| IN_executionId_from || ' AND ' || IN_executionId_til;
	END IF;
	
	IF IN_maxProcessInstances = 0 THEN /* all */
	
		P_query := 'INSERT INTO TMP_ARCHIVING_PROCINST '|| chr(13)||chr(10)||
	 			   '   SELECT PROC_INST_ID_, END_TIME_ FROM ARCHIVE_ACT_HI_PROCINST '|| chr(13)||chr(10)||
	  			   '   '||  P_query;
	  			   
	ELSE /* limit: IN_maxProcessInstances */
		P_query := 'INSERT INTO TMP_ARCHIVING_PROCINST ' ||chr(13)||chr(10)||
 			       ' SELECT PROC_INST_ID_, END_TIME_ FROM ( ' ||chr(13)||chr(10)||
 			       '   SELECT PROC_INST_ID_, END_TIME_ FROM ARCHIVE_ACT_HI_PROCINST ' ||chr(13)||chr(10)||
  			       '   '||  P_query || chr(13)||chr(10)||
  			       ') WHERE ROWNUM <= '|| IN_maxProcessInstances;	
	END IF;
	
	dbms_output.put_line('QUERY (before execute): /fill temp table with PI candidates/ ' || P_query);
    EXECUTE IMMEDIATE P_query;
	dbms_output.put_line('.... rows inserted into TMP_ARCHIVING_PROCINST: ' || TO_CHAR(SQL%ROWCOUNT));		
  			   
  			   
	/* 3. Check PI's im TEMP ready for ROLLBACK */
	select count(*) INTO P_piProcessed FROM TMP_ARCHIVING_PROCINST;
	
	IF P_piProcessed = 0 THEN /* no candidates found */
		dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]: NO ProcessInstance-Candidates for archive-Rollback found! ');
		dbms_output.put_line('Try TA-ROLLBACK ...');
    	ROLLBACK;  /*-- TMP_ARCHIVING_PROCINST un-Delete */
    	dbms_output.put_line('TA-ROLLBACK DONE! ...' ||chr(13)||chr(10));
    	
    	P_result := '[ROLLB_ARCHIVE_CAMUNDA_HISTORY]:  NO ProcessInstance candidates for archive-Rollback found!'|| chr(13)||chr(10)||
    				to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') || chr(13)||chr(10)||
              		' Used PARAMS: IN_executionId_from: ' || IN_executionId_from || 
	                       '; IN_executionId_til: ' || IN_executionId_til ||
	                       '; IN_maxProcessInstances: ' || IN_maxProcessInstances;
    	
   		RETURN 'RESULT: ' || P_result;	  
    
	ELSE
		dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]: '|| P_piProcessed ||' ProcessInstance candidates for Rollback found!');
	
		/* LOOP over tables */
		FOR i IN 1 .. P_archiveTablesArray.count LOOP 
		
			P_tableName := P_archiveTablesArray(i);
			
			dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]: #######  Start restore from:  ARCHIVE_' || P_tableName ||'  ...');
			
			P_tableFields := ''; /* reset, becouse had some problems with double columns  */
			/* fetch table column names into P_tableFields : */
			select LISTAGG(COLUMN_NAME, ', ') 
				WITHIN GROUP (ORDER BY TABLE_NAME, COLUMN_ID) INTO P_tableFields 
				from ALL_TAB_COLUMNS 
				where TABLE_NAME = P_tableName;
			
			/* INSERT */
			P_query := 'INSERT INTO '|| P_tableName  ||' hi ' ||chr(13)||chr(10)||
 					   ' SELECT ' || P_tableFields ||chr(13)||chr(10)||
 					   '  FROM ARCHIVE_' || P_tableName ||chr(13)||chr(10)|| 
 					   '  WHERE PROC_INST_ID_ in ( SELECT tmp.PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST tmp)';
 		    dbms_output.put_line('QUERY (before execute): /copy back to history table/ ' || P_query);
			EXECUTE IMMEDIATE P_query;
            dbms_output.put_line('.... rows inserted: ' || TO_CHAR(SQL%ROWCOUNT));
			
			/* DELETE */
			dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]:         Delete in Archive: ARCHIVE_' || P_tableName ||'  ...');		  	P_query := ' DELETE ' || P_tableName || ' WHERE PROC_INST_ID_ in (select PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST)';
			P_query := ' DELETE ARCHIVE_' || P_tableName || ' ar WHERE ar.PROC_INST_ID_ in (select PROC_INST_ID_ FROM TMP_ARCHIVING_PROCINST)';
		  dbms_output.put_line('QUERY (before execute): ' || P_query);
			EXECUTE IMMEDIATE P_query;
			dbms_output.put_line('.... rows deleted: ' || TO_CHAR(SQL%ROWCOUNT) || chr(13)||chr(10));
		END LOOP;
		

    	/* COMMIT TRANSACTION */
    	dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]: PIs processed: ' || P_piProcessed );
    	dbms_output.put_line('TRY TA-COMMIT ...');
   		COMMIT;
    	dbms_output.put_line('TA-COMMIT DONE!' ||chr(13)||chr(10));
    	
    	P_executionDuration := sysdate - P_startDate;
    	
    	P_result := '[ROLLB_ARCHIVE_CAMUNDA_HISTORY]:  EXECUTED (commited) successfully! ' || chr(13)||chr(10)||
    			to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') || '; Duration: ' || to_char(round(P_executionDuration*24*60*60, 1)) || ' sec.' ||  chr(13)||chr(10) ||
    			' PIs processed: ' || P_piProcessed || chr(13)||chr(10)||
    			' Used PARAMS: IN_executionId_from: ' || IN_executionId_from || 
	                       '; IN_executionId_til: ' || IN_executionId_til ||
	                       '; IN_maxProcessInstances: ' || IN_maxProcessInstances;
	                       
		dbms_output.put_line(P_result);	                       
    
    	RETURN 'RESULT: ' || P_result;	  
	END IF;
	
  
  EXCEPTION
    /* WHEN NO_DATA_FOUND THEN  */
    WHEN OTHERS THEN 
      dbms_output.put_line('[ROLLB_ARCHIVE_CAMUNDA_HISTORY]: executed with ERRORS!');
      dbms_output.put_line('ERROR! ' || SQLERRM || ' ' || DBMS_UTILITY.FORMAT_ERROR_STACK || ' ' || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);
      dbms_output.put_line('TRY TA-ROLLBACK ...');
      ROLLBACK;
      dbms_output.put_line('TA-ROLLBACK DONE!' ||chr(13)||chr(10));
      
      P_executionDuration := sysdate - P_startDate;
      
	  P_result := '[ROLLB_ARCHIVE_CAMUNDA_HISTORY]:  EXECUTED with ERRORs! ' || chr(13)||chr(10)||
			to_char(systimestamp, 'DD.MM.YYYY HH24:MI:SS ..FF3') || '; Duration: ' || to_char(round(P_executionDuration*24*60*60, 1)) || ' sec.' || chr(13)||chr(10) ||
			 ' ERROR: ' || SQLERRM || ' ' || DBMS_UTILITY.FORMAT_ERROR_STACK || ' ' || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
      
      dbms_output.put_line(P_result);
      /*--INSERT INTO errors VALUES(SQLCODE, substr(SQLERRM, 64, 1), SYSTIMESTAMP); */

       RETURN 'RESULT: ' || P_result;
END ROLLB_ARCHIVE_CAMUNDA_HISTORY;
/

quit;
/