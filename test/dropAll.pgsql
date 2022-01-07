CREATE TEMP TABLE dropF (
   name text
);


INSERT INTO dropF (name) SELECT format('DROP FUNCTION %I.%I(%s) CASCADE;', nspname, proname, oidvectortypes(proargtypes))
FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid) 
WHERE ns.nspname = 'it175073'  order by proname;



DO $$

DECLARE

functionName text;
r RECORD;

BEGIN

FOR functionName IN SELECT name FROM dropF LOOP

RAISE NOTICE 'Analyzing %', functionName;

EXECUTE functionName;



END LOOP;

FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'it175073') LOOP
    EXECUTE 'DROP TABLE ' || quote_ident(r.tablename) || ' CASCADE';
END LOOP;

END;

$$;
