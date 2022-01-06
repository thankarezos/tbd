drop SCHEMA IF EXISTS logging cascade;
CREATE SCHEMA logging;
drop table if EXISTS logging.t_history;
CREATE TABLE logging.t_history (
        id serial,
        tstamp timestamp DEFAULT now(),
        schemaname text,
        tabname text,
        operation text,
        who  text,
        oldvalue text,
        newValue text
);



CREATE or REPLACE FUNCTION change_trigger() RETURNS trigger AS $$
       BEGIN
         IF TG_OP = 'INSERT'
         THEN 
         INSERT INTO logging.t_history (
                tabname, schemaname, operation,who,oldValue,newValue
              ) VALUES (
                TG_RELNAME, TG_TABLE_SCHEMA, TG_OP,(Select username from userS where sesionid = CONCAT(pg_backend_pid(),pg_postmaster_start_time())),'',
                (select new::text)
              );
           RETURN NEW;
         ELSIF  TG_OP = 'UPDATE'
         THEN
           INSERT INTO logging.t_history (
             tabname, schemaname, operation,who,oldValue,newValue
           )
           VALUES (
               TG_RELNAME, TG_TABLE_SCHEMA, TG_OP,(Select username from userS where sesionid = CONCAT(pg_backend_pid(),pg_postmaster_start_time())),(select old::text),
               (select new::text)
           );
           RETURN NEW;
         ELSIF TG_OP = 'DELETE'
         THEN
           INSERT INTO logging.t_history
             (tabname, schemaname, operation,who,oldValue,newValue)
             VALUES (
               TG_RELNAME, TG_TABLE_SCHEMA, TG_OP,(Select username from userS where sesionid = CONCAT(pg_backend_pid(),pg_postmaster_start_time())),(select old::text),''
             );
             RETURN OLD;
         END IF;
       END;
$$ LANGUAGE 'plpgsql' SECURITY DEFINER;


drop trigger if EXISTS ek on ekpompes cascade;
CREATE TRIGGER ek after INSERT OR UPDATE OR DELETE ON ekpompes
        FOR EACH ROW EXECUTE PROCEDURE change_trigger();

drop trigger if EXISTS syn on syntelestes cascade;
CREATE TRIGGER syn after INSERT OR UPDATE OR DELETE ON syntelestes
        FOR EACH ROW EXECUTE PROCEDURE change_trigger();

drop trigger if EXISTS synekp on syntelestesekp cascade;
CREATE TRIGGER synekp after INSERT OR UPDATE OR DELETE ON syntelestesekp
        FOR EACH ROW EXECUTE PROCEDURE change_trigger();

drop trigger if EXISTS prog on programs cascade;
CREATE TRIGGER prog after INSERT OR UPDATE OR DELETE ON programs
        FOR EACH ROW EXECUTE PROCEDURE change_trigger();

select * from logging.t_history