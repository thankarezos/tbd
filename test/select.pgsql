SELECT account();
SELECT esoda();
SELECT ekpompes();
SELECT expenses();
SELECT program_pr();
SELECT staff();
SELECT syntelestes();

SELECT DROPdb();
SELECT DROPekpompes();
SELECT createekpompes();
SELECT ekpompes();
SELECT * FROM getekpompes();
SELECT addbroadcast('gia sas','18','50');
SELECT taskperiods();

    INSERT INTO TaskPeriods
    SELECT
    1,'Task A', '20180110 10:00:00', '20180120 18:00:00'; 
    INSERT into TaskPeriods
    SELECT
    2,'Task A', '20180115 05:00:00 ', '20180130 23:00:00';
SELECT taskset();

CREATE TABLE IF NOT EXISTS taskset
    (
        sid SERIAL,
        name varchar(100),
        rating rating,
        time int,
        primary key(sid)
    );

select * from programs;




Select 1 from (
    SELECT
    CASE WHEN
    ('00:15:01' BETWEEN t2.strtime AND t2.endtime) OR
    ('00:20:00' BETWEEN t2.strtime AND t2.endtime) OR
    ('00:15:01' < t2.strtime AND '00:20:00' > t2.endtime) OR
    ('00:15:01' > t2.strtime AND '00:20:00' < t2.endtime)
    THEN
    'yes'
    ELSE
    'no'
    END AS OverLapping
    FROM programs AS t2
) AS overL
where overlapping= 'no'

select isOverlaping('0001/01/3 23:00','0001/01/4 00:00');
select isOverlaping('00:45','00:50');
select isOverlaping('00:25','00:28');
select isOverlaping('00:46','00:50');

select isOverlaping('00:28','00:46');
select isOverlaping('00:28','00:40');
select isOverlaping('00:35','00:50');
select isOverlaping('00:30','00:40');
select isOverlaping('00:31','00:39');




--getResult
SELECT * FROM getResult(NULL,NULL,NULL,50,200);
SELECT * FROM getResultSyntelestes(NULL,NULL,NULL,'Painter',NULL);
SELECT deleteEkpompi(1);
SELECT DISTINCT role FROM getSyntelestes();
SELECT rating FROM pg_type WHERE typname;
SELECT * FROM pg_type;
SELECT pg_type(rating);
SELECT unnest(enum_range(NULL::rating)) 
SELECT enum_range(NULL::rating) 

DROP TABLE IF EXISTS syntelestesekp CASCADE;
    create TABLE IF NOT EXISTS syntelestesekp
    (   
        sidek  integer,
        nameek varchar(20),
        sidsy integer,
        name varchar(20),
        surname varchar(20),
        role varchar(20),
        phoneNumber varchar(15),
        primary key(sidek,sidsy)
    );
INSERT INTO syntelestesekp (sidek,sidsy,name,surname,role,phoneNumber)
        VALUES (1,2,'Boba','The Builder','Builder','09099109346');
INSERT INTO syntelestesekp (sidek,sidsy,name,surname,role,phoneNumber)
        VALUES (1,3,'Boba','The Builder','Builder','09099109346');

INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
WHERE ekpompes.sid=1 AND syntelestes.sid=1;

INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
WHERE ekpompes.sid=2 AND syntelestes.sid=2 ;

INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
WHERE ekpompes.sid=2 AND syntelestes.sid=1 ;


INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
WHERE ekpompes.sid=1 AND syntelestes.sid=3 ;

SELECT * FROM getResultSykminus(1,NULL,NULL,NULL,NULL,NULL);

SELECT * FROM TABLE1 crossjoin TABLE2;

INSERT INTO syntelestesekp (sidek,sidsy,name,surname,role,phoneNumber)
SELECT column1, column2, column3, ...
FROM TABLE1
WHERE condition;

SELECT * FROM syntelestesekp ORDER BY sidek,sidsy;
SELECT * getResultSykminus(1,NULL,NULL,NULL,NULL,NULL); 


