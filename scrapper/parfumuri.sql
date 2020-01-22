set serveroutput on;

DROP TABLE parfumuri;
CREATE TABLE parfumuri (
  id INT NOT NULL,
  poza varchar2(400),
  nume varchar2(400),
  brand varchar2(400),
  cantitate varchar2(200),
  pret varchar2(200),
  sex int,
  stoc int
)
/


DECLARE
v_fisier1 UTL_FILE.FILE_TYPE;
v_sir VARCHAR2(400);
v_poza varchar2(400);
v_nume varchar2(400);
v_brand varchar2(400);
v_cantitate varchar2(200);
v_pret varchar2(200);
v_stoc int;
i  pls_integer := 0;
v_z int :=1;
v_optiune int :=1;
BEGIN
LOOP
IF(v_optiune=1)
THEN
v_fisier1:=UTL_FILE.FOPEN('MYDIR','manFragrances.txt','R');
ELSIF (v_optiune=2)
THEN
v_fisier1:=UTL_FILE.FOPEN('MYDIR','womanFragrances.txt','R');
ELSIF (v_optiune=3)
THEN
v_fisier1:=UTL_FILE.FOPEN('MYDIR','sharedFragrances.txt','R');
ELSE
EXIT;
END IF;
LOOP
     BEGIN
        UTL_FILE.GET_LINE(v_fisier1,v_sir);
     EXCEPTION
      WHEN NO_DATA_FOUND THEN EXIT;
     END;
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_poza := substr(v_sir, 1, i - 1); 
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_nume := substr(v_sir, 1, i - 1);
    IF (v_nume='\')
    THEN
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_nume := substr(v_sir, 1, i - 2);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_nume := v_nume || substr(v_sir, 1, i - 1);
    END IF; 
     i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_brand := substr(v_sir, 1, i - 1); 
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, ',');
    v_cantitate := substr(v_sir, 2, i - 2);
     i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '"');
    v_sir := substr(v_sir, i + 1);
    i := instr(v_sir, '}');
    v_pret := substr(v_sir, 2, i - 2);  
    v_stoc := FLOOR(DBMS_RANDOM.VALUE(5,201));
    insert into parfumuri values(v_z,v_poza,v_nume,v_brand,v_cantitate,v_pret,v_optiune,v_stoc);
    commit;
    v_z := v_z+1;
END LOOP;
v_optiune := v_optiune +1;
END LOOP;
UTL_FILE.FCLOSE(v_fisier1);
delete from parfumuri where cantitate is null;
END;
