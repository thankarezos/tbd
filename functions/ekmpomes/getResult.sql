CREATE OR REPLACE FUNCTION getResult(int,text,rating,int) 
RETURNS setof ekpompes AS
 $$
    Select * from ekpompes 
    where sid = ( 
    CASE
        WHEN $1 is null then sid else $1 
    end
    )
    AND name = ( 
    CASE
        WHEN $2 is null then name else $2 
    end
    )
    AND rating = ( 
    CASE
        WHEN $3 is null then rating else $3
    end
    )
    AND time = ( 
    CASE
        WHEN $4 is null then time else $4
    end
    )
$$  LANGUAGE sql