SELECT addSyntelestesek(3,3);
SELECT deleteEkpompi(6);
SELECT * FROM getResultSyntelestes(" + id + "," + name + "," + surname + "," + role + "," + phoneNumber + ") EXCEPT SELECT sidek FROM getResultSyntelestesek(" + this.id + "," + id + "," + name + "," + surname + "," + role + "," + phoneNumber + ") WHERE sidsy = " + id + ";
SELECT *
FROM  getSyntelestesek(1) 
[WHERE condition]

SELECT addSyntelestesek(1,1)

IF NOT EXISTS ( SELECT 1 FROM getSyntelestesek(1) WHERE sid = 1 )
BEGIN
    SELECT * FROM  getSyntelestesek(1) 
END

SELECT checkif(1,4);



DO
$do$
DECLARE
   _sql text;
BEGIN
   SELECT INTO _sql
          string_agg(format('DROP %s %s;'
                          , CASE WHEN proisagg THEN 'AGGREGATE' ELSE 'FUNCTION' END
                          , oid::regprocedure)
                   , E'\n')
   FROM   pg_proc
   WHERE  pronamespace = 'public'::regnamespace;  -- schema name here!

   IF _sql IS NOT NULL THEN
      RAISE NOTICE '%', _sql;  -- debug / check first
      -- EXECUTE _sql;         -- uncomment payload once you are sure
   ELSE 
      RAISE NOTICE 'No fuctions found in schema %', quote_ident(_schema);
   END IF;
END
$do$;

DO
$do$
DECLARE
   _sql text;
BEGIN
   

   IF _sql IS NOT NULL THEN
    --   RAISE NOTICE '%', _sql;  -- debug / check first
      EXECUTE _sql;         -- uncomment payload once you are sure
   ELSE 
      RAISE NOTICE 'No fuctions found in schema %', quote_ident(_schema);
   END IF;
END
$do$;

CREATE FUNCTION systool_generatescript_functiondelete()
    RETURNS character varying
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
Declare
_sql text;
BEGIN

    SELECT into _sql 'DROP FUNCTION ' || ns.nspname || '.' || proname 
       || '(' || oidvectortypes(proargtypes) || ');'
    FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
    WHERE ns.nspname = 'it185193'  order by proname;

    RETURN _sql;
    
END;
$BODY$;

select systool_generatescript_functiondelete();

SELECT ns.nspname || '.' || proname 
       || '(' || oidvectortypes(proargtypes) || ');'
    FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
    WHERE ns.nspname = 'it185193'

Declare @sql NVARCHAR(MAX) = N'';

SELECT @sql = @sql + N' DROP FUNCTION ' 
                   + QUOTENAME(SCHEMA_NAME(it185193)) 
                   + N'.' + QUOTENAME(name)
FROM sys.objects
WHERE type_desc LIKE '%FUNCTION%';

Exec sp_executesql @sql
GO;


DROP FUNCTION ns.nspname || '.' || proname 
       || '(' || oidvectortypes(proargtypes) || ')'
        FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
        WHERE ns.nspname = 'it185193'


DO $$ 
  DECLARE 
    r RECORD;
BEGIN
  FOR r IN 
    (
      SELECT table_name 
      FROM pg_proc
      WHERE table_schema=current_schema()
    ) 
  LOOP
     EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.table_name) || ' CASCADE';
  END LOOP;
END $$ ;

DO $$ 
  DECLARE 
    r RECORD;
BEGIN
  FOR r IN 
    (
      SELECT ns.nspname || '.' || proname 
       || '(' || oidvectortypes(proargtypes) || ')' as name
        FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
        WHERE ns.nspname = 'it185193'
    ) 
  LOOP
     EXECUTE 'DROP FUNCTION IF EXISTS ' || quote_ident(r.name) || ' CASCADE';
  END LOOP;
END $$ ;

CREATE OR REPLACE FUNCTION drop()
 RETURNS setof t
 LANGUAGE plpgsql
AS $function$
    BEGIN
        
    END;
$function$;

CREATE OR REPLACE FUNCTION drop() RETURNS SETOF t  AS $$
    SELECT ns.nspname || '.' || proname 
       || '(' || oidvectortypes(proargtypes) || ')' AS name
    FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
    WHERE ns.nspname = 'it185193' 
$$  LANGUAGE sql;