CREATE OR REPLACE FUNCTION getResultSyntelestes(int,text,text,text,text) 
RETURNS setof syntelestes AS
 $$
    Select * from syntelestes 
    where sid = ( 
    CASE
        WHEN $1 is null then sid else $1 
    end
    )
    AND name LIKE
    CONCAT('%',
    CASE
        WHEN $2 is null then name else $2
    end,
    '%')
    AND surname LIKE
    CONCAT('%',
    CASE
        WHEN $3 is null then surname else $3
    end,
    '%')
    AND role =(
    CASE
        WHEN $4 is null then role else $4
    end
    )
    AND phoneNumber LIKE
    CONCAT('%',
    CASE
        WHEN $5 is null then phoneNumber else $5
    end
    '%')
$$  LANGUAGE sql