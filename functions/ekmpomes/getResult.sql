CREATE OR REPLACE FUNCTION getResult(text) 
RETURNS setof ekpompes AS
 $$
    Select * from ekpompes where name = ( 
    CASE
        WHEN $1 is null then name else $1 
        end)
$$  LANGUAGE sql

