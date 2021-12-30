CREATE OR REPLACE FUNCTION getResult(int,text,rating,int,int) 
RETURNS setof ekpompes AS
 $$
    Select * from ekpompes 
    where sid = ( 
    CASE
        WHEN $1 is null then sid else $1 
    end
    )
    AND name LIKE ( 
    CASE
        WHEN $2 is null then name else %$2% 
    end
    )
    AND rating = ( 
    CASE
        WHEN $3 is null then rating else $3
    end
    )
    AND time BETWEEN $4 AND $5
$$  LANGUAGE sql

